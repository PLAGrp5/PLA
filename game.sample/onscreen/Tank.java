package onscreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import automate.*;

/* Classe permettant de modéliser les tanks dans le jeu
 */
public class Tank extends Entity {
	BufferedImage[] m_sprites;

	public Tank(int i, int j, char dir) {
		super('T', i, j, dir);
	}

	public Tank(Map m, BufferedImage sprite, int i, int j, char dir, float scale, int dose_couleur, Color color) {
		super('T', i, j, dir);
		m_map = m;
		m_sprite = sprite;
		m_scale = scale;
		lastj = p.j;
		lasti = p.i;
		m.insert(this);
		m_tank = color;
		jauge_couleur = dose_couleur;
		splitTankSprite();
		this.setvie(15);
		this.initinventaire();
	}

	/*
	 * Réalise l'action du tank Step est appelé depuis le controller en fonction des
	 * touches enfoncé
	 */
	public void move(Map m, char dir, char type_action) {
		long now = System.currentTimeMillis();
		long elapsed = now - m_lastMove;
		if (elapsed > 100L) {
			lastj = p.j;
			lasti = p.i;
			m_lastMove = now;

			/*
			 * Si notre jauge de couleur n'est pas vide et que l'action est un mouvement On
			 * colorie la case précedente seulement si elle change de couleur
			 */
			if ((jauge_couleur > 0) && (type_action == 'm')) {
				if (m.color[p.i][p.j] == 'F' || m.color[p.i][p.j] == 'B' || m.color[p.i][p.j] == 'R') {
					if ((m_tank == Color.cyan) && (m.color[p.i][p.j] != 'B')) {
						m.color[p.i][p.j] = 'B';
						jauge_couleur--;
					} else if ((m_tank == Color.orange) && (m.color[p.i][p.j] != 'R')) {
						m.color[p.i][p.j] = 'R';
						jauge_couleur--;
					}
				}
			}

			if (aut_bonus) {
				comport_bonus.step(this);
			} else {
				Action a;
				if (type_action == 'p') {
					a = new Pop(m_map);
				} else if (type_action == 'w') {
					a = new Wizz();
				} else { // if(type_action == 'm')
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
				}
				a.execute(this);
			}
		}
	}

	// Récuperation des différente images du tank dans un tableau à partir des
	// sprites
	void splitTankSprite() {
		m_sprites = new BufferedImage[4];
		for (int j = 0; j < 4; j++) {
			int x = j * 32;
			int y = 0;
			m_sprites[j] = m_sprite.getSubimage(x, y, 32, 32);
		}
	}

	// Affichage d'un tank
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
