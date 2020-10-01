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

package org.modelio.vcore.smkernel;

import java.util.Collection;
import java.util.HashSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

/**
 * Helper class that deletes a model object.
 */
@objid ("bdae8001-cb6b-11e1-87f1-001ec947ccaf")
class DeleteHelper {
    /**
     * Initialize the deleter.
     */
    @objid ("bdae8004-cb6b-11e1-87f1-001ec947ccaf")
    public DeleteHelper() {
    }

    /**
     * Delete the given model object
     * 
     * @param objToDelete the object to delete.
     */
    @objid ("bdae8007-cb6b-11e1-87f1-001ec947ccaf")
    public void doDelete(SmObjectImpl objToDelete) {
        // Compute all objects to delete
        Collection<SmObjectImpl> toDelete = new HashSet<>();
        getAllComponents(objToDelete, toDelete);
        
        // Set all objects as being deleted
        for (SmObjectImpl obj : toDelete) {
            obj.getData().setRFlags(IRStatus.BEINGDELETED, 0, 0);
        }
        
        boolean success = false;
        try {
            // Detach deleted objects from alive ones, only from the other side.
            for (SmObjectImpl obj : toDelete) {
                SmClass cls = obj.getClassOf();
                for (final SmDependency dep : cls.getAllDepDef()) {
                    if (!dep.isToDelete()) {
                        SmDependency opposite = dep.getSymetric();
                        if (opposite != null) {
                            boolean eraseReadonly = ! isToSkipReadonly(opposite);
                            for (SmObjectImpl target : obj.getDepValList(dep)) {
                                if (target.isValid() && (eraseReadonly || target.isModifiable())) {
                                    target.getMetaOf().eraseObjDepVal(target, opposite, obj);
                                }
                            }
                        }
                    }
                }
            }
        
            // Set all objects as deleted
            for (SmObjectImpl obj : toDelete) {
                obj.getMetaOf().deleteObject(obj);
            }
        
            success = true;
        
        } finally {
            if (!success) {
                // Transaction will be rollbacked except objects state,
                // that must be set back here.
                for (SmObjectImpl obj : toDelete) {
                    final ISmObjectData data = obj.getData();
                    setAsValid(data);
                }
            }
        }
    }

    @objid ("00396a66-1199-1f35-b94f-001ec947cd2a")
    private static void setAsValid(final ISmObjectData data) {
        data.setRFlags(0, IRStatus.DELETED | IRStatus.BEINGDELETED, 0);
    }

    @objid ("05786125-0b7e-4334-9c64-6918537fa477")
    private void getAllComponents(SmObjectImpl obj, Collection<SmObjectImpl> toDelete) {
        toDelete.add(obj);
        final SmClass cls = obj.getClassOf();
        for (final SmDependency dep : cls.getAllDepDef()) {
            if (dep.isToDelete()) {
                for (SmObjectImpl c : obj.getDepValList(dep)) {
                    if (!toDelete.contains(c)) {
                        getAllComponents(c, toDelete);
                    }
                }
            }
        }
    }

    @objid ("bc547458-54d1-4d68-b6bb-ccf4510fdd1b")
    private boolean isToSkipReadonly(SmDependency dep) {
        return (dep.hasDirective(SmDirective.SMCD_KEEP_DELETED_ON_READONLY));
    }

}
