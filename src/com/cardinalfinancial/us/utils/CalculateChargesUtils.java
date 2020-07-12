package com.cardinalfinancial.us.utils;

import com.cardinalfinancial.us.common.ToolConstants;
import com.cardinalfinancial.us.exception.CheckoutException;
import com.cardinalfinancial.us.model.Checkout;
import com.cardinalfinancial.us.model.Tool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for calculating charges for tool rentals
 *
 * @author Sylvia Barnai
 */
public class CalculateChargesUtils {

    /**
     * Logger used to log errors for exceptions that occur within this class
     */
    private static final Logger LOGGER = Logger.getLogger(CalculateChargesUtils.class.getName());

    public static final String CHARGE_ALL_DAYS = "CHARGE_ALL";
    public static final String CHARGE_WEEKENDS = "CHARGE_WEEKENDS";
    public static final String CHARGE_HOLIDAYS = "CHARGE_HOLIDAYS";
    public static final String CHARGE_BOTH = "CHARGE_BOTH";

    public static int calculateChargeableDaysCount(Tool tool, Checkout checkout) {

        // number of chargeable days
        int chargeableDaysCount = 0;

        try {
            // Convert checkout date string to local date and add 1 more day to it
            LocalDate date = DateUtils.parseInputDate(checkout.getCheckoutDate()).plusDays(1);
            // Create due date instance from checkout date and rental day count
            LocalDate dueDate = DateUtils.parseInputDate(checkout.getCheckoutDate()).plusDays(checkout.getRentalDayCount());

            while (!date.isEqual(dueDate.plusDays(1))) {
                // Tool has no holiday charge and weekend charge
                if (tool.getToolType().isHolidayCharge().equals(ToolConstants.NO_CHARGE) && tool.getToolType().isWeekendCharge().equals(ToolConstants.NO_CHARGE)) {
                    // check if the date is 4th of July
                    if (tool.getToolType().isHolidayCharge().equals(ToolConstants.NO_CHARGE) && tool.getToolType().isWeekendCharge().equals(ToolConstants.NO_CHARGE) && isFourthOfJuly(date)
                            && (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                        // if 4th of July is on Saturday or Sunday, weekday needs to be subtracted from chargeableDaysCount
                        chargeableDaysCount--;
                    }

                    // If it is Labor Day or a weekend day, then do not increment chargeableDaysCount
                    else if (!isLaborDay(date)) {
                        if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                            chargeableDaysCount++;
                        }
                    }

                }

                // Tool has no weekend charge but it has holiday charge
                else if (tool.getToolType().isWeekendCharge().equals(ToolConstants.NO_CHARGE) && tool.getToolType().isHolidayCharge().equals(ToolConstants.YES_CHARGE)) {
                    // If it is the weekend, then do not increment the chargeableDaysCount. If it is a weekday, then increment chargeableDaysCount
                    if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                        chargeableDaysCount++;
                    }

                }

                // Tool has no holiday charge, but has weekend charge
                else if (tool.getToolType().isHolidayCharge().equals(ToolConstants.NO_CHARGE) && tool.getToolType().isWeekendCharge().equals(ToolConstants.YES_CHARGE)) {
                    // check that the date is July 4th
                    if (isFourthOfJuly(date)) {
                        // Check if the date is Saturday or Sunday and if the due date is on Saturday......
                        // if the due date is on Sunday, they do not get a free day for Sunday
                        if (((date.getDayOfWeek() == DayOfWeek.SATURDAY) && (DateUtils.parseInputDate(checkout.getCheckoutDate()).isEqual(date.minusDays(1))))
                                || ((date.getDayOfWeek() == DayOfWeek.SUNDAY) && (LocalDate.parse(DateUtils.calculateDueDate(checkout.getCheckoutDate(), checkout.getRentalDayCount())).isEqual(date)))) {
                            chargeableDaysCount++;
                        }
                    }

                    // If it is not Labor Day, increment chargeableDaysCount charged by default
                    else if (!isLaborDay(date)) {
                        chargeableDaysCount++;

                    }

                }
                // Increment the date by 1 day
                date = date.plusDays(1);
            }
            return chargeableDaysCount;
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            throw new CheckoutException("Failed to count number of days charged");
        }
    }


    /**
     * Calculates the pre-discount charge as charge days X daily charge. Resulting total rounded to cents.
     *
     * @param chargeableDaysCount The number of chargeable days, from the day after checkout through and including due date, excluding "no charge" days as specified by the tool type
     * @param dailyRentalCharge   The amount per day, specified by the tool type
     * @return the calculated pre-discount charge
     */
    public static double calculatePreDiscountCharge(int chargeableDaysCount, double dailyRentalCharge) {
        // Uses BigDecimal and setScale to format final charge to two decimal places
        double preDiscountCharge = new BigDecimal(chargeableDaysCount * dailyRentalCharge).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
        return preDiscountCharge;
    }

    /**
     * Calculates the discount amount from discount percent and pre-discount charge. Resulting amount rounded to cents.
     *
     * @param discountPercent   The discount percent, specified at checkout
     * @param preDiscountCharge The pre-discount charge, which was calculated by calculatePreDiscountCharge() function
     * @return the calculated discount amount
     */
    public static double calculateDiscountAmount(int discountPercent, double preDiscountCharge) {
        // Uses BigDecimal and setScale to format final charge to two decimal places
        double discountAmount = new BigDecimal(discountPercent * 0.01f * preDiscountCharge).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return discountAmount;
    }


    /**
     * Calculates the final charge, which is pre-discount charge minus discount amount.
     *
     * @param preDiscountCharge The pre-discount charge, calculated by calculatePreDiscountCharge()
     * @param discountAmount    The discount amount, specified at checkout
     * @return the calculated final charge
     */
    public static double calculateFinalCharge(double preDiscountCharge, double discountAmount) {
        double finalCharge = 0;
        try {
            finalCharge = preDiscountCharge - discountAmount;
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Failed to calculate final charge");
            throw new CheckoutException("Failed to calculate final charge");
        }
        return finalCharge;

    }

    /**
     * Determines whether a given date falls on the 4th of July
     *
     * @param date The specified date
     * @return true if it is on 4th of July, false if it is not
     */
    public static boolean isFourthOfJuly(LocalDate date) {
        if (date.getMonthValue() == 7 && date.getDayOfMonth() == 4) {
            return true;
        }
        return false;
    }

    /**
     * Determines whether a given date falls on Labor Day
     *
     * @param date The specified date
     * @return true if it is on Labor Day, false if it is not
     */
    public static boolean isLaborDay(LocalDate date) {
        if (date.getMonthValue() == 9 && date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() < 8)
            return true;
        return false;
    }
}