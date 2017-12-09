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

import ufc.br.model.Exercise;
import ufc.br.model.Level;
import ufc.br.service.LevelService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LevelApplicationTests {
	Level level;
	Level savedLevel;
	
	@Autowired
	LevelService service;
	
	@Before
	public void setUp() throws Exception {
		
		for(Level level: service.get().getBody()) {
			service.delete(level.getId());
		}
		
		service.save(new Level(1));
		this.savedLevel = service.get().getBody().get(0);
	}
	
	@Test
	public void saveLevelTest() {
		
		Level level = new Level(2); 
		ResponseEntity<String> response = service.save(level);
		ResponseEntity<String> expectedResponse = new ResponseEntity<String>("sucesso", HttpStatus.OK);
		
		
		Assert.assertEquals(expectedResponse.getBody(), response.getBody());
	}
	
	@Test
	public void getLevelTest() {
		Level levelResponse = service.get(savedLevel.getId()).getBody();
		
		Assert.assertEquals(savedLevel.getLevel(), levelResponse.getLevel()); 
	}

}
