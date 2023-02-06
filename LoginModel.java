package Model;

import Database.Database;

import java.sql.Connection;
import java.sql.Statement;

public class LoginModel {

    private int id;

    private boolean admin= false;

    private Database db;

    public String username;
    public String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login_mail) {
        this.username = login_mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String login_password) {
        this.password = login_password;
    }
}
