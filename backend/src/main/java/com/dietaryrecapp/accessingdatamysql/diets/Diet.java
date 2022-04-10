package com.dietaryrecapp.accessingdatamysql.diets;

import com.dietaryrecapp.accessingdatamysql.products.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Diet {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer diet_id;
    private Integer user_id;
    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "PRODUCT_ID_FK"))
    private Product product;
    private LocalDate date;
    private Float weight;
}
