## 1. Tic-Tac-Toe – Initial Setup
- Accepts an initial 3x3 board input (using `X`, `O`, `_`).
- Displays the board before and after a valid user move.
- Lets the user make a move by entering coordinates (1 to 3).
- Automatically chooses `X` or `O` based on the board state.
----------
- Validates user input and displays messages for:
    - Non-numeric input
    - Out-of-range coordinates
    - Occupied cells
-----------
- Shows the current game state after the move:
    - `Game not finished`
    - `Draw`
    - `X wins`
    - `O wins`

## 2. Tic-Tac-Toe – Easy Level AI

This stage introduces the first playable version of the Tic-Tac-Toe game with a basic AI opponent.
- The game starts with an empty 3x3 board.
- The user plays first as `X`, entering coordinates.
- The computer plays as `O` and makes random valid moves (easy level AI).
- Players alternate turns until the game ends.
-------
- The computer randomly selects an available cell for its move.
- No strategy is applied — this is the easiest difficulty.
-------
- Rejects invalid inputs (non-numeric, out-of-range, or occupied).
- Prompts the user until a valid move is made.

##### Game States
- `X wins`
- `O wins`
- `Draw`


## 3. Tic-Tac-Toe – Player Modes & Game Menu
- `start <player1> <player2>` — Starts a new game.    
`<player1>` plays as `X`, `<player2>` plays as `O`.
--------
- Each player can be:
    - `user` — Human player
    - `easy` — AI player (easy level)
- `exit` — Quits the game.

Examples:
- `start user easy` — Human vs AI
- `start easy user` — AI vs Human
- `start user user` — Two humans
- `start easy easy` — AI vs AI

## 4. Tic-Tac-Toe – Medium level AI
1. **Winning Move** – If the AI can win in one move, it plays that move.
2. **Blocking Move** – If the opponent is about to win, the AI blocks it.
3. **Fallback Move** – If neither of the above applies, the AI picks a random available move.

## 5. Tic-Tac-Toe – Hard level AI
The **Minimax algorithm** evaluates all possible moves and chooses the best one, assuming the opponent also plays optimally.
- It **simulates** future moves to predict outcomes.
- It **maximizes** its chances of winning.
- It **minimizes** the opponent's chances of winning.
- It **never makes mistakes** and **never loses**.


## Additional functionality:
- Customize the board size (e.g., 3x3, 5x5, or even 10x10).
- Choose own symbols instead of `X` and `O`.