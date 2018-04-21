import info.gridworld.actor.*;
import info.gridworld.grid.Location;
// Import components from gridworld.jar

public class Jumper extends Actor {
  private static final int FOURTYFIVE = 45;
  private static final int CIRCLE = 360;

  Jumper() { }

  public void act() {
    if (canMove()) {
      move();
    } else {
      turn();
    }
  }

  public void turn() {
    setDirection((getDirection() + FOURTYFIVE) % CIRCLE);
  }

  public void move() {
    Location toCheck = getLocation().getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
    moveTo(toCheck);
  }

  public boolean canMove() {
    Location toCheck = getLocation().getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
    return (getGrid().isValid(toCheck) && getGrid().get(toCheck) == null);
  }
}
