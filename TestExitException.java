package com.coding.task.quantcast.exception;

public class TestExitException extends RuntimeException {

  /**
   * An exception that stores the exit code for later verification.
   */

  private final int status;

  public TestExitException(int status) {
    this.status = status;
  }

  public int getStatus() {
    return status;
  }

}
