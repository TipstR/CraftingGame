module org.game {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example to javafx.fxml;
    exports org.game;
    opens org.game to javafx.fxml;
}
