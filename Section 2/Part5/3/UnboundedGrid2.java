import info.gridworld.grid.*;
import java.util.ArrayList;

public class UnboundedGrid2 extends AbstractGrid<Object> {
  
  private static final int ZERO = 0;
  private static final int TWO = 2;
  private static final int SIXTEEN = 16;
  
  private int mySize;

  private Object[][] map;

  public UnboundedGrid2() {
    this.mySize = SIXTEEN;

    map = new Object[mySize][mySize];
  }

  public int getNumRows() {
    return -1;
  }

  public int getNumCols() {
    return -1;
  }

  public boolean isValid(Location loc) {
    return (loc.getRow() >= ZERO && loc.getCol() >= ZERO);
  }

  private void NewStorage() {
    Object[][] newMap = new Object[mySize * TWO][mySize * TWO];
    for (int i = ZERO; i < mySize; ++i) {
      for (int t = ZERO; t < mySize; ++t) {
        newMap[i][t] = map[i][t];
      }
    }
    mySize *= TWO;
    map = newMap;
  }

  public Object get(Location loc) {
    if (!isValid(loc)) {
      throw new IllegalArgumentException(loc + "is NOT A VALID LOCATION");
    }
    Object res;
    if(loc.getRow() >= mySize || loc.getCol() >= mySize) {
      res = null;
    }
    else {
      res = map[loc.getRow()][loc.getCol()];
    }
    return res;
  }

  public Object put(Location loc, Object obj) {
    if (!isValid(loc)) {
      throw new IllegalArgumentException(loc + "is NOT A VALID LOCATION");
    }
    
    Object oriObj;
    while(loc.getRow() >= mySize || loc.getCol() >= mySize) {
      NewStorage();
    }

    oriObj = map[loc.getRow()][loc.getCol()];
    map[loc.getRow()][loc.getCol()] = obj;
    return oriObj;
  }

  public Object remove(Location loc) {
    if (!isValid(loc)) {
      throw new IllegalArgumentException(loc + "is NOT A VALID LOCATION");
    }
    Object res = get(loc);

    if (res != null) {
      map[loc.getRow()][loc.getCol()] = null;
      return res;
    }

    return null;
  }

  public ArrayList<Location> getOccupiedLocations() {
    ArrayList<Location> list = new ArrayList<Location>();

    for (int i = ZERO; i < mySize; ++i) {
      for (int t = ZERO; t < mySize; ++t) {
        if (map[i][t] != null) {
          list.add(new Location(i, t));
        }
      } 
    }

    return list;
  }

  /*
  ArrayList<Location> getValidAdjacentLocations(Location loc) {
    ArrayList<Location> list = new ArrayList<Location>();

    for(int i = -ONE; i <= ONE; ++i) {
      for(int t = -ONE; t <= ONE; ++t) {
        if (i != ZERO || t != ZERO) {
          if (isValid(new Location(loc.getRow() + i, loc.getCol() + t))) {
            list.add(new Location(loc.getRow() + i, loc.getCol() + t));
          }
        }
      }
    }

    return list;
  }

  ArrayList<Location> getEmptyAdjacentLocations(Location loc) {
    ArrayList<Location> list = getValidAdjacentLocations(loc);

    for(Location location : list) {
      if (getNode(location) != null) {
        list.remove(location);
      }
    }

    return list;
  }
  */

}