package com.cardinalfinancial.us.utils;

import com.cardinalfinancial.us.exception.CheckoutException;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class used for formatting currency and percentages
 *
 * @author Sylvia Barnai
 */

public class NumberFormatUtils {

    /**
     * Logger used to log errors for exceptions that occur within this class
     */
    private static final Logger LOGGER = Logger.getLogger(NumberFormatUtils.class.getName());

    /**
     * Format currency in US dollars, USD ($)
     *
     * @param dollarAmount The dollar amount as a double
     * @return dollar amount as a formatted String
     */

    public static String formatDoubleCurrency(double dollarAmount) {
        NumberFormat numberFormat = null;
        try {
            // set locale to US and format for dollar sign and digits:
            Locale currentLocale = new Locale("en", "US");
            // Returns a currency format for the current locale of US
            numberFormat = NumberFormat.getCurrencyInstance(currentLocale);
            // Sets maximum number of digits allowed in the fractional part of a number (displayed after the decimal)
            numberFormat.setMaximumFractionDigits(2);
            return numberFormat.format(dollarAmount);

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Invalid dollar amount");
            throw new CheckoutException("Invalid dollar amount");

        }
    }

    /**
     * Used to format percentage for discount
     *
     * @param percentageAmount the percentage as an integer
     * @return percentage amount as a formatted String
     */

    public static String formatPercentage(int percentageAmount) {
        String formattedPercentage = "";
        // Try to format String by adding % symbol to the end of the String and throw 
        // exception if percentageAmount that needs to be formatted is invalid
        try {
            formattedPercentage = String.format("%d%%", percentageAmount);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Invalid dollar amount");
            throw new CheckoutException("Invalid dollar amount");
        }
        return formattedPercentage;

    }

}
