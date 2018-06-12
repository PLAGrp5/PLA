package onscreen;

public abstract class BonusEtMalus {
	public char type;

	// renvoie false si inventaire plein
	public boolean prendre(Entity ent) {
		if (ent instanceof Tank && ((!ent.sbires_allies[0].alive) || (!ent.sbires_allies[1].alive))) {
			ent.printsbire = "sbire_bonus_0";
			if(!ent.sbires_allies[0].alive) {
				ent.sbires_allies[0].setvie(5);
				ent.sbires_allies[0].jauge_couleur = 15;
				ent.sbires_allies[0].alive = true;
			} else {
				ent.sbires_allies[1].setvie(5);
				ent.sbires_allies[1].jauge_couleur = 15;
				ent.sbires_allies[1].alive = true;
			}
		}
		else if (ent.inventaire[0] == null) {
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

	public void jeter(Entity e) {
	}

}
