package my_projects.web_tree.controller.ajax;

import my_projects.web_tree.model.FileModelLayer;
import my_projects.web_tree.model.Tree;
import my_projects.web_tree.utill.HtmlTree;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ajax/loadingFromFile")
public class LoadingTreeFromFileController extends HttpServlet {
    private FileModelLayer model=new FileModelLayer();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Tree tree= model.createTree(req.getParameter("fileText"));
        req.getServletContext().setAttribute("tree",tree);

        PrintWriter printWriter=resp.getWriter();
        printWriter.write(HtmlTree.covertTreeToHtml(tree.getRoot()).toString());

    }
}

