package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.Encryption;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionTest {
    private Encryption encryption;

    @BeforeEach
    void setUp() {
        encryption = new Encryption();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void MD5() {
        String expected = "0192023a7bbd73250516f069df18b500";
        assertEquals(expected,encryption.MD5("admin123") );
    }
}