package org.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AppTest {
  /**
   * gameController object to test gameController methods.
   */
  @Mock
  private final GameController gameController = new GameController();

  /**
   * Test starting and stopping of miner thread
   */
  @Test
  void startStopMiner() {
    // start miner thread
    gameController.addMiner();

    // wait for miner doing its job
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    // stop miner thread
    gameController.removeMiner();

    // wait for miner to stop
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }



    // if it's not equal to 0, the thread has been started
    assertNotEquals(0, gameController.getOreCount());

    // if it's equal to 0, the thread has been stopped
    assertEquals(0, gameController.getMiners().size());
  }

  /**
   * Test starting and stopping of lumber jack thread
   */
  @Test
  void startStopLumberJack() {
    // start lumber jack thread
    gameController.addLumberJack();

    // wait for lumber jack doing its job
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    // stop lumber jack thread
    gameController.removeLumberJack();

    // wait for lumber jack to stop
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    // if it's not equal to 0, the thread has been started
    assertNotEquals(0, gameController.getWoodCount());

    // if it's equal to 0, the thread has been stopped
    assertEquals(0, gameController.getLumberJacks().size());
  }

  /**
   * Testing building houses.
   */
  @Test
  void testBuildHouse() {
    gameController.cheatOre(100);
    gameController.cheatWood(100);

    int oreBeforeHouse = gameController.getOreCount();
    int woodBeforeHouse = gameController.getWoodCount();

    gameController.buildHouse();

    assertEquals(1, gameController.getHouseCount());
    assertEquals(oreBeforeHouse - 100, gameController.getOreCount());
    assertEquals(woodBeforeHouse - 100, gameController.getWoodCount());


  }

}
