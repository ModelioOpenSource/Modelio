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

/**
 * This interface is used to record modifications done to the model,
 * so that they can be rollbacked in case of error.
 */
@objid ("006e7896-0d1e-1f20-85a5-001ec947cd2a")
public interface IActionManager {
    /**
     * Add the given action to this action manager.
     * @param action the action to add
     * @throws AddActionNoActiveTransactionException if no transaction is open.
     */
    @objid ("006e9c68-0d1e-1f20-85a5-001ec947cd2a")
    void addAction(final IAction action) throws AddActionNoActiveTransactionException;

    /**
     * Returns whether action recording is enabled or not.
     * @return <code>true</code> if action recording is enabled, <code>false</code> in the other case.
     */
    @objid ("006e9d12-0d1e-1f20-85a5-001ec947cd2a")
    boolean isEnabled();

    /**
     * Tells whether a transaction is open.
     * @return <code>true</code> if a transaction is open else false.
     */
    @objid ("f1cc6452-b8f8-4681-8ddb-d7acb530d90c")
    boolean hasCurrentTransaction();
}

