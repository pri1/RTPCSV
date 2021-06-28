package com.coding.task.quantcast;

import com.coding.task.quantcast.exception.TestExitException;
import java.security.Permission;

/**
 * A special implementation of {@link SecurityManager} that throws a {@link RuntimeException} when
 * {@link System#exit(int)} is called. This allows us to test the exit codes generated by our little application in a
 * test.
 */
public class TestingSecurityManager extends SecurityManager {
    @Override
    public void checkPermission(Permission perm) {
        // Allow everything.
    }

    @Override
    public void checkPermission(Permission perm, Object context) {
        // Allow everything.
    }

    @Override
    public void checkExit(int status) {
        super.checkExit(status);
        // By throwing a RuntimeException, we can catch it in our tests and verify the exit code.
        throw new TestExitException(status);
    }

}
