package my_projects.search_engine.util;

import java.io.IOException;
import java.nio.file.Path;

public interface FileReader {
    String reedFile(Path file) throws IOException;
}
