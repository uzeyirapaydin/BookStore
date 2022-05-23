package com.uzeyirapaydin.CaseStudy.mapper;

public interface EntityMapper<E, D> {
    D toDTO(E e);
    E toEntity(D d);
}
