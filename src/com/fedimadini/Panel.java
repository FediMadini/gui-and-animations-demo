package com.fedimadini;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Panel extends JPanel {

	private final List<Shape> shapes;

	public Panel() {
		var panelDimension = new Dimension(AnimationSettings.BASE_WIDTH, AnimationSettings.BASE_HEIGHT);
		shapes = new ArrayList<Shape>();
		setPreferredSize(panelDimension);
		setMinimumSize(panelDimension);
		setMaximumSize(panelDimension);
		setBackground(Color.BLACK);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		shapes.forEach(shape -> shape.drawShape(g));
	}

	public void spawnShape() {
		shapes.add(new Shape());
	}
}
