module src.sudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.sudoku to javafx.fxml;
    exports com.sudoku;
}