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

package org.modelio.app.core.metamodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;
import org.modelio.app.core.plugin.AppCore;
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
     * Load all metamodel fragments declared by plugin extensions.
     * @return all metamodel fragments.
     */
    @objid ("9c66e6d1-ccd3-497c-8591-f1fbdc8824fd")
    public static Collection<IGMetamodelExtension> loadMetamodelExtensions() {
        IExtensionRegistry registry = RegistryFactory.getRegistry();
        IConfigurationElement[] configurationElements = registry.getConfigurationElementsFor(MMFRAGMENT_EXTENSION_POINT_ID);
        
        
        Map<MMetamodelFragment, IGMetamodelExtension> map = new HashMap<>(configurationElements.length);
        
        for (IConfigurationElement node : configurationElements) {
            try {
                IGMetamodelExtension mmf = (IGMetamodelExtension) node.createExecutableExtension("class");
                map.put(mmf.getMmFragment(), mmf);
            } catch (CoreException | ClassCastException e) {
                AppCore.LOG.error("Couldn't instantiate '%s' metamodel fragment class from '%s' plugin:", node.getAttribute("class"), node.getContributor().getName());
                AppCore.LOG.error(e);
            }
        }
        
        
        if (map.isEmpty()) {
            throw new IllegalStateException("No metamodel fragment provided by any plugin. Check Modelio packaging.");
        }
        
        
        MMFragmentTopologicalSorter<MMetamodelFragment> sorter = new MMFragmentTopologicalSorter<>(map.keySet());
        
        List<MMetamodelFragment> sortedmm;
        try {
            sortedmm = sorter.sort();
        } catch (CyclicDependencyException e) {
            throw new IllegalStateException(e.getLocalizedMessage(), e);
        }
        
        Collection<IGMetamodelExtension> ret  = new ArrayList<>(sortedmm.size());
        
        for (MMetamodelFragment mmf : sortedmm) {
            ret.add(map.get(mmf));
        }
        return ret;
    }

}
