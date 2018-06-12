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
package framework;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import Parser.ParseException;
import onscreen.Map;
import onscreen.Sbire;
import onscreen.Tank;
import ui.Model;
import ui.Controller;
import ui.View;

import javax.swing.JPanel;
//import Parser.*;

public class GameUI implements ActionListener {

	static String license = "Copyright (C) 2017  Pr. Olivier Gruber "
			+ "This program comes with ABSOLUTELY NO WARRANTY. "
			+ "This is free software, and you are welcome to redistribute it "
			+ "under certain conditions; type `show c' for details.";

	static GameUI game;

	// public static void main(String[] args) {
	//
	// game = new Game();
	//
	// // notice that the main thread will exit here,
	// // but not your program... hence the hooking
	// // of the window events to System.exit(0) when
	// // the window is closed. See class WindowListener.
	//
	// /*
	// * *** WARNING *** WARNING *** WARNING *** WARNING ***
	// * If you do something here, on this "main" thread,
	// * you will have parallelism and thus race conditions.
	// *
	// * ONLY FOR ADVANCED DEVELOPERS
	// *
	// * *** WARNING *** WARNING *** WARNING *** WARNING ***
	// */
	// }

	JFrame m_frame;
	GameView m_view;
	Timer m_timer;
	GameModel m_model;
	GameController m_controller;
	JLabel m_text;
	int m_fps;
	String m_msg;
	long m_start;
	long m_elapsed;
	long m_lastRepaint;
	long m_lastTick;
	long temps_de_pause;
	int m_nTicks;
	protected Menu menu;
	protected Help help;
	protected Pause pause;
	protected GameOver over;
	protected Credit credit;
	int tpsBase;

	protected Parametres param;
	File map = new File("game.sample/onscreen/map_test.txt");
	File sb1_1;
	File sb1_2;
	File sb2_1;
	File sb2_2;

	ImageIcon icon = new ImageIcon("game.sample/sprites/image.png");

	public enum STATE {
		Menu, Game, Help, Pause, Over, Param, Credit
	};

	public STATE state = STATE.Menu;

	public void setState(STATE g) {
		state = g;
	}

	public GameUI(Dimension d) {
		System.out.println(license);

		// create the main window and the periodic timer
		// to drive the overall clock of the simulation.
		createWindow(d);
	}

	public GameModel getModel() {
		return m_model;
	}

	public GameView getView() {
		return m_view;
	}

	public GameController getController() {
		return m_controller;
	}

	public void addNorth(Component c) {
		m_frame.add(c, BorderLayout.NORTH);
	}

	public void addSouth(Component c) {
		m_frame.add(c, BorderLayout.SOUTH);
	}

	public void addWest(Component c) {
		m_frame.add(c, BorderLayout.WEST);
	}

	public void addEast(Component c) {
		m_frame.add(c, BorderLayout.EAST);
	}

	void createWindow(Dimension d) {
		if (state == STATE.Game) {
			Map m = new Map(map);
			Model model;

			try {
				model = new Model(m);
			} catch (FileNotFoundException | ParseException e) {
				e.printStackTrace();
				return;
			}

			Controller controller = new Controller(model);
			View view = new View(model, controller);

			m_model = model;
			m_model.m_game = this;
			m_view = view;
			m_view.m_game = this;
			m_controller = controller;
			m_controller.m_game = this;

			m_frame = new JFrame();
			m_frame.setTitle("Gitank"); // Nom de la fenêtre
			m_frame.setLayout(new BorderLayout());
			m_frame.setIconImage(icon.getImage()); // Icone du jeu

			m_frame.add(m_view, BorderLayout.CENTER);

			m_text = new JLabel();
			m_text.setText("Starting up...");
			m_frame.add(m_text, BorderLayout.NORTH);

			m_frame.setSize(d);
			m_frame.doLayout();
			m_frame.setVisible(true);

			// hook window events so that we exit the Java Platform
			// when the window is closed by the end user.
			m_frame.addWindowListener(new WindowListener(m_model));

			m_frame.pack();
			m_frame.setLocationRelativeTo(null);

			JMenuBar jmb = new JMenuBar();
			JMenu jmFile = new JMenu("Menu");
			JMenuItem jmiPause = new JMenuItem("Pause");
			JMenuItem jmiExit = new JMenuItem("Exit");
			jmFile.add(jmiPause);
			jmFile.addSeparator();
			jmFile.add(jmiExit);
			jmb.add(jmFile);

			jmiPause.setActionCommand("PAUSE");
			jmiExit.setActionCommand("EXIT");

			jmiPause.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_N));
			jmiExit.setAccelerator(KeyStroke.getKeyStroke((char) 27));

