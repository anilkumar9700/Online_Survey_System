package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Question;
import model.Survey;

public class SurveyRetrievalManager {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/anildb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Anil@123";

    public Survey retrieveSurvey(String title) {
        Survey survey = null;
        String sql = "SELECT * FROM surveys WHERE title = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String retrievedTitle = rs.getString("title");
                    String description = rs.getString("description");
                    survey = new Survey(retrievedTitle, description);
                    List<Question> questions = retrieveQuestionsForSurvey(retrievedTitle);
                    survey.setQuestions(questions);
                } else {
                    System.out.println("No survey found with title: " + title);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return survey;
    }

    public List<Survey> retrieveAllSurveys() {
        List<Survey> surveys = new ArrayList<>();
        String sql = "SELECT * FROM surveys";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                Survey survey = new Survey(title, description);
                List<Question> questions = retrieveQuestionsForSurvey(title);
                survey.setQuestions(questions);
                surveys.add(survey);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return surveys;
    }

    private List<Question> retrieveQuestionsForSurvey(String surveyTitle) {
        List<Question> questions = new ArrayList<>();
        // Implement the logic to retrieve questions for the survey from the database
        // Populate the questions list with the retrieved questions
        return questions;
    }
}