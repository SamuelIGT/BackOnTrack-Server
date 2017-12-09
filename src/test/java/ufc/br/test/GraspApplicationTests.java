package ufc.br.test;

import java.util.Calendar;

import org.junit.After;
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
import ufc.br.model.Grasp;
import ufc.br.model.Level;
import ufc.br.service.ExerciseService;
import ufc.br.service.GraspService;
import ufc.br.service.LevelService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GraspApplicationTests {
	Grasp grasp;
	Grasp savedGrasp;

	@Autowired
	GraspService service;
	@Autowired
	ExerciseService exerciseService;
	@Autowired
	LevelService levelService;
	
	@Before
	public void setup(){
		//undoneChanges();
		for(Grasp grasp: service.get().getBody()) {
			service.delete(grasp.getId());
		}
		/*Exercise exercise = new Exercise("Exercício Salvo", "Exercício de teste salvo", null, null);
		exerciseService.save(exercise);
		
		Level level = new Level(1);
		levelService.save(level);*/
		
		service.save(new Grasp(null, null, Calendar.getInstance().getTime(), "Dica", 2));
		this.savedGrasp = service.get().getBody().get(0);
	}

	@Test
	public void saveGraspTest() {

		/*Exercise exercise = new Exercise("Exercício Mãos", "Exercício para as mãos", null, null);
		exerciseService.save(exercise);
		Level level = new Level(2);
		levelService.save(level);*/
		Grasp grasp = new Grasp(null, null, Calendar.getInstance().getTime(), "Dica 2", 3); 
		ResponseEntity<String> response = service.save(grasp);
		ResponseEntity<String> expectedResponse = new ResponseEntity<String>("sucesso", HttpStatus.OK);

		Assert.assertEquals(expectedResponse.getBody(), response.getBody());

	}

	@Test
	public void getGraspTest() {
		Grasp graspResponse = service.get(savedGrasp.getId()).getBody();

		Assert.assertEquals(savedGrasp.getTip(), graspResponse.getTip());
		Assert.assertEquals(savedGrasp.getExercise(), graspResponse.getExercise());
		Assert.assertEquals(savedGrasp.getDate().toString(), graspResponse.getDate().toString());
		Assert.assertEquals(savedGrasp.getSequence(), graspResponse.getSequence());
		Assert.assertEquals(savedGrasp.getLevel(), graspResponse.getLevel());
		

	}
	
	/*@After
	public void undoneChanges() {
		for(Exercise exercise: exerciseService.get().getBody()) {
			exerciseService.delete(exercise.getId());
		}
		for(Level level: levelService.get().getBody()) {
			levelService.delete(level.getId());
		}
	}*/

}