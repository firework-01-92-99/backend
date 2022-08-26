package senior.project.firework.exceptions;

import java.time.LocalDateTime;

public class ExceptionRepo {

    public static enum ERROR_CODE {

        ACCOUNT_USERNAME_INCORRECT(1001),
        ACCOUNT_PASSWORD_INCORRECT(1002),
        USERNAME_HAVE_ALREADY(1003);

        private int errorValue;

        ERROR_CODE(int errorValue){
            this.errorValue = errorValue;
        }

    }
    private ERROR_CODE errorCode;
    private String errorMessage;
    private LocalDateTime errorDateTime;

    public ExceptionRepo(ERROR_CODE errorCode, String errorMessage, LocalDateTime errorDateTime) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDateTime = errorDateTime;
    }

    public ERROR_CODE getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public LocalDateTime getErrorDateTime() {
        return errorDateTime;
    }
}
