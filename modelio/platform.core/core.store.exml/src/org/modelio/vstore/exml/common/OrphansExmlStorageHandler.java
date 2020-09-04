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

package org.modelio.vstore.exml.common;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.smkernel.RepositoryObjectStub;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Storage handler for orphan elements.
 * <p>
 * Used mainly to ease debugging of orphans/detached/duplicate objects.
 * 
 * @author cmarin
 */
@objid ("085c1537-821d-4f5a-85af-f4c878ead258")
public class OrphansExmlStorageHandler extends RepositoryObjectStub {
    @objid ("fd478f67-498e-4a89-be85-4e50513fd8b7")
    private static final boolean TRACE = false;

    @objid ("d7456c9f-eb07-43ac-bbf4-4d002e434ace")
    private final String cause;

    @objid ("38ed2226-4237-4c46-ad80-42815797de87")
    private final AbstractExmlRepository repository;

    @objid ("cd49f70a-f954-4bff-b589-5d1c71c3236d")
    public OrphansExmlStorageHandler(AbstractExmlRepository repository, String cause) {
        this.repository = repository;
        this.cause = cause;
    }

    @objid ("b879d591-9d31-409d-91d7-351ee4c9a260")
    @Override
    public void depValAppended(SmObjectImpl obj, SmDependency dep, SmObjectImpl val) {
        if (dep.isCompositionOpposite()) {
            if (val.getRepositoryObject() != this) {
                // Trigger again the repository object to ask him to propagate the handler
                // We need this because:
                // - ExmlStorageHandler.depValAppended indirectly uses on getCompositionOwner() 
                // - getCompositionOwner() implementation on AssociationEnd depends 
                //   on dependencies not yet set.
                if (TRACE) {
                    String jclsName = getClass().getSimpleName();
                    Log.trace("%s: %s.%s.append(%s): the source is %s.", jclsName, obj, dep, val,this.cause);
                    Log.trace("%s:  ask %s to propagate handler (%s) to %s", jclsName, val, val.getRepositoryObject().getClass().getSimpleName(), obj);
                    Log.trace("%s:    %s owner = %s" , jclsName, val, val.getCompositionOwner());
                    Log.trace("%s:    %s owner = %s" , jclsName, obj, obj.getCompositionOwner());
                    val.getRepositoryObject().depValAppended(val, dep.getSymetric(), obj);
                    if (obj.getRepositoryObject() == this) {
                        Log.trace("%s:    ==> failed, still same handler." , jclsName);
                    } else {
                        Log.trace("%s:   ==> success:  new repo object=%s" , jclsName, obj.getRepositoryObject());
                    }
                } else {
                    val.getRepositoryObject().depValAppended(val, dep.getSymetric(), obj);
                }
            }
        }
        /*if (dep.isPartOf() || dep.isComposition() || dep.isSharedComposition()) {
            SmDependency opp = dep.getSymetric();
            if (! opp.isComposition() || opp.isSharedComposition()) {
                throw new IllegalStateException(String.format("%s.%s.append(%s): the source is %s.", obj, dep, val,this.cause));
            }
        }*/
    }

    @objid ("2cb533d0-d528-4f82-a8a4-e5c5f77196a7")
    @Override
    public byte getRepositoryId() {
        return this.repository.getRepositoryId();
    }

    @objid ("3ca6e1d5-ded6-40b4-b23f-e9b06e2c90c5")
    @Override
    public String toString() {
        return String.format("Orphans EXML handler for %s objects on %s", this.cause, this.repository);
    }

    @objid ("e8189df5-3393-4fd3-9434-7341ea9f918b")
    @Override
    protected void writeObjectCalled(SmObjectImpl obj) throws IllegalStateException {
        // assert disabled : throw new IllegalStateException(obj+" is "+this.cause);
    }

}
