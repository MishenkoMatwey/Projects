package Model;

import java.util.LinkedList;

public class Graph {


    private LinkedList <Node> nodes;
    private LinkedList <Edge> edges;

    public Graph(){
        nodes = new LinkedList<Node>();
        edges = new LinkedList<Edge>();
    }

    Graph(Graph graph){
        this();
        this.nodes.addAll(graph.nodes);
        for (Edge edge : graph.edges) {
            this.edges.add(new Edge(edge));
        }
    }

    public void addNode(Node _node){
        nodes.add(_node);
    }

    public void addEdge(Edge _edge){
        edges.add(_edge);
    }

    void deleteNode(Node node){
        for (Edge edge:node.getListEdge()){
            deleteEdge(edge);
        }
        nodes.remove(node);
    }

    void deleteEdge(Edge edge) {
        edges.remove(edge);
    }

    public  LinkedList<Node> getNodes(){
        return nodes;
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }


    public void Clear(){
        nodes.clear();
        edges.clear();
    }
}
