package Model;


import java.awt.*;

public final class Edge {
    private Color color = Color.BLACK;
    private Node startNode;
    private Node endNode;
    private Integer weight = 0;
    private Integer bandwidth = 0;

    public Edge(Node startNode,Node endNode, int weight, int bandwidth) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
        this.bandwidth = bandwidth;
        startNode.addListEdge(this);
        endNode.addListEdge(this);
    }

    Edge(Edge edge) {
        this.startNode = edge.startNode;
        this.endNode = edge.endNode;
        this.weight = edge.weight;
        this.bandwidth = edge.bandwidth;
        startNode.addListEdge(this);
        endNode.addListEdge(this);
        this.color = Color.BLACK;
    }

    void changeColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

}
