package my_projects.web_tree.model;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FileModelLayer {
    private StringBuilder listContiguity=new StringBuilder();
    public Tree createTree(String fileText) throws IOException {
        if (fileText.isEmpty()) {
            return null;
        }

        Map<String, Node> allNodes = new HashMap<>();
        Tree tree = new Tree();

        List<String> listContiguity = new ArrayList<>(Arrays.asList(fileText.split("\n")));
        String nameRootNode = listContiguity.get(0).split(" ")[0];
        for (String lineContiguity : listContiguity) {
            String name = lineContiguity.split(" ")[0];
            Node node = new Node(name, null);
            allNodes.put(name, node);
        }

        for (String lineContiguity : listContiguity) {
            String[] parseLineNode = lineContiguity.split(" ");
            for (int i = 1; i < parseLineNode.length; i++) {
                Node node;
                if (allNodes.get(parseLineNode[i]) == null) {
                    node = new Node(parseLineNode[i], null);
                } else {
                    node = allNodes.get(parseLineNode[i]);
                }
                node.setParentNode(allNodes.get(parseLineNode[0]));
                allNodes.get(parseLineNode[0]).getChildrenMap().put(node.getIdForHtml(), node);
            }
        }
        tree.setRoot(allNodes.get(nameRootNode));
        return tree;
    }

    public String covertTreeToListContiguity(Node root){
        if(!root.getChildrenMap().isEmpty()){
            listContiguity.append(root.getName()).append(" ");
            listContiguity.append(root.getChildrenMap().values().stream().map(Node::getName).collect(Collectors.joining(" ")));
            listContiguity.append("\n");
            for (Node node:root.getChildrenMap().values()){
                covertTreeToListContiguity(node);
            }
        }
        return listContiguity.toString();
    }

}
