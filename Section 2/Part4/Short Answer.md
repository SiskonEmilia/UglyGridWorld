# Short Answer for Part 4

## Set 7

The source code for the Critter class is in the critters directory

1. What methods are implemented in Critter?

    **Answer:**

    ```java
    // @file: info/gridworld/actor/Critter.java
    // @line: 38-131
    public void act()
    public ArrayList<Actor> getActors()
    public void processActors(ArrayList<Actor> actors)
    public ArrayList<Location> getMoveLocations()
    public Location selectMoveLocation(ArrayList<Location> locs)
    public void makeMove(Location loc)
    ```

1. What are the five basic actions common to all critters when they act?

    **Answer:**

    ```java
    // @file: info/gridworld/actor/Critter.java
    // @line: 56-131
    public ArrayList<Actor> getActors()
    public void processActors(ArrayList<Actor> actors)
    public ArrayList<Location> getMoveLocations()
    public Location selectMoveLocation(ArrayList<Location> locs)
    public void makeMove(Location loc)
    ```

1. Should subclasses of Critter override the getActors method? Explain.

    **Answer:**

    It is optional, as it has been implemented in the Critter, and the subclasses of it could choose to use the original logic of getting actors.

    ```java
    // @file: info/gridworld/actor/Critter.java
    // @line: 56-59
    public ArrayList<Actor> getActors()
    {
      return getGrid().getNeighbors(getLocation());
    }
    ```

1. Describe the way that a critter could process actors.

    **Answer:** **Almost any way it want!** As the subclasses of Critter could override the `processActors(ArrayList<Actor>)` method and add its own logic in.

    ```java
    // @file: info/gridworld/actor/Critter.java
    // @line: 71
    public void processActors(ArrayList<Actor> actors)
    ```

1. What three methods must be invoked to make a critter move? Explain each of these methods.

    **Answer:** `public ArrayList<Location> getMoveLocations()`, `public Location selectMoveLocation(ArrayList<Location> locs)` and `public void makeMove(Location loc)`. The first of which is used to choose locations that the critter might move to. The second one is used to select the location that the critter will move to from the given locations. The thrid one is used to make the critter move to the `loc` location.

    ```java
    // @file: info/gridworld/actor/Critter.java
    // @line: 88-131
    public ArrayList<Location> getMoveLocations()
    public Location selectMoveLocation(ArrayList<Location> locs)
    public void makeMove(Location loc)
    ```

1. Why is there no Critter constructor?

    **Answer:** There's nothing inside that need to be initialized by its constructor.

    ```java
    // @file: info/gridworld/actor/Critter.java
    // @line: 31-133
    public class Critter extends Actor {
      /* No Data Member Inside */
    }
    ```

## Set 8

The source code for the ChameleonCritter class is in the critters directory

1. Why does act cause a ChameleonCritter to act differently from a Critter even though ChameleonCritter does not override act?

    **Answer:** Because it the unchanged `act()` calls methods might be changed, such as `getActors()` and `processActors(ArrayList<Actor>)`.

    ```java
    // @file: info/gridworld/actor/Critter.java
    // @line: 38-47
    public void act()
    {
      if (getGrid() == null)
          return;
      ArrayList<Actor> actors = getActors();
      processActors(actors);
      ArrayList<Location> moveLocs = getMoveLocations();
      Location loc = selectMoveLocation(moveLocs);
      makeMove(loc);
    }
    ```

1. Why does the makeMove method of ChameleonCritter call super.makeMove?

    **Answer:** Because they share the logic of movement. All the things that the new `makeMove(Location)` wants to do is to change the Direction of ChameleonCritter before moving.

    ```java
    // @file: projects/critters/ChameleonCritter.java
    // @line: 50-54
    public void makeMove(Location loc)
    {
      setDirection(getLocation().getDirectionToward(loc));
      super.makeMove(loc);
    }
    ```

1. How would you make the ChameleonCritter drop flowers in its old location when it moves?

    **Answer:** Override the `makeMove(Loacation)` method to the following form:

    ```java
    public void makeMove(Location loc)
    {
      setDirection(getLocation().getDirectionToward(loc));
      Location originLocation = getLocation();
      super.makeMove(loc);
      Flower flower = new Flower(getColor());
      flower.putSelfInGrid(getGrid(), originLocation);
    }
    ```

    Its original form:

    ```java
    // @file: projects/critters/ChameleonCritter.java
    // @line: 50-54
    public void makeMove(Location loc)
    {
      setDirection(getLocation().getDirectionToward(loc));
      super.makeMove(loc);
    }
    ```

1. Why doesn’t ChameleonCritter override the getActors method?

    **Answer:** Because the way it get actors is the same as its base class, so there's no need to override it.

    ```java
    // @file: info/gridworld/actor/Critter.java
    // @line: 56-59
    public ArrayList<Actor> getActors()
    {
      return getGrid().getNeighbors(getLocation());
    }
    ```

1. Which class contains the getLocation method?

    **Answer:** The Actor class.

    ```java
    // @file: info/gridworld/actor/Actor.java
    // @line: 102-105
    public Location getLocation()
    {
      return location;
    }
    ```

1. How can a Critter access its own grid?

    **Answer:** Call the method `getGrid()` of its base class Actor:

    ```java
    getGrid(); // return its own grid.

    // @file: info/gridworld/actor/Actor.java
    // @line: 92-95
    public Grid<Actor> getGrid()
    {
      return grid;
    }
    ```

