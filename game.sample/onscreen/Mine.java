package onscreen;

public class Mine extends BonusEtMalusFixes {

	public Mine() {
		this.type = 'M';
	}

	public void jeter(Map m, Entity ent) {
		if (!(ent.inventaireVide())) {
			int i;
			switch (ent.dir) {
			case 'U':
				if (m.insertMineOK(ent))
					m.insert(new Entity('M', ent.p.i + 1, ent.p.j));
				break;
			case 'D':
				if (m.insertMineOK(ent))
					m.insert(new Entity('M', ent.p.i - 1, ent.p.j));
				break;
			case 'L':
				if (m.insertMineOK(ent))
					m.insert(new Entity('M', ent.p.i, ent.p.j + 1));
				break;
			case 'R':
				if (m.insertMineOK(ent))
					m.insert(new Entity('M', ent.p.i, ent.p.j - 1));
				break;
			}
			for (i = 0; i < 2; i++)
				ent.inventaire[i] = ent.inventaire[i + 1];
			ent.inventaire[i] = null;
			ent.nbre_mine--;
			if (ent.nbre_mine == 0) {
				ent.printmine = "mine_0";
			}
			else if(ent.inventaire[0].type == 'V') {
				ent.printvie = "Vie_1";
				ent.printmine = "mine";
			}
			else if(ent.inventaire[0].type == 'M') {
				ent.printmine = "mine_1";
				ent.printvie = "Vie";
			}
			System.out.println("MINE POSÉE");
		}
	}

}
