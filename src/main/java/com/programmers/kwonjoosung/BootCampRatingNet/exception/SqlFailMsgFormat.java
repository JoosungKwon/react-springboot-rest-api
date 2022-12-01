package com.programmers.kwonjoosung.BootCampRatingNet.exception;

public enum SqlFailMsgFormat {

    INSERT_FAIL("[INSERT FAIL] {}"),
    SELECT_FAIL("[SELECT FAIL] {}"),
    UPDATE_FAIL("[UPDATE FAIL] {}"),
    DELETE_FAIL("[DELETE FAIL] {}");

    private final String message;

    SqlFailMsgFormat(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
