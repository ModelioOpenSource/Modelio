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

package org.modelio.vcore.session.api.transactions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Indicates that transaction creation failed.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("0c31c4d9-1069-4bd3-98f8-1d62613ea350")
public class TransactionCreationException extends TransactionException {
    @objid ("35bbc954-88df-43dd-9cce-d6399617813d")
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     * @param   message   the detail message. The detail message is saved for
     * later retrieval by the {@link #getMessage()} method.
     */
    @objid ("7d858ce5-d477-4c4f-b6c9-b89a212e39e5")
    public TransactionCreationException(final String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a
     * detail message of <tt>cause.getLocalizedMessage()</tt>
     * which typically contains the detail message of
     * <tt>cause</tt>.
     * @param  cause the cause (which is saved for later retrieval by the
     * {@link #getCause()} method).  (A <tt>null</tt> value is
     * not permitted.)
     * @since  1.4
     */
    @objid ("8b67aac6-d561-4ae7-bd99-7db284a9e9ca")
    public TransactionCreationException(final String message, Throwable cause) {
        super(message, cause);
    }

}
