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

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.modelio.api.module.IPeerModule;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.context.configuration.IConfigParamValidator;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.api.module.context.configuration.IModuleConfigurationListener;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.parts.GPartFactory;
import org.modelio.gproject.parts.module.GModule;
import org.modelio.gproject.parts.module.MTopoSorter;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.platform.core.IModelioEventService;
import org.modelio.platform.core.IModelioService;
import org.modelio.platform.core.ModelioEnv;
import org.modelio.platform.core.events.ModelioEvent;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.mda.infra.IMdaResourceProvider;
import org.modelio.platform.mda.infra.MdaResourceService;
import org.modelio.platform.mda.infra.plugin.MdaInfra;
import org.modelio.platform.mda.infra.service.AbstractIRTModuleListener;
import org.modelio.platform.mda.infra.service.IModuleManagementService;
import org.modelio.platform.mda.infra.service.IModuleRegistry;
import org.modelio.platform.mda.infra.service.IModuleService;
import org.modelio.platform.mda.infra.service.IRTModule;
import org.modelio.platform.mda.infra.service.IRTModule.ModuleRuntimeState;
import org.modelio.platform.mda.infra.service.IRTModuleListener;
import org.modelio.platform.mda.infra.service.ModuleContextFactory;
import org.modelio.platform.mda.infra.service.UnknownModuleException;
import org.modelio.platform.mda.infra.service.impl.expert.MdaExpert;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.collections.TopologicalSorter.CyclicDependencyException;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.net.UriPathAccess;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;

/**
 * Module services.
 */
@objid ("8dd0dee5-f1b7-11e1-af52-001ec947c8cc")
@Creatable
public class ModuleManagementService implements IModuleManagementService, IModuleService {
    @objid ("f534248f-95c4-437e-851b-a2d6cb80fed6")
    private static final String PROP_SELECT_DONE = "isSelectDone";

    @objid ("ca5e85e3-0c73-11e2-a703-001ec947c8cc")
    @Inject
    @Optional
    private ModelioEnv env;

    @objid ("0996ff85-73ef-4e69-8cac-750f8353272c")
    private ManagerModuleListener eventFirer;

    @objid ("877d9db1-0e30-11e2-baba-001ec947c8cc")
    @Inject
    @Optional
    private IMModelServices mModelServices;

    @objid ("03876997-dd58-4b67-a04d-75d4323412f8")
    private MdaExpert mdaExpert;

    @objid ("2a611be9-f348-11e1-9458-001ec947c8cc")
    @Inject
    @Optional
    private IModelioEventService modelioEventService;

    @objid ("495b93d1-f2b5-11e1-af52-001ec947c8cc")
    private IModuleRegistryAccess moduleRegistry = new ModuleRegistry();

    @objid ("2c0a9a6a-f16b-4096-a0b8-9865cd590887")
    private final MdaResourceService mdaResourceProviderRegistry = new MdaResourceService();

    /**
     * Activates and starts the given module. This method does NOT activate nor start modules required by the given module.
     * @param module the module to activate.
     * @throws ModuleException if an error occurred while trying to activate the module.
     */
    @objid ("2bb63eee-f1ed-11e1-af52-001ec947c8cc")
    @Override
    public void activateModule(GModule gModule) throws ModuleException {
        IRTModule rtModule = getIRTModule(gModule);
        rtModule.getController().activate();
        
    }

    /**
     * Stops and deactivates the given module. Modules requiring the given module will be stopped first.
     * @param module the module to deactivate.
     * @throws ModuleException if an error occurred while trying to deactivate the module.
     */
    @objid ("2bb63ef2-f1ed-11e1-af52-001ec947c8cc")
    @Override
    public void deactivateModule(GModule gModule) throws ModuleException {
        IRTModule rtModule = getIRTModule(gModule);
        
        rtModule.getController().deactivate();
        
    }

    /**
     * Returns the started IRTModule matching the passed GModule or <code>null</code> if none is found.
     * @param gModule the GModule to search a started IRTModule for.
     * @return the started IRTModule matching to the passed GModule or <code>null</code> if none is found.
     */
    @objid ("4d2b74de-1390-4a06-beae-8fe3d8508d04")
    @Override
    public IRTModule getIRTModule(GModule gModule) {
        return getModuleRegistry().getModule(gModule);
    }

