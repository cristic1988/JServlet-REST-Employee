package org.example.app.service;


import java.util.List;

public interface AppService<T,S> {

    T create(S request);

    List<T> getAll();


    T getById(Long id);

    T update(Long id, S request);

    boolean deleteById(Long id);
}
