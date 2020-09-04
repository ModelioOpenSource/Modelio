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

@objid ("006f87cc-0d1e-1f20-85a5-001ec947cd2a")
public class UndoNoDoneTransactionException extends TransactionException {
    @objid ("007e3fe2-702b-1f21-85a5-001ec947cd2a")
    private static final long serialVersionUID = 1L;

    @objid ("006ef866-0d1e-1f20-85a5-001ec947cd2a")
    public UndoNoDoneTransactionException(final String msg) {
        super(msg);
    }

}
