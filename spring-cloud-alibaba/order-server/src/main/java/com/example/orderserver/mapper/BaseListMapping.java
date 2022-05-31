package com.example.orderserver.mapper;

import java.util.List;

/**
 * 列表类型的对象转换接口
 */
public interface BaseListMapping<F, T> extends BaseObjectMapping<F, T> {
    /**
     * 将源类型的对象列表转换为目标类型的对象列表
     * 
     * @param entities 源类型的对象列表
     * @return 目标类型的对象列表
     */
    List<T> toList(List<F> entities);

    /**
     * 将目标类型的对象列表转换为源类型的对象列表
     * 
     * @param entities 目标类型的对象列表
     * @return 源类型的对象列表
     */
    List<F> fromList(List<T> entities);
}
