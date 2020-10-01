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

package org.modelio.platform.mda.infra.service.impl.controller.states.features;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.platform.mda.infra.service.impl.IRTModuleAccess;
import org.modelio.platform.mda.infra.service.impl.controller.load.ModuleLoader;
import org.modelio.vcore.session.api.IMetamodelSupport;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;

/**
 * Loads and register metamodel fragments as long the feature is active.
 */
@objid ("b6e8464c-a74a-460c-8a71-2a1178e411a0")
public class MmFragmentsFeature extends AbstractFeature {
    /**
     * @param module the module
     */
    @objid ("17b410cf-040e-46e9-a6a5-da4cc7eef8fd")
    public MmFragmentsFeature(IRTModuleAccess module) {
        super(module);
    }

    /**
     * Load metamodel fragments
     */
    @objid ("5133bf2d-2d2c-494d-9709-e764e248e746")
    @Override
    public void enable() throws ModuleException {
        // Instantiate metamodel fragments
        ModuleLoader loader = new ModuleLoader(this.module);
        
        List<ISmMetamodelFragment> mmFrags = loader.loadMetamodelFragments( this.module.getClassLoader());
        this.module.getMetamodelFragments().addAll(mmFrags);
    }

    /**
     * unload metamodel fragments
     */
    @objid ("16e0f9c7-3b64-4abd-8e7f-a521d491198c")
    @Override
    public void disable() {
        for (ISmMetamodelFragment mmf : this.module.getMetamodelFragments()) {
            IMetamodelSupport mmSupport = this.module.getGModule().getProject().getSession().getMetamodelSupport();
            mmSupport.removeMetamodelFragment(mmf);
        }
        
        this.module.getMetamodelFragments().clear();
    }

    @objid ("d8fbe16e-67fb-4e41-984a-5c938ef4d8a1")
    @Override
    public String toString() {
        return "Metamodel fragments loaded feature";
    }

}
