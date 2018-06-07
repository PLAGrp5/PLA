package onscreen;

/*
 * Classe qui désigne un automate, elle contient un tableau de transition
 */

public class Automate {
	//Nos automate sont partagé, ainsi ,l'état courant est directement contenu dans notre entité
	// State courant; 
	Transition[] t;

	public Automate(State e, Transition[] t) {
		// courant = e;
		this.t = new Transition[4];
		this.t = t;
	}

	/*
	 * Execution de la première transition possible
	 * cad que:
	 * - l'etat source necessaire à la transition est notre etat courant
	 * - la condition pour réaliser l'action est respecté
	 * Remarque: Ici notre automate est déterministe, on prend donc la première transition possible
	 * 			(Normalement c'est la seule qui est possible)
	 */
	public void step(Entity e) {
		e.lasti = e.p.i;
		e.lastj = e.p.j;
		int i = 0;
		while ((i < t.length) && !((t[i].src == e.courant) && (t[i].cond.eval(e)))) {
			i++;
		}
		if (i < t.length) {
			t[i].act.execute(e);
			e.courant = t[i].dest;
		}
	}
}
