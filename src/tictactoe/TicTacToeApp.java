package tictactoe;

import handler.InputHandler;

import java.util.Scanner;

public class TicTacToeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input command: > ");
            String[] input = scanner.nextLine().split(" ");
            if (input.length == 0) continue;
            if (input[0].equals("exit")) break;

            try {
                if (InputHandler.hasValidInput(input)) {
                    System.out.println("Bad parameters!");
                    continue;
                }

                Game game = InputHandler.parseInput(input);
                game.start();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}