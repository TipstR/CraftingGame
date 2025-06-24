module org.game {
    requires javafx.controls;
    requires javafx.fxml;

    exports org.game;
    opens org.game to javafx.fxml;


}
