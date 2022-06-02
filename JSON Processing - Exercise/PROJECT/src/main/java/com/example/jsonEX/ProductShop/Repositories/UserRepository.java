package com.example.jsonEX.ProductShop.Repositories;

import com.example.jsonEX.ProductShop.entities.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


}
