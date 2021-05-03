package com.example.ExomeAnalysisPlatform.service;

import com.example.ExomeAnalysisPlatform.entity.GeneEntry;

import java.util.List;

public interface ExomeAnalysisService {
    String getCosanguinity(long id);

    List<GeneEntry> getIncidentialDiscoveries(long id);
}
