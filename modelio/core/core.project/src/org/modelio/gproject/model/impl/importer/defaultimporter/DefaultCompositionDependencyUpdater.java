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
package org.modelio.gproject.model.impl.importer.defaultimporter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.model.impl.importer.core.BrokenDepReport;
import org.modelio.gproject.model.impl.importer.core.IBrokenDependencyHandler;
import org.modelio.gproject.model.impl.importer.core.IDependencyUpdater;
import org.modelio.gproject.model.impl.importer.core.IObjectFinder;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * {@link IDependencyUpdater} implementation for composition dependencies.
 */
@objid ("00811d2a-d3aa-108f-8d81-001ec947cd2a")
public class DefaultCompositionDependencyUpdater implements IDependencyUpdater {
    @objid ("bb886551-4467-11e2-b513-002564c97630")
    private final ICoreSession localSession;

    @objid ("009231b4-e7d6-1090-8d81-001ec947cd2a")
    private final IBrokenDependencyHandler brokenDependencyHandler;

    @objid ("00924442-e7d6-1090-8d81-001ec947cd2a")
    private final IObjectFinder objectFinder;

    /**
     * @param brokenDependencyHandler the broken dependencies handler
     * @param objectFinder the object finder
     * @param localSession the local session
     */
    @objid ("00927778-e7d6-1090-8d81-001ec947cd2a")
    public  DefaultCompositionDependencyUpdater(IBrokenDependencyHandler brokenDependencyHandler, IObjectFinder objectFinder, ICoreSession localSession) {
        super();
        this.brokenDependencyHandler = brokenDependencyHandler;
        this.objectFinder = objectFinder;
        this.localSession = localSession;
        
    }

    @objid ("0081dc4c-d3aa-108f-8d81-001ec947cd2a")
    @Override
    public List<SmObjectImpl> execute(final SmObjectImpl refObject, SmDependency smDep, SmObjectImpl localObject) {
        SmDependency localDep = this.objectFinder.getSameDependency(smDep);
        if (localDep != null) {
            // Get the dep values in the reference model
            List<MObject> refValues = refObject.mGet(smDep);
        
            // Find the equivalent values in the local model
            List<SmObjectImpl> equivalentLocalValues = getEquivalentLocalValues(refValues);
        
            // If some values could not be found locally, the dependency will be 'broken' after the import and will require some
            // repairing...
            if (equivalentLocalValues.size() != refValues.size()) {
                // Publish the broken dep values
                fireBrokenDepValues(refObject, refValues, smDep, localObject, equivalentLocalValues);
            }
        
            // Update the local dep values according to the reference values
            return updateDependency(localObject, smDep, localDep, equivalentLocalValues, refValues);
        } else {
            return Collections.emptyList();
        }
        
    }

    @objid ("00822756-d3aa-108f-8d81-001ec947cd2a")
    protected List<SmObjectImpl> getEquivalentLocalValues(List<MObject> refValues) {
        List<SmObjectImpl> equivalentLocalValues = new ArrayList<>(refValues.size());
        
        // Get new dependencies values and find them in the destination model
        for (MObject refDepVal : refValues) {
            if (refDepVal != null) {
                SmObjectImpl sameObj = this.objectFinder.getSameObject((SmObjectImpl) refDepVal);
                if (sameObj != null && !sameObj.isDeleted()) {
                    equivalentLocalValues.add(sameObj);
                }
            } else {
                throw new RuntimeException("Error_Null_element_in_dependency");
            }
        }
        return equivalentLocalValues;
    }

    /**
     * Update a dependency
     * @param localObject the local object to modify
     * @param refDep the dependency in the reference model
     * @param localDep the dependency in the local model
     * @param equivalentLocalValues the new dependency values found in the local model
     * @param refValues the new dependency values in the source model
     * @return a list of orphan elements.
     */
    @objid ("00821400-d3aa-108f-8d81-001ec947cd2a")
    protected List<SmObjectImpl> updateDependency(SmObjectImpl localObject, SmDependency refDep, SmDependency localDep, List<SmObjectImpl> equivalentLocalValues, List<MObject> refValues) {
        // Optimization: update values only if old and new dependency values are really different
        // without this optimization, all the current local values of the dep would be first marked for garbage and later
        // re-attached, wasting a lot of processing.
        // The comparison takes care of the ordering if needed.
        List<MObject> localValues = localObject.mGet(localDep);
        if (!isSetEqual(refValues, localValues, refDep.isOrdered())) {
            // Remove all values that should be kept in the dep
            localValues.removeAll(equivalentLocalValues);
        
            // Collect values that are going to become orphan...
            List<SmObjectImpl> orphans = new ArrayList(localValues);
        
            // Clear all remaining values
            localValues.clear();
        
            // Put back values from the reference model
            for (SmObjectImpl obj : equivalentLocalValues) {
                localObject.appendDepVal(localDep, obj);
            }
        
            return orphans;
        }
        return Collections.emptyList();
    }

    @objid ("009333d4-e7d6-1090-8d81-001ec947cd2a")
    private void fireBrokenDepValues(SmObjectImpl refObject, List<MObject> refValues, SmDependency smDep, SmObjectImpl localObject, List<SmObjectImpl> equivalentLocalValues) {
        for (MObject refVal : refValues) {
            if (!equivalentLocalValues.contains(refVal)) {
                this.brokenDependencyHandler.handleBrokenDep(new BrokenDepReport(refObject, localObject, smDep,
                        (SmObjectImpl) refVal));
        
            }
        }
        
    }

    @objid ("00818828-d3aa-108f-8d81-001ec947cd2a")
    private boolean isSetEqual(final List<?> a, final List<?> b, boolean ordered) {
        if (a.size() != b.size()) {
            return false;
        }
        
        if (!ordered) {
            // Ordering does not matter
            for (Object object : a) {
                if (!b.contains(object)) {
                    return false;
                }
            }
        } else {
            // Ordering DOES matter
            for (int i = 0; i < a.size(); i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

}
