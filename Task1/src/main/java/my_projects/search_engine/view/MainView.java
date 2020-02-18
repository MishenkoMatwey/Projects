package my_projects.search_engine.view;

import my_projects.search_engine.controller.NavigationController;
import my_projects.search_engine.controller.SearchController;
import my_projects.search_engine.controller.TreeController;

import javax.swing.*;
import java.awt.*;

public class MainView {
    private final JFrame jFrame = new JFrame();
    private final int width;
    private final int height;
    private final BottomJPanel bottomJPanel;//to enter directory,file extension and pattern
    private final RightJPanel rightJPanel; //for navigation  to navigate through text
    private final PanelForTree panelForTree;//file system tree
    private final PanelForText panelForText;//text file

    private NavigationController controllerForNavigation;
    private SearchController controllerForSearch;
    private TreeController treeController;





    public MainView(int width, int height, NavigationController navigationController,
                    SearchController searchController, TreeController treeController) {

        controllerForNavigation=navigationController;
        controllerForSearch=searchController;
        this.treeController=treeController;
        controllerForSearch.setMainView(this);
        controllerForNavigation.setMainView(this);
        this.treeController.setMainView(this);
        this.width = width;
        this.height = height;
        this.bottomJPanel = new BottomJPanel(controllerForSearch);
        this.rightJPanel =  new RightJPanel(controllerForNavigation);
        this.panelForTree = new PanelForTree(treeController);
        this.panelForText = new PanelForText();
        assembleMainView();
    }

    private void assembleMainView() {
        /*
        settings for MainView
         */
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width / 2 - width / 2, dimension.height / 2 - height / 2, width, height);
        jFrame.setLayout(new BorderLayout());
        /*
           add left,top panel;
         */
        jFrame.add(bottomJPanel, BorderLayout.AFTER_LAST_LINE);
        jFrame.add(rightJPanel, BorderLayout.EAST);


        JSplitPane splitVertical = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);

        splitVertical.setTopComponent(panelForTree);
        splitVertical.setBottomComponent(panelForText);
        splitVertical.setDividerLocation(200);
        jFrame.add(splitVertical, BorderLayout.CENTER);
    }

    public BottomJPanel getBottomJPanel() {
        return bottomJPanel;
    }

    public RightJPanel getRightJPanel() {
        return rightJPanel;
    }

    public PanelForTree getPanelForTree() {
        return panelForTree;
    }

    public PanelForText getPanelForText() {
        return panelForText;
    }

    public NavigationController getControllerForNavigation() {
        return controllerForNavigation;
    }

    public void setControllerForNavigation(NavigationController controllerForNavigation) {
        this.controllerForNavigation = controllerForNavigation;
    }

    public SearchController getControllerForSearch() {
        return controllerForSearch;
    }

    public void setControllerForSearch(SearchController controllerForSearch) {
        this.controllerForSearch = controllerForSearch;
    }

    public TreeController getTreeController() {
        return treeController;
    }

    public void setTreeController(TreeController treeController) {
        this.treeController = treeController;
    }

}
