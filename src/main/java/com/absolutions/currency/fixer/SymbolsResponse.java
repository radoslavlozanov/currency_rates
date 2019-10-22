package com.absolutions.currency.fixer;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class SymbolsResponse extends BaseFixerResponse {
    private Map<String, String> symbols;
}
