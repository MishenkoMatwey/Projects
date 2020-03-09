package my_projects.web_tree.controller;

import my_projects.web_tree.model.Tree;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class Main extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().setAttribute("tree",new Tree());
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("WEB-INF/JSP/main-page.jsp");
        requestDispatcher.forward(req,resp);
    }
}

