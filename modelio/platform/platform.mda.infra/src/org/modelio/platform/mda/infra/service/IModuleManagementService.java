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
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.modelio.api.module.IPeerModule;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.module.GModule;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.platform.core.IModelioService;
import org.modelio.vbasic.progress.IModelioProgress;

/**
 * Modelio modules service interface, to work at {@link GModule} level.
 */
@objid ("895d3ad9-f1b7-11e1-af52-001ec947c8cc")
public interface IModuleManagementService extends IModelioService {
    /**
     * Activates and starts the given module. This method does NOT activate nor start modules required by the given module.
     * 
     * @param gModule the module to activate.
     * @throws org.modelio.api.module.lifecycle.ModuleException if an error occurred while trying to activate the module.
     */
    @objid ("2bb63f11-f1ed-11e1-af52-001ec947c8cc")
    void activateModule(GModule gModule) throws ModuleException;

    /**
     * Stops and deactivates the given module. Modules requiring the given module will be stopped first.
     * 
     * @param gModule the module to deactivate.
     * @throws org.modelio.api.module.lifecycle.ModuleException if an error occurred while trying to deactivate the module.
     */
    @objid ("2bb8a12c-f1ed-11e1-af52-001ec947c8cc")
    void deactivateModule(GModule gModule) throws ModuleException;

    /**
     * Returns the started IRTModule matching the passed GModule or <code>null</code> if none is found.
     * 
     * @param gModule the GModule to search a started IRTModule for.
     * @return the started IRTModule matching to the passed GModule or <code>null</code> if none is found.
     */
    @objid ("78c50f93-2f53-4afe-8323-e712eba3907c")
    IRTModule getIRTModule(GModule gModule);

    /**
     * Returns the ModuleRegistry which contains the list of all loaded IRTModule and the list of all started IRTModule.
     * 
     * @return the {@link IModuleRegistry}
     */
    @objid ("2bb8a12f-f1ed-11e1-af52-001ec947c8cc")
    IModuleRegistry getModuleRegistry();

    /**
     * Get the public services of a specific module.
     * <p>
     * This method needs the concrete interface of a module, to return the loaded instance of this peer module.
     * @param <T> the interface of the searched peer module.
     * 
     * @param metaclass the interface of the searched peer module.
     * @return the peer module regarding the given metaclass
     * @throws org.modelio.platform.mda.infra.service.UnknownModuleException when the required module is not found.
     */
    @objid ("2bb63efb-f1ed-11e1-af52-001ec947c8cc")
    <T extends IPeerModule> T getPeerModule(Class<T> metaclass) throws UnknownModuleException;

    /**
     * Ensure loading all RT modules of the given project.
     * <p>
     * Only {@link IRTModule} instances are initialized, the modules themselves are not loaded. This allows calling install(...) methods to update existing modules before they are started.
     * 
     * @param project the project to start all activated modules of.
     */
    @objid ("00105607-a423-4d20-b1e0-bcfa579892f4")
    void initRTModules(GProject project);

    /**
     * Installs, load and start the module contained in the given file in the given project. This method adds (or update) a module in the given GProject, then load and start the corresponding {@link IRTModule}.
     * 
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call done() on the given monitor. Accepts <i>null</i>, indicating that no progress should be reported and that the operation cannot
     * be cancelled.
     * @param gProject the project to install the module into.
     * @param moduleFilePath the path to the file of the module.
     * @throws org.modelio.api.module.lifecycle.ModuleException if an error occurred while trying to install the module.
     */
    @objid ("e86e3167-7fb8-4acf-86cc-8dde6f25e120")
    void installModule(final IModelioProgress monitor, GProject gProject, Path moduleFilePath) throws ModuleException;

    /**
     * Install a module already in a module store. Installs, load and start the module referenced by the handle in the given project. This method adds (or update) a module in the given GProject, then load and start the corresponding {@link IRTModule}.
     * 
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call done() on the given monitor. Accepts <i>null</i>, indicating that no progress should be reported and that the operation cannot
     * be cancelled.
     * @param gProject the project to deploy the module to
     * @param rtModuleHandle the module handle
     * @param origUri the URI where an archive of the module may be found
     * @throws org.modelio.api.module.lifecycle.ModuleException on failure
     */
    @objid ("40acb2b5-218c-4ab6-a3da-3b649f633d18")
    void installModule(final IModelioProgress monitor, GProject gProject, IModuleHandle rtModuleHandle, URI origUri) throws ModuleException;

    /**
     * Stops, unload and removes a module.
     * 
     * @param gModule the module to remove.
     * @throws org.modelio.api.module.lifecycle.ModuleException if an error occurred while trying to remove the module.
     */
    @objid ("b61165c6-0d64-11e2-ae8f-002564c97630")
    void removeModule(GModule gModule) throws ModuleException;

    /**
     * Stops, unload and removes a module.
     * 
     * @param gModule the module to remove.
     * @param deleteAnnotations if true, delete all annotations typed by extensions provided by the module.
     * @throws org.modelio.api.module.lifecycle.ModuleException if an error occurred while trying to remove the module.
     */
    @objid ("c7c46c59-a0e8-4d1a-a71c-72c366e07f07")
    void removeModule(GModule gModule, boolean deleteAnnotations) throws ModuleException;

    /**
     * Load and Start all activated non started modules of the given project.
     * 
     * @param project the project to start all activated modules of.
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call done() on the given monitor. Accepts <i>null</i>, indicating that no progress should be reported and that the operation cannot
     * be cancelled.
     */
    @objid ("2bb63f02-f1ed-11e1-af52-001ec947c8cc")
    void startAllModules(GProject project, final IProgressMonitor monitor);

    /**
     * Stop all started modules and unloads all loaded modules of the given project.
     * 
     * @param project the project to stop all modules of.
     */
    @objid ("2bb63f07-f1ed-11e1-af52-001ec947c8cc")
    void stopAllModules(GProject project);

}
