package com.dietaryrecapp.accessingdatamysql.users;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "authorities")
@Getter
@Setter
public class Authorities {

    @Id
    private String username;
    private String authority;
}
