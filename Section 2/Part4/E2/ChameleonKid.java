import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class ChameleonKid extends ChameleonCritter {
  private static final int FORWARD = 0;
  private static final int BACKWARD = 180;
  
  @Override
  public void processActors(ArrayList<Actor> actors)
  {
      int r = ((int) (Math.random() * 2)) % 2;
      Location[] locations = {getLocation().getAdjacentLocation(FORWARD), 
        getLocation().getAdjacentLocation(BACKWARD)};
      Actor target = null;

      for(Actor actor: actors) {
        if (actor.getLocation().equals(locations[r])) {
          target = actor;
          break;
        }
      }

      if (target == null) {
        r = (r + 1) % 2;
        for(Actor actor: actors) {
          if (actor.getLocation().equals(locations[r])) {
            target = actor;
            break;
          }
        }
      }

      if (target != null) {
        setColor(target.getColor());
      }
      else {
        setColor(getColor().darker());
      }
  }
}