package com.dietaryrecapp.accessingdatamysql.products;

import com.dietaryrecapp.accessingdatamysql.diets.Diet;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer product_id;
    private String product_name;
    private Float carbohydrates;
    private Float proteins;
    private Float fat;
    private Float kcal;

}
