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

package org.modelio.platform.mda.infra.service.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.module.GModule;
import org.modelio.platform.mda.infra.service.IModuleRegistry;
import org.modelio.platform.mda.infra.service.IRTModule;

/**
 * Access to modify the Module registry.
 */
@objid ("443bea02-4ae8-46fa-9919-b9d33eb60e93")
public interface IModuleRegistryAccess extends IModuleRegistry {
    /**
     * Adds a module to the list of the projects modules
     * 
     * @param module the module.
     */
    @objid ("1e6375ee-edc3-11e1-88ee-001ec947c8cc")
    void addModule(IRTModule module);

    /**
     * Adds a module to the list of started modules
     * 
     * @param module the started module.
     */
    @objid ("1e6375e8-edc3-11e1-88ee-001ec947c8cc")
    void addStartedModule(IRTModule module);

    /**
     * Remove a module from the list of started modules.
     * 
     * @param module the stopped module.
     */
    @objid ("1e6375eb-edc3-11e1-88ee-001ec947c8cc")
    void removeStartedModule(IRTModule module);

    /**
     * Remove a module from the list of loaded modules.
     * 
     * @param module the unloaded module.
     */
    @objid ("1e6375f5-edc3-11e1-88ee-001ec947c8cc")
    void removeModule(IRTModule module);

    /**
     * Get the {@link IRTModule} corresponding to the given
     * {@link GModule}.
     * <p>
     * Creates a new IRTModule if none is found.
     * 
     * @param model the module model.
     * @return the matching <code>IRTModule</code>.
     */
    @objid ("0d308e9c-e633-4505-bb8c-a3c8f9c5794b")
    IRTModule loadRTModule(GModule model);

    /**
     * Makes the registry unusable and release resources.
     */
    @objid ("06971dbd-5b21-4076-950e-c1e4ff743ec0")
    void dispose();

    /**
     * Set the registry project name for debugging.
     * 
     * @param name the project name
     */
    @objid ("8dea9875-0273-4e2d-81f1-66b880c3ddba")
    void setProjectName(String name);

}
