package com.example.ExomeAnalysisPlatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneEntry {
    String geneName;
    String chromosome;
    String position;
}
