package automate;

import onscreen.*;
import ui.*;
/*
 * c'est une classe mère englobant toutes les actions
 * par exemple Move, Pop et Wizz
 * cette classe contient une méthode execute(Entity e) qui consiste à lancer l'action sur l'entité e
 */

public class Action {
	char dir;
	Model model;
	
	public void execute(Entity e) {
	}
}
