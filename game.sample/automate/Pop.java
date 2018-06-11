package automate;

import java.awt.Color;

import ui.*;
import onscreen.*;

public class Pop extends Action {

	public Pop(Model model) {
		this.model = model;
	}

	public void execute(Entity e) {
		this.model = e.m_model;
		// On réalise l'action Pop seulement si jauge de couleur suffisante
		if (e.jauge_couleur >= 4) {

			// On colorie la croix associé à l'action Pop

			/*
			 * Pour chaque case à colorier: on regarde si la case est coloriable si elle
			 * l'est, on regarde si la nouvelle couleur est différente de l'actuelle On ne
			 * colorie que les cases dont la couleur changera cela consommera une recharge
			 * de peinture tout le temps 4 doses de peintures
			 */

			// La croix du bas
			if (model.m.color[e.p.i - 1][e.p.j] == 'F' || model.m.color[e.p.i - 1][e.p.j] == 'B'
					|| model.m.color[e.p.i - 1][e.p.j] == 'R') {
				if ((e.m_tank == Color.cyan) && (model.m.color[e.p.i - 1][e.p.j] != 'B')) {
					model.m.color[e.p.i - 1][e.p.j] = 'B';
				} else if ((e.m_tank == Color.orange) && (model.m.color[e.p.i - 1][e.p.j] != 'R')) {
					model.m.color[e.p.i - 1][e.p.j] = 'R';
				}
			}

			// La croix du haut
			if (model.m.color[e.p.i + 1][e.p.j] == 'F' || model.m.color[e.p.i + 1][e.p.j] == 'B'
					|| model.m.color[e.p.i + 1][e.p.j] == 'R') {
				if ((e.m_tank == Color.cyan) && (model.m.color[e.p.i + 1][e.p.j] != 'B')) {
					model.m.color[e.p.i + 1][e.p.j] = 'B';
				} else if ((e.m_tank == Color.orange) && (model.m.color[e.p.i + 1][e.p.j] != 'R')) {
					model.m.color[e.p.i + 1][e.p.j] = 'R';
				}
			}

			// La croix de gauche
			if (model.m.color[e.p.i][e.p.j - 1] == 'F' || model.m.color[e.p.i][e.p.j - 1] == 'B'
					|| model.m.color[e.p.i][e.p.j - 1] == 'R') {
				if ((e.m_tank == Color.cyan) && (model.m.color[e.p.i][e.p.j - 1] != 'B')) {
					model.m.color[e.p.i][e.p.j - 1] = 'B';
				} else if ((e.m_tank == Color.orange) && (model.m.color[e.p.i][e.p.j - 1] != 'R')) {
					model.m.color[e.p.i][e.p.j - 1] = 'R';
				}
			}

			// La croix de droite
			if (model.m.color[e.p.i][e.p.j + 1] == 'F' || model.m.color[e.p.i][e.p.j + 1] == 'B'
					|| model.m.color[e.p.i][e.p.j + 1] == 'R') {
				if ((e.m_tank == Color.cyan) && (model.m.color[e.p.i][e.p.j + 1] != 'B')) {
					model.m.color[e.p.i][e.p.j + 1] = 'B';
				} else if ((e.m_tank == Color.orange) && (model.m.color[e.p.i][e.p.j + 1] != 'R')) {
					model.m.color[e.p.i][e.p.j + 1] = 'R';
				}
			}
			e.jauge_couleur -= 4;

		}

	}
}
