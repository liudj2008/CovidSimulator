import java.util.Random;

enum Direction {
    UP, DOWN, LEFT, RIGHT, UPRIGHT, UPLEFT, DOWNLEFT, DOWNRIGHT, STAY;
    private static final Direction[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random rand = new Random();

    public static Direction getRandomDirection()  {
        return VALUES[rand.nextInt(SIZE)];
    }
}