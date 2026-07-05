import java.util.Scanner;

public class ConnectFour {

    static final int ROWS = 6;
    static final int COLS = 7;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        char choice;

        do {

            char[][] board = new char[ROWS][COLS];

            // Fill board with '.'
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    board[i][j] = '.';
                }
            }

            char player = 'X';
            int moves = 0;
            boolean win = false;

            while (!win && moves < ROWS * COLS) {

                displayBoard(board);

                System.out.println("Player " + player + ", enter column (1-7): ");
                int col = sc.nextInt() - 1;

                if (col < 0 || col >= COLS) {
                    System.out.println("Invalid column!");
                    continue;
                }

                int row = placePiece(board, col, player);

                if (row == -1) {
                    System.out.println("Column is full!");
                    continue;
                }

                moves++;

                if (checkWin(board, row, col, player)) {
                    displayBoard(board);
                    System.out.println("Player " + player + " wins!");
                    win = true;
                } else if (moves == ROWS * COLS) {
                    displayBoard(board);
                    System.out.println("Game Draw!");
                } else {
                    if (player == 'X')
                        player = 'O';
                    else
                        player = 'X';
                }
            }

            System.out.print("Play Again? (Y/N): ");
            choice = sc.next().charAt(0);

        } while (choice == 'Y' || choice == 'y');

        sc.close();
    }

    // Display Board
    static void displayBoard(char[][] board) {

        System.out.println();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("1 2 3 4 5 6 7");
        System.out.println();
    }

    // Place Piece
    static int placePiece(char[][] board, int col, char player) {

        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][col] == '.') {
                board[i][col] = player;
                return i;
            }
        }

        return -1;
    }

    // Check Win
    static boolean checkWin(char[][] board, int row, int col, char player) {

        return checkDirection(board, row, col, player, 0, 1) ||   // Horizontal
               checkDirection(board, row, col, player, 1, 0) ||   // Vertical
               checkDirection(board, row, col, player, 1, 1) ||   // Diagonal \
               checkDirection(board, row, col, player, 1, -1);    // Diagonal /
    }

    // Count pieces in one direction
    static boolean checkDirection(char[][] board, int row, int col, char player, int dr, int dc) {

        int count = 1;

        int r = row + dr;
        int c = col + dc;

        while (r >= 0 && r < ROWS && c >= 0 && c < COLS && board[r][c] == player) {
            count++;
            r += dr;
            c += dc;
        }

        r = row - dr;
        c = col - dc;

        while (r >= 0 && r < ROWS && c >= 0 && c < COLS && board[r][c] == player) {
            count++;
            r -= dr;
            c -= dc;
        }

        return count >= 4;
    }
}