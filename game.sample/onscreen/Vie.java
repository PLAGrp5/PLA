package onscreen;

public class Vie extends BonusEtMalus {

	public Vie() {
		this.type = 'V';
	}

	public void jeter(Entity e) {
		if (!(e.inventaireVide())) {
			int i;
			if (e.vie + 5 >= e.vie_max)
				e.vie = e.vie_max;
			else
				e.vie += 5;
			for (i = 0; i < 2; i++)
				e.inventaire[i] = e.inventaire[i + 1];
			e.inventaire[i] = null;
			e.nbre_vie--;
			if (!e.inventaireVide()) {
				if (e.inventaire[0].type == 'V') {
					e.printvie = "Vie_1";
					e.printmine = "mine";
				} else if(e.inventaire[0].type == 'M') {
					e.printmine = "mine_1";
					e.printvie = "Vie";
				}
			} else if (e.inventaireVide()) {
				e.printmine = "mine_0";
				e.printvie = "Vie_0";
			}
			System.out.println("VIE AUGMENTÉE : " + e.vie);
		}
	}
}
