package my_projects.web_tree.controller.ajax;

import my_projects.web_tree.model.Node;
import my_projects.web_tree.model.Tree;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax/cut")
public class CutNodeController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idNode = req.getParameter("idNode");
        Tree tree = (Tree) req.getServletContext().getAttribute("tree");
        if (idNode!=null) {
            Node node=tree.findNode(idNode);
            req.getServletContext().setAttribute("cutNode",node);
            RequestDispatcher requestDispatcher=req.getRequestDispatcher("/ajax/delete");
            requestDispatcher.forward(req,resp);
        }
    }
}