    @objid ("22d08283-2c16-44f6-96ee-92ada138cf68")
    @Override
    public IMdaExpert getMdaExpert() {
        return this.mdaExpert;
    }

    /**
     * Returns the ModuleRegistry which contains the list of all loaded IRTModule and the list of all started IRTModule.
     * @return the {@link IModuleRegistry}
     */
    @objid ("2bb63ef6-f1ed-11e1-af52-001ec947c8cc")
    @Override
    public IModuleRegistry getModuleRegistry() {
        return this.moduleRegistry;
    }

    @objid ("008f6d9e-96dd-103f-87fd-001ec947cd2a")
    @Override
    public String getName() {
        return "ModuleManagementService";
    }

    @objid ("2bb63ed3-f1ed-11e1-af52-001ec947c8cc")
    @Override
    public <T extends IPeerModule> T getPeerModule(Class<T> metaclass) throws UnknownModuleException {
        try {
            for (IRTModule module : this.moduleRegistry.getStartedModules()) {
                IPeerModule peerModule = module.getPeerModule();
                String metaclassInterfaceName = metaclass.getCanonicalName();
                Class<? extends IPeerModule> peerModuleClass = peerModule.getClass();
                for (Class<?> interf : peerModuleClass.getInterfaces()) {
                    String interfName = interf.getCanonicalName();
                    if (interfName.equals(metaclassInterfaceName)) {
                        return metaclass.cast(peerModule);
                    }
                }
            }
        } catch (ClassCastException e) {
            MdaInfra.LOG.error(e);
        }
        throw new UnknownModuleException(
                String.format("The '%s' class is not kwown as a Java module.", metaclass.getName()));
        
    }

    @objid ("54c108e5-d963-4068-83b5-d700733746e6")
    @Override
    public IMdaResourceProvider getMdaResourceProvider() {
        return this.mdaResourceProviderRegistry;
    }

    @objid ("d252ff00-c1bf-4e73-816c-811f2ec3e593")
    @Override
    public List<IRTModule> getStartedModules() {
        return new ArrayList<>(this.moduleRegistry.getStartedModules());
    }

    /**
     * Ensure loading all RT modules of the given project.
     * <p>
     * Only {@link IRTModule} instances are initialized, the modules themselves are not loaded. This allows calling install(...) methods to update existing modules before they are started.
     * @param project the project to start all activated modules of.
     */
    @objid ("897a9375-b523-40b1-af56-2927f71ab07c")
    @Override
    public void initRTModules(IGProject project) {
        List<GModule> gModules = project.getParts(GModule.class);
        
        // Sort the module list by start order
        List<GModule> sortedModules = getSortedModules(gModules);
        
        // Load sorted modules list in order
        for (GModule gModule : sortedModules) {
            getRTModule(gModule);
        }
        
    }

    /**
     * Installs, load and start the module contained in the given file in the given project. This method adds (or update) a module in the given GProject, then load and start the corresponding {@link IRTModule}.
     * @param gProject the project to install the module into.
     * @param moduleUri the path to the file of the module.
     * @param authData authentication data to access the URI. May be <i>null</i>.
     * @throws ModuleException if an error occurred while trying to install the module.
     * @deprecated not used in 3.8. to be deleted if still not used in >3.8
     */
    @objid ("2bb63ee9-f1ed-11e1-af52-001ec947c8cc")
    @Deprecated
    private void __installModule(IGProject gProject, URI moduleUri, IAuthData authData) throws ModuleException {
        try (UriPathAccess access = new UriPathAccess(moduleUri, authData)) {
            Path archivePath = access.getPath();
        
            installModule(null, gProject, archivePath, moduleUri);
        } catch (IOException e) {
            throw new ModuleException(FileUtils.getLocalizedMessage(e), e);
        }
        
    }

