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

package org.modelio.vcore.smkernel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MStatus;

@objid ("4739ab97-d281-11e1-b069-001ec947ccaf")
class MStatusImpl implements MStatus {
    @objid ("0e9894b5-d4cd-11e1-b069-001ec947ccaf")
    private final long status;

    @objid ("0e9894bb-d4cd-11e1-b069-001ec947ccaf")
    private static final long VISIBLE_MASK = IRStatus.DOMAINVISIBLE | IPStatus.OBJECTVISIBLE | IPStatus.OBJECTVISIBLE;

    @objid ("0e9894c0-d4cd-11e1-b069-001ec947ccaf")
    private static final long BROWSE_MASK = IRStatus.DOMAINBROWSE | IPStatus.OBJECTBROWSE | IPStatus.OBJECTBROWSE;

    @objid ("0e9894c5-d4cd-11e1-b069-001ec947ccaf")
    private static final long WRITE_MASK = IRStatus.DOMAINWRITE | IPStatus.OBJECTWRITE | IPStatus.OBJECTWRITE;

    @objid ("15f08282-7f2f-475b-b69c-bf005c627623")
    private final boolean dirty;

    @objid ("aaf50c0a-d287-11e1-b069-001ec947ccaf")
    MStatusImpl(SmObjectImpl obj) {
        long lstatus = obj.getSmStatusFlags();
        int count = 0;
        
        if (! SmStatus.isComplete(lstatus)) {
            SmObjectImpl owner = obj;
            while (! SmStatus.isComplete(lstatus)) {
                if (++count > 10000) {
                    throw new IllegalStateException(String.format("Cycle in composition graph of %s.", obj.toString()));
                }
                
                owner = owner.getCompositionOwner();
                if (owner == null) {
                    break;
                }
                lstatus = SmStatus.combine(lstatus, owner.getSmStatusFlags());
            }
        }
        
        this.status = lstatus;
        this.dirty = obj.getRepositoryObject().isDirty(obj);
    }

    @objid ("aaf50c0d-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isUserVisible() {
        return SmStatus.areAllSet(this.status, IRStatus.USERVISIBLE) == StatusState.TRUE;
    }

    @objid ("aaf50c12-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isUserBrowse() {
        return SmStatus.areAllSet(this.status, IRStatus.USERBROWSE) == StatusState.TRUE;
    }

    @objid ("aaf50c17-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isUserWrite() {
        return SmStatus.areAllSet(this.status, IRStatus.USERWRITE) == StatusState.TRUE;
    }

    @objid ("aaf50c1c-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isDomainVisible() {
        return SmStatus.areAllSet(this.status, IRStatus.DOMAINVISIBLE) == StatusState.TRUE;
    }

    @objid ("aaf50c21-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isDomainBrowse() {
        return SmStatus.areAllSet(this.status, IRStatus.DOMAINBROWSE) == StatusState.TRUE;
    }

    @objid ("aaf50c26-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isDomainWrite() {
        return SmStatus.areAllSet(this.status, IRStatus.DOMAINWRITE) == StatusState.TRUE;
    }

    @objid ("aaf50c2b-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isObjectVisible() {
        return SmStatus.areAllSet(this.status, IPStatus.OBJECTVISIBLE) == StatusState.TRUE;
    }

    @objid ("aaf50c30-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isObjectBrowse() {
        return SmStatus.areAllSet(this.status, IPStatus.OBJECTBROWSE) == StatusState.TRUE;
    }

    @objid ("aaf50c35-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isObjectWrite() {
        return SmStatus.areAllSet(this.status, IPStatus.OBJECTWRITE) == StatusState.TRUE;
    }

    @objid ("aaf76e40-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isVisible() {
        return SmStatus.areAllSet(this.status, VISIBLE_MASK) == StatusState.TRUE;
    }

    @objid ("aaf76e45-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isBrowse() {
        return SmStatus.areAllSet(this.status, BROWSE_MASK) == StatusState.TRUE;
    }

    @objid ("aaf76e4a-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isWrite() {
        return SmStatus.areAllSet(this.status, WRITE_MASK) == StatusState.TRUE;
    }

    @objid ("aaf76e4f-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isCmsSync() {
        return SmStatus.areAllSet(this.status, IRStatus.CMSSYNC) == StatusState.TRUE;
    }

    @objid ("aaf76e54-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isCmsModified() {
        return SmStatus.areAllSet(this.status, IRStatus.CMSMODIFIED) == StatusState.TRUE;
    }

    @objid ("aaf76e59-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isCmsToAdd() {
        return SmStatus.areAllSet(this.status, IRStatus.CMSTOADD) == StatusState.TRUE;
    }

    @objid ("b865e7e9-08ce-4f60-aa63-289de4d7a4b1")
    @Override
    public boolean isCmsToDelete() {
        return SmStatus.areAllSet(this.status, IRStatus.CMSTODELETE) == StatusState.TRUE;
    }

    @objid ("aaf76e5e-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isCmsReadOnly() {
        return SmStatus.areAllSet(this.status, IRStatus.CMSREADONLY) == StatusState.TRUE;
    }

    @objid ("aaf76e63-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isCmsManaged() {
        return SmStatus.areAllSet(this.status, IRStatus.CMSMANAGED) == StatusState.TRUE;
    }

    @objid ("4293fbad-a039-4361-b3ea-68b1a6bffe2b")
    @Override
    public boolean isCmsConflict() {
        return SmStatus.areAllSet(this.status, IRStatus.CMSCONFLICT) == StatusState.TRUE;
    }

    @objid ("aaf76e68-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isRamc() {
        return SmStatus.areAllSet(this.status, IRStatus.RAMC) == StatusState.TRUE;
    }

    @objid ("aaf76e6d-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isShell() {
        return SmStatus.areAllSet(this.status, IRStatus.SHELL) == StatusState.TRUE;
    }

    @objid ("aaf76e72-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isDeleted() {
        return SmStatus.areAllSet(this.status, IRStatus.DELETED) == StatusState.TRUE;
    }

    @objid ("aaf76e77-d287-11e1-b069-001ec947ccaf")
    @Override
    public boolean isModifiable() {
        final StatusState forbid = SmStatus.isAnySet(this.status, IRStatus.RMASK_MODIFIABLE_FORBIDDEN);
        final StatusState allow = SmStatus.areAllSet(this.status, IRStatus.RMASK_MODIFIABLE_REQUIRED);
        return allow != StatusState.FALSE && forbid!=StatusState.TRUE;
    }

    @objid ("2017e4d7-1e9d-11e2-90db-001ec947ccaf")
    @Override
    public String toString() {
        return SmStatus.toString(this.status);
    }

    @objid ("40954dd5-0788-4470-a829-f04e01807155")
    @Override
    public boolean isLockingNeeded() {
        return SmStatus.areAllSet(this.status, IRStatus.CMSNEEDSLOCK) == StatusState.TRUE;
    }

    @objid ("ae05f11d-b748-4e3a-9ab2-3ed8ce7cedc0")
    @Override
    public boolean isDirty() {
        return this.dirty;
    }

}
