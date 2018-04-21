/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.Bug;

/**
 * A <code>DancingBug</code> traces out a square "Circle" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug
{
    private int steps;
    private int[] dance;
    private int dancing;
    private boolean shouldDance;

    /**
     * Constructs a Circle bug that traces a square of a given side length
     * @param length the side length
     */
    public DancingBug(int[] dance)
    {
        steps = 0;
        shouldDance = true;
        this.dance = new int[dance.length];
        for (int i = 0; i < dance.length; ++i) {
            this.dance[i] = dance[i];
        }
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if (shouldDance) {
          dance();
          steps = (steps + 1) % dance.length;
        }

        if (canMove() && dancing == 0)
        {
            move();
            shouldDance = true;
        }
        else
        {
            if (dancing > 0) {
              --dancing;
            }
            turn();
        }
    }

    private void dance() {
      dancing = dance[steps];
      shouldDance = false;
    }
}