    /**
     * Stops, unload and removes a module.
     * @param gModule the module to remove.
     * @throws ModuleException if an error occurred while trying to remove the module.
     */
    @objid ("b60fdf1c-0d64-11e2-ae8f-002564c97630")
    @Override
    public void removeModule(GModule gModule) throws ModuleException {
        removeModule(gModule, true);
    }

    /**
     * Stops, unload and removes a module.
     * @param gModule the module to remove.
     * @param deleteAnnotations if true, delete all annotations typed by extensions provided by the module.
     * @throws ModuleException if an error occurred while trying to remove the module.
     */
    @objid ("7bd74c29-722e-46f2-a367-31b5158b3c10")
    @Override
    public void removeModule(GModule gModule, boolean deleteAnnotations) throws ModuleException {
        IRTModule rtModule = getIRTModule(gModule);
        if (rtModule != null) {
            rtModule.getController().removeFromProject(deleteAnnotations);
        } else if(gModule.getProject() != null){
            try {
                gModule.getProject().removeGPart(null, gModule);
            } catch (GPartException e) {
                throw new ModuleException(e.getLocalizedMessage(), e);
            }
        }
        
    }

    /**
     * Get the {@link IRTModule} corresponding to the given
     * {@link GModule}.
     * <p>
     * Creates a new IRTModule if none is found.
     * @param gModule the module .
     * @return the matching <code>IRTModule</code>.
     */
    @objid ("9398cd15-9ffa-47a3-87f6-95b5ab6922ed")
    private IRTModule getRTModule(GModule gModule) {
        IRTModule rtModule = this.moduleRegistry.getModule(gModule);
        
        if (rtModule == null) {
            rtModule = new RTModule(gModule, this.moduleRegistry, this.mdaResourceProviderRegistry);
            // Add loaded module to the registry
            this.moduleRegistry.addModule(rtModule);
        }
        return rtModule;
    }

    /**
     * Load and Start all activated non started modules of the given project.
     * @param project the project to start all activated modules of.
     * @param aMonitor optional progress monitor, may be <code>null</code>
     */
    @objid ("2bb63edc-f1ed-11e1-af52-001ec947c8cc")
    @Override
    public void startAllModules(IGProject project, final IProgressMonitor aMonitor) {
        SubMonitor progress = SubMonitor.convert(aMonitor);
        
        List<GModule> gModules = project.getParts(GModule.class);
        progress.setWorkRemaining(gModules.size());
        
        // Sort the module list by start order
        List<GModule> sortedModules = getSortedModules(gModules);
        
        // Load all modules, but start only activated modules.
        for (GModule gModule : sortedModules) {
            try {
                // Instantiate the RT module
                IRTModule rtModule = getRTModule(gModule);
                rtModule.getListeners().add(this.eventFirer);
                if (rtModule.getState() == ModuleRuntimeState.Started) {
                    continue;
                }
        
                // If the module has the property "SELECT_ON_OPEN", consider it's a first install
                GProperties properties = gModule.getProperties();
                final boolean isSelectDone = properties.getBooleanValue(PROP_SELECT_DONE, false);
                if (!isSelectDone) {
                    // It's a first install : install, activate and start
                    rtModule.getController().install();
        
                    // Remove the property, next time it will be a simple start
                    properties.setBooleanProperty(PROP_SELECT_DONE, true, DefinitionScope.LOCAL);
                } else if (gModule.isActive()) {
                    // Start the module
                    progress.subTask(MdaInfra.I18N.getMessage("ModuleStartProgress.Starting", rtModule.getLabel(),
                            rtModule.getVersion().toString()));
        
                    rtModule.getController().start();
                }
            } catch (ModuleException e) {
                MdaInfra.LOG.warning("%s v%s module start failed : %s", gModule.getId(), gModule.getVersion(), e.getLocalizedMessage());
                MdaInfra.LOG.debug(e);
            } catch (RuntimeException | LinkageError e) {
                MdaInfra.LOG.error("%s v%s module start unexpected error : %s", gModule.getId(), gModule.getVersion(), e.toString());
                MdaInfra.LOG.error(e);
            }
            progress.worked(1);
        
        }
        progress.done();
        
    }

