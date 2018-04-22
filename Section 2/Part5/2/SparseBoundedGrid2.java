import info.gridworld.grid.*;

import java.util.ArrayList;
import java.util.HashMap;

public class SparseBoundedGrid2 extends AbstractGrid<Object> {

  private int cols;
  private int rows;
  private HashMap<Location, Object> map;

  public SparseBoundedGrid2(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    map = new HashMap<Location, Object>();
  }

  public int getNumRows() {
    return rows;
  }

  public int getNumCols() {
    return cols;
  }

  public boolean isValid(Location loc) {
    return (loc.getRow() < rows && loc.getCol() < cols && loc.getRow() >= 0 && loc.getCol() >= 0);
  }

  public Object get(Location loc) {
    if (map.containsKey(loc)) {
      return map.get(loc);
    }
    return null;
  }

  public Object put(Location loc, Object obj) {
    Object oriObj = null;
    if (map.containsKey(loc)) {
      oriObj = map.get(loc);
    }
    map.put(loc, obj);
    return oriObj;
  }

  public Object remove(Location loc) {
    if (map.containsKey(loc)) {
      Object res =  map.get(loc);
      map.remove(loc);
      return res;
    }
    return null;
  }
 
  public ArrayList<Location> getOccupiedLocations() {
    ArrayList<Location> list = new ArrayList<Location>();

    for(Location item : map.keySet()) {
      list.add(item);
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