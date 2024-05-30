package com.test.service;

import com.test.domain.dto.Checkout;
import com.test.domain.dto.RentalAgreement;
import com.test.exception.CheckoutException;

public interface CheckoutService {
    RentalAgreement checkout(Checkout request) throws CheckoutException;
}