    /**
     * Stop all started modules and unloads all loaded modules of the given project.
     * @param project the project to stop all modules of.
     */
    @objid ("2bb63ee1-f1ed-11e1-af52-001ec947c8cc")
    @Override
    public void stopAllModules(IGProject project) {
        // Unload modules in any order : closeModule() will unload dependencies first.
        for (IRTModule module : new ArrayList<>(getModuleRegistry().getModules())) {
            closeModule(module);
        }
        
    }

    @objid ("b5a61523-1207-11e2-8ab5-001ec947c8cc")
    @Inject
    @Optional
    @SuppressWarnings ("unused")
    void onProjectClosed(@EventTopic (ModelioEventTopics.PROJECT_CLOSED) IGProject gProject, IEclipseContext context) {
        // Clean up generic context
        context.set(IModuleContext.class, null);
        
        // Note: Don't remove this instance from the context , it would be garbage collected.
        
        // dispose the module registry and create a new one.
        this.moduleRegistry.dispose();
        this.moduleRegistry = new ModuleRegistry();
        
        // dispose the module mda resource provider registry and create a new one.
        this.mdaResourceProviderRegistry.reset();
        
        this.eventFirer = null;
        this.mdaExpert = null;
        
    }

    @objid ("b5a6151d-1207-11e2-8ab5-001ec947c8cc")
    @Inject
    @Optional
    void onProjectOpening(@EventTopic (ModelioEventTopics.PROJECT_OPENING) IGProject gProject, IEclipseContext context) {
        // Create a generic context to make the module API usable from anywhere.
        EmptyModuleConfiguration emptyConfiguration = new EmptyModuleConfiguration();
        IModuleContext genericContext = ModuleContextFactory.getInstance().createModuleContext(null, emptyConfiguration, emptyConfiguration);
        context.set(IModuleContext.class, genericContext);
        
        this.moduleRegistry.setProjectName(gProject.getName());
        
        this.eventFirer = new ManagerModuleListener(this.modelioEventService, this, this.moduleRegistry);
        this.mdaExpert = new MdaExpert(this.moduleRegistry);
        
    }

    /**
     * Unload definitively a module beginning by modules depending on him.
     * @param module the module to unload.
     */
    @objid ("050708da-7140-4e40-b847-497d6a674dda")
    private void closeModule(IRTModule module) {
        try {
            // Stop dependent modules first
            for (IRTModule m : module.getModuleMandatoryUses()) {
                closeModule(m);
            }
            for (IRTModule m : module.getModuleOptionalUses()) {
                closeModule(m);
            }
        
            module.getController().close();
        } catch (ModuleException | RuntimeException | LinkageError e) {
            MdaInfra.LOG.warning("'" + module.getName() + "' module does not want to close:");
            MdaInfra.LOG.warning(e);
        }
        
    }

    /**
     * Sort the module list by start order.
     * @param gModules a module list
     * @return the sorted list
     */
    @objid ("13acb1e6-ab67-4456-853e-401c51d4c810")
    private List<GModule> getSortedModules(List<GModule> gModules) {
        List<GModule> sortedModules;
        try {
            sortedModules = MTopoSorter.sortModules(gModules);
        } catch (CyclicDependencyException e) {
            MdaInfra.LOG.error(e);
            // Try to remove cycle and sort again
            Collection<GModule> cycle = e.getCycle();
            sortedModules = new ArrayList<>(gModules);
            sortedModules.removeAll(cycle);
            try {
                sortedModules = MTopoSorter.sortModules(gModules);
                // Append cycle at first position
                sortedModules.addAll(0, cycle);
            } catch (CyclicDependencyException e2) {
                // Return original list as last resort.
                sortedModules = gModules;
            }
        }
        return sortedModules;
    }

