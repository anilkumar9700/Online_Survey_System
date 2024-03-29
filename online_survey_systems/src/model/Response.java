package model;

public class Response {
	private int questionId;
    private String answer;

    public Response(int questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Response{" +
                "questionId=" + questionId +
                ", answer='" + answer + '\'' +
                '}';
    }
}

