package tictactoe;

import factory.Player;

public class Game {
    private final Board board;
    private final Player playerX;
    private final Player playerO;

    public Game(Board board, Player xPlayer, Player oPlayer) {
        this.board = board;
        this.playerX = xPlayer;
        this.playerO = oPlayer;
    }

    public void start() {
        board.displayMatrix();
        char currentSymbol = playerX.getSymbol();

        while (board.isPlayable()) {
            Player currentPlayer = (currentSymbol == playerX.getSymbol()) ? playerX : playerO;
            Board.Position move = currentPlayer.getMove(board);
            if (!board.acceptMove(currentSymbol, move)) continue;

            board.displayMatrix();
            String result = board.checkWinner(board.getMatrixCopy());
            if (!result.isEmpty()) {
                System.out.println(result);
                break;
            } else if (board.isDraw()) {
                System.out.println("Draw");
                break;
            }

            currentSymbol = (currentSymbol == playerX.getSymbol()) ? playerO.getSymbol() : playerX.getSymbol();
        }
    }
}