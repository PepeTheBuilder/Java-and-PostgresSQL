package controller;

import Database.Database;
import view.LoginView;

public class Control {
    Database db = new Database();
    boolean connection = db.connect();
    Integer idCurrent=0;
    public void controller() {
        if (connection) {
            System.out.println("The database is connected ");
            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView);
            loginView.setVisible(true);
            db.close();
        } else {
            System.out.println("The database is not connected");
        }
    }
}
