package com.intellias.marketplace.repo;

import com.intellias.marketplace.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
