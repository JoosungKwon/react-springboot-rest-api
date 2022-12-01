package com.programmers.kwonjoosung.BootCampRatingNet.error;

public enum SqlErrorMsgFormat {

    INSERT_FAIL("[INSERT FAIL] {}"),
    SELECT_FAIL("[SELECT FAIL] {}"),
    UPDATE_FAIL("[UPDATE FAIL] {}"),
    DELETE_FAIL("[DELETE FAIL] {}");

    private final String message;

    SqlErrorMsgFormat(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
