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

public class Help {

	JFrame helpFrame;
	JPanel controlPanel;
	GameUI g_ui;

	public Help(GameUI g) {
		g_ui = g;
		prepareGUI();
	}

	private void prepareGUI() {
		helpFrame = new JFrame("Gitank Help");
		helpFrame.setSize(1300, 700);
		helpFrame.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		helpFrame.setLocation(dim.width / 2 - helpFrame.getSize().width / 2,
				dim.height / 2 - helpFrame.getSize().height / 2);
		helpFrame.setIconImage(new ImageIcon("game.sample/sprites/image.png").getImage());
		
		helpFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		ImageIcon imageicon = new ImageIcon("game.sample/sprites/Help.jpg");
		// L'image de fond est issue du site https://pixabay.com qui est une banque
		// d'images libre de droits
		Image image = imageicon.getImage();

		BorderLayout grid = new BorderLayout();
		controlPanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		controlPanel.setLayout(grid);

		helpFrame.setContentPane(controlPanel);
		helpFrame.setVisible(true);
	}

	public void showEvent() {
		MyButton ExitButton = new MyButton("EXIT", "game.sample/sprites/bleu.jpg", "game.sample/sprites/rouge.png");

		// JButton ExitButton = new JButton("EXIT");
		ExitButton.setActionCommand("EXIT");
		ExitButton.addActionListener(new ButtonClickListener());
		ExitButton.setPreferredSize(new Dimension(350, 100));

		JPanel bouton1 = new JPanel();
		bouton1.setOpaque(false);
		bouton1.add(ExitButton);

		controlPanel.add(bouton1, BorderLayout.SOUTH);
		helpFrame.setVisible(true);
	}

	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			if (command.equals("EXIT")) {
				g_ui.setState(STATE.Menu);
				Dimension d = new Dimension(1024, 1024);
				g_ui.createWindow(d);
				g_ui.createTimer();
				helpFrame.dispose();
			}
		}

	}

}
