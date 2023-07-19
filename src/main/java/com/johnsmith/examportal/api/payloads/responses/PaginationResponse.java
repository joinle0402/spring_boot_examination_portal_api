package com.johnsmith.examportal.api.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PaginationResponse<T> {
    private List<T> content = new ArrayList<>();
    private int currentPage;
    private int limit;
    private Long totalElements;
    private int totalPages;
    private boolean lastPage;
}
