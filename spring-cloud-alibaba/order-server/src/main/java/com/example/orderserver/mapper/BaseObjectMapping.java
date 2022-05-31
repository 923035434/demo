package com.example.orderserver.mapper;

/**
 * 类型转换接口定义
 */
public interface BaseObjectMapping<F, T> {
    /**
     * 将源类型的对象转换为目标类型的对象
     * 
     * @param entity 源类型的对象
     * @return 目标类型的对象
     */
    T to(F entity);

    /**
     * 将目标类型的对象转换为源类型的对象
     * 
     * @param entity 目标类型的对象
     * @return 源类型的对象
     */
    F from(T entity);
}
