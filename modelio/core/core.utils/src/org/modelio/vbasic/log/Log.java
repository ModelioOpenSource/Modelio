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
package org.modelio.vbasic.log;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This class provides a simple logging facility that can be completely disabled
 * at compilation time, leaving absolutely no overhead in the code. Inspired by:
 * "MinLog: A low overhead, lightweight logging system." by Nathan Sweet
 * <misc@n4te.com>
 * 
 * Usage:
 * <pre>
 * if (Log.ENABLED) Log.error("An error message...");
 * </pre>
 * <br><b>
 * IMPORTANT NOTE: <br>To benefit from the absence of overhead when ENABLED is compiled to 'false', any logging statement MUST be guarded by a 'if (Log.ENABLED)' condition.
 * </b><br>
 * 
 * 
 * @author phv
 */
@objid ("00534e22-da4a-1f33-b94f-001ec947cd2a")
public class Log {
    /**
     * Global flag to enable or disable logging.
     * DO NOT REMOVE final otherwise we cannot benefit of a "MinLog: A low overhead, lightweight logging system." by Nathan Sweet"
     */
    @objid ("000bddbc-e3a3-1f33-b94f-001ec947cd2a")
    public static final boolean ENABLED = false;

    @objid ("000d4ed6-e3a3-1f33-b94f-001ec947cd2a")
    private static IBasicLogger logger = new BasicLogger();

    /**
     * The logging methods
     * @param message the message
     */
    @objid ("000bfba8-e3a3-1f33-b94f-001ec947cd2a")
    public static void error(final String message) {
        logger.error(message);
    }

    /**
     * @param format see {@link String#format(String, Object...)}
     * @param args format arguments
     */
    @objid ("000c595e-e3a3-1f33-b94f-001ec947cd2a")
    public static void error(final String format, final Object... args) {
        logger.error(format, args);
    }

    /**
     * Log an exception with its stack trace as error.
     * @param ex the exception
     */
    @objid ("000c842e-e3a3-1f33-b94f-001ec947cd2a")
    public static void error(final Throwable ex) {
        logger.error(ex);
    }

    /**
     * Log a warning message.
     * @param message the message
     */
    @objid ("000c96e4-e3a3-1f33-b94f-001ec947cd2a")
    public static void warning(final String message) {
        logger.warning(message);
    }

    /**
     * @param format see {@link String#format(String, Object...)}
     * @param args format arguments
     */
    @objid ("000cb2fa-e3a3-1f33-b94f-001ec947cd2a")
    public static void warning(final String format, final Object... args) {
        logger.warning(format, args);
    }

    /**
     * Log an exception with its stack trace as warning.
     * @param ex the exception
     */
    @objid ("000cddca-e3a3-1f33-b94f-001ec947cd2a")
    public static void warning(final Throwable ex) {
        logger.warning(ex);
    }

    /**
     * Log a trace message
     * @param message the message
     */
    @objid ("000cf198-e3a3-1f33-b94f-001ec947cd2a")
    public static void trace(final String message) {
        logger.trace(message);
    }

    /**
     * @param format see {@link String#format(String, Object...)}
     * @param args format arguments
     */
    @objid ("000d0ebc-e3a3-1f33-b94f-001ec947cd2a")
    public static void trace(final String format, final Object... args) {
        logger.trace(format, args);
    }

    /**
     * Log an exception with its stack trace as trace.
     * @param ex the exception
     */
    @objid ("000d3a9a-e3a3-1f33-b94f-001ec947cd2a")
    public static void trace(final Throwable ex) {
        logger.trace(ex);
    }

    @objid ("000d6cf4-e3a3-1f33-b94f-001ec947cd2a")
    private  Log() {
        
    }

    /**
     * Set the service used to log messages.
     * @param value the logging service.
     */
    @objid ("63967127-59d8-471f-a406-dd9ea8bb6787")
    public static void setLogger(IBasicLogger value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        logger = value;
        
    }

    @objid ("c093f537-ec73-4608-82ca-fedd1296a756")
    public static IBasicLogger getLogger() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return logger;
    }

}
