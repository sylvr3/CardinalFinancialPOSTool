package com.cardinalfinancial.us.model;

import com.cardinalfinancial.us.common.ToolConstants;

/**
 * ToolType enum used for parameters that are related to tool type
 *
 * @author Sylvia Barnai
 */
public enum ToolType {

    /**
     * Enum values for ToolType (constants for each tool's type)
     */
    LADDER(ToolConstants.LADDER, ToolConstants.RENTAL_CHARGE_LADDER, ToolConstants.YES_CHARGE, ToolConstants.YES_CHARGE, ToolConstants.NO_CHARGE),
    CHAINSAW(ToolConstants.CHAINSAW, ToolConstants.RENTAL_CHARGE_CHAINSAW, ToolConstants.YES_CHARGE, ToolConstants.NO_CHARGE, ToolConstants.YES_CHARGE),
    JACKHAMMER(ToolConstants.JACKHAMMER, ToolConstants.RENTAL_CHARGE_JACKHAMMER, ToolConstants.YES_CHARGE, ToolConstants.NO_CHARGE, ToolConstants.NO_CHARGE);

    /**
     * Value for tool type
     */

    private String value;

    /**
     * The daily charge for the tool
     */

    private double dailyCharge;

    /**
     * Flag for determining whether there is a weekday charge for the tool
     */

    private String weekdayCharge;
    /**
     * Flag for determining whether there is a weekend charge for the tool
     */
    private String weekendCharge;

    /**
     * Flag for determining whether there is a holiday charge for the tool
     */
    private String holidayCharge;

    /**
     * Retrieve value for tool type
     *
     * @return value for tool type
     */

    public String getValue() {
        return value;
    }

    /**
     * Retrieves the daily rental charge
     *
     * @return dailyCharge The daily rental charge
     */

    public double getDailyCharge() {
        return dailyCharge;
    }

    /**
     * Determine whether there should be a weekday charge
     *
     * @return weekdayCharge "Yes" if there is a weekday charge, "No" if there is no weekday charge
     */

    public String isWeekdayCharge() {
        return weekdayCharge;
    }

    /**
     * Determine whether there should be a weekend charge
     *
     * @return weekendCharge "Yes" if there is a weekend charge, "No" if there is no weekend charge
     */

    public String isWeekendCharge() {
        return weekendCharge;
    }

    /**
     * Determine whether there should be a holiday charge
     *
     * @return holidayCharge "Yes" if there is a holiday charge, "No" if there is no holiday charge
     */
    public String isHolidayCharge() {
        return holidayCharge;
    }

    /**
     * Constructor for ToolType instance
     *
     * @param value         The value for the tool type
     * @param dailyCharge   The daily charge
     * @param weekdayCharge The weekday charge
     * @param weekendCharge The weekend charge
     * @param holidayCharge The holiday charge
     */

    ToolType(String value, double dailyCharge, String weekdayCharge, String weekendCharge, String holidayCharge) {
        this.value = value;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }
}
