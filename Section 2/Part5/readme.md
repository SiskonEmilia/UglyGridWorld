# GridWorld - Section2 - Part5

This part requires us to implement our own grids. To check the effect of them, you'd better pick those .java files out from their floders and compile them with the SparseGridRunner.java, and then run SparseGridRunner to check the effect:

```bash
javac -cp .:gridworld.jar SparseBoundedGrid.java SparseBoundedGrid2.java UnboundedGrid2.java SparseGridRunner.java
java -cp .:gridworld.jar SparseGridRunner
```

## SparseBoundedGrid

In this exercise, we choose to use a LinkedList with a helper class:

```java
  public class OccupantInCol {
    public OccupantInCol (Object occupant, int col) {
      this.occupant = occupant;
      this.col = col;
    }
    private Object occupant;
    private int col;
  }
```

If the matrix of grid is sparse, this implemention will obviously decrease the cost in space. Using `LinkedList` makes it possible for us to develop the application quicklier.

But using this method introduces extra cost in accessing cells in grid.

## SparseBoundedGrid2

In this exercise, we choose to use `HashMap` to store our cells in grid. This method can solve the problem of accessing cells, while occupy only a litte more than the `LinkedList` method.

```java
private HashMap<Location, Object> map;
```

## UnboundedGrid2

In this exercise, we implement an UnboundedGrid with the array that automatically changes the size of itself.

```java
  private int mySize;
  private Object[][] map;

  map = new Object[mySize][mySize];
```
When the `put` method is called, it will check whether this the given location is out of range. If so, it will extend the array to contain the object.

```java
  public Object put(Location loc, Object obj) {
    if (!isValid(loc)) {
      throw new IllegalArgumentException(loc + "is NOT A VALID LOCATION");
    }
    
    Object oriObj;
    while(loc.getRow() >= mySize || loc.getCol() >= mySize) {
      NewStorage();
    }

    oriObj = map[loc.getRow()][loc.getCol()];
    map[loc.getRow()][loc.getCol()] = obj;
    return oriObj;
  }
```