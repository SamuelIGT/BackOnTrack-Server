package ufc.br.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
@Entity
public class Permition {
    @Id
    @GeneratedValue
    private int id;
    private boolean isUnlocked;
    @OneToOne
    private Grasp grasp;
    @OneToOne
    private Patient patient;

    public Permition(){
        this.isUnlocked = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }

    public Grasp getGrasp() {
        return grasp;
    }

    public void setGrasp(Grasp grasp) {
        this.grasp = grasp;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
