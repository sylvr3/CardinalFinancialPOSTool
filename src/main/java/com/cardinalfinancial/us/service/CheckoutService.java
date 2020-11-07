package com.cardinalfinancial.us.service;

import com.cardinalfinancial.us.model.Checkout;
import com.cardinalfinancial.us.model.RentalAgreement;

/**
 * Interface that defines processCheckout() function to be used by CheckoutServiceImpl
 * This helps achieve loose coupling and makes the classes more modular
 * @author Sylvia Barnai
 */

public interface CheckoutService {
    RentalAgreement processCheckout(Checkout checkout);
}
