package com.absolutions.currency.feign;

import com.absolutions.currency.fixer.SymbolsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "FixerClient",url = "http://data.fixer.io/api", fallback = FixerFallback.class)
public interface FixerClient {

    @RequestMapping(value = "/symbols", method = RequestMethod.GET)
    public SymbolsResponse symbols(@RequestParam("access_key") String accessKey);
}
