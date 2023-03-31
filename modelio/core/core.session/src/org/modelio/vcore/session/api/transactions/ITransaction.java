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

/**
 * Represents a model modification transaction.
 * <p>
 * The model can be modified only if a transaction is open.
 * A transaction should be used the following way:
 * <pre>
 * CoreSession session = ...
 * try (ITransaction t = session.createTransaction("do something")) {
 * // code
 * ...
 * 
 * // Commit transaction
 * t.commit();
 * }
 * </pre>
 */
@objid ("7484de1d-bab8-11e1-9fd3-001ec947ccaf")
public interface ITransaction extends AutoCloseable {
    /**
     * Commit a transaction <br>
     * 
     * If the active stack is empty, we are closing and committing an 'undo'
     * block, therefore let stack the closed transaction in the "undo" stack
     * (only if it is 'undoable' otherwise just delete it).
     * <p>
     * @throws: EndTransactionBadIdException if the transaction being committed
     * is not the currently active one (Sequence error)
     * @throws: EndTransactionBadIdException if there is no currently active
     * transaction.
     */
    @objid ("6ae9a80d-bab9-11e1-9fd3-001ec947ccaf")
    void commit();

    /**
     * Rollback the currently active transaction.
     * <p>
     * The actions of the currently active transaction are undo and the
     * transaction removed from the active stack.
     */
    @objid ("6ae9a80c-bab9-11e1-9fd3-001ec947ccaf")
    void rollback();

    /**
     * Disable undo/redo on the transaction.
     */
    @objid ("60d87889-babd-11e1-9fd3-001ec947ccaf")
    void disableUndo();

    @objid ("008a88a6-d715-1fe1-9845-001ec947cd2a")
    @Override
    void close() throws RuntimeException;

    /**
     * Get the transaction's name.
     * @return the name of the transaction.
     */
    @objid ("75d0b810-1e9d-11e2-8009-002564c97630")
    String getName();
}

