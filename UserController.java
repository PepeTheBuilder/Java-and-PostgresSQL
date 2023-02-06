package controller;

import Database.Database;
import view.LoginView;
import view.UpdateView;
import view.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserController {

    private UserView userView;
    //private UserModel userModel;
    private Database db;
    private int idUser;

    public static void main(String[] args) {
        Database db = new Database();
        db.connect();
        UserView userView=new UserView(1);
        UserController userController= new UserController(userView,1);
        userView.setVisible(true);

    }

    public UserController(UserView view, int id){
        idUser=id;
        userView=view;
        db = new Database();
        db.connect();

        this.userView.addLogoutListener(new LogoutListener());
        this.userView.addUserMangaListener(new UserMangaListener());
        this.userView.addUserManhwaListener(new UserManhwaListener());
        this.userView.addGoToUpdatesListener(new GoToUpdatesListener());
        this.userView.addAllManhwaListener(new AllManhwaListener());
        this.userView.addAllMangaListener(new AllMangaListener());
        this.userView.addShowEverythingListener(new ShowEverythingListener());

    }
    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
             LoginView loginView = new LoginView();
             LoginController loginController= new LoginController(loginView);
            userView.setVisible(false);
            loginView.setVisible(true);
            db.close();
        }
    }

    public class ShowEverythingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: STUFF
            //userView.showMessage("ShowEverything button was pressed");
            db.selectAll();
        }
    }

    public class AllMangaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: STUFF
            db.selectManga();
        }
    }

    public class AllManhwaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: STUFF
            db.selectManhwa();
        }
    }

    public class UserMangaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: STUFF
            //userView.showMessage("User manga button was pressed");
            db.myManga(idUser);
        }
    }

    public class UserManhwaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: STUFF
            //userView.showMessage("User manhwa button was pressed");
            db.myManhwa(idUser);
        }
    }

    public class GoToUpdatesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            UpdateView view=new UpdateView(idUser);
            UpdateController updateController= new UpdateController(view, idUser);
            view.setVisible(true);
            userView.setVisible(false);
            db.close();
        }
    }
}
