// package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	private static final int MAX_MAP_SIZE = 1000;

	public Location next; // Next step's location
	public boolean isEnd = false; 
	public Stack<Location> crossLocation = new Stack<Location>();
	boolean isVisited[][];

	private int counters[] = new int[4];
	
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		isEnd = false;
		isVisited = new boolean[MAX_MAP_SIZE][MAX_MAP_SIZE];
		for (int i = 0; i < MAX_MAP_SIZE; ++i) {
			for (int t = 0; t < MAX_MAP_SIZE; ++t) {
				isVisited[i][t] = false;
			}
		}
		// Initialize the isVisited matrix

		for (int i = 0; i < 4; ++i) {
			counters[i] = 1;
		}
	}

	public boolean isEnd() {
		return isEnd;
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
		} else if (moveBack()){
			// try to go back
			++stepCount;
		} else {
			isEnd = true;
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
		Location location = getLocation(), target;
		int row, col;

		int directions[] = 
			{Location.NORTH, Location.SOUTH, Location.WEST, Location.EAST};
		
		for (int direction : directions) {
			target = location.getAdjacentLocation(direction);
			row = target.getRow();
			col = target.getCol();

			if (gr.isValid(target) && !isVisited[row][col]) {
				Actor actor = gr.get(target);
				if (actor == null || actor instanceof Flower) {
					for (int i = 0; i < counters[(int)((direction % 360) / 90)]; ++i) {
						valid.add(target);
					}
				} else if (actor instanceof Rock && actor.getColor().equals(Color.RED)) {
					valid.clear();
					valid.add(target);
					return valid;
				}
			}
		}

		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		ArrayList<Location> locations = getValid(getLocation());
		if (locations.isEmpty()){
			return false;
		}
		else {
			next = locations.get((int)(Math.random() * locations.size()) % locations.size());
			return true;
		}
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();

		if (gr.isValid(next)) {
			if (gr.get(next) instanceof Rock && gr.get(next).getColor().equals(Color.RED)) {
				isEnd = true;
				--stepCount;
				setDirection(getLocation().getDirectionToward(next));
				return;
			}
			
			isVisited[getLocation().getRow()][getLocation().getCol()] = true;
			isVisited[next.getRow()][next.getCol()] = true;
			crossLocation.push(getLocation());
			setDirection(getLocation().getDirectionToward(next));

			++counters[(int)((getDirection() % 360) / 90)];

			moveTo(next);
		} else {
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}

	public boolean moveBack() {
		if (crossLocation.isEmpty()) {
			return false;
		}
		Location location = crossLocation.pop(), oriLocation = getLocation();
		setDirection(getLocation().getDirectionToward(location));
		--counters[(int)(((getDirection() + 180) % 360) / 90)];
		moveTo(location);

		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(getGrid(), oriLocation);

		return true;
	}
}
