package view;

import Database.Database;

import javax.swing.*;
import java.awt.event.ActionListener;

public class UpdateView extends JFrame {
    private JLabel username;
    private JComboBox titleCombo;
    private JButton updateBtn;
    private JTextField currentChapter;
    private JRadioButton readingRadioButton;
    private JRadioButton planToReadRadioButton;
    private JRadioButton droppedRadioButton;
    private JRadioButton completedRadioButton;
    private JButton logoutBtn;
    private JButton backToMeniuBtn;
    private JLabel ChooseTxt;
    private JLabel CurrentChTxt;
    private JLabel LetsKeepTxt;
    private JPanel basePanel;
    private JRadioButton mangaRadioButton;
    private JRadioButton manhwaRadioButton;
    private Database db;
    private int idUser=1;

    public UpdateView(int idUser){
        this.idUser=idUser;
        db= new Database();
        db.connect();
        add(basePanel);
        setUsernameLabel(idUser);
        setSize(950, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Center the window on the screen
        setLocationRelativeTo(null);
        db.comboBox(titleCombo);
        setVisible(true);
    }

    public String getCombo(){
        return this.getTitleCombo().getItemAt(this.getTitleCombo().getSelectedIndex()).toString();
    }

    public int getChapter(){
         String result=currentChapter.getText();
         Integer val=-1;
         try {
                  val = Integer.parseInt(result);
             }
         catch (NumberFormatException e) {
                 System.out.println("Invalid String");
                 return -1;
             }
          return val;
    }

    public int ReadRadioButton(){
        if(readingRadioButton.isSelected())
            return 1;
        if(planToReadRadioButton.isSelected())
            return 2;
        if(droppedRadioButton.isSelected())
            return 3;
        if(completedRadioButton.isSelected())
            return 4;
        return 0;
    }

    public int MangaRadioButton(){
        if(mangaRadioButton.isSelected())
            return 1;
        if(manhwaRadioButton.isSelected())
            return 2;
        return 0;
    }
    public void addUpdateListener(ActionListener listener ) {
        //System.out.println("ceva");
        updateBtn.addActionListener(listener);
    }
    public void addLogoutListener(ActionListener listener ) {
        logoutBtn.addActionListener(listener);
    }
    public void addBackToMeniuListener(ActionListener listener ) {
       backToMeniuBtn.addActionListener(listener);
    }
    public void setUsernameLabel(int id){
        if(id!=0){
            String name = db.getName(id);
            username.setText(name);
        }
    }
    public JComboBox getTitleCombo() {
        return titleCombo;
    }


}
