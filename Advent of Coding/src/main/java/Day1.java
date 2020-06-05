import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day1 {
    public static String INPUT = "src/main/files/day1";
    private final static String NORTH = "NORTH";
    private final static String SOUTH = "SOUTH";
    private final static String EAST = "EAST";
    private final static String WEST = "WEST";

    //part 2
    public static List<Point> alreadyBeen = new ArrayList<>();
    public static Point rabbitHole = null;

    public static void main(String[] args) throws IOException {
        List<String> directions;
        String facing = NORTH;
        Point startingPoint = new Point(0,0);
        Point currentPoint = new Point(0, 0);

        directions = getDirections(new ArrayList<>());

        for(String direction: directions){
            int blocks = Integer.parseInt(direction.substring(1));
            if(direction.charAt(0) == ('L')){
                currentPoint = moving('L', blocks, currentPoint, facing);
                facing = turning('L', facing);
            } else if (direction.charAt(0) == ('R')){
                currentPoint = moving('R', blocks, currentPoint, facing);
                facing = turning('R', facing);
            }
        }

        //part 1 answer
        System.out.println("Last point: " + getDistance(startingPoint, currentPoint));

        //part 2 answer
        System.out.println("Distance to the Rabbit Hole: " + getDistance(startingPoint, rabbitHole));
    }

    public static ArrayList<String> getDirections(ArrayList<String> directions) throws IOException {
        BufferedReader buff = new BufferedReader(new FileReader(INPUT));
        String direction;
        while((direction = buff.readLine()) != null){
            directions.addAll(Arrays.asList(direction.split(", ")));
        }
        return directions;
    }

    //Assumes North and East are positive, South and West are negative
    public static Point moving(char direction, int blocks, Point currentPoint, String facing){
        int movex = 0;
        int movey = 0;
        if (direction == 'L') {
            switch(facing){
                case NORTH:
                    movex = -1 * blocks;
                    break;
                case SOUTH:
                    movex = blocks;
                    break;
                case EAST:
                    movey = blocks;
                    break;
                case WEST:
                    movey = -1 * blocks;
                    break;
            }
        } else if(direction == 'R'){
            switch(facing){
                case NORTH:
                    movex = blocks;
                    break;
                case SOUTH:
                    movex = -1 * blocks;
                    break;
                case EAST:
                    movey = -1 *blocks;
                    break;
                case WEST:
                    movey = blocks;
                    break;
            }
        }

        addVisitedPoint(currentPoint, movex, movey);
        currentPoint.translate(movex, movey);

        return currentPoint;
    }

    public static String turning(char direction, String facing){
        switch(facing){
            case NORTH:
                if(direction == 'L'){
                    return WEST;
                } else if(direction == 'R'){
                    return EAST;
                }
                break;
            case SOUTH:
                if(direction == 'L'){
                    return EAST;
                } else if(direction == 'R'){
                    return WEST;
                }
                break;
            case EAST:
                if(direction == 'L'){
                    return NORTH;
                } else if(direction == 'R'){
                    return SOUTH;
                }
                break;
            case WEST:
                if(direction == 'L'){
                    return SOUTH;
                } else if(direction == 'R'){
                    return NORTH;
                }
                break;
            default:
                return null;
        }
        return null;
    }

    public static int getDistance(Point start, Point current){
        return Math.abs(start.x - current.x) + Math.abs(start.y - current.y);
    }

    //part 2 methods
    public static void addVisitedPoint(Point current, int x, int y){
        int nowX = current.x;
        int nowY = current.y;

        int finalX = x + nowX;
        int finalY = y + nowY;

        if(x != 0){
            if(x > 0){
                for(int i = nowX + 1; i <= finalX; i++){
                    Point walking = new Point(i, nowY);
                    if(rabbitHole == null && beenBefore(walking)){
                        rabbitHole = walking;
                    }
                    alreadyBeen.add(walking);
                }
            } else if (x < 0){
                for(int i = nowX - 1; i >= finalX; i--){
                    Point walking = new Point(i, nowY);
                    if(rabbitHole == null && beenBefore(walking)){
                        rabbitHole = walking;
                    }
                    alreadyBeen.add(walking);
                }
            }
        } else if(y != 0){
            if(y > 0){
                for(int i = nowY + 1; i <= finalY; i++){
                    Point walking = new Point(nowX, i);
                    if(rabbitHole == null && beenBefore(walking)){
                        rabbitHole = walking;
                    }
                    alreadyBeen.add(walking);
                }
            } else if(y < 0){
                for(int i = nowY - 1; i >= finalY; i--){
                    Point walking = new Point(nowX, i);
                    if(rabbitHole == null && beenBefore(walking)){
                        rabbitHole = walking;
                    }
                    alreadyBeen.add(walking);
                }
            }
        }

    }

    public static boolean beenBefore(Point current){
        for(Point point: alreadyBeen){
            if(point.x == current.x && point.y == current.y){
                return true;
            }
        }
        return false;
    }
}
