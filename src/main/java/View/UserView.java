package View;

import Database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserView extends JFrame{
    private JButton logoutBtn;
    private JButton showAllMangaBtn;
    private JButton showAllManhwaBtn;
    private JButton myManhwaBtn;
    private JButton myMangaBtn;
    private JButton showEverythingBtn;
    private JLabel textLabel1;
    private JButton goToUpdatesBtn;
    private JLabel space;
    private JLabel username;

    private JPanel basePanel;
    private Label labelMessage;
    Database db;
    private  int idUser=0;

    public UserView(int idUser){
        this.idUser=idUser;
        db= new Database();
        db.connect();
        System.out.println(idUser);
        System.out.println(db.getName(idUser));
        setUsernameLabel();

        setSize(450,320);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(basePanel);
        setVisible(true);

    }
    public void setUsernameLabel(){
        if(idUser!=0){
            String name = db.getName(idUser);
                username.setText(name);
        }
    }
    public void setMessage(String message) {
        labelMessage.setText(message);
    }
    public void showMessage (String message) {
        JOptionPane.showMessageDialog( this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
    public void addLogoutListener(ActionListener listener) {
        logoutBtn.addActionListener(listener);
    }
    public void addShowEverythingListener(ActionListener listener ) {
        showEverythingBtn.addActionListener(listener);
    }
    public void addAllMangaListener(ActionListener listener ) {
        showAllMangaBtn.addActionListener(listener);
    }
    public void addAllManhwaListener(ActionListener listener ) {
        showAllManhwaBtn.addActionListener(listener);
    }
    public void addUserMangaListener(ActionListener listener ) {
        myMangaBtn.addActionListener(listener);
    }
    public void addUserManhwaListener(ActionListener listener ) {
        myManhwaBtn.addActionListener(listener);
    }
    public void addGoToUpdatesListener(ActionListener listener ) {
        goToUpdatesBtn.addActionListener(listener);
    }

}
