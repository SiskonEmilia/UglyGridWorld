import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import java.util.ArrayList;

public class RockHound extends Critter {
  public void processActors(ArrayList<Actor> actors)
  {
      int n = actors.size();
      for(Actor actor : actors) {
        if (actor instanceof Rock) {
          actor.removeSelfFromGrid();
        }
      }
  }
}