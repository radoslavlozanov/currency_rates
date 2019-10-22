package com.absolutions.currency.repo;

import com.absolutions.currency.entity.Currency;
import com.absolutions.currency.entity.Rate;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface RateRepository extends PagingAndSortingRepository<Rate, Long> {
    boolean existsByBaseAndCurrencyAndTimestamp(Currency base, Currency currency, Long timestamp);

    List<Rate> findAllByBaseAndDateAndCurrency(Currency base, Date date, Currency currency);
}
