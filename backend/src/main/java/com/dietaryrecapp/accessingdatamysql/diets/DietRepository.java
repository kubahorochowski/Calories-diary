package com.dietaryrecapp.accessingdatamysql.diets;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DietRepository extends CrudRepository<Diet, Integer> {

    @Query("select d from Diet d where user_id like ?1")
    List<Diet> findByUserId(Integer user_id);

    @Query("select d from Diet d where user_id like ?1 and date like ?2")
    List<Diet> findByUserIdAndDate(Integer user_id, LocalDate date);

}
