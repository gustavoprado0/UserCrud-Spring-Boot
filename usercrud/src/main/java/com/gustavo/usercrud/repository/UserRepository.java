package com.gustavo.usercrud.repository;

import com.gustavo.usercrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
