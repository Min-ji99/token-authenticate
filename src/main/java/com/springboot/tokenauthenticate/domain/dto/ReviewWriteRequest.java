package com.springboot.tokenauthenticate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReviewWriteRequest {
    private Long hospitalId;
    private String title;
    private String content;
    private String username;
}
