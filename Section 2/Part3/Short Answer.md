# Short Answer for Part 3

## Set 3

Assume the following statements when answering the following questions.

```java
Location loc1 = new Location(4, 3);
Location loc2 = new Location(3, 4);
```

1. How would you access the row value for loc1?

    **Answer:** 

    ```java
    int row = loc1.getRow();
    ```

    ```java
    // @file: info/gridworld/gird/Location.java
    // @line: 110-113

    public int getRow()
    {
      return row;
    }
    ```

1. What is the value of b after the following statement is executed?
    
    ```java
    boolean b = loc1.equals(loc2);
    ```

    **Answer:** `false`. Because `loc1.equals(Location loc2)` will return false if `loc1.row != loc2.row` or `loc1.col != loc2.col`.

    ```java
    // @file: info/gridworld/gird/Location.java
    // @line: 205-212

    public boolean equals(Object other)
    {
      if (!(other instanceof Location))
          return false;

      Location otherLoc = (Location) other;
      return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
    }
    ```

1. What is the value of loc3 after the following statement is executed?

    ```java
    Location loc3 = loc2.getAdjacentLocation(Location.SOUTH);
    ```

    **Answer:**

    ```java
    loc3.equals(new Location(4, 4)); // true
    ```

    ```java
    // @file: info/gridworld/gird/Location.java
    // @line: 130-169

    public Location getAdjacentLocation(int direction){
      int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
      if (adjustedDirection < 0)
          adjustedDirection += FULL_CIRCLE;

      adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
      int dc = 0;
      int dr = 0;
      // ...
      else if (adjustedDirection == SOUTH)
        dr = 1;
      // ...
      return new Location(getRow() + dr, getCol() + dc);
    } 
    ```

1. What is the value of dir after the following statement is executed?
 
    ```java
    int dir = loc1.getDirectionToward(new Location(6, 5));
    ```

    **Answer:** 

    ```java
    dir == 135; // true
    ```
    
    ```java
    // @file: info/gridworld/grid/Location.java
    // @line: 178-195

    public int getDirectionToward(Location target)
    {
      int dx = target.getCol() - getCol();
      int dy = target.getRow() - getRow();
      // y axis points opposite to mathematical orientation
      int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));

      // mathematical angle is counterclockwise from x-axis,
      // compass angle is clockwise from y-axis
      int compassAngle = RIGHT - angle;
      // prepare for truncating division by 45 degrees
      compassAngle += HALF_RIGHT / 2;
      // wrap negative angles
      if (compassAngle < 0)
          compassAngle += FULL_CIRCLE;
      // round to nearest multiple of 45
      return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
    }
    ```

1. How does the getAdjacentLocation method know which adjacent location to return?

    **Answer:** 

    ```java
    // @file: info/gridworld/gird/Location.java
    // @line: 130-169

    public Location getAdjacentLocation(int direction){
      int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
      if (adjustedDirection < 0)
          adjustedDirection += FULL_CIRCLE;
      // First, it normalize the direction value.

      adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
      int dc = 0;
      int dr = 0;
      /*
        Then, it use if - else if - else statements to judge which is the location that the actor is facing. (And set the offset)
      */

      return new Location(getRow() + dr, getCol() + dc); 
      // Use offsets to calculate the final answer.
    } 
    ```

## Set 4

1. How can you obtain a count of the objects in a grid? How can you obtain a count of the empty locations in a bounded grid?

    **Answer:**
    
    ```java
    int countObjects = grid.getOccupiedLocations().size(); // count of the objects
    int countEmpty = grid.getNumRows() * grid.getNumCols() - countObjects; // count of the empty locations
    ```
    Because `getOccupiedLocations()` method gives a list of all objects' locations, the size of it is the count of objects in grid.

    By working out `getNumRows() * getNumCols()`, we can get the count of all locations in the grid. Thus minusing it with `countObjcts` results in the count of empty locations.

    ```java
    // @file: info/gridworld/grid/BoundedGrid.java
    // @line: 48-51

    public int getNumRows()
    {
        return occupantArray.length;
    }

    // @line: 53-58

    public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return occupantArray[0].length;
    }

    // @line: 66-83

    public ArrayList<Location> getOccupiedLocations()
    {
      ArrayList<Location> theLocations = new ArrayList<Location>();

      // Look at all grid locations.
      for (int r = 0; r < getNumRows(); r++)
      {
        for (int c = 0; c < getNumCols(); c++)
        {
          // If there's an object at this location, put it in the array.
          Location loc = new Location(r, c);
          if (get(loc) != null)
              theLocations.add(loc);
        }
      }

      return theLocations;
    }
    ```

