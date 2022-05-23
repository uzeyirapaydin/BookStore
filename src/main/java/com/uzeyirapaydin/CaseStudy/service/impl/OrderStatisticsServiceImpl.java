package com.uzeyirapaydin.CaseStudy.service.impl;

import com.uzeyirapaydin.CaseStudy.dto.response.AggregationMonthlyReport;
import com.uzeyirapaydin.CaseStudy.dto.response.OrderMonthlyReportResponse;
import com.uzeyirapaydin.CaseStudy.dto.response.OrderMonthlyReportRow;
import com.uzeyirapaydin.CaseStudy.entity.Order;
import com.uzeyirapaydin.CaseStudy.service.OrderStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderStatisticsServiceImpl implements OrderStatisticsService {

    @Autowired
    AggregationMonthlyReport aggregationMonthlyReport;
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public OrderMonthlyReportResponse getMonthlyReport() {
        OrderMonthlyReportResponse response = new OrderMonthlyReportResponse();
        AggregationResults<OrderMonthlyReportRow> result = mongoTemplate.aggregate(aggregationMonthlyReport.prepare(), Order.class, OrderMonthlyReportRow.class);
        response.setOrderMonthlyReport(result.getMappedResults());
        return response;
    }
}
