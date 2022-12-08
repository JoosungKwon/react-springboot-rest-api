package com.programmers.kwonjoosung.BootCampRatingNet.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataParseException extends ServiceRuntimeException {

    private static final String MESSAGE_KEY = "error.data.parse";
    private static final String DETAIL_KEY = "error.data.parse.detail";

    public DataParseException(String tableName, String columnName, String value) {
        super(MESSAGE_KEY, DETAIL_KEY, new String[] {tableName, columnName, value});
    }
}
