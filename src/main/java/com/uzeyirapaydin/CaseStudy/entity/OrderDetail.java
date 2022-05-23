package com.uzeyirapaydin.CaseStudy.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = -1836495529425541295L;

    @EqualsAndHashCode.Include
    @NonNull
    @JsonDeserialize
    private UUID bookId;

    @Min(1)
    private int itemQuantity;

    @Null
    private Double itemPrice;
}
