package onscreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import automate.*;
import ui.*;

public class Sbire extends Entity {

	BufferedImage[] m_sprites;
	public Automate comport;

	public Sbire(int i, int j, char dir) {
		super('T', i, j, dir);
	}

	public Sbire(Model model, BufferedImage sprite, int i, int j, char dir, float scale, int dose_couleur,
			Color color) {
		super('T', i, j, dir);
		m_model = model;
		m_sprite = sprite;
		m_scale = scale;
		lastj = p.j;
		lasti = p.i;
		model.m.insert(this);
		m_tank = color;
		jauge_couleur = dose_couleur;
		splitSbiresSprite();
		this.setvie(15);
		this.initinventaire();
	}

	// Récuperation des différente images du sbire dans un tableau à partir des
	// sprites
	void splitSbiresSprite() {
		m_sprites = new BufferedImage[4];
		for (int j = 0; j < 4; j++) {
			int x = j * 32;
			int y = 0;
			m_sprites[j] = m_sprite.getSubimage(x, y, 32, 32);
		}
	}

	// Affichage d'un sbire
	public void paint(Graphics g, char dir) {
		Image img;
		switch (dir) {
		case 'N':
			img = m_sprites[1];
			break;
		case 'S':
			img = m_sprites[3];
			break;
		case 'W':
			img = m_sprites[0];
			break;
		case 'E':
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
