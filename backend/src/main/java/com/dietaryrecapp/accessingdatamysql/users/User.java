package com.dietaryrecapp.accessingdatamysql.users;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer user_id;
    private String username;
    private String email;
    private String password;
    private LocalDate birthdate;
    private Float weight;
    private Integer height;
    private Character sex;
    private Character enabled;

}
