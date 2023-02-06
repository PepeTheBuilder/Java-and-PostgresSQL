package controller;

import Database.Database;
import Model.AdminModel;
import view.AdminView;
import view.LoginView;
import view.RegistrationView;
import view.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView loginView;
    private Database db;


    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.db = new Database();
        db.connect();
        this.loginView.addLoginListener(new LoginListener());
        this.loginView.addRegisterListener(new RegisterListener());
    }
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            int userId = db.loginDB(username, password);
            if (userId > 0) {
                if(db.isAdmin(userId)==true){
                    loginView.setVisible(false);
                    AdminModel adminModel = new AdminModel(userId);
                    AdminView adminView = new AdminView(adminModel);
                    AdminController adminController = new AdminController(adminView, adminModel);
                    adminView.setVisible(true);

                }
                else {
                     loginView.setVisible(false);
                    UserView view=new UserView(userId);
                    UserController updateController= new UserController(view, userId);
                    view.setVisible(true);
                }

            } else {
                loginView.setMessage("Invalid username or password");
            }
        }
    }

    class RegisterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //new UpdateController(new UpdateView(1), new UpdateModel(), 1);
            new RegistrationController(new RegistrationView());
            loginView.setVisible(false);
            db.close();
        }
    }

}