    @objid ("c73d0384-ced7-40fa-a066-b7c60c83b87d")
    private void installModule(final IModelioProgress aMonitor, IGProject gProject, Path moduleFilePath, URI origUri) throws ModuleException {
        try {
            SubProgress mon = SubProgress.convert(aMonitor, 2);
            IModuleHandle rtModuleHandle = gProject.getProjectEnvironment().getModulesCache().installModuleArchive(moduleFilePath, mon.newChild(1));
            // Is module in the cache?
            if (rtModuleHandle == null) {
                throw new ModuleException(MdaInfra.I18N.getMessage("ModuleExceptionMessage.InvalidArchivePath", moduleFilePath.toString()));
            }
        
            installModule(mon.newChild(1), gProject, rtModuleHandle, origUri);
        } catch (IOException e) {
            throw new ModuleException(MdaInfra.I18N.getMessage("ModuleExceptionMessage.CannotInstallModule", FileUtils.getLocalizedMessage(e)), e);
        }
        
    }

    /**
     * Installs, load and start the module contained in the given file in the given project. This method adds (or update) a module in the given GProject, then load and start the corresponding {@link IRTModule}.
     * @param gProject the project to install the module into.
     * @param moduleFilePath the path to the file of the module.
     * @throws ModuleException if an error occurred while trying to install the module.
     * @since 3.8
     */
    @objid ("4802c3b0-4596-489e-a9de-99f4ccd55eb2")
    @Override
    public void installModule(IModelioProgress monitor, IGProject gProject, Path moduleFilePath) throws ModuleException {
        installModule(monitor, gProject, moduleFilePath, moduleFilePath.toUri());
    }

    @objid ("6f0c0634-45e0-4a34-af4a-2b4f1c3e753a")
    @Override
    public void installModule(final IModelioProgress monitor, IGProject gProject, IModuleHandle rtModuleHandle, URI origUri) throws ModuleException {
        GModule newGModule = null;
        try {
            // Are dependencies missing or incompatible?
            ModuleResolutionHelper.checkCanInstall(rtModuleHandle, gProject);
        
            // Determine whether it is a first deployment or it is an upgrade.
            // For this, we have to look for the old module.
            GModule previouslyInstalledGModule = ModuleResolutionHelper.getGModuleByHandle(gProject, rtModuleHandle);
            if (previouslyInstalledGModule == null) {
                // Install the GModule in the GProject
                GProjectPartDescriptor d = new GProjectPartDescriptor(GProjectPartType.MODULE, rtModuleHandle.getName(), rtModuleHandle.getVersion(), DefinitionScope.LOCAL);
                d.setLocation(origUri);
                newGModule = (GModule) GPartFactory.getInstance().instantiate(d);
        
                gProject.addGPart(newGModule, true);
        
                // Instantiate a RTModule if necessary
                IRTModule rt = getRTModule(newGModule);
                boolean ok = false;
                try {
        
                    // Install, activate and start
                    rt.getListeners().add(this.eventFirer);
                    rt.getController().install();
                    ok = true;
                } finally {
                    if (!ok) {
                        // Uninstall the GModule
                        gProject.removeGPart(newGModule);
        
                        // Unregister the module registered by loadModule()
                        this.moduleRegistry.removeModule(rt);
                    }
                }
        
            } else {
                // Update existing module
                IRTModule startedOldModule = getModuleRegistry().getModule(previouslyInstalledGModule);
        
                startedOldModule.getController().updateTo(rtModuleHandle, origUri);
        
                newGModule = startedOldModule.getGModule();
            }
        
            if (newGModule != null) {
                // Note 08/04/2015: this event is not used
                this.modelioEventService.postSyncEvent(this, ModelioEvent.MODULE_DEPLOYED, newGModule);
            }
        } catch (GPartException e) {
            throw new ModuleException(MdaInfra.I18N.getMessage("ModuleExceptionMessage.CannotInstallModule", e.getLocalizedMessage()), e);
        }
        
    }

    @objid ("8010845b-c71c-4897-8d8c-3a33da9a2477")
    private static class EmptyModuleConfiguration implements IModuleUserConfiguration, IModuleAPIConfiguration {
        @objid ("28fc8e6a-125d-4b30-a7d7-eb6be5fdc9e8")
        @Override
        public void setParameterValidator(String paramName, IConfigParamValidator validator) {
            // Ignore
        }

