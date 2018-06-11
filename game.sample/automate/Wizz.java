package automate;

import onscreen.Entity;

public class Wizz extends Action{
	public Wizz() {
	}
	
	public void execute(Entity e) {
			//On recharge la couleur de cinq unités de peinture (modifiable)
			e.jauge_couleur += 5;
			
			//On limite la réserve de couleur à 30 (modifiable)
			if(e.jauge_couleur > 30) {
				e.jauge_couleur = 30;
			}
		
	}
}
