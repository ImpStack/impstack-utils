package org.impstack.time;

/**
 * A simple timer class. This timer works with an accuracy of milliseconds.
 *
 * @author remy
 * @since 11/10/17
 */
public final class Timer {

    private long start;

    public Timer() {
        this.start = System.currentTimeMillis();
    }

    public long read() {
        return System.currentTimeMillis() - start;
    }

    public long reset() {
        long time = read();
        this.start = System.currentTimeMillis();
        return time;
    }
}
