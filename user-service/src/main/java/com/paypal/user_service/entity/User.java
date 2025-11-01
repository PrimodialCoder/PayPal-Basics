package com.paypal.user_service.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "app_user")
public class User extends BaseModel {
    private String name;

    @Column(unique = true)
    private String email;
    private String password;

    private String role;
}
