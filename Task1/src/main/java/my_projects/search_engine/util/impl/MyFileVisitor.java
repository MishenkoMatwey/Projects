package my_projects.search_engine.util.impl;

import my_projects.search_engine.util.Algorithm;
import my_projects.search_engine.util.FileReader;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;


final public class MyFileVisitor extends SimpleFileVisitor<Path> {

    private List<String> listPath = new ArrayList<>();
    private String pattern;
    private String fileExtension;
    private FileReader fileReader;
    private Algorithm algorithm;
    private String rootPath;

    MyFileVisitor(FileReader defaultFileReader, Algorithm algorithm) {
        this.fileReader = defaultFileReader;
        this.algorithm = algorithm;
    }


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (getFileExtension(file).equals(this.fileExtension)) {
            String text = fileReader.reedFile(file);
            boolean isSubStr = algorithm.findSubStr(pattern, text);
            if (isSubStr) {
                listPath.add(file.toString().substring(rootPath.length()));
            }
        }
        return FileVisitResult.CONTINUE;
    }


    private String getFileExtension(Path file) {
        String fileName = file.getFileName().toString();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return "." + fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";
    }

    void setPattern(String pattern) {
        this.pattern = pattern;
    }

    void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    List<String> getListPaths() {
        return listPath;
    }

}
