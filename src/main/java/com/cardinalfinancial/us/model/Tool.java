package com.cardinalfinancial.us.model;

/**
 * Tool enum. This enum includes tools listed with various attributes.
 * There are three possible tools: ladders, chainsaws, and jackhammers.
 *
 * @author Sylvia Barnai
 */

public enum Tool {
    /**
     * Enum values for Tool enum (tool code, tool type, and tool brand)
     */
    LADW(ToolCode.LADW, ToolType.LADDER, ToolBrand.WERNER),
    CHNS(ToolCode.CHNS, ToolType.CHAINSAW, ToolBrand.STIHL),
    JAKR(ToolCode.JAKR, ToolType.JACKHAMMER, ToolBrand.RIDGID),
    JAKD(ToolCode.JAKD, ToolType.JACKHAMMER, ToolBrand.DEWALT);

    /**
     * The unique identifier for a tool instance
     */

    private ToolCode toolCode;

    /**
     * The unique identifier for a tool instance
     */

    private ToolType toolType;

    /**
     * The brand name of the tool
     */
    private ToolBrand toolBrand;

    /**
     * Constructor for Tool enum
     *
     * @param toolCode  The tool type
     * @param toolType  The tool code
     * @param toolBrand The tool brand
     */
    Tool(ToolCode toolCode, ToolType toolType, ToolBrand toolBrand) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
    }

    /**
     * Retrieves the tool code
     *
     * @return the tool code
     */
    public ToolCode getToolCode() {
        return toolCode;
    }

    /**
     * Retrieves the tool type
     *
     * @return the tool type
     */
    public ToolType getToolType() {
        return toolType;
    }

    /**
     * Retrieves the tool brand
     *
     * @return the tool brand
     */
    public ToolBrand getToolBrand() {
        return toolBrand;
    }

    /**
     * Retrieves a Tool object that is based on a tool code
     *
     * @param code The tool code
     * @return The Tool object
     */
    public static Tool getTool(String code) {
        switch (code) {
            case "LADW":
                return LADW;
            case "CHNS":
                return CHNS;
            case "JAKR":
                return JAKR;
            case "JAKD":
                return JAKD;
            default:
                return null;
        }
    }
}