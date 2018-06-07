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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

<<<<<<< HEAD
//import framework.GameUI.STATE;
//import framework.Menu.ButtonClickListener;

public class GameUI {
=======
import ui.Accueil;
//import ui.Fenetre;

public class GameUI {

  static String license = "Copyright (C) 2017  Pr. Olivier Gruber " + "This program comes with ABSOLUTELY NO WARRANTY. "
      + "This is free software, and you are welcome to redistribute it "
      + "under certain conditions; type `show c' for details.";

  static GameUI game;

//  public static void main(String[] args) {
//
//    game = new Game();
//
//    // notice that the main thread will exit here,
//    // but not your program... hence the hooking
//    // of the window events to System.exit(0) when
//    // the window is closed. See class WindowListener.
//
//    /*
//     * *** WARNING *** WARNING *** WARNING *** WARNING ***
//     * If you do something here, on this "main" thread,
//     * you will have parallelism and thus race conditions.
//     * 
//     *           ONLY FOR ADVANCED DEVELOPERS
//     *           
//     * *** WARNING *** WARNING *** WARNING *** WARNING ***
//     */
//  }


  JFrame m_frame;
  GameView m_view;
  Timer m_timer;
  GameModel m_model;
  GameController m_controller;
  JLabel m_text;
  JButton m_button;
  JPanel m_panel;
  int m_fps;
  String m_msg;
  long m_start;
  long m_elapsed;
  long m_lastRepaint;
  long m_lastTick;
  int m_nTicks;

  public GameUI(GameModel m, GameView v, GameController c, Dimension d) {
    m_model = m; m_model.m_game = this;
    m_view = v; m_view.m_game = this;
    m_controller = c; m_controller.m_game = this;

    System.out.println(license);

    // create the main window and the periodic timer
    // to drive the overall clock of the simulation.
    createWindow(d);
    createTimer();
  }
  
  public GameUI(JFrame jf, GameView v, GameModel m, GameController c) {
	  this.m_frame = jf;
	  this.m_view = v;
	  this.m_model = m;
	  this.m_controller = c;
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
    m_frame.add(c,BorderLayout.NORTH);
  }
  public void addSouth(Component c) {
    m_frame.add(c,BorderLayout.SOUTH);
  }
  public void addWest(Component c) {
    m_frame.add(c,BorderLayout.WEST);
  }
  public void addEast(Component c) {
    m_frame.add(c,BorderLayout.EAST);
  }

  private void createWindow(Dimension d) {
	  
    m_frame = new JFrame();
    //m_button = new JButton("TestButton");
    m_panel = new JPanel();
    m_frame.setTitle("Gitank"); // Nom de la fenêtre
    m_frame.setLayout(new BorderLayout());
    m_frame.setIconImage(new ImageIcon("game.sample/sprites/image.png").getImage()); // Icone du jeu


  //  m_panel.setLayout(new BorderLayout());
  //  m_panel.add(m_view, BorderLayout.CENTER);
    
  //  m_frame.add(m_view, BorderLayout.CENTER);


    m_text = new JLabel();
    m_text.setText("Starting up...");
    m_frame.add(m_text, BorderLayout.NORTH);
    
    m_frame.setSize(d);
  //  m_frame.add(m_button);

    //Fenetre fen = new Fenetre(m_frame, m_panel);
   // fen.TestButton();
    Accueil acc = new Accueil(m_frame, m_view, m_controller, m_model);
    acc.TestBouton();

   // m_panel.doLayout();
 //   m_frame.doLayout();
 //   m_frame.add(m_panel);
  //  m_frame.setVisible(true); 
   // m_button = new JButton("test");
  //  m_frame.add(m_button);
   // m_button.addActionListener(l);
    
    
   // Fenetre fen = new Fenetre();
    // hook window events so that we exit the Java Platform
    // when the window is closed by the end user.
// A recoller ici
   // System.out.println("Testfin");
    
  }

  public void Test() {
	  System.out.println("Superbe Méthode");
  }
  
