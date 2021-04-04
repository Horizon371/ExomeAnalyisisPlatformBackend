package com.example.ExomeAnalysisPlatform.service;

import com.example.ExomeAnalysisPlatform.dto.response.ExomeResponseDto;
import com.example.ExomeAnalysisPlatform.dto.response.PageResponseDto;
import com.example.ExomeAnalysisPlatform.entity.ExomeEntity;
import com.example.ExomeAnalysisPlatform.helper.mapper.ExomeDtoMapper;
import com.example.ExomeAnalysisPlatform.repository.ExomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.example.ExomeAnalysisPlatform.config.Config.savePath;

@Service
@AllArgsConstructor
public class ExomeServiceImpl implements ExomeService {

    private final ExomeRepository exomeRepository;
    private final ExomeDtoMapper exomeMapper;

    @Override
    public ExomeResponseDto saveExome(MultipartFile exomeFile) throws IOException {

        String filePath = savePath + exomeFile.getOriginalFilename();
        exomeFile.transferTo(new File(filePath));

        ExomeEntity exomeEntity = new ExomeEntity(
                null,
                exomeFile.getOriginalFilename(),
                filePath
        );
        exomeRepository.save(exomeEntity);
        return exomeMapper.toDto(exomeEntity);
    }

    @Override
    public ExomeResponseDto getExome(long id) {
        ExomeEntity exomeEntity = exomeRepository.getOne(id);
        return exomeMapper.toDto(exomeEntity);
    }

    @Override
    public List<ExomeResponseDto> getExomeDtos() {
        return exomeMapper.toDtos(exomeRepository.findAll());
    }

    @Override
    public PageResponseDto getExomeDtosPaginated(int page, int size, String nameFilter) {
        Pageable paging = PageRequest.of(page, size);
        Page<ExomeEntity> currentPage;
        if (nameFilter.equals("")) {
            currentPage = exomeRepository.findAll(paging);
        } else {
            currentPage = exomeRepository.findAllByNameContainingIgnoreCase(nameFilter, paging);
        }
        return new PageResponseDto(currentPage);
    }

    @Override
    public File getExomeFile(long id) {
        ExomeEntity exomeEntity = exomeRepository.getOne(id);
        return new File(exomeEntity.getPath());
    }

    @Override
    public void deleteExome(long id) {
        exomeRepository.deleteById(id);
    }
}
