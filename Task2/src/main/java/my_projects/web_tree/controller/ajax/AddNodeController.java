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

@WebServlet("/ajax/add")
public class AddNodeController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParentNode = req.getParameter("idParentNode");
        String nameNodeForAdd = req.getParameter("nodeForAdd");
        Tree tree = (Tree) req.getServletContext().getAttribute("tree");

        if (idParentNode != null) {
            tree.addNode(idParentNode, nameNodeForAdd);
        } else {
            tree.setRoot(new Node(nameNodeForAdd, null));
        }

        PrintWriter printWriter = resp.getWriter();
        printWriter.write(HtmlTree.covertTreeToHtml(tree.getRoot()).toString());
    }
}
