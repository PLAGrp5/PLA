package edu.ricm3.game.sample;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class point {
	BufferedImage m_sprite;
	int m_w, m_h;
	int m_x, m_y;
	int m_nrows, m_ncols;
	int m_step;
	int m_nsteps;
	int m_idx;
	float m_scale;
	long m_lastMove, m_lastReverse;
	Model m_model;

	point(Model model, BufferedImage sprite, int x, int y, float scale) {
		m_model = model;
		m_sprite = sprite;
		m_x = x;
		m_y = y;
		m_scale = scale;
	}

/*	void step(long now) {
		long elapsed = now - m_lastMove;
		if (elapsed > 60L) {
			m_lastMove = now;
			int nsteps = m_nrows * m_ncols;
			if (m_nsteps < nsteps) {
				m_x += m_step;
				m_nsteps++;
			} else if (m_nsteps < 2 * nsteps) {
				m_x -= m_step;
				m_nsteps++;
			} else {
				m_nsteps = 0;
			}
			m_idx = (m_idx + 1) % m_sprites.length;
		}
	}*/

	/**
	 * paints this square on the screen.
	 * 
	 * @param g
	 */
	void paint(Graphics g) {

		Image img = m_sprite;
		int w = (int) (m_scale * 32);
		int h = (int) (m_scale * 32);
		g.drawImage(img, m_x, m_y, w, h, null);

	}

}
