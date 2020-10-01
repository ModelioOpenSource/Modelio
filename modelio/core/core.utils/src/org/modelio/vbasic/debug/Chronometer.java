/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.vbasic.debug;

import java.time.Duration;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;

/**
 * Service to measure duration between several steps.
 * <p>
 * To be used mostly to log performances.
 * @author cma
 */
@objid ("609ee5c8-4bf3-4b8b-ab8a-0d387df3e290")
public class Chronometer {
    @objid ("9be32ca7-9cd4-485c-be02-66af68ffc5cc")
    private final long start;

    @objid ("3ededcab-9c92-4e0a-9df5-439c872bec22")
    private long last;

    /**
     * Initializes a chronometer and starts it.
     */
    @objid ("4fec5d99-addf-496e-995c-d55c253abd7d")
    public Chronometer() {
        this.start = System.nanoTime();
        this.last = this.start;
    }

    /**
     * Get the duration since last call to {@link #next()}.
     * If first call returns the duration since instantiation.
     * 
     * @return the duration since last call in nanoseconds.
     */
    @objid ("43d9cd14-47f4-442c-bf2a-898de57795a4")
    public long next() {
        long now = System.nanoTime();
        long duration = now - this.last;
        this.last = now;
        return duration;
    }

    /**
     * @return the duration since instantiation in nanoseconds.
     */
    @objid ("8f6dfc44-c69f-48f3-81e1-26b56cbad5f4")
    public long total() {
        long now = System.nanoTime();
        return now - this.start;
    }

    /**
     * Log the duration since last call to {@link #next()}.
     * If first call logs the duration since instantiation.
     * 
     * @param label a prefix for the log.
     */
    @objid ("639f6e8b-96f3-49ef-89a0-d053f0fc7010")
    public void logNext(String label) {
        Duration d = Duration.ofNanos(next());
        Log.trace("%s : %s", label, humanReadableFormat(d));
    }

    /**
     * Log the duration since instantiation.
     * 
     * @param label a prefix for the log.
     */
    @objid ("6c8c1be6-e0b0-4583-9961-13a5a0d5d6a7")
    public void logTotal(String label) {
        long now = System.nanoTime();
        Duration d = Duration.ofNanos(now - this.start);
        this.last = now;
        Log.trace("%s : %s", label, humanReadableFormat(d));
    }

    /**
     * Return a human readable string form of the duration.
     * 
     * @param durationNano a duration in nanoseconds
     * @return a user friendly string
     */
    @objid ("edd777db-7114-4741-a60e-6dc2242e1b5f")
    public static String format(long durationNano) {
        return humanReadableFormat( Duration.ofNanos(durationNano));
    }

    @objid ("b2ac7b50-145d-41d0-b91e-37d37c8e51b8")
    private static String humanReadableFormat(Duration duration) {
        return duration.toString()
                        .substring(2)
                        .replaceAll("(\\d[HMS])(?!$)", "$1 ")
                        .toLowerCase();
    }

}
