package com.fileorganizer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;

public class DuplicateFileHandler {

    private static Set<String> fileNames = new HashSet<>();

    public static void handleDuplicates(File file, String targetFolderPath) {
        String fileName = file.getName();
        if (fileNames.contains(fileName)) {
            moveToDuplicates(file, targetFolderPath);
        } else {
            fileNames.add(fileName);
        }
    }

    private static void moveToDuplicates(File file, String targetFolderPath) {
        try {
            File duplicateFolder = new File(targetFolderPath + "/Duplicates");
            if (!duplicateFolder.exists()) {
                duplicateFolder.mkdirs();
            }
            Files.move(file.toPath(), new File(duplicateFolder, file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved duplicate: " + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
