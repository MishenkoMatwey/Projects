package Model;

import org.junit.Assert;
import org.junit.Test;

public class GraphTest {
    private Graph graph = new Graph();
    private Node node1 = new Node("name1", 50, 70);
    private Node node2 = new Node("name2", 70, 50);
    private int edges_count = 0, nodes_count = 0;


    @Test
    public void addNode() {
        nodes_count = graph.getNodes().size();
        graph.addNode(node1);
        Assert.assertEquals(graph.getNodes().size(), nodes_count + 1);
    }

    @Test
    public void addEdge() {
        edges_count = graph.getEdges().size();
        Edge edge = new Edge(node1, node2, 0, 15);
        graph.addEdge(edge);
        Assert.assertEquals(graph.getEdges().size(), edges_count + 1);
    }

    @Test
    public void deleteNode() {
        graph.addNode(node1);
        nodes_count = graph.getNodes().size();
        graph.deleteNode(node1);
        Assert.assertEquals(graph.getNodes().size(), nodes_count - 1);
    }

    @Test public void deleteEdge() {
        edges_count = graph.getEdges().size();
        Edge edge = new Edge(node1, node2, 0, 15);
        graph.addEdge(edge);
        edges_count++;
        graph.deleteEdge(edge);
        Assert.assertEquals(graph.getEdges().size(), edges_count - 1);
    }
}