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
package org.modelio.vcore.session.impl.transactions.smAction;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.transactions.TransactionException;

/**
 * Tells that a model modification occurred while no transaction is open.
 */
@objid ("006e7d78-0d1e-1f20-85a5-001ec947cd2a")
public class AddActionNoActiveTransactionException extends TransactionException {
    @objid ("007fef40-702b-1f21-85a5-001ec947cd2a")
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     * @param   msg   the detail message. The detail message is saved for
     * later retrieval by the {@link #getMessage()} method.
     */
    @objid ("006d48a4-0d1e-1f20-85a5-001ec947cd2a")
    public  AddActionNoActiveTransactionException(final String msg) {
        super(msg);
    }

}
