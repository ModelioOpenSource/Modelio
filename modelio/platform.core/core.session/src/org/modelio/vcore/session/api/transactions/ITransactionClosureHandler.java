/* 
 * Copyright 2013-2019 Modeliosoft
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
 * Handler called by the transaction manager on commit.
 */
@objid ("ca36c8d5-f1bb-4840-a8c7-f5a1636627a7")
public interface ITransactionClosureHandler {
    /**
     * Called during the transaction commit process.
     * 
     * @param transaction the transaction
     */
    @objid ("bb9589d6-a5ba-418c-9a17-dd6b0ad73c41")
    void commit(ITransaction transaction);

}
