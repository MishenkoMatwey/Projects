package my_projects.search_engine.model;

import javax.swing.*;
import java.io.IOException;


public interface ModelLayer {
    JTree getFileTree(String path, String pattern, String fileExtension, boolean caseSensitive) throws IOException;
}
