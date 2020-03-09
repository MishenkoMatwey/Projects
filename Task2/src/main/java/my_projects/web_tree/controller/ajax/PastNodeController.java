package my_projects.web_tree.controller.ajax;

import my_projects.web_tree.model.Node;
import my_projects.web_tree.model.Tree;
import my_projects.web_tree.utill.HtmlTree;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/ajax/past")
public class PastNodeController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Node node = (Node) req.getServletContext().getAttribute("cutNode");
        String idParentNode = req.getParameter("idNode");
        boolean cutAll = Boolean.parseBoolean(req.getParameter("all"));
        Tree tree = (Tree) req.getServletContext().getAttribute("tree");
        PrintWriter printWriter = resp.getWriter();
        if (node != null) {
            if (!cutAll) {
                node.setChildrenMap(new HashMap<>());
            }
            tree.addNode(idParentNode, node);
            printWriter.write(HtmlTree.covertTreeToHtml(tree.getRoot()).toString());
        }
        printWriter.write("");
    }
}