1. How can you check if location (10,10) is in a grid?

    **Answer:**

    ```java
    boolean isInGrid = grid.isValid(new Location(10, 10));
    ```

    `isValid(Location)` method will check if this is a valid grid, which means if it is inside the grid.

    ```java
    // @file: info/gridworld/grid/BoundedGrid.java
    // @line: 60-64
    public boolean isValid(Location loc)
    {
      return 0 <= loc.getRow() && loc.getRow() < getNumRows()
        && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
    ```

1. Grid contains method declarations, but no code is supplied in the methods. Why? Where can you find the implementations of these methods?

    In classes that implements the interface, like BoundedGrid.java and UnboundedGrid.java.

    ```java
    // @file: info/gridworld/Grid.java
    // @line: 35
    int getNumRows();

    // @file: info/gridworld/BoundedGrid.java
    // @line: 48-51
    public int getNumRows()
    {
      return occupantArray.length;
    }
    ```

1. All methods that return multiple objects return them in an ArrayList. Do you think it would be a better design to return the objects in an array? Explain your answer.

    No. Although Array is more effective than ArrayList, it is a fixed-size data strcture. And before we finishing executing these methods, we cannot ensure the size of it. Thus ArrayList is the one we have to choose. But there's still somewhere that we can optimize the code. We're supposed to change the return type to IList interface, by doing which can we makes our code fit the coding rules of JAVA better.

    ```java
    // @file: info/gridworld/grid/Grid.java
    // @line: 85
    public ArrayList<Location> getOccupiedLocations();
    ```

## Set 5

1. Name three properties of every actor.

    **Answer:**

    ```java
    // @file: info/gridworld/actor/Actor.java
    // @line: 32-34

    private Location location; // 1st
    private int direction; // 2nd
    private Color color; // 3rd
    ```

1. When an actor is constructed, what is its direction and color?

    **Answer:** `Color.BLUE` and `Location.NORTH`.

    ```java
    // @file: info/gridworld/actor/Actor.java
    // @line: 39-45
    public Actor()
    {
      color = Color.BLUE;
      direction = Location.NORTH;
      // ...
    ```

1. Why do you think that the Actor class was created as a class instead of an interface?

    **Answer:**
    
    - It does not have any method which is not implemented.
    - It is not declared as a interface but a class.

    ```java
    // @file: info/gridworld/actor/Actor.java
    // @line: 29

    public class Actor
    ```

1. Can an actor put itself into a grid twice without first removing itself? Can an actor remove itself from a grid twice? Can an actor be placed into a grid, remove itself, and then put itself back? Try it out. What happens?

    **Answer:**

    No. No. Yes.

    In the first and the second cases, an `IllegalStateException` will be thrown. In the third case, the actor was successfully put back.

    ```java
    // @file: info/gridworld/actor/Actor.java
    // @line: 115-119
    public void putSelfInGrid(Grid<Actor> gr, Location loc)
    {
      if (grid != null)
        throw new IllegalStateException(
          "This actor is already contained in a grid.");
    // ...

    // @line: 133-137
    public void removeSelfFromGrid()
    {
      if (grid == null)
        throw new IllegalStateException(
          "This actor is not contained in a grid.");
    // ...
    ```

1. How can an actor turn 90 degrees to the right?

    **Answer:** 
    
    ```java
    actor.setDirection(actor.getDirection() + Location.HALF_RIGHT);
    ```

    ```java
    // @file: info/gridworld/grid/Location.java
    // @line: 48
    public static final int HALF_RIGHT = 45;

    // @file: info/gridworld/actor/Actor.java
    // @line: 69-72
    public int getDirection()
    {
      return direction;
    }

    // @line: 80-85
    public void setDirection(int newDirection)
    {
      direction = newDirection % Location.FULL_CIRCLE;
      if (direction < 0)
        direction += Location.FULL_CIRCLE;
    }
    ```

## Set 6
    
