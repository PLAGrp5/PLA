package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import framework.GameController;
import framework.GameModel;
import framework.GameUI;
import framework.GameView;

public class Accueil extends JFrame implements ActionListener{
	
	JButton bouton = new JButton("Mon Bouton");
	//JFrame frame = new JFrame();
	JFrame m_frame = new JFrame();
	JPanel panel = new JPanel();
	GameView m_view;
	GameController m_controller;
	GameModel m_model;
	
	public Accueil(JFrame m_frame, GameView m_view, GameController c, GameModel m) {
		this.m_frame = m_frame;
		this.m_view = m_view;
		this.m_controller = c;
		this.m_model = m;
	}
	
	
	public void TestBouton() {
		bouton.addActionListener(this); // Quand clic bouton
		panel.add(bouton);
		panel.setBackground(Color.red);
		m_frame.add(panel);
		m_frame.setVisible(true);	
	}
	
	public void actionPerformed (ActionEvent e){
		 Object o = e.getSource();
		 if (o == bouton) {
				System.out.println("clic");
				Jeu jeu = new Jeu(m_frame, m_view, m_model, m_controller);
				//GameUI.LancerJeu(m_frame, m_view, m_model, m_controller);
				jeu.LancerJeu();
		 }
		 dispose();
	}
}
