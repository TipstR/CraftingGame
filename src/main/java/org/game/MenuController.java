package org.game;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 * Controller for the Menu-UI.
 */
public class MenuController {


  /**
   * Function for starting a new game. This is called by JavaFX.
   *
   * @throws IOException Can throw IOException.
   */
  @FXML
  private void newGame() throws IOException {
    App.setRoot("game");
  }
}
