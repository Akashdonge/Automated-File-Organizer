package com.fileorganizer;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileCompressor {

    public static void compressFile(File file, String zipFilePath) {
        try (OutputStream out = new FileOutputStream(zipFilePath);
             ZipArchiveOutputStream zipOut = new ZipArchiveOutputStream(out)) {
            ZipArchiveEntry entry = new ZipArchiveEntry(file.getName());
            zipOut.putArchiveEntry(entry);
            try (FileInputStream fileIn = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fileIn.read(buffer)) > 0) {
                    zipOut.write(buffer, 0, len);
                }
            }
            zipOut.closeArchiveEntry();
            zipOut.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
