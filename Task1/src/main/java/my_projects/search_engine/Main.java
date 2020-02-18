package my_projects.search_engine;

import my_projects.search_engine.controller.NavigationController;
import my_projects.search_engine.controller.SearchController;
import my_projects.search_engine.controller.TreeController;
import my_projects.search_engine.model.FileModelLayer;
import my_projects.search_engine.util.Algorithm;
import my_projects.search_engine.util.FileReader;
import my_projects.search_engine.util.FileSearcher;
import my_projects.search_engine.util.impl.DefaultFileReader;
import my_projects.search_engine.util.impl.DefaultFileSearcher;
import my_projects.search_engine.util.impl.KnuthMorrisPratt;
import my_projects.search_engine.view.*;

public class Main {

    public static void main(String[] args){
        FileReader fileReader=new DefaultFileReader();
        Algorithm algorithm=new KnuthMorrisPratt();
        FileSearcher fileSearcher=new DefaultFileSearcher(fileReader,algorithm);
        FileModelLayer modelLayer=new FileModelLayer(fileSearcher);

        NavigationController navigationController=new NavigationController();
        SearchController searchController=new SearchController(modelLayer);
        TreeController treeController=new TreeController(fileReader);

        MainView mainView = new MainView(800, 500,navigationController,searchController,treeController);

    }
}
