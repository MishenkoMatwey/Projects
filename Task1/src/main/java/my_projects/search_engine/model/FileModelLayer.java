package my_projects.search_engine.model;

import my_projects.search_engine.util.FileSearcher;

import javax.swing.*;
import java.io.IOException;

public class FileModelLayer implements ModelLayer {
    private FileSearcher fileSearcher;

    public FileModelLayer(FileSearcher fileSearcher) {
        this.fileSearcher = fileSearcher;
    }

    @Override
    public JTree getFileTree(String path, String pattern, String fileExtension, boolean caseSensitive) throws IOException {
        MyTreeModel treeModel = new MyTreeModel(path);
        for (String element : fileSearcher.getListPath(path, pattern, fileExtension, caseSensitive)) {
            treeModel.addNodes(element.substring(1));
        }
        normalizeTree(treeModel.getRoot(), fileExtension);
        return new JTree(treeModel.getRoot());
    }

    private void normalizeTree(Node root, String fileExtension) {
        if (root.getChildrenMap().isEmpty())
            return;

        Node childNode = (Node) root.getChildrenMap().values().toArray()[0];

        if (root.getChildrenMap().size() <= 1 && !childNode.getName().contains(fileExtension)) {
            root.setName(root.getName() + "/" + childNode.getName());
            root.setNodePath(childNode.getNodePath());
            root.setChildrenMap(childNode.getChildrenMap());
            root.removeAllChildren();
            for (Node node : childNode.getChildrenMap().values()) {
                root.add(node);
            }
            normalizeTree(root, fileExtension);
        } else {
            for (Node node : root.getChildrenMap().values()) {
                normalizeTree(node, fileExtension);
            }
        }
    }

    public FileSearcher getFileSearcher() {
        return fileSearcher;
    }

    public void setFileSearcher(FileSearcher fileSearcher) {
        this.fileSearcher = fileSearcher;
    }

}
