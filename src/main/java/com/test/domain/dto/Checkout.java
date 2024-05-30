package com.test.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.test.domain.serial.JsonDateDeserializer;
import com.test.domain.serial.JsonDateSerializer;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Checkout {

    @JsonProperty("tool_code")
    private String toolCode;
    @JsonProperty("rental_day_count")
    private Integer rentalDayCount;
    @JsonProperty("discount_percentage")
    private Integer discountPercentage;

    @JsonProperty("checkout_date")
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Instant checkoutDate;

    @SuppressWarnings("unused")
    public Checkout() {
    }
    public Checkout(Checkout checkout) {
        this.toolCode = checkout.getToolCode();
        this.rentalDayCount = checkout.getRentalDayCount();
        this.discountPercentage = checkout.getDiscountPercentage();
        this.checkoutDate = checkout.getCheckoutDate();
    }

    public Checkout(String toolCode, Integer rentalDayCount, Integer discountPercentage, Instant checkoutDate) {
        this.toolCode = toolCode;
        this.rentalDayCount = rentalDayCount;
        this.discountPercentage = discountPercentage;
        this.checkoutDate = checkoutDate;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public Integer getRentalDayCount() {
        return rentalDayCount;
    }

    public void setRentalDayCount(Integer rentalDayCount) {
        this.rentalDayCount = rentalDayCount;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Instant getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Instant checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @Override
    public String toString() {
        return "{\"Checkout\":{"
                + "        \"toolCode\":\"" + toolCode + "\""
                + ",         \"rentalDayCount\":\"" + rentalDayCount + "\""
                + ",         \"discountPercentage\":\"" + discountPercentage + "\""
                + ",         \"checkoutDate\":" + checkoutDate
                + "}}";
    }
}
