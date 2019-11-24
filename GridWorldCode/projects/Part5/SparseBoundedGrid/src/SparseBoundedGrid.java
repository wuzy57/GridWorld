
import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Color;

public class SparseBoundedGrid<E> extends AbstractGrid<E> {
  //用ArrayList存储占有者
  private ArrayList<LinkedList<OccupantInCol>> occupantArray;
  private int rows, columns;
  
  // 确保至少有一个可用位置
  public SparseBoundedGrid(int rows, int cols) {
    if (rows <= 0) {
      throw new IllegalArgumentException("rows <= 0");
    }   
    if (cols <= 0) {
          throw new IllegalArgumentException("cols <= 0");
    }

    this.rows = rows;
    this.columns = cols;

    // 分配存储空间
    occupantArray = new ArrayList<LinkedList<OccupantInCol>>(rows);
    for (int i = 0; i < rows; i++) {
      LinkedList<OccupantInCol> temp = new LinkedList<OccupantInCol>();
      occupantArray.add(temp);
    }
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
//获取占有者的函数
  public ArrayList<Location> getOccupiedLocations() {
    ArrayList<Location> theLocations = new ArrayList<Location>();
    // 找出当前的所有占有者，并获取占有者的位置
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < occupantArray.get(r).size(); c++) {
        OccupantInCol ob = occupantArray.get(r).get(c);
         Location loc = new Location(r, ob.getCol());
         theLocations.add(loc);
      }
    }
    return theLocations;
  }

  @SuppressWarnings("unchecked")
public E get(Location loc) {
	  if (!isValid(loc)) {
		  throw new IllegalArgumentException("Location " + loc
                    	+ " is not valid");
	  }
      LinkedList<OccupantInCol> row = occupantArray.get(loc.getRow());
      // 检查当前行有没有占有者
      if (row.size() == 0) {
    	  return null;
      }
      else {
        // 查找列中有没有占有者
        for (int i = 0; i < row.size(); i++) {
          // 遍历LinkedList
        	if (row.get(i).getCol() == loc.getCol()) {
              return (E) row.get(i).getOccupant();
            }
          }
        }
    return null;
  }
  //将对象放入指定位置
  public E put(Location loc, E obj)
    {
        if (!isValid(loc)) {
          throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        } 
        if (obj == null) {
          throw new IllegalArgumentException("obj == null");
        }
        // 添加对象到Grid中
        E oldOccupant = get(loc);
        occupantArray.get(loc.getRow()).add(new OccupantInCol(obj, loc.getCol()));
        return oldOccupant;
    }
  //移除指定位置的对象
  public E remove(Location loc)
    {
        if (!isValid(loc)) {
          throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        // 移除对象
        E r = get(loc);
        LinkedList<OccupantInCol> list = occupantArray.get(loc.getRow());
        if (list.size() != 0) {
          for (int i = 0; i< list.size(); i++) {
            // 从指定列中找到占有者
            if (list.get(i).getCol() == loc.getCol()) {
              list.remove(i);
            }
          }
        }
        return r;
    }
}
