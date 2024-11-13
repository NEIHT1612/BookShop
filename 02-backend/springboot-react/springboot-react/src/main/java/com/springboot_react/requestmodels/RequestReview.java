package com.springboot_react.requestmodels;

import lombok.Data;

import java.util.Optional;

@Data
public class RequestReview {
    private Long bookId;

    private double rating;

    private Optional<String> reviewDescription;
}
