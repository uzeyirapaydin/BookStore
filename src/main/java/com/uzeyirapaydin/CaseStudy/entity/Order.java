package com.uzeyirapaydin.CaseStudy.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Document
@Data
@Builder
public class Order {
    @Id
    private UUID id;

    private UUID customerId;

    private String status;

    private Date creationDate;

    private Set<OrderDetail> details;
}
