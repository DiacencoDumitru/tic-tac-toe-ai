package factory;

import tictactoe.Board;

public interface Player {
    Board.Position getMove(Board board);
    char getSymbol();
}
