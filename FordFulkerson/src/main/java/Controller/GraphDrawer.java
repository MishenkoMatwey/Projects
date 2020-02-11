package Controller;

import Model.Graph;
import Model.Node;
import Ui.Graphicsview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.util.LinkedList;

public final class GraphDrawer extends JPanel {
    private Graph graph;
    private LinkedList<Graph> graphList; // list of graph States on each iteration of the algorithm
    private int iteration;
    private NodeDrawer nodeDrawer;
    private EdgeDrawer edgeDrawer;
    private Graphicsview frame;
    private final InitEdge initEdge = new InitEdge(this);

    public GraphDrawer(Graph _graph, Graphicsview frame) {
        graph = _graph;
        graphList = new LinkedList<>();
        iteration = 0;
        this.frame = frame;
        nodeDrawer = new NodeDrawer(graph);
        edgeDrawer = new EdgeDrawer(graph);

        MyMouse myMouse = new MyMouse();
        addMouseMotionListener(myMouse);
        addMouseListener(myMouse);
        addMouseWheelListener(myMouse);
    }

    Graph getGraph() {
        return graph;
    }

    public LinkedList<Graph> getGraphList() {
        return graphList;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public NodeDrawer getNodeDrawer() {
        return nodeDrawer;
    }

    public EdgeDrawer getEdgeDrawer() {
        return edgeDrawer;
    }

    public void setGraph(Graph _graph) {
        graph = _graph;
        nodeDrawer.setGraph(_graph);
        edgeDrawer.setGraph(_graph);
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        edgeDrawer.paint(graphics);
        nodeDrawer.paint(graphics);
    }

    /**
     * search node that is found in the specified coordinates
     */
    private Node searchNode(Point point) {
        for (Node elm : graph.getNodes()) {
            if (elm.movingVertices(point) != null)
                return elm;
        }
        return null;
    }


    private class MyMouse extends MouseAdapter implements MouseMotionListener {
        private Node node = null;
        @Override
        public void mousePressed(MouseEvent event) {
            if (event.getButton() == MouseEvent.BUTTON1)
                node = searchNode(event.getPoint());
        }

        @Override
        public void mouseClicked(MouseEvent event) {
            if (event.getButton() == MouseEvent.BUTTON3) {
                node = searchNode(event.getPoint());
                if (node == null) {
                    new InitNode(GraphDrawer.this, event);
                }
                if (event.getClickCount() > 1 && node != null) {
                    initEdge.addNodeToEdge(node);
                    if (graph.getEdges().size() != 0)
                        frame.startButton.setEnabled(true);
                }
            }
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            if (node != null) {
                node.setPosY(event.getY());
                node.setPosX(event.getX());
                repaint();
            }
        }


        @Override
        public void mouseWheelMoved(MouseWheelEvent event) {
            //Zoom in
            if (event.getWheelRotation() > 0) {
                for (Node node : graph.getNodes()) {
                    int vecX = node.getPosX() - event.getX();
                    int vecY = node.getPosY() - event.getY();
                    double vecSize = Math.sqrt((double) (Math.pow(vecX, 2) + Math.pow(vecY, 2)));
                    node.setPosX((int) (vecX + vecX / vecSize * 20 + event.getX()));
                    node.setPosY((int) (vecY + vecY / vecSize * 20 + event.getY()));
                    node.setDiameter((int) (node.getDiameter() + 1));

                }
                repaint();
            }
            //Zoom out
            if (event.getWheelRotation() < 0) {
                for (Node node : graph.getNodes()) {
                    if (node.getDiameter() > 5) {
                        int vecX = node.getPosX() - event.getX();
                        int vecY = node.getPosY() - event.getY();
                        double vecSize = Math.sqrt((double) (Math.pow(vecX, 2) + Math.pow(vecY, 2)));
                        node.setPosX((int) (vecX - vecX / vecSize * 20 + event.getX()));
                        node.setPosY((int) (vecY - vecY / vecSize * 20 + event.getY()));
                        node.setDiameter((int) (node.getDiameter() - 1));
                    }
                }
                repaint();
            }
        }
    }
}

