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
package org.modelio.gproject.module;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.collections.TopologicalSorter;
import org.modelio.vbasic.collections.TopologicalSorter.CyclicDependencyException;
import org.modelio.vbasic.version.VersionedItem;

/**
 * Utility class to sort {@link IModuleHandle} by dependency analysis.
 * <p>
 * Use it to get the order the modules should be installed/started.
 */
@objid ("11b934c0-9ebe-49b6-a582-243d9b1efd14")
public class HTopoSorter extends TopologicalSorter<IModuleHandle> {
    @objid ("3c79aed3-6caa-4084-9691-1ebe71f5b24e")
    private Collection<IModuleHandle> modules;

    /**
     * Sort handles by dependencies, the first ones having no dependencies.
     * @param modules module handles to sort. This collection is not modified.
     * @return the sorted list.
     * @throws CyclicDependencyException in case of cyclic dependency.
     */
    @objid ("326c4040-1a18-4f33-a6ee-86dcdc209316")
    public static List<IModuleHandle> sortHandles(Collection<IModuleHandle> modules) throws CyclicDependencyException {
        return new HTopoSorter(modules).sort();
    }

    @objid ("a772f0b0-e48a-4731-ad86-50226ac0935d")
    private  HTopoSorter(Collection<IModuleHandle> modules) {
        this.modules = modules;
    }

    @objid ("e74e8a5f-3b50-46dd-93a5-efff3361567e")
    @Override
    public Collection<IModuleHandle> getNodes() {
        return this.modules;
    }

    @objid ("11496091-4c2c-4611-8aab-97e9a54d636c")
    @Override
    public Collection<IModuleHandle> getAdjacent(IModuleHandle node) {
        Collection<IModuleHandle> ret = new ArrayList<>();
        
        for (IModuleHandle m : this.modules) {
            if (dependsOn(node, m)) {
                ret.add(m);
            }
        }
        return ret;
    }

    @objid ("50aaa348-df86-4792-a5f0-f7ec1e34671e")
    static boolean dependsOn(IModuleHandle module1, IModuleHandle module2) {
        if (module2 == null) {
            return false;
        }
        
        for (VersionedItem<?> requiredRef : module1.getDependencies()) {
            if (module2.getName().equals(requiredRef.getName()) && !module2.getVersion().isOlderThan(requiredRef.getVersion())) {
                return true;
            }
        }
        
        for (VersionedItem<?> weakRef : module1.getWeakDependencies()) {
            if (module2.getName().equals(weakRef.getName()) && !module2.getVersion().isOlderThan(weakRef.getVersion())) {
                return true;
            }
        }
        return false;
    }

}
