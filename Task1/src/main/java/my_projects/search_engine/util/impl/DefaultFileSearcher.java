package my_projects.search_engine.util.impl;

import my_projects.search_engine.util.Algorithm;
import my_projects.search_engine.util.FileReader;
import my_projects.search_engine.util.FileSearcher;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class DefaultFileSearcher implements FileSearcher {

    private FileReader defaultFileReader;
    private Algorithm algorithm;

    public DefaultFileSearcher(FileReader defaultFileReader, Algorithm algorithm) {
        this.defaultFileReader = defaultFileReader;
        this.algorithm = algorithm;
    }

    @Override
    public List<String> getListPath(String path, String pattern, String fileExtension, boolean caseSensitive) throws IOException {
        algorithm.createCmp(caseSensitive);
        MyFileVisitor fileVisitor = new MyFileVisitor(defaultFileReader, algorithm);
        fileVisitor.setFileExtension(fileExtension);
        fileVisitor.setPattern(pattern);
        fileVisitor.setRootPath(path);
        Files.walkFileTree(Paths.get(path), fileVisitor);
        return fileVisitor.getListPaths();
    }


    public FileReader getDefaultFileReader() {
        return defaultFileReader;
    }

    public void setDefaultFileReader(FileReader defaultFileReader) {
        this.defaultFileReader = defaultFileReader;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }
}
