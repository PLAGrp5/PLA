package framework;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


import framework.GameUI.STATE;

public class Credit {

	JFrame creditFrame;
	JPanel controlPanel;
	GameUI g_ui;

	public Credit(GameUI g) {
		g_ui = g;
		prepareGUI();
	}

	private void prepareGUI() {
		creditFrame = new JFrame("Gitank Help");
		creditFrame.setSize(700, 700);
		creditFrame.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		creditFrame.setLocation(dim.width / 2 - creditFrame.getSize().width / 2,
				dim.height / 2 - creditFrame.getSize().height / 2);

		creditFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		ImageIcon imageicon = new ImageIcon("game.sample/sprites/credit.jpg");
		// L'image de fond est issue du site https://pixabay.com qui est une banque
		// d'images libre de droits
		Image image = imageicon.getImage();

		BorderLayout grid = new BorderLayout();
		controlPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		controlPanel.setLayout(grid);

		creditFrame.setContentPane(controlPanel);
		creditFrame.setVisible(true);
		
	}
	
	public void showEvent() {
		MyButton ExitButton = new MyButton("EXIT", "game.sample/sprites/bleu.jpg", "game.sample/sprites/rouge.png");

		ExitButton.setActionCommand("EXIT");
		ExitButton.addActionListener(new ButtonClickListener());
		ExitButton.setPreferredSize(new Dimension(250, 100));

		JPanel exit = new JPanel();
		exit.setOpaque(false);
		exit.add(ExitButton);

		controlPanel.add(exit, BorderLayout.SOUTH);
		creditFrame.setVisible(true);
	}
	
	
	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			if (command.equals("EXIT")) {
				g_ui.setState(STATE.Menu);
				Dimension d = new Dimension(1024, 1024);
				g_ui.createWindow(d);
				g_ui.createTimer();
				creditFrame.dispose();
			}
		}

	}
}
