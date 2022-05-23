package com.uzeyirapaydin.CaseStudy.dto.response;

import lombok.*;

@Data
@Builder
public class ErrorResponse {
    private String title;
    private int status;
    private String description;
    private String requestMethod;
    private String requestUri;
}
