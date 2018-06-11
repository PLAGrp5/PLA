package automate;

import onscreen.*;
import ui.*;

/*
 * Classe qui désigne un automate, elle contient un tableau de transition
 */
public class Automate {
	// Nos automate sont partagé, ainsi ,l'état courant est directement contenu dans
	// notre entité
	// State courant;
	public Transition[] t;
	public Model model;

	public Automate(State e, Transition[] t) {
		// courant = e;

		this.t = t;
	}

	public Automate(Model model, State e, Transition[] t) {
		// courant = e;
		this.model = model;
		this.t = t;
	}

	/*
	 * Execution de la première transition possible cad que: - l'etat source
	 * necessaire à la transition est notre etat courant - la condition pour
	 * réaliser l'action est respecté Remarque: Ici notre automate est déterministe,
	 * on prend donc la première transition possible (Normalement c'est la seule qui
	 * est possible)
	 */

	public void step(Entity e) {
		e.lasti = e.p.i;
		e.lastj = e.p.j;
		Transition[] t_ok = new Transition[t.length];
		int nb_trans = 0;
		for (int i = 0; i < t.length; i++) {
			if (t[i].eval(e))
				t_ok[nb_trans++] = t[i];
		}
		if (nb_trans == 0)
			t_ok[nb_trans++] = t[t.length - 1];

		int rand = (int) (Math.random() * nb_trans);
		t_ok[rand].act.execute(e);
		e.courant = t_ok[rand].dest;
	}
}
