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

package org.modelio.vcore.session.api.transactions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.transactions.ITransactionSupport;

/**
 * Root class for all transaction exceptions thrown by {@link ITransactionSupport}.
 */
@objid ("006e7918-0d1e-1f20-85a5-001ec947cd2a")
public abstract class TransactionException extends RuntimeException {
    @objid ("00819a8e-702b-1f21-85a5-001ec947cd2a")
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     * @param   message   the detail message. The detail message is saved for
     * later retrieval by the {@link #getMessage()} method.
     */
    @objid ("006d4d22-0d1e-1f20-85a5-001ec947cd2a")
    public TransactionException(final String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     * @param  message the detail message (which is saved for later retrieval
     * by the {@link #getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     * {@link #getCause()} method).  (A <tt>null</tt> value is
     * permitted, and indicates that the cause is nonexistent or
     * unknown.)
     */
    @objid ("4247e9e8-1933-4615-875f-9e37651ce755")
    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }

}
