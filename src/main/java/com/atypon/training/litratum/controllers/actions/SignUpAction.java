package com.atypon.training.litratum.controllers.actions;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.atypon.training.litratum.controllers.tools.RandomGenerator;
import com.atypon.training.litratum.model.database.daos.Dao;
import com.atypon.training.litratum.model.database.daos.NormalUserDao;
import com.atypon.training.litratum.model.database.daos.UserDao;
import com.atypon.training.litratum.model.database.datatypes.NormalUser;
import com.atypon.training.litratum.model.database.datatypes.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpAction implements ActionInterface {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("userPassword");
        String email = req.getParameter("userEmail");

        char[] bcryptChars = BCrypt.withDefaults().hashToChar(12, password.toCharArray());
        password = new String(bcryptChars);

        String userId = RandomGenerator.getRandomString(64);
        String normalUserId = RandomGenerator.getRandomString(64);

        User user = new User(userId, userName, email, password, true);
        NormalUser normalUser = new NormalUser(normalUserId, userId);

        Dao userDao = new UserDao();
        Dao normalDao = new NormalUserDao();

        userDao.addEntry(user);
        normalDao.addEntry(normalUser);

        req.setAttribute("userName", userName);
        RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
        dispatcher.forward(req, resp);
    }
}
