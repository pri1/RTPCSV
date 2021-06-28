package com.coding.task.quantcast;

import com.coding.task.quantcast.exception.TestExitException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Unit test for Application.
 * <p/>
 * A unit test aims to test all code and code paths of a specific class.
 */
@ExtendWith(MockitoExtension.class)
public class ApplicationTest {

  static SecurityManager originalSecurityManager;

  @BeforeAll
  public static void setup() {
    originalSecurityManager = System.getSecurityManager();
    System.setSecurityManager(new TestingSecurityManager());
  }

  @AfterAll
  public static void tearDown() {
    System.setSecurityManager(originalSecurityManager);
  }

  @Test
  public void testMain() throws Exception {
    String[] args = {"-f", "C:\\Users\\Win10\\Desktop\\prince\\coding-task-quantcast\\sample.csv",
        "-d", "2018-12-09"};
    Application.main(args);
  }

  @Test
  public void testMain1() throws Exception {
    String[] args = {"C:\\Users\\Win10\\Desktop\\prince\\coding-task-quantcast\\sample.csv",
        "-d", "2018-12-09"};
    Application.main(args);
    try {
      Application.main(args);
      // Our custom SecurityManager should have thrown an exception when HelloApp exited.
      // This means this line below cannot be reached. To make sure that our custom SecurityManager
      // works as expected, we fail the test if this line is ever reached:
      fail("Unreachable.");
    } catch (TestExitException e) {
      // Did the program exit with the expected error code?
      assertThat(e.getStatus(), is(HelloApp.EXIT_STATUS_PARAMETER_NOT_UNDERSTOOD));
    }
  }

  @Test
  public void testBogusArgument1() throws Exception {
    String[] args = {"-f","-f",
        "-d", null};

    try {
      Application.main(args);
      // Our custom SecurityManager should have thrown an exception when HelloApp exited.
      // This means this line below cannot be reached. To make sure that our custom SecurityManager
      // works as expected, we fail the test if this line is ever reached:
      fail("Unreachable.");
    } catch (TestExitException e) {
      // Did the program exit with the expected error code?
      assertThat(e.getStatus(), is(HelloApp.EXIT_STATUS_PARAMETER_NOT_UNDERSTOOD));
    }
  }

  @Test
  public void testBogusArgument2() throws Exception {
    String[] args = {"-f", "C:\\Users\\Win10\\Desktop\\prince\\coding-task-quantcast\\sample.csv",
        "-d", "2018-12-09", "90i9i90"};

    try {
      Application.main(args);
      // Our custom SecurityManager should have thrown an exception when HelloApp exited.
      // This means this line below cannot be reached. To make sure that our custom SecurityManager
      // works as expected, we fail the test if this line is ever reached:
      fail("Unreachable.");
    } catch (TestExitException e) {
      // Did the program exit with the expected error code?
      assertThat(e.getStatus(), is(HelloApp.EXIT_STATUS_PARAMETER_NOT_UNDERSTOOD));
    }
  }

  @Test
  public void testBogusArgument3() throws Exception {
    String[] args = {"-f", "C:\\Users\\Win10\\Desktop\\prince\\coding-task-quantcast\\sample1.csv",
        "-d", "2018-12-09"};

    try {
      Application.main(args);
      // Our custom SecurityManager should have thrown an exception when HelloApp exited.
      // This means this line below cannot be reached. To make sure that our custom SecurityManager
      // works as expected, we fail the test if this line is ever reached:
      fail("Unreachable.");
    } catch (TestExitException e) {
      // Did the program exit with the expected error code?
      assertThat(e.getStatus(), is(HelloApp.EXIT_STATUS_PARAMETER_NOT_UNDERSTOOD));
    }
  }

  @Test
  public void testBogusArgument4() throws Exception {
    String[] args = {"-f", "C:\\Users\\Win10\\Desktop\\prince\\coding-task-quantcast\\sample1.csv",
        "-d", "2018-12-09"};

    try {
      Application.main(args);
      // Our custom SecurityManager should have thrown an exception when HelloApp exited.
      // This means this line below cannot be reached. To make sure that our custom SecurityManager
      // works as expected, we fail the test if this line is ever reached:
      fail("Unreachable.");
    } catch (TestExitException e) {
      // Did the program exit with the expected error code?
      assertThat(e.getStatus(), is(HelloApp.EXIT_STATUS_PARAMETER_NOT_UNDERSTOOD));
    }
  }


  @Test
  public void testBogusArgument() {
    String[] args = {"bicycle"};

    try {
      HelloApp.main(args);
      // Our custom SecurityManager should have thrown an exception when HelloApp exited.
      // This means this line below cannot be reached. To make sure that our custom SecurityManager
      // works as expected, we fail the test if this line is ever reached:
      fail("Unreachable.");
    } catch (TestExitException e) {
      // Did the program exit with the expected error code?
      assertThat(e.getStatus(), is(HelloApp.EXIT_STATUS_PARAMETER_NOT_UNDERSTOOD));
    }
  }

  @Test
  public void testTooHighArgument() {
    String[] args = {"999"};

    try {
      HelloApp.main(args);
      fail("Unreachable.");
    } catch (TestExitException e) {
      // Did the program exit with the expected error code?
      assertThat(e.getStatus(), is(HelloApp.EXIT_STATUS_HELLO_FAILED));
    }
  }

  @Test
  public void testDefaultArgument() {
    // Passing no arguments should work.
    String[] args = {};
    HelloApp.main(args);
  }

  @Test
  public void classInstanceForCodeCoverageTest() {
    // Strictly speaking this test doesn't achieve anything, because HelloApp contains only a single static
    // method, but for purposes of full code coverage it is included. In general,
    // it is easier to aim for full code coverage and be done with it, than to remember why class X is stuck at
    // 95% code coverage.
    new Application();
  }
}
