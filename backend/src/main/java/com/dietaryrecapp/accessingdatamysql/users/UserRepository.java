package com.dietaryrecapp.accessingdatamysql.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u where username like ?1")
    User findUserByUsername(String username);

    @Query("select u from User u where user_id like ?1")
    User findUserById(Integer id);
}
