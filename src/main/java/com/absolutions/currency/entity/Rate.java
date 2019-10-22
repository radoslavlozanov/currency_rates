package com.absolutions.currency.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "RATE", indexes = {
        @Index(name = "RATE_INDX_DATE", columnList = "RATE_DATE"),
        @Index(name = "RATE_INDX_TIMESTAMP", columnList = "RATE_TIMESTAMP")
})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Rate extends BaseEntity {
    private Currency currency;
    private Currency base;
    private BigDecimal rate;
    private Date date;
    private Long timestamp;

    public Rate() {
    }

    public Rate(Currency currency, Currency base, BigDecimal rate, Date date, Long timestamp) {
        this.currency = currency;
        this.base = base;
        this.rate = rate;
        this.date = date;
        this.timestamp = timestamp;
    }

    @JoinColumn(name= "CURRENCY_ID", nullable = false)
    @ManyToOne(targetEntity = Currency.class)
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @JoinColumn(name = "BASE_ID", nullable = false)
    @ManyToOne(targetEntity = Currency.class)
    public Currency getBase() {
        return base;
    }

    public void setBase(Currency base) {
        this.base = base;
    }

    @Basic
    @Column(name = "RATE", nullable = false)
    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Basic
    @Column(name = "RATE_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "RATE_TIMESTAMP", nullable = false)
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Rate rate = (Rate) o;
        return rate.base.equals(base) && rate.currency.equals(rate) && rate.rate.equals(rate)
                && rate.date.equals(date) && rate.timestamp.equals(timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), currency, base, rate, date, timestamp);
    }
}
