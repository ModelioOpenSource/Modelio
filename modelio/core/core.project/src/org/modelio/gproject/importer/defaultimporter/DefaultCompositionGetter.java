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
package org.modelio.gproject.importer.defaultimporter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.importer.core.ICompositionGetter;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Default implementation of {@link ICompositionGetter}.
 */
@objid ("007f1552-d3aa-108f-8d81-001ec947cd2a")
public class DefaultCompositionGetter implements ICompositionGetter {
    @objid ("00756598-5541-1091-8d81-001ec947cd2a")
    @Override
    public Collection<SmObjectImpl> getAllChildren(final Collection<SmObjectImpl> roots) {
        return getAllChildren(roots, null);
    }

    @objid ("0074d0b0-5541-1091-8d81-001ec947cd2a")
    @Override
    public List<SmObjectImpl> getChildren(SmObjectImpl anObject) {
        ArrayList<SmObjectImpl> results = new ArrayList<>();
        
        for (SmDependency dep : anObject.getClassOf().getAllComponentAndSharedDepDef()) {
            Object depVal = anObject.getDepVal(dep);
            if (dep.isMultiple()) {
                @SuppressWarnings("unchecked")
                final List<SmObjectImpl> smDepVal = (List<SmObjectImpl>) depVal;
                results.addAll(smDepVal);
            } else {
                if (depVal != null) {
                    results.add((SmObjectImpl) depVal);
                }
            }
        }
        return results;
    }

    @objid ("00750d32-5541-1091-8d81-001ec947cd2a")
    protected final Collection<SmObjectImpl> getAllChildren(final Collection<SmObjectImpl> roots, final StopFilter filter) {
        Set<SmObjectImpl> results = new HashSet<>(roots);
        
        // initialize a current roots list from the passed root elements
        List<SmObjectImpl> nextRoots = new ArrayList<>(roots);
        
        List<SmObjectImpl> tmpList = new ArrayList<>();
        
        // Loop until there is no root nodes
        while (!nextRoots.isEmpty()) {
        
            // Get direct children of current roots into 'impl.list'
            for (SmObjectImpl root : nextRoots) {
                // add children to 'tmpList'
                tmpList.addAll(getChildren(root));
            }
        
            // Clear the current roots list in order to rebuild it for next iteration
            nextRoots.clear();
        
            // Add each new child to the result set and to the next roots list
            for (SmObjectImpl child : tmpList) {
                if (!results.contains(child) && (filter == null || filter.accept(child))) {
                    // Add the child to the set
                    results.add(child);
        
                    // Add the child to the next roots list
                    nextRoots.add(child);
                }
            }
            tmpList.clear();
        }
        return results;
    }

    /**
     * Filter to use to stop composition tree walking.
     */
    @objid ("007667c2-5541-1091-8d81-001ec947cd2a")
    public interface StopFilter {
        /**
         * @param anObject the current object
         * @return <i>true</i> to continue, <i>false</i> to stop the composition tree iteration.
         */
        @objid ("00767078-5541-1091-8d81-001ec947cd2a")
        boolean accept(Object anObject);
}
    

}
