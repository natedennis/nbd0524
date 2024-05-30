package com.test.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tool")
public class ToolEntity extends BaseEntity {

    @Column(name = "tool_code", length = 10, nullable = false, unique = true)
    private String toolCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tool_type_id", insertable = true, updatable = true, nullable = false)
    private ToolTypeEntity toolType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", insertable = true, updatable = true, nullable = false)
    private BrandEntity brand;

    public ToolEntity() {
    }

    public ToolEntity(String toolCode, ToolTypeEntity toolType, BrandEntity brand) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public ToolTypeEntity getToolType() {
        return toolType;
    }

    public void setToolType(ToolTypeEntity toolType) {
        this.toolType = toolType;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ToolEntity brand = (ToolEntity) o;
        return Objects.equals(getId(), brand.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "{\"ToolEntity\":"

                + ",         \"toolCode\":\"" + toolCode + "\""
                + ",         \"toolType\":" + toolType
                + ",         \"brand\":" + brand
                + super.toString()
                + "}";
    }
}
