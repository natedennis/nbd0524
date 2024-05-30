package com.test.service.impl;

import com.test.domain.dto.Checkout;
import com.test.domain.dto.RentalAgreement;
import com.test.domain.entity.ToolEntity;
import com.test.exception.CheckoutException;
import com.test.repo.ToolRepository;
import com.test.service.CheckoutService;
import com.test.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Date;

import static com.test.exception.CheckoutException.ERROR_CODE.*;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {
    Logger logger = LoggerFactory.getLogger(CheckoutServiceImpl.class);

    ToolRepository toolRepository;

    public CheckoutServiceImpl(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    @Override
    public RentalAgreement checkout(Checkout request) throws CheckoutException {
        RentalAgreement response = new RentalAgreement(request);
        if(request.getRentalDayCount() < 1) {
            throw new CheckoutException(RENTAL_DAY_COUNT, request.getRentalDayCount().toString());
        }
        if(request.getDiscountPercentage() > 100 || request.getDiscountPercentage() < 0) {
            throw new CheckoutException(DISCOUNT_RATE, request.getDiscountPercentage().toString());
        }

        ToolEntity tool = toolRepository.findByToolCode(request.getToolCode());
        response.setToolTypeName(tool.getToolType().getName());
        response.setBrandName(tool.getBrand().getName());


        // calculate due date
        response.setDueDate(Utils.calculateDueDate(Date.from(request.getCheckoutDate()),request.getRentalDayCount()).toInstant());
        response.setDailyRentalCharge(tool.getToolType().getDailyCharge());
//      Charge days - Count of chargeable days, from day after checkout through and including due
//                    date, excluding “no charge” days as specified by the tool type.
        //these holidays cant fall on weekend
        Date dateAfterCheckout = Utils.calculateDueDate(Date.from(request.getCheckoutDate()),1 );
        long billableDays = 0L;
        if(tool.getToolType().getWeekdayCharge()) {
            billableDays = Utils.numberOfWeekDays(dateAfterCheckout, request.getRentalDayCount() );
        }
        if(tool.getToolType().getWeekendCharge()){
            billableDays = Utils.numberOfWeekendDays(dateAfterCheckout, request.getRentalDayCount() );
        }
        if(!tool.getToolType().getHolidayCharge()){
            billableDays = billableDays - Utils.holidayCount(dateAfterCheckout,request.getRentalDayCount() );
        }
        response.setChargeDays(billableDays);

        // Pre-discount charge - Calculated as charge days X daily charge. Resulting total rounded half up
        // to cents.
        BigDecimal preDiscountCharge = (new BigDecimal(billableDays)).multiply(tool.getToolType().getDailyCharge())
                                        .setScale(2, RoundingMode.HALF_UP);
        response.setPreDiscountCharge(preDiscountCharge);
        //Discount amount - calculated from discount % and pre-discount charge. Resulting amount
        //rounded half up to cents.
        BigDecimal decimal =(new BigDecimal(request.getDiscountPercentage())).divide(new BigDecimal("100"));
        BigDecimal discountAmount = preDiscountCharge.multiply(decimal).setScale(2,RoundingMode.HALF_UP);
        response.setDiscountAmount(discountAmount);
        // Final charge - Calculated as pre-discount charge - discount amount.
        BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount);
        response.setFinalCharge(finalCharge);

        return response;
    }



}
