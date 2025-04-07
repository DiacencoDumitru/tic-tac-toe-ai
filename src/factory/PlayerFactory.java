package factory;

import tictactoe.Board;

public interface PlayerFactory {
    static Player createPlayer(String mode, char symbol, Board board) {
        return switch (mode.toLowerCase()) {
            case "user" -> new HumanPlayer(symbol);
            case "easy" -> new EasyAIPlayer(symbol, false);
            case "medium" -> new MediumAIPlayer(symbol);
            case "hard" -> new HardAIPlayer(symbol, board);
            default -> throw new IllegalArgumentException("Bad parameters!");
        };
    }
}
