package onscreen;

public class Wizz extends Action{
	public Wizz() {
	}
	
	public void execute(Entity e) {
			//On recharge la couleur de deux unités
			e.jauge_couleur += 2;
			
			//On limite la réserve de couleur à 30
			if(e.jauge_couleur > 30) {
				e.jauge_couleur = 30;
			}
		
	}
}
