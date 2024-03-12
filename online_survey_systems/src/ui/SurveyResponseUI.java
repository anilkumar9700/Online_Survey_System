package ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Question;
import model.Response;

public class SurveyResponseUI extends JFrame {
	private List<String> questions;
    private boolean surveyResponseCompleted = false;

    public SurveyResponseUI() {
        initializeUI();
    }

    public SurveyResponseUI(List<String> questions) {
        this.questions = questions;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Survey Response");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 1));

        JLabel titleLabel = new JLabel("Please enter your survey responses:");
        panel.add(titleLabel);

        if (questions != null) {
            for (String question : questions) {
                JLabel questionLabel = new JLabel(question);
                JTextField responseField = new JTextField(20);
                panel.add(questionLabel);
                panel.add(responseField);
            }
        }

        JButton submitButton = new JButton("Submit Responses");
        submitButton.addActionListener(e -> submitResponses());
        panel.add(submitButton);

        add(panel);
        setVisible(true);
    }

    private void submitResponses() {
        // Implementation to submit the survey responses to the database
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<String> questions = List.of("What is your age?", "What is your gender?", "What is your favorite color?");
            new SurveyResponseUI(questions);
        });
    }
}