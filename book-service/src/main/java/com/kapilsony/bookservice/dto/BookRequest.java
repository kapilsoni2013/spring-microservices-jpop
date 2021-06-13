package com.kapilsony.bookservice.dto;

import lombok.Data;

@Data
public class BookRequest {
    private String name;
    private Float price;
    private String author;
    private String category;
    private String description;
}
