module org.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;
    requires org.apache.logging.log4j;

    exports org.game;
    opens org.game to javafx.fxml;


}
