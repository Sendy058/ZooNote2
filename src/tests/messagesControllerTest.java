package tests;

import controllers.messagesController;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class messagesControllerTest {

    private messagesController controller;

    @Test
    void zisti() throws SQLException {
        controller = new messagesController();
        assertEquals("Admin", controller.zisti(0));
    }
}