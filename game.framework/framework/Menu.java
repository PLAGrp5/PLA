package framework;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import framework.GameUI.*;

public class Menu {

	JFrame menuFrame;
	JLabel headerLabel;
	JPanel controlPanel;
	GameUI g_ui;
	final JFileChooser fc = new JFileChooser();

	public Menu(GameUI g) {
		g_ui = g;
		prepareGUI();
	}

	private void prepareGUI() {
		menuFrame = new JFrame("Gitank Menu");
		menuFrame.setSize(1024, 1024);
		menuFrame.setIconImage(new ImageIcon("game.sample/sprites/image.png").getImage());
		menuFrame.setLayout(new GridLayout(5, 5));

		headerLabel = new JLabel("", JLabel.CENTER);

		menuFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		menuFrame.add(headerLabel);
		menuFrame.add(controlPanel);
		menuFrame.setVisible(true);
	}

	public void showEvent() {
		headerLabel.setText("Gitank");

		JButton StartButton = new JButton("START");
		JButton HelpButton = new JButton("HELP");
		JButton ExitButton = new JButton("EXIT");
		JButton OpenButton = new JButton("OPEN");

		StartButton.setActionCommand("START");
		HelpButton.setActionCommand("HELP");
		ExitButton.setActionCommand("EXIT");
		OpenButton.setActionCommand("OPEN");

		StartButton.addActionListener(new ButtonClickListener());
		HelpButton.addActionListener(new ButtonClickListener());
		ExitButton.addActionListener(new ButtonClickListener());
		OpenButton.addActionListener(new ButtonClickListener());

		controlPanel.add(StartButton);
		controlPanel.add(HelpButton);
		controlPanel.add(ExitButton);
		controlPanel.add(OpenButton);

		menuFrame.setVisible(true);
	}

	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			if (command.equals("START")) {
				g_ui.setState(STATE.Game);
				Dimension d = new Dimension(1447, 1024);
				g_ui.createWindow(d);
				g_ui.createTimer();
				menuFrame.dispose();
			} else if (command.equals("HELP")) {
				g_ui.setState(STATE.Help);
				Dimension d = new Dimension(1447, 1024);
				g_ui.createWindow(d);
				g_ui.createTimer();
				menuFrame.dispose();
			} else if (command.equals("EXIT")) {
				System.exit(0);
			} else if (command.equals("OPEN")) {
				int returnVal = fc.showOpenDialog(menuFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					java.io.File file = fc.getSelectedFile();
					
				} else {

				}
			}
		}

	}

}