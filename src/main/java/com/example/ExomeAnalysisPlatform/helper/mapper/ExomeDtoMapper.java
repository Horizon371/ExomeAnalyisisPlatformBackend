package com.example.ExomeAnalysisPlatform.helper.mapper;

import com.example.ExomeAnalysisPlatform.dto.response.ExomeResponseDto;
import com.example.ExomeAnalysisPlatform.entity.ExomeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExomeDtoMapper {

    ExomeEntity toEntity(ExomeResponseDto exomeResponseDto);

    ExomeResponseDto toDto(ExomeEntity exomeEntity);

    List<ExomeResponseDto> toDtos (List<ExomeEntity> exomeEntities);
}
