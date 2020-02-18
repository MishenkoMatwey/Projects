package my_projects.search_engine.model;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Map;

public class Node extends DefaultMutableTreeNode {

    private String nodePath;
    private String name;
    private Map<String, Node> childrenMap;

    Node(String path, String name, Map<String, Node> childrenMap) {
        this.nodePath = path;
        this.name = name;
        this.childrenMap = childrenMap;
    }


    public String getNodePath() {
        return nodePath;
    }

    @Override
    public String toString() {
        return name;
    }

    void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    Map<String, Node> getChildrenMap() {
        return childrenMap;
    }

    void setChildrenMap(Map<String, Node> childrenMap) {
        this.childrenMap = childrenMap;
    }

}