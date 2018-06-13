package onscreen;

public class Mine extends BonusEtMalus {

	public Mine() {
		this.type = 'M';
	}

	public void jeter(Entity e) {
		if (!(e.inventaireVide())) {
			int i;
			Point p = new Point(e.p);
			switch (e.dir) {
			case 'N':
				p.i++;
				break;
			case 'S':
				p.i--;
				break;
			case 'W':
				p.j++;
				break;
			default:
				p.j--;
				break;
			}
			if (e.m_model.m_Map.insertMineOK(e))
				e.m_model.m_Map.insert(new Entity('M', p.i, p.j));
			for (i = 0; i < 2; i++)
				e.inventaire[i] = e.inventaire[i + 1];
			e.inventaire[i] = null;
			e.nbre_mine--;
			if (!e.inventaireVide()) {
				if (e.inventaire[0].type == 'V') {
					e.printvie = "Vie_1";
					e.printmine = "mine";
				} else if (e.inventaire[0].type == 'M') {
					e.printmine = "mine_1";
					e.printvie = "Vie";
				}
			} else if (e.inventaireVide()) {
				e.printmine = "mine_0";
				e.printvie = "Vie_0";
			}
			System.out.println("MINE POSÉE");
		}
	}

}
