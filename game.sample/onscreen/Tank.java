package onscreen;

import ui.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/* Classe permettant de modéliser les tanks dans le jeu
 */
public class Tank extends Entity {
	BufferedImage[] m_sprites;

	public Tank(int i, int j, char dir) {
		super('T', i, j, dir);
	}

	public Tank(Map m, BufferedImage sprite, int i, int j, char dir, float scale, Color color) {
		super('T', i, j, dir);
		m_map = m;
		m_sprite = sprite;
		m_scale = scale;
		lastj = p.j;
		lasti = p.i;
		this.color = color;
		splitTankSprite();
		m.insert(this);
	}

	public Tank(Map m, BufferedImage sprite, int i, int j, char dir, float scale, Automate a) {
		super('T', i, j, dir);
		m_map = m;
		m_sprite = sprite;
		m_scale = scale;
		comport = a;
		splitTankSprite();
		m.insert(this);
	}

	/*
	 * public void move(Map m, char dir) { if (this.dir != dir) this.turn(dir); else
	 * { Point p = nextstep(); // calcul nouvel coordonnées if (canimove(m, p.i,
	 * p.j)) { m.free(this.p.i, this.p.j); this.p = p; m.insert(this); } else if
	 * (m.map[p.i][p.j].type == 'T') this.opposite(); } }
	 */

	public void hit(Model model) {
		new Hit().execute(model, this);
	}

	public void step(Map m, char dir) {
		long now = System.currentTimeMillis();
		long elapsed = now - m_lastMove;
		if (elapsed > 100L) {
			lastj = p.j;
			lasti = p.i;
			m_lastMove = now;
			if (aut) {
				comport.step(this);
			} else {
				Action a;
				switch (dir) {
				case 'U':
					a = new Move('U', m_map);
					break;
				case 'D':
					a = new Move('D', m_map);
					break;
				case 'L':
					a = new Move('L', m_map);
					break;
				case 'R':
					a = new Move('R', m_map);
					break;
				default:
					a = new Move('U', m_map);
					break;
				}
				a.execute(this);
			}
		}
	}

	void splitTankSprite() {
		m_sprites = new BufferedImage[4];
		for (int j = 0; j < 4; j++) {
			int x = j * 32;
			int y = 0;
			m_sprites[j] = m_sprite.getSubimage(x, y, 32, 32);
		}
	}

	public void paint(Graphics g, char dir) {
		Image img;
		switch (dir) {
		case 'U':
			img = m_sprites[1];
			break;
		case 'D':
			img = m_sprites[3];
			break;
		case 'L':
			img = m_sprites[0];
			break;
		case 'R':
			img = m_sprites[2];
			break;
		default:
			img = m_sprites[0];
		}
		int w = (int) (m_scale * 32);
		int h = (int) (m_scale * 32);
		if (!aut) {
			g.setColor(color);
			g.fillRect(lastj * 32, lasti * 32, 32, 32);
			g.drawImage(img, p.j * 32, p.i * 32, w, h, null);
		} else {
			g.setColor(Color.white);
			g.fillRect(lastj * 32, lasti * 32, 32, 32);
			g.drawImage(img, p.j * 32, p.i * 32, w, h, null);
		}
	}
}