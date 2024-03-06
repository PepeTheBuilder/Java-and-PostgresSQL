package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegistrationView extends JFrame {
    private JLabel labelUsername = new JLabel("Username: ");
    private JTextField textUsername = new JTextField();
    private JLabel labelPassword = new JLabel("Password: ");
    private JPasswordField fieldPassword = new JPasswordField();
    private JLabel labelEmail = new JLabel("Email: ");
    private JTextField textEmail = new JTextField();
    private JButton buttonRegister = new JButton("Register");
    private JLabel labelMessage = new JLabel();

    private JButton loginBtn = new JButton("Back to login page");

    public RegistrationView() {
        // Set up the UI layout
        setVisible(true);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
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
        add(labelEmail, constraints);

        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(textEmail, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(buttonRegister, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(labelMessage, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(loginBtn, constraints);


        setTitle("Registration");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public String getUsername() {
        return textUsername.getText();
    }

    public String getPassword() {
        return new String(fieldPassword.getPassword());
    }

    public String getEmail() {
        return textEmail.getText();
    }

    public void addRegisterListener(ActionListener listener) {
        buttonRegister.addActionListener(listener);
    }

    public void setMessage(String message) {
        labelMessage.setText(message);
    }

    public void addLoginListener(ActionListener e) {
            loginBtn.addActionListener(e);
        }
}

