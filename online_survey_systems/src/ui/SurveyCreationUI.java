package ui;

import java.awt.Component;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SurveyCreationUI extends JFrame {
	  private List<String> questions;

	    public SurveyCreationUI(List<String> questions) {
	        this.questions = questions;
	        initializeUI();
	    }

	    private void initializeUI() {
	        setTitle("Survey Creation");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(600, 400);
	        setLocationRelativeTo(null);

	        JPanel panel = new JPanel(new GridLayout(0, 1));

	        JLabel titleLabel = new JLabel("Please enter your survey questions:");
	        panel.add(titleLabel);

	        for (String question : questions) {
	            JLabel questionLabel = new JLabel(question);
	            JTextField questionField = new JTextField(20);
	            panel.add(questionLabel);
	            panel.add(questionField);
	        }

	        JButton submitButton = new JButton("Submit Survey");
	        submitButton.addActionListener(e -> submitSurvey());
	        panel.add(submitButton);

	        add(panel);
	        setVisible(true);
	    }

	    private void submitSurvey() {
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey_db", "username", "password")) {
	            String sql = "INSERT INTO survey_responses (question, answer) VALUES (?, ?)";
	            PreparedStatement statement = connection.prepareStatement(sql);

	            for (int i = 0; i < questions.size(); i++) {
	                String question = questions.get(i);
	                Component component = getContentPane().getComponent(i * 2 + 1);
	                String answer = ((JTextField) component).getText();

	                statement.setString(1, question);
	                statement.setString(2, answer);

	                statement.executeUpdate();
	            }

	            statement.close();

	            JOptionPane.showMessageDialog(this, "Survey responses submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
	            dispose();

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Failed to submit survey responses. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            List<String> questions = new ArrayList<>();
	            questions.add("What is your age?");
	            questions.add("What is your gender?");
	            questions.add("What is your favorite color?");
	            new SurveyCreationUI(questions);
	        });
	    }
	}