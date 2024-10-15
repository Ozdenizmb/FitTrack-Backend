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
@Table(name = "user_data", schema = "util_sch")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;

}
