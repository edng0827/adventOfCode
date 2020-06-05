import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

    @Test
    void getDirections() throws IOException {
        Day1.INPUT = "THROW EXCEPTION";
        assertThrows(IOException.class, () -> {
            Day1.getDirections(new ArrayList<>());
        });

        Day1.INPUT = "src/main/files/day1";
        assertEquals("R5", Day1.getDirections(new ArrayList<>()).get(0));
    }

    @Test
    void moving() {
        //Turning left
        Point NorthLeft = Day1.moving('L', 1, new Point(0,0), "NORTH");
        assertEquals(new Point(-1, 0), NorthLeft);

        Point SouthLeft = Day1.moving('L', 1, new Point(0,0), "SOUTH");
        assertEquals(new Point(1, 0), SouthLeft);

        Point EastLeft = Day1.moving('L', 1, new Point(0,0), "EAST");
        assertEquals(new Point(0, 1), EastLeft);

        Point WestLeft = Day1.moving('L', 1, new Point(0,0), "WEST");
        assertEquals(new Point(0, -1), WestLeft);

        //Turning Right
        Point NorthRight = Day1.moving('R', 1, new Point(0,0), "NORTH");
        assertEquals(new Point(1, 0), NorthRight);

        Point SouthRight = Day1.moving('R', 1, new Point(0,0), "SOUTH");
        assertEquals(new Point(-1, 0), SouthRight);

        Point EastRight = Day1.moving('R', 1, new Point(0,0), "EAST");
        assertEquals(new Point(0, -1), EastRight);

        Point WestRight = Day1.moving('R', 1, new Point(0,0), "WEST");
        assertEquals(new Point(0, 1), WestRight);

    }

    @Test
    void turning() {
        //Left Turns
        String NorthLeft = Day1.turning('L', "NORTH");
        assertEquals("WEST", NorthLeft);

        String SouthLeft = Day1.turning('L', "SOUTH");
        assertEquals("EAST", SouthLeft);

        String EastLeft = Day1.turning('L', "EAST");
        assertEquals("NORTH", EastLeft);

        String WestLeft = Day1.turning('L', "WEST");
        assertEquals("SOUTH", WestLeft);

        //Right turns
        String NorthRight = Day1.turning('R', "NORTH");
        assertEquals("EAST", NorthRight);

        String SouthRight = Day1.turning('R', "SOUTH");
        assertEquals("WEST", SouthRight);

        String EastRight = Day1.turning('R', "EAST");
        assertEquals("SOUTH", EastRight);

        String WestRight = Day1.turning('R', "WEST");
        assertEquals("NORTH", WestRight);
    }

    @Test
    void getDistance() {
        assertEquals(20, Day1.getDistance(new Point(5, 5), new Point(-5,-5)));
    }

    @Test
    void addVisitedPoint() {
        Day1.alreadyBeen = new ArrayList<>(Arrays.asList(new Point(0,2), new Point(0,-2), new Point(2,0), new Point(-2,0)));
        Day1.rabbitHole = null;
        //did not find rabbit hole and now list has 5
        Day1.addVisitedPoint(new Point(0,0), 1, 0);
        assertEquals(5, Day1.alreadyBeen.size());
        assertNull(Day1.rabbitHole);
        //land exactly on rabbit hole list has 6
        Day1.addVisitedPoint(new Point(-1,0), -1, 0);
        assertEquals(6, Day1.alreadyBeen.size());
        assertEquals(new Point(-2,0),Day1.rabbitHole);
        //pass rabbit hole and list has 8 because they moved 2 blocks
        Day1.rabbitHole = null;
        Day1.addVisitedPoint(new Point(0,-3), 0, 2);
        assertEquals(8, Day1.alreadyBeen.size());
        assertEquals(new Point(0,-2),Day1.rabbitHole);
    }

    @Test
    void beenBefore() {
        Day1.alreadyBeen = new ArrayList<>(Arrays.asList(new Point(0,1)));
        assertTrue(Day1.beenBefore(new Point(0,1)));
        assertFalse(Day1.beenBefore(new Point(0,0)));
    }
}