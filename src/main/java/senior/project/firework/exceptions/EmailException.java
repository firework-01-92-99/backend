package senior.project.firework.exceptions;

public class EmailException extends RuntimeException {
    public EmailException(String code) {
        super("file." + code);
    }

    public static EmailException templateNotFound() {
        return new EmailException("template.not.found");
    }
}
