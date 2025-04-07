package factory;

import tictactoe.Board;

public class HardAIPlayer implements Player {
    private final Board board;
    private final char symbol;
    private final char opponent;

    public HardAIPlayer(char symbol, Board board) {
        this.symbol = symbol;
        this.opponent = (symbol == 'X') ? 'O' : 'X';
        this.board = board;
    }

    @Override
    public Board.Position getMove(Board board) {
        System.out.println("Making move level \"hard\"");
        return aiMove(board.getMatrixCopy(), symbol, opponent);
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    private Board.Position aiMove(char[][] matrix, char player, char opponent) {
        int size = matrix.length;

        int bestScore = Integer.MIN_VALUE;
        int bestX = -1;
        int bestY = -1;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (matrix[row][col] == ' ') {
                    matrix[row][col] = player;
                    int score = minimax(matrix, false, player, opponent, size);
                    matrix[row][col] = ' ';

                    if (score > bestScore) {
                        bestScore = score;
                        bestX = row;
                        bestY = col;
                    }
                }
            }
        }
        return new Board.Position(bestX, bestY);
    }

    private int minimax(char[][] matrix, boolean isMaximizing, char player, char opponent, int size) {
        String result = board.checkWinner(matrix);
        if (result.equals(player + Board.WINS)) return 1;
        if (result.equals(opponent + Board.WINS)) return -1;

        // that's the main problem: minimax now evaluates draws based on the simulated matrix, not the actual board.
        if (isDrawMinimaxMatrix(matrix)) return 0;

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if (matrix[row][col] == ' ') {
                        matrix[row][col] = player;
                        int score = minimax(matrix, false, player, opponent, size);
                        matrix[row][col] = ' ';
                        bestScore = Math.max(bestScore, score);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if (matrix[row][col] == ' ') {
                        matrix[row][col] = opponent;
                        int score = minimax(matrix, true, player, opponent, size);
                        matrix[row][col] = ' ';
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
            return bestScore;
        }
    }

    public boolean isDrawMinimaxMatrix(char[][] matrix) {
        boolean isDraw = true;
        for (char[] row : matrix) {
            for (char c : row) {
                if (c == ' ') {
                    isDraw = false;
                    break;
                }
            }
            if (!isDraw) break;
        }
        return isDraw;
    }
}
