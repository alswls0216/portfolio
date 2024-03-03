package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteDto {
    private String title;
    private String author;
    private String publisher;
    private String category;
    private String b_status;
    private int b_id;
}
