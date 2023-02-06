package view;

import Model.AdminModel;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AdminView extends JFrame{
    private JButton addNewMangaBtn;
    private JTextField titleField;
    private JButton addNewManhwaBtn;
    private JButton removeTitleBtn;
    private JButton removeUserBtn;
    private JLabel space;
    private JLabel usernameLabel;
    private JLabel space2;
    private JLabel space3;
    private JLabel space4;
    private JPanel baseline;
    private JButton logoutBtn;

    private AdminModel model;

    public AdminView(AdminModel adminModel){
        this.model =adminModel;
        setSize(350, 210);
        setVisible(true);
        add(baseline);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUsernameLabel();
    }

    public void addNewMangaListener(ActionListener e) {
        addNewMangaBtn.addActionListener(e);
    }

    public void addNewManhwaListener(ActionListener e) {
        addNewManhwaBtn.addActionListener(e);
    }
    public void addRemoveTitleBtnListener(ActionListener e) {
        removeTitleBtn.addActionListener(e);
    }

    public void addRemoveUserListener(ActionListener e) {
        removeUserBtn.addActionListener(e);
    }

    public String getField() {
        return titleField.getText();
    }
    public void setUsernameLabel(){
        if(model.getUserId()!=0){
            String name = model.getUsername();
            usernameLabel.setText(name);
        }
    }
    public void addLogoutListener(ActionListener listener) {
        logoutBtn.addActionListener(listener);
    }

}
