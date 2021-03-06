package ufc.br.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
@Entity
public class Level {
    @Id
    @GeneratedValue
    private int id;
    private int level;

    public Level(int level){
        this.level = level;
    }
    

    public Level() {
		super();
	}


	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
