package com.uzeyirapaydin.CaseStudy.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Constraint;
import java.util.UUID;

@Document
@Data
@Builder
public class Book {

    @Id
    private UUID id;

    private String name;
    private String authorName;

    private int stock;
    private double price;

    @Version
    private Long version;
}
