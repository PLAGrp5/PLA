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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;

import Parser.Ast;
import Parser.AutomataParser;
import Parser.ParseException;
import automate.Automate;
import framework.GameModel;
import onscreen.Bullet;
import onscreen.Entity;
import onscreen.Map;
import onscreen.Point;
import onscreen.Sbire;
import onscreen.Tank;

public class Model extends GameModel {
	// LinkedList<Square> m_squares;
	// BufferedImage m_cowboySprite;
	// BufferedImage m_explosionSprite;
	// Cowboy[] m_cowboys;
	BufferedImage m_charbleuSprite;
	BufferedImage m_charrougeSprite;
	BufferedImage m_sbirebleuSprite;
	BufferedImage m_sbirerougeSprite;
	BufferedImage m_sbirebleu_hit;
	BufferedImage m_sbirerouge_hit;
	BufferedImage m_char_HitR;
	BufferedImage m_char_HitB;
	BufferedImage m_mur;
	BufferedImage m_mine;
	BufferedImage m_sol;
	BufferedImage m_item;
	BufferedImage m_blue;
	BufferedImage m_red;
	public BufferedImage m_mort;
	public BufferedImage m_portail;
	public BufferedImage m_bullet;

	public String last_touche = "";

	public Map m_Map;

	public int nsbire = 4;
	public Sbire[] sbires = new Sbire[nsbire];

	public int nbullet = 0;
	public Bullet[] bullets = new Bullet[nbullet];

	public int ntank = 2;
	public Tank[] tanks = new Tank[ntank];

	public int nautomate = 3;
	public Automate[] automates = new Automate[nautomate];

	Random rand = new Random();
	Overhead m_overhead = new Overhead();

	public static String fromFile(String fp) {
		File f = null;
		Scanner scan = null;
		String res = "";
		try {
			f = new File(fp);
			scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("File not &Found");
			System.exit(0);
		}
		while (scan.hasNextLine()) {
			res = scan.nextLine();
		}
		return res;
	}

	public static Automate getAut(Automate[] listAut, String fp) {
		String sb = fromFile(fp);
		for (int i = 0; i < listAut.length; i++) {
			if (listAut[i].name.equalsIgnoreCase(sb)) {
				return listAut[i];
			}
		}
		return listAut[0];
	}

	public static int getIndex(Automate[] listAut, String s) {
		for (int i = 0; i < listAut.length; i++) {
			if (listAut[i].name.equalsIgnoreCase(s)) {
				return i;
			}
		}
		return 0;
	}

	public static String[] getList(Automate[] listAut) {
		String[] nameList = new String[listAut.length];
		for (int i = 0; i < listAut.length; i++) {
			nameList[i] = listAut[i].name;
		}
		return nameList;
	}

