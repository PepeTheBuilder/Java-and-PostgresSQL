package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginView extends JFrame {
    private JLabel labelUsername = new JLabel("Username: ");
    private JTextField textUsername = new JTextField();
    private JLabel labelPassword = new JLabel("Password: ");
    private JPasswordField fieldPassword = new JPasswordField();
    private JButton buttonLogin = new JButton("Login");
    private JLabel labelMessage = new JLabel();
    private JButton buttonRegister = new JButton("Register");

    public LoginView() {
        // Set up the UI layout


        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        setBounds(300,200,400,300);
        setSize(300, 300);
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(labelUsername, constraints);

        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(textUsername, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(labelPassword, constraints);

        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(fieldPassword, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(buttonLogin, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(labelMessage, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(buttonRegister, constraints);
        // Set the default close operation and size of the window
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Center the window on the screen
        setLocationRelativeTo(null);
    }

    public String getUsername() {
        return textUsername.getText();
    }

    public String getPassword() {
        return new String(fieldPassword.getPassword());
    }

    public void setMessage(String message) {
        labelMessage.setText(message);
    }

    public void addLoginListener(ActionListener listener) {
        buttonLogin.addActionListener(listener);
    }

    public void addRegisterListener(ActionListener listener ) {
        buttonRegister.addActionListener(listener);
    }

}
