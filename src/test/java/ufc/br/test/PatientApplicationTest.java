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
import ufc.br.model.Patient;
import ufc.br.service.ExerciseService;
import ufc.br.service.PatientService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientApplicationTest {
	Patient patient;
	Patient savedPatient;

	@Autowired
	PatientService service = new PatientService();

	@Before
	public void setup(){
		for(Patient patient: service.get().getBody()) {
			service.delete(patient.getId());
		}

		service.save(new Patient("222222", "Samuel Alves", "(88)8888-8888", "Antônio Alves", "(88) 9988-9988"));
		this.savedPatient = service.get().getBody().get(0);
	}

	@Test
	public void savePatientTest() {

		Patient patient = new Patient("333333", "Antonio Alves Junior", "(88)9797-9797", "Vitor Emanuel", "(88) 5566-3452"); 
		ResponseEntity<String> response = service.save(patient);
		ResponseEntity<String> expectedResponse = new ResponseEntity<String>("Patient : "+ patient.getName()+" cadastrado!", HttpStatus.OK);

		System.out.println("\n\n\n\n"+response.getBody().toString() + "\n" + response.getStatusCode().toString()+"\n\n\n\n");

		Assert.assertEquals(expectedResponse.getBody(), response.getBody());

	}

	@Test
	public void getPatientTest() {
		Patient patientResponse = service.get(savedPatient.getId()).getBody();

		Assert.assertEquals(savedPatient.getName(), patientResponse.getName());
		Assert.assertEquals(savedPatient.getParent(), patientResponse.getParent());
		Assert.assertEquals(savedPatient.getPhone(), patientResponse.getPhone());
		Assert.assertEquals(savedPatient.getPhoneParent(), patientResponse.getPhoneParent());
		Assert.assertEquals(savedPatient.getRegistration(), patientResponse.getRegistration());
	}


}