package com.test.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.test.domain.serial.JsonDateSerializer;
import com.test.domain.serial.MoneySerializer;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RentalAgreement extends Checkout {

    @JsonProperty("tool_type")
    private String toolTypeName;

    @JsonProperty("tool_brand")
    private String brandName;

    @JsonProperty("due_date")
    @JsonSerialize(using = JsonDateSerializer.class)
    private Instant dueDate;

    @JsonProperty("daily_rental_charge")
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal dailyRentalCharge;

    @JsonProperty("charge_days")
    private Long chargeDays;

    @JsonProperty("pre-discount_charge")
    private BigDecimal preDiscountCharge;

    @JsonProperty("discount_amount")
    private BigDecimal discountAmount;

    @JsonProperty("final_charge")
    private BigDecimal finalCharge;

    public RentalAgreement() {
    }

    public RentalAgreement(Checkout checkout) {
        super(checkout);
    }

    public RentalAgreement(String toolCode, Integer rentalDayCount, Integer discountPercentage, Instant checkoutDate,
                           String toolTypeName, String brandName, Instant dueDate,
                           BigDecimal dailyRentalCharge, Long chargeDays, BigDecimal preDiscountCharge,
                           BigDecimal discountAmount, BigDecimal finalCharge) {
        super(toolCode, rentalDayCount, discountPercentage, checkoutDate);
        this.toolTypeName = toolTypeName;
        this.brandName = brandName;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public String getToolTypeName() {
        return toolTypeName;
    }

    public void setToolTypeName(String toolTypeName) {
        this.toolTypeName = toolTypeName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Instant getDueDate() {
        return dueDate;
    }

    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public void setDailyRentalCharge(BigDecimal dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    public Long getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(Long chargeDays) {
        this.chargeDays = chargeDays;
    }

    public BigDecimal getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public void setPreDiscountCharge(BigDecimal preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
    }

    public void printToConsole(){
        System.out.println( "Tool code: " + this.getToolCode());
        System.out.println( "Tool type: " + this.getToolTypeName());
        System.out.println( "Tool brand: " + this.getBrandName());
        System.out.println( "Rental days: " + this.getRentalDayCount());
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        System.out.println( "Check out date: " + formatter.format(Date.from(this.getCheckoutDate())));
        System.out.println( "Due date: " + formatter.format(Date.from(this.getDueDate())));
        NumberFormat money = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println( "Daily rental charge: " + money.format(this.getDailyRentalCharge()));
        System.out.println( "Charge days: " + this.getChargeDays());
        System.out.println( "Pre-discount charge: " + money.format(this.getPreDiscountCharge()));
        System.out.println( "Discount percent: " + this.getDiscountPercentage()+"%");
        System.out.println( "Discount amount: " + money.format(this.getDiscountAmount()));
        System.out.println( "Final charge: " + money.format(this.getFinalCharge()));
    }

    @Override
    public String toString() {
        return "{\"RentalAgreement\":"

                + ",         \"toolTypeName\":\"" + toolTypeName + "\""
                + ",         \"brandName\":\"" + brandName + "\""
                + ",         \"dueDate\":" + dueDate
                + ",         \"dailyRentalCharge\":\"" + dailyRentalCharge + "\""
                + ",         \"chargeDays\":\"" + chargeDays + "\""
                + ",         \"preDiscountCharge\":\"" + preDiscountCharge + "\""
                + ",         \"discountAmount\":\"" + discountAmount + "\""
                + ",         \"finalCharge\":\"" + finalCharge + "\""
                + super.toString()
                + "}";
    }
}
