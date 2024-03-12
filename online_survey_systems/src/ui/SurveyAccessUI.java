package ui;


import javax.swing.JOptionPane;
import model.Survey;
import model.Question;
import util.SurveyRetrievalManager;

public class SurveyAccessUI {
	public static void main(String[] args) {
        // Assuming the title of the survey you want to access
        String surveyTitle = "Your Survey Title";

        // Create an instance of the SurveyRetrievalManager
        SurveyRetrievalManager surveyRetrievalManager = new SurveyRetrievalManager();

        // Retrieve the survey by its title
        Survey survey = surveyRetrievalManager.retrieveSurvey(surveyTitle);

        // Check if the survey was found
        if (survey != null) {
            // Display information about the retrieved survey
            System.out.println("Survey Title: " + survey.getTitle());
            System.out.println("Description: " + survey.getDescription());

            // Display the questions associated with the survey
            System.out.println("Questions:");
            for (Question question : survey.getQuestions()) {
                System.out.println("  - " + question.getText());
            }
        } else {
            // Display a message if the survey was not found
            JOptionPane.showMessageDialog(null, "Survey not found with title: " + surveyTitle);
        }
    }
}