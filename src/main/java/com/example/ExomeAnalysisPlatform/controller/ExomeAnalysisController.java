package com.example.ExomeAnalysisPlatform.controller;

import com.example.ExomeAnalysisPlatform.entity.GeneEntry;
import com.example.ExomeAnalysisPlatform.service.ExomeAnalysisService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<GeneEntry>> incidentialDiscoveries(@RequestBody long id){
        List<GeneEntry> geneEntries = exomeAnalysisService.getIncidentialDiscoveries(id);
        return new ResponseEntity<>(geneEntries, HttpStatus.OK);
    }
}
