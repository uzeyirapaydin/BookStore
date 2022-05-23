package com.uzeyirapaydin.CaseStudy.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
@Document
public class Customer {
    @Id
    private UUID id;

    private String name;

    private String surname;

    @Indexed(unique=true)
    private String email;

    private String password;
}
