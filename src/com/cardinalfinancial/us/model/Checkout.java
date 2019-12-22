package com.cardinalfinancial.us.model;

import java.util.logging.Logger;

/**
 * RentalAgreement class that is generated when a tool is checked out using Builder pattern
 *
 * @author Sylvia Barnai
 */

public final class Checkout {

    /**
     * Logger used to log errors for exceptions that occur within this class
     */
    private static final Logger LOGGER = Logger.getLogger(Checkout.class.getName());

    /**
     * The code of the tool being rented.
     */
    private String toolCode;

    /**
     * The number of days the tool is rented for
     */
    private int rentalDayCount;

    /**
     * The discount percent
     */

    private int discountPercent;

    /**
     * The checkout date
     */

    private String checkoutDate;


    /**
     * Private constructor for Checkout instance
     *
     * @param builder RentalAgreement.Builder class used to construct the Checkout instance
     */
    private Checkout(Builder builder) {
        this.toolCode = builder.toolCode;
        this.rentalDayCount = builder.rentalDayCount;
        this.checkoutDate = builder.checkoutDate;
        this.discountPercent = builder.discountPercent;
    }

    /**
     * Static Builder class for constructing Checkout instance
     */
    public static class Builder {

        /* Instance fields */

        /**
         * The code of the tool being rented.
         */
        private String toolCode;

        /**
         * The number of days the tool is rented for
         */
        private int rentalDayCount;

        /**
         * The discount percent
         */

        private int discountPercent;

        /**
         * The checkout date
         */

        private String checkoutDate;

        /**
         * Creates a new instance of Checkout
         *
         * @return Checkout.Builder class used to construct the Checkout instance
         */
        public static Builder newInstance() {
            return new Builder();
        }

        /**
         * Private constructor for Builder
         */
        private Builder() {
        }

        /* Setters for Checkout properties */

        /**
         * Sets the tool code
         *
         * @param toolCode the tool code to be set
         * @return Checkout.Builder class used to construct the Checkout instance
         */
        public Builder setToolCode(String toolCode) {
            this.toolCode = toolCode;
            return this;
        }

        /**
         * Sets the rental day count
         * If number of rental days is less than 1, then throw exception
         *
         * @param rentalDayCount the rental day count to set
         * @return Checkout.Builder class used to construct the Checkout instance
         */

        public Builder setRentalDayCount(int rentalDayCount) {
            if (rentalDayCount < 1)
                throw new IllegalArgumentException("Tool must be rented for at least 1 day." + " Please enter the number of days you would like to rent the tool.");
            this.rentalDayCount = rentalDayCount;
            return this;
        }

        /**
         * Sets the discount percent
         * If discount percentage is not between 0 and 100%, then throw exception
         *
         * @param discountPercent the discount percent to set
         * @return Checkout.Builder class used to construct the Checkout instance
         */
        public Builder setDiscountPercent(int discountPercent) {
            if (discountPercent < 0 || discountPercent > 100)
                throw new IllegalArgumentException("Discount percent must be between 0 and 100. Please enter the discount percent for the tool rental.");
            this.discountPercent = discountPercent;
            return this;
        }

        /**
         * Sets the checkout date. Checkout date is a date string in M/d/yy or MM/dd/yy format
         *
         * @param checkoutDate the checkout date to set
         * @return Checkout.Builder class used to construct the Checkout instance
         */

        public Builder setCheckoutDate(String checkoutDate) {
            this.checkoutDate = checkoutDate;
            return this;
        }

        /**
         * Build method to deal with outer class to return outer instance
         *
         * @return Checkout instance
         */
        public Checkout build() {
            return new Checkout(this);
        }
    }


    /* Accessors for Checkout properties */

    /**
     * Retrieves the tool code
     *
     * @return the tool code
     */

    public String getToolCode() {
        return toolCode;
    }

    /**
     * Retrieves the rental day count
     *
     * @return the number of rental days
     */

    public int getRentalDayCount() {
        return rentalDayCount;
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
     * Retrieves the checkout date
     *
     * @return the checkout date
     */
    public String getCheckoutDate() {
        return checkoutDate;
    }

}