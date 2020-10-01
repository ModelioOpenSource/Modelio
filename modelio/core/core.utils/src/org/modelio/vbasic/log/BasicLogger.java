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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * A simple Logger utility, logging into System.out.
 * <p>
 * Ignores the 'log level', all messages are always logged.
 * </p>
 */
@objid ("000dece2-e3a3-1f33-b94f-001ec947cd2a")
class BasicLogger implements IBasicLogger {
    @objid ("000bc43a-e3a3-1f33-b94f-001ec947cd2a")
    private static final String[] PREFIXS = { "INFO: ", "WARN: ", " ERR: " };

    @objid ("77b26ef8-9b64-11e1-94a3-001ec947ccaf")
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS ");

    @objid ("00259388-e3a3-1f33-b94f-001ec947cd2a")
    protected void log(final int level, final String message) {
        StringBuilder builder = new StringBuilder(256);
        
        synchronized (BasicLogger.dateFormatter) {
            builder.append(BasicLogger.dateFormatter.format(new Date()));
        }
        builder.append(BasicLogger.PREFIXS[level]);
        builder.append(message);
        
        print(builder.toString());
    }

    /**
     * Prints the message to System.out. Called by the default
     * implementation of {@link #log(int, String, String, Throwable)}.
     */
    @objid ("0025ace2-e3a3-1f33-b94f-001ec947cd2a")
    protected static void print(final String message) {
        System.out.println(message);
    }

    /**
     * @param format see {@link String#format(String, Object...)}
     * @param args format arguments
     */
    @objid ("2b5c050f-004e-4bd8-adec-3fe20d6eff41")
    @Override
    public void warning(final String format, final Object... args) {
        log(IBasicLogger.WARNING, String.format(format, args));
    }

    /**
     * @param format see {@link String#format(String, Object...)}
     * @param args format arguments
     */
    @objid ("9dd71750-5534-4329-8e72-8b14e8f1625b")
    @Override
    public void trace(final String format, final Object... args) {
        log(IBasicLogger.TRACE, String.format(format, args));
    }

    @objid ("d6ebb9f6-43be-4d6c-8754-394186ca0900")
    @Override
    public void error(final Throwable ex) {
        log(IBasicLogger.ERROR, ex.getMessage());
        final StringWriter stackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(stackTrace));
        log(IBasicLogger.ERROR, stackTrace.toString());
    }

    /**
     * The logging methods
     */
    @objid ("82506d9e-a3dc-41b5-bfca-98a993914c50")
    @Override
    public void error(final String message) {
        log(IBasicLogger.ERROR, message);
    }

    @objid ("6055f922-4fa0-48bd-8e6a-9ee0b6d0f535")
    @Override
    public void warning(final String message) {
        log(IBasicLogger.WARNING, message);
    }

    @objid ("7f11df5f-f508-4478-97b8-00ab0ac60d78")
    @Override
    public void trace(final String message) {
        log(IBasicLogger.TRACE, message);
    }

    @objid ("60883a45-ea4d-416b-a59a-1cc61c194733")
    @Override
    public void trace(final Throwable ex) {
        log(IBasicLogger.TRACE, ex.getMessage());
    }

    @objid ("0a3f338c-056b-4936-bdff-e6d0efe84221")
    @Override
    public void warning(final Throwable ex) {
        log(IBasicLogger.WARNING, ex.getMessage());
        final StringWriter stackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(stackTrace));
        log(IBasicLogger.WARNING, stackTrace.toString());
    }

    /**
     * @param format see {@link String#format(String, Object...)}
     * @param args format arguments
     */
    @objid ("d0f4f4d3-eaa2-4bec-8a33-d576bdbf584c")
    @Override
    public void error(final String format, final Object... args) {
        log(IBasicLogger.ERROR, String.format(format, args));
    }

    @objid ("c8faf5f5-c790-4f63-95a0-cbd84cc6c738")
    @Override
    public int getLevel() {
        return IBasicLogger.TRACE;
    }

    @objid ("12d03153-5267-40e8-a293-d4e2fb054abe")
    @Override
    public void setLevel(int level) {
        // Ignored
    }

}
