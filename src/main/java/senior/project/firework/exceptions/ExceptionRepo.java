package senior.project.firework.exceptions;

import java.time.LocalDateTime;

public class ExceptionRepo {

    public static enum ERROR_CODE {

        ACCOUNT_EMAIL_INCORRECT(1001),
        ACCOUNT_PASSWORD_INCORRECT(1002),
        ACCOUNT_EMAIL_HAVE_ALREADY(1003),

        STATUS_ACCOUNT_WAIT_APPROVE(2006),
        STATUS_ACCOUNT_WAIT_EDIT(2007),
        STATUS_ACCOUNT_WAIT_DELETE(2008),
        STATUS_ACCOUNT_WAIT_OTP(2010),
        STATUS_ACCOUNT_REJECT(2005),
        STATUS_ACCOUNT_DELETED(2009),

        POSITION_POSTING_NOT_NULL(3001);

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
