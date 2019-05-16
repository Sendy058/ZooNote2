package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Entities.Repair;

import static org.junit.jupiter.api.Assertions.*;

class RepairTest {

    private Repair repair;
    private String expected;

    @BeforeEach
    void setUp() {
        repair = new Repair("WC", "oprava WC", "prebieha", 600.0);
    }

    @Test
    void getNazov() {
        expected = "WC";
        assertEquals(expected, repair.getNazov());
    }

    @Test
    void getPopis() {
        expected = "oprava WC";
        assertEquals(expected, repair.getPopis());
    }

    @Test
    void getStav() {
        expected = "prebieha";
        assertEquals(expected, repair.getStav());
    }

    @Test
    void getCena() {
        double expected = 600.0;
        assertEquals(expected, repair.getCena());
    }
}