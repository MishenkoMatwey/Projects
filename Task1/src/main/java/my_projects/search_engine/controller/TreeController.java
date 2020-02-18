package my_projects.search_engine.controller;


import my_projects.search_engine.model.Node;
import my_projects.search_engine.util.FileReader;
import my_projects.search_engine.view.MainView;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.io.IOException;
import java.nio.file.Paths;


public class TreeController implements TreeSelectionListener {

    private MainView mainView;
    private FileReader fileReader;
    private String rootPath;


    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public TreeController(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void valueChanged(TreeSelectionEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        TreePath path = event.getPath();
        if (path.getLastPathComponent() instanceof Node) {
            Node node = (Node) path.getLastPathComponent();
            stringBuilder.append(rootPath).append("/").append(node.getNodePath());
        }
        if (stringBuilder.length() > 0) {
            try {
                mainView.getPanelForText().getTextArea().setText(fileReader.reedFile(Paths.get(stringBuilder.toString())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
