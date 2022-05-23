package com.uzeyirapaydin.CaseStudy.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

@Data
public class OrderMonthlyReportResponse {
    private List<OrderMonthlyReportRow> orderMonthlyReport;
}
