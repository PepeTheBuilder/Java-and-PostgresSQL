package controller;

import Database.Database;
import org.jetbrains.annotations.NotNull;
import view.LoginView;
import view.UpdateView;
import view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UpdateController {

    private UpdateView updateView;
    private Database db;
    private int idUser;

    public static void main(String[] args) {
         Database db = new Database();
         db.connect();
         UpdateView updateView1=new UpdateView(1);
         UpdateController updateController= new UpdateController(updateView1,1);
         updateView1.setVisible(true);

    }

    public UpdateController(@NotNull UpdateView updateView, int id){
        idUser = id;
        this.updateView = updateView;
        db = new Database();
        db.connect();
        this.updateView.addUpdateListener(new UpdateListener());
        this.updateView.addLogoutListener(new LogoutListener());
        this.updateView.addBackToMeniuListener(new BackToMeniuListener());
        updateView.setVisible(true);
    }


    public void showMessage (String message) {
        JOptionPane.showMessageDialog( this.updateView, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: THIS MF
            String title = updateView.getCombo();
            int chapter = updateView.getChapter();
            int radio = updateView.ReadRadioButton();
            int type = updateView.MangaRadioButton();
            ArrayList<String> status = new ArrayList<String>();

            status.add("On Going");
            status.add("Planning to read");
            status.add("Dropped");
            status.add("Completed");

            if(type ==1){
            db.update(title,chapter, status.get(radio),"manga",idUser);
            showMessage("The database was successfully updated!");
            }
            if(type ==2) {
                db.update(title,chapter, status.get(radio),"manhwa",idUser);
                showMessage("The database was successfully updated!");
            }

        }
    }

    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginView loginView = new LoginView();
            LoginController loginController= new LoginController(loginView);
            updateView.setVisible(false);
            loginView.setVisible(true);
            db.close();
        }
    }
    public class BackToMeniuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: STUFF
            UserView view=new UserView(idUser);
            UserController updateController= new UserController(view, idUser);
            view.setVisible(true);
            updateView.setVisible(false);
            db.close();
        }
    }


}
