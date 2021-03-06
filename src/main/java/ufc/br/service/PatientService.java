package ufc.br.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ufc.br.model.Patient;
import ufc.br.repository.PatientRepository;
import ufc.br.repository.ResponsibleRepository;

@Service
public class PatientService {
	@Autowired
	PatientRepository repository;
	@Autowired
	ResponsibleRepository responsible;

	private String gerarMatricula(){
		Date data = new Date(); 
		return "20"+(data.getYear()-100)+(data.getMonth()+1)+data.getDate()+data.getHours()+data.getMinutes()+data.getSeconds();
	}
	
	public ResponseEntity<String> save(Patient patient){
		if(null == repository.findByName(patient.getName())){
			patient.setRegistration(gerarMatricula());
			patient.setResponsible(responsible.findOne(1));
			repository.save(patient);
			return new ResponseEntity<String>("Patient : "+ patient.getName()+" cadastrado!", HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("Patient : "+ patient.getName()+" já cadastrado!", HttpStatus.IM_USED);
		}
	}

	public ResponseEntity<String> delete(Integer id){
		repository.delete(id);
		return new ResponseEntity<String>("Patient removido", HttpStatus.OK);
	}

	public ResponseEntity<String> update(Patient patient){
		Patient aux = repository.findByName(patient.getName());
		if(null==aux){
			//atualizo
			repository.save(patient);
			return new ResponseEntity<String>("Patient : "+ patient.getName()+" cadastrado!", HttpStatus.OK);
		
		}else{
			if(aux.getId().equals(patient.getId())){
				//atualizo
				repository.save(patient);
				return new ResponseEntity<String>("Patient : "+ patient.getName()+" cadastrado!", HttpStatus.OK);
			
			}else{
				//nao atualizo
				return new ResponseEntity<String>("Patient : "+ patient.getName()+" já cadastrado!", HttpStatus.IM_USED);
			}
		}
	}

	public ResponseEntity<Patient> get(Integer id){
		return new ResponseEntity<Patient>(this.repository.findOne(id), HttpStatus.OK);
	}

	public ResponseEntity<List<Patient>> get(){
		if(this.repository == null) {
			System.out.println("Why is null?");
		}
		ResponseEntity<List<Patient>> list = new ResponseEntity<List<Patient>>(this.repository.findAll(), HttpStatus.OK);
		if(list == null) {
			return new ResponseEntity<List<Patient>>(new ArrayList<Patient>(), HttpStatus.OK);
		}
		return list;
	}
}
