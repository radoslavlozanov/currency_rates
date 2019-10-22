package com.absolutions.currency.repo;

import com.absolutions.currency.entity.Rate;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RateRepository extends PagingAndSortingRepository<Rate, Long> {
}
