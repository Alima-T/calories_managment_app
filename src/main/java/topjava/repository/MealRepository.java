package topjava.repository;

import topjava.model.Meal;

import java.util.Collection;

// TODO add userId
public interface MealRepository { // DAO (Data Access Object) работает с Entity, а Repository объектами более высокого уровня (в т.ч. с Entity), которые мы передаем на UI
    // null if updated meal does not belong to userId
    Meal save(Meal meal);

    // false if meal does not belong to userId
    boolean delete(int id);

    // null if meal does not belong to userId
    Meal get(int id);

    // ORDERED dateTime desc
    Collection<Meal> getAll();


}


