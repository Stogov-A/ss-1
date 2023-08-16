package com.poluhin.ss.demo.controller;

import com.poluhin.ss.demo.domain.model.*;
import com.poluhin.ss.demo.service.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resource")
public class ResourceController {

    private final ResourceObjectService service;

    @PostMapping
    public Mono<String> createResourceObject(@RequestBody ResourceObject object) {
        Mono<String> save = service.save(object);
        return save;
    }

    @GetMapping("/{id}")
    public Mono<ResourceObject> getResourceObject(@PathVariable Integer id) {
        return service.get(id);
    }

}
