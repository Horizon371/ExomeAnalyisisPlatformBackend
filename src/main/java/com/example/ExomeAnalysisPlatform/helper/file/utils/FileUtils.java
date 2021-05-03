package com.example.ExomeAnalysisPlatform.helper.file.utils;

import java.io.File;

public class FileUtils {
    public static String getUniqueFileName(String fileName) {
        File file = new File(fileName);
        int version = 0;
        while (file.exists()) {
            version++;
            String fileNameBase = fileName.substring(0, fileName.lastIndexOf('.'));
            String extension = fileName.substring(fileName.lastIndexOf('.'));
            file = new File(fileNameBase + "(" + version + ")" + extension);
        }
        return file.getAbsolutePath();
    }

    public static String getFileName(String fileName) {
        return fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.lastIndexOf('.'));
    }

    public static String deleteFile(String fileName){
        File file = new File(fileName);
        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
        return fileName;
    }
}
