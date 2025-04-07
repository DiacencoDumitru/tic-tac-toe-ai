package handler;

import tictactoe.Board;
import tictactoe.Game;
import factory.Player;
import factory.PlayerFactory;

public class InputHandler {

    private InputHandler() {}

    public static boolean hasValidInput(String[] input) {
        if (input.length < 3 || !input[0].equalsIgnoreCase("start")) {
            return true;
        }
        int length = input.length;
        return length != 3 && length != 4 && length != 6;
    }

    public static Game parseInput(String[] input) {
        if (hasValidInput(input)) {
            throw new IllegalArgumentException("Bad parameters!");
        }

        return switch (input.length) {
            case 3 -> parseDefaultGame(input);
            case 4 -> parseWithBoardSize(input);
            case 6 -> parseWithBoardSizeAndSymbols(input);
            default -> throw new IllegalArgumentException("Bad parameters!");
        };
    }

    private static Game parseDefaultGame(String[] input) {
        int boardSize = 3;
        char xSymbol = 'X';
        char oSymbol = 'O';
        Board board = new Board(boardSize);
        Player playerX = PlayerFactory.createPlayer(input[1], xSymbol, board);
        Player playerO = PlayerFactory.createPlayer(input[2], oSymbol, board);
        return new Game(board, playerX, playerO);
    }

    private static Game parseWithBoardSize(String[] input) {
        int boardSize = Integer.parseInt(input[3]);
        validateBoardSize(boardSize);
        char xSymbol = 'X';
        char oSymbol = 'O';
        Board board = new Board(boardSize);
        Player playerX = PlayerFactory.createPlayer(input[1], xSymbol, board);
        Player playerO = PlayerFactory.createPlayer(input[2], oSymbol, board);
        return new Game(board, playerX, playerO);
    }

    private static Game parseWithBoardSizeAndSymbols(String[] input) {
        int boardSize = Integer.parseInt(input[3]);
        validateBoardSize(boardSize);

        char xSymbol = input[4].charAt(0);
        char oSymbol = input[5].charAt(0);
        validateSymbols(xSymbol, oSymbol);

        Board board = new Board(boardSize);
        Player playerX = PlayerFactory.createPlayer(input[1], xSymbol, board);
        Player playerO = PlayerFactory.createPlayer(input[2], oSymbol, board);
        return new Game(board, playerX, playerO);
    }

    private static void validateBoardSize(int size) {
        if (size < 3 || size > 10) {
            throw new IllegalArgumentException("Board size must be between 3 and 10.");
        }
    }

    private static void validateSymbols(char x, char o) {
        if (x == o) {
            throw new IllegalArgumentException("Symbols must be different");
        }
    }
}
