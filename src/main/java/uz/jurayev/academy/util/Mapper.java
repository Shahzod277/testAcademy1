package uz.jurayev.academy.util;

public interface Mapper<T, R> {
    R  mapFrom(T entity);
}
