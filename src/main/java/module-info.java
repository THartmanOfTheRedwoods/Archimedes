module com.example.archimedes {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.redwoods.cis12.archimedes to javafx.fxml;
    exports edu.redwoods.cis12.archimedes;
}