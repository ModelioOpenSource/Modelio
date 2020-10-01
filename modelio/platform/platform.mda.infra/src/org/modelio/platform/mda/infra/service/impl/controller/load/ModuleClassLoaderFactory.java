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

package org.modelio.platform.mda.infra.service.impl.controller.load;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.NoSuchElementException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.modelio.api.module.IModule;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.gproject.module.GModule;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.platform.mda.infra.plugin.MdaInfra;
import org.modelio.platform.mda.infra.service.IModuleClassLoaderProvider;
import org.modelio.platform.mda.infra.service.IRTModule;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.model.spi.IGMetamodelExtension;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;

/**
 * Service class to build a module class loader.
 */
@objid ("1a5dcd22-40b1-413c-8a5a-bd524c0a22f9")
public class ModuleClassLoaderFactory {
    @objid ("fa0145de-4a3d-4f08-8c41-2d6acc2af35a")
    private static final String CLASSLOADERS_EXTENSION_ID = "org.modelio.platform.mda.infra.classloaders";

    @objid ("a2628fc0-f0d9-4b25-8ce1-65340726bd4f")
    private static final String CLASSLOADERPROVIDER = "classloaderprovider";

    @objid ("b9e514c5-3096-42f6-92a0-6065bbedfb46")
    private static final String MODULE_API_PROVIDER = "module-api-provider";

    @objid ("063e1289-b5cc-4499-9374-7df5ef92a9b5")
    private ModuleClassLoaderFactory() {
        // no instance
    }

    /**
     * Creates a ClassLoader for the given module and local class path.
     * 
     * @param rtModule a module
     * @return a class loader
     * @throws org.modelio.api.module.lifecycle.ModuleException in case of error
     */
    @objid ("fe34fc41-9d66-445e-92d6-f0d40c8aac36")
    public static ClassLoader setupClassLoader(final IRTModule rtModule) throws ModuleException {
        // Plugin modules already have their class loader
        final ClassLoader providedClassLoader = rtModule.getGModule().getModuleHandle().getProvidedClassLoader();
        if (providedClassLoader != null) {
            return providedClassLoader;
        }
        
        
        // We need to "preload" the module so that we can actually access its
        // main class to call the static method on.
        // Resolve all loaded dependencies
        
        final List<IRTModule> loadedDependencies = new ArrayList<>();
        loadedDependencies.addAll(rtModule.getRequiredDependencies());
        loadedDependencies.addAll(rtModule.getOptionalDependencies());
        
        // Construct a class loader on these informations.
        return ModuleClassLoaderFactory.setupClassLoader(rtModule.getGModule(), loadedDependencies);
    }

