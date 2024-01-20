package com.example.chessit;

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
                if (moveIndexes[1] == moveIndexes[3] && (moveIndexes[2] == moveIndexes[0] - 1 || (moveIndexes[2] == moveIndexes[0] - 2 && moveIndexes[0] == 6)) && pieceMovesTo == null) {
                    System.out.println("Valid Pawn move");
                    return true;
                }
                else if((moveIndexes[1] == moveIndexes[3] -1 && moveIndexes[2] == moveIndexes[0] - 1 && pieceMovesTo != null) ||
                        (moveIndexes[1] == moveIndexes[3] +1 && moveIndexes[2] == moveIndexes[0] - 1 && pieceMovesTo != null)){
                    System.out.println("Valid Pawn Move");
                    return true;
                }
                else {
                    System.out.println("Invalid Pawn move");
                    return false;
                }
            } else if (pieceToBeMoved.equals("PawnB")) {
                // Check if the move is valid for a black Pawn piece
                if (moveIndexes[1] == moveIndexes[3] && (moveIndexes[2] == moveIndexes[0] + 1 || (moveIndexes[2] == moveIndexes[0] + 2 && moveIndexes[0] == 1)) && pieceMovesTo == null) {
                    System.out.println("Valid Black Pawn move");
                    return true;
                }
                else if((moveIndexes[1] == moveIndexes[3] -1 && moveIndexes[2] == moveIndexes[0] + 1 && pieceMovesTo != null) ||
                        (moveIndexes[1] == moveIndexes[3] +1 && moveIndexes[2] == moveIndexes[0] + 1 && pieceMovesTo != null)){
                    System.out.println("Valid Black Pawn Move");
                    return true;
                }
                else {
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
}
