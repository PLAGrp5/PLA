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
	//LinkedList<Square> m_squares;
	//BufferedImage m_cowboySprite;
	//BufferedImage m_explosionSprite;
	BufferedImage m_charbleuSprite;
	BufferedImage m_charrougeSprite;
	//Cowboy[] m_cowboys;

	Entity[] ent = new Entity[2];
	Tank t, t3, t4;
	public Map m;
	Tank t2;
	Random rand = new Random();
	Overhead m_overhead = new Overhead();

	public Model(Map m) {
		this.m = m;

		loadSprites();
		/*m_cowboys = new Cowboy[Options.MAX_NCOWBOYS];
		for (int i = 0; i < m_cowboys.length; i++) {
			m_cowboys[i] = new Cowboy(this, i, m_cowboySprite, 4, 6, 100 + i * 100, 200 + i * 100, 3F);
			m_cowboys[i].setExplosion(m_explosionSprite, 11, 10);
		}

		m_squares = new LinkedList<Square>();
		for (int i = 0; i < Options.NSQUARES; i++)
			m_squares.add(new Square(this, rand.nextInt(200), rand.nextInt(200)));*/

		t = new Tank(this, m_charbleuSprite, 1, 10, 'L', 1F);

		State e = new State("1");
		Condition cond = new Condition();
		Action act = new Move('D', m, t);
		Transition trans = new Transition(e, e, act, cond);
		Automate a = new Automate(e, trans);

		t.comport = a;
		t.aut = true;
		ent[0] = t;

		t2 = new Tank(this, m_charrougeSprite, 5, 15, 'L', 1F);
		t2.aut = false;
		
		t4 = new Tank(this, m_charbleuSprite, 8, 19, 'L', 1F);
		t4.aut = false;

		t3 = new Tank(this, m_charbleuSprite, 6, 28, 'L', 1F);

		Action act1 = new Move('L', m, t3);
		Transition trans1 = new Transition(e, e, act1, cond);
		Automate a1 = new Automate(e, trans1);

		t3.comport = a1;
		t3.aut = true;
		ent[1] = t3;

		// m_point2 = new point(this, m_charrougeSprite, 32,32, 1F);
	}

	@Override
	public void shutdown() {

	}

	public Overhead getOverhead() {
		return m_overhead;
	}
/*
	public Cowboy[] cowboys() {
		return m_cowboys;
	}

	public Iterator<Square> squares() {
		return m_squares.iterator();
	}
*/
	/**
	 * Simulation step.
	 * 
	 * @param now
	 *          is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {
		/*
		 * if ((now - t.m_lastMove) > 200L) { t.comport.step(); t.m_lastMove = now; } if
		 * ((now - t3.m_lastMove) > 200L) { t3.comport.step(); t3.m_lastMove = now;
		 */
		for(int i =0; i<2; i++){
			if (now - ent[i].m_lastMove > 200L) {
				ent[i].comport.step();
				ent[i].m_lastMove = now;
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
		/*File imageFile = new File("game.sample/sprites/winchester.png");
		try {
			m_cowboySprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		/*
		 * Long explosion set; png file; 64x64 px sprite size Krasi Wasilev (
		 * http://freegameassets.blogspot.com)
		 
		imageFile = new File("game.sample/sprites/explosion01_set_64.png");
		try {
			m_explosionSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}*/

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

	}

}
