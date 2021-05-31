package com.example.ExomeAnalysisPlatform.helper.scripts;

import com.example.ExomeAnalysisPlatform.entity.GeneFrequencyEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.example.ExomeAnalysisPlatform.config.Config.pythonBaseScriptPath;
import static com.example.ExomeAnalysisPlatform.config.Config.pythonScriptGeneFrequency;

public class GeneFrequency {
    public static List<GeneFrequencyEntry> getGeneFrequency(String geneName) {
        List<GeneFrequencyEntry> entries = new ArrayList<>();
        Process process;
        try {
            String[] cmd = {
                    "python",
                    pythonBaseScriptPath + pythonScriptGeneFrequency,
                    geneName
            };
            ProcessBuilder processBuilder = new ProcessBuilder(cmd);
            processBuilder.redirectErrorStream(true);
            process = processBuilder.start();
            InputStream stdout = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
            entries = createFrequencyEntries(reader);
        } catch (Exception e) {
            System.out.println("Exception Raised" + e.toString());
        }
        return entries;
    }

    private static List<GeneFrequencyEntry> createFrequencyEntries(BufferedReader reader) throws IOException {
        List<GeneFrequencyEntry> entries = new ArrayList<>();
        String line = reader.readLine();
        while(reader.ready()){
            line = reader.readLine();
            String[] fields = line.split(" ");
            entries.add(new GeneFrequencyEntry(fields[0], fields[1], fields[2]));
        }
        return entries;
    }
}
