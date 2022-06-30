package topjava.repository;

import topjava.model.Meal;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Alima-T 30.06.2022
 */
/*
До Java 1.5 для реализации Map, которую можно безопасно использовать в многопоточной Java-программе,
были только Hashtable или synchronized Map, потому что HashMap НЕ безопасен.
ConcurrentHashMap работает производительнее, потому что блокирует лишь часть Map.
Позволяет одновременные операции чтения и в то же время обеспечивает целостность, синхронизируя операции записи.
 */
public class InMemoryMealRepository implements MealRepository{
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(); // int value that may be updated atomically.

    @Override
    public Meal save(Meal meal) {
        if(meal.isNew()){
            meal.setId(counter.incrementAndGet());
            return meal;
        }
        return repository.computeIfPresent(meal.getId(), (id, oldMeal)->meal); // handle case: update, but not present in storage
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id)!=null;
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values();
    }
}
