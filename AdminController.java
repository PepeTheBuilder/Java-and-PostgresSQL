package controller;

import Database.Database;
import Model.AdminModel;
import view.AdminView;
import view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController {

    private AdminView view;
    private AdminModel model;
    private Database db;
    private boolean troll=false;

    public AdminController(AdminView adminView ,AdminModel adminModel){
        this.view=adminView;
        this.model=adminModel;
        this.db = new Database();
        model.setUsername(db.getName(model.getAdminId()));
        this.view.addLogoutListener(new LogoutListener());
        this.view.addNewMangaListener(new addNewMangaListener());
        this.view.addNewManhwaListener(new addNewManhwaListener());
        this.view.addRemoveUserListener(new addRemoveUserListener());
        this.view.addRemoveTitleBtnListener(new addRemoveTitleBtnListener());
    }
    class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           if(troll) showMessage("Button pressed successfully");
            LoginView loginView = new LoginView();
            LoginController loginController= new LoginController(loginView);
            view.setVisible(false);
            loginView.setVisible(true);
            db.close();
        }
    }
    public void showMessage (String message) {
        JOptionPane.showMessageDialog( this.view, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    class addRemoveUserListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(troll)showMessage("Button pressed successfully");
            String user= view.getField();
            if(!db.checkUsername(user)){
            db.deleteUser(db.getUserId(user));
            showMessage("User removed");
            }
            else showMessage("Username doesn't exist!");
        }
    }
    class addRemoveTitleBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(troll)showMessage("Button pressed successfully");
            String title= view.getField();
            if(!title.isBlank() || !title.isEmpty()) {
                String table = db.whatTableBook(title);
                if (table.equals("bookmark_manga")) {
                    db.deleteTitle("manga", title, table);
                    showMessage("Title deleted from manga!");
                } else if (table.equals("bookmark_manhwa")) {
                    db.deleteTitle("manhwa", title, table);
                    showMessage("Title deleted from manhwa!");
                } else
                    showMessage("Title removed");
            }else showMessage("invalid input text");
        }
    }
    class addNewManhwaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(troll)showMessage("Button pressed successfully");
            String title = view.getField();
            if(!title.isBlank() || !title.isEmpty()) {
                db.insert("Manhwa", title);
                showMessage("Manhwa added successfully");
            }
            else showMessage("Title is empty");
        }
    }

    class addNewMangaListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(troll)showMessage("Button pressed successfully");
            String title = view.getField();
            if(!title.isBlank() || !title.isEmpty()) {
                db.insert("Manga", title);
                showMessage("Manga added successfully");
            }
            showMessage("Title is empty");
        }
    }

}
