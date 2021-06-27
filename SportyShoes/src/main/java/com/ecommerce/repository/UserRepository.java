package com.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;
import com.ecommerce.models.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}

