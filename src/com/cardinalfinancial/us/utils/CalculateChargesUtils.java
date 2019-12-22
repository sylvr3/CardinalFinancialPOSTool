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

    /**
     * Determine how many days should be charged based on whether holidays or weekend charges apply for a particular tool
     *
     * @param tool     Tool instance
     * @param checkout Checkout instance
     * @return the number of chargeable days
     */

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
                    if (date.getMonthValue() == 7 && date.getDayOfMonth() == 4) {
                        if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                            // if 4th of July is on Saturday or Sunday, weekday needs to be subtracted from chargeableDaysCount
                            chargeableDaysCount--;
                        }
                    }

                    // If it is Labor Day, then do not increment chargeableDaysCount
                    else if (date.getMonthValue() == 9 && date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() < 8) {

                    }

                    // If it is the weekend, then do not increment chargeableDaysCount. If it is a weekday, then increment chargeableDaysCount
                    else if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {

                    } else {
                        chargeableDaysCount++;
                    }
                }

                // Tool has no weekend charge but it has holiday charge
                else if (tool.getToolType().isWeekendCharge().equals(ToolConstants.NO_CHARGE) && tool.getToolType().isHolidayCharge().equals(ToolConstants.YES_CHARGE)) {
                    // If it is the weekend, then do not increment the chargeableDaysCount. If it is a weekday, then increment chargeableDaysCount
                    if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    } else {
                        chargeableDaysCount++;
                    }

                }

                // Tool has no holiday charge, but has weekend charge
                else if (tool.getToolType().isHolidayCharge().equals(ToolConstants.NO_CHARGE) && tool.getToolType().isWeekendCharge().equals(ToolConstants.YES_CHARGE)) {
                    // check that the date is July 4th
                    if (date.getMonthValue() == 7 && date.getDayOfMonth() == 4) {
                        // Check if the date is Saturday
                        if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                            // if the checkout date is Friday, then they do not get a free day for Saturday
                            if (DateUtils.parseInputDate(checkout.getCheckoutDate()).isEqual(date.minusDays(1))) {
                                chargeableDaysCount++;
                            }
                        }
                        // Check if the checkout date is Sunday
                        else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                            // if the due date is on Sunday, they do not get a free day for Sunday
                            if (LocalDate.parse(DateUtils.calculateDueDate(checkout.getCheckoutDate(), checkout.getRentalDayCount())).isEqual(date)) {
                                chargeableDaysCount++;
                            }
                        }
                    }

                    // check if the date is Labor Day
                    else if (date.getMonthValue() == 9 && date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() < 8) {
                    }

                    // Increment chargeableDaysCount charged by default
                    else {
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
}
