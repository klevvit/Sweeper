package header.display;

import javax.swing.*;
import java.util.Date;

/**
 * Class that displays stopwatch that can show time from its start or frozen at some moment value. <br>
 * Important: <code>freeze()</code> or <code>reset()</code> this stopwatch before losing link to this object!
 * Or just use the same Stopwatch object instead.
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
// todo stopwatch doesn't stop after pressing smile or starting a new game while current game is on; another timer starts simultaneously.
public class Stopwatch extends Counter {
	private long beginTime;
	private final Timer timer;

	private boolean justResumed = false;

	private static final int TICKS_CORRECTION_INTERVAL = 10;

	public Stopwatch() {
		timer = new Timer(1000, event ->
		{
			setValue(getValue() + 1);

			// Standard javax.swing.Timer is well-optimised but slower than real-time one and accumulates noticeable
			// error. For me it's about 1-second difference every 500 seconds.
			// So every TICKS_CORRECTION_INTERVAL ticks we compare time with the computer clock and set the next
			// second manually to compensate the error.
			if (getValue() % TICKS_CORRECTION_INTERVAL == 0 || justResumed) {

				justResumed = false;

				long timeFromStart = new Date().getTime() - beginTime;
				long tillNextSecond = 1000L * getValue() - timeFromStart;

				// if lag > 1 second, tillNextSecond is negative
				long secondsShift = tillNextSecond > 0 ? 0 : -tillNextSecond / 1000 + 1;
				long sleepTime = tillNextSecond > 0 ? tillNextSecond :  // equals tillNextSecond if it's > 0
						(tillNextSecond + 1000 * secondsShift);  // same as adding thousands until result is > 0

				if (secondsShift > 0) {
					setValue(getValue() + (int) secondsShift);
				}

				freeze();

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
	 * Launches stopwatch. Shows "1" immediately and increments value every second.
	 */
	public void start() {
		setValue(1);
		beginTime = new Date().getTime();
		timer.start();
	}

	/** Resumes display updates. Stopwatch shows time since <code>start()</code> call. */
	public void resume() {
		justResumed = true;
		timer.start();
	}

	/** Stops display updates if stopwatch was launched. */
	public void freeze() {
		timer.stop();
	}

	/** Stops display updates if stopwatch was launched. Shows zero. <br>
	 *  Note: <code>resume()</code> call will show time since last <code>start()</code> call. To start timer from zero,
	 *  use todo
	 */
	public void reset() {
		timer.stop();
		setValue(0);
	}

}
