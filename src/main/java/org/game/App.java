package org.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
     * @param stage The Stage ore window of the UI.
     * @throws IOException Can throw IOException.
     */
    @Override
    public void start(final Stage stage) throws IOException {
        initializeScene();

        if (scene == null) {
            return;
        }
        stage.setScene(scene);
        stage.setTitle("Crafting Game");
        stage.show();

    }

    /**
     * Initializes scene in a static function, so it can be used in start(stage).
     * @throws IOException Can throw IOException.
     */
    private static void initializeScene() throws IOException {
        final int RES_X = 640;
        final int RES_Y = 480;

        scene = new Scene(loadFXML("menu"), RES_X, RES_Y);
    }

    /**
     * Sets the root to a fxml file. This determines the first screen you see on the UI.
     * @param fxml Name of the fxml file.
     * @throws IOException Can throw IOException.
     */
    static void setRoot(final String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Loads a fxml-file.
     * @param fxml Name of the fxml-file.
     * @return A loaded fxml-file.
     * @throws IOException Can throw IOException.
     */
    private static Parent loadFXML(final String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Main Function of the class.
     * @param args Arguments for main.
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
