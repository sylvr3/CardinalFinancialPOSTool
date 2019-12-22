package com.cardinalfinancial.us.service;

import com.cardinalfinancial.us.exception.CheckoutException;
import com.cardinalfinancial.us.model.Checkout;
import com.cardinalfinancial.us.model.RentalAgreement;
import com.cardinalfinancial.us.model.Tool;
import com.cardinalfinancial.us.utils.CalculateChargesUtils;
import com.cardinalfinancial.us.utils.DateUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ServiceImpl that implements CheckoutService in order to process the checkout and generate a RentalAgreement instance
 *
 * @author Sylvia Barnai
 */

public class CheckoutServiceImpl implements CheckoutService {

    /**
     * Logger used to log errors for exceptions that occur within this class
     */

    private static final Logger LOGGER = Logger.getLogger(CheckoutServiceImpl.class.getName());

    @Override

    /**
     * Processes Checkout instance generates new RentalAgreement instance based on tool and checkout criteria
     * @param checkout The Checkout instance to be processed
     * @return RentalAgreement instance
     */

    public RentalAgreement processCheckout(Checkout checkout) {

        RentalAgreement rentalAgreement = null;

        // Constructs Tool and RentalAgreement instances and throws exception if error occurs

        try {
            Tool tool = Tool.getTool(checkout.getToolCode());
            rentalAgreement = RentalAgreement.Builder.newInstance()
                    .setToolCode(checkout.getToolCode())
                    .setToolType(tool.getToolType().getValue())
                    .setToolBrand(tool.getToolBrand().getValue())
                    .setNumberOfDaysRented(checkout.getRentalDayCount())
                    .setCheckoutDate(checkout.getCheckoutDate())
                    .setDueDate(DateUtils.calculateDueDate(checkout.getCheckoutDate(), checkout.getRentalDayCount()))
                    .setDailyRentalCharge(tool.getToolType().getDailyCharge())
                    .setChargeableDaysCount(CalculateChargesUtils.calculateChargeableDaysCount(tool, checkout))
                    .setPreDiscountCharge(CalculateChargesUtils.calculatePreDiscountCharge(CalculateChargesUtils.calculateChargeableDaysCount(tool, checkout), tool.getToolType().getDailyCharge()))
                    .setDiscountPercent(checkout.getDiscountPercent())
                    .setDiscountAmount(CalculateChargesUtils.calculateDiscountAmount(checkout.getDiscountPercent(), CalculateChargesUtils.calculatePreDiscountCharge(CalculateChargesUtils.calculateChargeableDaysCount(tool, checkout), tool.getToolType().getDailyCharge())))
                    .setFinalCharge(CalculateChargesUtils.calculateFinalCharge(CalculateChargesUtils.calculatePreDiscountCharge(CalculateChargesUtils.calculateChargeableDaysCount(tool, checkout), tool.getToolType().getDailyCharge()), CalculateChargesUtils.calculateDiscountAmount(checkout.getDiscountPercent(), CalculateChargesUtils.calculatePreDiscountCharge(CalculateChargesUtils.calculateChargeableDaysCount(tool, checkout), tool.getToolType().getDailyCharge()))))
                    .build();
            rentalAgreement.printRentalAgreementDetails();
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Unable to create RentalAgreement instance");
            throw new CheckoutException("Unable to create RentalAgreement instance");

        }
        return rentalAgreement;

    }
}
