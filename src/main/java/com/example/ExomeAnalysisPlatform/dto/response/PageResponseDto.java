package com.example.ExomeAnalysisPlatform.dto.response;

import com.example.ExomeAnalysisPlatform.entity.ExomeEntity;
import com.example.ExomeAnalysisPlatform.helper.mapper.ExomeDtoMapper;
import lombok.Data;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageResponseDto{

    long totalNumberOfElements;
    int currentPage;
    List<ExomeResponseDto> exomes;

    public PageResponseDto(Page<ExomeEntity> page) {
        ExomeDtoMapper exomeDtoMapper = Mappers.getMapper( ExomeDtoMapper.class );
        this.totalNumberOfElements = page.getTotalElements();
        this.currentPage = page.getNumber();
        this.exomes = exomeDtoMapper.toDtos(page.getContent());
    }
}
