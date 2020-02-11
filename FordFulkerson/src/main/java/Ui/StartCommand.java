package Ui;

import Controller.GraphDrawer;
import Model.FordFulkerson;
import Model.Graph;

import java.awt.event.ActionEvent;

class StartCommand implements ButtonCommand {
    private FordFulkerson algorithm;
    private Graphicsview frame;

    StartCommand(Graph graph, GraphDrawer drawer, Graphicsview frame) {
        algorithm = new FordFulkerson(graph, drawer, frame);
        this.frame = frame;

    }

    public void actionPerformed(ActionEvent e) {
        frame.getGoToStart().setEnabled(true);
        frame.getGoToEnd().setEnabled(true);
        frame.getNextButton().setEnabled(true);
        frame.getPrevButton().setEnabled(true);
        frame.startButton.setEnabled(false);
        algorithm.graphToMatrix();
        int max_flow = algorithm.maxFlow(algorithm.result, 0, algorithm.result.length - 1);
        frame.getjTextFieldMaxFlow().setText(String.valueOf(max_flow));
        algorithm.matrixToGraph();
        algorithm.getDrawer().repaint();
        algorithm.getDrawer().updateUI();
        frame.getCounter().setText("" + frame.getDrawer().getGraphList().size() + "/" + frame.getDrawer().getGraphList().size());
        frame.getLogString().chengeTextLog(frame.getDrawer().getGraphList().size()-1);

    }
}