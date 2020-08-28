package engine.entity;

import javax.persistence.Entity;


public class Response {
    public final static Response CORRECT = new Response(true, "Congratulations, you're right!");
    public final static Response INCORRECT = new Response(false, "Wrong answer! Please, try again.");
    private boolean success;
    private String feedback;

    public Response(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
