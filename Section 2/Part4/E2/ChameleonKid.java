import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/* Section2 Part4 Exercise2
   This is the ChameleonKid class
   which is not so powerful. */

public class ChameleonKid extends ChameleonCritter {

  private static final int FORWARD = 0;
  private static final int BACKWARD = 180;
  // Avoid magic numbers
  
  /* Override this method to process received actors */
  @Override
  public void processActors(ArrayList<Actor> actors)
  {
      int r = ((int) (Math.random() * 2)) % 2; 
      // Random

      Location[] locations = {getLocation().getAdjacentLocation(FORWARD), 
        getLocation().getAdjacentLocation(BACKWARD)};
      // Get locations
      Actor target = null;

      for(Actor actor: actors) {
        if (actor.getLocation().equals(locations[r])) {
          target = actor;
          break;
        }
      }
      // Search for target

      if (target == null) {
        r = (r + 1) % 2;
        for(Actor actor: actors) {
          if (actor.getLocation().equals(locations[r])) {
            target = actor;
            break;
          }
        }
      }
      // If not found, change direction

      if (target != null) {
        setColor(target.getColor());
      }
      else {
        setColor(getColor().darker());
      }
      // If still not found change color
  }
}