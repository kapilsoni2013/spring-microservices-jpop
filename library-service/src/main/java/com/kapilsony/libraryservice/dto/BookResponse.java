package com.kapilsony.libraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Long bookId;

    private String name;
    private Float price;
    private String author;
    private String category;
    private String description;
}
