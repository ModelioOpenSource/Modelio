/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.utils.plugin;

import java.util.Iterator;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.FileAppender;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.LoggerFactory;

@objid ("0049aab6-4382-1fe3-9845-001ec947cd2a")
public class Utils implements BundleActivator {
    @objid ("0055b0ea-4447-1fe3-9845-001ec947cd2a")
    public static final String PLUGIN_ID = "org.modelio.utils";

    @objid ("003cb1f8-0db0-1feb-93a7-001ec947cd2a")
    public static final String MODELIO_LOGFILENAME = "modelio.log";

// private LogBackListener logbackListener;
    @objid ("004ffcae-4447-1fe3-9845-001ec947cd2a")
    @Override
    public void start(BundleContext bundleContext) {
        plugKernelLogToEclipseLog();
    }

    @objid ("005028aa-4447-1fe3-9845-001ec947cd2a")
    @Override
    public void stop(BundleContext bundleContext) {
    }

    @objid ("5d0016a8-64f0-493c-9f8f-0158238c4637")
    private void plugKernelLogToEclipseLog() {
        // Set Modelio kernel logger
        org.modelio.vbasic.log.Log.setLogger(new KernelLogger());
    }

    /**
     * Find the current Modelio log file from the logback current configuration.
     * 
     * @return the current Modelio log file, null if unable to find it.
     */
    @objid ("2ba8983e-a272-477b-811d-dc94502b0907")
    public static String getLogFile() {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        
        // Look for default FILE appender on the root logger.
        Logger rootLogger = lc.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        FileAppender<ILoggingEvent> app = (FileAppender<ILoggingEvent>) rootLogger.getAppender("LOGFILE");
        
        if (app != null) {
            return app.getFile();
        }
        
        // configuration file hacked, return the first file appender.
        for (Logger logger : lc.getLoggerList()) {
            Iterator<Appender<ILoggingEvent>> it = logger.iteratorForAppenders();
            while (it.hasNext()) {
                Appender<ILoggingEvent> appender = it.next();
                if (appender instanceof FileAppender) {
                    return ((FileAppender<ILoggingEvent>) appender).getFile();
                }
            }
        }
        
        // don't know what to do
        return null;
    }

}
