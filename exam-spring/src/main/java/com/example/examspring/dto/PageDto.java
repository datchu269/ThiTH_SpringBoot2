package com.example.examspring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@NoArgsConstructor
@SuperBuilder
public class PageDto<T> {
    private Integer pageSize;
    private Integer pageNumber;

    private int totalPages;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private List<T> list;
}
