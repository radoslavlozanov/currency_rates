package com.absolutions.currency.feign;

import com.absolutions.currency.fixer.SymbolsResponse;

public class FixerFallback implements FixerClient {
    @Override
    public SymbolsResponse symbols(String accessKey) {
        throw new RuntimeException("Misconfigured feign client");
    }
}
