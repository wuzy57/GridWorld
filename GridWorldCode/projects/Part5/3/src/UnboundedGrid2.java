import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.*;
import java.awt.Color;

public class UnboundedGrid2<E> extends AbstractGrid<E> {
  // 用二维数组存占有者Obj
  private Object[][] occupantArray;
  // 网格大小temp变量
  private int gridSize;
  private final int factor = 2;

  public UnboundedGrid2() {
    gridSize = 16;
    occupantArray = new Object[16][16];
  }

  public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }
    
    // 指定非负数才有效
    public boolean isValid(Location loc)
    {
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }
    //获取占有者的位置
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        for (int i = 0; i < gridSize; i++) {
          for (int j = 0; j < gridSize; j++) {
            Location loc = new Location(i, j);
            if (get(loc) != null) {
              theLocations.add(loc);
            }
          }
        }

        return theLocations;
    }

    //获取当前位置obj
	public E get(Location loc)
    {
        if (loc == null) {
          throw new IllegalArgumentException("loc == null");
        }    
        // 超出网格范围是合法的，当超出时，拓展Grid
        if (loc.getRow() >= gridSize || loc.getCol() >= gridSize) {
          return null;
        }
        return (E) occupantArray[loc.getRow()][loc.getCol()];
    }
	//放入obj
    public E put(Location loc, E obj) {
    	if (!isValid(loc)) {
    		throw new IllegalArgumentException("Location " + loc + " is not valid");
    	}    
    	if (obj == null) {
    		throw new IllegalArgumentException("obj == null");
        }
        int presentSize = gridSize;
        // 拓展的数据计算
        while (loc.getRow() >= gridSize || loc.getCol() >= gridSize) {
          gridSize *= factor;
        }
        // 拓展网格
        if (gridSize != presentSize) {
          Object[][] newGrid = new Object[gridSize][gridSize];
          for (int i = 0; i < presentSize; i++) {
            System.arraycopy(occupantArray[i], 0, newGrid[i], 0, presentSize);
          }
          // 用拓展后的网格覆盖当前网格
          occupantArray = newGrid;
        }
        //加入obj到网格
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }
    //删除当前位置obj
    public E remove(Location loc)
    {
        if (!isValid(loc)) {
          throw new IllegalArgumentException("Location " + loc + " is not valid");
        }    
        // 执行移除操作
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}