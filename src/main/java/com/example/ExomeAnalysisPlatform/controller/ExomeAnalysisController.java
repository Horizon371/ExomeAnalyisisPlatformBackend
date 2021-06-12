package com.example.ExomeAnalysisPlatform.controller;

import com.example.ExomeAnalysisPlatform.dto.response.IncidentialDiscoveriesData;
import com.example.ExomeAnalysisPlatform.entity.GeneFrequencyEntry;
import com.example.ExomeAnalysisPlatform.service.ExomeAnalysisService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/analysis")
@AllArgsConstructor
public class ExomeAnalysisController {

    ExomeAnalysisService exomeAnalysisService;

    @PostMapping("/cosanguinity")
    public ResponseEntity<String> cosanguinity(@RequestBody long id){
        String cosanguinity = exomeAnalysisService.getCosanguinity(id);
        return new ResponseEntity<>(cosanguinity, HttpStatus.OK);
    }

    @PostMapping("/incidential")
    public ResponseEntity<IncidentialDiscoveriesData> incidentialDiscoveries(@RequestBody long id){
        IncidentialDiscoveriesData incidentialDiscoveries = exomeAnalysisService.getIncidentialDiscoveries(id);
        return new ResponseEntity<>(incidentialDiscoveries, HttpStatus.OK);
    }

    @PostMapping("/frequency")
    public ResponseEntity<List<GeneFrequencyEntry>> geneFrequenciesForGeneVariations(@RequestBody String geneName){
        List<GeneFrequencyEntry> frequencies = exomeAnalysisService.getGeneVariationFrequencies(geneName);
        return new ResponseEntity<>(frequencies, HttpStatus.OK);
    }

    @PostMapping(value = "/clusteringImg")
    public void image(@RequestBody String something, HttpServletResponse response) throws IOException {
        File file = new File("D:\\Projects\\Licenta\\Exome Analysis Project\\saved clustering plots\\clusters1.png");
        response.setHeader("Content-Disposition", "filename=" + "clusters1.png");
        response.getOutputStream().write(Files.readAllBytes(file.toPath()));
    }

    @PostMapping("/cluster")
    public ResponseEntity<String> cluster(@RequestBody String something){
        exomeAnalysisService.cluster();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
