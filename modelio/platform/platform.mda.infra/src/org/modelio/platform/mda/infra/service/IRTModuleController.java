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

package org.modelio.platform.mda.infra.service;

import java.net.URI;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.platform.mda.infra.service.impl.IModuleRegistryAccess;
import org.modelio.vbasic.version.Version;

/**
 * Controller of a {@link IRTModule}.
 */
@objid ("ae4bfe37-a3fd-4e72-a987-d996ef32860d")
public interface IRTModuleController {
    /**
     * @return the controlled module.
     */
    @objid ("d8c51593-d4fa-48bb-872a-5b8d39ee862c")
    IRTModule getRTModule();

    /**
     * Installs, activate and start the module.
     * 
     * @throws org.modelio.api.module.lifecycle.ModuleException on failure.
     */
    @objid ("2395ad81-38e7-4871-8581-92823cfdc2b3")
    void install() throws ModuleException;

    /**
     * Stop the module.
     * 
     * @throws org.modelio.api.module.lifecycle.ModuleException on failure.
     */
    @objid ("9d8093dc-56cd-4e3d-ad03-c7cb9835435e")
    void stop() throws ModuleException;

    /**
     * Unload the IModule implementation.
     * 
     * @throws org.modelio.api.module.lifecycle.ModuleException on failure.
     */
    @objid ("74717681-d89d-4509-a214-5c3ed3dcf5ec")
    void unload() throws ModuleException;

    /**
     * Update the module.
     * 
     * @param rtModuleHandle the new module handle
     * @param moduleUri the new module URI
     * @throws org.modelio.api.module.lifecycle.ModuleException on failure.
     */
    @objid ("b38753ad-0b5f-49bd-98c2-a678760f879a")
    void updateTo(IModuleHandle rtModuleHandle, URI moduleUri) throws ModuleException;

    /**
     * Stop and unload the module before the project is closed.
     * <p>
     * The module will free its resources that are not freed with the project when closing.
     * 
     * @throws org.modelio.api.module.lifecycle.ModuleException on failure.
     */
    @objid ("275c2ca1-d1ef-418a-a9a1-9b87acfcf6a3")
    void close() throws ModuleException;

    /**
     * Load and start the module
     * 
     * @throws org.modelio.api.module.lifecycle.ModuleException on failure
     */
    @objid ("413dfead-9b37-4e0f-b6fb-906d76189601")
    void start() throws ModuleException;

    /**
     * Remove the module from the project
     * 
     * @param deleteAnnotations if true, delete all annotations typed by extensions provided by the module.
     * @throws org.modelio.api.module.lifecycle.ModuleException on failure.
     */
    @objid ("568dbdcc-4cd0-4688-9a08-11a7b7143428")
    void removeFromProject(boolean deleteAnnotations) throws ModuleException;

    /**
     * To set the module as broken.
     * 
     * @param e the breakage cause
     */
    @objid ("4b8a1458-d044-4503-92d5-46a66ebf1238")
    void broken(ModuleException e);

    /**
     * Load the module implementation.
     * 
     * @throws org.modelio.api.module.lifecycle.ModuleException on failure.
     */
    @objid ("c31481c0-4bd1-474c-b669-ca74e605d9b7")
    void load() throws ModuleException;

    /**
     * Activate and start the module.
     * 
     * @throws org.modelio.api.module.lifecycle.ModuleException on failure.
     */
    @objid ("c16ac946-f92b-4670-a39c-cfe382061df4")
    void activate() throws ModuleException;

    /**
     * Stop and deactivate an activated module.
     * 
     * @throws org.modelio.api.module.lifecycle.ModuleException on failure.
     */
    @objid ("dde216fc-e24c-48a6-9666-895b1e41fa65")
    void deactivate() throws ModuleException;

    /**
     * @return the modules registry.
     */
    @objid ("9185f322-d1b5-427a-9ab2-4af0e83a81f6")
    IModuleRegistryAccess getModuleRegistry();

    /**
     * Run the module update process from its already updated GModule.
     * 
     * @param oldVersion the old module version
     * @throws org.modelio.api.module.lifecycle.ModuleException on failure
     */
    @objid ("9d542358-9c57-4179-86ee-980f568e8364")
    void updateFromGModule(Version oldVersion) throws ModuleException;

}
