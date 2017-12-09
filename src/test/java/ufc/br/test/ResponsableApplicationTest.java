package ufc.br.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import ufc.br.model.Exercise;
import ufc.br.model.Midia;
import ufc.br.model.Object;
import ufc.br.model.Responsible;
import ufc.br.service.ExerciseService;
import ufc.br.service.ResponsibleService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ResponsableApplicationTest {
	Responsible responsable;
	Responsible savedResponsable;
	
	@Autowired
	ResponsibleService service;
	
	@Before
	public void setUp() throws Exception {
		for(Responsible responsable: service.get().getBody()) {
			service.delete(responsable.getId());
		}
		
		service.save(new Responsible("samuel.br.igt@gmail.com", "abcd", "Samuel Alves"));
		this.savedResponsable = service.get().getBody().get(0);
	}
	
	@Test
	public void saveResponsableTest() {
		
		Responsible responsable = new Responsible("samuel123@ufc.br", "1234", "Samuel"); 
		ResponseEntity<String> response = service.save(responsable);
		ResponseEntity<String> expectedResponse = new ResponseEntity<String>("sucesso", HttpStatus.OK);
		
		
		Assert.assertEquals(expectedResponse.getBody(), response.getBody());
	}
	
	@Test
	public void getResponsableTest() {
		Responsible responsableResponse = service.get(savedResponsable.getId()).getBody();
		
		Assert.assertEquals(savedResponsable.getName(), responsableResponse.getName());
		Assert.assertEquals(savedResponsable.getEmail(), responsableResponse.getEmail());
		Assert.assertEquals(savedResponsable.getPassword(), responsableResponse.getPassword());
	}
	

}