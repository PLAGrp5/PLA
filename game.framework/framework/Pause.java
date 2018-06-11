package framework;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import framework.GameUI.STATE;

public class Pause {

	JFrame pauseFrame;
	JLabel headerLabel;
	JPanel controlPanel;
	GameUI g_ui;

	public Pause(GameUI g) {
		g_ui = g;
		prepareGUI();
	}

	private void prepareGUI() {
		pauseFrame = new JFrame("Gitank pause");
		pauseFrame.setSize(256, 128);
		pauseFrame.setLayout(new GridLayout(3, 1));

		headerLabel = new JLabel("", JLabel.CENTER);

		pauseFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		pauseFrame.add(headerLabel);
		pauseFrame.add(controlPanel);
		pauseFrame.setVisible(true);
	}

	public void showEvent() {
		headerLabel.setText("Pause");
		JButton ExitButton = new JButton("EXIT");
		ExitButton.setActionCommand("EXIT");
		JButton ResumeButton = new JButton("RESUME");
		ResumeButton.setActionCommand("RESUME");

		ExitButton.addActionListener(new ButtonClickListener());
		ResumeButton.addActionListener(new ButtonClickListener());

		controlPanel.add(ExitButton);
		controlPanel.add(ResumeButton);

		pauseFrame.setVisible(true);
	}

	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			if (command.equals("EXIT")) {
				g_ui.setState(STATE.Over);
				Dimension d = new Dimension(1024, 1024);
				pauseFrame.dispose();
				g_ui.m_frame.dispose();
				g_ui.createWindow(d);
			} else if (command.equals("RESUME")) {
				g_ui.setState(STATE.Game);
				pauseFrame.dispose();
				g_ui.resumeTimer();
				g_ui.temps_de_pause += System.currentTimeMillis() - g_ui.m_start;
			}
		}
	}
}