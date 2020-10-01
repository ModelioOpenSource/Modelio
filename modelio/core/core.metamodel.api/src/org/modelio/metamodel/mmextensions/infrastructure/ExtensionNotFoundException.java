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

package org.modelio.metamodel.mmextensions.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Thrown when a Stereotype, TagType, NoteType or RichNote has not been found.
 * <p>
 * Made a <code>ExtensionNotFoundException</code> a runtime exception since Modelio 3.7.1 .
 */
@objid ("a75a0c47-0e6d-4756-9546-51c118f8b5f7")
public class ExtensionNotFoundException extends IllegalArgumentException {
    @objid ("cb34c75a-749d-4090-ae41-844745181ba6")
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with <code>null</code> as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    @objid ("389c36af-ad52-4070-9cf8-d87264f985ea")
    public ExtensionNotFoundException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     * @param   message   the detail message. The detail message is saved for
     * later retrieval by the {@link #getMessage()} method.
     */
    @objid ("5e67672e-a504-412e-b0f1-53e249be362f")
    public ExtensionNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * <code>cause</code> is <i>not</i> automatically incorporated in
     * this exception's detail message.
     * @param  message the detail message (which is saved for later retrieval
     * by the {@link #getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     * {@link #getCause()} method).  (A <tt>null</tt> value is
     * permitted, and indicates that the cause is nonexistent or
     * unknown.)
     */
    @objid ("404596e2-2113-4620-a111-8737bd9f333d")
    public ExtensionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables (for example, {@link
     * java.security.PrivilegedActionException}).
     * @param  cause the cause (which is saved for later retrieval by the
     * {@link #getCause()} method).  (A <tt>null</tt> value is
     * permitted, and indicates that the cause is nonexistent or
     * unknown.)
     */
    @objid ("85553e9b-080d-4f81-866a-44ea7fc491ba")
    public ExtensionNotFoundException(Throwable cause) {
        super(cause);
    }

}
