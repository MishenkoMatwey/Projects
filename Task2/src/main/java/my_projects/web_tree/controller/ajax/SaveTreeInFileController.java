package my_projects.web_tree.controller.ajax;

import my_projects.web_tree.model.FileModelLayer;
import my_projects.web_tree.model.Tree;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ajax/saveToFile")
public class SaveTreeInFileController extends HttpServlet {
    FileModelLayer model=new FileModelLayer();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          Tree tree = (Tree) req.getServletContext().getAttribute("tree");
          String listContiguity=model.covertTreeToListContiguity(tree.getRoot());
          PrintWriter printWriter=resp.getWriter();
          printWriter.write(listContiguity);
    }
}
