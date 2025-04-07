package factory;

import tictactoe.Board;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner scanner = new Scanner(System.in);
    private final char symbol;

    public HumanPlayer(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public Board.Position getMove(Board board) {
        while (true) {
            System.out.print("Enter the coordinates: > ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ");
            if (parts.length != 2) continue;
            try {
                int row = Integer.parseInt(parts[0]) - 1;
                int col = Integer.parseInt(parts[1]) - 1;
                if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                    return new Board.Position(row, col);
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}