# Short Answer for Part 5

## Set 10

The source code for the AbstractGrid class is in Appendix D.

1. Where is the isValid method specified? Which classes provide an implementation of this method?

    **Answer:** In the class Grid. And it's implemented in BoundedGrid and UnboundedGrid.

    ```java
    // @file: info/gridworld/grid/Grid.java
    // @line: 50
    boolean isValid(Location loc);

    // @file: info/gridworld/grid/BoundedGrid.java
    // @line: 60-64
    public boolean isValid(Location loc)
    {
      return 0 <= loc.getRow() && loc.getRow() < getNumRows()
        && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    // @file: info/gridworld/grid/UnboundedGrid.java
    // @line: 53-56
    public boolean isValid(Location loc)
    {
        return true;
    }
    ```

1. Which AbstractGrid methods call the isValid method? Why don’t the other methods need to call it?

    **Answer:** `getValidAdjacentLocations(Location)`. Because other methods call this method or other methods that call it to get locations - obvious, they are valid.

    ```java
    // @file: info/gridworld/grid/AbstractGrid.java
    // @line: 44
    if (isValid(neighborLoc))

    // @line: 54
    for (Location neighborLoc : getValidAdjacentLocations(loc))

    // @line: 65
    for (Location neighborLoc : getValidAdjacentLocations(loc))

    // @line: 82
    for (Location loc : getOccupiedLocations())

    // @line: 31
    for (Location neighborLoc : getOccupiedAdjacentLocations(loc))
    ```

1. Which methods of the Grid interface are called in the getNeighbors method? Which classes provide implementations of these methods?

    **Answer:** `getOccupiedAdjacentLocations(Location)`, AbstructGrid.

    ```java
    // @file: info/gridworld/grid/AbstructGrid.java
    // @line: 28-34
    public ArrayList<E> getNeighbors(Location loc)
    {
      ArrayList<E> neighbors = new ArrayList<E>();
      for (Location neighborLoc : getOccupiedAdjacentLocations(loc))
        neighbors.add(get(neighborLoc));
      return neighbors;
    }

    // @line: 62-71
    public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
    {
      ArrayList<Location> locs = new ArrayList<Location>();
      for (Location neighborLoc : getValidAdjacentLocations(loc))
      {
        if (get(neighborLoc) != null)
          locs.add(neighborLoc);
      }
      return locs;
    }
    ```

1. Why must the get method, which returns an object of type E, be used in the getEmptyAdjacentLocations method when this method returns locations, not objects of type E?

    **Answer:** It is used to check if this location is empty or not. As in the first case, this method will return `null`.

    ```java
    // @file: info/gridworld/grid/AbstructGrid.java
    // @line 56-57
    if (get(neighborLoc) == null)
      locs.add(neighborLoc);
    
    // @file: info/gridworld/grid/Grid.java
    // @line: 72-79
    /**
     * Returns the object at a given location in this grid. <br />
     * Precondition: <code>loc</code> is valid in this grid
     * @param loc a location in this grid
     * @return the object at location <code>loc</code> (or <code>null<code> 
     *  if the location is unoccupied)
     */
    E get(Location loc);
    ```

1. What would be the effect of replacing the constant Location.HALF_RIGHT with Location.RIGHT in the two places where it occurs in the getValidAdjacentLocations method?

    **Answer:** This method will only return locations in front of, behind, left to and right to the given location. (At most 4 locations returned)

    ```java
    // @file: info/gridworld/grid/AbstactGrid.java
    // @line: 41-47
    for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
    {
      Location neighborLoc = loc.getAdjacentLocation(d);
      if (isValid(neighborLoc))
        locs.add(neighborLoc);
      d = d + Location.HALF_RIGHT;
    }

    // @file: info/gridworld/grid/
    // @line: 48
    public static final int HALF_RIGHT = 45;
    // @line: 40
    public static final int RIGHT = 90;
    ```

## Set 11

The source code for the BoundedGrid class is in Appendix D.

1. What ensures that a grid has at least one valid location?

    **Answer:** Statements in the constructor. They ensure that the `rows` and `cols` is more than zero. Thus there'll be at least one valid location.

    ```java
    // @file: info/gridworld/grid/BoundedGrid.java
    // @line: 39-46
    public BoundedGrid(int rows, int cols)
    {
      if (rows <= 0)
        throw new IllegalArgumentException("rows <= 0");
      if (cols <= 0)
        throw new IllegalArgumentException("cols <= 0");
      occupantArray = new Object[rows][cols];
    }
    ```

1. How is the number of columns in the grid determined by the getNumCols method? What assumption about the grid makes this possible?

    **Answer:** Through the `occupantArray[0].length`. The assumption is that there's at least one row, which is ensured by the constructor.

    ```java
    // @file: info/gridworld/grid/BoundedGrid.java
    // @line: 53-58
    public int getNumCols()
    {
      // Note: according to the constructor precondition, numRows() > 0, so
      // theGrid[0] is non-null.
      return occupantArray[0].length;
    }
    ```

