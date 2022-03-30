package uz.jurayev.academy.util;

import uz.jurayev.academy.model.Result;

public interface Mapper<T, R,K> {
    K mapFrom(T entity);
}
