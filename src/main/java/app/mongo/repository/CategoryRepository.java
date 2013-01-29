package app.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import app.domain.Category;
import app.domain.Recipe;

public interface CategoryRepository extends MongoRepository<Category, String> {
	
	public Category findByName(String name);
	
	public List<Category> findByNameLike(String name);
}
