package my_projects.web_tree.controller.ajax;

import my_projects.web_tree.model.Tree;
import my_projects.web_tree.utill.HtmlTree;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ajax/delete")
public class DeleteNodeController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idNode = req.getParameter("idNode");
        boolean deleteAll = Boolean.parseBoolean(req.getParameter("all"));
        Tree tree = (Tree) req.getServletContext().getAttribute("tree");
        if (idNode != null) {
            if (deleteAll) {
                tree.deleteSubTree(idNode);
            } else {
                tree.deleteNode(idNode);
            }
        }
        PrintWriter printWriter = resp.getWriter();
        if(tree.getRoot()!=null){
            printWriter.write(HtmlTree.covertTreeToHtml(tree.getRoot()).toString());
        }else {
            printWriter.write("");
        }


    }
}
