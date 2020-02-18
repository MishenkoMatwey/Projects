package my_projects.search_engine.model;

import java.util.*;


class MyTreeModel {
    private Node root;

    MyTreeModel(String rootPath) {
        this.root = new Node(rootPath, "", new HashMap<>());
    }

    Node getRoot() {
        return root;
    }

    void addNodes(String absolutePath) {
        if (absolutePath.isEmpty())
            return;

        StringBuilder saveCurrentPath = new StringBuilder();
        Node nodeForIteration = root;

        for (String directory : absolutePath.split("/")) {
            saveCurrentPath.append(directory);
            if (nodeForIteration.getChildrenMap().get(directory) == null) {
                Node nodeForAdd = new Node(saveCurrentPath.toString(), directory, new HashMap<>());
                nodeForIteration.getChildrenMap().put(directory, nodeForAdd);
                nodeForIteration.add(nodeForAdd);
            }
            nodeForIteration = nodeForIteration.getChildrenMap().get(directory);
            saveCurrentPath.append("/");
        }

    }


}
