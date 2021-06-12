package com.example.ExomeAnalysisPlatform.service;

import com.example.ExomeAnalysisPlatform.dto.response.IncidentialDiscoveriesData;
import com.example.ExomeAnalysisPlatform.entity.ExomeEntity;
import com.example.ExomeAnalysisPlatform.entity.GeneEntry;
import com.example.ExomeAnalysisPlatform.entity.GeneFrequencyEntry;
import com.example.ExomeAnalysisPlatform.helper.file.utils.FileUtils;
import com.example.ExomeAnalysisPlatform.helper.scripts.Clustering;
import com.example.ExomeAnalysisPlatform.helper.scripts.Cosangunity;
import com.example.ExomeAnalysisPlatform.helper.scripts.GeneFrequency;
import com.example.ExomeAnalysisPlatform.helper.scripts.IncidentialDiscoveries;
import com.example.ExomeAnalysisPlatform.repository.ExomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class ExomeAnalysisServiceImpl implements ExomeAnalysisService{

    private final ExomeRepository exomeRepository;

    @Override
    public void cluster(){
        Clustering.cluster();
    }

    @Override
    public String getCosanguinity(long id) {
        ExomeEntity exomeEntity = exomeRepository.getOne(id);
        String fileName = FileUtils.getFileName(exomeEntity.getName());
        return Cosangunity.getCosangunity(fileName);
    }

    @Override
    public IncidentialDiscoveriesData getIncidentialDiscoveries(long id) {
        ExomeEntity exomeEntity = exomeRepository.getOne(id);
        String fileName = FileUtils.getFileName(exomeEntity.getName());
        List<GeneEntry> geneEntries = IncidentialDiscoveries.getIncidentialDiscoveries(fileName);
        List<String> geneNames = getGeneNames(geneEntries);
        List<String> chromosomes = getChromosomes(geneEntries);
        return new IncidentialDiscoveriesData(geneEntries, geneNames, chromosomes);
    }

    @Override
    public List<GeneFrequencyEntry> getGeneVariationFrequencies(String geneName) {
        List<GeneFrequencyEntry> geneFrequencies =  GeneFrequency.getGeneFrequency(geneName);
//        geneFrequencies.sort(Comparator.comparing(GeneFrequencyEntry::getFrequency).reversed());
        return geneFrequencies;
    }

    private List<String> getGeneNames(List<GeneEntry> geneEntries){
        List<String> geneNames = new ArrayList<>();
        for(GeneEntry geneEntry : geneEntries){
            if(!geneNames.contains(geneEntry.getGeneName())){
                geneNames.add(geneEntry.getGeneName());
            }
        }
        return geneNames;
    }

    private List<String> getChromosomes(List<GeneEntry> geneEntries){
        List<String> chromosomes = new ArrayList<>();
        for(GeneEntry geneEntry : geneEntries){
            if(!chromosomes.contains(geneEntry.getChromosome())){
                chromosomes.add(geneEntry.getChromosome());
            }
        }
        return chromosomes;
    }
}
