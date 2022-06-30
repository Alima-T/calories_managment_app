package topjava.web;

import org.slf4j.Logger;
import topjava.model.Meal;
import topjava.repository.InMemoryMealRepository;
import topjava.repository.MealRepository;
import topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private MealRepository repository;

    @Override
    public void init() {
        repository = new InMemoryMealRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");

        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("calories")));
        log.info(meal.isNew() ? "Create{}" : "Update{}", meal);
        repository.save(meal);
        resp.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        log.info("getAll");
//        log.debug("redirect to meals");
//        resp.sendRedirect("meals.jsp");
//        req.setAttribute("allMeals", MealsUtil.filteredByStreams(MealsUtil.MEALS, LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY)); // allMeals - название атрибута пойдет в jsp страницу в <c:forEach items="${allMeals}" var="meal">
        String action = req.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(req);
                log.info("Delete id={}", id);
                repository.delete(id);
                resp.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action)?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),"",1000):
                        repository.get(getId(req));
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("/mealForm.jsp").forward(req,resp);
                break;
            case "all":
            default:
                log.info("getAll");
                req.setAttribute("meals", MealsUtil.getTos(repository.getAll(),MealsUtil.DEFAULT_CALORIES_PER_DAY));
        }
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

    private int getId(HttpServletRequest req) {
        String paramId = Objects.requireNonNull(req.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
