package automate;

import onscreen.Entity;

public class Wizz extends Action {
	public Wizz() {
	}

	public void execute(Entity e) {
		// On recharge la couleur de dix unités de peinture (modifiable)
		e.jauge_couleur += 10;

		// On limite la réserve de couleur à 50 (modifiable)
		if (e.jauge_couleur > 50)
			e.jauge_couleur = 50;

	}
}
