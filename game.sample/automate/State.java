package automate;

/*
 * Type d'un etat (chaine de caractere)
 */

public class State {

	String nom;
	
	public State(String nom) {
		this.nom = nom;
	}
	
	public boolean compare(String nom2) {
		return nom.equals(nom2);
	}
}