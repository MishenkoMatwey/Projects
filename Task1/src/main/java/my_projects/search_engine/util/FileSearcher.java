package my_projects.search_engine.util;

import java.io.IOException;
import java.util.List;

public interface FileSearcher {
    List<String> getListPath(String path, String pattern, String fileExtension,boolean caseSensitive) throws IOException;
}
