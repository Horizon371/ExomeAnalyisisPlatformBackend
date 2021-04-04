package com.example.ExomeAnalysisPlatform.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class PageRequestDto {
    int page;
    int size;
    String nameFilter;
}
