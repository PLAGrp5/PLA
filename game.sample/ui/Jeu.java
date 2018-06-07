package ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import framework.GameController;
import framework.GameModel;
import framework.GameUI;
import framework.GameView;
import framework.WindowListener;

public class Jeu extends JFrame {

	JFrame m_frame;
	GameView m_view;
	GameController m_controller;
	GameModel m_model;
	
	public Jeu(JFrame jf, GameView v, GameModel m, GameController c) {
		this.m_frame = jf;
		this.m_view = v;
		this.m_controller = c;
		this.m_model = m;
	}
	
	public void LancerJeu() {
		JPanel m_panel = new JPanel();
		m_frame.setContentPane(m_panel);
		//m_frame.repaint();
		m_frame.revalidate();
		m_panel.setLayout(new BorderLayout());
		//m_panel.setBackground(Color.green);

		m_panel.add(m_view, BorderLayout.CENTER);
		m_panel.doLayout();
		m_panel.setVisible(true);
		//m_frame.add(m_panel);
		//m_frame.setVisible(true);
	    m_frame.addWindowListener(new WindowListener(m_model));

	    m_frame.pack();
	    m_frame.setLocationRelativeTo(null);
	    
	    GameController ctr = m_controller;

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
	  //  boolean i = false;
	   // while (i != true) {};
	    GameUI test = new GameUI(m_frame, m_view, m_model, m_controller);
	    test.Test();
	}
    
}
