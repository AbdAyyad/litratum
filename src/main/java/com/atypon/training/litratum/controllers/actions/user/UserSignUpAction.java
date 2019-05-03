package com.atypon.training.litratum.controllers.actions.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.RandomGenerator;
import com.atypon.training.litratum.model.database.daos.implementations.NormalUserDao;
import com.atypon.training.litratum.model.database.daos.implementations.UserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.ISubUserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.IUserDao;
import com.atypon.training.litratum.model.database.datamodel.NormalUserModel;
import com.atypon.training.litratum.model.database.datamodel.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserSignUpAction implements IAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("userPassword");
        String email = req.getParameter("userEmail");

        char[] bcryptChars = BCrypt.withDefaults().hashToChar(12, password.toCharArray());
        password = new String(bcryptChars);

        String userId = RandomGenerator.getRandomString(64);
        String normalUserId = RandomGenerator.getRandomString(64);

        UserModel user = new UserModel(userId, userName, email, password);
        NormalUserModel normalUser = new NormalUserModel(normalUserId, userId);

        IUserDao userDao = new UserDao();
        ISubUserDao<NormalUserModel> normalDao = new NormalUserDao();

        userDao.add(user);
        normalDao.add(normalUser);

        HttpSession session = req.getSession();

        session.setAttribute("userName", userName);
        session.setAttribute("userEmail", email);
        session.setAttribute("loggedInUser", true);
        session.setMaxInactiveInterval(7200);

        RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
        dispatcher.forward(req, resp);
    }
}
