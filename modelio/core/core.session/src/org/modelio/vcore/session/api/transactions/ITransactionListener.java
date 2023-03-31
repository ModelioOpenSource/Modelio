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
import org.modelio.vcore.session.impl.transactions.Transaction;

/**
 * This listener is called for each transaction being committed or aborted, whether or not the transaction is a top-level transaction.
 * It's main purpose is to connect the "core auditor" to detect blocking semantic errors.
 * @author phv
 */
@objid ("006f8380-0d1e-1f20-85a5-001ec947cd2a")
public interface ITransactionListener {
    @objid ("006ec6f2-0d1e-1f20-85a5-001ec947cd2a")
    void onCommitTransaction(final Transaction theTransaction, final boolean isSession);

    @objid ("006ec79c-0d1e-1f20-85a5-001ec947cd2a")
    void onAbortTransaction(final Transaction theTransaction, final boolean isSession);

    @objid ("00189c64-5b91-1f4e-bf23-001ec947cd2a")
    void onUndoTransaction(final Transaction theTransaction);

    @objid ("001917ac-5b91-1f4e-bf23-001ec947cd2a")
    void onRedoTransaction(final Transaction theTransaction);
}

