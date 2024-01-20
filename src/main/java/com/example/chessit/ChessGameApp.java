package com.example.chessit;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import java.io.File;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChessGameApp extends Application {

    // Create an instance of the ChessGame class
    private ChessGame chessGame = new ChessGame();
    private ImageView[][] squares = new ImageView[8][8];

    // GUI elements
    private TextField inputField;
    private Label messageLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the chessboard GUI
        GridPane chessboard = createChessboard();

        // Create the input area for moves and message label
        VBox vbox = createInputArea();

        // Combine the chessboard and input area in an HBox
        HBox hbox = new HBox(chessboard);
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(10));

        // Create the main scene and display it
        Scene scene = new Scene(hbox);
        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to create the chessboard GUI
    private GridPane createChessboard() {
        GridPane chessboard = new GridPane();
        chessboard.setGridLinesVisible(true);

        // Create the squares of the chessboard and set their colors
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                StackPane square = new StackPane();
                square.setMinSize(120, 120);
                square.setStyle((row + col) % 2 == 0 ? "-fx-background-color: white;" : "-fx-background-color: green;");
                squares[row][col] = new ImageView();
                square.getChildren().add(squares[row][col]);
                chessboard.add(square, col, row);

                // Add mouse event handlers for each square
                final int currentRow = row;
                final int currentCol = col;
                square.setOnDragDetected(e -> onDragDetected(e, currentRow, currentCol));
                square.setOnDragOver(e -> onDragOver(e, currentRow, currentCol));
                square.setOnDragDropped(e -> onDragDropped(e, currentRow, currentCol));
                square.setOnDragDone(e -> onDragDone(e));
            }
        }

        // Initialize the pieces on the chessboard
        initializePieces();
        return chessboard;
    }

    private void initializePieces() {

        // KnightB
        File imageFileKnightB = new File("Images/Knight Chess Piece.png");
        Image pieceImage = new Image(imageFileKnightB.toURI().toString());
        squares[0][1].setImage(pieceImage);
        squares[0][6].setImage(pieceImage);

        // RookB
        File imageFileRookB = new File("Images/Rook Chess Piece.png");
        pieceImage = new Image(imageFileRookB.toURI().toString());
        squares[0][0].setImage(pieceImage);
        squares[0][7].setImage(pieceImage);

        // BishopB
        File imageFileBishopB = new File("Images/Bishop Chess Piece.png");
        pieceImage = new Image(imageFileBishopB.toURI().toString());
        squares[0][2].setImage(pieceImage);
        squares[0][5].setImage(pieceImage);

        // KingB
        File imageFileKingB = new File("Images/King Chess Piece.png");
        pieceImage = new Image(imageFileKingB.toURI().toString());
        squares[0][3].setImage(pieceImage);

        // QueenB
        File imageFileQueenB = new File("Images/Queen Chess Piece.png");
        pieceImage = new Image(imageFileQueenB.toURI().toString());
        squares[0][4].setImage(pieceImage);

        // PawnB
        for (int i = 0; i < 8; i++) {
            File imageFilePawnB = new File("Images/Pawn Chess Piece.png");
            pieceImage = new Image(imageFilePawnB.toURI().toString());
            squares[1][i].setImage(pieceImage);
        }

        // KnightW
        File imageFileKnightW = new File("Images/Knight Chess Piece W.png");
        pieceImage = new Image(imageFileKnightW.toURI().toString());
        squares[7][1].setImage(pieceImage);
        squares[7][6].setImage(pieceImage);

        // RookW
        File imageFileRookW = new File("Images/Rook Chess Piece W.png");
        pieceImage = new Image(imageFileRookW.toURI().toString());
        squares[7][0].setImage(pieceImage);
        squares[7][7].setImage(pieceImage);

        // BishopW
        File imageFileBishopW = new File("Images/Bishop Chess Piece W.png");
        pieceImage = new Image(imageFileBishopW.toURI().toString());
        squares[7][2].setImage(pieceImage);
        squares[7][5].setImage(pieceImage);

        // KingW
        File imageFileKingW = new File("Images/King Chess Piece W.png");
        pieceImage = new Image(imageFileKingW.toURI().toString());
        squares[7][3].setImage(pieceImage);

        // QueenW
        File imageFileQueenW = new File("Images/Queen Chess Piece W.png");
        pieceImage = new Image(imageFileQueenW.toURI().toString());
        squares[7][4].setImage(pieceImage);

        // PawnW
        for (int i = 0; i < 8; i++) {
            File imageFilePawnW = new File("Images/Pawn Chess Piece W.png");
            pieceImage = new Image(imageFilePawnW.toURI().toString());
            squares[6][i].setImage(pieceImage);
        }
    }

    // Method to create the input area for moves and message label
    private VBox createInputArea() {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        inputField = new TextField();
        Button submitButton = new Button("Submit Move");
        messageLabel = new Label();

        // When the submit button is clicked, process the move
        submitButton.setOnAction(e -> processMove());

        vbox.getChildren().addAll(inputField, submitButton, messageLabel);
        return vbox;
    }

    // Method to process the move submitted by the user
    private void processMove() {
        String input = inputField.getText().toUpperCase();
        int[] moveIndexes = chessGame.getIndexes(input);
        boolean isValidMove = chessGame.checkValidMove(moveIndexes);

        if (isValidMove) {
            int sourceRow = moveIndexes[0];
            int sourceColumn = moveIndexes[1];
            int destRow = moveIndexes[2];
            int destColumn = moveIndexes[3];

            // Get the image from the source location
            Image pieceImage = squares[sourceRow][sourceColumn].getImage();

            // Move the image to the destination location
            squares[destRow][destColumn].setImage(pieceImage);

            // Clear the source location
            squares[sourceRow][sourceColumn].setImage(null);

            // Update the chess game model
            chessGame.makeMove(moveIndexes, isValidMove);

            messageLabel.setText("Move is valid.");
        } else {
            messageLabel.setText("Invalid move.");
        }
    }

    private void onDragDetected(MouseEvent event, int row, int col) {
        Image pieceImage = squares[row][col].getImage();
        if (pieceImage != null) {
            Dragboard db = squares[row][col].startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(pieceImage);
            db.setContent(content);
            event.consume();
        }
    }

    private void onDragOver(DragEvent event, int row, int col) {
        if (event.getGestureSource() != squares[row][col] && event.getDragboard().hasImage()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    private void onDragDropped(DragEvent event, int destRow, int destCol) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasImage()) {
            int sourceRow = -1;
            int sourceCol = -1;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (squares[row][col] == event.getGestureSource()) {
                        sourceRow = row;
                        sourceCol = col;
                        break;
                    }
                }
            }

            if (sourceRow != -1 && sourceCol != -1) {
                // Check if the move is valid
                int[] moveIndexes = { sourceRow, sourceCol, destRow, destCol };
                boolean isValidMove = chessGame.checkValidMove(moveIndexes);

                if (isValidMove) {
                    // Move the piece to the destination
                    squares[destRow][destCol].setImage(db.getImage());
                    squares[sourceRow][sourceCol].setImage(null);

                    // Update the chess game model
                    chessGame.makeMove(moveIndexes, isValidMove);
                    messageLabel.setText("Move is valid.");
                    success = true;
                } else {
                    messageLabel.setText("Invalid move.");
                }
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }

    private void onDragDone(DragEvent event) {
        event.consume();
    }

}
