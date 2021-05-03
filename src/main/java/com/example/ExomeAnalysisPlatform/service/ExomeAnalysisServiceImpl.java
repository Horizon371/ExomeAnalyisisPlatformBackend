package com.example.ExomeAnalysisPlatform.service;

import com.example.ExomeAnalysisPlatform.entity.ExomeEntity;
import com.example.ExomeAnalysisPlatform.entity.GeneEntry;
import com.example.ExomeAnalysisPlatform.helper.file.utils.FileUtils;
import com.example.ExomeAnalysisPlatform.helper.scripts.Cosangunity;
import com.example.ExomeAnalysisPlatform.helper.scripts.IncidentialDiscoveries;
import com.example.ExomeAnalysisPlatform.repository.ExomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExomeAnalysisServiceImpl implements ExomeAnalysisService{

    private final ExomeRepository exomeRepository;

    @Override
    public String getCosanguinity(long id) {
        ExomeEntity exomeEntity = exomeRepository.getOne(id);
        String fileName = FileUtils.getFileName(exomeEntity.getName());
        return Cosangunity.getCosangunity(fileName);
    }

    @Override
    public List<GeneEntry> getIncidentialDiscoveries(long id) {
        ExomeEntity exomeEntity = exomeRepository.getOne(id);
        String fileName = FileUtils.getFileName(exomeEntity.getName());
        return IncidentialDiscoveries.getIncidentialDiscoveries(fileName);
    }
}
