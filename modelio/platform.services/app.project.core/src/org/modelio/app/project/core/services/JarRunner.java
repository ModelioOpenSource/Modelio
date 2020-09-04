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

package org.modelio.app.project.core.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map.Entry;
import java.util.Map;
import java.util.jar.JarFile;
import javax.annotation.PreDestroy;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.InjectionException;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.app.project.core.plugin.AppProjectCore;
import org.modelio.gproject.gproject.GProject;
import org.modelio.script.engine.core.engine.IScriptRunner;
import org.modelio.script.engine.core.engine.ScriptRunnerFactory;

/**
 * Runs a jar file main class as an E4 model processor instantiated with {@link ContextInjectionFactory}.
 * <p>
 * The main class name must be specified in the jar Manifest.
 * <p>
 * All methods tagged @Inject will be called, then one method tagged @PostConstruct.<br>
 * The {@link PreDestroy @PreDestroy} method will be called last.
 * <p>
 * The following classes are injected:<ul>
 * <li> {@link Modelio}
 * <li> {@link GProject}
 * <li> {@link IModelingSession}
 * </ul>
 */
@objid ("f1d8087d-48cf-4072-8ccf-6ab2489a01f9")
class JarRunner {
    @objid ("682328af-8225-4ffa-b777-d9135863e77d")
    public JarRunner() {
        super();
    }

    @objid ("a261e70a-9150-4f6a-a877-94f5963fb82e")
    public void runJarFile(GProject openedProject, File scriptFile, Map<String, String> params) {
        String mainClassName ;
        try (JarFile j = new JarFile(scriptFile);){
            mainClassName = j.getManifest().getMainAttributes().getValue("Main-Class");
        } catch (IOException e) {
            AppProjectCore.LOG.error("Couldn't run '"+scriptFile+"' : "+e.getLocalizedMessage());
            System.exit(-1);
            return; // just to tell compiler mainClassName is never null.
        }
            
        try {
            // Instantiate a ScriptRunner only to get its configured class loader
            final IScriptRunner scriptRunner = ScriptRunnerFactory.getInstance().getScriptRunner("jython");
            scriptRunner.addClassLoader(scriptRunner.getEngine().getClass().getClassLoader());
        
            URL[] urls = new URL[]{scriptFile.toURI().toURL()};
            @SuppressWarnings("resource")
            URLClassLoader cl = new URLClassLoader(urls, scriptRunner.getEngine().getClass().getClassLoader());
            scriptRunner.addClassLoader(cl);
        
            // Configure Eclipse 4 contexts
            IEclipseContext context = EclipseContextFactory.create(mainClassName);
            IEclipseContext staticContext = EclipseContextFactory.create(mainClassName+" static context");
            
            staticContext.set(GProject.class, openedProject);
            staticContext.set("parameters", params);
            for (Entry<String, String> p : params.entrySet())
                staticContext.set(p.getKey(), p.getValue());
        
            // Load class, inject contexts
            Class<?> clazz = cl.loadClass(mainClassName);
            Object object = ContextInjectionFactory.make(clazz, context, staticContext);
            
            // invoke @PreDestroy if any
            ContextInjectionFactory.invoke(object, PreDestroy.class, staticContext, staticContext);
            context.dispose();
        } catch (InjectionException e) {
            AppProjectCore.LOG.error("Couldn't run '"+scriptFile+"' : "+e.getLocalizedMessage());
            AppProjectCore.LOG.error(e);
            System.exit(-1);
        } catch (MalformedURLException e) {
            AppProjectCore.LOG.error("Couldn't convert '"+scriptFile+"' to URL: "+e.getLocalizedMessage());
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            AppProjectCore.LOG.error("Couldn't load '"+mainClassName+"' class from '"+scriptFile+"' : "+e.getLocalizedMessage());
            System.exit(-1);
        }
    }

}