1. Which statement(s) in the canMove method ensures that a bug does not try to move out of its grid?

    **Answer:** 

    ```java
    // @file: info/gridworld/actor/Bug.java
    // @line: 98-99

    if (!gr.isValid(next))
      return false;
    // Statements above are what you want
    // Statements below are what makes it work

    // @file: info/gridworld/grid/BoundedGrid.java
    // @line: 60-64

    public boolean isValid(Location loc)
    {
      return 0 <= loc.getRow() && loc.getRow() < getNumRows()
        && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
    ```

1. Which statement(s) in the canMove method determines that a bug will not walk into a rock?

    **Answer:** 

    ```java
    // @file: info/gridworld/actor/Bug.java
    // @line 100-101
    Actor neighbor = gr.get(next);
    return (neighbor == null) || (neighbor instanceof Flower);
    ```

1. Which methods of the Grid interface are invoked by the canMove method and why?

     **Answer:** `isValid(Location)`. It is used to check whether the location is out of the grid or not.

    ```java
    // @file: info/gridworld/actor/Bug.java
    // @line: 98-99
    if (!gr.isValid(next))
      return false;
    ```


1. Which method of the Location class is invoked by the canMove method and why?

    **Answer:** `getAdjacentLocation(Direction)`. It is used to find the next location that the actor is move to.

    ```java
    // @file: info/gridworld/actor/Bug.java
    // @line: 97
    Location next = loc.getAdjacentLocation(getDirection());
    ```

1. Which methods inherited from the Actor class are invoked in the canMove method?

    **Answer:** `getGrid()`, `getLocation()` and `getDirection()`.

    ```java
    // @file: info/gridworld/actor/Actor.java
    // @line: 92
    public Grid<Actor> getGrid()

    // @line: 102
    public Location getLocation()

    // @line: 69
    public int getDirection()

    // @file: info/gridworld/actor/Bug.java
    // @line: 93-97
    Grid<Actor> gr = getGrid();
    // ...
    Location loc = getLocation();
    Location next = loc.getAdjacentLocation(getDirection());
    ``` 

1. What happens in the move method when the location immediately in front of the bug is out of the grid?

    **Answer:** The Bug will be removed from the grid and leave a flower at its original location.

    ```java
    // @file:
    // @line: 78-83
    if (gr.isValid(next))
      moveTo(next);
    else
      removeSelfFromGrid();
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc);
    ```

1. Is the variable loc needed in the move method, or could it be avoided by calling getLocation() multiple times?

    **Answer:** Nope. As at the end of this function, if the Bug is going to move out of the grid, the location of the actor has been set to `null`, but the original location is still needed.

    ```java
    // @file: info/gridworld/actor/Actor.java
    // @line: 133-145
    public void removeSelfFromGrid()
    {
      // ...
      location = null;
    }

    // @file: info/gridworld/actor/Bug.java
    // @line: 78-83
    if (gr.isValid(next))
      moveTo(next);
    else
      removeSelfFromGrid();
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc); 
    // If removeSelfFromGrid() is called, getLocation() will return nothing but null, while loc can record the original location of the actor
    ```

1. Why do you think the flowers that are dropped by a bug have the same color as the bug?

    **Answer:** It is wrriten so in the `move()` method.

    ```java
    // @file: info/gridworld/actor/Bug.java
    // @line: 82-83
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc);
    ```

1. When a bug removes itself from the grid, will it place a flower into its previous location?

    **Answer:** Yes.

    ```java
    // @file: info/gridworld/actor/Bug.java
    // @line: 78-83
    if (gr.isValid(next))
      moveTo(next);
    else
      removeSelfFromGrid();
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc); 
    ```

1. Which statement(s) in the move method places the flower into the grid at the bug’s previous location?

    **Answer:**

    ```java
    // @file: info/gridworld/actor/Bug.java
    // @line: 83
    flower.putSelfInGrid(gr, loc); 
    ```


1. If a bug needs to turn 180 degrees, how many times should it call the turn method?

    **Answer:** 4 times, as 180 / 45 = 4.

    ```java
    // @file: info/gridworld/actor/Bug.java
    // @line: 62-65
    public void turn()
    {
      setDirection(getDirection() + Location.HALF_RIGHT);
    }

    // @file: info/gridworld/grid/Location.java
    // @line: 48
    public static final int HALF_RIGHT = 45;
    ```