
import info.gridworld.grid.Grid;

import java.util.ArrayList;
import java.awt.Color;

public class OccupantInCol {
  // 存储Object
  private Object occupant;
  // 存储Index
  private int colIndex;

  //构造函数
  OccupantInCol(Object object, int col) {
    this.occupant = object;
    this.colIndex = col;
  }

  //设定当前位置的占有者
  public void setOccupant(Object object) {
    this.occupant = object;
  }

  //返回当前位置的占有者
  public Object getOccupant() {
    return occupant;
  }

   //设定Index
  public void setCol(int col){
    this.colIndex = col;
  }

  //返回Index
  public int getCol() {
    return colIndex;
  }
}
