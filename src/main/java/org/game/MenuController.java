package org.game;

import javafx.fxml.FXML;
import java.io.IOException;

/**
 * Controller for the Menu-UI.
 */
public class MenuController {


    /**
     * Function for starting a new game. This is called by JavaFX.
     * @throws IOException Can throw IOException.
     */
    @FXML
    private void newGame() throws IOException {
        App.setRoot("game");
    }
}
