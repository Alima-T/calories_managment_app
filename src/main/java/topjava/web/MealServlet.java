package topjava.web;

import org.slf4j.Logger;
import topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("getAll");
        log.debug("redirect to meals");
//        response.sendRedirect("meals.jsp");
        request.setAttribute("allMeals", MealsUtil.filteredByStreams(MealsUtil.MEALS, LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY)); // allMeals - название атрибута пойдет в jsp страницу в <c:forEach items="${allMeals}" var="meal">
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
