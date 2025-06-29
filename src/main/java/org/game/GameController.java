package org.game;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Controller for the Game-UI.
 */
public class GameController implements Initializable {

    /**
     * Text field in UI to display the population.
     */
    @FXML
    private TextField populationTextField;
    /**
     * Starting population.
     */
    private final int INIT_POPULATION = 3;
    /**
     * Keeps track of the population.
     */
    private int population = INIT_POPULATION;
    /**
     * Text field in UI to display not working workers/population.
     */
    @FXML
    private TextField freeWorkerTextField;
  /**
   * Text field in UI to display the population.
   */
  @FXML
  private TextField populationTextField;
  /**
   * Starting population.
   */
  private static final int INIT_POPULATION = 3;
  /**
   * Keeps track of the population.
   */
  private int population = INIT_POPULATION;
  /**
   * Text field in UI to display not working workers/population.
   */
  @FXML
  private TextField freeWorkerTextField;

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
   * Text field in UI to display current workers working as miners.
   */
  @FXML
  private TextField minerCountTextField;
  /**
   * List of miners. Keeps track of threads and kills them when done.
   */
  private final ArrayList<Worker> miners = new ArrayList<Worker>();

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
     * Text field in UI to display current workers working as lumber jacks.
     */
    @FXML
    private TextField lumberJackCountTextField;
    /**
     * List of lumber jacks. Keeps track of threads and kills them when done.
     */
    private ArrayList<Worker> lumberJacks = new ArrayList<Worker>();
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
   * Text field in UI to display current workers working as lumber jacks.
   */
  @FXML
  private TextField lumberJackCountTextField;
  /**
   * List of lumber jacks. Keeps track of threads and kills them when done.
   */
  private final ArrayList<Worker> lumberJacks = new ArrayList<Worker>();


