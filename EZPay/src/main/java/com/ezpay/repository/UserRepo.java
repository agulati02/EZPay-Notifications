package com.ezpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezpay.entity.User;

/**
 * UserRepo interface
 * Repository interface for User entity, providing CRUD operations and custom
 * query methods.
 * This interface allows for interaction with the database to perform operations
 * on User entities.
 * 
 * @author Akhil Kholia
 * @date 2024-08-31
 */
@Repository
public interface UserRepo extends JpaRepository<User, String> {

     /**
      * Finds a user by their ID.
      * 
      * @param id User ID to search for.
      * @return User object corresponding to the provided ID.
      */

     User findUserById(String id);
}