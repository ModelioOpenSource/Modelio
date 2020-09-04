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

package org.modelio.script.engine.core.engine;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.Display;
import org.modelio.script.engine.plugin.ScriptEnginePlugin;

/**
 * This is the ClassLoader specially configured for scripts. Classes are looked
 * up in the class loaders:
 * <ul>
 * <li>several classloaders contributed by application plugins
 * <li>the project module classloaders
 */
@objid ("0076d3ce-bf95-1069-96f6-001ec947cd2a")
public class ScriptClassLoader extends URLClassLoader {
    @objid ("c355dd19-fe1f-4928-9af2-c8fcaa8140d4")
    private static final String CLASSLOADERS_EXTENSION_ID = "org.modelio.script.engine.classloaders";

    @objid ("00b94e46-eab4-4452-979a-992daa438a37")
    private static final String CLASSLOADERPROVIDER = "classloaderprovider";

    @objid ("6e1a2ccc-7bdf-4308-b41c-4e1a944a7b01")
    private static final String JYTHONAPIPROVIDER = "jython-api-provider";

    @objid ("0076d86a-bf95-1069-96f6-001ec947cd2a")
    private final List<ClassLoader> parents = new ArrayList<>();

    @objid ("007706dc-bf95-1069-96f6-001ec947cd2a")
    public ScriptClassLoader() {
        super(new URL[0]);
        addContributingPluginClassLoaders();
    }

    @objid ("007225f4-c5d9-1069-96f6-001ec947cd2a")
    public void dispose() {
        this.parents.clear();
    }

    @objid ("0076d702-bf95-1069-96f6-001ec947cd2a")
    @Override
    public URL findResource(String name) {
        for (ClassLoader parent : this.parents) {
            URL ret = parent.getResource(name);
            if (ret != null) {
                return ret;
            }
        }
        return super.findResource(name);
    }

    @objid ("0076dda6-bf95-1069-96f6-001ec947cd2a")
    @Override
    @SuppressWarnings("unchecked")
    public Enumeration<URL> findResources(String name) throws IOException {
        Enumeration<URL>[] tmp = new Enumeration[this.parents.size() + 1];
        int i = 0;
        for (ClassLoader parent : this.parents) {
            tmp[i] = parent.getResources(name);
            i++;
        }
        
        tmp[i] = super.findResources(name);
        return new CompoundEnumeration<>(tmp);
    }

    /**
     * Returns the search path of URLs for loading classes and resources. This
     * includes: - the parent class loaders URLs - the original list of URLs
     * specified to the constructor, - along with any URLs subsequently appended
     * by the addURL() method.
     * @return the search path of URLs for loading classes and resources.
     */
    @objid ("0076debe-bf95-1069-96f6-001ec947cd2a")
    @Override
    public URL[] getURLs() {
        List<URL> urls = new LinkedList<>();
        for (ClassLoader l : this.parents) {
            if (l instanceof URLClassLoader) {
                for (URL u : ((URLClassLoader) l).getURLs()) {
                    urls.add(u);
                }
            }
        }
        for (URL u : super.getURLs()) {
            urls.add(u);
        }
        return urls.toArray(new URL[0]);
    }

    /**
     * Finds and loads the class with the specified name from the URL search
     * path. Any URLs referring to JAR files are loaded and opened as needed
     * until the class is found. Looks for classes first in parents class
     * loaders
     * @param name the name of the class
     * @return the resulting class
     * @exception ClassNotFoundException
     * if the class could not be found
     */
    @objid ("0076dcfc-bf95-1069-96f6-001ec947cd2a")
    @Override
    protected Class<? extends Object> findClass(String name) throws ClassNotFoundException {
        // Jython seems to look for non existing classes a lot, this optimization (hack?) should make execution a lot faster...
        // examples : "java.lang.__path__" , "org.modelio.api.modelio.__path__"
        if (name.endsWith("__path__")) {
            throw new ClassNotFoundException(name);
        }
        
        for (ClassLoader parent : this.parents) {
            try {
                return parent.loadClass(name);
            } catch (ClassNotFoundException|NoClassDefFoundError e) {
                // nothing: search next
            }
        }
        return super.findClass(name);
    }

    @objid ("03b33fe6-0d6f-11e2-a9b2-001ec947ccaf")
    void add(ClassLoader p) {
        if (p != null) {
            this.parents.add(p);
        }
    }

    /**
     * Loop on contributing plugins and add provider class loaders
     */
    @objid ("007320a8-c5d9-1069-96f6-001ec947cd2a")
    private void addContributingPluginClassLoaders() {
        // This must be synchronous for the batch mode...
        Display.getDefault().syncExec(new Runnable() {
            @Override
            public void run() {
                IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(CLASSLOADERS_EXTENSION_ID);
                for (IConfigurationElement elt : config) {
                    if (elt.getName().equals(JYTHONAPIPROVIDER)) {
                        try {
                            Object provider = elt.createExecutableExtension(CLASSLOADERPROVIDER);
                            if (provider instanceof IClassLoaderProvider) {
                                add(((IClassLoaderProvider) provider).getClassLoader());
                            }
        
                        } catch (CoreException e) {
                            ScriptEnginePlugin.LOG.error(e);
                        }
                    }
                }
            }
        });
    }

    /**
     * A useful utility class that will enumerate over an array of enumerations.
     * 
     * @param <E>
     * The type of enumerated elements
     */
    @objid ("00770808-bf95-1069-96f6-001ec947cd2a")
    public class CompoundEnumeration<E> implements Enumeration<E> {
        @objid ("0076e062-bf95-1069-96f6-001ec947cd2a")
        private int index = 0;

        @objid ("0076e15c-bf95-1069-96f6-001ec947cd2a")
        private final Enumeration<E>[] enums;

        @objid ("00770772-bf95-1069-96f6-001ec947cd2a")
        public CompoundEnumeration(Enumeration<E>[] enums) {
            this.enums = enums;
        }

        @objid ("0076e288-bf95-1069-96f6-001ec947cd2a")
        private boolean next() {
            while (this.index < this.enums.length) {
                if (this.enums[this.index] != null && this.enums[this.index].hasMoreElements()) {
                    return true;
                }
                this.index++;
            }
            return false;
        }

        @objid ("0076e332-bf95-1069-96f6-001ec947cd2a")
        @Override
        public boolean hasMoreElements() {
            return next();
        }

        @objid ("0076e3c8-bf95-1069-96f6-001ec947cd2a")
        @Override
        public E nextElement() {
            if (!next()) {
                throw new NoSuchElementException();
            }
            return this.enums[this.index].nextElement();
        }

    }

}
