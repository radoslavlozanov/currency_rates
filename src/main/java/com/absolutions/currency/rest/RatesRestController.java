package com.absolutions.currency.rest;

import com.absolutions.currency.component.DbUpdater;
import com.absolutions.currency.feign.FixerClient;
import com.absolutions.currency.fixer.LatestResponse;
import com.absolutions.currency.fixer.SymbolsResponse;
import com.absolutions.currency.fixer.TimeseriesResponse;
import com.absolutions.currency.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController()
@RequestMapping("/rates")
public class RatesRestController {
    @Value("${fixer.access.key}")
    private String accessKey;

    @Autowired
    private FixerClient fixerClient;
    @Autowired
    private DbUpdater dbUpdater;

    @RequestMapping(method = RequestMethod.GET, value = "/historic/{base}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TimeseriesResponse historic(@PathVariable("base") String base) {
        TimeseriesResponse timeseriesResponse = fixerClient.timeseries(accessKey, "1999-01-01",
                Utils.DATE_FORMAT.format(new Date()), base);
        // TODO: update database records

        return timeseriesResponse;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/latest/{base}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LatestResponse latestRate(@PathVariable("base") String base) {
        LatestResponse latestResponse = fixerClient.latest(accessKey, base);
        dbUpdater.updateLatestRates(latestResponse);

        return latestResponse;
    }

}
