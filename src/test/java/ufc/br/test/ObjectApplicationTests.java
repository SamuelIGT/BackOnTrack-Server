package ufc.br.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import ufc.br.model.Object;
import ufc.br.service.ObjectService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectApplicationTests {
	
	Object savedObject;
	
	@Autowired
	ObjectService service;
	
	@Before
	public void setUp() throws Exception {
		for(Object object: service.get().getBody()) {
			service.delete(object.getId());
		}
		 
		 service.save(new Object("Lápis"));
		 this.savedObject = service.get().getBody().get(0);
	}
	
	@Test
	public void saveObjectTest() {
		Object object = new Object("Bola");
		ResponseEntity<String> response = service.save(object);
		ResponseEntity<String> expectedResponse = new ResponseEntity<String>(object.getName()+" adicionado!", HttpStatus.OK);
		
		Assert.assertEquals(expectedResponse.getBody(), response.getBody());
	}
	
	@Test
	public void getExerciseTest() {
		Object exerciseResponse = service.get(savedObject.getId()).getBody();
		
		Assert.assertEquals(savedObject.getName(), exerciseResponse.getName());
	}
	
}