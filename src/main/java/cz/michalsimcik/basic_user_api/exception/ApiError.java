package cz.michalsimcik.basic_user_api.exception;

import java.time.Instant;

/**
 * Represents an API error response containing error details and timestamp.
 */
public class ApiError {
    private Integer errorCode;
    private String message;
    private Instant timestamp;

    public ApiError(Integer errorCode, String message, Instant timestamp) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = timestamp;

    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}

