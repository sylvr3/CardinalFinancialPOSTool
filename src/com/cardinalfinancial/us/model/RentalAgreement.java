package com.cardinalfinancial.us.model;

import com.cardinalfinancial.us.utils.DateUtils;
import com.cardinalfinancial.us.utils.NumberFormatUtils;

/**
 * RentalAgreement class that is generated when a tool is checked out using Builder pattern
 *
 * @author Sylvia Barnai
 */

public final class RentalAgreement {

    /**
     * Tool code for rental agreement, specified at checkout
     */

    private String toolCode;
    /**
     * Tool type for rental agreement, retrieved from tool info
     */

    private String toolType;

    /**
     * Tool brand for rental agreement, retrieved from tool info
     */

    private final String toolBrand;

    /**
     * Number of days for rental, specified at checkout
     */
    private int numberOfDaysRented;


    /**
     * The date that the tool was checked out (in String format), specified at checkout
     */

    private String checkoutDate;

    /**
     * Date the tool needs to be returned (in String format), calculated from checkout date and rental days
     */

    private String dueDate;

    /**
     * Amount charged per day, specified by the tool type
     */

    private double dailyRentalCharge;

    /**
     * Count of chargeable days, from day after checkout through and including due date, excluding "no charge" days as specified by the tool type.
     */

    private int chargeableDaysCount;

    /**
     * Charge before discount.
     * Calculated as charge days * daily charge. Resulting total rounded half up to cents.
     */

    private double preDiscountCharge;

    /**
     * Percent discount for the rental, specified at checkout
     */

    private int discountPercent;


    /**
     * Discount amount, calculated from discount % and pre-discount charge. Resulting amount rounded half up to cents.
     */

    private double discountAmount;


    /**
     * The final amount that is charged and is calculated by subtracting discount amount from pre-discount charge
     */

    private double finalCharge;


    /**
     * Private constructor for RentalAgreement instance
     *
     * @param builder RentalAgreement.Builder class used to construct the RentalAgreement instance
     */

    private RentalAgreement(Builder builder) {
        this.toolCode = builder.toolCode;
        this.toolType = builder.toolType;
        this.toolBrand = builder.toolBrand;
        this.numberOfDaysRented = builder.numberOfDaysRented;
        this.checkoutDate = builder.checkoutDate;
        this.dueDate = builder.dueDate;
        this.dailyRentalCharge = builder.dailyRentalCharge;
        this.chargeableDaysCount = builder.chargeableDaysCount;
        this.preDiscountCharge = builder.preDiscountCharge;
        this.discountPercent = builder.discountPercent;
        this.discountAmount = builder.discountAmount;
        this.finalCharge = builder.finalCharge;
    }

    /**
     * Static Builder class for constructing RentalAgreement instance
     */
    public static class Builder {

        /* Instance fields */

        /**
         * Tool code for rental agreement, specified at checkout
         */

        private String toolCode;
        /**
         * Tool type for rental agreement, retrieved from tool info
         */

        private String toolType;

        /**
         * Tool brand for rental agreement, retrieved from tool info
         */

        private String toolBrand;

        /**
         * Number of days for rental, specified at checkout
         */
        private int numberOfDaysRented;


        /**
         * The date that the tool was checked out (in String format), specified at checkout
         */

        private String checkoutDate;

        /**
         * Date the tool needs to be returned (in String format), calculated from checkout date and rental days
         */

        private String dueDate;

        /**
         * Amount charged per day, specified by the tool type
         */

        private double dailyRentalCharge;

        /**
         * Count of chargeable days, from day after checkout through and including due date, excluding "no charge" days as specified by the tool type.
         */

        private int chargeableDaysCount;

        /**
         * Charge before discount.
         * Calculated as charge days X daily charge. Resulting total rounded half up to cents.
         */

        private double preDiscountCharge;

        /**
         * Percent discount for the rental, specified at checkout
         */

        private int discountPercent;


        /**
         * Discount amount, calculated from discount % and pre-discount charge. Resulting amount rounded half up to cents.
         */

        private double discountAmount;

        /**
         * The final amount that is charged and is calculated by subtracting discount amount from pre-discount charge
         */

        private double finalCharge;


        /**
         * Creates a new instance of RentalAgreement
         *
         * @return RentalAgreement.Builder class used to construct the RentalAgreement instance
         */
        public static Builder newInstance() {
            return new Builder();
        }

        /**
         * Private constructor for Builder
         */
        private Builder() {
        }

        /* Setters */

        /**
         * Sets the tool code
         *
         * @param toolCode the tool code to set, based on the tool info
         * @return RentalAgreement.Builder class used to construct the RentalAgreement instance
         */

        public Builder setToolCode(String toolCode) {
            this.toolCode = toolCode;
            return this;
        }

        /**
         * Sets the tool type
         *
         * @param toolType the tool type to set, basd on the tool info
         * @return RentalAgreement.Builder class used to construct the RentalAgreement instance
         */

        public Builder setToolType(String toolType) {
            this.toolType = toolType;
            return this;
        }

        /**
         * Sets the tool brand
         *
         * @param toolBrand the tool brand to set, based on the tool info
         * @return RentalAgreement.Builder class used to construct the RentalAgreement instance
         */

        public Builder setToolBrand(String toolBrand) {
            this.toolBrand = toolBrand;
            return this;
        }


        /**
         * Sets the number of days the tool was rented for
         *
         * @param numberOfDaysRented the number of days the tool was rented, specified at checkout
         * @return RentalAgreement.Builder class used to construct the RentalAgreement instance
         */
        public Builder setNumberOfDaysRented(int numberOfDaysRented) {
            this.numberOfDaysRented = numberOfDaysRented;
            return this;
        }

