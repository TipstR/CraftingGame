package org.game;

import atlantafx.base.theme.Dracula;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Main Class of Crafting Game.
 */
public class App extends Application {

  /**
   * It's the current scene of the stage. Like a site of a website.
   */
  private static Scene scene;

  /**
   * Needed for javafx to start the UI. It is automatically called when javafx:run is run in maven.
   *
   * @param stage The Stage ore window of the UI.
   * @throws IOException Can throw IOException.
   */
  @Override
  public void start(final Stage stage) throws IOException {
    initializeScene();

    if (scene == null) {
      return;
    }
    setUserAgentStylesheet(new Dracula().getUserAgentStylesheet());
    stage.setScene(scene);
    stage.setTitle("Crafting Game");

    stage.show();
  }

  /**
   * Initializes scene in a static function, so it can be used in start(stage).
   *
   * @throws IOException Can throw IOException.
   */
  private static void initializeScene() throws IOException {
    final int resX = 1680;
    final int resY = 1050;

    scene = new Scene(loadFxml("menu"), resX, resY);
  }

  /**
   * Sets the root to a fxml file. This determines the first screen you see on the UI.
   *
   * @param fxml Name of the fxml file.
   * @throws IOException Can throw IOException.
   */
  public static void setRoot(final String fxml) throws IOException {
    scene.setRoot(loadFxml(fxml));
  }

  /**
   * Loads a fxml-file.
   *
   * @param fxml Name of the fxml-file.
   * @return A loaded fxml-file.
   * @throws IOException Can throw IOException.
   */
  private static Parent loadFxml(final String fxml) throws IOException {
    final FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  /**
   * Main Function of the class.
   *
   * @param args Arguments for main.
   */
  public static void main(final String[] args) {
    launch(args);
  }
}
