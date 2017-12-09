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
import ufc.br.service.ExerciseService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LevelApplicationTest {
	Exercise exercise;
	Exercise savedExercise;
	
	@Autowired
	ExerciseService service;
	
	@Before
	public void setup() {
		for(Exercise exercise: service.get().getBody()) {
			service.delete(exercise.getId());
		}
		
		service.save(new Exercise("Exercício Salvo", "Exercício de teste salvo", null, null));
		this.savedExercise = service.get().getBody().get(0);
	}
	
	@Test
	public void saveExerciseTest() {
		
		Exercise exercise = new Exercise("Exercício Teste", "Exercício de teste", null, null); 
		ResponseEntity<String> response = service.save(exercise);
		ResponseEntity<String> expectedResponse = new ResponseEntity<String>("Exercicio : " + exercise.getTitle() + " cadastrado!", HttpStatus.CREATED);
		
		
		Assert.assertEquals(expectedResponse.getBody(), response.getBody());
	}
	
	@Test
	public void getExerciseTest() {
		Exercise exerciseResponse = service.get(savedExercise.getId()).getBody();
		
		Assert.assertEquals(savedExercise.getTitle(), exerciseResponse.getTitle()); 
	}
	

}
