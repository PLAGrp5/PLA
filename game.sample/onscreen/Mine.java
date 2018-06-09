package onscreen;

import ui.*;

public class Mine extends BonusEtMalusFixes {

	public Mine() {
		this.type = 'M';
	}

	public void jeter(Model model, Entity e) {
		if (!(e.inventaireVide())) {
			int i;
			switch (e.dir) {
			case 'U':
				if (e.m_model.m.insertMineOK(e))
					e.m_model.m.insert(new Entity('M', e.p.i + 1, e.p.j));
				break;
			case 'D':
				if (e.m_model.m.insertMineOK(e))
					e.m_model.m.insert(new Entity('M', e.p.i - 1, e.p.j));
				break;
			case 'L':
				if (e.m_model.m.insertMineOK(e))
					e.m_model.m.insert(new Entity('M', e.p.i, e.p.j + 1));
				break;
			case 'R':
				if (e.m_model.m.insertMineOK(e))
					e.m_model.m.insert(new Entity('M', e.p.i, e.p.j - 1));
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
