package com.example.ExomeAnalysisPlatform.helper.file.converter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static com.example.ExomeAnalysisPlatform.config.Config.pythonBaseScriptPath;
import static com.example.ExomeAnalysisPlatform.config.Config.pythonScriptSaveXlsAsCSV;

public class FileConverter {
    public static void saveFileCSVCopy(String savedFileName) {
        Process process;
        try {
            String[] cmd = {
                    "python",
                    pythonBaseScriptPath + pythonScriptSaveXlsAsCSV,
                    savedFileName
            };
            ProcessBuilder processBuilder = new ProcessBuilder(cmd);
            processBuilder.redirectErrorStream(true);
            process = processBuilder.start();
            InputStream stdout = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Exception Raised" + e.toString());
        }
    }
}
