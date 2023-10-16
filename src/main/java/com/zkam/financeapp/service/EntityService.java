package com.zkam.financeapp.service;

import com.zkam.financeapp.domain.GeneratedIdEntity;

public interface EntityService<T extends GeneratedIdEntity> {
    T create(T entity);

    T delete(T entity);

}
