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
import ufc.br.service.MidiaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MidiaApplicationTests {

	Midia midia;
	Midia savedMidia;
	
	@Autowired
	MidiaService service;
	
	@Before
	public void setUp() throws Exception {
		for(Midia midia: service.get().getBody()) {
			service.delete(midia.getId());
		}
		
		service.save(new Midia("teste/videoPath", "teste/audioPath"));
		this.savedMidia = service.get().getBody().get(0);
	}
	
	@Test
	public void saveMidiaTest() {
		
		Midia midia = new Midia("src/videoPath", "src/audioPath"); 
		ResponseEntity<String> response = service.save(midia);
		ResponseEntity<String> expectedResponse = new ResponseEntity<String>("sucesso", HttpStatus.OK);
		
		
		Assert.assertEquals(expectedResponse.getBody(), response.getBody());
	}
	
	@Test
	public void getMidiaTest() {
		Midia midiaResponse = service.get(savedMidia.getId()).getBody();
		
		Assert.assertEquals(savedMidia.getPathAudio(), midiaResponse.getPathAudio()); 
		Assert.assertEquals(savedMidia.getPathVideo(), midiaResponse.getPathVideo());
	}
	

}

