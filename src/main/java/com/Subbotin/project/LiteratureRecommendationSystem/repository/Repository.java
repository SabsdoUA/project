package LiteratureRecommendationSystem.repository;

import java.util.List;

public interface Repository<T> {
    void add(T item);
    void update(T item);
    T findById(String id);
    List<T> findAll();
    boolean removeById(String id);
    int size();
    void clear();
}