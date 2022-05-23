package com.uzeyirapaydin.CaseStudy.controller;

import com.uzeyirapaydin.CaseStudy.dto.response.OrderMonthlyReportResponse;
import com.uzeyirapaydin.CaseStudy.service.OrderStatisticsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    final OrderStatisticsService orderStatisticsService;

    @ApiOperation(value = "Get Monthly Order Statistics")
    @GetMapping("/order/monthly-report")
    public ResponseEntity<OrderMonthlyReportResponse> getOrderMonthlyReport() {
        return ResponseEntity.ok(orderStatisticsService.getMonthlyReport());
    }
}
