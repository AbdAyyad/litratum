package com.atypon.training.litratum.controllers.actions.user;

import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.model.database.daos.implementations.NormalUserDao;
import com.atypon.training.litratum.model.database.daos.implementations.UserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.INormalUserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.IUserDao;
import com.atypon.training.litratum.model.database.datamodel.NormalUserModel;
import com.atypon.training.litratum.model.database.datamodel.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowUserInformationAction implements IAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object sessionAttr = session.getAttribute("loggedInUser");
        boolean loggedInUser = sessionAttr == null ? false : (Boolean) sessionAttr;
        RequestDispatcher dispatcher;
        if (loggedInUser) {
            String email = (String) session.getAttribute("userEmail");

            IUserDao dao = new UserDao();
            INormalUserDao normalDao = new NormalUserDao();

            UserModel user = dao.getByEmail(email);
            NormalUserModel normalUserModel = normalDao.getByUserId(user.getUserId());

            String license = normalUserModel.getLicenseId();

            if (license == null) {
                license = "no license";
            }

            session.setAttribute("userName", user.getUserEmail());
            session.setAttribute("user", user);
            session.setAttribute("license", license);
            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.HOME_PAGE);
        }
        dispatcher.forward(req, resp);
    }
}
