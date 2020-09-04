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

package org.modelio.gproject.module;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.collections.TopologicalSorter.CyclicDependencyException;
import org.modelio.vbasic.collections.TopologicalSorter;
import org.modelio.vbasic.version.VersionedItem;

/**
 * Utility class to sort {@link GModule} and {@link IModuleHandle} by dependency analysis.
 * <p>
 * Use it to get the order the modules should be installed/started .
 * 
 * @author cmarin
 */
@objid ("4e989610-2900-4af3-9df9-43ae9423faaf")
public final class ModuleSorter {
    @objid ("67a5ffd0-fc90-4012-8e01-0ef279dbaf3c")
    private ModuleSorter() {
        // no instance
    }

    /**
     * Sort handles by dependencies, the first ones having no dependencies.
     * @param modules module handles to sort. This collection is not modified.
     * @return the sorted list.
     * @throws org.modelio.vbasic.collections.TopologicalSorter.CyclicDependencyException in case of cyclic dependency.
     */
    @objid ("44f96df5-3521-4ed9-bc6d-5a6718c15e1c")
    public static List<IModuleHandle> sortHandles(Collection<IModuleHandle> modules) throws CyclicDependencyException {
        return new HTopoSorter(modules).sort();
    }

    /**
     * Sort handles by dependencies, the first ones having no dependencies.
     * @param modules module to sort. This collection is not modified.
     * @return the sorted list.
     * @throws org.modelio.vbasic.collections.TopologicalSorter.CyclicDependencyException in case of cyclic dependency.
     */
    @objid ("1702582e-4ff2-4e17-ac97-025230430121")
    public static List<GModule> sortModules(Collection<GModule> modules) throws CyclicDependencyException {
        return new MTopoSorter(modules).sort();
    }

    @objid ("11b934c0-9ebe-49b6-a582-243d9b1efd14")
    private static class HTopoSorter extends TopologicalSorter<IModuleHandle> {
        @objid ("3c79aed3-6caa-4084-9691-1ebe71f5b24e")
        private Collection<IModuleHandle> modules;

        @objid ("a772f0b0-e48a-4731-ad86-50226ac0935d")
        public HTopoSorter(Collection<IModuleHandle> modules) {
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

    @objid ("e5fb74f3-da5e-4569-88e0-d92ceaff6859")
    private static class MTopoSorter extends TopologicalSorter<GModule> {
        @objid ("fd916a27-77ef-4181-a0b1-c38605d29a08")
        private Collection<GModule> modules;

        @objid ("1a5f1e08-a8bf-4992-9297-7b956009c8c6")
        public MTopoSorter(Collection<GModule> modules) {
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

}
