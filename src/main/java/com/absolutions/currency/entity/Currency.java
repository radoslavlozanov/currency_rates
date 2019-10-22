package com.absolutions.currency.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "CURRENCY")
public class Currency extends BaseEntity {
    private String code;
    private String name;

    public Currency() {
    }

    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Basic
    @Column(name = "CODE", length = 3)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "NAME", length = 512)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Currency currency = (Currency) o;
        return code.equals(currency.code) &&
                name.equals(currency.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), code, name);
    }
}