			jmiPause.addActionListener(this);
			jmiExit.addActionListener(this);

			m_frame.setJMenuBar(jmb);
			m_frame.setVisible(true);

			GameController ctr = getController();

			// let's hook the controller,
			// so it gets mouse events and keyboard events.
			m_view.addKeyListener(ctr);
			m_view.addMouseListener(ctr);
			m_view.addMouseMotionListener(ctr);

			// grab the focus on this JPanel, meaning keyboard events
			// are coming to our controller. Indeed, the focus controls
			// which part of the overall GUI receives the keyboard events.
			m_view.setFocusable(true);
			m_view.requestFocusInWindow();

			m_controller.notifyVisible();
		} else if (state == STATE.Menu) {
			menu = new Menu(this);
			menu.showEvent();
		} else if (state == STATE.Credit) {
			credit = new Credit(this);
			credit.showEvent();
		} else if (state == STATE.Help) {
			help = new Help(this);
			help.showEvent();
		} else if (state == STATE.Pause) {
			pause = new Pause(this);
			pause.showEvent();
		} else if (state == STATE.Over) {
			over = new GameOver(this);
			over.showEvent();
		} else if (state == STATE.Param) {
			param = new Parametres(this);
			param.showEvent();
		}
	}

	/*
	 * Let's create a timer, it is the heart of the simulation, ticking periodically
	 * so that we can simulate the passing of time.
	 */
	void createTimer() {
		if (state == STATE.Game) {
			m_nTicks = 0;
			temps_de_pause = 0;
			int tick = 1; // one millisecond
			m_start = System.currentTimeMillis();
			m_lastTick = m_start;
			tpsBase = 60000;
			m_lastRepaint = 0;
			m_timer = new Timer(tick, new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					tick();
				}
			});
			m_timer.start();
		}
	}

	void stopTimer() {
		m_timer.stop();
	}

	void resumeTimer() {
		m_timer.start();
	}

	/*
	 * This is the period tick callback. We compute the elapsed time since the last
	 * tick. We inform the model of the current time.
	 */
	private void tick() {
		long now = System.currentTimeMillis() - m_start;
		long tempsrestant = tpsBase - now + temps_de_pause;
		long elapsed = (now - m_lastTick);
		m_lastTick = now;
		m_elapsed += elapsed;
		m_nTicks++;
		m_model.step(now);
		m_controller.step(now);

		Model mod = (Model) m_model;
		int parcourstank = 0;

		while (parcourstank < mod.ntank) {
			if (mod.tanks[parcourstank].vie == 0) {

				setState(STATE.Over);
				m_frame.dispose();
				Dimension d = new Dimension(1024, 1024);
				createWindow(d);
				stopTimer();

			}
			parcourstank++;
		}

		if (tempsrestant <= 0) {
			setState(STATE.Over);
			m_frame.dispose();
			Dimension d = new Dimension(1024, 1024);
			createWindow(d);
			stopTimer();
		}
		elapsed = now - m_lastRepaint;
		if (elapsed > Options.REPAINT_DELAY) {
			double tick = (double) elapsed / (double) m_nTicks;
			long tmp = (long) (tick * 10.0);
			tick = tmp / 10.0;
			m_elapsed = 0;
			m_nTicks = 0;
			String txt = "Tick=" + tick + "ms";
			while (txt.length() < 15)
				txt += " ";
			txt = txt + m_fps + " fps   ";
			while (txt.length() < 25)
				txt += " ";
			if (m_msg != null) {
				txt += m_msg;

			}
			// System.out.println(txt);
			while (txt.length() < 150) {
				txt += " ";
			}
			txt = txt + "Temps restant :   " + tempsrestant / 1000;
			while (txt.length() < 250) {
				txt += " ";
			}
			if (m_msg != null) {
				txt += m_msg;

			}
			m_text.setText(txt);
			m_text.repaint();
			m_view.paint();
			m_lastRepaint = now;
		}
		// System.out.printf("Temps restant : " + tempsrestant/1000 + "\n");
	}

	public void setFPS(int fps, String msg) {
		m_fps = fps;
		m_msg = msg;
	}

	public void drawPLayer1Panel(Tank t, Sbire s1, Sbire s2, int score, String vie, String mine, String sbire,
			int nbre_mine, int nbre_vie) {
		JPanel pan = new JPanel(new GridLayout(16, 3));

		pan.add(new JLabel(""));
		pan.add(new JLabel("Score :"));
		pan.add(new JLabel("" + score + ""));

		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa.png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa.png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa.png")));

		pan.add(new JLabel(""));
		pan.add(new JLabel("Joueur 1"));
		pan.add(new JLabel(""));

		pan.add(new JLabel(""));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/Vie.png")));
		pan.add(new JLabel("" + t.vie + ""));

		pan.add(new JLabel(""));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/peintureB.png")));
		pan.add(new JLabel("" + t.jauge_couleur + ""));

		pan.add(new JLabel(new ImageIcon("game.sample/sprites/" + vie + ".png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/" + mine + ".png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/" + sbire + ".png")));

		pan.add(new JLabel(""));
		pan.add(new JLabel("" + nbre_vie + ""));
		pan.add(new JLabel("" + nbre_mine + ""));

		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa.png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa.png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa.png")));

		pan.add(new JLabel(""));
		pan.add(new JLabel("Sbire 1"));
		pan.add(new JLabel(""));

		pan.add(new JLabel(""));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/Vie.png")));
		pan.add(new JLabel("" + s1.vie + ""));

		pan.add(new JLabel(""));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/peintureB.png")));
		pan.add(new JLabel("" + s1.jauge_couleur + ""));

		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa.png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa.png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa.png")));

		pan.add(new JLabel(""));
		pan.add(new JLabel("Sbire 2"));
		pan.add(new JLabel(""));

		pan.add(new JLabel(""));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/Vie.png")));
		pan.add(new JLabel("" + s2.vie + ""));

		pan.add(new JLabel(""));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/peintureB.png")));
		pan.add(new JLabel("" + s2.jauge_couleur + ""));

		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa.png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa.png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa.png")));

		pan.setBackground(Color.cyan);
		addWest(pan);
	}

	public void drawPLayer2Panel(Tank t, int score, String vie, String mine, String sbire, int nbre_mine,
			int nbre_vie) {
		JPanel pan = new JPanel(new GridLayout(16, 3));

		pan.add(new JLabel(""));
		pan.add(new JLabel("Score :"));
		pan.add(new JLabel("" + score + ""));

		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa1.png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa1.png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa1.png")));

		pan.add(new JLabel(""));
		pan.add(new JLabel("Joueur 2"));
		pan.add(new JLabel(""));

		pan.add(new JLabel(""));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/Vie.png")));
		pan.add(new JLabel("" + t.vie + ""));

		pan.add(new JLabel(""));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/peintureR.png")));
		pan.add(new JLabel("" + t.jauge_couleur + ""));

		pan.add(new JLabel(new ImageIcon("game.sample/sprites/" + vie + ".png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/" + mine + ".png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/" + sbire + ".png")));

		pan.add(new JLabel(""));
		pan.add(new JLabel("" + nbre_vie + ""));
		pan.add(new JLabel("" + nbre_mine + ""));

		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa1.png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa1.png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa1.png")));

		pan.add(new JLabel(""));
		pan.add(new JLabel("Sbire 1"));
		pan.add(new JLabel(""));

		pan.add(new JLabel(""));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/Vie.png")));
		pan.add(new JLabel("" + t.vie + ""));

		pan.add(new JLabel(""));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/peintureR.png")));
		pan.add(new JLabel("" + t.jauge_couleur + ""));

		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa1.png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa1.png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa1.png")));

		pan.add(new JLabel(""));
		pan.add(new JLabel("Sbire 2"));
		pan.add(new JLabel(""));

		pan.add(new JLabel(""));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/Vie.png")));
		pan.add(new JLabel("" + t.vie + ""));

		pan.add(new JLabel(""));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/peintureR.png")));
		pan.add(new JLabel("" + t.jauge_couleur + ""));

		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa1.png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa1.png")));
		pan.add(new JLabel(new ImageIcon("game.sample/sprites/sepa1.png")));

		pan.setBackground(Color.orange);
		addEast(pan);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();

		if (command.equals("EXIT")) {
			int option = JOptionPane.showConfirmDialog(m_frame.getContentPane(), "Êtes-vous sûr ?", "Quitter ?",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
			if (option == JOptionPane.YES_OPTION) {
				setState(STATE.Over);
				m_frame.dispose();
				Dimension d = new Dimension(1024, 1024);
				m_model.shutdown();
				createWindow(d);
				createTimer();
			}

		} else if (command.equals("PAUSE")) {
			setState(STATE.Pause);
			Dimension d = new Dimension(1024, 1024);
			createWindow(d);
			stopTimer();
			temps_de_pause -= System.currentTimeMillis() - m_start;
		}
	}
}