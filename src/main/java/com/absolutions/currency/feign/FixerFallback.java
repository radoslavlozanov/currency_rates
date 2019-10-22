package com.absolutions.currency.feign;

import com.absolutions.currency.fixer.LatestResponse;
import com.absolutions.currency.fixer.SymbolsResponse;
import com.absolutions.currency.fixer.TimeseriesResponse;

public class FixerFallback implements FixerClient {
    @Override
    public SymbolsResponse symbols(String accessKey) {
        throw new RuntimeException("Misconfigured feign client");
    }

    @Override
    public TimeseriesResponse timeseries(String accessKey, String startDate, String endDate, String base) {
        throw new RuntimeException("Misconfigured feign client");
    }

    @Override
    public LatestResponse latest(String accessKey, String base) {
        throw new RuntimeException("Misconfigured feign client");
    }
}
