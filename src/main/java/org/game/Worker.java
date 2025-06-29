package org.game;

/**
 * Class to create worker, to keep track of the threads in GameController.java.
 */
public class Worker {
  /**
   * Determines if the thread should keep working.
   */
  private volatile boolean working;
  /**
   * What job the worker has. This is used to determine in which list this worker is.
   */
  private final String job;

  /**
   * Constructor of Worker.
   *
   * @param newJob What job the worker gets.
   */
  public Worker(final String newJob) {
    this.working = false;
    this.job = newJob;
  }

  /**
   * Getter of working.
   *
   * @return Current value of working.
   */
  public boolean isWorking() {
    return working;
  }

  /**
   * Setter of working.
   *
   * @param newWorking New value of working.
   */
  public void setWorking(final boolean newWorking) {
    this.working = newWorking;
  }

  /**
   * Getter of job.
   *
   * @return Current value of job.
   */
  public String getJob() {
    return job;
  }

  @Override
  public String toString() {
    return "Worker: [working: " + working + ", job: " + job + "]";
  }
}
