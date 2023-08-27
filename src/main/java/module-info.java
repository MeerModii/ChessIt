module com.example.chessit {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chessit to javafx.fxml;
    exports com.example.chessit;
}