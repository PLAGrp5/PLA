package onscreen;

public class Mine extends BonusEtMalus {

	public Mine() {
		this.type = 'M';
	}

	public void jeter(Entity e) {
		if (!(e.inventaireVide())) {
			int i;
			switch (e.dir) {
			case 'N':
				if (e.m_model.m_Map.insertMineOK(e))
					e.m_model.m_Map.insert(new Entity('M', e.p.i + 1, e.p.j));
				break;
			case 'S':
				if (e.m_model.m_Map.insertMineOK(e))
					e.m_model.m_Map.insert(new Entity('M', e.p.i - 1, e.p.j));
				break;
			case 'W':
				if (e.m_model.m_Map.insertMineOK(e))
					e.m_model.m_Map.insert(new Entity('M', e.p.i, e.p.j + 1));
				break;
			case 'E':
				if (e.m_model.m_Map.insertMineOK(e))
					e.m_model.m_Map.insert(new Entity('M', e.p.i, e.p.j - 1));
				break;
			}
			for (i = 0; i < 2; i++)
				e.inventaire[i] = e.inventaire[i + 1];
			e.inventaire[i] = null;
			e.nbre_mine--;
			if (e.nbre_mine == 0) {
				e.printmine = "mine_0";
			} else if (e.inventaire[0].type == 'V') {
				e.printvie = "Vie_1";
				e.printmine = "mine";
			} else if (e.inventaire[0].type == 'M') {
				e.printmine = "mine_1";
				e.printvie = "Vie";
			}
			System.out.println("MINE POSÉE");
		}
	}

}
