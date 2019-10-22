package com.absolutions.currency.component;

import com.absolutions.currency.entity.Currency;
import com.absolutions.currency.fixer.SymbolsResponse;
import com.absolutions.currency.repo.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DbUpdater {
    private static final long RUN_INTERVAL = 1000 * 60 * 5; // Five minues

    @Autowired
    private CurrencyRepository currencyRepository;

    private long lastRun = 0l;

    @Async
    public void updateCurrencies(SymbolsResponse symbolsResponse) {
        // Prevent duplicate runs - only single run will be done each 5 minutes
        synchronized (this) {
            if (lastRun > (System.currentTimeMillis() - RUN_INTERVAL)) {
                return;
            }
            lastRun = System.currentTimeMillis();
        }

        Set<String> currencyCodes = symbolsResponse.getSymbols().keySet();

        for (String currencyCode: currencyCodes) {
            if (currencyRepository.getByCode(currencyCode) != null) {
                continue;
            }

            currencyRepository.save(new Currency(currencyCode, symbolsResponse.getSymbols().get(currencyCode)));
        }
    }
}
