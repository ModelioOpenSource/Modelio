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

package org.modelio.diagram.persistence;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Indicates that reading or writing persistent elements failed.
 * 
 * @author cmarin
 */
@objid ("cb7049cf-186f-11e2-92d2-001ec947c8cc")
public class PersistenceException extends RuntimeException {
    @objid ("cb7049d1-186f-11e2-92d2-001ec947c8cc")
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new persistence exception with the specified cause.
     * <p>
     * The detail message associated with <code>cause</code> is automatically incorporated in this exception's detail
     * message.
     * 
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method). (A <tt>null</tt>
     * value is not permitted.)
     */
    @objid ("cb7049d3-186f-11e2-92d2-001ec947c8cc")
    public PersistenceException(Throwable cause) {
        super(cause.getLocalizedMessage(), cause);
    }

    /**
     * Constructs a new persistence exception with the specified detail message. The cause is not initialized, and may
     * subsequently be initialized by a call to {@link #initCause}.
     * 
     * @param message the detail message. The detail message is saved for later retrieval by the {@link #getMessage()}
     * method.
     */
    @objid ("cb7049d7-186f-11e2-92d2-001ec947c8cc")
    public PersistenceException(String message) {
        super(message);
    }

    /**
     * Constructs a new persistence exception with the specified detail message and cause.
     * <p>
     * Note that the detail message associated with <code>cause</code> is <i>not</i> automatically incorporated in this
     * runtime exception's detail message.
     * 
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method). (A <tt>null</tt>
     * value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    @objid ("cb7049db-186f-11e2-92d2-001ec947c8cc")
    public PersistenceException(String message, Exception cause) {
        super(message, cause);
    }

}
