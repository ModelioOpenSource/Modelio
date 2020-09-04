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

package org.modelio.utils.log.writers;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.Logger;
import org.modelio.utils.plugin.Utils;
import org.osgi.service.log.LogLevel;

/**
 * Logging utility class for plugins.
 * 
 * The PluginLogger forwards log information to both the OSGI log service and the Modelio log file.
 * <p>
 * Application rules:
 * <ul>
 * <li>use error() to log information about an error situation.</li>
 * <li>use warning() to log information about a failure or unwanted situation that is not blocking.</li>
 * <li>use info() to log information about normal operation.</li>
 * <li>use debug() to log detailed information for debugging operations.</li>
 * </ul>
 * </p>
 */
@objid ("005988a0-404e-1fe3-9845-001ec947cd2a")
public class PluginLogger {
    @objid ("00851a42-4307-1fe3-9845-001ec947cd2a")
    private final org.eclipse.equinox.log.Logger osgiLogger;

    /**
     * The Modelio log level as defined by Admin tool in the preferences.
     * <p>
     * One of: <ul>
     * <li> {@link LogLevel#ERROR},
     * <li>{@link LogLevel#WARN},
     * <li>{@link LogLevel#INFO},
     * <li>{@link LogLevel#DEBUG}
     * </ul>
     * LogLevel.AUDIT and LogLevel.TRACE are not supported.
     */
    @objid ("ccaa6369-0bea-44fd-937f-74fa90d5313b")
    private static LogLevel logLevel = LogLevel.ERROR;

    /**
     * The slf4j logger (messages dumped in modelio log file).
     */
    @objid ("f1da2ee3-8676-4bea-a27a-bcee713ef92c")
    private final org.slf4j.Logger slf4jLogger;

    /**
     * C'tor a combined OSGI/Modelio logger for a plugin.
     * 
     * @param osgiLogger the OSGI logger provider by the caller plugin
     */
    @objid ("008a1d9e-4307-1fe3-9845-001ec947cd2a")
    public PluginLogger(final Logger osgiLogger) {
        // The osgi logger is provided by the caller
        this.osgiLogger = osgiLogger;
        
        // The slf4J logger is obtained from Slf4j logger factory.
        // Reuse the osgi logger name to name the slf4j logger
        this.slf4jLogger = org.slf4j.LoggerFactory.getLogger(osgiLogger.getName());
    }

    /**
     * Log an error.
     * 
     * @param msg the error message to log
     */
    @objid ("008ae936-4307-1fe3-9845-001ec947cd2a")
    public void error(final String msg) {
        if (PluginLogger.logLevel.implies(LogLevel.ERROR)) {
            this.osgiLogger.log(LogLevel.ERROR.ordinal(), msg);
            this.slf4jLogger.error(msg);
        }
    }

    /**
     * Log a formatted error. The logged message is build as <code>String.format(format, args);</code>
     * 
     * @param format the message formating string
     * @param args the message arguments to formatter
     */
    @objid ("008b0664-4307-1fe3-9845-001ec947cd2a")
    public void error(final String format, final Object... args) {
        if (PluginLogger.logLevel.implies(LogLevel.ERROR)) {
            String msg = String.format(format, args);
            this.osgiLogger.log(LogLevel.ERROR.ordinal(), msg);
            this.slf4jLogger.error(msg);
        }
    }

    /**
     * Log an error for an exception. The log message is e.getMaeesage() plus e.toString()
     * 
     * @param e the exception to log
     */
    @objid ("008b3d32-4307-1fe3-9845-001ec947cd2a")
    public void error(final Throwable e) {
        if (PluginLogger.logLevel.implies(LogLevel.ERROR)) {
            this.osgiLogger.log(LogLevel.ERROR.ordinal(), e.getMessage(), e);
            this.slf4jLogger.error(e.getMessage(), e);
        }
    }

    /**
     * Log a warning.
     * 
     * @param msg the warning message to log
     */
    @objid ("008b5c5e-4307-1fe3-9845-001ec947cd2a")
    public void warning(final String msg) {
        if (PluginLogger.logLevel.implies(LogLevel.WARN)) {
            this.osgiLogger.log(LogLevel.WARN.ordinal(), msg);
            this.slf4jLogger.warn(msg);
        }
    }

    /**
     * Log a formatted warning. The logged message is build as <code>String.format(format, args);</code>
     * 
     * @param format the message formating string
     * @param args the message arguments to formatter
     */
    @objid ("008b79c8-4307-1fe3-9845-001ec947cd2a")
    public void warning(final String format, final Object... args) {
        if (PluginLogger.logLevel.implies(LogLevel.WARN)) {
            String msg = String.format(format, args);
            this.osgiLogger.log(LogLevel.WARN.ordinal(), msg);
            this.slf4jLogger.warn(msg);
        }
    }

    /**
     * Log an warning for an exception. The log message is e.getMaeesage() plus e.toString()
     * 
     * @param e the exception to log
     */
    @objid ("008bb0f0-4307-1fe3-9845-001ec947cd2a")
    public void warning(final Throwable e) {
        if (PluginLogger.logLevel.implies(LogLevel.WARN)) {
            this.osgiLogger.log(LogLevel.WARN.ordinal(), e.getMessage(), e);
            this.slf4jLogger.warn(e.getMessage(), e);
        }
    }

