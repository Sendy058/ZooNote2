package tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Entities.BankAccount;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        bankAccount = new BankAccount();
    }

    @Test
    void getId() {
        int expected = 5;
        bankAccount.setId(expected);
        assertEquals(expected, bankAccount.getId());
    }

    @Test
    void getStav() {
        double expected = 5.238;
        bankAccount.setStav(expected);
        assertEquals(expected, bankAccount.getStav());
    }
}