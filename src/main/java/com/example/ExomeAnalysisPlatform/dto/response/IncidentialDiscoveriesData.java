package com.example.ExomeAnalysisPlatform.dto.response;

import com.example.ExomeAnalysisPlatform.entity.GeneEntry;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class IncidentialDiscoveriesData {
    List<GeneEntry> geneEntries;
    List<String> geneNames;
    List<String> chromosomes;
}
