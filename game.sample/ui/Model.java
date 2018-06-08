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
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;

import framework.*;
import onscreen.*;

public class Model extends GameModel {
	// LinkedList<Square> m_squares;
	// BufferedImage m_cowboySprite;
	// BufferedImage m_explosionSprite;
	// Cowboy[] m_cowboys;
	BufferedImage m_charbleuSprite;
	BufferedImage m_charrougeSprite;
	BufferedImage m_mur;
	BufferedImage m_mine;
	BufferedImage m_sol;
	BufferedImage m_item;
	BufferedImage m_blue;
	BufferedImage m_red;
	public BufferedImage m_bullet;
	// Cowboy[] m_cowboys;
	public Map m;

	public int nsbire = 2;
	public Sbire[] sbires = new Sbire[nsbire];

	public int nbullet = 0;
	public Bullet[] bullets = new Bullet[nbullet];

	/*public int nent = 2;
	public Entity[] ent = new Entity[nent];*/

	public Automate[] automates = new Automate[2];
	Tank t2, t4;
	Sbire s, s3;
	Random rand = new Random();
	Overhead m_overhead = new Overhead();

	public Model(Map m) {
		this.m = m;

		loadSprites();
		/*
		 * m_cowboys = new Cowboy[Options.MAX_NCOWBOYS]; for (int i = 0; i <
		 * m_cowboys.length; i++) { m_cowboys[i] = new Cowboy(this, i, m_cowboySprite,
		 * 4, 6, 100 + i * 100, 200 + i * 100, 3F);
		 * m_cowboys[i].setExplosion(m_explosionSprite, 11, 10); }
		 * 
		 * m_squares = new LinkedList<Square>(); for (int i = 0; i < Options.NSQUARES;
		 * i++) m_squares.add(new Square(this, rand.nextInt(200), rand.nextInt(200)));
		 */
		Color colort = Color.cyan;
		Color colort2 = Color.orange;
		Color coloria = Color.gray;
		
		s = new Sbire(m, m_charbleuSprite, 1, 10, 'L', 1F, 30, coloria);

		State e = new State("1");

		Condition cond = new CondFree(m);
		Condition cond1 = new CondDefault(m);
		
		Action act = new Move();
		Action act1 = new Turn();

		Transition[] trans = new Transition[2];
		trans[0] = new Transition(e, e, act, cond);
		trans[1] = new Transition(e, e, act1, cond1);

		Automate a = new Automate(e, trans);

		s.comport = a;
		s.courant = e;
		sbires[0] = s;
		
		t2 = new Tank(m, m_charrougeSprite, 5, 15, 'L', 1F, 30 , colort2);
		t2.aut_bonus = false;

		t4 = new Tank(m, m_charbleuSprite, 8, 19, 'L', 1F, 30, colort);
		t4.aut_bonus = false;

		s3 = new Sbire(m, m_charbleuSprite, 6, 28, 'L', 1F, 30,coloria);

		/*
		 * Action act1 = new Move('L', m); Transition trans1 = new Transition(e, e,
		 * act1, cond); Automate a1 = new Automate(e, trans1);
		 */

		s3.comport = a;
		s3.courant = e;
		sbires[1] = s3;

		// Parte test Bullet

		// m_point2 = new point(this, m_charrougeSprite, 32,32, 1F);
	}

	@Override
	public void shutdown() {

	}

	public Overhead getOverhead() {
		return m_overhead;
	}

	public void add(Entity e) {
		if (e instanceof Bullet) {
			nbullet++;
			if (nbullet > bullets.length) {
				Bullet[] tmp = new Bullet[2 * nbullet];
				System.arraycopy(bullets, 0, tmp, 0, bullets.length);
				bullets = tmp;
			}
			bullets[nbullet - 1] = (Bullet) e;
		} else if (e instanceof Tank) {
			nsbire++;
			if (nsbire > sbires.length) {
				Sbire[] tmp = new Sbire[2 * nsbire];
				System.arraycopy(sbires, 0, tmp, 0, sbires.length);
				sbires = tmp;
			}
			sbires[nsbire - 1] = (Sbire)e;
		}
	}

	public void del(Entity e) {
		m.free(e.p.i, e.p.j);
		if (e instanceof Bullet) {
			int i;
			for (i = 0; i < nbullet && !bullets[i].equals(e); i++)
				;
			if (i < nbullet) {
				nbullet--;
				for (; i < nbullet; i++)
					bullets[i] = bullets[i + 1];
			}
		}
	}

	/*
	 * public Cowboy[] cowboys() { return m_cowboys; }
	 * 
	 * public Iterator<Square> squares() { return m_squares.iterator(); }
	 */
	/**
	 * Simulation step.
	 * 
	 * @param now is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {
		/*
		 * if ((now - t.m_lastMove) > 200L) { t.comport.step(); t.m_lastMove = now; } if
		 * ((now - t3.m_lastMove) > 200L) { t3.comport.step(); t3.m_lastMove = now;
		 */
		for (int i = 0; i < nsbire; i++) {
			if (now - sbires[i].m_lastMove > 200L) {
				sbires[i].comport.step(sbires[i]);
				sbires[i].m_lastMove = now;
			}
		}

		for (int i = 0; i < nbullet; i++) {
			if (now - bullets[i].m_lastMove > 100L) {
				bullets[i].comport.step(bullets[i]);
				bullets[i].m_lastMove = now;
			}
		}

		// }
		/*
		 * m_overhead.overhead();
		 * 
		 * Iterator<Square> iter = m_squares.iterator(); while (iter.hasNext()) { Square
		 * s = iter.next(); s.step(now); } for (int i = 0; i < m_cowboys.length; i++)
		 * m_cowboys[i].step(now);
		 */
	}

	private void loadSprites() {
		/*
		 * Cowboy with rifle, western style; png; 48x48 px sprite size Krasi Wasilev (
		 * http://freegameassets.blogspot.com)
		 */
		/*
		 * File imageFile = new File("game.sample/sprites/winchester.png"); try {
		 * m_cowboySprite = ImageIO.read(imageFile); } catch (IOException ex) {
		 * ex.printStackTrace(); System.exit(-1); } /* Long explosion set; png file;
		 * 64x64 px sprite size Krasi Wasilev ( http://freegameassets.blogspot.com)
		 * 
		 * imageFile = new File("game.sample/sprites/explosion01_set_64.png"); try {
		 * m_explosionSprite = ImageIO.read(imageFile); } catch (IOException ex) {
		 * ex.printStackTrace(); System.exit(-1); }
		 */

		File imageFile = new File("game.sample/sprites/charb.png");
		try {
			m_charbleuSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.sample/sprites/charr.png");
		try {
			m_charrougeSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.sample/sprites/mur.png");
		try {
			m_mur = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.sample/sprites/mine.png");
		try {
			m_mine = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.sample/sprites/bullets.png");
		try {
			m_bullet = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		
		imageFile = new File("game.sample/sprites/sol.png");
		try {
			m_sol = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		
		imageFile = new File("game.sample/sprites/item.png");
		try {
			m_item = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		
		imageFile = new File("game.sample/sprites/blue.png");
		try {
			m_blue = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		
		imageFile = new File("game.sample/sprites/red.png");
		try {
			m_red = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
}