        @objid ("f1df3d92-8c77-4dbc-a842-6d74aec44019")
        @Override
        public String getParameterValue(String key) {
            return null;
        }

        @objid ("56a4e39a-0f7d-46ce-91e4-27a0d86a6677")
        @Override
        public Map<String, String> getParameters() {
            return new HashMap<>();
        }

        @objid ("8fbc177d-0a97-4e1b-b6be-11d0f562e2e1")
        @Override
        public boolean setParameterValue(String key, String value) {
            return false;
        }

        @objid ("4259acc8-495e-4abe-946d-b49f4ef70771")
        @Override
        public void updateFrom(Map<String, String> parameters) {
            // Do nothing
        }

        @objid ("ab443c75-e8f9-4736-97f5-ae5c621cf5b7")
        @Override
        public Path getModuleResourcesPath() {
            return null;
        }

        @objid ("2e2e5718-94ad-4436-92f8-9d2d3fe5ba6b")
        @Override
        public List<Path> getDocpath() {
            return Collections.emptyList();
        }

        @objid ("f17bca7a-57ba-49a9-934e-53359f1fa502")
        @Override
        public IConfigParamValidator getParameterValidator(String paramName) {
            return null;
        }

        @objid ("ff539bce-bf74-49ed-aec9-327f940a4686")
        @Override
        public boolean isLocked(String key) {
            return true;
        }

        @objid ("eba0cc40-834f-4037-a0b2-74cab4bf7201")
        @Override
        public Map<String, Path> getStylePath() {
            return new HashMap<>();
        }

        @objid ("d30afdf6-534a-4ddb-9c39-0e1418843990")
        @Override
        public void addListener(IModuleConfigurationListener l) {
            // Do nothing
        }

        @objid ("8b1a3c50-cd8d-4370-ba4d-d73f1f9b25ae")
        @Override
        public void removeListener(IModuleConfigurationListener l) {
            // Do nothing
        }

    }

    /**
     * {@link IRTModuleListener} put on all modules to:
     * <ul>
     * <li>fire matching events in {@link IModelioEventService}.
     * <li>update the module registry
     * </ul>
     */
    @objid ("c8933b50-72a5-4f2a-95e2-d939d2e0c555")
    static class ManagerModuleListener extends AbstractIRTModuleListener {
        @objid ("1ec8b1d5-8823-4321-ba3c-e6ef71bb305d")
        private IModelioEventService modelioEventService;

        @objid ("449e5f30-ddb6-4b7f-8897-c8eddf506e4f")
        private IModelioService svc;

        @objid ("929dfc59-f10d-4420-b1da-590dfdc514d5")
        private IModuleRegistryAccess registry;

        @objid ("80cdb293-d5b0-4049-8a21-55868df62944")
        public  ManagerModuleListener(IModelioEventService modelioEventService, IModelioService svc, IModuleRegistryAccess registry) {
            super();
            this.modelioEventService = modelioEventService;
            this.svc = svc;
            this.registry = registry;
            
        }

        @objid ("9746356e-40fa-4903-b565-e2286c9b3793")
        @Override
        public void moduleStarted(IRTModule module) {
            this.registry.addStartedModule(module);
            this.modelioEventService.postSyncEvent(this.svc, ModelioEvent.MODULE_STARTED, module);
            
        }

        @objid ("15d214dc-5a51-4efd-ac17-1e0d65243498")
        @Override
        public void moduleStopping(IRTModule module) {
            this.registry.removeStartedModule(module);
            this.modelioEventService.postSyncEvent(this.svc, ModelioEvent.MODULE_STOPPED, module);
            
        }

        @objid ("0ee6579a-656a-4041-aa67-0dbaceb275db")
        @Override
        public void moduleLoaded(IRTModule module) {
            this.registry.addModule(module);
        }

        @objid ("0d6324df-9fbd-4ce2-8dd9-1c83298d2794")
        @Override
        public void moduleRemoved(IRTModule module) {
            this.registry.removeModule(module);
            this.modelioEventService.postSyncEvent(this.svc, ModelioEvent.MODULE_REMOVED, module);
            
        }

    }

}
