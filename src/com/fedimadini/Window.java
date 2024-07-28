package com.fedimadini;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Window {

	private JFrame frame;

	private Panel panel;

	public Window() {
		initialize();
		startLoop();
	}

	private void initialize() {

		frame = new JFrame();
		panel = new Panel();

		var button = new JButton();
		button.setText("Spawn shape");
		button.addActionListener(event -> panel.spawnShape());

		frame.setTitle("My first GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.getContentPane().add(BorderLayout.SOUTH, button);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	private void startLoop() {
		long previousFrameTimeStampInMilliSeconds = System.nanoTime();
		while (true) {
			if (System.nanoTime()
					- previousFrameTimeStampInMilliSeconds > AnimationSettings.DELTA_TIME_IN_NANOSECONDS) {
				previousFrameTimeStampInMilliSeconds = System.nanoTime();
				panel.repaint();
			}
		}
	}
}
