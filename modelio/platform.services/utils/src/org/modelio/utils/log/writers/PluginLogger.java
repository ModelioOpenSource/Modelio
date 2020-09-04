/* 
 * Copyright 2013-2018 Modeliosoft
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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.Logger;
import org.modelio.utils.plugin.Utils;
import org.osgi.service.log.LogService;

/**
 * Logging utility class for plugins.
 */
@objid ("005988a0-404e-1fe3-9845-001ec947cd2a")
public class PluginLogger {
    @objid ("00851a42-4307-1fe3-9845-001ec947cd2a")
    private final Logger logger;

    @objid ("ccaa6369-0bea-44fd-937f-74fa90d5313b")
    public static int logLevel = LogService.LOG_ERROR;

    @objid ("008a1d9e-4307-1fe3-9845-001ec947cd2a")
    public PluginLogger(final Logger logger) {
        this.logger = logger;
    }

    @objid ("008ae936-4307-1fe3-9845-001ec947cd2a")
    public void error(final String msg) {
        if (PluginLogger.logLevel >= LogService.LOG_ERROR) {
            this.logger.log(LogService.LOG_ERROR, msg);
        }
    }

    @objid ("008b0664-4307-1fe3-9845-001ec947cd2a")
    public void error(final String format, final Object... args) {
        if (PluginLogger.logLevel >= LogService.LOG_ERROR) {
            this.logger.log(LogService.LOG_ERROR, String.format(format, args));
        }
    }

    @objid ("008b3d32-4307-1fe3-9845-001ec947cd2a")
    public void error(final Throwable e) {
        if (PluginLogger.logLevel >= LogService.LOG_ERROR) {
            this.logger.log(LogService.LOG_ERROR, e.getMessage(), e);
        }
    }

    @objid ("008b5c5e-4307-1fe3-9845-001ec947cd2a")
    public void warning(final String msg) {
        if (PluginLogger.logLevel >= LogService.LOG_WARNING) {
            this.logger.log(LogService.LOG_WARNING, msg);
        }
    }

    @objid ("008b79c8-4307-1fe3-9845-001ec947cd2a")
    public void warning(final String format, final Object... args) {
        if (PluginLogger.logLevel >= LogService.LOG_WARNING) {
            this.logger.log(LogService.LOG_WARNING, String.format(format, args));
        }
    }

    @objid ("008bb0f0-4307-1fe3-9845-001ec947cd2a")
    public void warning(final Throwable e) {
        if (PluginLogger.logLevel >= LogService.LOG_WARNING) {
            this.logger.log(LogService.LOG_WARNING, e.getMessage(), e);
        }
    }

    @objid ("008bcf04-4307-1fe3-9845-001ec947cd2a")
    public void info(final String msg) {
        if (PluginLogger.logLevel >= LogService.LOG_INFO) {
            this.logger.log(LogService.LOG_INFO, msg);
        }
    }

    @objid ("008bed2c-4307-1fe3-9845-001ec947cd2a")
    public void info(final String format, final Object... args) {
        if (PluginLogger.logLevel >= LogService.LOG_INFO) {
            this.logger.log(LogService.LOG_INFO, String.format(format, args));
        }
    }

    @objid ("008c2544-4307-1fe3-9845-001ec947cd2a")
    public void info(final Throwable e) {
        if (PluginLogger.logLevel >= LogService.LOG_INFO) {
            this.logger.log(LogService.LOG_INFO, e.getMessage(), e);
        }
    }

    @objid ("008c43e4-4307-1fe3-9845-001ec947cd2a")
    public void debug(final String msg) {
        if (PluginLogger.logLevel >= LogService.LOG_DEBUG) {
            if (this.logger.isLoggable(LogService.LOG_DEBUG)) {
                this.logger.log(LogService.LOG_DEBUG, msg);
            }
        }
    }

    @objid ("008c6360-4307-1fe3-9845-001ec947cd2a")
    public void debug(final String format, final Object... args) {
        if (PluginLogger.logLevel >= LogService.LOG_DEBUG) {
            if (this.logger.isLoggable(LogService.LOG_DEBUG)) {
                this.logger.log(LogService.LOG_DEBUG, String.format(format, args));
            }
        }
    }

    @objid ("008c9ee8-4307-1fe3-9845-001ec947cd2a")
    public void debug(final Throwable e) {
        if (PluginLogger.logLevel >= LogService.LOG_DEBUG) {
            if (this.logger.isLoggable(LogService.LOG_DEBUG)) {
                this.logger.log(LogService.LOG_DEBUG, e.getMessage(), e);
            }
        }
    }

    @objid ("004d691c-ca76-1fea-8789-001ec947cd2a")
    public boolean isDebugEnabled() {
        return (PluginLogger.logLevel >= LogService.LOG_DEBUG) && this.logger.isLoggable(LogService.LOG_DEBUG);
    }

    /**
     * Find the current Modelio log file from the logback current configuration.
     * @return the current Modelio log file, null if unable to find it.
     */
    @objid ("1ff3ef68-3b26-4aeb-836f-3079fe4e7b2a")
    public static String getLogFile() {
        return Utils.getLogFile();
    }

}
