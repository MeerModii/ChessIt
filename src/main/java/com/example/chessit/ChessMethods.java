package com.example.chessit;

public abstract class ChessMethods {

    // Creating chessboard array
    protected static String[][] chessboard = new String[8][8];

    // Static block to assemble pieces in the array
    static {
        // Initialize the chessboard with the starting arrangement of pieces
        // Black Side
        chessboard[0][0] = "RookB";
        chessboard[0][1] = "KnightB";
        chessboard[0][2] = "BishopB";
        chessboard[0][3] = "KingB";
        chessboard[0][4] = "QueenB";
        chessboard[0][5] = "BishopB";
        chessboard[0][6] = "KnightB";
        chessboard[0][7] = "RookB";

        // White Side
        chessboard[7][0] = "Rook";
        chessboard[7][1] = "Knight";
        chessboard[7][2] = "Bishop";
        chessboard[7][3] = "King";
        chessboard[7][4] = "Queen";
        chessboard[7][5] = "Bishop";
        chessboard[7][6] = "Knight";
        chessboard[7][7] = "Rook";

        // Pawns
        for (int i = 0; i < 8; i++) {
            chessboard[6][i] = "Pawn";
            chessboard[1][i] = "PawnB";
        }
    }

    // Printing the array
    public void printChessboard() {
        // Print the current chessboard arrangement
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(chessboard[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Not used when using drag and drop feature
    public int[] convertToIndices(String position) {
        // Convert a chess notation position (e.g., A1) to array indices
        int[] indices = new int[2];
        indices[0] = Integer.parseInt(position.substring(1)) - 1; // Row index
        indices[1] = position.charAt(0) - 'A'; // Column index

        System.out.println("Position: " + position);
        System.out.println("Row index: " + indices[0]);
        System.out.println("Column index: " + indices[1]);

        return indices;
    }


    // Not used when using drag and drop feature
    public int[] getIndexes(String input) {
        // Split the user input (e.g., "A2 B3") and convert to array indices
        String[] moves = input.split(" ");

        // Chess notation
        String sourcePosition = moves[0];
        String destinationPosition = moves[1];

        System.out.println("Source Position: " + sourcePosition);
        System.out.println("Destination Position: " + destinationPosition);

        // Convert the source and destination positions to array indices
        int[] sourceIndices = convertToIndices(sourcePosition);
        int[] destinationIndices = convertToIndices(destinationPosition);

        int sourceRow = sourceIndices[0];
        int sourceColumn = sourceIndices[1];

        int destinationRow = destinationIndices[0];
        int destinationColumn = destinationIndices[1];

        int[] indexes = { sourceRow, sourceColumn, destinationRow, destinationColumn };
        return indexes;
    }

    // Abstract method to check valid move for different pieces
    public abstract boolean checkValidMove(int[] moveIndexes);

    // Abstract method to make a move
    public abstract void makeMove(int[] moveIndexes, boolean validmove);
}
