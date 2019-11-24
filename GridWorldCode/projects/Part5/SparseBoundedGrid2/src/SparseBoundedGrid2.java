
import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.awt.Color;

public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
  // HashMap存储Location和Actor
  private Map<Location, E> occupantArray;
  private int rows, columns;
  
  // 确保Grid可用
  public SparseBoundedGrid2(int rows, int cols) {
    if (rows <= 0) {
      throw new IllegalArgumentException("rows <= 0");
    }
            
        if (cols <= 0) {
          throw new IllegalArgumentException("cols <= 0");
        }

    this.rows = rows;
    this.columns = cols;

    // 初始化一个HashMap
    occupantArray = new HashMap<Location, E>();
    
  }

  public int getNumRows() {
    return rows;
  }

  public int getNumCols() {
    return columns;
  }

  public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

  public ArrayList<Location> getOccupiedLocations() {
    ArrayList<Location> theLocations = new ArrayList<Location>();
    // key代表location，是唯一的
    for (Location key : occupantArray.keySet()) {  
    theLocations.add(key);
    }  

    return theLocations;
  }

  public E get(Location loc) {
    if (!isValid(loc)) {
      throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
    }
   // 返回当前位置的占有者
    return (E) occupantArray.get(loc);
  }

  public E put(Location loc, E obj)
    {
        if (!isValid(loc)) {
          throw new IllegalArgumentException("Location " + loc + " is not valid");
        }     
        if (obj == null) {
          throw new IllegalArgumentException("obj == null");
        }
        // 添加对象到Grid中
        E oldOccupant = get(loc);
        occupantArray.put(loc, obj);
        return oldOccupant;
    }

  public E remove(Location loc)
    {
        if (!isValid(loc)) {
          throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
            
        // 从Grid中移除对象
        E r = get(loc);
        occupantArray.remove(loc);
        return r;
    }
}