        /**
         * Sets the checkout date
         *
         * @param checkoutDate the checkoud date to set, specified at checkout
         * @return RentalAgreement.Builder class used to construct the RentalAgreement instance
         */

        public Builder setCheckoutDate(String checkoutDate) {
            this.checkoutDate = checkoutDate;
            return this;
        }

        /**
         * Sets the due date
         *
         * @param dueDate the due date to set
         * @return RentalAgreement.Builder class used to construct the RentalAgreement instance
         */

        public Builder setDueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        /**
         * Sets the daily rental charge
         *
         * @param dailyRentalCharge the daily rental charge to set, specified by the tool type
         * @return RentalAgreement.Builder class used to construct the RentalAgreement instance
         */
        public Builder setDailyRentalCharge(double dailyRentalCharge) {
            this.dailyRentalCharge = dailyRentalCharge;
            return this;
        }

        /**
         * Sets the number of chargeable days
         *
         * @param chargeableDaysCount the count of chargeable days
         * @return RentalAgreement Builder class used to construct the RentalAgreement instance
         */
        public Builder setChargeableDaysCount(int chargeableDaysCount) {
            this.chargeableDaysCount = chargeableDaysCount;
            return this;
        }

        /**
         * Sets the pre-discount charge
         *
         * @param preDiscountCharge the pre-discount charge
         * @return RentalAgreement.Builder class used to construct the RentalAgreement instance
         */
        public Builder setPreDiscountCharge(double preDiscountCharge) {
            this.preDiscountCharge = preDiscountCharge;
            return this;
        }

        /**
         * Sets the discount percent
         *
         * @param discountPercent the discount percent specified at checkout
         * @return RentalAgreement.Builder class used to construct the RentalAgreement instance
         */
        public Builder setDiscountPercent(int discountPercent) {
            this.discountPercent = discountPercent;
            return this;
        }

        /**
         * Sets the discount amount
         *
         * @param discountAmount the discount amount
         * @return RentalAgreement.Builder class used to construct the RentalAgreement instance
         */
        public Builder setDiscountAmount(double discountAmount) {
            this.discountAmount = discountAmount;
            return this;
        }

        /**
         * Sets the final charge
         *
         * @param finalCharge the final charge
         * @return RentalAgreement.Builder class used to construct the RentalAgreement instance
         */
        public Builder setFinalCharge(double finalCharge) {
            this.finalCharge = finalCharge;
            return this;
        }

        /**
         * Build method to deal with outer class to return outer instance
         *
         * @return RentalAgreement instance
         */
        public RentalAgreement build() {
            return new RentalAgreement(this);
        }
    }


    /* Accessors for RentalAgreement properties */

    /**
     * Retrieves the tool code
     *
     * @return the tool code
     */

    public String getToolCode() {
        return toolCode;
    }

    /**
     * Retrieves the tool type
     *
     * @return the tool type
     */

    public String getToolType() {
        return toolType;
    }

    /**
     * Retrieves the tool brand
     *
     * @return the tool brand
     */

    public String getToolBrand() {
        return toolBrand;
    }

    /**
     * Retrieves the number of rental days
     *
     * @return the number of rental days
     */


    public int getNumberOfDaysRented() {
        return numberOfDaysRented;
    }

    /**
     * Retrieves the checkout date
     *
     * @return the checkout date
     */


    public String getCheckoutDate() {
        return checkoutDate;
    }


    /**
     * Retrieves the due date
     *
     * @return the due date
     */


    public String getDueDate() {
        return dueDate;
    }


    /**
     * Retrieves the amount charged per day
     *
     * @return the daily rental charge
     */

    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    /**
     * Retrieves the number of days to be charged
     *
     * @return the number of chargeable days
     */

    public int getChargeableDaysCount() {
        return chargeableDaysCount;
    }

    /**
     * Retrieves the pre-discount charge
     *
     * @return the pre-discount charge
     */

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }


    /**
     * Retrieves the discount percent
     *
     * @return The discount percent
     */

    public int getDiscountPercent() {
        return discountPercent;
    }


    /**
     * Retrieves the discount amount
     *
     * @return the discount amount
     */

    public double getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Retrieves the final charge
     *
     * @return the final charge
     */

    public double getFinalCharge() {
        return finalCharge;
    }

    /**
     * Prints out the RentalAgreement values as text to the console, following the formatting that is stated in the specification
     */
    public void printRentalAgreementDetails() {
        // RentalAgreement details
        System.out.println("Tool code: " + getToolCode() + "\n" +
                "Tool type: " + getToolType() + "\n" +
                "Tool brand: " + getToolBrand() + "\n" +
                "Rental days: " + getNumberOfDaysRented() + "\n" +
                "Checkout date: " + DateUtils.convertDateToUSStandardFormat(getCheckoutDate()) + "\n" +
                "Due date: " + DateUtils.convertDateToUSStandardFormat(getDueDate()) + "\n" +
                "Daily charge: " + NumberFormatUtils.formatDoubleCurrency(getDailyRentalCharge()) + "\n" +
                "Charge days: " + getChargeableDaysCount() + "\n" +
                "Pre-discount charge: " + NumberFormatUtils.formatDoubleCurrency(getPreDiscountCharge()) + "\n" +
                "Discount %: " + NumberFormatUtils.formatPercentage(getDiscountPercent()) + "\n" +
                "Discount amount: " + NumberFormatUtils.formatDoubleCurrency(getDiscountAmount()) + "\n" +
                "Final charge: " + NumberFormatUtils.formatDoubleCurrency(getFinalCharge()) + "\n");
    }

}