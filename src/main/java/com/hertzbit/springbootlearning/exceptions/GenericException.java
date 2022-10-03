package com.hertzbit.springbootlearning.exceptions;

public class GenericException {

    private String errorMessage;
    private int errorStatusCode;
    private String errorTimestamp;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorStatusCode() {
        return errorStatusCode;
    }

    public void setErrorStatusCode(int errorStatusCode) {
        this.errorStatusCode = errorStatusCode;
    }

    public String getErrorTimestamp() {
        return errorTimestamp;
    }

    public void setErrorTimestamp(String errorTimestamp) {
        this.errorTimestamp = errorTimestamp;
    }
}
