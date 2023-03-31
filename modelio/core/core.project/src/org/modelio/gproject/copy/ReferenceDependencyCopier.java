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
package org.modelio.gproject.copy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.importer.core.IDependencyUpdater;
import org.modelio.gproject.importer.core.IObjectFinder;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("01f419b4-0000-3329-0000-000000000000")
class ReferenceDependencyCopier implements IDependencyUpdater {
    @objid ("bb6bd4d0-4467-11e2-b513-002564c97630")
    private final ICoreSession localSession;

    @objid ("000d55f2-5247-1091-8d81-001ec947cd2a")
    private final IObjectFinder objectFinder;

    @objid ("01f419b4-0000-3331-0000-000000000000")
    public  ReferenceDependencyCopier(ICoreSession localSession, IObjectFinder objectFinder) {
        this.localSession = localSession;
        this.objectFinder = objectFinder;
        
    }

    @objid ("01f419b4-0000-3341-0000-000000000000")
    @Override
    public List<SmObjectImpl> execute(final SmObjectImpl refObject, SmDependency smDep, SmObjectImpl localObject) {
        SmDependency localDep = this.objectFinder.getSameDependency(smDep);
        
        if (localDep != null) {
            // Get the dep values in the reference model
            List<MObject> refValues = refObject.mGet(smDep);
        
            // Find the equivalent values in the local model
            List<SmObjectImpl> equivalentLocalValues = getEquivalentLocalValues(refValues);
        
            // Do the update according to the dependency
            updateDependency(localObject, localDep, equivalentLocalValues);
        }
        return Collections.emptyList();
    }

    @objid ("01f419b4-0000-3355-0000-000000000000")
    protected void updateDependency(SmObjectImpl localObject, SmDependency localDep, List<SmObjectImpl> equivalentLocalValues) {
        for (SmObjectImpl obj : equivalentLocalValues) {
            localObject.appendDepVal(localDep, obj);
        }
        
    }

    @objid ("000de5f8-5247-1091-8d81-001ec947cd2a")
    private List<SmObjectImpl> getEquivalentLocalValues(List<MObject> refValues) {
        List<SmObjectImpl> equivalentLocalValues = new ArrayList<>(refValues.size());
        
        // Get new dependencies values and find them in the destination model
        for (MObject refDepVal : refValues) {
            if (refDepVal != null) {
                SmObjectImpl sameObj = this.objectFinder.getSameObject((SmObjectImpl) refDepVal);
                if (sameObj != null && !sameObj.isDeleted()) {
                    equivalentLocalValues.add(sameObj);
                }
            }
        }
        return equivalentLocalValues;
    }

}
