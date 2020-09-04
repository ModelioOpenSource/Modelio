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

package org.modelio.vcore.session.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.session.impl.storage.memory.MemoryRepository;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.ShellObjectException;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Repository for shell objects.
 * <p>
 * <li>Denies any modification to the owned objects.
 * <li>{@link #attach(SmObjectImpl)} set the object as shell.
 * <li>{@link #detach(SmObjectImpl)} removes the shell flag.
 */
@objid ("1534d188-929f-11e1-81e9-001ec947ccaf")
class ShellObjectsRepository extends MemoryRepository {
    @objid ("be091ddf-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public void attModified(final SmObjectImpl obj, final SmAttribute att) {
        //        // Allow only modification of name and status.
        //        if (att.isNameAtt() || att.equals(SmObjectData.Metadata.statusAtt()))
        //            super.attModified(obj, att);
        //        else
                    throw new ShellObjectException(obj);
    }

    @objid ("be091df0-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public void depValMoved(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl val) {
        throw new ShellObjectException(obj);
    }

    @objid ("f5260e87-08b1-11e2-b33c-001ec947ccaf")
    @Override
    public void attach(SmObjectImpl obj) {
        super.attach(obj);
        try (final IModelLoader loader = getModelLoaderProvider().beginRefreshSession()) {
            // Add shell flag
            loader.setRStatus(obj, IRStatus.SHELL, 0, 0);
        }
    }

    @objid ("f5260e8b-08b1-11e2-b33c-001ec947ccaf")
    @Override
    public void detach(SmObjectImpl obj) {
        try (final IModelLoader loader = getModelLoaderProvider().beginRefreshSession()) {
            // Remove shell flag
            loader.setRStatus(obj, 0,  IRStatus.SHELL, 0);
        }
        super.detach(obj);
    }

    @objid ("26a2568f-3579-11e2-a87b-001ec947ccaf")
    @Override
    public void addObject(SmObjectImpl obj) {
        super.addObject(obj);
        try (final IModelLoader loader = getModelLoaderProvider().beginRefreshSession()) {
            // Add shell flag
            loader.setRStatus(obj, IRStatus.SHELL, 0, 0);
        }
    }

}
