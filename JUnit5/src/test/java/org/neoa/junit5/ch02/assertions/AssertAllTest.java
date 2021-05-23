package org.neoa.junit5.ch02.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertAllTest {

    @Test
    @DisplayName("SUT should default to not being under current verification")
    void testSystemNotVerified() {
        SUT systemUnderTest = new SUT("Our system under test");

        assertAll("By default, SUT is not under current verification",
                () -> assertEquals("Our system under test", systemUnderTest.getSystemName()),
                () -> assertFalse(systemUnderTest.isVerified())
        );

    }

    @Test
    @DisplayName("SUT should be under current verification")
    void testSystemUnderVerification() {
        SUT systemUnderTest = new SUT("Our system under test");
        systemUnderTest.verify();
        assertAll("SUT under current verification",
                () -> assertEquals("Our system under test", systemUnderTest.getSystemName()),
                () -> assertTrue(systemUnderTest.isVerified())
        );
    }

    @Test
    @DisplayName("SUT should be under current verification")
    void systemUnderVerificationResponsesTrueWhenItHasBeenVerified() {
        SUT systemUnderTest = new SUT("Our system under test");
        systemUnderTest.verify();
        assertTrue(systemUnderTest.isVerified(), () -> "System should be under verification");
    }

    @Test
    @DisplayName("SUT should not be under current verification")
    void systemUnderVerificationResponsesFalseWhenItHasNotBeenVerified() {
        SUT systemUnderTest = new SUT("Our system under test");
        assertFalse(systemUnderTest.isVerified(), () -> "System should not be under verification");
    }

    @Test
    @DisplayName("SUT should have not current Job")
    void systemUnderVerificationShouldHaveNotACurrentJob() {
        SUT systemUnderTest = new SUT("Our system under test");
        assertNull(systemUnderTest.getCurrentJob(), () -> "There should be no current job");
    }
}
