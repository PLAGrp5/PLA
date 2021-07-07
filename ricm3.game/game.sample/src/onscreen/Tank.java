package src.onscreen;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import edu.ricm3.game.sample.*;

/* Classe permettant de modéliser les tanks dans le jeu
 */
public class Tank extends Entity {
	BufferedImage[] m_sprites;
	long m_lastMove;

	public Tank(int i, int j, char dir) {
		super('T', i, j, dir);
	}

	public Tank(Model m, BufferedImage sprite, int i, int j, char dir, float scale) {
		super('T', i, j, dir);
		m_model = m;
		m_sprite = sprite;
		m_scale = scale;
		splitTankSprite();
	}

	/*public void move(Map m, char dir) {
		if (this.dir != dir)
			this.turn(dir);
		else {
			Point p = nextstep(); // calcul nouvel coordonnées
			if (canimove(m, p.i, p.j)) {
				m.free(this.p.i, this.p.j);
				this.p = p;
				m.insert(this);
			} else if (m.map[p.i][p.j].type == 'T')
				this.opposite();
		}
	}*/

	public void step(Map m, char dir) {
		long now = System.currentTimeMillis();
		Action a = new Move(dir, m, this);
		long elapsed = now - m_lastMove;
		if (elapsed > 100L) {
			m_lastMove = now;
			a.execute();
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
		g.drawImage(img, p.j * 32, p.i * 32, w, h, null);
	}
}