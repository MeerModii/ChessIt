package com.example.chessit;
import java.util.Scanner;

public class ChessGame extends ChessMethods {

    // Method to get the piece at a specific row and column on the chessboard
    public String getPieceAt(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) {
            return null; // Invalid position
        }
        return chessboard[row][col];
    }

    @Override
    // Method to check valid move for different pieces
    public boolean checkValidMove(int[] moveIndexes) {
        String pieceToBeMoved = chessboard[moveIndexes[0]][moveIndexes[1]];
        String pieceMovesTo = chessboard[moveIndexes[2]][moveIndexes[3]];

        System.out.println("Piece to be moved: " + pieceToBeMoved);
        System.out.println("Piece moves to: " + pieceMovesTo);

        if (pieceToBeMoved != null) {
            if (pieceToBeMoved.equals("Pawn")) {
                // Check if the move is valid for a Pawn piece
                if (moveIndexes[1] == moveIndexes[3] && moveIndexes[2] == moveIndexes[0] - 1 && pieceMovesTo == null) {
                    System.out.println("Valid Pawn move");
                    return true;
                } else {
                    System.out.println("Invalid Pawn move");
                    return false;
                }
            } else if (pieceToBeMoved.equals("PawnB")) {
                // Check if the move is valid for a black Pawn piece
                if (moveIndexes[1] == moveIndexes[3] && moveIndexes[2] == moveIndexes[0] + 1 && pieceMovesTo == null) {
                    System.out.println("Valid Black Pawn move");
                    return true;
                } else {
                    System.out.println("Invalid Black Pawn move");
                    return false;
                }
            } else if (pieceToBeMoved.equals("KnightB") || pieceToBeMoved.equals("Knight")) {
                // Check if the move is valid for a Knight piece
                int rowDiff = Math.abs(moveIndexes[2] - moveIndexes[0]);
                int colDiff = Math.abs(moveIndexes[3] - moveIndexes[1]);
                if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
                    System.out.println("Valid Knight move");
                    return true;
                } else {
                    System.out.println("Invalid Knight move");
                    return false;
                }
            } else if (pieceToBeMoved.equals("BishopB") || pieceToBeMoved.equals("Bishop")) {
                // Check if the move is valid for a Bishop piece
                int rowDiff = Math.abs(moveIndexes[2] - moveIndexes[0]);
                int columnDiff = Math.abs(moveIndexes[3] - moveIndexes[1]);
                if (rowDiff == columnDiff) {
                    System.out.println("Valid Bishop move");
                    return true;
                } else {
                    System.out.println("Invalid Bishop move");
                    return false;
                }
            } else if (pieceToBeMoved.equals("KingB") || pieceToBeMoved.equals("King")) {
                // Check if the move is valid for a King piece
                int rowDiff = Math.abs(moveIndexes[2] - moveIndexes[0]);
                int columnDiff = Math.abs(moveIndexes[3] - moveIndexes[1]);
                if ((rowDiff <= 1 && columnDiff <= 1)) {
                    System.out.println("Valid King move");
                    return true;
                } else {
                    System.out.println("Invalid King move");
                    return false;
                }
            } else if (pieceToBeMoved.equals("QueenB") || pieceToBeMoved.equals("Queen")) {
                // Check if the move is valid for a Queen piece
                int rowDiff = Math.abs(moveIndexes[2] - moveIndexes[0]);
                int columnDiff = Math.abs(moveIndexes[3] - moveIndexes[1]);
                if ((rowDiff == columnDiff) || (moveIndexes[0] == moveIndexes[2]) || (moveIndexes[1] == moveIndexes[3])) {
                    System.out.println("Valid Queen move");
                    return true;
                } else {
                    System.out.println("Invalid Queen move");
                    return false;
                }
            } else if (pieceToBeMoved.equals("RookB") || pieceToBeMoved.equals("Rook")) {
                // Check if the move is valid for a Rook piece
                if ((moveIndexes[0] == moveIndexes[2]) || (moveIndexes[1] == moveIndexes[3])) {
                    System.out.println("Valid Rook move");
                    return true;
                } else {
                    System.out.println("Invalid Rook move");
                    return false;
                }
            } else {
                System.out.println("Invalid piece");
                return false;
            }
        } else {
            System.out.println("No piece found at the source position");
            return false;
        }
    }

    @Override
    public void makeMove(int[] moveIndexes, boolean validMove) {
        // TODO Auto-generated method stub
        String pieceToBeMoved = chessboard[moveIndexes[0]][moveIndexes[1]];
        String pieceMovesTo = chessboard[moveIndexes[2]][moveIndexes[3]];
//
//        System.out.println("Piece to be moved: " + pieceToBeMoved);
//        System.out.println("Piece moves to: " + pieceMovesTo);

        if (validMove) {
            // Move the piece to the destination cell
            chessboard[moveIndexes[2]][moveIndexes[3]] = pieceToBeMoved;
            // Empty the source cell (set it to null)
            chessboard[moveIndexes[0]][moveIndexes[1]] = null;
        }
    }

    // Main method to run the chess game
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an instance of the ChessGame class
        ChessGame chessGame = new ChessGame();

        // Print the initial chessboard
        chessGame.printChessboard();

        while (true) {
            System.out.print("Enter your move: ");
            String input = scanner.nextLine().toUpperCase();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game.");
                break;
            }

            int[] moveIndexes = chessGame.getIndexes(input);
            boolean isValidMove = chessGame.checkValidMove(moveIndexes);

            if (isValidMove) {
                // Apply the move and update the chessboard
                chessGame.makeMove(moveIndexes, isValidMove);
                System.out.println("Move is valid. Chessboard after the move:");
                chessGame.printChessboard();
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }
}