1. What are the requirements for a Location to be valid in a BoundedGrid?

    **Answer:** A location is valid if and only if its col <= the number of cols in the Gird and its row <= the number of rows in the Grid.

    ```java
    // @file: info/gridworld/grid/BoundedGrid.java
    // @line: 60-64
    public boolean isValid(Location loc)
    {
      return 0 <= loc.getRow() && loc.getRow() < getNumRows()
        && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
    ```

In the next four questions, let r = number of rows, c = number of columns, and n = number of occupied locations.

1. What type is returned by the getOccupiedLocations method? What is the time complexity (Big-Oh) for this method?

    **Answer:** `ArrayList<Location>`. O(r * c).

    ```java
    // @file: info/gridworld/grid/BoundedGrid.java
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

1. What type is returned by the get method? What parameter is needed? What is the time complexity (Big-Oh) for this method?

    **Answer:** E, which is determined by the given general type. It needs a location as parameter. The complexity of it is O(1).

    ```java
    // @file: info/gridworld/grid/BoundedGrid.java
    // @line: 85-91
    public E get(Location loc)
    {
      if (!isValid(loc))
        throw new IllegalArgumentException("Location " + loc
          + " is not valid");
      return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }
    ```

1. What conditions may cause an exception to be thrown by the put method? What is the time complexity (Big-Oh) for this method?

    **Answer:** If the location is not valid or the object is `null`. O(1).

    ```java
    // @file: info/gridworld/grid/BoundedGrid.java
    // @line: 93-105
    public E put(Location loc, E obj)
    {
      if (!isValid(loc))
        throw new IllegalArgumentException("Location " + loc
          + " is not valid");
      if (obj == null)
        throw new NullPointerException("obj == null");

        // Add the object to the grid.
      E oldOccupant = get(loc);
      occupantArray[loc.getRow()][loc.getCol()] = obj;
      return oldOccupant;
    }
    ```

1. What type is returned by the remove method? What happens when an attempt is made to remove an item from an empty location? What is the time complexity (Big-Oh) for this method?

    **Answer:** E, which is determined by the given general type. An `IllegalArgumentException` will be thrown. O(1).

    ```java
    // @file: info/gridworld/grid/BoundedGrid.java
    // @line: 107-117
    public E remove(Location loc)
    {
      if (!isValid(loc))
        throw new IllegalArgumentException("Location " + loc
          + " is not valid");
      
      // Remove the object from the grid.
      E r = get(loc);
      occupantArray[loc.getRow()][loc.getCol()] = null;
      return r;
    }
    ```

1. Based on the answers to questions 4, 5, 6, and 7, would you consider this an efficient implementation? Justify your answer.

    **Answer:**  Yes, as O(1) is the smallest complexity. And most of them have reached it. The only O(r * c) method has few ways to optimize.

    ```java
    // @file: info/gridworld/grid/BoundedGrid.java
    // @line: 29-119
    /* Too much to display */
    ```

## Set 12

The source code for the UnboundedGrid class is in Appendix D.

1. Which method must the Location class implement so that an instance of HashMap can be used for the map? What would be required of the Location class if a TreeMap were used instead? Does Location satisfy these requirements?

    **Answer:** `hashCode` and `equals`. It has to implements the `Comparable` interface, which means the `compareTo` must be implemented. Luckily, it meets all the requirements.

    ```java
    // @file: info/gridworld/grid/Location.java
    // @line: 28
    public class Location implements Comparable {/* */}
    // @line: 218
    public int hashCode()
    // @line: 205
    public boolean equals(Object other)
    // @line: 234
    public int compareTo(Object other)
    ```


1. Why are the checks for null included in the get, put, and remove methods? Why are no such checks included in the corresponding methods for the BoundedGrid?

    **Answer:** To ensure that the object is not `null`. Other methods which need checking call these three methods to check.

    ```java
    // @file: info/gridworld/grid/BoundedGrid.java
    // @line: 77
    if (get(loc) != null) // As an example
    ```
1. What is the average time complexity (Big-Oh) for the three methods: get, put, and remove? What would it be if a TreeMap were used instead of a HashMap?

    **Answer:** O(1). O(log n), in which n is the number of objects in the grid.

    ```java
    // @file: info/gridworld/grid/BoundedGrid.java
    // @line: 85-117
    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
    ```

1. How would the behavior of this class differ, aside from time complexity, if a TreeMap were used instead of a HashMap?

    **Answer:** The order of objects and locations in ArrayList to be returned might changes. As the different order of traversing.

    ```java
    // @file: info/gridworld/grid/BoundedGrid.java
    // @line: ???
    ```

1. Could a map implementation be used for a bounded grid? What advantage, if any, would the two-dimensional array implementation that is used by the BoundedGrid class have over a map implementation?

    **Answer:** Yes. It could help with reducing the complexity in spcace. Two-dimensional array is better at the complexity in time in most cases.

    ```java
    // @file: DataStructure
    // @line: I don't know
    ```