package org.game;

public class GameController {
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


