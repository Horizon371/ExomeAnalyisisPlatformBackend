package com.example.ExomeAnalysisPlatform.helper.scripts;

import com.example.ExomeAnalysisPlatform.entity.GeneEntry;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static com.example.ExomeAnalysisPlatform.config.Config.*;

public class Clustering {
    public static void cluster(){
        Process process;
        try {
            String[] cmd = {
                    "python",
                    pythonBaseScriptPath + pythonScriptClustering,
            };
            ProcessBuilder processBuilder = new ProcessBuilder(cmd);
            processBuilder.redirectErrorStream(true);
            process = processBuilder.start();
            InputStream stdout = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
            String line = reader.readLine();
            System.out.println(line);
            while(reader.ready()){
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("Exception Raised" + e.toString());
        }
    }
}
