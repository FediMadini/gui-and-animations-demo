package com.fedimadini;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Shape {

	private double xPosition;

	private double yPosition;

	private byte xDirection;

	private byte yDirection;

	private double speedInNanoseconds;

	private double angleInRadians;

	private Color color;

	private ShapeType type;

	public Shape() {
		var random = new Random();
		this.xPosition = 0;
		this.yPosition = 0;
		this.xDirection = 1;
		this.yDirection = 1;
		this.speedInNanoseconds = (AnimationSettings.BASE_HEIGHT * random.nextDouble(0.05, 1.0))
				/ TimeUnit.SECONDS.toNanos(1L);
		this.angleInRadians = Math.toRadians(random.nextDouble(1.0, 90.0));
		this.color = new Color(random.nextInt(10, 256), random.nextInt(10, 256), random.nextInt(10, 256));
		this.type = ShapeType.values()[random.nextInt(ShapeType.values().length)];
	}

	private enum ShapeType {
		SMALL_RECTANGLE((int) (AnimationSettings.BASE_HEIGHT * 0.05), (int) (AnimationSettings.BASE_HEIGHT * 0.025),
				false),
		MEDIUM_RECTANGLE((int) (AnimationSettings.BASE_HEIGHT * 0.075), (int) (AnimationSettings.BASE_HEIGHT * 0.05),
				false),
		LARGE_RECTANGLE((int) (AnimationSettings.BASE_HEIGHT * 0.1), (int) (AnimationSettings.BASE_HEIGHT * 0.075),
				false),
		SMALL_SQUARE((int) (AnimationSettings.BASE_HEIGHT * 0.025), (int) (AnimationSettings.BASE_HEIGHT * 0.025),
				false),
		MEDIUM_SQUARE((int) (AnimationSettings.BASE_HEIGHT * 0.05), (int) (AnimationSettings.BASE_HEIGHT * 0.05),
				false),
		LARGE_SQUARE((int) (AnimationSettings.BASE_HEIGHT * 0.075), (int) (AnimationSettings.BASE_HEIGHT * 0.075),
				false),
		SMALL_CIRCLE((int) (AnimationSettings.BASE_HEIGHT * 0.025), (int) (AnimationSettings.BASE_HEIGHT * 0.025),
				true),
		MEDIUM_CIRCLE((int) (AnimationSettings.BASE_HEIGHT * 0.05), (int) (AnimationSettings.BASE_HEIGHT * 0.05), true),
		LARGE_CIRCLE((int) (AnimationSettings.BASE_HEIGHT * 0.075), (int) (AnimationSettings.BASE_HEIGHT * 0.075),
				true);

		private final int width;
		private final int height;
		private final boolean isOval;

		ShapeType(int width, int height, boolean isOval) {
			this.width = width;
			this.height = height;
			this.isOval = isOval;
		}

	}

	public void drawShape(Graphics graphics) {

		double nextXPosition, nextYPosition;

		graphics.setColor(color);

		if (type.isOval) {
			graphics.fillOval((int) Math.round(xPosition), (int) Math.round(yPosition), type.width, type.height);
		} else {
			graphics.fillRect((int) Math.round(xPosition), (int) Math.round(yPosition), type.width, type.height);
		}

		nextXPosition = xPosition + (Math.cos(angleInRadians) * speedInNanoseconds
				* AnimationSettings.DELTA_TIME_IN_NANOSECONDS * xDirection);

		nextYPosition = yPosition + (Math.sin(angleInRadians) * speedInNanoseconds
				* AnimationSettings.DELTA_TIME_IN_NANOSECONDS * yDirection);

		if (Math.round(nextXPosition) <= 0) {
			xDirection = 1;
		} else if (AnimationSettings.BASE_WIDTH <= Math.round(nextXPosition) + type.width) {
			xDirection = -1;
		}

		if (Math.round(nextYPosition) <= 0) {
			yDirection = 1;
		} else if (AnimationSettings.BASE_HEIGHT <= Math.round(nextYPosition) + type.height) {
			yDirection = -1;
		}

		xPosition = nextXPosition;

		yPosition = nextYPosition;
	}
}
