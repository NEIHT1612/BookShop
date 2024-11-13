package com.springboot_react.requestmodels;

import lombok.Data;

@Data
public class RequestAddBook {
    private String title;

    private String author;

    private String description;

    private int copies;

    private String category;

    private String img;
}
