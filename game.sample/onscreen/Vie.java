package onscreen;

public class Vie extends BonusEtMalusFixes {

	public Vie() {
		this.type = 'V';
	}

	public void jeter(Map m, Entity ent) {
		if (!(ent.inventaireVide())) {
			int i;
			if (ent.vie + 5 >= ent.vie_max)
				ent.vie = ent.vie_max;
			else
				ent.vie += 5;
			for (i = 0; i < 2; i++)
				ent.inventaire[i] = ent.inventaire[i + 1];
			ent.inventaire[i] = null;
			ent.nbre_vie--;
			if (ent.nbre_vie == 0) {
				ent.printvie = "Vie_0";
			}
			else if(ent.inventaire[0].type == 'V') {
				ent.printvie = "Vie_1";
				ent.printmine = "mine";
			}
			else if(ent.inventaire[0].type == 'M') {
				ent.printmine = "mine_1";
				ent.printvie = "Vie";
			}
			System.out.println("VIE AUGMENTÉE : " + ent.vie);
		}
	}
}
