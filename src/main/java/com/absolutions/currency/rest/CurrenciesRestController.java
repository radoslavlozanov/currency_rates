package com.absolutions.currency.rest;

import com.absolutions.currency.component.DbUpdater;
import com.absolutions.currency.feign.FixerClient;
import com.absolutions.currency.fixer.SymbolsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController()
@RequestMapping("/")
public class CurrenciesRestController {
    @Value("${fixer.access.key}")
    private String accessKey;

    @Autowired
    private FixerClient fixerClient;
    @Autowired
    private DbUpdater dbUpdater;

    @RequestMapping(method = RequestMethod.GET, value = "/currencies", produces = MediaType.APPLICATION_JSON_VALUE)
    public SymbolsResponse currencies() {
        SymbolsResponse symbolsResponse = fixerClient.symbols(accessKey);
        dbUpdater.updateCurrencies(symbolsResponse);

        return symbolsResponse;
    }

}
