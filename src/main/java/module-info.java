module org.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;

    exports org.game;
    opens org.game to javafx.fxml;


}
