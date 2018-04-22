import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.ArrayList;


public class BlusterCritter extends Critter {
  private int courage;
  private static final int EDGE = 5;
  private static final int OFFSET = -2;
  // To avoid magic numbers
  

  public BlusterCritter (int courage) {
    this.courage = courage;
  }
  // Constructor, with the int parameter courage

  public ArrayList<Actor> getActors() {
    Grid<Actor> grid = getGrid();
    ArrayList<Actor> list = new ArrayList<Actor>();
    Location location = getLocation();
    int row = location.getRow() + OFFSET,
      col = location.getCol() + OFFSET;
    /*
    Initialise some variable
    But actually
    I don't know the usage of them
    now
    */
    
    for (int i = 0; i < EDGE; ++i) {
      for (int t = 0; t < EDGE; ++t) {
        if (i != -OFFSET || t != -OFFSET) {
          if (grid.isValid(new Location(row + i, col + t))) {
            if (grid.get(new Location(row + i, col + t)) != null) {
              list.add(grid.get(new Location(row + i, col + t)) );
            }
          }
        }
      }
    }
    /*
    Get everthing
    which is within two steps
    away from the
    BlusterCritter
    */
    
    return list;
  }

  public void processActors(ArrayList<Actor> actors)
  {
      int n = actors.size();
      if (n < courage) {
        setColor(getColor().brighter());
      }else {
        setColor(getColor().darker());
      }
      /*
      If courage enough
      brighter
      else
      darker
      */
  }
}