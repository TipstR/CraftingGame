package org.game;

import javafx.fxml.FXML;

import java.io.IOException;

public class MenuController {

    @FXML
    private void newGame() throws IOException {
        App.setRoot("game");
    }
}
