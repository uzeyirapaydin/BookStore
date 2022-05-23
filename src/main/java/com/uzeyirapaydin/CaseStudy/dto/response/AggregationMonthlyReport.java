package com.uzeyirapaydin.CaseStudy.dto.response;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.stereotype.Component;

@Component
public class AggregationMonthlyReport {

    public Aggregation prepare() {
        return Aggregation.newAggregation(
                Aggregation.project("creationDate", "details")
                        .and(DateOperators.Month.monthOf("creationDate")).as("monthIndex"),
                Aggregation.unwind("details"),
                Aggregation.group("monthIndex")
                        .sum("details.itemQuantity").as("totalBookCount")
                        .sum("details.itemPrice").as("totalPurchasedPrice")
                        .count().as("totalOrderCount"),
                Aggregation.project("totalBookCount", "totalOrderCount", "totalPurchasedPrice")
                        .and("_id").as("monthIndex")
        );
    }
}
