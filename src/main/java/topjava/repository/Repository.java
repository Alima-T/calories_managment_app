package topjava.repository;

import topjava.model.Meal;

import java.util.Collection;

public interface Repository { // DAO (Data Access Object) работает с Entity, а Repository объектами более высокого уровня (в т.ч. с Entity), которые мы передаем на UI
    Collection<Meal> getAllMeals();
    void createMeal(Meal meal);
    void deleteMeal(String id, Meal meal);
    void updateMeal(String id, Meal meal);


}
