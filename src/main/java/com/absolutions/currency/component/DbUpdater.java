package com.absolutions.currency.component;

import com.absolutions.currency.entity.Currency;
import com.absolutions.currency.entity.Rate;
import com.absolutions.currency.fixer.LatestResponse;
import com.absolutions.currency.fixer.SymbolsResponse;
import com.absolutions.currency.repo.CurrencyRepository;
import com.absolutions.currency.repo.RateRepository;
import com.absolutions.currency.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;

@Component
public class DbUpdater {
    private static final long RUN_INTERVAL = 1000 * 60 * 5; // Five minues

    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private RateRepository rateRepository;

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

    @Async
    public void updateLatestRates(LatestResponse latestResponse) {
        // Start only single updater
        synchronized (rateRepository) {
            updateLatestRatesImpl(latestResponse);
        }
    }

    private void updateLatestRatesImpl(LatestResponse latestResponse) {
        Currency base = currencyRepository.getByCode(latestResponse.getBase());
        if (base == null) {
            return;
        }

        for (String currencyCode: latestResponse.getRates().keySet()) {
            Currency curCurrency = currencyRepository.getByCode(currencyCode);

            if (base.equals(curCurrency)) {
                // Do not save 1 on 1 currency rate
                continue;
            }

            try {
                BigDecimal rate = latestResponse.getRates().get(currencyCode);
                Date date = Utils.DATE_FORMAT.parse(latestResponse.getDate());
                Long timestamp = latestResponse.getTimestamp();

                if (!rateRepository.existsByBaseAndCurrencyAndTimestamp(base, curCurrency, timestamp)) {
                    rateRepository.save(new Rate(curCurrency, base, rate, date, timestamp));
                }
            } catch (ParseException parseExceptino) {
                continue;
            }
        }
    }
}
