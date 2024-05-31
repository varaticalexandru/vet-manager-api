package org.alexv.vet_manager_api.commons.mapper;

import java.util.List;

public interface Mapper<A, B> {
    B toDTO(A a);

    A toEntity(B b);

    List<B> toDTO(List<A> a);

    List<A> toEntity(List<B> b);
}