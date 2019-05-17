package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.RequestUsersData;
import Entities.User;

import static org.junit.jupiter.api.Assertions.*;

class RequestUsersDataTest {

    private RequestUsersData request;
    private String login, password;

    @BeforeEach
    void setUp() {
        request = new RequestUsersData();
    }

    @Test
    void getUsersData() {
        User user = new User();
        user.setName("Viktoria");
        login = "ondrejova";
        password = "4d891397df3a8bd64c7d8f8675ea3787";
        assertEquals(user.getName(),request.getUsersData(login, password).getName());
    }
}