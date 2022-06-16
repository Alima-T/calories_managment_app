package topjava.repository;


import topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealRepository implements IRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>(); //thread save realisation for HashMap is ConcurrentHashMap
    private AtomicInteger counter = new AtomicInteger(0);

    {
            createMeal (new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
            createMeal (new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
            createMeal (new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
            createMeal (new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
            createMeal (new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
            createMeal (new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
            createMeal (new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public void createMeal(Meal meal) {

    }

    @Override
    public Collection<Meal> getAllMeals() {
        return null;
    }

    @Override
    public void deleteMeal(String id, Meal meal) {

    }

    @Override
    public void updateMeal(String id, Meal meal) {
       
    }
}