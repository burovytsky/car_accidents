package accidents.repository;


import java.util.Collection;

public interface Store<T> {

    Collection<T> getAll();

    void create(T t);

    T findById(int id);
}
