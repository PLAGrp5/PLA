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
			System.out.println("VIE AUGMENTÉE : " + ent.vie);
		}
	}
}
