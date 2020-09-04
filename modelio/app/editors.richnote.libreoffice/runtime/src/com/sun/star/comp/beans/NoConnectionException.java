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

package com.sun.star.comp.beans;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This exception is called when a method is called which needs an established connection but no connection is
 * established yet or anymore.
 * 
 * @since OOo 2.0.0
 */
@objid ("aa17205f-d805-4e1e-bc99-d8dd9b3045ae")
public class NoConnectionException extends Exception {
    @objid ("13250774-f20e-48af-9e96-2b9511555442")
    private static final long serialVersionUID = 1L;

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
    @objid ("ef232ec3-9446-4380-a649-90bcd0e773ca")
    public NoConnectionException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with <code>null</code> as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    @objid ("57985f70-d116-492c-9795-73254025dd8a")
    public NoConnectionException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     * @param   message   the detail message. The detail message is saved for
     * later retrieval by the {@link #getMessage()} method.
     */
    @objid ("695d11fd-e8d7-4cff-b552-8f031f72d779")
    public NoConnectionException(final String message) {
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
     * @since  Modelio 3.7
     */
    @objid ("b199dac8-5b6b-4908-ad8b-73a388972579")
    public NoConnectionException(String message, final Throwable cause) {
        super(message, cause);
    }

}
