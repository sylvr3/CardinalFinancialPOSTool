package com.cardinalfinancial.us.model;

import com.cardinalfinancial.us.common.ToolConstants;

/**
 * ToolCode enum used for parameters that are related to tool code
 *
 * @author Sylvia Barnai
 */
public enum ToolCode {

    /**
     * Enum values for ToolCode (constants for each tool's code)
     */
    LADW(ToolConstants.LADW),
    CHNS(ToolConstants.CHNS),
    JAKR(ToolConstants.JAKR),
    JAKD(ToolConstants.JAKD);

    /**
     * Value for tool code
     */
    private String value;

    /**
     * Retrieve value for tool code
     *
     * @return value for tool code
     */
    public String getValue() {
        return value;
    }

    /**
     * Constructor for ToolCode enum that has one argument: value
     *
     * @param value the value for the tool code
     */
    ToolCode(String value) {
        this.value = value;
    }
}