package com.adillice.recipes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.adillice.recipes.models.Recipe;

@Repository
public interface RecipeRepo extends CrudRepository<Recipe, Long>{

	//custom queries
	@Query(value="SELECT * FROM recipes r WHERE LOWER(r.title) LIKE LOWER(CONCAT('%',:search,'%'))", nativeQuery=true)
	List<Recipe> searchRecipes(@Param("search") String search);
	
}
