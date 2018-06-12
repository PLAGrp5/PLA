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

import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import automate.Hit;
import automate.Move;
import framework.*;
import onscreen.Bullet;

/**
 * This class is to illustrate the most simple game controller. It does not
 * much, but it shows how to obtain the key strokes, mouse buttons, and mouse
 * moves.
 * 
 * With ' ', you see what you should never do, SLEEP. With '+' and '-', you can
 * add or remove some simulated overheads.
 * 
 * @author Pr. Olivier Gruber
 */

public class Controller extends GameController implements ActionListener {

	Model m_model;
	Button m_cowboysOn;
	Button m_explosionsOn;
	Button m_strobesOn;
	Button m_plus, m_minus;
	Music m_player;
	// Map m = new Map(30);

	public Controller(Model m) {
		m_model = m;
	}

	/**
	 * Simulation step. Warning: the model has already executed its step.
	 * 
	 * @param now
	 *            is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// if (Options.ECHO_KEYBOARD)
		// System.out.println("KeyTyped: " + e);
		if (e.getKeyChar() == ' ') {
			try {
				/*
				 * NEVER, EVER, DO THIS! NEVER LOOP FOR LONG, NEVER BLOCK, OR NEVER SLEEP, YOU
				 * WILL BLOCK EVERYTHING.
				 */
				System.err.println("You should not have done that!");
				System.out.println("ZZzzz....");
				Thread.sleep(3000);
				System.out.println("Hey! I am back");
			} catch (InterruptedException ex) {
			}
		} else if (e.getKeyChar() == '+') {
			Overhead h = m_model.getOverhead();
			h.inc();
		} else if (e.getKeyChar() == '-') {
			Overhead h = m_model.getOverhead();
			h.dec();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (Options.ECHO_KEYBOARD)
			System.out.println("KeyPressed: " + e.getKeyChar() + " code=" + e.getKeyCode());
		switch (e.getKeyChar()) {
		// move Joueur 1
		case 'T':
		case 't':
			m_model.tanks[0].move('N');
			m_model.last_touche = "z";
			break;
		case 'F':
		case 'f':
			m_model.tanks[0].move('W');
			m_model.last_touche = "f";
			break;
		case 'H':
		case 'h':
			m_model.tanks[0].move('E');
			m_model.last_touche = "h";
			break;
		case 'G':
		case 'g':
			m_model.tanks[0].move('S');
			m_model.last_touche = "g";
			break;
		case 'R':
		case 'r':
			m_model.tanks[0].hit();
			m_model.last_touche = "r";
			break;
		case 'y':
		case 'Y':
			if (!(m_model.tanks[0].inventaireVide())) {
				m_model.tanks[0].inventaire[0].jeter(m_model.tanks[0]);
			}
			else {
				System.out.println("Inventaire Vide");
			}
			m_model.last_touche = "y";
			break;

		// Pop Joueur 1
		case 'W':
		case 'w':
			m_model.tanks[0].pop();
			m_model.last_touche = "w";
			break;

		// Wizz Joueur 1
		case 'x':
		case 'X':
			m_model.tanks[0].wizz();
			m_model.last_touche = "x";
			break;

		// Pop Joueur 2
		case 'L':
		case 'l':
			m_model.tanks[1].pop();
			m_model.last_touche = "l";
			break;

		// Wizz Joueur 2
		case 'm':
		case 'M':
			m_model.tanks[1].wizz();
			m_model.last_touche = "m";
			break;
		case 'p' :
		case 'P' :
			if (!(m_model.tanks[1].inventaireVide())) {
				m_model.tanks[1].inventaire[0].jeter(m_model.tanks[1]);
			}
			else {
				System.out.println("Inventaire Vide");
			}
			m_model.last_touche = "p";

			break;
		case 'o':
		case 'O':
			m_model.tanks[1].hit();
			m_model.last_touche = "o";
			break;
		default:
			break;
		}
		switch (e.getKeyCode()) {
		// Move Joueur 2
		case 37:
			m_model.tanks[1].move('W');
			m_model.last_touche = "FL";
			break;
		case 38:
			m_model.tanks[1].move('N');
			m_model.last_touche = "FU";
			break;
		case 39:
			m_model.tanks[1].move('E');
			m_model.last_touche = "FR";
			break;
		case 40:
			m_model.tanks[1].move('S');
			m_model.last_touche = "FD";
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (Options.ECHO_KEYBOARD)
			System.out.println("KeyReleased: " + e.getKeyChar() + " code=" + e.getKeyCode());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MouseClicked: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MousePressed: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MouseReleased: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseEntered: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseExited: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseDragged: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseMoved: (" + e.getX() + "," + e.getY());
	}

	public void notifyVisible() {
		Container cont = new Container();
		cont.setLayout(new FlowLayout());

		m_strobesOn = new Button("Strobes");
		m_strobesOn.addActionListener(this);
		cont.add(m_strobesOn);

		m_cowboysOn = new Button("Cowboys");
		m_cowboysOn.addActionListener(this);
		cont.add(m_cowboysOn);

		m_plus = new Button("+");
		m_plus.addActionListener(this);
		cont.add(m_plus);

		m_minus = new Button("-");
		m_minus.addActionListener(this);
		cont.add(m_minus);

		m_explosionsOn = new Button("X");
		m_explosionsOn.addActionListener(this);
		cont.add(m_explosionsOn);

		/*
		 * File file; file = new File("game.sample/sprites/Future-RPG.wav"); //file =
		 * new File("game.sample/sprites/Runaway-Food-Truck.wav"); try { //m_player =
		 * new Music(file); cont.add(m_player.getControls()); } catch (Exception ex) { }
		 * m_game.addSouth(cont);
		 */
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == m_strobesOn)
			Options.STROBBING_SQUARES = !Options.STROBBING_SQUARES;
		else if (s == m_cowboysOn)
			Options.SHOW_COWBOYS = !Options.SHOW_COWBOYS;
		else if (s == m_explosionsOn)
			Options.EXPLODE_COWBOYS = !Options.EXPLODE_COWBOYS;
		else if (s == m_plus && Options.SHOW_NCOWBOYS < Options.MAX_NCOWBOYS)
			Options.SHOW_NCOWBOYS++;
		else if (s == m_minus && Options.SHOW_NCOWBOYS > 0)
			Options.SHOW_NCOWBOYS--;
	}

}
