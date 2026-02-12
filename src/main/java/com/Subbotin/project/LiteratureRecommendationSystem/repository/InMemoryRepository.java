package LiteratureRecommendationSystem.repository;

import LiteratureRecommendationSystem.domain.Identifiable;
import java.util.*;

public abstract class InMemoryRepository<T extends Identifiable> implements Repository<T> {

    private final Map<String, T> byId = new HashMap<>();

    @Override
    public void add(T item) {
        String id = requireValidId(item);
        if (byId.containsKey(id)) {
            throw new IllegalArgumentException("Položka s ID už existuje: " + id);
        }
        byId.put(id, item);
    }

    @Override
    public void update(T item) {
        String id = requireValidId(item);
        if (!byId.containsKey(id)) {
            throw new IllegalArgumentException("Neexistuje položka s ID:" + id);
        }
        byId.put(id, item);
    }

    @Override
    public T findById(String id) {
        String validId = requireNonBlankId(id);
        return byId.get(validId);
    }

    @Override
    public List<T> findAll() {
        return List.copyOf(byId.values());
    }

    @Override
    public boolean removeById(String id) {
        String validId = requireNonBlankId(id);
        return byId.remove(id) != null;
    }

    @Override
    public int size() {
        return byId.size();
    }

    @Override
    public void clear() {
        byId.clear();
    }

    private String requireValidId(T item) {
        Objects.requireNonNull(item, "položka");
        String id = item.getId();
        return requireNonBlankId(id);
    }

    private String requireNonBlankId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID je prázdne");
        }
        return id;
    }
}
