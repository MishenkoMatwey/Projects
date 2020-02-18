package my_projects.search_engine.view;

import my_projects.search_engine.controller.TreeController;

import javax.swing.*;
import java.awt.*;


public class PanelForTree extends JPanel{
    private JTree fileTree;
    private TreeController controller;

    public void updateFileTree(JTree fileTree,String rootPath) {
        this.fileTree = fileTree;
        removeAll();
        fileTree.setShowsRootHandles(true);
        fileTree.setRootVisible(false);
        controller.setRootPath(rootPath);
        fileTree.getSelectionModel().addTreeSelectionListener(controller);
        add(new JScrollPane(fileTree),BorderLayout.CENTER);
        revalidate();
    }

    public JTree getFileTree() {
        return fileTree;
    }

    PanelForTree(TreeController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        add(new JScrollPane(fileTree),BorderLayout.CENTER);
    }
}
