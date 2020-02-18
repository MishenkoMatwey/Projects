package my_projects.search_engine.controller;


import my_projects.search_engine.model.ModelLayer;
import my_projects.search_engine.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SearchController implements ActionListener {
    private MainView mainView;
    private String path;
    private ModelLayer fileModelLayer;

    public SearchController(ModelLayer fileModelLayer) {
        this.fileModelLayer = fileModelLayer;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (((JButton) e.getSource()).getName()) {
            case ("buttonChooseDir"): {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.showOpenDialog(mainView.getBottomJPanel());
                if (fileChooser.getSelectedFile() != null) {
                    mainView.getBottomJPanel().getPathForDirectories().setText(fileChooser.getSelectedFile().getName());
                    setPath(fileChooser.getSelectedFile().getPath());
                }
                break;
            }
            case ("buttonFindFiles"): {
                if (!mainView.getBottomJPanel().getPattern().getText().isEmpty()
                        && !mainView.getBottomJPanel().getExtension().getText().isEmpty()
                        && !mainView.getBottomJPanel().getPathForDirectories().getText().isEmpty()) {
                    try {
                        String pattern = mainView.getBottomJPanel().getPattern().getText();
                        String extension = mainView.getBottomJPanel().getExtension().getText();
                        boolean caseSensitive = mainView.getBottomJPanel().getButtonCheckRegister().isSelected();
                        JTree newFileTree = fileModelLayer.getFileTree(path, pattern, extension, caseSensitive);
                        mainView.getPanelForTree().updateFileTree(newFileTree, path);
                        mainView.getPanelForText().getTextArea().setText("");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            }
            default:
                break;
        }
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
