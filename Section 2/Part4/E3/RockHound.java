import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import java.util.ArrayList;
// Get everything I want!

/* This is the Hound which has
   Special interest over rocks
   And will remove them from
   its sight */
public class RockHound extends Critter {
  //Override this method to process actors
  public void processActors(ArrayList<Actor> actors)
  {
      for(Actor actor : actors) {
        if (actor instanceof Rock) {
          actor.removeSelfFromGrid();
        }
      }
      // if rock, remove
  }
}