package com.atletii.mhpdeskbookingbackend.common.mapper;


import com.atletii.mhpdeskbookingbackend.common.persistence.BaseEntity;
import com.atletii.mhpdeskbookingbackend.common.service.BaseEntityModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

public interface BaseModelEntityMapper<M extends BaseEntityModel, E extends BaseEntity> {

    E mapToEntity(M model);

    M mapToModel(E entity);

    default Optional<M> mapToModel(Optional<E> e) {
        if (e.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(mapToModel(e.get()));
    }

    default Page<M> mapToModel(Page<E> page) {
        List<M> content = page.stream().map(this::mapToModel).toList();
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }

    default List<M> mapToModels(List<E> es) {
        return es.stream().map(this::mapToModel).toList();
    }
}
