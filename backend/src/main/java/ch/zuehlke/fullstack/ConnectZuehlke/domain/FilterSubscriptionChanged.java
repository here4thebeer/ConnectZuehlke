package ch.zuehlke.fullstack.ConnectZuehlke.domain;

public class FilterSubscriptionChanged {

    private String message;

    public FilterSubscriptionChanged() {
        message = "something changed!";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}