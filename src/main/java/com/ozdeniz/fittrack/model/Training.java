package com.ozdeniz.fittrack.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ozdeniz.fittrack.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "training_data", schema = "util_sch")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Training extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "duration")
    private LocalTime duration;
    @Column(name = "category")
    private int category;
    @Column(name = "image_name")
    private String imageName;

}
