package automate;

import onscreen.*;

/*
 * Classe qui désigne un automate, elle contient un tableau de transition
 */
public class Automate {
	// Nos automate sont partagé, ainsi ,l'état courant est directement contenu dans
	// notre entité
	
	//public Transition[] t;
	public State init;
	public behaviours[] b;
	public String name;

	public Automate(String name, State e, behaviours[] t) {
		this.name = name;
		init = e;
		this.b = t;
	}

	public Automate(State e, behaviours[] t) {
		this.b = t;
	}

	/*
	 * public Automate(Model model, State e, Transition[] t) { // courant = e;
	 * this.model = model; this.t = t; }
	 */
	/*
	 * Execution de la première transition possible cad que: - l'etat source
	 * necessaire à la transition est notre etat courant - la condition pour
	 * réaliser l'action est respecté Remarque: Ici notre automate est déterministe,
	 * on prend donc la première transition possible (Normalement c'est la seule qui
	 * est possible)
	 */
	
	public void step(Entity e) {
		int i=0;
		while(i < b.length) {
			b[i].eval(e);
			i++;
		}
	}
}
