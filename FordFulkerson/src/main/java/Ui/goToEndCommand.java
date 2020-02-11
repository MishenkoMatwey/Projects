package Ui;

import Controller.GraphDrawer;

import java.awt.event.ActionEvent;

class goToEndCommand implements ButtonCommand {

    private GraphDrawer drawer;
    private Graphicsview frame;

    goToEndCommand(GraphDrawer drawer, Graphicsview frame) {
        this.drawer = drawer;
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        if (!drawer.getGraphList().isEmpty()) {

            drawer.setGraph(drawer.getGraphList().getLast());
            drawer.setIteration(drawer.getGraphList().size() - 1);
            drawer.repaint();

            frame.getCounter().setText("" + drawer.getGraphList().size() + "/" + drawer.getGraphList().size());
            frame.getLogString().chengeTextLog(drawer.getGraphList().size()-1);
        }
    }
}