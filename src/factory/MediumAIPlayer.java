package factory;

import tictactoe.Board;

import java.util.Random;

import static tictactoe.Board.EMPTY_SPACE;

public class MediumAIPlayer implements Player {
    private final char symbol;
    private final char opponent;

    public MediumAIPlayer(char symbol) {
        this.symbol = symbol;
        this.opponent = (symbol == 'X') ? 'O' : 'X';
    }

    @Override
    public Board.Position getMove(Board board) {
        System.out.println("Making move level \"medium\"");
        return mediumMove(board, symbol, opponent);
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    private Board.Position mediumMove(Board board, char player, char opponent) {
        char[][] matrix = board.getMatrixCopy();
        int size = matrix.length;
        int neededCount = size - 1;

        // if we can win
        Board.Position winningMove = findWinningOrBlockingMove(matrix, player, neededCount);
        if (winningMove != null) return winningMove;

        // if we can block
        Board.Position blockingMove = findWinningOrBlockingMove(matrix, opponent, neededCount);
        if (blockingMove != null) return blockingMove;

        // otherwise will use random from easy. (existing logic)
        return new EasyAIPlayer(symbol, true).getMove(board);
    }

    private Board.Position findWinningOrBlockingMove(char[][] matrix, char target, int count) {
        int size = matrix.length;

        for (int i = 0; i < size; i++) {
            Board.Position position = checkLine(matrix[i], i, true, target, count); // row
            if (position != null) return position;

            char[] column = getColumn(matrix, i);
            position = checkLine(column, i, false, target, count); // col
            if (position != null) return position;
        }

        Board.Position diag = checkDiagonal(matrix, target, count, true);  // leftdiag
        if (diag != null) return diag;

        diag = checkDiagonal(matrix, target, count, false); // right diag
        if (diag != null) return diag;

        return null;
    }

    private Board.Position checkLine(char[] line, int index, boolean isRow, char target, int count) {
        if (countOccurrences(line, target) != count) return null;

        for (int i = 0; i < line.length; i++) {
            if (line[i] == EMPTY_SPACE) {
                return isRow ? new Board.Position(index, i) : new Board.Position(i, index);
            }
        }
        return null;
    }

    private char[] getColumn(char[][] matrix, int colIndex) {
        int size = matrix.length;
        char[] col = new char[size];
        for (int i = 0; i < size; i++) {
            col[i] = matrix[i][colIndex];
        }
        return col;
    }

    private Board.Position checkDiagonal(char[][] matrix, char target, int count, boolean isLeft) {
        int size = matrix.length;
        int matchCount = 0;

        for (int i = 0; i < size; i++) {
            char cell = isLeft ? matrix[i][i] : matrix[i][size - i - 1];
            if (cell == target) matchCount++;
        }

        if (matchCount != count) return null;

        for (int i = 0; i < size; i++) {
            int row = i;
            int col = isLeft ? i : size - i - 1;
            if (matrix[row][col] == EMPTY_SPACE) {
                return new Board.Position(row, col);
            }
        }

        return null;
    }

    private int countOccurrences(char[] array, char target) {
        int count = 0;
        for (char c : array) {
            if (c == target) count++;
        }
        return count;
    }
}
