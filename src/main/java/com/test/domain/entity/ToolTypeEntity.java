package com.test.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

@Entity
@Table(name = "tool_type")
public class ToolTypeEntity extends BaseEntity {

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "daily_charge",precision=10, scale=2)
    private BigDecimal dailyCharge = BigDecimal.ZERO;

    @Column(name = "daily_charge_currency", length = 3, nullable = false)
    private String dailyChargeCurrency;

    @Column(name = "weekday_charge", nullable = false)
    private Boolean weekdayCharge;

    @Column(name = "weekend_charge", nullable = false)
    private Boolean weekendCharge;

    @Column(name = "holiday_charge", nullable = false)
    private Boolean holidayCharge;

    public ToolTypeEntity() {}

    public ToolTypeEntity(String name, BigDecimal dailyCharge, String dailyChargeCurrency,
                          Boolean weekdayCharge, Boolean weekendCharge, Boolean holidayCharge) {
        this.name = name;
        this.dailyCharge = dailyCharge;
        this.dailyChargeCurrency = dailyChargeCurrency;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(BigDecimal dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public String getDailyChargeCurrency() {
        return dailyChargeCurrency;
    }

    public void setDailyChargeCurrency(String dailyChargeCurrency) {
        this.dailyChargeCurrency = dailyChargeCurrency;
    }

    public Boolean getWeekdayCharge() {
        return weekdayCharge;
    }

    public void setWeekdayCharge(Boolean weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    public Boolean getWeekendCharge() {
        return weekendCharge;
    }

    public void setWeekendCharge(Boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    public Boolean getHolidayCharge() {
        return holidayCharge;
    }

    public void setHolidayCharge(Boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ToolTypeEntity brand = (ToolTypeEntity) o;
        return Objects.equals(getId(), brand.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "{\"ToolTypeEntity\":"

                + ",         \"name\":\"" + name + "\""
                + ",         \"dailyCharge\":\"" + dailyCharge + "\""
                + ",         \"dailyChargeCurrency\":\"" + dailyChargeCurrency + "\""
                + ",         \"weekdayCharge\":\"" + weekdayCharge + "\""
                + ",         \"weekendCharge\":\"" + weekendCharge + "\""
                + ",         \"holidayCharge\":\"" + holidayCharge + "\""
                + super.toString()
                + "}";
    }
}

