package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Entities.Animal;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    private Animal animal;
    private String expected;

    @BeforeEach
    void setUp() {
        animal = new Animal("Kikino", "3.5.2015", "zije", "zlaty","trieda", "rad", "celad", "druh" );
    }

    @Test
    void setMeno() {
        expected = "Misko";
        animal.setMeno(expected);
        assertEquals(expected, animal.getMeno());
    }

    @Test
    void getDatum_narodenia() {
        expected = "3.5.2015";
        assertEquals(expected, animal.getDatum_narodenia());
    }

    @Test
    void getStav() {
        expected = "zije";
        assertEquals(expected, animal.getStav());
    }

    @Test
    void getZdravotna_karta() {
        expected = "zlaty";
        assertEquals(expected, animal.getZdravotna_karta());
    }

    @Test
    void getTrieda() {
        expected = "trieda";
        assertEquals(expected, animal.getTrieda());
    }

    @Test
    void getRad() {
        expected = "rad";
        assertEquals(expected, animal.getRad());
    }

    @Test
    void getCelad() {
        expected = "celad";
        assertEquals(expected, animal.getCelad());
    }

    @Test
    void getDruh() {
        expected = "druh";
        assertEquals(expected, animal.getDruh());
    }
}