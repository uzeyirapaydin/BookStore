package com.uzeyirapaydin.CaseStudy.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Month;

@Data
public class OrderMonthlyReportRow {
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private BigDecimal totalPurchaseAmount;

    @JsonIgnore
    transient Integer monthIndex;

    @JsonProperty
    public String getMonth() {
        if (monthIndex == null) {
            return null;
        }

        return Month.of(monthIndex).name();
    }
}
