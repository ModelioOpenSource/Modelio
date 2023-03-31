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
package org.modelio.gproject.parts.module;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.collections.TopologicalSorter;
import org.modelio.vbasic.collections.TopologicalSorter.CyclicDependencyException;
import org.modelio.vbasic.version.VersionedItem;

/**
 * Utility class to sort {@link GModule} by dependency analysis.
 * <p>
 * Use it to get the order the modules should be installed/started.
 */
@objid ("e5fb74f3-da5e-4569-88e0-d92ceaff6859")
public class MTopoSorter extends TopologicalSorter<GModule> {
    @objid ("f19cdaba-163b-4ba4-9ab9-e3f9c147eff7")
    private Collection<GModule> modules;

    /**
     * Sort handles by dependencies, the first ones having no dependencies.
     * @param modules module to sort. This collection is not modified.
     * @return the sorted list.
     * @throws CyclicDependencyException in case of cyclic dependency.
     */
    @objid ("c1bc9923-1754-4f7b-ace9-74928341b052")
    public static List<GModule> sortModules(Collection<GModule> modules) throws CyclicDependencyException {
        return new MTopoSorter(modules).sort();
    }

    @objid ("1a5f1e08-a8bf-4992-9297-7b956009c8c6")
    private  MTopoSorter(Collection<GModule> modules) {
        this.modules = modules;
    }

    @objid ("b93418c7-656e-4ca6-8cde-6421d86b577f")
    @Override
    public Collection<GModule> getNodes() {
        return this.modules;
    }

    @objid ("aa2fe6b6-dc2b-4e60-b464-c83de3c7e132")
    @Override
    public Collection<GModule> getAdjacent(GModule node) {
        Collection<GModule> ret = new ArrayList<>();
        
        for (GModule m : this.modules) {
            if (dependsOn(node, m)) {
                ret.add(m);
            }
        }
        return ret;
    }

    @objid ("e6666f47-806d-4156-8604-de27a69979e2")
    static boolean dependsOn(GModule module1, GModule module2) {
        if (module2 == null) {
            return false;
        }
        
        for (VersionedItem<?> requiredRef : module1.getModuleHandle().getDependencies()) {
            if (module2.getName().equals(requiredRef.getName()) && !module2.getVersion().isOlderThan(requiredRef.getVersion())) {
                return true;
            }
        }
        
        for (VersionedItem<?> weakRef : module1.getModuleHandle().getWeakDependencies()) {
            if (module2.getName().equals(weakRef.getName()) && !module2.getVersion().isOlderThan(weakRef.getVersion())) {
                return true;
            }
        }
        return false;
    }

}
