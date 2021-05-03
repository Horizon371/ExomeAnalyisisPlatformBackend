package com.example.ExomeAnalysisPlatform.helper.scripts;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.example.ExomeAnalysisPlatform.config.Config.*;

public class Cosangunity {
    public static String getCosangunity(String savedFileName) {
        String cosangunity = "0";
        Process process;
        try {
            String[] cmd = {
                    "python",
                    pythonBaseScriptPath + pythonScriptCosangunity,
                    savedFileName
            };
            ProcessBuilder processBuilder = new ProcessBuilder(cmd);
            processBuilder.redirectErrorStream(true);
            process = processBuilder.start();
            InputStream stdout = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
            cosangunity = reader.readLine();
        } catch (Exception e) {
            System.out.println("Exception Raised" + e.toString());
        }
        return cosangunity;
    }
}
