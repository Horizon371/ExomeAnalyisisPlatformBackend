package com.example.ExomeAnalysisPlatform.service;

import com.example.ExomeAnalysisPlatform.dto.response.IncidentialDiscoveriesData;
import com.example.ExomeAnalysisPlatform.entity.GeneFrequencyEntry;

import java.util.List;

public interface ExomeAnalysisService {
    String getCosanguinity(long id);

    IncidentialDiscoveriesData getIncidentialDiscoveries(long id);

    List<GeneFrequencyEntry> getGeneVariationFrequencies(String geneName);

    void cluster();
}
