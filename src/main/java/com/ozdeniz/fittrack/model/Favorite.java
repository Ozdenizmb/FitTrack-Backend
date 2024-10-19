package com.ozdeniz.fittrack.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ozdeniz.fittrack.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "favorite_data", schema = "util_sch")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Favorite extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "training_id")
    private UUID trainingId;

}
