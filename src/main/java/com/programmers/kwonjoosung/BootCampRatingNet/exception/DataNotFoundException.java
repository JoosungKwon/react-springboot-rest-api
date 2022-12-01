package com.programmers.kwonjoosung.BootCampRatingNet.exception;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

@Slf4j
public class DataNotFoundException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "[Data Not Found] tableName: {1} columnName: {2} value: {3}";
    private static final String MESSAGE_LOG_FORMAT = "[Data Not Found] tableName: {} columnName: {} value: {}";

    public DataNotFoundException(String tableName, String columnName, String value) {
        super(MessageFormat.format(MESSAGE_FORMAT, tableName, columnName, value));
        log.error(MESSAGE_LOG_FORMAT, tableName, columnName, value);
    }
}
