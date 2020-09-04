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

package org.modelio.vcore.session.impl.transactions.events;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.model.change.ChangeCause;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.smkernel.IPStatus;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.SmStatus;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("01f419b4-0000-0b8b-0000-000000000000")
class StatusChangeEvent implements IStatusChangeEvent {
    @objid ("01f42120-0000-1a5e-0000-000000000000")
     boolean isCommit;

    @objid ("e61581bf-0137-4012-9226-18b8c67ea961")
    private Map<SmObjectImpl, Long> statusChanged = new HashMap<>();

    @objid ("ec691138-26f9-48db-a7e5-a0c5a22097f6")
     ChangeCause cause;

    @objid ("00037bae-f120-1f3c-aafd-001ec947cd2a")
    private Collection<SmObjectImpl> accessChanged = null;

    @objid ("000391fc-f120-1f3c-aafd-001ec947cd2a")
    private Collection<SmObjectImpl> auditStatusChanged = null;

    @objid ("0003a5ca-f120-1f3c-aafd-001ec947cd2a")
    private Collection<SmObjectImpl> cmsStatusChanged = null;

    @objid ("1ece7750-b149-4e53-9327-a6bc22b8132e")
    private Collection<SmObjectImpl> shellStateChanged = null;

    /**
     * Test whether the status change event is empty.
     * <p>
     * An empty status change event does not need to be fired.
     * @return <code>true</code> if the status change event is empty.
     */
    @objid ("da5447ea-c757-4cb6-98c7-e624c4ce0297")
    @Override
    public boolean isEmpty() {
        return this.statusChanged.isEmpty();
    }

    @objid ("33d5a18d-0eba-4d8c-ad90-3034f7cac0af")
    @Override
    public Collection<SmObjectImpl> getAccessChanged() {
        if (this.accessChanged == null) {
            this.accessChanged = new HashSet<>();
            
            fill (this.accessChanged, IRStatus.MASK_RACCESS 
                    | IPStatus.MASK_PACCESS 
                    | IRStatus.RMASK_MODIFIABLE_FORBIDDEN 
                    | IRStatus.RMASK_MODIFIABLE_REQUIRED);
        }
        return this.accessChanged;
    }

    @objid ("27abb038-dbb6-4be5-b39a-ae8a7faeed8a")
    @Override
    public Collection<SmObjectImpl> getCmsStatusChanged() {
        if (this.cmsStatusChanged == null) {
            this.cmsStatusChanged = new HashSet<>();
            
            fill (this.cmsStatusChanged, IRStatus.MASK_CMS);
        }
        return this.cmsStatusChanged;
    }

    @objid ("2369d7d8-7e42-4c33-a9aa-3afea8917055")
    @Override
    public Collection<SmObjectImpl> getAuditStatusChanged() {
        if (this.auditStatusChanged == null) {
            this.auditStatusChanged = new HashSet<>();
        
            fill (this.auditStatusChanged, IRStatus.AUDIT1 | IRStatus.AUDIT2);
        }
        return this.auditStatusChanged;
    }

    /**
     * Fill a collection with elements whose the given status flags changed.
     * @param coll the collection to fill
     * @param flagsToCompare the status flags to compare.
     */
    @objid ("e980882e-6e50-4b66-a2a7-04f55faf55d4")
    private void fill(Collection<SmObjectImpl> coll, long flagsToCompare) {
        for (Entry<SmObjectImpl, Long> entry : this.statusChanged.entrySet()) {
            SmObjectImpl obj = entry.getKey();
            long oldStatus = entry.getValue();
            long newStatus = obj.getData().getStatus();
        
            if (SmStatus.getBits(oldStatus, flagsToCompare) != SmStatus.getBits(newStatus, flagsToCompare))
                coll.add(obj);
        }
    }

    @objid ("54161df3-294e-44e8-b80b-a6b806434385")
    @Override
    public Collection<SmObjectImpl> getShellStateChanged() {
        if (this.shellStateChanged == null) {
            this.shellStateChanged = new HashSet<>();
            
            fill (this.shellStateChanged, IRStatus.SHELL);
        }
        return this.shellStateChanged;
    }

    @objid ("bfe8aa7e-3765-4cc4-a124-208602613dd7")
    @Override
    public Map<SmObjectImpl, Long> getStatusChanged() {
        return this.statusChanged;
    }

    /**
     * Remove a model object from the list of changed status elements.
     * @param deletedEl the model object to remove.
     */
    @objid ("4b8d8d27-ffc2-4326-9e2f-e5f8ce04a7df")
    void remove(MObject deletedEl) {
        this.statusChanged.remove(deletedEl);
    }

    /**
     * Add a changed element.
     * @param refered the model object whose status changed
     * @param oldStatus the old object status
     * @param newStatus the new object status
     */
    @objid ("6d11a1f0-a0e0-4e56-aa02-d82212010e29")
    public void add(SmObjectImpl refered, long oldStatus, long newStatus) {
        if (! this.statusChanged.containsKey(refered))
            this.statusChanged.put(refered, oldStatus);
    }

    @objid ("742afebb-edba-4a0f-95fe-2d247a5424cf")
    @Override
    public ChangeCause getCause() {
        return this.cause;
    }

}
