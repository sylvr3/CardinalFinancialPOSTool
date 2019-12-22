package com.cardinalfinancial.us.utils;

import com.cardinalfinancial.us.exception.CheckoutException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class used to handle calculations for different types of dates and the number of specific types of days
 *
 * @author Sylvia Barnai
 */

public class DateUtils {

    /**
     * Logger used to log errors for exceptions that occur within this class
     */
    private static final Logger LOGGER = Logger.getLogger(DateUtils.class.getName());

    /**
     * Converts the input date string to LocalDate
     *
     * @param date The input date string to be converted
     * @return Date represented as LocalDate object
     */
    public static LocalDate parseInputDate(String date) {
        LocalDate formattedDate = null;
        try {
            // Create two dateTimeFormatter objects to handle dates in M/d/yy and MM/dd/yy
            DateTimeFormatter formatterSingleDigits = DateTimeFormatter.ofPattern("M/d/yy")
                    .withLocale(Locale.US);
            DateTimeFormatter formatterDoubleDigits = DateTimeFormatter.ofPattern("MM/dd/yy")
                    .withLocale(Locale.US);

            // Store DateTimeFormatter instances in an array
            DateTimeFormatter[] formats = {formatterSingleDigits,
                    formatterDoubleDigits};
            boolean invalidFormat = false;

            // Try parsing the date in each of the formats that are available and if it is unable to, set invalidFormat flag to true
            for (DateTimeFormatter format : formats) {
                try {
                    formattedDate = LocalDate.parse(date, format);
                    invalidFormat = false;
                    break;
                } catch (DateTimeParseException e) {
                    invalidFormat = true;
                    LOGGER.log(Level.WARNING, "Failed to parse date [" + date + "] with format [" + format + "]");
                    throw new DateTimeParseException("Failed to parse date [" + date + "] with format [" + format + "]", date, 0);
                }
            }

            if (invalidFormat) {
                LOGGER.log(Level.WARNING, "Failed to parse date [" + date + "]");
                throw new CheckoutException("Failed to parse date [" + date + "]");
            }
            return formattedDate;

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Date is in invalid format");
            throw new CheckoutException("Date is in invalid format");

        }
    }


    /**
     * Converts date string from M/d/yy to MM/dd/yy format for RentalAgreement output for date strings
     *
     * @param dateStr The string to be converted
     * @return date String in MM/dd/yy format
     */
    public static String convertDateToUSStandardFormat(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        Date date = null;
        // Attempts to convert String to Date object and throws an exception if it fails to
        try {
            date = (Date) formatter.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.log(Level.WARNING, "Failed to parse date");
            throw new CheckoutException("Failed to parse date");
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Failed to parse date due to invalid input");
            throw new CheckoutException("Failed to parse date due to invalid input");
        }
        return formatter.format(date);
    }

    /**
     * Calculates the due date based on the checkout date and the number of rental days
     * @param checkoutDate the checkout date, specified at checkout
     * @param numberOfDaysRented, the number of rental days, specified at checkout
     * @return due date as a formatted string in MM/dd/yy format so that it is in the correct format for console output
     */
    public static String calculateDueDate(String checkoutDate, int numberOfDaysRented) {
        LocalDate dueDate = null;
        String formattedDate = "";
        String convertedDate = "";

        // Regex pattern for M/d/yy format
        String dateRegex = "\\d{1}/\\d{2}/\\d{2}";

        // If the date is in M/d/yy format, then convert it to MM/dd/yy, otherwise just set convertedDate to checkoutDate String
        if (checkoutDate.matches(dateRegex)) {
            convertedDate = DateUtils.convertDateToUSStandardFormat(checkoutDate);

        } else {
            convertedDate = checkoutDate;
        }

        try {
            // Calculates the due date
            dueDate = parseInputDate(convertedDate).plusDays(numberOfDaysRented);
            // Converts dueDate to "MM/dd/yy" format for output
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
            formattedDate = dueDate.format(formatter);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Invalid due date");
            throw new CheckoutException("Invalid due date");

        }
        return formattedDate;
    }



}

