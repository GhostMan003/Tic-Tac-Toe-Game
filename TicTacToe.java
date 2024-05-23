package Task_02_Tic_Tac_Toe_Game;

import java.util.Scanner;

public class TicTacToe {
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY_CELL = '-';
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = 'X';
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }

    public void playGame() {
        boolean gameWon = false;
        boolean gameOver = false;

        while (!gameOver) {
            displayBoard();
            makeMove();
            gameWon = checkWin();

            if (gameWon) {
                displayBoard();
                System.out.println("Player " + currentPlayer + " Wins!");
                gameOver = true;
            } else if (isBoardFull()) {
                displayBoard();
                System.out.println("It's a Draw!");
                gameOver = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private void displayBoard() {
        System.out.println("\n-------------");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
        System.out.println();
    }

    private void makeMove() {
        int row, col;

        do {
            System.out.print("Player " + currentPlayer + ", enter your move\n");
            row = getUserInput("row") - 1;
            col = getUserInput("column") - 1;
        } while (!isValidMove(row, col));

        board[row][col] = currentPlayer;
    }

    private int getUserInput(String coordinate) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter " + coordinate + " [1-3]: ");
        return scanner.nextInt();
    }

    private boolean isValidMove(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            System.out.println("Invalid move! Please enter valid row and column numbers.");
            return false;
        }

        if (board[row][col] != EMPTY_CELL) {
            System.out.println("Invalid move! That position is already occupied.");
            return false;
        }

        return true;
    }

    private boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
          if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
            return true;
          }
        }
    
        // Check columns
        for (int j = 0; j < 3; j++) {
          if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
            return true;
          }
        }
    
        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
          return true;
        }
    
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
          return true;
        }
    
        return false;
      }

    private boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    public void chooseSymbol() {
        char chosenSymbol = getUserSymbol();
        if (chosenSymbol == 'X' || chosenSymbol == 'O') {
            currentPlayer = chosenSymbol;
            System.out.println("You chose '" + currentPlayer + "'. Let's start the game!");
          } else {
            System.out.println("Invalid choice. Defaulting to 'X'. Let's start the game!");
          }
    }

    private char getUserSymbol() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose your symbol (X or O): ");
        return Character.toUpperCase(scanner.next().charAt(0));
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.chooseSymbol();
        game.playGame();
    }
}

