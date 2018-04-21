import info.gridworld.grid.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class SparseBoundedGrid extends AbstractGrid<Object> {

  // private static final int ONE = 1;
  private static final int ZERO = 0;
  
  public class OccupantInCol {
    public OccupantInCol (Object occupant, int col) {
      this.occupant = occupant;
      this.col = col;
    }
    private Object occupant;
    private int col;
  }

  private int cols;
  private int rows;
  private ArrayList<LinkedList<OccupantInCol>> occupantMap;

  public SparseBoundedGrid(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    occupantMap = new ArrayList<LinkedList<OccupantInCol>>();
    for (int i = ZERO; i < rows; ++i) {
      occupantMap.add(new LinkedList<OccupantInCol>());
    }
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

  private OccupantInCol getNode(Location loc) {
    OccupantInCol res = null;
    if (isValid(loc)) {
      for (OccupantInCol node : occupantMap.get(loc.getRow())) {
        if (node.col == loc.getCol()) {
          res = node;
          break;
        }
      }
    }
    return res;
  }

  public Object get(Location loc) {
    OccupantInCol res = getNode(loc);
    if (res == null) {
      return null;
    }
    return res.occupant;
  }

  public Object put(Location loc, Object obj) {
    OccupantInCol res = getNode(loc);

    if (res == null) {
      occupantMap.get(loc.getRow()).addLast(new OccupantInCol(obj, loc.getCol()));
    }

    return res;
  }

  public Object remove(Location loc) {
    OccupantInCol res = getNode(loc);

    if (res != null) {
      occupantMap.get(loc.getRow()).remove(res);
      return res.occupant;
    }

    return null;
  }
 
  public ArrayList<Location> getOccupiedLocations() {
    ArrayList<Location> list = new ArrayList<Location>();

    for (int i = ZERO; i < rows; ++i) {
      for (OccupantInCol item : occupantMap.get(i)) {
        list.add(new Location(i, item.col));
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