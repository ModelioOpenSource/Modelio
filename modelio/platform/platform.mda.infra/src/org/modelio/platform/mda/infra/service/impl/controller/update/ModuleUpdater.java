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
package org.modelio.platform.mda.infra.service.impl.controller.update;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.gproject.data.project.GProperties.Entry;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.parts.module.GModule;
import org.modelio.platform.mda.infra.plugin.MdaInfra;
import org.modelio.platform.mda.infra.service.IRTModuleController;
import org.modelio.platform.mda.infra.service.impl.IModuleRegistryAccess;
import org.modelio.platform.mda.infra.service.impl.IRTModuleAccess;
import org.modelio.platform.mda.infra.service.impl.controller.load.ModuleLoader;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.NullProgress;
import org.modelio.vbasic.version.Version;

/**
 * @author phv
 */
@objid ("eabbc60d-01a1-11e2-9fca-001ec947c8cc")
public class ModuleUpdater {
    @objid ("e153cfd1-9766-48a9-8940-bf89249e797b")
    private IGProject gProject;

    @objid ("a15ad58d-1916-4181-8c60-c59466bc7a43")
    private IModuleRegistryAccess moduleRegistry;

    /**
     * @param gProject the project
     * @param moduleRegistry the module registry
     */
    @objid ("a9653cb9-9432-43db-b7b3-5c63afda0241")
    public  ModuleUpdater(IGProject gProject, IModuleRegistryAccess moduleRegistry) {
        this.gProject = gProject;
        this.moduleRegistry = moduleRegistry;
        
    }

    /**
     * Update an already installed module
     * @param rtModuleToUpdate the existing module
     * @param rtModuleHandle the module handle to install
     * @param moduleUri the module file path
     * @throws ModuleException on failure.
     */
    @objid ("44326855-0324-11e2-9fca-001ec947c8cc")
    public void moduleUpdateInstall(IRTModuleAccess rtModuleToUpdate, IModuleHandle rtModuleHandle, URI moduleUri) throws ModuleException {
        GModule oldGModule = rtModuleToUpdate.getGModule();
        
        if (Objects.equals(oldGModule.getModuleHandle(), rtModuleHandle)) {
            // This is the same module handle instance, no need to restart it
            return;
        }
        
        MdaInfra.LOG.info("Updating '%s' %s module to '%s' %s", oldGModule.getName(), oldGModule.getVersion(), rtModuleHandle.getName(), rtModuleHandle.getVersion());
        MdaInfra.LOG.indent();
        
        try {
            // Stop and unload the module
            rtModuleToUpdate.getController().unload();
        
            // Get the previous values of the module parameters
            Map<String, String> oldParameters = new HashMap<>();
            for (Entry entry : oldGModule.getProperties().entries()) {
                oldParameters.put(entry.getName(), entry.getValue());
            }
        
            // Remove the old GModule first
            try {
                this.gProject.removeGPart(oldGModule);
            } catch (GPartException e1) {
                throw new ModuleException(oldGModule.getName() + " module could not be removed: " + e1.toString(), e1);
            }
            this.moduleRegistry.removeModule(rtModuleToUpdate);
        
            // Install the new module
            IModelioProgress monitor = new NullProgress(); // TODO use a progress monitor
            GModule updatedGModule = installInGProject(rtModuleHandle, moduleUri, monitor);
        
            // Set the current GModule
            rtModuleToUpdate.setGModule(updatedGModule);
            this.moduleRegistry.addModule(rtModuleToUpdate);
        
            // Call static method #install on module main class so that post-unzip/pre-loading operations can be done.
            new ModuleLoader(rtModuleToUpdate).callStaticMethodInstall();
        
            // Load the module
            rtModuleToUpdate.getController().load();
        
            Version oldVersion = oldGModule.getVersion();
            try {
                // Upgrade the module
                rtModuleToUpdate.getConfiguration().updateFrom(oldParameters);
                rtModuleToUpdate.getIModule().getLifeCycleHandler().upgrade(oldVersion, oldParameters);
        
                // Activate and start
                rtModuleToUpdate.getController().activate();
        
            } catch (ModuleException e) {
                MdaInfra.LOG.error(e);
                // Note as deactivated.
                rtModuleToUpdate.getController().deactivate();
                throw e;
            } catch (RuntimeException | LinkageError e) {
                MdaInfra.LOG.error(e);
                // Note as deactivated.
                // this.moduleService.deactivateModule(iModule);
                ModuleException e2 = new ModuleException(
                        MdaInfra.I18N.getMessage("ModuleInstaller.upgradeFailed",
                                oldGModule.getName(),
                                oldVersion,
                                updatedGModule.getVersion(),
                                e.toString()),
                        e);
        
                rtModuleToUpdate.getController().broken(e2);
        
                throw e2;
            }
        } finally {
            MdaInfra.LOG.dedent();
        }
        
    }

