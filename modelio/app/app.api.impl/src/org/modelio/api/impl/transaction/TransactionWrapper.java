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

package org.modelio.api.impl.transaction;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.transactions.ITransaction;

/**
 * Wrapper between mda transaction and jni transaction.
 */
@objid ("c5a16330-4182-4101-91db-6114cc89c5c6")
public class TransactionWrapper implements org.modelio.api.modelio.model.ITransaction {
    @objid ("3786d50a-095a-470a-b703-2189c20d3015")
    private ITransaction wrapped = null;

    /**
     * Constructor
     * 
     * @param wrapped the wrapped transaction.
     */
    @objid ("1c44d7fb-06af-4003-9fc7-7e4545fb8f2b")
    public TransactionWrapper(ITransaction wrapped) {
        this.wrapped = wrapped;
    }

    /**
     * @return the wrapped transaction
     */
    @objid ("cb337759-5614-4a26-a75a-90774c603f18")
    public ITransaction getWrapped() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.wrapped;
    }

    @objid ("a53268dd-4482-40e7-9a0f-4bd8a184ae2a")
    @Override
    public void commit() {
        this.wrapped.commit();
    }

    @objid ("87e062bd-2177-4e48-8388-30f7567f76d6")
    @Override
    public void rollback() {
        this.wrapped.rollback();
    }

    @objid ("a420e70b-0e71-41eb-8401-b841689eb185")
    @Override
    public void close() throws RuntimeException {
        this.wrapped.close();
    }

}
