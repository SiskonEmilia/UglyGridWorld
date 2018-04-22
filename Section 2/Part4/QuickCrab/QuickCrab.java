import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;

public class QuickCrab extends CrabCritter {

  private static final int ZERO = 0;
  private static final int ONE = 1;
  

  public void makeMove(Location loc) {
    Location[] locations = {getLocation().getAdjacentLocation(Location.LEFT).getAdjacentLocation(Location.LEFT), 
      getLocation().getAdjacentLocation(Location.RIGHT).getAdjacentLocation(Location.RIGHT)};
    int r = ((int) (Math.random() * 2)) % 2;

    if (getGrid().isValid(locations[ZERO]) && getGrid().isValid(locations[ONE])) {
      if (getGrid().get(locations[ZERO]) == null && getGrid().get(locations[ONE]) == null) {
        moveTo(locations[r]);
        return;
      }
    }
    
    super.makeMove(loc);
  } 
}