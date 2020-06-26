import java.util.Random;

public class helper {
    static Position getRandomPosition(int size) {
        Random rand = new Random();
        int row = rand.nextInt(size);
        int col = rand.nextInt(size);

        return new Position(row, col);
    }

    static Position getNextPosition(Position[][] matrix, int row, int col) {
        Direction direction = Direction.getRandomDirection();
        int size = matrix.length;

        if (direction == Direction.DOWN) {
            if (row + 1 < size) {
                row++;
            }
        }

        else if (direction == Direction.UP) {
            if (row - 1 >= 0) {
                row--;
            }
        }

        else if (direction == Direction.LEFT) {
            if (col - 1 >= 0) {
                col--;
            }
        }

        else if (direction == Direction.RIGHT) {
            if (col + 1 < size) {
                col++;
            }
        }

        else if (direction == Direction.UPLEFT) {
            if (row - 1 >= 0 && col - 1 >= 0) {
                row--;
                col--;
            }
        }

        else if (direction == Direction.UPRIGHT) {
            if (row - 1 >= 0 && col + 1 < size) {
                row--;
                col++;
            }
        }

        else if (direction == Direction.DOWNLEFT) {
            if (row + 1 < size && col - 1 >= 0) {
                row++;
                col--;
            }
        }

        else if (direction == Direction.DOWNRIGHT) {
            if (row + 1 < size && col + 1 < size) {
                row++;
                col++;
            }
        }

        return new Position(row, col);
    }
}
