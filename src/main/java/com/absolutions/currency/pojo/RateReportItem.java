package com.absolutions.currency.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RateReportItem {
    private long timestamp;
    private BigDecimal rate;
}
