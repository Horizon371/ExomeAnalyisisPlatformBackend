package com.example.ExomeAnalysisPlatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneFrequencyEntry {
    String position;
    String chromosome;
    String frequency;
}
