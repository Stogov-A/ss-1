package com.poluhin.ss.demo.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.*;

@Document(value = "resources")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class ResourceObjectEntity {

    @Id
    private String id;

    private String value;
    private String path;


}