## Set 9

1. Why doesn’t CrabCritter override the processActors method?

    **Answer:** The way it process actors is the same as the base class, so there's no need to override it.

    ```java
    // @file: info/gridworld/actor/Critter.java
    // @line: 71-78
    public void processActors(ArrayList<Actor> actors)
    {
      for (Actor a : actors)
      {
        if (!(a instanceof Rock) && !(a instanceof Critter))
          a.removeSelfFromGrid();
      }
    }
    ```

1. Describe the process a CrabCritter uses to find and eat other actors. Does it always eat all neighboring actors? Explain.

    **Answer:** Find: `getActors()`, eat: `processActors(ArrayList<Actor>)`. The CrabCritter first call the `getActors()` method to find other actors, and then call the `processActors(ArrayList<Actor>)` to eat them. But it will not eat all the neighboring actors, as the `getActors()` only get actors that the CrabCritter is facing:

    ```java
    // @file: projects/critters/CrabCritter.java
    // @line: 44-58
    public ArrayList<Actor> getActors()
    {
      ArrayList<Actor> actors = new ArrayList<Actor>();
      int[] dirs =
        { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
      for (Location loc : getLocationsInDirections(dirs))
      {
        Actor a = getGrid().get(loc);
        if (a != null)
          actors.add(a);
      }

      return actors;
    }
    ```

1. Why is the getLocationsInDirections method used in CrabCritter?

    **Answer:** To find all the actors that the CrabCritter is facing.

    ```java
    // @file: projects/critters/CrabCritter.java
    // @line: 49-53
    for (Location loc : getLocationsInDirections(dirs))
    {
      Actor a = getGrid().get(loc);
      if (a != null)
        actors.add(a);
    }
    ```

1. If a CrabCritter has location (3, 4) and faces south, what are the possible locations for actors that are returned by a call to the getActors method?

    **Answer:** (4, 3), (4, 4) and (4, 5).

    ```java
    // @file: projects/critters/CrabCritter.java
    // @line: 44-57
    public ArrayList<Actor> getActors()
    {
    ArrayList<Actor> actors = new ArrayList<Actor>();
    int[] dirs =
      { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
    for (Location loc : getLocationsInDirections(dirs))
    {
      Actor a = getGrid().get(loc);
      if (a != null)
        actors.add(a);
    }

    return actors;
    }

    // @line: 101-114
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
      ArrayList<Location> locs = new ArrayList<Location>();
      Grid gr = getGrid();
      Location loc = getLocation();

      for (int d : directions)
      {
        Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
        if (gr.isValid(neighborLoc))
          locs.add(neighborLoc);
      }
      return locs;
    }  
    ```

1. What are the similarities and differences between the movements of a CrabCritter and a Critter?

    **Answer:** 

    Differences are displayed on the methods which are overriden, in other words, on `getActors()`, `getMoveLocations()` and `makeMove(Location)`.

    - Similarities:
      - They both eat all actors that are selected.
    - Difference:
      - CrabCritter only selct actors it is in the facing sector area. 
      - CrabCritter can only move to its left or right.
    
    ```java
    // @file: projects/critters/CrabCritter.java
    // @line 44-91
    public ArrayList<Actor> getActors()
    {
      ArrayList<Actor> actors = new ArrayList<Actor>();
      int[] dirs =
        { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
      for (Location loc : getLocationsInDirections(dirs))
      {
        Actor a = getGrid().get(loc);
        if (a != null)
          actors.add(a);
      }

      return actors;
    }

    /**
     * @return list of empty locations immediately to the right and to the left
     */
    public ArrayList<Location> getMoveLocations()
    {
      ArrayList<Location> locs = new ArrayList<Location>();
      int[] dirs =
        { Location.LEFT, Location.RIGHT };
      for (Location loc : getLocationsInDirections(dirs))
        if (getGrid().get(loc) == null)
          locs.add(loc);

      return locs;
    }

    /**
     * If the crab critter doesn't move, it randomly turns left or right.
     */
    public void makeMove(Location loc)
    {
      if (loc.equals(getLocation()))
      {
        double r = Math.random();
        int angle;
        if (r < 0.5)
          angle = Location.LEFT;
        else
          angle = Location.RIGHT;
        setDirection(getDirection() + angle);
      }
      else
        super.makeMove(loc);
    }
    ```

1. How does a CrabCritter determine when it turns instead of moving?

    **Answer:** When it cannot move as expected, it will try to turn to continue moving.

    ```java
    // @file: projects/critters/CrabCritter.java
    // @line: 79-88
    if (loc.equals(getLocation()))
    {
      double r = Math.random();
      int angle;
      if (r < 0.5)
        angle = Location.LEFT;
      else
        angle = Location.RIGHT;
      setDirection(getDirection() + angle);
    }
    ```

1. Why don’t the CrabCritter objects eat each other?

    **Answer:** Beacause a Critter won't eat a Critter due to the original implemention of `processActors(ArrayList<Actor>` in the base class Critter.

    ```java
    // @file: info/gridworld/actor/Critter.java
    // @line: 71-78
    public void processActors(ArrayList<Actor> actors)
    {
      for (Actor a : actors)
      {
        if (!(a instanceof Rock) && !(a instanceof Critter))
          a.removeSelfFromGrid();
      }
    }
    ```