    /**
     * Creates a ClassLoader for the given module and local class path.
     * 
     * @param gModule The module
     * @param loadedDependencies the list of all loaded IModules the current module depends on
     * (either strongly or weakly)
     * @return a class loader
     * @throws org.modelio.api.module.lifecycle.ModuleException in case of error
     */
    @objid ("60e506f2-e046-4264-95f9-800ac4a8fae7")
    private static URLClassLoader setupClassLoader(final GModule gModule, final List<IRTModule> loadedDependencies) throws ModuleException {
        // - collect the module resources directory and add it in a list with
        // all files in the 'classpath' administrative infos
        // - collect all required and available weak dependency modules class
        // loaders
        // - collect all required plugins class loaders
        // - build a class loader from both the list of path and the list of
        // class loaders.
        
        final List<URL> completeClassPath = new ArrayList<>();
        final Collection<ClassLoader> parentLoaders = new ArrayList<>();
        
        // The local class path for the module. Each path may be relative to the
        // module resources path.
        final IModuleHandle rtModuleHandle = gModule.getModuleHandle();
        final List<Path> declaredClasspath = rtModuleHandle.getJarPaths();
        
        final Path fClassPath = rtModuleHandle.getResourcePath();
        if (fClassPath == null) {
            return new ModuleClassLoader(
                    rtModuleHandle.getName(),
                    completeClassPath.toArray(new URL[completeClassPath.size()]),
                    parentLoaders);
        }
        
        try {
            // Add the module path to the loader classpath (for resources loading)
            completeClassPath.add(fClassPath.toUri().toURL());
        
            // Add the module declared libraries to the classpath
            if (declaredClasspath == null || declaredClasspath.isEmpty()) {
                final String msg = String.format(
                        "The '%1$s' module classpath is empty!",
                        rtModuleHandle.getName());
                final ModuleException e2 = new ModuleException(msg);
                throw e2;
            } else {
                for (final Path path : declaredClasspath) {
                    try {
                        completeClassPath.add(fClassPath.resolve(path).toUri()
                                .toURL());
                    } catch (final MalformedURLException e) {
                        final String msg = String
                                .format("The '%1$s' module '%2$s' classpath entry is invalid:\n %3$s",
                                        rtModuleHandle.getName(),
                                        path.toString(),
                                        e.getLocalizedMessage());
                        final ModuleException e2 = new ModuleException(msg);
                        e2.initCause(e);
                        throw e2;
                    }
                }
            }
        
            // Mandatory plugin class loaders
            parentLoaders.add(IModule.class.getClassLoader());
            parentLoaders.add(Version.class.getClassLoader());
        
            // Collect metamodels class loaders
            for (final IGMetamodelExtension mmExt : gModule.getProject().getMetamodelExtensions()) {
                final ISmMetamodelFragment mmf = mmExt.getMmFragment();
                final ClassLoader loader = mmf.getClass().getClassLoader();
                if (!parentLoaders.contains(loader)) {
                    parentLoaders.add(loader);
                }
            }
        
            // Collect class loaders of some plugins modules should have access to.
            ModuleClassLoaderFactory.addPluginsClassLoaders(parentLoaders);
        
            // Get required modules class loaders
            for (final IRTModule im : loadedDependencies) {
                final ClassLoader classLoader = im.getClassLoader();
                if (classLoader != null) {
                    parentLoaders.add(classLoader);
                }
            }
        
            // Create final module class loader
            return new ModuleClassLoader(
                    rtModuleHandle.getName(),
                    completeClassPath.toArray(new URL[completeClassPath.size()]),
                    parentLoaders);
        
        } catch (final MalformedURLException e) {
            final String msg = String.format(
                    "The '%1$s' module '%2$s' classpath is invalid:\n %3$s",
                    rtModuleHandle.getName(), fClassPath.toString(),
                    e.getLocalizedMessage());
            final ModuleException e2 = new ModuleException(msg);
            e2.initCause(e);
            throw e2;
        }
    }

    /**
     * Loop on contributing plugins and add provider class loaders
     */
    @objid ("8a81c9b3-f34b-11e1-9458-001ec947c8cc")
    private static void addPluginsClassLoaders(final Collection<ClassLoader> parentLoaders) {
        for (final IConfigurationElement elt : new ExtensionPointContributionManager(ModuleClassLoaderFactory.CLASSLOADERS_EXTENSION_ID).getExtensions(ModuleClassLoaderFactory.MODULE_API_PROVIDER)) {
            try {
                final Object provider = elt.createExecutableExtension(ModuleClassLoaderFactory.CLASSLOADERPROVIDER);
                if (provider instanceof IModuleClassLoaderProvider) {
                    final ClassLoader loader = ((IModuleClassLoaderProvider) provider).getClassLoader();
                    if (!parentLoaders.contains(loader)) {
                        parentLoaders.add(loader);
                    }
                }
            } catch (final CoreException e) {
                MdaInfra.LOG.error(e);
            }
        }
    }

    /**
     * This is the ClassLoader specially designed for modules.
     * <p>
     * Classes are looked up in the required module class loaders, before the
     * module own class path. That's why this class loader may have many parent
     * class loaders.
     */
    @objid ("8a81c9bd-f34b-11e1-9458-001ec947c8cc")
    private static class ModuleClassLoader extends URLClassLoader {
        /**
         * Module name of the class loader.<br>
         * Just for debugging purpose.
         */
        @objid ("7f508e16-0263-11e2-9fca-001ec947c8cc")
        private final String moduleName;

        @objid ("8a842bfe-f34b-11e1-9458-001ec947c8cc")
        private final Collection<ClassLoader> parents;

