# Test Report

## Test 0: Normal Jumpping

```java
  @org.junit.Test
  public void jumpTest() throws Exception {
    ActorWorld world = new ActorWorld();
    Jumper alice = new Jumper();

    world.add(new Location(8, 8), alice);

    alice.act();

    assertEquals(alice.getLocation().getRow(), 6);
    assertEquals(alice.getLocation().getCol(), 8);
    assertEquals(alice.getDirection(), 0); 
  }
```

### Analysis:

This test is designed to check that wether jumper is able to jump correctly or not. First, we put our jumper at (8, 8), then call the act method of it. If everything goes right, it should be at (6, 8). And its direction should not change.

## Test 1: Block Test

```java
@org.junit.Test
  public void blockTest() throws Exception {
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
```

### Analysis:

This test is designed to ensure that jumpers are correctly blocked by flowers and rocks. Put flower and rock at the path they will pass, and call the act methods of them. The expected result is that they change and only change their direction.

## Test 2: RangeError Test

```java
  @org.junit.Test
  public void rangeTest() throws Exception {
    ActorWorld world = new ActorWorld();
    Jumper alice = new Jumper();

    world.add(new Location(1, 0), alice);

    alice.act();

    assertEquals(alice.getLocation().getRow(), 1);
    assertEquals(alice.getLocation().getCol(), 0);
    assertEquals(alice.getDirection(), 45); 
    /* Alice should trun around and should not move */
  }
```

### Analysis:

Will the jumper jump out of the grid? This test is made to check it. Put the jumper a cell away from the edge and make it facing it, then act method will affect nothing but its direction.

## Test 3： Edge Test

```java
@org.junit.Test
  public void edgeTest() throws Exception {
    ActorWorld world = new ActorWorld();
    Jumper alice = new Jumper();

    world.add(new Location(0, 0), alice);

    alice.act();

    assertEquals(alice.getLocation().getRow(), 0);
    assertEquals(alice.getLocation().getCol(), 0);
    assertEquals(alice.getDirection(), 45); 
    /* Alice should trun around and should not move */
  }
```

### Analysis:

Similar to Test 2, but there's no space between the jumper and the edge.

## Test 4: Actor Test

```java
  @org.junit.Test
  public void actorTest() throws Exception {
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
```

### Analysis:

What will happen if a jumper is going to jumo onto a actor? This test is designed to find the answer.

## Test 5: Meet Test

```java
  @org.junit.Test
  public void meetTest() throws Exception {
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
```

### Analysis:

Now we have two jumpers facing each other, will they fight to take place the other one? or just turn around and go away?