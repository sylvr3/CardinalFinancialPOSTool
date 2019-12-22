package com.cardinalfinancial.us.model;

import com.cardinalfinancial.us.common.ToolConstants;

/**
 * ToolBrand enum used for parameters that are related to tool type
 *
 * @author Sylvia Barnai
 */
public enum ToolBrand {

    /**
     * Enum values for ToolBrand (constants for each tool's brand)
     */
    WERNER(ToolConstants.WERNER),
    STIHL(ToolConstants.STIHL),
    RIDGID(ToolConstants.RIDGID),
    DEWALT(ToolConstants.DEWALT);

    /**
     * Value for tool brand
     */

    private String value;

    /**
     * Retrieve value for tool brand
     *
     * @return value for tool brand
     */
    public String getValue() {
        return value;
    }

    /**
     * Constructor for ToolBrand enum that has one argument: value
     *
     * @param value the value for the tool brand
     */
    ToolBrand(String value) {
        this.value = value;
    }

}
