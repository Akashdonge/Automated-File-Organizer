package com.fileorganizer;

import java.io.File;
import java.util.HashMap;

public class FileOrganizer {
    public static void main(String[] args) {
        String folderPath = "C:\\Users\\YourUsername\\Downloads"; // Set the target folder
        File folder = new File(folderPath);
        
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid folder path.");
            return;
        }
        
        HashMap<String, String[]> categories = FileUtils.loadCategoriesFromConfig();
        FileUtils.organizeFiles(folder, categories);
    }
}
