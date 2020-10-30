package com.adillice.recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.adillice.recipes.models.Category;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Long>{
	
	//custom queries
	Category findByNameEquals(String string);
	
}