        @objid ("8a842ca6-f34b-11e1-9458-001ec947c8cc")
        public ModuleClassLoader(final String name, final URL[] classpath, final Collection<ClassLoader> pParents) {
            super(classpath);
            this.moduleName = name;
            this.parents = pParents;
        }

        @objid ("8a842c56-f34b-11e1-9458-001ec947c8cc")
        @Override
        public URL findResource(final String name) {
            for (final ClassLoader parent : this.parents) {
                final URL ret = parent.getResource(name);
                if (ret != null) {
                    return ret;
                }
            }
            return super.findResource(name);
        }

        @objid ("8a842c58-f34b-11e1-9458-001ec947c8cc")
        @Override
        @SuppressWarnings("unchecked")
        public Enumeration<URL> findResources(final String name) throws IOException {
            final Enumeration<URL>[] tmp = new Enumeration[this.parents.size() + 1];
            int i = 0;
            for (final ClassLoader parent : this.parents) {
                tmp[i] = parent.getResources(name);
                i++;
            }
            
            tmp[i] = super.findResources(name);
            return new CompoundEnumeration<>(tmp);
        }

        /**
         * Returns the search path of URLs for loading classes and resources.
         * <p>
         * This includes:
         * <ul>
         * <li>the parent classloaders URLs
         * <li>the original list of URLs specified to the constructor,
         * <li>along with any URLs subsequently appended by the addURL() method.
         * </ul>
         * 
         * @return the search path of URLs for loading classes and resources.
         */
        @objid ("8a842c6e-f34b-11e1-9458-001ec947c8cc")
        @Override
        public URL[] getURLs() {
            final List<URL> urls = new ArrayList<>();
            for (final ClassLoader l : this.parents) {
                if (l instanceof URLClassLoader) {
                    for (final URL u : ((URLClassLoader) l).getURLs()) {
                        urls.add(u);
                    }
                }
            }
            for (final URL u : super.getURLs()) {
                urls.add(u);
            }
            return urls.toArray(new URL[urls.size()]);
        }

        /**
         * Looks for classes first in parents classloaders
         */
        @objid ("8a842c65-f34b-11e1-9458-001ec947c8cc")
        @Override
        protected Class<? extends Object> findClass(final String name) throws ClassNotFoundException {
            try {
                for (final ClassLoader parent : this.parents) {
                    try {
                        return parent.loadClass(name);
                    } catch (@SuppressWarnings("unused") final ClassNotFoundException e) {
                        // nothing: search next
                    }
                }
                return super.findClass(name);
            } catch (final Error e) {
                MdaInfra.LOG.error(
                        "%s: %s while looking for a class. classpath=",
                        this.moduleName, e.getClass().getSimpleName());
                for (final URL url : getURLs()) {
                    MdaInfra.LOG.error("%s:  - %s", this.moduleName,
                            url.toString());
                }
                throw e;
            }
        }

        /**
         * A useful utility class that will enumerate over an array of
         * enumerations.
         * 
         * @param <E>
         * The type of enumerated elements
         */
        @objid ("8a842bfd-f34b-11e1-9458-001ec947c8cc")
        public class CompoundEnumeration<E> implements Enumeration<E> {
            @objid ("8a842c4f-f34b-11e1-9458-001ec947c8cc")
            private int index = 0;

            @objid ("8a842c4c-f34b-11e1-9458-001ec947c8cc")
            private final Enumeration<E>[] enums;

            @objid ("8a842ca7-f34b-11e1-9458-001ec947c8cc")
            public CompoundEnumeration(final Enumeration<E>[] enums) {
                this.enums = enums;
            }

            @objid ("8a842c50-f34b-11e1-9458-001ec947c8cc")
            @Override
            public boolean hasMoreElements() {
                return this.next();
            }

            @objid ("8a842c51-f34b-11e1-9458-001ec947c8cc")
            @Override
            public E nextElement() {
                if (!this.next()) {
                    throw new NoSuchElementException();
                }
                return this.enums[this.index].nextElement();
            }

            @objid ("8a842c52-f34b-11e1-9458-001ec947c8cc")
            private boolean next() {
                while (this.index < this.enums.length) {
                    if (this.enums[this.index] != null
                            && this.enums[this.index].hasMoreElements()) {
                        return true;
                    }
                    this.index++;
                }
                return false;
            }

        }

    }

}
