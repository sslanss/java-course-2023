package edu.hw1;

public final class Task8 {

    private static final int BOARD_SIZE = 8;

    private Task8() {
    }

    private static boolean checkBorders(int x, int y) {
        return x > 0 && x < BOARD_SIZE && y > 0 && y < BOARD_SIZE;
    }

    private static boolean checkBoardSize(int x, int y) {
        return x == BOARD_SIZE && y == BOARD_SIZE;
    }

    @SuppressWarnings("MagicNumber")
    private static boolean checkPosition(int x, int y, int[][] field) {
        int[][] steps = {
            {1, -2}, {1, 2},
            {2, -1}, {2, 1}
        };
        for (int i = 0; i < steps.length; i++) {
            int possibleX = x + steps[i][0];
            int possibleY = y + steps[i][1];
            if (checkBorders(possibleX, possibleY) && field[possibleX][possibleY] == 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean knightBoardCapture(int[][] field) {
        if (checkBoardSize(field.length, field[0].length)) {
            for (int i = 0; i < BOARD_SIZE - 1; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (field[i][j] == 1 && !checkPosition(i, j, field)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
