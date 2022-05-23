package com.uzeyirapaydin.CaseStudy.service;

import com.uzeyirapaydin.CaseStudy.dto.response.OrderMonthlyReportResponse;

import java.util.List;

public interface OrderStatisticsService {

    OrderMonthlyReportResponse getMonthlyReport();
}
