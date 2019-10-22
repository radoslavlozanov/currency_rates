package com.absolutions.currency.repo;

import com.absolutions.currency.entity.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    Currency getByCode(String code);
}
