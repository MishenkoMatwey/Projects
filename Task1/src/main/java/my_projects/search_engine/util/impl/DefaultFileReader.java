package my_projects.search_engine.util.impl;

import my_projects.search_engine.util.FileReader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class DefaultFileReader implements FileReader {

    @Override
    public String reedFile(Path filePath) throws IOException {
        if (Files.isDirectory(filePath))
            return "";
        byte[] text = Files.readAllBytes(filePath);
        return new String(text, StandardCharsets.UTF_8);
    }
}
