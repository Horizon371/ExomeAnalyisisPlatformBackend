package com.example.ExomeAnalysisPlatform.helper.scripts;

import com.example.ExomeAnalysisPlatform.entity.GeneEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.example.ExomeAnalysisPlatform.config.Config.*;

public class IncidentialDiscoveries {
    public static List<GeneEntry> getIncidentialDiscoveries(String fileName) {
        List<GeneEntry> entries = new ArrayList<>();
        Process process;
        try {
            String[] cmd = {
                    "python",
                    pythonBaseScriptPath + pythonScriptIncidentialDiscoveries,
                    fileName
            };
            ProcessBuilder processBuilder = new ProcessBuilder(cmd);
            processBuilder.redirectErrorStream(true);
            process = processBuilder.start();
            InputStream stdout = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
            entries = createGeneEntries(reader);
        } catch (Exception e) {
            System.out.println("Exception Raised" + e.toString());
        }
        return entries;
    }

    private static List<GeneEntry> createGeneEntries(BufferedReader reader) throws IOException {
        List<GeneEntry> entries = new ArrayList<>();
        String line = reader.readLine();
        while(reader.ready()){
            line = reader.readLine();
            String[] fields = line.split("\\s+");
            entries.add(new GeneEntry(fields[1], fields[2], fields[3]));
        }
        return entries;
    }
}
