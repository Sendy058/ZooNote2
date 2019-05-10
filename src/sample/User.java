package sample;

import javafx.beans.property.SimpleStringProperty;

public class User {

    private SimpleStringProperty username = null;
    private SimpleStringProperty password = null ;
    private SimpleStringProperty type = null;
    private SimpleStringProperty name = null;
    private SimpleStringProperty surname = null;
    private SimpleStringProperty email = null;

    public User(String name, String surname, String username, String email, String type){
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.username = new SimpleStringProperty(username);
        this.email = new SimpleStringProperty(email);
        this.type = new SimpleStringProperty(type);
    }
    public User(){

    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username = new SimpleStringProperty(username);
    }

    public String getPassword() {
        return password.get();
    }


    public void setPassword(String password) {
        this.password= new SimpleStringProperty(password);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type = new SimpleStringProperty(type);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String surname) {
        this.surname = new SimpleStringProperty(surname);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }
}
