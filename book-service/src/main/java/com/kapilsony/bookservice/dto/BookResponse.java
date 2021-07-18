package com.kapilsony.bookservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {
    private Long bookId;

    private String name;
    private Float price;
    private String author;
    private String category;
    private String description;
}
