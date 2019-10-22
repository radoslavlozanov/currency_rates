package com.absolutions.currency.rest;

import com.absolutions.currency.entity.Currency;
import com.absolutions.currency.pojo.ReportResponse;
import com.absolutions.currency.repo.CurrencyRepository;
import com.absolutions.currency.repo.RateRepository;
import com.absolutions.currency.util.Utils;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;

@RestController()
@RequestMapping("/report")
public class ReportsRestController {
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private CurrencyRepository currencyRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{currency}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReportResponse report(@PathVariable("currency") String base, @PathVariable("date") String date) {
        Currency currency = currencyRepository.getByCode(base);

        if (currency == null) {
            throw new RuntimeException("Invalid currency code");
        }

        Date reportDate = null;
        try {
            reportDate = Utils.DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format, supported is yyyy-MM-dd");
        }

        Currency baseCurrency = currencyRepository.getByCode("EUR");
        return ReportResponse.fromDbResults(reportDate,
                rateRepository.findAllByBaseAndDateAndCurrency(baseCurrency, reportDate, currency));
    }

}
