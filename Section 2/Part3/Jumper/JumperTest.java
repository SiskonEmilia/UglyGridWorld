import static org.junit.Assert.*; // Junit components
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;

public class JumperTest {

  /* Test 0: Normal Jumpping */
  /* This test is used to test
     that is our jumpers able
     to jump */
  @org.junit.Test
  public void jumpTest() {
    ActorWorld world = new ActorWorld();
    Jumper alice = new Jumper();

    world.add(new Location(8, 8), alice);

    alice.act();

    assertEquals(alice.getLocation().getRow(), 6);
    assertEquals(alice.getLocation().getCol(), 8);
    assertEquals(alice.getDirection(), 0); 
  }

  /* Test 1: Rock and Flower */
  /* This test is uesd to test
     the case 1 in Design Report */
  @org.junit.Test
  public void blockTest() {
    ActorWorld world = new ActorWorld();
    Jumper alice = new Jumper();
    Jumper emilia = new Jumper();
    Bug flowerMaker = new Bug();
    Rock rock = new Rock();

    world.add(new Location(7, 8), alice);
    world.add(new Location(7, 6), emilia);
    world.add(new Location(5, 8), flowerMaker);
    world.add(new Location(5, 6), rock);

    flowerMaker.act(); // Produce a flower
    alice.act();
    emilia.act();

    assertEquals(alice.getLocation().getRow(), 7);
    assertEquals(alice.getLocation().getCol(), 8);
    assertEquals(alice.getDirection(), 45); 
    /* Alice should trun around and should not move */

    assertEquals(emilia.getLocation().getRow(), 7);
    assertEquals(emilia.getLocation().getCol(), 6);
    assertEquals(emilia.getDirection(), 45); 
    /* Emilia should trun around and should not move */
  }

  /* Test 2: RangeError Test */
  /* This test is to test that
     wether jumper jump out
     of the Grid or not */
  @org.junit.Test
  public void rangeTest() {
    ActorWorld world = new ActorWorld();
    Jumper alice = new Jumper();

    world.add(new Location(1, 0), alice);

    alice.act();

    assertEquals(alice.getLocation().getRow(), 1);
    assertEquals(alice.getLocation().getCol(), 0);
    assertEquals(alice.getDirection(), 45); 
    /* Alice should trun around and should not move */
  }

  /* Test 3： Edge Test */
  /* This test is to test that
     wether jumper will across
     the edge while facing it */
  @org.junit.Test
  public void edgeTest() {
    ActorWorld world = new ActorWorld();
    Jumper alice = new Jumper();

    world.add(new Location(0, 0), alice);

    alice.act();

    assertEquals(alice.getLocation().getRow(), 0);
    assertEquals(alice.getLocation().getCol(), 0);
    assertEquals(alice.getDirection(), 45); 
    /* Alice should trun around and should not move */
  }

  /* Test 4: Actor Test */
  /* This test is to test that
     wether jumper will trun
     while blocked by actors */
  @org.junit.Test
  public void actorTest() {
    ActorWorld world = new ActorWorld();
    Jumper alice = new Jumper();
    Bug bug = new Bug();

    world.add(new Location(4, 0), alice);
    world.add(new Location(2, 0), bug);
    alice.act();

    assertEquals(alice.getLocation().getRow(), 4);
    assertEquals(alice.getLocation().getCol(), 0);
    assertEquals(alice.getDirection(), 45); 
    /* Alice should trun around and should not move */
  }

  /* Test 5: Meet Test */
  /* This test is to test that
     what will happen if two
     jumper meets together */
  @org.junit.Test
  public void meetTest() {
    ActorWorld world = new ActorWorld();
    Jumper alice = new Jumper();
    Jumper emilia = new Jumper();
    world.add(new Location(7, 8), alice);
    world.add(new Location(5, 8), emilia);
    emilia.setDirection(180); // Makes them face to face

    alice.act();
    emilia.act();


    assertEquals(alice.getLocation().getRow(), 7);
    assertEquals(alice.getLocation().getCol(), 8);
    assertEquals(alice.getDirection(), 45); 
    /* Alice should trun around and should not move */

    assertEquals(emilia.getLocation().getRow(), 5);
    assertEquals(emilia.getLocation().getCol(), 8);
    assertEquals(emilia.getDirection(), 180 + 45); 
    /* Emilia should trun around and should not move */
  }
}