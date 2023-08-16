package com.poluhin.ss.demo.repository;

import com.poluhin.ss.demo.domain.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.*;

public interface ResourceObjectRepository extends ReactiveMongoRepository<ResourceObjectEntity, Integer> {
}
