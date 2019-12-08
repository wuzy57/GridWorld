//package info.gridworld.maze;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	private Location next;
	private Location last;
	private boolean isEnd = false;
	private Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	private Integer stepCount = 0;
	private boolean hasShown = false;//final message has been shown
	
	private int []probability = new int[4]; // 0 North, 1 East, 2 South, 3 West

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
		next = null;
		// 初始化栈
		crossLocation = new Stack<ArrayList<Location>>();
		
		// 记录四个方向的可能性
		for (int i = 0; i < 4; i++) {
			probability[i] = 1;
		}
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		if (stepCount == 0) {
			ArrayList<Location> parent = new ArrayList<Location>();
			parent.add(getLocation());
			crossLocation.push(parent);
		}
		boolean willMove = canMove();
		if (isEnd) {
		//到达终点显示步数		
			if (!hasShown) {
				String msg = "Total: " + stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
		    last = getLocation();
			move();
			//步数++ 
			stepCount++;
			
			// 记录上一个位置
			ArrayList<Location> parent = crossLocation.pop();
			parent.add(getLocation());
			crossLocation.push(parent);
						
			// 存储当前位置到节点
			ArrayList<Location>  present = new ArrayList<Location>();
			present.add(getLocation());
			crossLocation.push(present);
		}
		else {
			// 移动到上一个位置
		      if (!crossLocation.empty()) {
		        // 从栈中弹出移动不了的位置
		        crossLocation.pop();
		        if (!crossLocation.empty()) {
		          Grid<Actor> grid = getGrid();
		          if (grid == null) {
		            return;
		          }
		          ArrayList<Location> parent = crossLocation.peek();
		          Location parentLocation = parent.get(0);

		          // 设置bug的方向和移动的方向相同
		          int dir = getLocation().getDirectionToward(parentLocation);
		          setDirection(dir);
		          
		          // 更新四个方向的可能性
		          int temp = dir/90;
		          updateProbability(temp);
		          
		          last = getLocation();
		          next = parentLocation;
		          
		          move();
		          stepCount++;
		        }
		      }
		}
	}
	//更新每个方向的可能性
	private void updateProbability(int temp) {
		if (temp == 0) {
		    probability[2]--;
		}
		else if (temp == 1) {
		    probability[3]--;
		}
		else if (temp == 2) {
		    probability[0]--;
		}
		else {
		    probability[1]--;
		}
	}
	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> grid = getGrid();
		if (grid == null) {
			return null;
		}
		ArrayList<Location> validLocations = new ArrayList<Location>();

	    // 查找四个方向
	    for (int i = 0; i < 4; i++) {
	      Location nextLoc = loc.getAdjacentLocation(getDirection() + i * 90); // North, East, South, West
	      
	      if (grid.isValid(nextLoc)) {
	        Actor actor = grid.get(nextLoc);

	        // 如果下一个是红色的石头，说明终止
	        if (actor instanceof Rock && actor.getColor().equals(Color.RED)) {
	          isEnd = true;
	          validLocations.add(nextLoc);
	          
	          Location present = getLocation();
	          
	          // 设置bug的方向和前进方向相同
	          int dir = getLocation().getDirectionToward(nextLoc);
	          setDirection(dir);
	          
	          moveTo(nextLoc);
	          
	          Flower flower = new Flower(getColor());
	  		  flower.putSelfInGrid(grid, present);
	  		  setColor(Color.RED);
	          
	          break;
	        }
	        
	        // 下个位置如果为null
	        else if (actor == null) {
	          validLocations.add(nextLoc);
	        }
	      }
	    }
	    return validLocations;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	//能否移动
	public boolean canMove() {
		Grid<Actor> grid = getGrid();
	    if (grid == null) {
	      return false;
	    }
	    //获取当前位置的下一个可移动的位置
	    ArrayList<Location> nextLocations = getValid(getLocation());
	    // 没有可移动的位置
	    if (nextLocations.size() == 0) {
	      return false;
	    }
	    else {
	    	ArrayList<Location> temp = new ArrayList<Location>();
	      for (int i = 0; i < nextLocations.size(); i++) {
	        // 如果下一个位置是石头，结束
	        if (grid.get(nextLocations.get(i)) instanceof Rock) {
	          next = nextLocations.get(i);
	          return true;
	        }
	        //选择下一个位置
	        else {
	          temp.add(nextLocations.get(i));
	        }
	      }
	      next = findBestLocation(temp);
	      return true;
	    }
	}
	//寻找四个方向
	private Location findBestLocation(ArrayList<Location> temp) {
		Location bestLocation = null;
		int maxProbability = 0;
		int dirToChoose = 0;
		int indexToChoose = 0;
		
		// 仅一个位置可以被选择
		if (temp.size() == 1) {
			int dir = getLocation().getDirectionToward(temp.get(0));
			probability[dir/90]++;
			bestLocation = temp.get(0);
		}
		//多个位置可以被选择
		else {
			boolean haveNorth = false;
			boolean haveEast = false;
			boolean haveSouth = false;
			boolean haveWest = false;
			
			Location []four = new Location[4];
			for (int i = 0; i < temp.size(); i++) {
				int dir = getLocation().getDirectionToward(temp.get(i));
				//标记每一个方向
				switch (dir) {
				case 0: haveNorth = true;
						four[0] = temp.get(i);
						break;
				case 1: haveEast = true;
						four[1] = temp.get(i);
						break;
				case 2: haveSouth = true;
						four[2] = temp.get(i);
						break;
				case 3: haveWest = true;
						four[3] = temp.get(i);
						break;
				default:
					break;
				}
			}
			
			// 随机选择
			int sum = 0;
			for (int i = 0 ; i < 4; i++) {
				sum += probability[i];
			}

			int random = (int) (Math.random() * sum);

			//North
			if  (random >= 0 && random < probability[0]) {
				dirToChoose = 0;
				if (haveNorth) {
					bestLocation = four[0];
					probability[0]++;
				}
			}
			//East
			else if (random >= probability[0] && random < (probability[0] + probability[1])) {
				dirToChoose = 1;
				if (haveEast) {
					bestLocation = four[1];
					probability[1]++;
				}
			}
			//South
			else if (random >= (probability[0] + probability[1]) && random < (probability[0] + probability[1] + probability[2])) {
				dirToChoose = 2;
				if (haveSouth) {
					bestLocation = four[2];
					probability[2]++;
				}
			}
			//West
			else if (random >= (probability[0] + probability[1] + probability[2]) && random < sum) {
				dirToChoose = 3;
				if (haveWest) {
					bestLocation = four[3];
					probability[3]++;
				}
			}
		}
		
		//没有最佳方向
		if (bestLocation == null) {
			for (int i = 0; i < temp.size(); i++) {
				int dir = getLocation().getDirectionToward(temp.get(i));
				if (probability[dir/90] > maxProbability) {
					maxProbability = probability[dir/90];
					dirToChoose = dir/90;
					indexToChoose = i;
				}
			}
			bestLocation = temp.get(indexToChoose);
			probability[dirToChoose]++;
		}
		return bestLocation;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}
		last = getLocation();
		if (gr.isValid(next)) {
			int dir = getLocation().getDirectionToward(next);
			setDirection(dir);
			probability[dir/90]++;
			moveTo(next);
		} else {
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, last);
	}
}
