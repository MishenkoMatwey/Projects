package my_projects.web_tree.model;


import java.io.Serializable;

public class Tree implements Serializable {
    private Node root;
    private Node searchNode;


    private void findNode(Node root, String idNodeForHtml) {
        if(root.getIdForHtml().equals(idNodeForHtml)){
            this.searchNode=root;
            return;
        }
        if (root.getChildrenMap()==null ||root.getChildrenMap().isEmpty() || this.searchNode != null)
            return;
        if (root.getChildrenMap().get(idNodeForHtml) != null) {
            this.searchNode = root.getChildrenMap().get(idNodeForHtml);
        } else {
            for (Node iterNode : root.getChildrenMap().values())
                findNode(iterNode, idNodeForHtml);
        }
    }

    public Node findNode(String idNodeForHtml) {
        searchNode = null;
        findNode(root, idNodeForHtml);
        if (searchNode != null) {
           return searchNode;
        }
        return null;
    }

    public void addNode(String idParentNode, String nameNodeForAdd) {
        searchNode = null;
        findNode(root, idParentNode);
        if (searchNode != null) {
            Node node=new Node(nameNodeForAdd,searchNode);
            searchNode.getChildrenMap().put(node.getIdForHtml(), node);
        }
    }

    public void addNode(String idParentNode, Node node) {
        searchNode = null;
        findNode(root, idParentNode);
        if (searchNode != null) {
            searchNode.getChildrenMap().put(node.getIdForHtml(), node);
            node.setParentNode(searchNode);
        }
    }
    public void deleteSubTree(String idNodeForHtml) {
        searchNode = null;
        findNode(root, idNodeForHtml);
        if(searchNode.getIdForHtml().equals(root.getIdForHtml())){
            return;
        }
        if (searchNode != null) {
            searchNode.getParentNode().getChildrenMap().remove(idNodeForHtml);
        }
    }

    public void deleteNode(String idNodeForHtml) {
        searchNode = null;
        findNode(root, idNodeForHtml);
        if(searchNode.getIdForHtml().equals(root.getIdForHtml())){
            searchNode=null;
            root=null;
        }
        if (searchNode != null) {
            searchNode.getChildrenMap().values().forEach(node -> node.setParentNode(searchNode.getParentNode()));
            searchNode.getParentNode().getChildrenMap().putAll(searchNode.getChildrenMap());
            searchNode.getParentNode().getChildrenMap().remove(idNodeForHtml);
        }
    }
    public void changeNode(String idNodeForHtml,String newName) {
        searchNode = null;
        findNode(root, idNodeForHtml);
        if (searchNode != null) {
            searchNode.setName(newName);
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

}
