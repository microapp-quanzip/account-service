package com.viettel.account.service.mapper;

import java.util.List;

public interface EntityMapper<E, D>{

    E toEntity(D d);

    D toDto(E e);

    List<E> toEntities(List<D> ds);

    List<D> toDtos(List<E> es);
}
