package topjava.util;
/**
 * Alima_T
 * 16-03-2022
 */

import topjava.model.UserMeal;
import topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByLoops(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);
    }

    public static List<UserMealWithExcess> filteredByLoops(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumPerDayMap = new HashMap<>();
        for (UserMeal meal : meals) {
            caloriesSumPerDayMap.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), Integer::sum);
        }
        List<UserMealWithExcess> mealsWithExcess = new ArrayList<>();
        for (UserMeal meal : meals) {
            LocalDateTime dateTime = meal.getDateTime();
            boolean excess = caloriesSumPerDayMap.get(dateTime.toLocalDate()) > caloriesPerDay;
            if (TimeUtil.isBetweenHalfOpen(dateTime.toLocalTime(), startTime, endTime)) {
                mealsWithExcess.add(convertToMealWithExcess(meal, excess));
            }
        }
        return mealsWithExcess;
    }

    private static UserMealWithExcess convertToMealWithExcess(UserMeal meal, boolean excess) {
        return new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }
}
