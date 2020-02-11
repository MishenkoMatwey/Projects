package Ui;

import Controller.GraphDrawer;

import java.awt.event.ActionEvent;

class PrevCommand implements ButtonCommand {
    private GraphDrawer drawer;
    private Graphicsview frame;

    PrevCommand(GraphDrawer drawer, Graphicsview frame) {
        this.drawer = drawer;
        this.frame = frame;
    }


    public void actionPerformed(ActionEvent e) {
        if (!drawer.getGraphList().isEmpty()) {
            if (drawer.getIteration() - 1 >= 0) // меняем индекс состояния в списке
                drawer.setIteration(drawer.getIteration()-1);

            drawer.setGraph(drawer.getGraphList().get(drawer.getIteration()));
            drawer.repaint();

            frame.getCounter().setText("" + (drawer.getIteration() + 1) + "/" + drawer.getGraphList().size());
            frame.getLogString().chengeTextLog(drawer.getIteration());


            drawer.setGraph(drawer.getGraphList().get(drawer.getIteration()));
            drawer.repaint();
        }
    }
}
