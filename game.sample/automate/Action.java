package automate;

import onscreen.Entity;
import onscreen.Map;
import ui.*;
/*
 * c'est une classe mère englobant toutes les actions
 * par exemple Move, Pop et Wizz
 * cette classe contient une méthode execute(Entity e) qui consiste à lancer l'action sur l'entité e
 */

public class Action {
	char dir;
	Map m;
	
	public void execute(Entity e) {
	}

	public void execute(Model model, Entity e) {
	}
}
