package com.programmers.kwonjoosung.BootCampRatingNet.exception;

public class DataNotFoundException extends ServiceRuntimeException {

    private static final String MESSAGE_KEY = "error.data.notfound";
    private static final String DETAIL_KEY = "error.data.notfound.detail";

    public DataNotFoundException(String tableName, String columnName, String value) {
        super(MESSAGE_KEY, DETAIL_KEY, new String[] {tableName, columnName, value});
    }
}
