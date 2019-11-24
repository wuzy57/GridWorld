

import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * A <code>Jumper</code> jump for two cells. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Actor
{
    /**
     * Constructs a red Jumper
     */
    public Jumper()
    {
        setColor(Color.RED);
    }

    /**
     * Constructs a Jumper in a given color
     */
    public Jumper(Color jumperColor) {
        setColor(jumperColor);
    }
    
    /**
     *act method decide what a jumper will do
     */
    public void act() {
        if (canJump()) {
            jump();
        }
        else {
            turn();
        }
    }

    /**
     *when the jumper can't not jump to next location, it will turn right for 90 degrees.
     */
    public void turn() {
        setDirection(getDirection() + Location.RIGHT);
    }

  //跳跃行为的判断函数
    public boolean canJump() {
Grid<Actor> gird = getGrid();
		
		if (gird == null) 
		{
			return false;
		}
		
		Location loc = getLocation();
		Location nextPosi = loc.getAdjacentLocation(getDirection()); 
		if (!gird.isValid(nextPosi))
		{
			return false;
		}
		//nextPosi存在于grid，才能获取
		Actor neighbor = gird.get(nextPosi);
		
		Location finalPosi = nextPosi.getAdjacentLocation(getDirection()); 

		if (gird.isValid(finalPosi))
		{
			neighbor = gird.get(finalPosi);
			//如果对象具有指定的类型，则instanceof运算符返回true,这里判断移动两格后的位置是否符合要求,只有空的或者花朵时能够移动过去
    		return (neighbor == null) || (neighbor instanceof Flower);
		}
		else return false;
    }

    //跳跃行为的执行函数
    public void jump() {
    	Grid<Actor> gird = getGrid();
		if (gird == null) 
		{
			return;
		}
		
		Location loca = getLocation();
		//前进两格
		Location next = loca.getAdjacentLocation(getDirection()); 
		Location finalPosi = next.getAdjacentLocation(getDirection()); 
		//判断前进两格后的位置是否处于网格内
		if (gird.isValid(finalPosi))
		{
			moveTo(finalPosi);
		}
		else
		{
			removeSelfFromGrid();//移除Actor
		}
    }


    
}