    /**
     * Function to add miners to mine ore. This is called by JavaFX.
     */
    @FXML
    public void addMiner() {
        if (getFreeWorkers() <= 0) {
            return;
        }
  /**
   * Function to add miners to mine ore. This is called by JavaFX.
   */
  @FXML
  public void addMiner() {

    if (getFreeWorkers() <= 0) {
  /**
   * Function to add miners to mine ore. This is called by JavaFX.
   */
  @FXML
  public void addMiner() {

    if (getFreeWorkers() <= 0) {
      return;
    }

    final Worker miner = new Worker("miner");
    miners.add(miner);

    final int delay = 1000;
    gatherResources(miners.get(miners.size() - 1), delay, oreCount);
  }

  /**
   * Function to remove miners. This is called by JavaFX.
   */
  @FXML
  public void removeMiner() {
    LOGGER.info("Trying to remove miner...");
    if (miners.isEmpty()) {
      LOGGER.info("There are no miners to remove! miners.isEmpty(): {}", miners.isEmpty());
      return;
    }
    miners.get(miners.size() - 1).setWorking(false);
    LOGGER.info("Miner removed!");
  }


    /**
     * Function to add lumber jacks to gather wood. This is called by JavaFX.
     */
    @FXML
    public void addLumberJack() {
        if (getFreeWorkers() <= 0) {
            return;
        }
  /**
   * Function to add lumber jacks to gather wood. This is called by JavaFX.
   */
  @FXML
  public void addLumberJack() {
    if (getFreeWorkers() <= 0) {
  /**
   * Function to add lumber jacks to gather wood. This is called by JavaFX.
   */
  @FXML
  public void addLumberJack() {
    if (getFreeWorkers() <= 0) {
      return;
    }

    final Worker lumberJack = new Worker("lumberJack");
    lumberJacks.add(lumberJack);

    final int delay = 1000;
    gatherResources(lumberJacks.get(lumberJacks.size() - 1), delay, woodCount);
  }

  /**
   * Function to remove lumber jacks. This is called by JavaFX.
   */
  @FXML
  public void removeLumberJack() {
    if (lumberJacks.isEmpty()) {
            lumberJacks.isEmpty());
      return;
    }
    lumberJacks.get(lumberJacks.size() - 1).setWorking(false);
  }

  /**
   * Function to calculate how many free workers there are. This is calculated by subtracting the
   * whole population with the amount of working workers.
   *
   * @return Count of free workers.
   */
  public int getFreeWorkers() {
    return population - miners.size() - lumberJacks.size();
  }

  /**
   * Function to start gathering a specific resource. After calling, it starts a new Thread.
   *
   * @param worker        Reference to a worker. Determines whether resources are getting gathered
   *                      or not.
   * @param delay         Determines the pause between gathering in ms
   * @param resourceCount Reference to the specific resource count.
   */
  public void gatherResources(final Worker worker, final long delay, final int... resourceCount) {

    worker.setWorking(true);
    final Thread workerThread = new Thread(new Runnable() {
      @Override
      public void run() {
        long elapsedTime = 0;
        long previousTime = 0;

        while (worker.isWorking()) {

          long currentTime = System.currentTimeMillis();

          elapsedTime += currentTime - previousTime;

          if (elapsedTime >= delay) {
            elapsedTime = 0;
            resourceCount[0]++;
          }

          previousTime = currentTime;
        }

        switch (worker.getJob()) {
          case "miner":
            miners.remove(worker);
            break;
          case "lumberJack":
            lumberJacks.remove(worker);
            break;
          default:
            break;
        }

      }
    });
    workerThread.setDaemon(true);
    workerThread.start();
  }

    /**
     * This function is called when the scene game.fxml is loaded.
     *
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        startUiLoop();
  /**
   * Function for building new houses to increase population. This is called by JavaFX.
   */
  @FXML
  public void buildHouse() {
    final int houseCostOre = 100;
    final int houseCostWood = 100;

    if (oreCount[0] >= houseCostOre && woodCount[0] >= houseCostWood) {
      houseCount += 1;
      oreCount[0] -= houseCostOre;
      woodCount[0] -= houseCostWood;
    } else {
    }
  }

  /**
   * This function is called when the scene game.fxml is loaded.
   *
   * @param location  The location used to resolve relative paths for the root object, or
   *                  {@code null} if the location is not known.
   * @param resources The resources used to localize the root object, or {@code null} if the root
   *                  object was not localized.
   */
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {

    startUiLoop();
  }

  /**
   * Function for starting the UI-Loop. It keeps all text fields updated.
   */
  public void startUiLoop() {
    final Thread uiLoop = new Thread(new Runnable() {
      @Override
      public void run() {
        long elapsedTime = 0;
        long previousTime = 0;
        final long updateTime = 200;
        final long sleepTime = 50;

        while (true) {
          long currentTime = System.currentTimeMillis();
          elapsedTime += currentTime - previousTime;

          if (elapsedTime >= updateTime) {
            Platform.runLater(() -> {
              population = INIT_POPULATION + houseCount;

              populationTextField.setText(Integer.toString(population));
              freeWorkerTextField.setText(Integer.toString(getFreeWorkers()));
              lumberJackCountTextField.setText(Integer.toString(lumberJacks.size()));
              minerCountTextField.setText(Integer.toString(miners.size()));
              oreCountTextField.setText(Integer.toString(oreCount[0]));
              woodCountTextField.setText(Integer.toString(woodCount[0]));
              houseCountTextField.setText(Integer.toString(houseCount));
            });
            elapsedTime = 0;
          }

          try {
            Thread.sleep(sleepTime); // Prevent CPU overuse
          } catch (InterruptedException e) {
            break; // exit thread if interrupted
          }
        }
      }
    });
    uiLoop.setDaemon(true);
    uiLoop.start();
  }

  /**
   * Function to get ore count.
   *
   * @return Current ore count.
   */
  public int getOreCount() {
    return oreCount[0];
  }

  /**
   * Function to get wood count.
   *
   * @return Current wood count.
   */
  public int getWoodCount() {
    return woodCount[0];
  }

  /**
   * Function to get population.
   *
   * @return Current population.
   */
  public int getPopulation() {
    return population;
  }

  /**
   * Function to get house count.
   *
   * @return Current house count.
   */
  public int getHouseCount() {
    return houseCount;
  }

  /**
   * Function to get all miners.
   *
   * @return List of miners.
   */
  public ArrayList<Worker> getMiners() {
    return new ArrayList<Worker>(miners);
  }

  /**
   * Function to get all lumber jacks.
   *
   * @return List of lumber jacks.
   */
  public ArrayList<Worker> getLumberJacks() {
    return new ArrayList<Worker>(lumberJacks);
  }
}


