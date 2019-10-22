package com.absolutions.currency.fixer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class TimeseriesResponse extends BaseFixerResponse {
    private boolean timeseries;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
    private String base;
    private Map<String, Map<String, String>> rates;
}
