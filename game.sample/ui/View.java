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
import java.util.Iterator;

import framework.GameView;

public class View extends GameView {

	private static final long serialVersionUID = 1L;
	public static final int NBRE_ROW = 30;
	public static final int NBRE_COL = 30;

	Color m_background = Color.white;
	Color m_line = Color.black;
	Color m_tankb = Color.cyan;
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
		g.setColor(m_background);
		g.fillRect(0, 0, getWidth(), getHeight());
		int rectWidth = 32;
		int rectHeight = 32;

		g.setColor(m_line);
		/*
		 * for (int i = 0; i < NBRE_ROW; i++) { g.drawLine(0, i * rectHeight,
		 * getWidth(), i * rectHeight); } for (int j = 0; j < NBRE_COL; j++) {
		 * g.drawLine(j * rectWidth, 0, j * rectWidth, getHeight()); }
		 */
		g.setColor(m_line);
		for (int i = 0; i < NBRE_ROW; i++) {
			for (int j = 0; j < NBRE_COL; j++) {
				g.drawRect(i * 32, j * 32, 32, 32);
				/*
				 * if (i == j) { g.fillRect(i*32, j*32, 32, 32); }
				 */
			}
		}

		g.setColor(Color.cyan);
		g.fillRect(m_model.t4.p.j * 32, m_model.t4.p.i * 32, 32, 32);
		m_model.t4.paint(g, m_model.t4.dir);
		
		g.setColor(Color.orange);
		g.fillRect(m_model.t2.p.j * 32, m_model.t2.p.i * 32, 32, 32);
		m_model.t2.paint(g, m_model.t2.dir);
		for (int i = 0; i < 2; i++) {
			g.setColor(Color.gray);
			g.fillRect(m_model.ent[i].p.j * 32, m_model.ent[i].p.i * 32, 32, 32);
			m_model.ent[i].paint(g, m_model.ent[i].dir);
		}
	}

}
