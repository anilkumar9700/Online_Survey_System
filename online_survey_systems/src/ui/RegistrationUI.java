package ui;

import javax.swing.*;
import util.UserRegistrationManager;
public class RegistrationUI extends JFrame{
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private boolean registrationCompleted = false;


    public RegistrationUI() {
        setTitle("User Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        panel.add(usernameLabel);
        panel.add(usernameField);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        panel.add(passwordLabel);
        panel.add(passwordField);
        
        registerButton = new JButton("Register");
        registerButton.addActionListener(e -> registerUser());
        panel.add(registerButton);
        
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        UserRegistrationManager registrationManager = new UserRegistrationManager();
        if (registrationManager.registerUser(username, password, this)) { // Pass 'this' as the JFrame argument
            JOptionPane.showMessageDialog(this, "Registration successful!");
            dispose(); // Close the registration window after successful registration
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed. Please try again.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegistrationUI::new);
    }
    public boolean isRegistrationCompleted() {
        return registrationCompleted;
    }

    public void waitForRegistrationCompletion() {
        while (!registrationCompleted) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}