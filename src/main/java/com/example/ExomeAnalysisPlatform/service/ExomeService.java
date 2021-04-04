package com.example.ExomeAnalysisPlatform.service;

import com.example.ExomeAnalysisPlatform.dto.response.ExomeResponseDto;
import com.example.ExomeAnalysisPlatform.dto.response.PageResponseDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ExomeService {

    ExomeResponseDto saveExome(MultipartFile exomeFile) throws IOException;

    ExomeResponseDto getExome(long id);

    List<ExomeResponseDto> getExomeDtos();

    PageResponseDto getExomeDtosPaginated(int page, int size, String nameFilter);

    File getExomeFile(long id);

    void deleteExome(long id);
}
