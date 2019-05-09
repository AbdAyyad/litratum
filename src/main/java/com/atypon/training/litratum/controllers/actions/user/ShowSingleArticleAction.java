package com.atypon.training.litratum.controllers.actions.user;

import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.AccessControl;
import com.atypon.training.litratum.controllers.tools.Constants;
import com.atypon.training.litratum.controllers.tools.JspPath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ShowSingleArticleAction implements IAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object sessionAttr = session.getAttribute("loggedInUser");
        boolean loggedInUser = sessionAttr == null ? false : (Boolean) sessionAttr;
        RequestDispatcher dispatcher;
        if (loggedInUser) {
            String userId = (String) session.getAttribute("userId");
            boolean isAuthorized = AccessControl.isAuthorized(userId);
            String article;
            if (isAuthorized) {
                String doi = req.getParameter("doi");
                String path = Constants.PROCESSED_FOLDER + doi + ".html";
                article = readArticle(path);
            } else {
                article = "<h3 class='text-center top-ten-percent'>your license expired</h3>";
            }
            session.setAttribute("article", article);
            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.HOME_PAGE);
        }
        dispatcher.forward(req, resp);
    }

    private String readArticle(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder article = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            article.append(line);
        }
        return article.toString();
    }
}
