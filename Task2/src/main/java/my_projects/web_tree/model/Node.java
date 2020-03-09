package my_projects.web_tree.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Node implements Serializable {
    private static int id;
    private String name;
    private String idForHtml;
    private Map<String,Node> childrenMap;
    private Node parentNode;

    public Node(String name,Node parentNode) {
        this.name = name;
        childrenMap=new HashMap<>();
        idForHtml=String.valueOf(id);
        this.parentNode=parentNode;
        id++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdForHtml() {
        return idForHtml;
    }

    public void setIdForHtml(String idForHtml) {
        this.idForHtml = idForHtml;
    }

    public Map<String, Node> getChildrenMap() {
        return childrenMap;
    }

    public void setChildrenMap(Map<String, Node> childrenMap) {
        this.childrenMap = childrenMap;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }
}
