package org.game;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller for the Game-UI.
 */
public class GameController {


    /**
     * Text field in UI to display the ore count.
     */
    @FXML
    private TextField oreCountTextField;
    /**
     * Count of stored ore.
     */
    private int[] oreCount = {0};
    /**
     * Is true if miner should work.
     */
    private boolean[] minerGo = {false};

    /**
     * Text field in UI to display the wood count.
     */
    @FXML
    private TextField woodCountTextField;
    /**
     * Count of stored wood.
     */
    private int[] woodCount = {0};
    /**
     * Is true if wood cutter should work.
     */
    private boolean[] woodCutterGo = {false};

    /**
     * Function to start mining ore. This is called by JavaFX.
     */
    @FXML
    public void selectMine() {
        final int DELAY = 1000;
        gatherResources(minerGo, DELAY, oreCountTextField, oreCount);
    }

    /**
     * Function to start cutting wood. This is called by JavaFX.
     */
    @FXML
    public void selectTimber() {
        final int DELAY = 1000;
        gatherResources(woodCutterGo, DELAY, woodCountTextField, woodCount);
    }

    /**
     * Function to stop all workers. This is called by JavaFX.
     */
    @FXML
    public void stopWorker() {
        minerGo[0] = false;
        woodCutterGo[0] = false;
    }

    /**
     * Function to start gathering a specific resource. After calling it stops every worker and starts a new Thread.
     *
     * @param workerGo Reference to a worker Boolean. Determines whether resources are getting gathered or not.
     * @param delay Determines the pause between gathering in ms
     * @param textField Reference to a TextField in JavaFx
     * @param resourceCount Reference to the specific resource count.
     */
    public void gatherResources(final boolean[] workerGo,
                                final long delay,
                                final TextField textField,
                                final int[] resourceCount) {
        stopWorker();

        workerGo[0] = true;
        Thread worker = new Thread(new Runnable() {
            @Override
            public void run() {
                long elapsedTime = 0;
                long previousTime = 0;

                while (workerGo[0]) {

                    long currentTime = System.currentTimeMillis();

                    elapsedTime += currentTime - previousTime;

                    if (elapsedTime >= delay) {
                        System.out.println("gameController running...");
                        elapsedTime = 0;
                        textField.setText(Integer.toString(resourceCount[0]));
                        resourceCount[0]++;
                    }

                    previousTime = currentTime;
                }
            }
        });
        worker.start();
    }
}


