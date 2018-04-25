# Short Answer for Part 2

## Set 2

The source code for the BoxBug class can be found in the boxBug directory.

1. What is the role of the instance variable sideLength?

    It serves as the max length that a BoxBug can go to the same direction while it does not `turn()`.

    ```java
    // @file: projects/boxBug/BoxBug.java
    // @line: 45-55

    if (steps < sideLength && canMove())
    {
      move();
      steps++;
    }
    else
    {
      turn();
      turn();
      steps = 0;
    }
    ```

1. What is the role of the instance variable steps?

    It records the number of steps that the BoxBug continuously takes to go forward.

    ```java
    // @file: projects/boxBug/BoxBug.java
    // @line: 45-55

    if (steps < sideLength && canMove())
    {
      move();
      steps++;
    }
    else
    {
      turn();
      turn();
      steps = 0;
    }
    ```

1. Why is the turn method called twice when steps becomes equal to sideLength?

    Because calling `turn()` for one time means 45 degrees of rotation, and what the designer wants is a rotation of 90 degrees.

    ```java
    // @file: info/gridworld/actor/Bug.java
    // @line: 62-65

    public void turn()
    {
    setDirection(getDirection() + Location.HALF_RIGHT);
    }
    ```

1. Why can the move method be called in the BoxBug class when there is no move method in the BoxBug code?

    Because BoxBug inherits it from Bug.

    ```java
    // @file: info/gridworld/actor/Bug.java
    // @line: 71
    public void move()
    
    // @file: projects/boxBug/BoxBug.java
    // @line: 25
    public class BoxBug extends Bug
    ```

1. After a BoxBug is constructed, will the size of its square pattern always be the same? Why or why not?

    Nope. If it cannot move in some cases, the size of its square pattern might get smaller.

    ```java
    // @file: projects/boxBug/BoxBug.java
    // @line: 45-55

    if (steps < sideLength && canMove())
    {
      move();
      steps++;
    }
    else
    {
      turn();
      turn();
      steps = 0;
    }
    ```

1. Can the path a BoxBug travels ever change? Why or why not?

    Yes. If it cannot move in some cases, the path of its travelling might change.

    ```java
    // @file: projects/boxBug/BoxBug.java
    // @line: 45-55

    if (steps < sideLength && canMove())
    {
      move();
      steps++;
    }
    else
    {
      turn();
      turn();
      steps = 0;
    }
    ```

1. When will the value of steps be zero?

    At the time it is blocked by something or its steps reaches the `sideLength`. Also, when it is constructed, the value of steps is also zero.

    ```java
    // @file: projects/boxBug/BoxBug.java
    // @line: 34-38
    
    public BoxBug(int length)
    {
        steps = 0;
        sideLength = length;
    }

    // @file: projects/boxBug/BoxBug.java
    // @line: 45-55

    if (steps < sideLength && canMove())
    {
      move();
      steps++;
    }
    else
    {
      turn();
      turn();
      steps = 0;
    }
    ```
