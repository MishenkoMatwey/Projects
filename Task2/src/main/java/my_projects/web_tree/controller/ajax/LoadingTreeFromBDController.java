package my_projects.web_tree.controller.ajax;

import my_projects.web_tree.model.DBModelLayer;
import my_projects.web_tree.model.Tree;
import my_projects.web_tree.utill.HtmlTree;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet("/ajax/loadingFromDB")
public class LoadingTreeFromBDController extends HttpServlet {
    private DBModelLayer model = new DBModelLayer();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(model.getTreeNames().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("nameTree");
        if (!name.isEmpty()) {
            Tree tree = null;
            try {
                tree = model.loadTree(name);
                req.getServletContext().setAttribute("tree", tree);
                PrintWriter printWriter = resp.getWriter();
                printWriter.write(HtmlTree.covertTreeToHtml(tree.getRoot()).toString());
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

