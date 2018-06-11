/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.awt.Rectangle;
import framework.GameView;
import java.awt.TexturePaint;

public class View extends GameView {

	private static final long serialVersionUID = 1L;
	public static final int NBRE_ROW = 30;
	public static final int NBRE_COL = 30;

	Color m_background = Color.white;
	Color m_line = Color.black;
	Color colorb = Color.cyan;
	long m_last;
	int m_npaints;
	int m_fps;
	Model m_model;
	Controller m_ctr;

	public View(Model m, Controller c) {
		m_model = m;
		m_ctr = c;
	}

	private void computeFPS() {
		long now = System.currentTimeMillis();
		if (now - m_last > 1000L) {
			m_fps = m_npaints;
			m_last = now;
			m_npaints = 0;
		}
		m_game.setFPS(m_fps, "npaints=" + m_npaints);
		m_npaints++;
	}

	@Override
	protected void _paint(Graphics g) {
		computeFPS();
		// erase background

		m_game.drawPLayer1Panel(m_model.tanks[0], m_model.sbires[0], m_model.sbires[1], m_model.m_Map.scorebleu(),
				m_model.tanks[0].printvie, m_model.tanks[0].printmine, m_model.tanks[0].printsbire,
				m_model.tanks[0].nbre_mine, m_model.tanks[0].nbre_vie);
		m_game.drawPLayer2Panel(m_model.tanks[1], m_model.m_Map.scorerouge(), m_model.tanks[1].printvie,
				m_model.tanks[1].printmine, m_model.tanks[1].printsbire, m_model.tanks[1].nbre_mine,
				m_model.tanks[1].nbre_vie);

		g.setColor(m_background);
		g.fillRect(0, 0, getWidth(), getHeight());


		/*
		 * g.setColor(m_line);
		 * 
		 * for (int i = 0; i < NBRE_ROW; i++) { g.drawLine(0, i * rectHeight,
		 * getWidth(), i * rectHeight); } for (int j = 0; j < NBRE_COL; j++) {
		 * g.drawLine(j * rectWidth, 0, j * rectWidth, getHeight()); }
		 */

		// Parcours de notre tableau color (contenu dans map)
		for (int k = 0; k < NBRE_ROW; k++) {
			for (int l = 0; l < NBRE_COL; l++) {

				// Pour chaque case on colorie avec la texture associé

				if (m_model.m_Map.color[k][l] == 'W') {
					g.drawImage(m_model.m_mur, 32 * l, 32 * k, 32, 32, null);
				}
				if (m_model.m_Map.color[k][l] == 'F') {
					g.drawImage(m_model.m_sol, 32 * l, 32 * k, 32, 32, null);
				}
				if (m_model.m_Map.color[k][l] == 'B') {
					g.drawImage(m_model.m_blue, 32 * l, 32 * k, 32, 32, null);
				}
				if (m_model.m_Map.color[k][l] == 'R') {
					g.drawImage(m_model.m_red, 32 * l, 32 * k, 32, 32, null);
				}
				if (m_model.m_Map.map[k][l].type == 'I') {
					g.drawImage(m_model.m_item, 32 * l, 32 * k, 32, 32, null);
				}
				if (m_model.m_Map.map[k][l].type == 'M') {
					g.drawImage(m_model.m_mine, 32 * l, 32 * k, 32, 32, null);
				}
			}
		}
		// m_model.m.print();

		m_model.tanks[0].paint(g, m_model.tanks[0].dir);
		m_model.tanks[1].paint(g, m_model.tanks[1].dir);
		for (int i = 0; i < m_model.nsbire; i++) {
			m_model.sbires[i].paint(g, m_model.sbires[i].dir);
		}
		for (int i = 0; i < m_model.nbullet; i++) {
			m_model.bullets[i].paint(g, m_model.bullets[i].dir);
		}

		/*
		 * g.setColor(m_line); for (int i = 0; i < NBRE_ROW; i++) { for (int j = 0; j <
		 * NBRE_COL; j++) { g.drawRect(i * 32, j * 32, 32, 32); /* if (i == j) {
		 * g.fillRect(i*32, j*32, 32, 32); }
		 */
		// }
		// }
	}

}
