package display;

import javax.swing.*;
import java.util.Date;

/**
 * Class that displays timer and can start-stop it.
 * Important: <code>stop()</code> this timer before losing link to this object!
 * Or just use the same timer object instead.
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 * todo timer doesn't stop after pressing smile or starting a new game while current game is on; another timer starts simultaneously.
 */
public class TimeCounter extends Counter {
	private long beginTime;
	private final Timer timer;

	private static final int TICKS_CORRECTION_INTERVAL = 10;

	public TimeCounter() {
		timer = new Timer(1000, event ->
		{
			setValue(getValue() + 1);

			// Standard javax.swing.Timer is well-optimised but slower than real-time one and accumulates noticeable
			// error. For me it's about 1-second difference every 500 seconds.
			// So every TICKS_CORRECTION_INTERVAL ticks we compare time with the computer clock and set the next
			// second manually to compensate the error.
			if (getValue() % TICKS_CORRECTION_INTERVAL == 0) {

				long timeFromStart = new Date().getTime() - beginTime;
				long tillNextSecond = 1000 * getValue() - timeFromStart;

				// if lag > 1 second, tillNextSecond is negative
				long secondsShift = tillNextSecond > 0 ? 0 : -tillNextSecond / 1000 + 1;
				long sleepTime = secondsShift > 0 ? tillNextSecond :  // equals tillNextSecond if it's > 0
						(tillNextSecond + 1000 * secondsShift);  // same as adding thousands until result is > 0

				if (secondsShift > 0) {
					setValue(getValue() + (int) secondsShift);
				}

				stop();

				Runnable sleepAndWake = () -> {
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					setValue(getValue() + 1);
					resume();
				};

				Thread thread = new Thread(sleepAndWake);
				thread.start();
			}
		});

		timer.setRepeats(true);

		setValue(0);
	}

	/**
	 * Launches timer. Shows "1" immediately and increments value every second.
	 * Stops timer at first if it was launched before.
	 */
	public void start() {
		setValue(1);
		beginTime = new Date().getTime();
		timer.start();
	}

	/** Launches timer from its current value. */
	public void resume() {
		timer.start();
	}

	/** Stops timer if it was launched. Current value freezes. */
	public void stop() {
		timer.stop();
	}

	/** Stops timer if it was launched and displays zero. */
	public void reset() {
		timer.stop();
		setValue(0);
	}

}
