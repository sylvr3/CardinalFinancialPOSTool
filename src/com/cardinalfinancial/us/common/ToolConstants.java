package com.cardinalfinancial.us.common;

/**
 * The Constants class used for initializing constant values that are used in Tool enum
 *
 * @author Sylvia Barnai
 */
public class ToolConstants {

    /* Constants for tool type */
    public static final String LADDER = "Ladder";
    public static final String CHAINSAW = "Chainsaw";
    public static final String JACKHAMMER = "Jackhammer";

    /* Constants for tool code */
    public static final String LADW = "LADW";
    public static final String CHNS = "CHNS";
    public static final String JAKR = "JAKR";
    public static final String JAKD = "JAKD";

    /* Constants for tool brand */
    public static final String WERNER = "Werner";
    public static final String STIHL = "Stihl";
    public static final String RIDGID = "Ridgid";
    public static final String DEWALT = "DeWalt";

    /* Constants for daily charges */
    public static final double RENTAL_CHARGE_LADDER = 1.99;
    public static final double RENTAL_CHARGE_CHAINSAW = 1.49;
    public static final double RENTAL_CHARGE_JACKHAMMER = 2.99;

    /* Constant strings that determine whether there should be a specific type of charge (weekday, weekend, or holiday */
    public static final String YES_CHARGE = "Yes";
    public static final String NO_CHARGE = "No";

}
