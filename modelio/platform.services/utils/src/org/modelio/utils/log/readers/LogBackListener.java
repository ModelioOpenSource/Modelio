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

package org.modelio.utils.log.readers;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogListener;
import org.osgi.service.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This backend log listener uses LogBack to output the log messages caught from OSGI log service.
 * 
 * LogBack claims itself to be the log4j successor, being faster and less resource consuming...
 * 
 * The log configuration is defined in the logback.xml file from the config folder of the log plugin.
 * 
 * To change the log configuration at runtime, add the following parameter to the Java command line:
 * 
 * <i>java -Dlogback.configurationFile=/path/to/logback.xml</i>
 * 
 * For the format of the configuration file read the LogBack manual here: http://logback.qos.ch/manual
 * 
 * 
 * @author phv
 */
@objid ("0014ce0e-e8bd-1fea-93a7-001ec947cd2a")
public class LogBackListener implements LogListener {
    @objid ("00895594-f485-1fea-93a7-001ec947cd2a")
    private final Map<Long, Logger> mLoggers = new HashMap<>();

    @objid ("0014d0fc-e8bd-1fea-93a7-001ec947cd2a")
    @Override
    public void logged(LogEntry logEntry) {
        if (logEntry.getBundle().getSymbolicName().contains("modelio")) {
        
            // Get the Logger object for this bundle or create one it if none
            // exists.
            Logger logger = this.mLoggers.get(logEntry.getBundle().getBundleId());
            if (logger == null) {
                logger = LoggerFactory.getLogger(logEntry.getBundle().getSymbolicName());
                this.mLoggers.put(logEntry.getBundle().getBundleId(), logger);
            }
        
            // log the entry
            if (logEntry.getException() != null) {
                logMessageWithException(logger, logEntry);
            } else {
                logMessage(logger, logEntry);
            }
        }
    }

    @objid ("0089a562-f485-1fea-93a7-001ec947cd2a")
    private static void logMessage(Logger logger, LogEntry logEntry) {
        switch (logEntry.getLevel()) {
        case LogService.LOG_DEBUG:
            logger.debug(logEntry.getMessage());
            break;
        case LogService.LOG_INFO:
            logger.info(logEntry.getMessage());
            break;
        case LogService.LOG_WARNING:
            logger.warn(logEntry.getMessage());
            break;
        case LogService.LOG_ERROR:
            logger.error(logEntry.getMessage());
            break;
        default:
            logger.info(logEntry.getMessage());
            break;
        }
    }

    @objid ("0089db22-f485-1fea-93a7-001ec947cd2a")
    private static void logMessageWithException(Logger logger, LogEntry logEntry) {
        switch (logEntry.getLevel()) {
        case LogService.LOG_DEBUG:
            logger.debug(logEntry.getMessage(), logEntry.getException());
            break;
        case LogService.LOG_INFO:
            logger.info(logEntry.getMessage(), logEntry.getException());
            break;
        case LogService.LOG_WARNING:
            logger.warn(logEntry.getMessage(), logEntry.getException());
            break;
        case LogService.LOG_ERROR:
            logger.error(logEntry.getMessage(), logEntry.getException());
            break;
        default:
            logger.info(logEntry.getMessage(), logEntry.getException());
            break;
        }
    }

}
