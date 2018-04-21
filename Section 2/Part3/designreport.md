# Design Report

1. What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock?

    The jumper will not stop trunning around untill is not going to jump onto an empty cell.

1. What will a jumper do if the location two cells in front of the jumper is out of the grid?

    This jumper will trun around untill it is not going to jump outside the grid.

1. What will a jumper do if it is facing an edge of the grid?

    The jumper will trun around untill it is not facing any "wall".

1. What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?

    The jumper will both trun around to avoid meeting at the same cell.

1. What will a jumper do if it encounters another jumper in its path?

    The jumper will both trun around to avoid meeting at the same cell.

1. Are there any other tests the jumper needs to make?

    Those tests almost cover all the areas.

    