	public Model(Map m) throws ParseException, FileNotFoundException {
		this.m_Map = m;
		Tank j1, j2;
		Sbire s11, s12, s21, s22;

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

		s11 = new Sbire(this, m_sbirebleuSprite, m_sbirebleu_hit, 2, 2, 'O', 1F, 30, colort);
		s12 = new Sbire(this, m_sbirebleuSprite, m_sbirebleu_hit, 27, 2, 'E', 1F, 30, colort);
		s21 = new Sbire(this, m_sbirerougeSprite, m_sbirerouge_hit, 2, 27, 'O', 1F, 30, colort2);
		s22 = new Sbire(this, m_sbirerougeSprite, m_sbirerouge_hit, 27, 27, 'E', 1F, 30, colort2);

		/*
		 * State e = new State("1");
		 * 
		 * Condition cond = new CondFree(); Condition cond1 = new CondDefault();
		 * 
		 * Action act = new Move(); Action act1 = new Turn('R'); Action act2 = new
		 * Hit();
		 * 
		 * Transition[] trans = new Transition[2]; trans[0] = new Transition(e, e, act,
		 * cond); trans[1] = new Transition(e, e, act1, cond1);
		 * 
		 * Transition[] trans1 = new Transition[2]; trans1[0] = new Transition(e, e,
		 * act2, cond); trans1[1] = new Transition(e, e, act1, cond1);
		 */

		Ast a = new AutomataParser(new BufferedReader(new FileReader("game.parser/example/automata.txt"))).Run();

		automates = (Automate[]) a.make();
		// automates =
		String sb1_1 = "data/automates/sb1_1.txt";
		String sb1_2 = "data/automates/sb1_2.txt";
		String sb2_1 = "data/automates/sb2_1.txt";
		String sb2_2 = "data/automates/sb2_2.txt";

		s11.comport = getAut(automates, sb1_1);
		s11.courant = s11.comport.init;
		s11.num_auto = getIndex(automates, s11.comport.name);
		s11.last_auto = s11.num_auto;
		sbires[0] = s11;

		s12.comport = getAut(automates, sb1_2);
		s12.courant = s12.comport.init;
		s12.num_auto = getIndex(automates, s12.comport.name);
		;
		s12.last_auto = s12.num_auto;
		sbires[1] = s12;

		s21.comport = getAut(automates, sb2_1);
		s21.courant = s21.comport.init;
		s21.num_auto = getIndex(automates, s21.comport.name);
		;
		s21.last_auto = s21.num_auto;
		sbires[2] = s21;

		s22.comport = getAut(automates, sb2_2);
		s22.courant = s22.comport.init;
		s22.num_auto = getIndex(automates, s22.comport.name);
		;
		s22.last_auto = s22.num_auto;
		sbires[3] = s22;

		j1 = new Tank(this, m_charbleuSprite, m_char_HitB, 15, 2, 'E', 1F, 50, colort);
		j2 = new Tank(this, m_charrougeSprite, m_char_HitR, 15, 27, 'O', 1F, 50, colort2);

		tanks[0] = j1;
		tanks[1] = j2;

		tanks[0].sbires_allies[0] = s11;
		tanks[0].sbires_allies[1] = s12;
		tanks[1].sbires_allies[0] = s21;
		tanks[1].sbires_allies[1] = s22;
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
		} else if (e instanceof Sbire) {
			nsbire++;
			if (nsbire > sbires.length) {
				Sbire[] tmp = new Sbire[2 * nsbire];
				System.arraycopy(sbires, 0, tmp, 0, sbires.length);
				sbires = tmp;
			}
			sbires[nsbire - 1] = (Sbire) e;
		}
	}

	public void del(Entity e) {
		m_Map.free(e.p.i, e.p.j);
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
	@Override
	public void step(long now) {
		/*
		 * if ((now - t.m_lastMove) > 200L) { t.comport.step(); t.m_lastMove = now; } if
		 * ((now - t3.m_lastMove) > 200L) { t3.comport.step(); t3.m_lastMove = now;
		 */

		int i;

		for (i = 0; i < ntank; i++) {
			if (tanks[i].aut_bonus && now - tanks[i].m_lastMove > 100L) {
				tanks[i].comport_bonus.step(tanks[i]);
				tanks[i].m_lastMove = now;
				if (++tanks[i].nstep > tanks[i].maxnstep) {
					tanks[i].nstep = 0;
					tanks[i].aut_bonus = false;
					tanks[i].courant = tanks[i].courant_bonus;
				}
			}
		}

		for (i = 0; i < nsbire; i++) {
			if (now - sbires[i].m_lastMove > m_game.set_refresh) {
				if (sbires[i].aut_bonus) {
					sbires[i].comport_bonus.step(sbires[i]);
					if (++sbires[i].nstep > sbires[i].maxnstep) {
						sbires[i].nstep = 0;
						sbires[i].aut_bonus = false;
						sbires[i].courant = sbires[i].courant_bonus;
					}
				} else
					sbires[i].comport.step(sbires[i]);
				sbires[i].m_lastMove = now;
			}
		}

		for (i = 0; i < nbullet; i++) {
			if (now - bullets[i].m_lastMove > 100L) {
				bullets[i].comport.step(bullets[i]);
				bullets[i].m_lastMove = now;
			}
		}
		if (now - m_Map.lastBonus > 10000L) {
			m_Map.lastBonus = now;
			m_Map.insertBonus();
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

	public Entity GetEntity(Point p) {
		return m_Map.map[p.i][p.j];
	}

	private void loadSprites() {

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

		imageFile = new File("game.sample/sprites/char_hitB.png");
		try {
			m_char_HitB = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.sample/sprites/char_hitR.png");
		try {
			m_char_HitR = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.sample/sprites/sbireb.png");
		try {
			m_sbirebleuSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.sample/sprites/sbirer.png");
		try {
			m_sbirerougeSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.sample/sprites/sbireb_hit.png");
		try {
			m_sbirebleu_hit = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.sample/sprites/sbirer_hit.png");
		try {
			m_sbirerouge_hit = ImageIO.read(imageFile);
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

		imageFile = new File("game.sample/sprites/mort.png");
		try {
			m_mort = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.sample/sprites/portail.png");
		try {
			m_portail = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
}
