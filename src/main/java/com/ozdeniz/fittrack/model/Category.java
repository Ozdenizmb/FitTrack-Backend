package com.ozdeniz.fittrack.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "category_data", schema = "util_sch")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {

    @Id
    private int id;

    @Column(name = "name")
    private String name;
}
