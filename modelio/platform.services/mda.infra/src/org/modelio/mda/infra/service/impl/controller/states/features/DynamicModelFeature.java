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

package org.modelio.mda.infra.service.impl.controller.states.features;

import java.nio.file.Files;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.mda.infra.service.impl.IRTModuleAccess;
import org.modelio.mda.infra.service.impl.controller.load.DynamicModelImporter;

/**
 * Reads the module.xml file and deserialize its content (basically: commands and diagrams customization)
 */
@objid ("3503cdfd-ac4a-45a3-93ea-c87037b63246")
public class DynamicModelFeature extends AbstractFeature {
    @objid ("ed7ccc6a-5200-4ba0-b293-7747c511dc15")
    private final DynamicModelImporter modelLoader;

    /**
     * @param module the module
     */
    @objid ("97662693-d1e4-44c6-95c0-ba9de021df28")
    public DynamicModelFeature(IRTModuleAccess module) {
        super(module);
        this.modelLoader = new DynamicModelImporter(module);
    }

    @objid ("3d04c142-d463-4d35-acc1-e9a2eac6c854")
    @Override
    public void enable() throws ModuleException {
        loadDynamicModel();
    }

    @objid ("0b824a67-8393-49bc-a212-14ac6a25f4ac")
    @Override
    public void disable() {
        // noop
        this.module.resetDynamicModel();
    }

    @objid ("b4ff7337-1fcc-461d-b692-070fa01b54fe")
    private void loadDynamicModel() throws ModuleException {
        IModuleHandle rtModuleHandle = this.module.getGModule().getModuleHandle();
        if (!this.module.getName().equals("LocalModule") ) {
            Path dynamicModelPath = rtModuleHandle.getDynamicModelPath();
            if (Files.exists(dynamicModelPath) && Files.isRegularFile(dynamicModelPath)) {
                this.modelLoader.loadDynamicModel(dynamicModelPath);
            }
        }
    }

    @objid ("958da678-df66-46ec-96d4-cd6aa25aba98")
    @Override
    public String toString() {
        return "Dynamic model feature";
    }

}
