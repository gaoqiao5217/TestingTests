import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PasswordCheckerTest {
    private PasswordChecker passwordChecker;

    @BeforeEach
    public void setUp() {
        passwordChecker = new PasswordChecker();
    }

    @Test
    public void testSetMinLengthValid() {
        passwordChecker.setMinLength(6);
        assertEquals(6, passwordChecker.getMinLength());
    }

    @Test
    public void testSetMinLengthInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            passwordChecker.setMinLength(-1);
        });
    }

    @Test
    public void testSetMaxRepeatsValid() {
        passwordChecker.setMaxRepeats(3);
        assertEquals(3, passwordChecker.getMaxRepeats());
    }

    @Test
    public void testSetMaxRepeatsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            passwordChecker.setMaxRepeats(0);
        });
    }

    @Test
    public void testVerifyValidPassword() {
        passwordChecker.setMinLength(6);
        passwordChecker.setMaxRepeats(2);
        assertTrue(passwordChecker.verify("aabbcc"));
    }

    @Test
    public void testVerifyShortPassword() {
        passwordChecker.setMinLength(8);
        passwordChecker.setMaxRepeats(2);
        assertFalse(passwordChecker.verify("aabbcc"));
    }

    @Test
    public void testVerifyTooManyRepeats() {
        passwordChecker.setMinLength(6);
        passwordChecker.setMaxRepeats(1);
        assertFalse(passwordChecker.verify("aabbcc"));
    }

    @Test
    public void testVerifyExactRepeats() {
        passwordChecker.setMinLength(6);
        passwordChecker.setMaxRepeats(2);
        assertTrue(passwordChecker.verify("aabbcc"));
    }

    @Test
    public void testVerifyWithoutRepeats() {
        passwordChecker.setMinLength(6);
        passwordChecker.setMaxRepeats(2);
        assertEquals(true, passwordChecker.verify("abcdef"));
    }

}