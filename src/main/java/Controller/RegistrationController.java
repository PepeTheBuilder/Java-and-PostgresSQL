package Controller;

import Database.Database;
import org.jetbrains.annotations.NotNull;
import View.LoginView;
import View.RegistrationView;
import Model.RegistrationModel;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationController {
    private RegistrationView registrationView;
    private RegistrationModel registerModel= new RegistrationModel();
    private Database db;
    private LoginController loginController;
    private LoginView loginView;

    public RegistrationController(RegistrationView registrationView) {
        this.registrationView = registrationView;
        this.db = new Database();
        db.connect();
        this.registrationView.addRegisterListener(new RegisterListener());
        this.registrationView.addLoginListener(new addLoginListener());
    }
    public void showMessage (String message) {
        JOptionPane.showMessageDialog(this.registrationView, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
    class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = registrationView.getUsername();
            String password = registrationView.getPassword();
            String email = registrationView.getEmail();
            if(registerModel.isValidUsername(username)) {
                if(db.checkUsername(username)) {
                    if (registerModel.isValidPassword(password)) {
                        if (registerModel.isValidEmail(email)) {
                            boolean register = db.registerDB(username, password, email);
                            if(register)
                            {
                                registrationView.setMessage("User registered as: " + username);
                                registrationView.setVisible(false);
                                db.close();
                                loginView = new LoginView();
                                loginController = new LoginController(loginView);
                                loginView.setVisible(true);
                            }
                            //TODO: now make the page for when you are logged in as the new user
                            // OR to take you back to the login page
                        } else registrationView.setMessage("Error: Email is not valid");
                    } else
                        registrationView.setMessage("Error: Password is not valid it need at least 4 characters long");
                }
                else registrationView.setMessage("Error: Username is already taken");
            }
            else registrationView.setMessage("Error: Username is not valid it need at least 4 characters long");
        }
    }
    class addLoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //registrationView.setMessage("Login from register ok!");
            //showMessage("Login from register ok!");
            registrationView.setVisible(false);
            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView);
            loginView.setVisible(true);
            db.close();
        }
    }


}


