package com.example.backendservicecore.model.mapper;

public interface Mapper<T, V> {
    T convertFromDTO(V v);
    V convertToDTO(T t);
}
