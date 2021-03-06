package app.mongo.repository;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repositories-test-context.xml")
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Before
	public void clear() {
		categoryRepo.deleteAll();
	}
	
	@Test
    public void grudTest() {
		
		// CREATE
		
		Category categoryTest = new Category();
		categoryTest.setName("category.name");
		
		categoryRepo.save(categoryTest);
		
		// READ
		
		List<Category> categoriesDocs = categoryRepo.findByNameLike(categoryTest.getName());
		
		assertNotNull(categoriesDocs);
		assertFalse(categoriesDocs.isEmpty());
		assertTrue(categoriesDocs.contains(categoryTest));
		
		Category categoryDoc = categoryRepo.findByIdOrName(null, categoryTest.getName());
		
		assertNotNull(categoryDoc);
		assertNotNull(categoryDoc.getId());
		assertEquals(categoryDoc, categoryTest);
		
		// UPDATE
		
		Category categoryNew = new Category();
		categoryNew.setName("New name");
		categoryNew.setId(categoryDoc.getId());
		
		categoryRepo.save(categoryNew);
		
		Category categoryDocUpdated = categoryRepo.findOne(categoryDoc.getId());
		
		assertEquals(categoryDocUpdated, categoryNew);
		
		// DELETE
		
		categoryRepo.delete(categoryDocUpdated);
		assertTrue(categoryRepo.count() == 0);
    }
}
