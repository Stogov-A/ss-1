package com.poluhin.ss.demo.service;

import com.poluhin.ss.demo.domain.entity.*;
import com.poluhin.ss.demo.domain.model.*;
import com.poluhin.ss.demo.repository.*;
import lombok.*;
import org.springframework.stereotype.*;
import reactor.core.publisher.Mono;

import static org.springframework.data.mapping.Alias.ofNullable;

@Service
@RequiredArgsConstructor
public class ResourceObjectService {

    private final ResourceObjectRepository repository;

    public Mono<String> save(ResourceObject resourceObject) {
        return repository.save(ResourceObjectEntity.builder()
                .path(resourceObject.getPath())
                .value(resourceObject.getValue())
                .build())
                .map(ResourceObjectEntity::getId);

    }

    public Mono<ResourceObject> get(int id) {
        return repository.findById(id)
                .map(r -> new ResourceObject(r.getId(), r.getValue(), r.getPath()));
    }

}
