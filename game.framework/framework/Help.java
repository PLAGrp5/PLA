package framework;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import framework.GameUI.STATE;

public class Help {

	JFrame helpFrame;
	JLabel headerLabel;
	JLabel regleLabel;
	JPanel controlPanel;
	GameUI g_ui;
	final JFileChooser fc = new JFileChooser();

	public Help(GameUI g) {
		g_ui = g;
		prepareGUI();
	}

	private void prepareGUI() {
		helpFrame = new JFrame("Gitank Help");
		helpFrame.setSize(400, 400);
		helpFrame.setLayout(new GridLayout(3, 1));

		headerLabel = new JLabel("", JLabel.CENTER);
		regleLabel = new JLabel();

		helpFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		helpFrame.add(headerLabel);
		helpFrame.add(regleLabel);
		helpFrame.add(controlPanel);
		helpFrame.setVisible(true);
	}

	public void showEvent() {
		headerLabel.setText("Gitank Règles");
		regleLabel.setText("Voici les règles de Gitank");
		JButton ExitButton = new JButton("EXIT");
		ExitButton.setActionCommand("EXIT");
		ExitButton.addActionListener(new ButtonClickListener());
		controlPanel.add(ExitButton);
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
