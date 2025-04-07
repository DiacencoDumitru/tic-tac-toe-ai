package factory;

import tictactoe.Board;

import java.util.List;
import java.util.Random;

public class EasyAIPlayer implements Player {
    private final Random random = new Random();
    private final char symbol;
    private final boolean silent;

    public EasyAIPlayer(char symbol, boolean silent) {
        this.symbol = symbol;
        this.silent = silent;
    }

    @Override
    public Board.Position getMove(Board board) {
        if (!silent) {
            System.out.println("Making move level \"easy\"");
        }
        List<Board.Position> positions = board.getAvailablePositions();
        return positions.get(random.nextInt(positions.size()));
    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}