  // Création du panel Jeu
  public void LancerJeu(JFrame jf, GameView v, GameModel m, GameController c) {
		JPanel m_panel = new JPanel();
		jf.setContentPane(m_panel);
		jf.repaint();
		jf.revalidate();
		m_panel.setLayout(new BorderLayout());
		m_panel.add(v, BorderLayout.CENTER);
		m_panel.doLayout();
		//m_panel.setVisible(true);
		//m_frame.add(m_panel);
		//m_frame.setVisible(true);
	    jf.addWindowListener(new WindowListener(m));

	    jf.pack();
	    jf.setLocationRelativeTo(null);
	    
	    GameController ctr = c;

	    // let's hook the controller, 
	    // so it gets mouse events and keyboard events.
	    v.addKeyListener(ctr);
	    v.addMouseListener(ctr);
	    v.addMouseMotionListener(ctr);

	    // grab the focus on this JPanel, meaning keyboard events
	    // are coming to our controller. Indeed, the focus controls
	    // which part of the overall GUI receives the keyboard events.
	    v.setFocusable(true);
	    v.requestFocusInWindow();

	    c.notifyVisible();
	    
	    this.m_view = v;
	}
  /* 
   * Let's create a timer, it is the heart of the simulation,
   * ticking periodically so that we can simulate the passing of time.
   */
  private void createTimer() {
    int tick = 1; // one millisecond
    m_start = System.currentTimeMillis();
    m_lastTick = m_start;
    m_timer = new Timer(tick, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        tick();
      }
    });
    m_timer.start();
  }

  /*
   * This is the period tick callback.
   * We compute the elapsed time since the last tick.
   * We inform the model of the current time.
   */
  private void tick() {
    long now = System.currentTimeMillis() - m_start;
    long elapsed = (now - m_lastTick);
    m_elapsed += elapsed;
    m_lastTick = now;
    m_nTicks++;
    m_model.step(now);
    m_controller.step(now);
    
    elapsed = now - m_lastRepaint;
    if (elapsed > Options.REPAINT_DELAY) {
      double tick = (double) m_elapsed / (double) m_nTicks;
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
      if (m_msg != null)
        txt += m_msg;
      //      System.out.println(txt);
      m_text.setText(txt);
      m_text.repaint();
      m_view.paint();
      m_lastRepaint = now;
    }
  }

  public void setFPS(int fps, String msg) {
    m_fps = fps;
    m_msg = msg;
  }
>>>>>>> 72eff46ecd6a4996acf6b7b7b20896f57acf6103

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
	int m_nTicks;
	protected Menu menu;
	protected Help help;
	
	public enum STATE {
		Menu, Game, Help, Pause
	};

	public STATE state = STATE.Menu;
	
	public void setState(STATE g) {
		state = g;
	}

	public GameUI(GameModel m, GameView v, GameController c, Dimension d) {
		m_model = m;
		m_model.m_game = this;
		m_view = v;
		m_view.m_game = this;
		m_controller = c;
		m_controller.m_game = this;

		System.out.println(license);

		// create the main window and the periodic timer
		// to drive the overall clock of the simulation.
		createWindow(d);
		createTimer();
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
			m_frame = new JFrame();
			m_frame.setTitle("Game of Tank"); // Nom de la fenêtre
			m_frame.setLayout(new BorderLayout());
			m_frame.setIconImage(new ImageIcon("game.sample/sprites/image.png").getImage()); // Icone du jeu
	
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
		} else if (state == STATE.Help) {
			help = new Help(this);
			help.showEvent();
		}
	}

	/*
	 * Let's create a timer, it is the heart of the simulation, ticking periodically
	 * so that we can simulate the passing of time.
	 */
	void createTimer() {
		if (state == STATE.Game) {	
			int tick = 1; // one millisecond
			m_start = System.currentTimeMillis();
			m_lastTick = m_start;
			m_timer = new Timer(tick, new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					tick();
				}
			});
			m_timer.start();
		}
	}

	/*
	 * This is the period tick callback. We compute the elapsed time since the last
	 * tick. We inform the model of the current time.
	 */
	private void tick() {
		long now = System.currentTimeMillis() - m_start;
		long elapsed = (now - m_lastTick);
		m_elapsed += elapsed;
		m_lastTick = now;
		m_nTicks++;
		m_model.step(now);
		m_controller.step(now);

		elapsed = now - m_lastRepaint;
		if (elapsed > Options.REPAINT_DELAY) {
			double tick = (double) m_elapsed / (double) m_nTicks;
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
			if (m_msg != null)
				txt += m_msg;
			// System.out.println(txt);
			m_text.setText(txt);
			m_text.repaint();
			m_view.paint();
			m_lastRepaint = now;
		}
	}

	public void setFPS(int fps, String msg) {
		m_fps = fps;
		m_msg = msg;
	}
}
