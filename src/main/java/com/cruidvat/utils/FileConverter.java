package com.cruidvat.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileConverter {

    static Logger log = LogManager.getLogger(FileConverter.class);

    public static String fileToString(File file) {
        String text = null;
        try {
            text = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("Failed to convert file to String", e);
        }
        return text;
    }

    public static String fileToString(String path) {
        String text = null;
        try {
            text = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("Failed to convert file to String", e);
        }
        return text;
    }

}
