module org.example.strengir {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.strengir to javafx.fxml;
    exports org.example.strengir;
}