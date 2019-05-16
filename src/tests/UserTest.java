package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Entities.User;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private String expected;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void getUsername() {
        expected = "vivi";
        user.setUsername(expected);
        assertEquals(expected, user.getUsername());
    }

    @Test
    void getType() {
        expected = "osetrovatel";
        user.setType(expected);
        assertEquals(expected, user.getType());
    }

    @Test
    void getName() {
        expected = "Viktoria";
        user.setName(expected);
        assertEquals(expected, user.getName());
    }

    @Test
    void getSurname() {
        expected = "Ondrejova";
        user.setSurname(expected);
        assertEquals(expected, user.getSurname());
    }

    @Test
    void getEmail() {
        expected = "vivi@sEnDNuDeSS.org";
        user.setEmail(expected);
        assertEquals(expected, user.getEmail());
    }
}