import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PasswordCheckerHamcrest {
    private PasswordChecker passwordChecker;

    @BeforeEach
    public void setUp() {
        passwordChecker = new PasswordChecker();
    }

    @Test
    public void testSetMinLengthValid() {
        passwordChecker.setMinLength(6);
        assertThat(passwordChecker.getMinLength(), is(6));
    }

    @Test
    public void testSetMinLengthInvalid() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> passwordChecker.setMinLength(-1));
        assertThat(thrown.getClass(), is(IllegalArgumentException.class));
    }

    @Test
    public void testSetMaxRepeatsValid() {
        passwordChecker.setMaxRepeats(3);
        assertThat(passwordChecker.getMaxRepeats(), is(3));
    }

    @Test
    public void testSetMaxRepeatsInvalid() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> passwordChecker.setMaxRepeats(0));
        assertThat(thrown.getClass(), is(IllegalArgumentException.class));
    }

    @Test
    public void testVerifyValidPassword() {
        passwordChecker.setMinLength(6);
        passwordChecker.setMaxRepeats(2);
        assertThat(passwordChecker.verify("aabbcc"), is(true));
    }

    @Test
    public void testVerifyShortPassword() {
        passwordChecker.setMinLength(8);
        passwordChecker.setMaxRepeats(2);
        assertThat(passwordChecker.verify("aabbcc"), is(false));
    }

    @Test
    public void testVerifyTooManyRepeats() {
        passwordChecker.setMinLength(6);
        passwordChecker.setMaxRepeats(1);
        assertThat(passwordChecker.verify("aabbcc"), is(false));
    }

    @Test
    public void testVerifyExactRepeats() {
        passwordChecker.setMinLength(6);
        passwordChecker.setMaxRepeats(2);
        assertThat(passwordChecker.verify("aabbcc"), is(true));
    }

    @Test
    public void testVerifyWithoutRepeats() {
        passwordChecker.setMinLength(6);
        passwordChecker.setMaxRepeats(2);
        assertThat(passwordChecker.verify("abcdef"), is(true));
    }
}