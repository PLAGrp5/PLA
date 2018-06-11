package onscreen;

import ui.*;

public abstract class BonusEtMalusFixes {
	public char type;

	// renvoie false si inventaire plein
	public boolean prendre(Entity ent) {
		if (ent.inventaire[0] == null) {
			ent.inventaire[0] = this;
			if (this.type == 'M') {
				ent.printmine = "mine_1";
				ent.nbre_mine++;
			} else if (this.type == 'V') {
				ent.printvie = "Vie_1";
				ent.nbre_vie++;
			}
		} else if (ent.inventaire[1] == null) {
			ent.inventaire[1] = this;
			if (this.type == 'M') {
				if (ent.inventaire[0].type == 'M') {
					ent.nbre_mine++;
				} else {
					ent.printmine = "mine";
					ent.nbre_mine++;
				}
			} else if (this.type == 'V') {
				if (ent.inventaire[0].type == 'V') {
					ent.nbre_vie++;
				} else {
					ent.printvie = "Vie";
					ent.nbre_vie++;
				}
			}
		} else if (ent.inventaire[2] == null) {
			ent.inventaire[2] = this;
			if (this.type == 'M') {
				if (ent.inventaire[0].type == 'M' || ent.inventaire[1].type == 'M') {
					ent.nbre_mine++;
				} else {
					ent.printmine = "mine";
					ent.nbre_mine++;
				}
			} else if (this.type == 'V') {
				if (ent.inventaire[0].type == 'V' || ent.inventaire[1].type == 'V') {
					ent.nbre_vie++;
				} else {
					ent.printvie = "Vie";
					ent.nbre_vie++;
				}
			}
		} else
			return false;
		return true;
	}

	public abstract void jeter(Model model, Entity ent);
}
