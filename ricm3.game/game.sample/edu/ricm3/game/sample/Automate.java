package edu.ricm3.game.sample;

public class Automate {
	
	Transition[] t;
	State etat_courant;
	
	public Automate() {
		System.out.println("Constructeur sans args");
		t = null;
		etat_courant = null;
	}
	
	public Automate(Transition[] t, State etat_courant) {
		this.t = t;
		this.etat_courant = etat_courant;
	}
	
	
	public static void step() {
		
	}
																																																																										
}
