package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    public static final char EMPTY_SPACE = ' ';
    public static final String WINS = " wins";
    private final char[][] matrix;
    private final int size;

    public Board() {
        matrix = new char[3][3];
        fillInitialMatrix(matrix);
        this.size = 3;
    }

    public Board(int size) {
        this.size = size;
        this.matrix = new char[size][size];
        fillInitialMatrix(matrix);
    }

    public static class Position {
        private final int row;
        private final int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }

    private void fillInitialMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            Arrays.fill(chars, EMPTY_SPACE);
        }
    }

    public List<Position> getAvailablePositions() {
        List<Position> positions = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (matrix[row][col] == EMPTY_SPACE) {
                    positions.add(new Position(row, col));
                }
            }
        }
        return positions;
    }

    public void displayMatrix() {
        System.out.println("---------");
        for (char[] row : matrix) {
            System.out.print("| ");
            for (char col : row) {
                System.out.print(col + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    public String checkWinner(char[][] matrix) {
        int length = matrix.length;

        for (int row = 0; row < length; row++) {

            if (matrix[row][0] != EMPTY_SPACE && allEqual(matrix[row])) {
                return matrix[row][0] + WINS;
            }

            char[] column = new char[length];
            for (int col = 0; col < length; col++) {
                column[col] = matrix[col][row];
            }
            if (column[0] != EMPTY_SPACE && allEqual(column)) {
                return column[0] + WINS;
            }
        }

        char[] leftDiagonal = new char[length];
        for (int i = 0; i < length; i++) {
            leftDiagonal[i] = matrix[i][i];
        }
        if (leftDiagonal[0] != EMPTY_SPACE && allEqual(leftDiagonal)) {
            return leftDiagonal[0] + WINS;
        }

        char[] rightDiagonal = new char[length];
        for (int i = 0; i < length; i++) {
            rightDiagonal[i] = matrix[i][length - i - 1];
        }
        if (rightDiagonal[0] != EMPTY_SPACE && allEqual(rightDiagonal)) {
            return rightDiagonal[0] + WINS;
        }

        return "";
    }

    private boolean allEqual(char[] line) {
        for (char c : line) {
            if (c != line[0]) {
                return false;
            }
        }
        return true;
    }

    public boolean hasWinner() {
        return checkWinner(getMatrixCopy()).isEmpty();
    }

    public boolean isPlayable() {
        return hasWinner() && !isDraw();
    }

    public boolean hasAvailablePositions() {
        return !getAvailablePositions().isEmpty();
    }

    public boolean isDraw() {
        return hasWinner() && !hasAvailablePositions();
    }

    public boolean acceptMove(char symbol, Position position) {
        if (position.getRow() < 0 || position.getCol() < 0 || position.getRow() > 2 || position.getCol() > 2 || matrix[position.getRow()][position.getCol()] != EMPTY_SPACE) {
            return false;
        }
        matrix[position.getRow()][position.getCol()] = symbol;
        return true;
    }

    public char[][] getMatrixCopy() {
        char[][] copy = new char[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = matrix[i].clone();
        }
        return copy;
    }
}
