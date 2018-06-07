package framework;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Jeu {
	JFrame m_frame;
	GameView m_view;
	
	public Jeu(JFrame jf, GameView v) {
		this.m_frame = jf;	
		this.m_view = v;
	}
	
	public JFrame LancerJeu() {
		 m_frame.add(m_view, BorderLayout.CENTER);
		 m_frame.doLayout();
		 m_frame.setVisible(true);
		 
		 return m_frame;
	}
}
