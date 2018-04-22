import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;

public class KingCrab extends CrabCritter {
  
  /* A KingCrab causes each actor that it 
  processes to move one location further away 
  from the KingCrab. If the actor cannot move 
  away, the KingCrab removes it from the grid. */
  public void processActors(ArrayList<Actor> actors) {
    //getDirectionToward
    Location location = getLocation(), 
      tempLocation;
    for (Actor actor : actors) {
      tempLocation = actor.getLocation().getAdjacentLocation(location.getDirectionToward(actor.getLocation()));
      if (canMoveTo(tempLocation)) {
        actor.moveTo(tempLocation);
      }
      else {
        actor.removeSelfFromGrid();
      }
    }
  }

  private boolean canMoveTo(Location location) {
    return (getGrid().isValid(location) && getGrid().get(location) == null);
  }
}