package topjava.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Alima-T 30.06.2022
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/users.jsp").forward(req, resp);
        resp.sendRedirect("/users.jsp");
    }
}
