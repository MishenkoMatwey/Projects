package my_projects.web_tree.utill;

import my_projects.web_tree.model.Node;


public class HtmlTree {
    private static StringBuilder html;

    public static StringBuilder covertTreeToHtml(Node root) {
        html = new StringBuilder();
        convert(root);
        return html;
    }

    static private void convert(Node root) {

        if (root.getChildrenMap()==null || root.getChildrenMap().isEmpty()) {
            html.append("<li class=\"cl\">");
            html.append("<div><p>");
            html.append("<img src=\"media/folder.png\"");
            html.append("id=\"").append(root.getIdForHtml()).append("\"/>");
            html.append("<span class=\"f-name\">");
            html.append(root.getName());
            html.append("</span>");
            html.append("</p></div></li>");
        } else {
            html.append("<li class=\"cl\">");
            html.append("<div><p>");
            html.append("<img src=\"media/folder.png\"");
            html.append("id=\"").append(root.getIdForHtml()).append("\"/>");
            html.append("<span class=\"f-name\">");
            html.append(root.getName());
            html.append("</span>");
            html.append("<span class=\"sc\">&#9658;</span></p></div>");
            html.append("<ul>");
            for (Node el : root.getChildrenMap().values()) {
                convert(el);
            }
            html.append("</ul></li>");
        }

    }

}
