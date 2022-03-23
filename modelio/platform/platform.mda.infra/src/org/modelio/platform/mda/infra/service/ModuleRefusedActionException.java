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
package org.modelio.platform.mda.infra.service;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.lifecycle.ModuleException;

/**
 * indicates the module implementation refused an action.
 * <p>
 * Thrown when IModule.select() or IModule.start() return false
 * 
 * @since Modelio 3.4
 */
@objid ("7fdc8c3f-8413-42a1-b4b1-a8c6a43a1f0b")
public class ModuleRefusedActionException extends ModuleException {
    @objid ("877a1438-2765-4d4f-9717-f399924179de")
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     * @param   message   the detail message. The detail message is saved for
     * later retrieval by the {@link #getMessage()} method.
     */
    @objid ("bc67face-b142-43e9-afdf-d762bd278980")
    public  ModuleRefusedActionException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     * @param  message the detail message (which is saved for later retrieval
     * by the {@link #getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     * {@link #getCause()} method).  (A <tt>null</tt> value is
     * permitted, and indicates that the cause is nonexistent or
     * unknown.)
     * @since  1.4
     */
    @objid ("7410cd9a-7f5d-45fa-b16e-b2bd3d4ecf57")
    public  ModuleRefusedActionException(String message, Throwable cause) {
        super(message, cause);
    }

}
