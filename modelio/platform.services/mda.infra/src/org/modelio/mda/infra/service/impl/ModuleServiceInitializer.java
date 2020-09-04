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

package org.modelio.mda.infra.service.impl;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.modelio.app.core.ModelioEnv;
import org.modelio.app.preferences.plugin.AppPreferences;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.gproject.module.catalog.FileModuleStore;
import org.modelio.gproject.module.rtcache.ModuleRTCache;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.mda.infra.plugin.MdaInfra;
import org.modelio.mda.infra.service.IModuleManagementService;
import org.modelio.mda.infra.service.IModuleService;
import org.modelio.mda.infra.service.plugins.PluginModulesCache;
import org.modelio.vbasic.progress.IModelioProgress;

/**
 * This class is used as a processor (see the plugin.xml file) to make the
 * injection framework instantiate the ModuleService and ModuleManagementService
 * and put them in the context.
 * <p>
 * Also initialize the module cache and put it in the context with
 * {@link IModuleStore} as key.
 */
@objid ("aa507ee3-b6c8-471f-836b-6097781f4c45")
public class ModuleServiceInitializer {
    @objid ("6b428c7e-8d37-467f-b2cb-c30c6df89c74")
    @Execute
    private static void execute(final IEclipseContext context) {
        final ModuleManagementService moduleService = ContextInjectionFactory.make(ModuleManagementService.class, context);
        context.set(IModuleManagementService.class, moduleService);
        context.set(IModuleService.class, moduleService);
        
        ModuleServiceInitializer.initModuleCache(context);
        
        ModuleI18NService.init(moduleService);
    }

    /**
     * initialize the module cache and register it in the context as
     * {@link IModuleStore}.
     * 
     * @param context the context to initialize.
     */
    @objid ("85f146e7-5f64-4268-a140-9cef33804584")
    private static void initModuleCache(final IEclipseContext context) {
        final ModelioEnv env = context.get(ModelioEnv.class);
        
        // Instantiate and register the module catalog
        final IModuleStore stdModuleCatalog = context.get(IModuleStore.class);
        
        // Get the mda.infra preference node, as the module catalog is managed by this plugin
        // Add a preference change listener to update module catalog path.
        if (stdModuleCatalog instanceof FileModuleStore) {
            AppPreferences.getPreferences().addPropertyChangeListener(new IPropertyChangeListener() {
                @Override
                public void propertyChange(final PropertyChangeEvent event) {
                    if (ModelioEnv.MODULE_PATH_PREFERENCE.equals(event.getProperty())) {
                        ((FileModuleStore) stdModuleCatalog).setCachePath(Paths.get((String) event.getNewValue()));
                    }
                }
            });
        }
        
        // Instantiate the module catalog cache
        final Path cachePath = MdaInfra.getContext().getBundle().getDataFile("modules_cache").toPath();
        final IModuleRTCache moduleCache = new ModuleRTCache(stdModuleCatalog, env.getAllMetamodelExtensions(), cachePath);
        
        MdaInfra.LOG.debug("Module cache created in: " + cachePath);
        
        // Plugdules : plugin modules "cache"
        final PluginModulesCache pluginCache = new PluginModulesCache(env.getAllMetamodelExtensions());
        
        // Register the module catalog cache as the module catalog
        final CompositeModuleCache aggregateCache = new CompositeModuleCache(pluginCache, moduleCache);
        context.set(IModuleRTCache.class, aggregateCache);
    }

    @objid ("f2bf5c46-4be2-4a53-b009-3493ab650efd")
    private static class CompositeModuleCache implements IModuleRTCache {
        @objid ("32d059d4-d8f0-4a3f-9893-a55a3fd2ab6f")
        private final IModuleRTCache pluginCache;

        @objid ("f7dd11ee-09c0-4c0a-a3e6-94a8db1780de")
        private final IModuleRTCache modulesCache;

        @objid ("d7b69173-4d3c-4abe-a828-209150fc492f")
        public CompositeModuleCache(final IModuleRTCache pluginCache, final IModuleRTCache modulesCache) {
            super();
            this.pluginCache = pluginCache;
            this.modulesCache = modulesCache;
        }

        @objid ("53bc41f1-d436-4ad2-b192-c2d9ae2ad13e")
        @Override
        public IModuleHandle installModuleArchive(final Path archive, final IModelioProgress monitor) throws IOException {
            return this.modulesCache.installModuleArchive(archive, monitor);
        }

        @objid ("bf82b40d-21d5-42ec-b234-1f7f0bc17285")
        @Override
        public IModuleHandle findModule(final String moduleName, final String moduleVersion, final IModelioProgress monitor) throws IOException {
            IModuleHandle h = this.pluginCache.findModule(moduleName, moduleVersion, monitor);
            
            if (h == null) {
                h = this.modulesCache.findModule(moduleName, moduleVersion, monitor);
            }
            return h;
        }

    }

}
