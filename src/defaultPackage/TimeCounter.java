/**
 * @author Kovalenko Lev
*/
/**
 * Copyright Kovalenko Lev (Sweeper) 2016. All rights reserved.
 */
package defaultPackage;

import java.util.Date;

public class TimeCounter extends Counter implements Runnable {
	private long time;
	private long beginTime;
	private boolean isOn;

	public TimeCounter() {
		create();
		setValue(0);
		isOn = false;
	}

	@Override
	public void run() {
		start();
	}

	protected void start() {
		isOn = true;
		setValue(0);
		beginTime = new Date().getTime();

		while (isOn) {
			resetTime();
			/*
			 * try { Thread.sleep(2000); } catch (InterruptedException e) {
			 * e.printStackTrace(); }
			 */
		}
	}

	public void stop() {
		isOn = false;
	}

	public void resetTime() {
		time = new Date().getTime();
		if (displayValue != (int) (time - beginTime) / 1000 + 1) {
			setValue((int) (time - beginTime) / 1000 + 1);
			Game.repaint();
		}
	}

	/*
	 * public void setTime(double t) { time = t; displayValue = (int) time;
	 * SweeperGame.repaint(); }
	 */
	public double getTime() {
		return time;
	}

}
