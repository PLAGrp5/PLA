package automate;

import onscreen.*;

public class Key extends Condition {

	public String touche;
	
	public Key(String touche) {
		this.touche = touche;
	}
	
	public boolean eval(Entity e) {
		return touche.equals(e.m_model.last_touche) ;
	}
}
