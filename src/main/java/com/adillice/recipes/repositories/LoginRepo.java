package com.adillice.recipes.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.adillice.recipes.models.User;

@Repository
public interface LoginRepo extends CrudRepository<User, Long>{

	//custom queries
	Optional<User> findByEmail(String string);
	Optional<User> findByUsername(String string);
	
}
