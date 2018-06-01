package edu.ricm3.game.sample;

public class Transition {

	
	State entree;
	State sortie;
	Action act;
	
	
	
	public Transition() {
		entree = null;
		sortie = null;
		act = null;
	}
	
	public Transition(State entree, State sortie, Action act) {
		this.entree = entree;
		this.sortie = sortie;
		this.act = act;
	}
}
