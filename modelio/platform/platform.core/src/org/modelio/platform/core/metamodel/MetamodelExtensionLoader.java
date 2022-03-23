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
package org.modelio.platform.core.metamodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;
import org.modelio.platform.core.plugin.AppCore;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.vbasic.collections.TopologicalSorter.CyclicDependencyException;
import org.modelio.vcore.model.spi.IGMetamodelExtension;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.meta.MMFragmentTopologicalSorter;

/**
 * Helper class to load metamodel fragments provided by plugins.
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("b1ad5bc7-e518-4890-a4ca-594be7df51a2")
public class MetamodelExtensionLoader {
    @objid ("e91a2757-62f9-4bf6-b141-ccd294e635fe")
    private static final String MMFRAGMENT_EXTENSION_POINT_ID = "org.modelio.core.metamodelprovider";

    /**
     * Load all metamodel fragments declared by plugin extensions, topologically sorted to avoid dependency problems.
     * @return all metamodel fragments.
     */
    @objid ("9c66e6d1-ccd3-497c-8591-f1fbdc8824fd")
    public static Collection<IGMetamodelExtension> loadAllMetamodelExtensions() {
        final Map<MMetamodelFragment, IGMetamodelExtension> map = new HashMap<>();
        
        for (final IConfigurationElement node : RegistryFactory.getRegistry().getConfigurationElementsFor(MMFRAGMENT_EXTENSION_POINT_ID)) {
            if (node.getName().equals("metamodel")) {
                try {
                    final IGMetamodelExtension mmf = (IGMetamodelExtension) node.createExecutableExtension("class");
                    map.put(mmf.getMmFragment(), mmf);
                } catch (CoreException | ClassCastException e) {
                    AppCore.LOG.error("Couldn't instantiate '%s' metamodel fragment class from '%s' plugin:", node.getAttribute("class"), node.getContributor().getName());
                    AppCore.LOG.error(e);
                }
            }
        }
        
        if (map.isEmpty()) {
            throw new IllegalStateException("No metamodel fragment provided by any plugin. Check Modelio packaging.");
        }
        
        final MMFragmentTopologicalSorter<MMetamodelFragment> sorter = new MMFragmentTopologicalSorter<>(map.keySet());
        
        List<MMetamodelFragment> sortedmm;
        try {
            sortedmm = sorter.sort();
        } catch (final CyclicDependencyException e) {
            throw new IllegalStateException(e.getLocalizedMessage(), e);
        }
        
        final Collection<IGMetamodelExtension> ret  = new ArrayList<>(sortedmm.size());
        
        for (final MMetamodelFragment mmf : sortedmm) {
            ret.add(map.get(mmf));
        }
        return ret;
    }

    /**
     * Get names of all "active" metamodel fragments declared by plugin extensions.
     * @return names of the active metamodel fragments.
     */
    @objid ("b464f17d-b354-4747-8030-62a0eaa5e2c4")
    public static Collection<String> getActiveMetamodelExtensions() {
        final Collection<String> ret = new ArrayList<>();
        
        for (final IConfigurationElement node : new ExtensionPointContributionManager(MMFRAGMENT_EXTENSION_POINT_ID).getExtensions("metamodel")) {
            try {
                final IGMetamodelExtension mmf = (IGMetamodelExtension) node.createExecutableExtension("class");
                ret.add(mmf.getMmFragment().getName());
            } catch (CoreException | ClassCastException e) {
                AppCore.LOG.error("Couldn't instantiate '%s' metamodel fragment class from '%s' plugin:", node.getAttribute("class"), node.getContributor().getName());
                AppCore.LOG.error(e);
            }
        }
        return ret;
    }

}
