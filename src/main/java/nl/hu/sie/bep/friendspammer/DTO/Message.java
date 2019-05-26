package nl.hu.sie.bep.friendspammer.DTO;

public class Message {

    public String to;
    public String from;
    public String subject;
    public String text;
    public String asHtml;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAsHtml() {
        return asHtml;
    }

    public void setAsHtml(String asHtml) {
        this.asHtml = asHtml;
    }
}
