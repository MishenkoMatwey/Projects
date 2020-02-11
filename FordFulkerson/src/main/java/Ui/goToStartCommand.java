package Ui;

import Controller.GraphDrawer;

import java.awt.event.ActionEvent;

class goToStartCommand implements ButtonCommand {

    private GraphDrawer drawer;
    private Graphicsview frame;

    goToStartCommand(GraphDrawer drawer, Graphicsview frame) {
        this.drawer = drawer;
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        if (!drawer.getGraphList().isEmpty()) {
            drawer.setGraph(drawer.getGraphList().getFirst());

            drawer.setIteration(0);
            drawer.repaint();


            frame.getCounter().setText("" + 1 + "/" + drawer.getGraphList().size());
            frame.getLogString().chengeTextLog(0);

            drawer.repaint();
        }
    }
}