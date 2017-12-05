package ufc.br.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.Console;
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
import org.springframework.web.client.RestTemplate;

import antlr.collections.List;
import edu.umd.cs.findbugs.ba.Debug;
import junit.framework.TestResult;
import ufc.br.controller.ExerciseController;
import ufc.br.model.Exercise;
import ufc.br.model.Midia;
import ufc.br.model.Object;
import ufc.br.service.ExerciseService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ExerciseApplicationTests {
	Exercise exercise;
	Exercise savedExercise;
	
	@Autowired
	ExerciseService service;
	
	@Before
	public void setup() {
		for(Exercise exercise: service.get().getBody()) {
			service.delete(exercise.getId());
		}
		
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.add(new Object("Bola"));
		Midia midia = new Midia("teste/videoPath", "teste/audioPath");
		
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
