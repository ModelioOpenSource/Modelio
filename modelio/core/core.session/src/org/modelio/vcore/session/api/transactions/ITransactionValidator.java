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
 * An ITransactionValidator is used by the TransactionManager to check a
 * transaction contents before commiting.
 * If the validate() method returns false, the transaction currently being
 * committed is automatically rollbacked.
 * The typical implementation of a ITransactionValidator is the core audit
 * (ModelShield)
 * 
 * 
 * @author phv
 */
@objid ("004b5852-81e9-1033-9188-001ec947cd2a")
public interface ITransactionValidator {
    @objid ("005ea11e-8239-1033-9188-001ec947cd2a")
    void validate(Transaction currentTransaction);
}

