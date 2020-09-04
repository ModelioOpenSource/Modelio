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

package org.modelio.api.impl.model.change;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.event.IStatusChangeEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This implementation works as a proxy between API and vcore.
 */
@objid ("2bb9afd5-b24f-4695-960c-01e608f4a6a0")
public class StatusChangeEvent implements IStatusChangeEvent {
    @objid ("c9344d83-0119-49a9-a3b0-6a0f67a6d443")
    private org.modelio.vcore.session.api.model.change.IStatusChangeEvent coreEvent;

    @objid ("69ef3a39-8228-4580-b3de-ca0473f4b5f4")
    public StatusChangeEvent(org.modelio.vcore.session.api.model.change.IStatusChangeEvent coreEvent) {
        this.coreEvent = coreEvent;
    }

    @objid ("d34c4bcf-9081-4da3-8217-85d2aaa20e16")
    @Override
    public Collection<MObject> getAccessChanged() {
        ArrayList<MObject> ret = new ArrayList<>();
        for (MObject elt : this.coreEvent.getAccessChanged()) {
            ret.add(elt);
        }
        return ret;
    }

    @objid ("980051e7-1108-4832-9da3-98df2d82519d")
    @Override
    public Collection<MObject> getCmsStatusChanged() {
        ArrayList<MObject> ret = new ArrayList<>();
        for (MObject elt : this.coreEvent.getCmsStatusChanged()) {
            ret.add(elt);
        }
        return ret;
    }

    @objid ("63e7bcc0-a80c-4471-9a7f-890f28f86334")
    @Override
    public Collection<MObject> getAuditStatusChanged() {
        ArrayList<MObject> ret = new ArrayList<>();
        for (MObject elt : this.coreEvent.getAuditStatusChanged()) {
            ret.add(elt);
        }
        return ret;
    }

    @objid ("8feb05fb-ddb9-48c3-b978-3f519978d583")
    @Override
    public Collection<MObject> getShellStateChanged() {
        ArrayList<MObject> ret = new ArrayList<>();
        for (MObject elt : this.coreEvent.getShellStateChanged()) {
            ret.add(elt);
        }
        return ret;
    }

    @objid ("5ccc1b6e-f4fb-431d-ae9e-4e9820030109")
    @Override
    public Collection<MObject> getStatusChanged() {
        ArrayList<MObject> ret = new ArrayList<>();
        for (MObject elt : this.coreEvent.getStatusChanged().keySet()) {
            ret.add(elt);
        }
        return ret;
    }

}
