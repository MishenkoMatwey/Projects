package my_projects.search_engine.util;

public interface Algorithm {
    boolean findSubStr(String pattern,String text);
    void createCmp(boolean caseSensitive);
}
