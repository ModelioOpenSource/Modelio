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

package org.modelio.vbasic.log;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Modelio kernel logging interface.
 * @author cmarin
 * @since 3.4
 */
@objid ("cdb3e727-8f58-4c2d-b6a9-1424dc772176")
public interface IBasicLogger {
    /**
     * Log a trace message
     * @param message the message
     */
    @objid ("059b4666-134c-46fb-8b1d-d5b9bf90444d")
    void trace(final String message);

    /**
     * Log an error
     * @param message the message
     */
    @objid ("523fb65e-89d7-4ef2-859f-b4a2bbefe713")
    void error(final String message);

    /**
     * @param format see {@link String#format(String, Object...)}
     * @param args format arguments
     */
    @objid ("99b8d566-e86a-490c-8803-b1b2f0faecb3")
    void warning(final String format, final Object... args);

    /**
     * Log an exception with its stack trace as warning.
     * @param ex the exception
     */
    @objid ("785fe325-c699-4c64-a8ee-e6fa3d02e786")
    void warning(final Throwable ex);

    /**
     * @param message the message
     */
    @objid ("430bef73-6e77-4a48-9772-209b7c783b4e")
    void warning(final String message);

    /**
     * @param format see {@link String#format(String, Object...)}
     * @param args format arguments
     */
    @objid ("59e32dbb-9b31-4d79-b609-0e023f20e478")
    void trace(final String format, final Object... args);

    /**
     * Log an exception with its stack trace as trace.
     * @param ex the exception
     */
    @objid ("35d11840-e0e8-4279-9d98-9af6900d98ad")
    void trace(final Throwable ex);

    /**
     * @param format see {@link String#format(String, Object...)}
     * @param args format arguments
     */
    @objid ("078f636e-417d-4e66-85da-4b391d5c018d")
    void error(final String format, final Object... args);

    /**
     * Log an exception with its stack trace as error.
     * @param ex the exception
     */
    @objid ("d4a96221-1974-44a6-bfb4-3bc6bc738077")
    void error(final Throwable ex);

}
