package com.cardinalfinancial.us;

import com.cardinalfinancial.us.model.Checkout;
import com.cardinalfinancial.us.model.RentalAgreement;
import com.cardinalfinancial.us.model.Tool;
import com.cardinalfinancial.us.service.CheckoutService;
import com.cardinalfinancial.us.service.CheckoutServiceImpl;

public class CardinalFinancialDemo {

    public static void main(String[] args) {
        CheckoutService checkoutService = new CheckoutServiceImpl();

        // Test 1:

        /*System.out.println("***** Test 1 *****");
         Exception in thread "main" java.lang.IllegalArgumentException: Discount percent must be between 0 and 100. Please enter the discount percent for the tool rental.
	at com.cardinalfinancial.us.model.Checkout$Builder.setDiscountPercent(Checkout.java:134)
	at com.cardinalfinancial.us.test.CardinalFinancialDemo.main(CardinalFinancialDemo.java:28)
        Tool jackhammer = Tool.JAKR;

        Checkout checkout = Checkout.Builder.newInstance()
                .setToolCode(jackhammer.getToolCode().getValue())
                .setRentalDayCount(5)
                .setDiscountPercent(101)
                .setCheckoutDate("9/3/15")
                .build();

        checkoutService.processCheckout(checkout);*/


        // Test 2:
        System.out.println("***** Test 2 *****");
        Tool ladder = Tool.LADW;
        Checkout checkout2 = Checkout.Builder.newInstance()
                .setToolCode(ladder.getToolCode().getValue())
                .setRentalDayCount(3)
                .setDiscountPercent(10)
                .setCheckoutDate("7/2/20")
                .build();

        RentalAgreement rentalAgreement2 = checkoutService.processCheckout(checkout2);

        rentalAgreement2.printRentalAgreementDetails();


        // Test 3
        System.out.println("***** Test 3 *****");
        Tool chainsaw = Tool.CHNS;

        Checkout checkout3 = Checkout.Builder.newInstance()
                .setToolCode(chainsaw.getToolCode().getValue())
                .setRentalDayCount(5)
                .setDiscountPercent(25)
                .setCheckoutDate("7/2/15")
                .build();

        RentalAgreement rentalAgreement3 = checkoutService.processCheckout(checkout3);
        rentalAgreement3.printRentalAgreementDetails();


        // Test 4
        System.out.println("***** Test 4 *****");
        Tool jackhammer2 = Tool.JAKD;

        Checkout checkout4 = Checkout.Builder.newInstance()
                .setToolCode(jackhammer2.getToolCode().getValue())
                .setRentalDayCount(6)
                .setDiscountPercent(0)
                .setCheckoutDate("9/3/15")
                .build();

        RentalAgreement rentalAgreement4 = checkoutService.processCheckout(checkout4);
        rentalAgreement4.printRentalAgreementDetails();


        // Test 5
        System.out.println("***** Test 5 *****");

        Tool jackhammer3 = Tool.JAKR;
        Checkout checkout5 = Checkout.Builder.newInstance()
                .setToolCode(jackhammer3.getToolCode().getValue())
                .setRentalDayCount(9)
                .setDiscountPercent(0)
                .setCheckoutDate("7/2/15")
                .build();

        RentalAgreement rentalAgreement5 = checkoutService.processCheckout(checkout5);
        rentalAgreement5.printRentalAgreementDetails();


        // Test 6

        System.out.println("***** Test 6 *****");

        Tool jackhammer4 = Tool.JAKR;

        Checkout checkout6 = Checkout.Builder.newInstance()
                .setToolCode(jackhammer4.getToolCode().getValue())
                .setRentalDayCount(4)
                .setDiscountPercent(50)
                .setCheckoutDate("7/2/20") // input date format can be M/d/yy or MM/dd/yy
                .build();

        RentalAgreement rentalAgreement6 = checkoutService.processCheckout(checkout6);
        rentalAgreement6.printRentalAgreementDetails();


    }

}