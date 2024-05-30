package com.test.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "brand")
public class BrandEntity extends BaseEntity {

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    public BrandEntity() {
    }

    public BrandEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BrandEntity brand = (BrandEntity) o;
        return Objects.equals(getId(), brand.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "{\"BrandEntity\":"

                + ",         \"name\":\"" + name + "\""
                + super.toString()
                + "}";
    }
}
