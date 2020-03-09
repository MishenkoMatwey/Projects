package my_projects.web_tree.controller.ajax;

import my_projects.web_tree.model.DBModelLayer;
import my_projects.web_tree.model.Tree;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ajax/saveToDB")
public class SaveTreeInBDController extends HttpServlet {
    private DBModelLayer model=new DBModelLayer();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("nameTree");
        Tree tree = (Tree) req.getServletContext().getAttribute("tree");
        try {
            model.saveTree(name,tree);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
