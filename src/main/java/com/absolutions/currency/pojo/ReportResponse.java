package com.absolutions.currency.pojo;

import com.absolutions.currency.entity.Currency;
import com.absolutions.currency.entity.Rate;
import com.absolutions.currency.util.Utils;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ReportResponse {
    private String date;
    private List<RateReportItem> items;

    public static ReportResponse fromDbResults(Date date, List<Rate> rates) {
        ReportResponse reportResponse = new ReportResponse();

        reportResponse.date = Utils.DATE_FORMAT.format(date);
        reportResponse.items = new ArrayList<>(rates.size());

        for (Rate rate: rates) {
            reportResponse.items.add(new RateReportItem(rate.getTimestamp(), rate.getRate()));
        }

        return reportResponse;
    }
}
