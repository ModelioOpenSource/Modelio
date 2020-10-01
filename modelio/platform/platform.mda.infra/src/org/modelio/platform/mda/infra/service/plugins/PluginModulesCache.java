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

package org.modelio.platform.mda.infra.service.plugins;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.spi.RegistryContributor;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.platform.mda.infra.plugin.MdaInfra;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.model.spi.IGMetamodelExtension;
import org.osgi.framework.Bundle;

/**
 * {@link IModuleRTCache} and {@link IModuleStore} implementation for plugin modules.
 * @author cma
 * @since 3.8
 */
@objid ("780289b3-09af-4ca5-95ae-7a5654664728")
public class PluginModulesCache implements IModuleRTCache, IModuleStore {
    @objid ("7aae8075-293c-423a-ab11-bc2e245dd6de")
    private final PluginModuleHandleFactory handleFactory;

    @objid ("6c430bed-608a-47b6-aab9-a268561ca2d9")
    private final Map<Bundle, IModuleHandle> handles = new WeakHashMap<>();

    @objid ("ac9f5c75-1208-4fb0-a41a-7ae8d47b6148")
    public PluginModulesCache(final Collection<IGMetamodelExtension> mmExtensions) {
        this.handleFactory = new PluginModuleHandleFactory(mmExtensions);
    }

    /**
     * Plugins don't need installation
     */
    @objid ("819992c7-cc7b-430a-b6b7-3956ab1d6654")
    @Override
    public IModuleHandle installModuleArchive(final Path archive, final IModelioProgress monitor) throws IOException {
        throw new UnsupportedOperationException();
    }

    @objid ("1d9dfbe7-a61c-49a6-a40a-4c78306481b6")
    @Override
    public IModuleHandle findModule(final String moduleName, final String moduleVersion, final IModelioProgress monitor) throws IOException {
        for (final IConfigurationElement elt : new ExtensionPointContributionManager(PluginModuleConstants.EXTENSION_ID).getExtensions(PluginModuleConstants.MODULE_EL)) {
            if (elt.getAttribute(PluginModuleConstants.MODULE_NAME).equals(moduleName)
                    && moduleVersion == null || elt.getAttribute(PluginModuleConstants.MODULE_VERSION).equals(moduleVersion)) {
        
                return getModuleHandle(monitor,  elt);
            }
        }
        return null;
    }

    @objid ("79593e3c-c6a5-4902-9cbc-cc07558f4898")
    private IModuleHandle getModuleHandle(final IModelioProgress monitor, final IConfigurationElement elt) throws IOException {
        final Bundle plugin = getBundle(elt.getContributor());
        
        IModuleHandle h = this.handles.get(plugin);
        if (h != null || this.handles.containsKey(plugin)) {
            return h;
        }
        
        h = this.handleFactory.get(plugin, monitor, elt);
        this.handles.put(plugin, h);
        return h;
    }

    /**
     * Plugins are not removable.
     */
    @objid ("f54842a9-a325-4623-8db5-e1f82cac3f31")
    @Override
    public void removeModule(final IModuleHandle mh) throws FileSystemException, IOException {
        throw new UnsupportedOperationException();
    }

    @objid ("f93cf0e6-a5a0-4b0f-b8a0-89d360b93a01")
    private static Bundle getBundle(final IContributor contributor) {
        /*
         * from https://stackoverflow.com/questions/3043828/getting-osgi-bundle-from-eclipse-iconfigurationelement
         *
         * All this information is based on Eclipse 3.6:
         *
         * The IContributor will be an instance of RegistryContributor if you are in the OSGI environment
         * which of course you are or you wouldn't be having this issue.
         *
         * RegistryContributor gives you two methods: getID() and getActualID().
         * getID() may return the host bundle if this was loaded from a fragment.
         * getActualID() always loads the id of the fragment/bundle the contributor represents.
         *
         * You can use this id in your BundleContext.getBundle(long id) method.
         */
        if (contributor instanceof RegistryContributor) {
            final long id = Long.parseLong(((RegistryContributor) contributor).getActualId());
            return MdaInfra.getContext().getBundle(id);
        } else {
            return Platform.getBundle(contributor.getName());
        }
    }

    @objid ("77ff05cb-91b2-4cec-811d-4d34809d37c4")
    @Override
    public List<IModuleHandle> findAllModules(final IModelioProgress monitor) throws FileSystemException, IOException {
        final List<IModuleHandle> ret = new ArrayList<>(1);
        for (final IConfigurationElement elt : new ExtensionPointContributionManager(PluginModuleConstants.EXTENSION_ID).getExtensions(PluginModuleConstants.MODULE_EL)) {
            ret.add(getModuleHandle(monitor,  elt));
        }
        return ret;
    }

    /**
     * Plugins have no archive. Always return null.
     */
    @objid ("cc0d6071-05a8-49ba-a116-1adb7cbac3c8")
    @Override
    public IModuleHandle findModule(final Path archivePath, final IModelioProgress monitor) throws FileSystemException, IOException {
        return null;
    }

    @objid ("67fde6fa-5902-4fd9-be74-e53489a15db5")
    @Override
    public List<IModuleHandle> findModule(final String moduleName, final IModelioProgress monitor) throws FileSystemException, IOException {
        final List<IModuleHandle> ret = new ArrayList<>(1);
        for (final IConfigurationElement elt : new ExtensionPointContributionManager(PluginModuleConstants.EXTENSION_ID).getExtensions(PluginModuleConstants.MODULE_EL)) {
            if (elt.getAttribute(PluginModuleConstants.MODULE_NAME).equals(moduleName)) {
                ret.add(getModuleHandle(monitor,  elt));
            }
        }
        return ret;
    }

}
