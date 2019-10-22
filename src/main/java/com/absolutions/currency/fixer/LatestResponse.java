package com.absolutions.currency.fixer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class LatestResponse extends BaseFixerResponse {
    private long timestamp;
    private String date;
    private String base;
    private Map<String, BigDecimal> rates;
}
