package com.fileorganizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileUtils {

    public static void organizeFiles(File folder, HashMap<String, String[]> categories) {
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("No files to organize.");
            return;
        }
        
        for (File file : files) {
            if (file.isFile()) {
                String fileType = getFileExtension(file.getName());
                String targetFolder = getTargetFolder(fileType, categories);
                if (targetFolder != null) {
                    moveFile(file, folder.getPath() + File.separator + targetFolder);
                }
            }
        }
    }

    private static String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        return (index > 0) ? fileName.substring(index).toLowerCase() : "";
    }

    private static String getTargetFolder(String fileType, HashMap<String, String[]> categories) {
        for (String category : categories.keySet()) {
            for (String extension : categories.get(category)) {
                if (fileType.equals(extension)) {
                    return category;
                }
            }
        }
        return "Miscellaneous"; // Default category
    }

    private static void moveFile(File file, String targetFolderPath) {
        try {
            File targetFolder = new File(targetFolderPath);
            if (!targetFolder.exists()) {
                targetFolder.mkdirs();
            }
            Path targetPath = Path.of(targetFolderPath, file.getName());
            Files.move(file.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved: " + file.getName() + " -> " + targetFolderPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, String[]> loadCategoriesFromConfig() {
        HashMap<String, String[]> categories = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            categories = mapper.readValue(new File("src/resources/config.json"), HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
