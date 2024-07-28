package com.fedimadini;

import java.util.concurrent.TimeUnit;

public class AnimationSettings {

	public static final int FPS = 60;

	public static final double DELTA_TIME_IN_NANOSECONDS = Double.valueOf(TimeUnit.SECONDS.toNanos(1)) / FPS;

	public static final int BASE_WIDTH = 1000;

	public static final int BASE_HEIGHT = 1000;

}
