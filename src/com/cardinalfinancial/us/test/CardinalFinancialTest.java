package com.cardinalfinancial.us.test;

import com.cardinalfinancial.us.model.Checkout;
import com.cardinalfinancial.us.model.RentalAgreement;
import com.cardinalfinancial.us.model.Tool;
import com.cardinalfinancial.us.service.CheckoutService;
import com.cardinalfinancial.us.service.CheckoutServiceImpl;
import com.cardinalfinancial.us.utils.DateUtils;
import com.cardinalfinancial.us.utils.NumberFormatUtils;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;


@RunWith(value = BlockJUnit4ClassRunner.class)

/**
 * JUnit test class that initializes Tool enums, invokes Checkout with the specified arguments, and asserts all values in the returned Rental Agreement instance are correct.
 * @author Sylvia Barnai
 */

public class CardinalFinancialTest extends TestCase {

    /**
     * The Checkout object being tested.
     */
    private CheckoutService checkoutService;

    /**
     * Initializes CheckoutService instance before executing one or more unit tests
     */
    @Before
    public void setUp() {
        checkoutService = new CheckoutServiceImpl();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test1() {
        System.out.println("***** Test 1 *****");

        Tool jackhammer = Tool.JAKR;

        Checkout checkout = Checkout.Builder.newInstance()
                .setToolCode(jackhammer.getToolCode().getValue())
                .setRentalDayCount(5)
                .setDiscountPercent(101)
                .setCheckoutDate("9/3/15")
                .build();

        RentalAgreement rentalAgreement = checkoutService.processCheckout(checkout);

        // Validate Checkout terms

        assertEquals("JAKR", checkout.getToolCode());
        assertEquals("09/03/15", DateUtils.convertDateToUSStandardFormat(checkout.getCheckoutDate()));
        assertEquals(5, checkout.getRentalDayCount());
        assertEquals("101%", NumberFormatUtils.formatPercentage(checkout.getDiscountPercent()));

        // Since the exception is thrown after the invalid discount percent is entered, then there is no need to validate the remaining values of the RentalAgreement instance
    }


    @Test
    public void test2() {
        System.out.println("***** Test 2 *****");
        Tool ladder = Tool.LADW;
        Checkout checkout = Checkout.Builder.newInstance()
                .setToolCode(ladder.getToolCode().getValue())
                .setRentalDayCount(3)
                .setDiscountPercent(10)
                .setCheckoutDate("7/2/20")
                .build();

        RentalAgreement rentalAgreement = checkoutService.processCheckout(checkout);

        // Validate Checkout terms
        assertEquals("LADW", checkout.getToolCode());
        assertEquals("07/02/20", DateUtils.convertDateToUSStandardFormat(checkout.getCheckoutDate()));
        assertEquals(3, checkout.getRentalDayCount());
        assertEquals("10%", NumberFormatUtils.formatPercentage(checkout.getDiscountPercent()));

        // Validate RentalAgreement expected values
        assertEquals("07/05/20", DateUtils.calculateDueDate(rentalAgreement.getCheckoutDate(), rentalAgreement.getNumberOfDaysRented()));
        assertEquals("$1.99", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDailyRentalCharge()));
        assertEquals(2, rentalAgreement.getChargeableDaysCount());
        assertEquals("$3.98", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getPreDiscountCharge()));
        assertEquals("10%", NumberFormatUtils.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$0.40", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDiscountAmount()));
        assertEquals("$3.58", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getFinalCharge()));
    }


    @Test
    public void test3() {
        System.out.println("***** Test 3 *****");
        Tool chainsaw = Tool.CHNS;

        Checkout checkout = Checkout.Builder.newInstance()
                .setToolCode(chainsaw.getToolCode().getValue())
                .setRentalDayCount(5)
                .setDiscountPercent(25)
                .setCheckoutDate("7/2/15")
                .build();

        RentalAgreement rentalAgreement = checkoutService.processCheckout(checkout);

        // Validate Checkout terms
        assertEquals("CHNS", checkout.getToolCode());
        assertEquals("07/02/15", DateUtils.convertDateToUSStandardFormat(checkout.getCheckoutDate()));
        assertEquals(5, checkout.getRentalDayCount());
        assertEquals("25%", NumberFormatUtils.formatPercentage(checkout.getDiscountPercent()));

        // Validate RentalAgreement expected values
        assertEquals("07/07/15", DateUtils.calculateDueDate(rentalAgreement.getCheckoutDate(), rentalAgreement.getNumberOfDaysRented()));
        assertEquals("$1.49", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDailyRentalCharge()));
        assertEquals(3, rentalAgreement.getChargeableDaysCount());
        assertEquals("$4.47", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getPreDiscountCharge()));
        assertEquals("25%", NumberFormatUtils.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$1.12", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDiscountAmount()));
        assertEquals("$3.35", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getFinalCharge()));
    }


    @Test
    public void test4() {
        System.out.println("***** Test 4 *****");
        Tool jackhammer = Tool.JAKD;

        Checkout checkout = Checkout.Builder.newInstance()
                .setToolCode(jackhammer.getToolCode().getValue())
                .setRentalDayCount(6)
                .setDiscountPercent(0)
                .setCheckoutDate("9/3/15")
                .build();

        RentalAgreement rentalAgreement = checkoutService.processCheckout(checkout);

        // Validate Checkout terms
        assertEquals("JAKD", checkout.getToolCode());
        assertEquals("09/03/15", DateUtils.convertDateToUSStandardFormat(checkout.getCheckoutDate()));
        assertEquals(6, checkout.getRentalDayCount());
        assertEquals("0%", NumberFormatUtils.formatPercentage(checkout.getDiscountPercent()));

        // Validate RentalAgreement expected values
        assertEquals("09/09/15", DateUtils.calculateDueDate(rentalAgreement.getCheckoutDate(), rentalAgreement.getNumberOfDaysRented()));
        assertEquals("$2.99", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDailyRentalCharge()));
        assertEquals(3, rentalAgreement.getChargeableDaysCount());
        assertEquals("$8.97", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getPreDiscountCharge()));
        assertEquals("0%", NumberFormatUtils.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$0.00", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDiscountAmount()));
        assertEquals("$8.97", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getFinalCharge()));

    }

    @Test
    public void test5() {
        System.out.println("***** Test 5 *****");

        Tool jackhammer = Tool.JAKR;
        Checkout checkout = Checkout.Builder.newInstance()
                .setToolCode(jackhammer.getToolCode().getValue())
                .setRentalDayCount(9)
                .setDiscountPercent(0)
                .setCheckoutDate("7/2/15")
                .build();

        RentalAgreement rentalAgreement = checkoutService.processCheckout(checkout);

        // Validate Checkout terms
        assertEquals("JAKR", checkout.getToolCode());
        assertEquals("07/02/15", DateUtils.convertDateToUSStandardFormat(checkout.getCheckoutDate()));
        assertEquals(9, checkout.getRentalDayCount());
        assertEquals("0%", NumberFormatUtils.formatPercentage(checkout.getDiscountPercent()));

        // Validate RentalAgreement expected values
        assertEquals("07/11/15", DateUtils.calculateDueDate(rentalAgreement.getCheckoutDate(), rentalAgreement.getNumberOfDaysRented()));
        assertEquals("$2.99", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDailyRentalCharge()));
        assertEquals(5, rentalAgreement.getChargeableDaysCount());
        assertEquals("$14.95", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getPreDiscountCharge()));
        assertEquals("0%", NumberFormatUtils.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$0.00", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDiscountAmount()));
        assertEquals("$14.95", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getFinalCharge()));
    }

    @Test
    public void test6() {
        System.out.println("***** Test 6 *****");

        Tool jackhammer = Tool.JAKR;

        Checkout checkout = Checkout.Builder.newInstance()
                .setToolCode(jackhammer.getToolCode().getValue())
                .setRentalDayCount(4)
                .setDiscountPercent(50)
                .setCheckoutDate("7/2/20") // input date format can be M/d/yy or MM/dd/yy
                .build();

        RentalAgreement rentalAgreement = checkoutService.processCheckout(checkout);

        // Validate Checkout terms
        assertEquals("JAKR", checkout.getToolCode());
        assertEquals("07/02/20", DateUtils.convertDateToUSStandardFormat(checkout.getCheckoutDate()));
        assertEquals(4, checkout.getRentalDayCount());
        assertEquals("50%", NumberFormatUtils.formatPercentage(checkout.getDiscountPercent()));

        // Validate RentalAgreement expected values
        assertEquals("07/06/20", DateUtils.calculateDueDate(rentalAgreement.getCheckoutDate(), rentalAgreement.getNumberOfDaysRented()));
        assertEquals("$2.99", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDailyRentalCharge()));
        assertEquals(1, rentalAgreement.getChargeableDaysCount());
        assertEquals("$2.99", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getPreDiscountCharge()));
        assertEquals("50%", NumberFormatUtils.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$1.50", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDiscountAmount()));
        assertEquals("$1.49", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getFinalCharge()));
    }

}