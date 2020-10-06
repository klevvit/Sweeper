package display;

import defaultPackage.Game;

import java.util.Date;

/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
public class TimeCounter extends Counter implements Runnable {
	private long time;
	private long beginTime;
	private boolean isOn;

	public TimeCounter() {
		setValue(0);
		isOn = false;
	}

	@Override
	public void run() {
		isOn = true;
		setValue(0);
		beginTime = new Date().getTime();

		while (isOn) {
			resetTime();
			 try {
			 	Thread.sleep(10);  // todo select best constant
			 } catch (InterruptedException e) {
			 	e.printStackTrace();
			 }
		}
	}

	public void stop() {
		isOn = false;
	}

	public void resetTime() {
		time = new Date().getTime();
		int valueNow = (int) (time - beginTime) / 1000 + 1;  // todo is this formula correct?

		if (getValue() != valueNow) {
			setValue(valueNow);
			repaint();
		}
	}

	public double getTime() {
		return time;
	}

}
