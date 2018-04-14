# Short Answer

## Part 1

1. Does the bug always move to a new location? Explain.

    Nope. The bug will change its location if and only if it is not blocked by a wall or a rock.

1. In which direction does the bug move?

    To the direction it is facing. But if it is blocked by a wall or a rock, it will rotate clockwise untill it is not blocked anymore.

1. What does the bug do if it does not move?

    Rotate clockwise.

1. What does a bug leave behind when it moves?

    Flowers of the same color as it. And the colors of those flowers will fade gradually.

1. What happens when the bug is at an edge of the grid? (Consider whether the bug is facing the edge as well as whether the bug is facing some other direction when answering this question.)

    It will rotate clockwise untill it is not blocked anymore. In another word, it will rotate clockwise till the time that it is not facing the obstacles.

1. What happens when a bug has a rock in the location immediately in front of it?

    It will rotate clockwise untill it is not blocked anymore. In another word, it will rotate clockwise till the time that it is not facing the rock.

1. Does a flower move?

    Nope.

1. What behavior does a flower have?

    Its color will fade gradually.

1. Does a rock move or have any other behavior?

    Nope, but it is able to block bugs.

1. Can more than one actor (bug, flower, rock) be in the same location in the grid at the same time?

    Nope.

## Part 2

1. Test the setDirection method with the following inputs and complete the table, giving the compass direction each input represents.

    Degrees|Compass Direction
    -|-
    0|north
    45|northeast
    90|east
    135|southeast
    180|south
    225|southwest
    270|west
    315|northwest
    360|north

1. Move a bug to a different location using the moveTo method. In which directions can you move it? How far can you move it? What happens if you try to move the bug outside the grid?

    Any direction. Any distance, but if you move it outside the grid, an illegalArgumentException will be thrown.

1. Change the color of a bug, a flower, and a rock. Which method did you use?

    ```java
    void setColor(java.awt.Color)
    ```

1. Move a rock on top of a bug and then move the rock again. What happened to the bug?

    It disappeared!