    /**
     * Log an informational message.
     * 
     * @param msg the information message to log
     */
    @objid ("008bcf04-4307-1fe3-9845-001ec947cd2a")
    public void info(final String msg) {
        if (PluginLogger.logLevel.implies(LogLevel.INFO)) {
            this.osgiLogger.log(LogLevel.INFO.ordinal(), msg);
            this.slf4jLogger.info(msg);
        }
    }

    /**
     * Log a formatted information message. The logged message is build as <code>String.format(format, args);</code>
     * 
     * @param format the message formating string
     * @param args the message arguments to formatter
     */
    @objid ("008bed2c-4307-1fe3-9845-001ec947cd2a")
    public void info(final String format, final Object... args) {
        if (PluginLogger.logLevel.implies(LogLevel.INFO)) {
            String msg = String.format(format, args);
            this.osgiLogger.log(LogLevel.INFO.ordinal(), msg);
            this.slf4jLogger.info(msg);
        }
    }

    /**
     * Log an information for an exception. The log message is e.getMaeesage() plus e.toString()
     * 
     * @param e the exception to log
     */
    @objid ("008c2544-4307-1fe3-9845-001ec947cd2a")
    public void info(final Throwable e) {
        if (PluginLogger.logLevel.implies(LogLevel.INFO)) {
            this.osgiLogger.log(LogLevel.INFO.ordinal(), e.getMessage(), e);
            this.slf4jLogger.info(e.getMessage(), e);
        }
    }

    /**
     * Log a debug information.
     * 
     * @param msg the debug message to log
     */
    @objid ("008c43e4-4307-1fe3-9845-001ec947cd2a")
    public void debug(final String msg) {
        if (isDebugEnabled()) {
            this.osgiLogger.log(LogLevel.DEBUG.ordinal(), msg);
            this.slf4jLogger.debug(msg);
        }
    }

    /**
     * Log a formatted debug message. The logged message is build as <code>String.format(format, args);</code>
     * 
     * @param format the message formating string
     * @param args the message arguments to formatter
     */
    @objid ("008c6360-4307-1fe3-9845-001ec947cd2a")
    public void debug(final String format, final Object... args) {
        if (isDebugEnabled()) {
            String msg = String.format(format, args);
            this.osgiLogger.log(LogLevel.DEBUG.ordinal(), msg);
            this.slf4jLogger.debug(msg);
        }
    }

    /**
     * Log a debug message for an exception. The log message is e.getMaeesage() plus e.toString()
     * 
     * @param e the exception to log
     */
    @objid ("008c9ee8-4307-1fe3-9845-001ec947cd2a")
    public void debug(final Throwable e) {
        if (isDebugEnabled()) {
            this.osgiLogger.log(LogLevel.DEBUG.ordinal(), e.getMessage(), e);
            this.slf4jLogger.debug(e.getMessage(), e);
        }
    }

    /**
     * @return true is {@link LogLevel#DEBUG} is implied by the current log level.
     */
    @objid ("004d691c-ca76-1fea-8789-001ec947cd2a")
    public boolean isDebugEnabled() {
        return (PluginLogger.logLevel.implies(LogLevel.DEBUG)) &&
                (this.osgiLogger.isLoggable(LogLevel.DEBUG.ordinal()) || this.slf4jLogger.isDebugEnabled());
    }

    /**
     * Find the current Modelio log file from the logback current configuration.
     * 
     * @return the current Modelio log file, null if unable to find it.
     */
    @objid ("1ff3ef68-3b26-4aeb-836f-3079fe4e7b2a")
    public static String getLogFile() {
        return Utils.getLogFile();
    }

    @objid ("8a55a078-afb4-40bc-aca6-70a93ed11026")
    public static LogLevel getLogLevel() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return logLevel;
    }

    /**
     * Set the Modelio log level as defined by Admin tool in the preferences.
     * <p>
     * One of: <ul>
     * <li> {@link LogLevel#ERROR},
     * <li>{@link LogLevel#WARN},
     * <li>{@link LogLevel#INFO},
     * <li>{@link LogLevel#DEBUG}
     * </ul>
     * LogLevel.AUDIT and LogLevel.TRACE are not supported.
     * 
     * @param value the new log level
     * @return the previous log level.
     */
    @objid ("7cce6167-5320-4407-9681-7c1ab78d4190")
    public static LogLevel setLogLevel(LogLevel value) {
        LogLevel previous = logLevel;
        
        if (Objects.equals(value,  logLevel))
            return logLevel;
        
        // Log logging level change
        org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PluginLogger.class);
        logger.info("Changing log level from {} to {}", previous, value);
        if (previous.implies(LogLevel.DEBUG) && ! value.implies(LogLevel.DEBUG)) {
            logger.debug("Leaving DEBUG log level", new Throwable("Log level change stack trace"));
        }
        
        // Change the logging level
        logLevel = value;
        return previous;
    }

    /**
     * Set the Modelio log level to log at least the given level.
     * <p>
     * One of: <ul>
     * <li> {@link LogLevel#ERROR},
     * <li>{@link LogLevel#WARN},
     * <li>{@link LogLevel#INFO},
     * <li>{@link LogLevel#DEBUG}
     * </ul>
     * LogLevel.AUDIT and LogLevel.TRACE are not supported.
     * 
     * @param value the requested log level
     * @return the previous log level.
     */
    @objid ("bdf6123a-f74b-425f-930e-12a392b1f85c")
    public static LogLevel ensureLogLevel(LogLevel value) {
        if (logLevel.implies(value))
            return logLevel;
        return setLogLevel(value);
    }

}