    @objid ("3c84a21f-4a94-4717-97a5-debd8bbfd170")
    private GModule installInGProject(IModuleHandle rtModuleHandle, URI moduleUri, IModelioProgress monitor) throws ModuleException {
        try {
            Path localArchiveDir = this.gProject.getPfs().getModuleBackupDir(rtModuleHandle.getName());
            Path localArchivePath = this.gProject.getPfs().getModuleBackupArchivePath(rtModuleHandle.getName(), rtModuleHandle.getVersion());
        
            // Clean the module data directory
            FileUtils.delete(localArchiveDir);
            Files.createDirectories(localArchiveDir);
        
            // Copy archive in data directory
            if (rtModuleHandle.getArchive() != null) {
                Files.copy(rtModuleHandle.getArchive(), localArchivePath, StandardCopyOption.REPLACE_EXISTING);
            }
        
            // Instantiate module
            final GProjectPartDescriptor fragmentDescriptor = new GProjectPartDescriptor(GProjectPartType.MODULE, rtModuleHandle.getName(), rtModuleHandle.getVersion(), DefinitionScope.LOCAL);
            fragmentDescriptor.setLocation(moduleUri);
            GModule module = new GModule(fragmentDescriptor);
            this.gProject.addGPart(module, true);
            return module;
        } catch (IOException e) {
            throw new ModuleException(FileUtils.getLocalizedMessage(e), e);
        } catch (GPartException e) {
            throw new ModuleException(e.getLocalizedMessage(), e);
        }
        
    }

    /**
     * Run the module update process from its already updated GModule.
     * @param rtModule the module to update
     * @param oldVersion the old module version
     * @throws ModuleException on failure
     */
    @objid ("9187234d-a4df-4754-925b-27ca692c9e4a")
    public void moduleUpdateFromGProject(IRTModuleAccess rtModule, Version oldVersion) throws ModuleException {
        // Call static method #install on module main class so that post-unzip/pre-loading operations can be done.
        new ModuleLoader(rtModule).callStaticMethodInstall();
        
        IRTModuleController controller = rtModule.getController();
        
        // Load the module
        controller.load();
        
        try {
            // Get the previous values of the module parameters
            Map<String, String> oldParameters = new HashMap<>();
            for (Entry entry : rtModule.getGModule().getProperties().entries()) {
                oldParameters.put(entry.getName(), entry.getValue());
            }
        
            // Upgrade the module
            rtModule.getConfiguration().updateFrom(oldParameters);
            rtModule.getIModule().getLifeCycleHandler().upgrade(oldVersion, oldParameters);
        
            // Activate and start
            controller.activate();
        
        } catch (ModuleException e) {
            MdaInfra.LOG.error(e);
            // Note as deactivated.
            controller.deactivate();
            throw e;
        } catch (RuntimeException | LinkageError e) {
            MdaInfra.LOG.error(e);
            // Note as deactivated.
            // this.moduleService.deactivateModule(iModule);
            ModuleException e2 = new ModuleException(
                    MdaInfra.I18N.getMessage("ModuleInstaller.upgradeFailed",
                            rtModule.getName(),
                            oldVersion,
                            rtModule.getVersion(),
                            e.toString()),
                    e);
        
            controller.broken(e2);
        
            throw e2;
        }
        
    }

}
