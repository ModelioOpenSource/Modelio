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

package org.modelio.gproject.model.impl.copy;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.model.impl.importer.core.AbstractObjectFinder;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("01f419b4-0000-2b47-0000-000000000000")
class CopierObjectFinder extends AbstractObjectFinder {
    @objid ("000e6fa0-5247-1091-8d81-001ec947cd2a")
    private final CopyMachine copyMachine;

    @objid ("01f419b4-0000-2b50-0000-000000000000")
    public CopierObjectFinder(CopyMachine copyMachine, IModel searchedSession, MMetamodel metamodel) {
        super(searchedSession, metamodel);
        this.copyMachine = copyMachine;
    }

    @objid ("01f419b4-0000-2b6b-0000-000000000000")
    @Override
    public SmObjectImpl getSameObject(final SmObjectImpl searchedObject) {
        // Find corresponding in the cloned objects
        SmObjectImpl ret = this.copyMachine.getMappedObject(searchedObject);
        if (ret == null) {
            if (this.copyMachine.isMonoSession()) {
                // Return the same
                ret = searchedObject;
            } else {
                // Return the same object in the destination project
                ret = (SmObjectImpl) this.searchedSession.findByRef(new MRef(searchedObject));
            }
        }
        return ret;
    }

}
