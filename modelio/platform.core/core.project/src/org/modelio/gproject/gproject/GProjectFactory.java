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

package org.modelio.gproject.gproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.ILockInfo;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptorReader;
import org.modelio.gproject.data.project.ProjectFileStructure;
import org.modelio.gproject.gproject.lock.ProjectLock;
import org.modelio.gproject.gproject.url.GUrlProjectFactory;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.gproject.module.rtcache.EmptyModuleCache;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.model.spi.IGMetamodelExtension;

/**
 * Factory that instantiates a GProject from a File path.
 */
@objid ("004d0788-3511-1fc6-b42e-001ec947cd2a")
public class GProjectFactory {
    @objid ("da624ce3-0e3b-11e2-8e4b-001ec947ccaf")
    private static Collection<IProjectFactory> factories = new ArrayList<>();

    /**
     * Get the remote project descriptor for a project descriptor.
     * 
     * @param projectDescriptor a project descriptor.
     * @param authData authentication data.
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call
     * <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @return the remote project descriptor.
     * @throws java.io.IOException in case of failure
     * @throws org.modelio.gproject.gproject.GProjectAuthenticationException in case of authentication failure.
     */
    @objid ("96d74dc5-cf49-4d1a-8670-fdb5001db4ab")
    public static ProjectDescriptor getRemoteDescriptor(ProjectDescriptor projectDescriptor, IAuthData authData, IModelioProgress monitor) throws GProjectAuthenticationException, IOException {
        IProjectFactory f = GProjectFactory.getProjectFactory(projectDescriptor);
        if (f != null) {
            return f.getRemoteDescriptor(projectDescriptor, authData, monitor);
        
        } else {
            // No remote descriptor for local projects
            return null;
        }
    }

    /**
     * Tells whether the given path is a project space path.
     * 
     * @param projectPath a directory path
     * @return <code>true</code> if it is a project space path, else
     * <code>false</code>.
     */
    @objid ("0021c0c8-34d4-1fc7-b42e-001ec947cd2a")
    public static boolean isProjectSpace(final Path projectPath) {
        Path confFile = GProjectFactory.getConfigFile(projectPath);
        return Files.isRegularFile(confFile);
    }

    /**
     * Register a project factory.
     * 
     * @param f a project factory.
     */
    @objid ("da64af38-0e3b-11e2-8e4b-001ec947ccaf")
    public static void registerFactory(IProjectFactory f) {
        GProjectFactory.factories.add(f);
    }

    /**
     * Create a ProjectDescriptor from a project directory.
     * 
     * @param projectDir the project directory.
     * @return the read project descriptor.
     * @throws java.io.IOException in case of error reading the configuration file.
     */
    @objid ("ea6edf6a-d50f-4f48-a07c-7bf27a487875")
    public static ProjectDescriptor readProjectDirectory(final Path projectDir) throws IOException {
        Path confFile = GProjectFactory.getConfigFile(projectDir);
        ProjectDescriptor desc = new ProjectDescriptorReader()
                .setDefaultScope(DefinitionScope.LOCAL)
                .read(confFile, null);
        
        try {
            desc.setLockInfo(GProjectFactory.getLockInformations(desc));
        } catch (IOException e) {
            // ignore
            Log.trace("Cannot get lock informations on " + projectDir + ":");
            Log.trace(e);
        }
        return desc;
    }

    @objid ("0021e1c0-34d4-1fc7-b42e-001ec947cd2a")
    private static Path getConfigFile(final Path projectPath) {
        return new ProjectFileStructure(projectPath).getProjectConfFile();
    }

    /**
     * Get the custom factory supporting the given descriptor.
     * <p>
     * Returns <code>null</code> if no such factory is found.
     * 
     * @param desc the project descriptor to load
     * @return the found factory or <code>null</code>.
     */
    @objid ("3bb2ca6c-85f2-49df-b408-4908ec7d361f")
    private static IProjectFactory getProjectFactory(ProjectDescriptor desc) {
        // Look for java services (META-INF/services)
        for (IProjectFactory f : java.util.ServiceLoader.load(IProjectFactory.class)) {
            if (f.supports(desc)) {
                return f;
            }
        }
        
        // Look for registered factories
        for (IProjectFactory f : GProjectFactory.factories) {
            if (f.supports(desc)) {
                return f;
            }
        }
        return null;
    }

    /**
     * Test whether a project is locked.
     * 
     * @param desc a project descriptor.
     * @return lock informations if the project is locked, else <i>null</i>.
     * @throws java.io.IOException in case of I/O failure
     */
    @objid ("2730c29a-29f2-48da-bf37-e87126767e2b")
    public static ILockInfo getLockInformations(final ProjectDescriptor desc) throws IOException {
        ProjectLock locker = ProjectLock.get(
                desc.getProjectFileStructure().getProjectRuntimePath(),
                desc.getName());
        return locker.test();
    }

    /**
     * No instance.
     */
    @objid ("b9e9ef93-f4b0-4a03-bb48-fc37f1f6cfa5")
    private GProjectFactory() {
        // No instance
    }

    /**
     * Initialize a GProject builder.
     * 
     * @param projectDescriptor a project descriptor.
     * @return a GProject builder to be configured.
     */
    @objid ("3d9a4673-48aa-401b-93bf-a2737da1295c")
    public static IBuilderMissingMetamodel from(ProjectDescriptor projectDescriptor) {
        return new Builder(projectDescriptor);
    }

    /**
     * Initialize a GProject builder from a project directory.
     * 
     * @param projectDir the project directory
     * @return a GProject builder to be configured.
     * @throws java.io.IOException in case of error reading the configuration file.
     */
    @objid ("da9e65b6-370b-4475-8934-b625221dcc8a")
    public static IBuilderMissingMetamodel fromProjectDirectory(Path projectDir) throws IOException {
        return new Builder(GProjectFactory.readProjectDirectory(projectDir));
    }


static {
        // Register the URI project factory.
        GProjectFactory.factories.add(new GUrlProjectFactory());
    }
    /**
     * Interface for {@link Builder} to force the caller to initialize the metamodel fragments
     * before calling optional methods.
     * 
     * @author cmarin
     * @since 3.6
     */
    @objid ("36cccaaa-64fb-4839-8c30-6f246b2bdba6")
    public interface IBuilderMissingMetamodel {
        /**
         * @param metamodelExtensions metamodel extensions to add
         * @return this builder with new methods accessible.
         */
        @objid ("976021f3-8d47-42b4-bcd0-6ce5346344b9")
        Builder withMetamodelExtensions(Collection<IGMetamodelExtension> metamodelExtensions);

        /**
         * Set the module catalog and the metamodel fragments from an existing project environment.
         * 
         * @param configuration an already configured project environment to copy.
         * @return this builder with new methods accessible.
         */
        @objid ("840b87f3-7455-44e5-9705-350c6e7d2315")
        Builder withEnvironment(IGProjectEnv configuration);

        /**
         * Use the same modules catalog and metamodel fragments as another project.
         * 
         * @param aProject another project. The project must be open.
         * @return this builder with new methods accessible.
         */
        @objid ("36cdfbb7-23d6-4f57-8d3f-e419948154c8")
        Builder withEnvironmentOf(GProject aProject);

    }

    /**
     * Builder pattern to load and optionally open a GProject.
     * 
     * @author cmarin
     * @since 3.6
     */
    @objid ("416180a8-ad84-4005-a391-2a04ce2c987c")
    public static class Builder implements IBuilderMissingMetamodel {
        @objid ("3972c22d-fabf-492c-9347-3290a7c54541")
        private GProjectEnvironment configuration = new GProjectEnvironment();

        @objid ("1ccb6dcf-aac3-451a-8940-32b95bad0301")
        private ProjectDescriptor projectDescriptor;

        @objid ("d3a5bf11-9e76-4741-a0ee-678ceaa2ca19")
        private IAuthData authData;

        @objid ("8f078008-4bd8-4db2-b505-232865f8258b")
        private IProjectMonitor eventListener;

        /**
         * @param projectDescriptor the mandatory project descriptor
         */
        @objid ("d92fa5ec-2fe2-4378-92aa-8c0e00ef33e8")
        public Builder(ProjectDescriptor projectDescriptor) {
            this.projectDescriptor = projectDescriptor;
        }

        /**
         * @param auth optional authentication data
         * @return this builder to chain calls.
         */
        @objid ("85e12658-8d4b-4f22-a860-bb313660fc4a")
        public Builder withAuth(IAuthData auth) {
            this.authData = auth;
            return this;
        }

        /**
         * @param metamodelFragments metamodel fragments to add.
         * @return this builder to chain calls.
         */
        @objid ("598d3094-0120-4f10-823f-8d84fcbe2c9f")
        @Override
        public Builder withMetamodelExtensions(Collection<IGMetamodelExtension> metamodelFragments) {
            this.configuration.addMetamodelExtensions(metamodelFragments);
            return this;
        }

        /**
         * Set the module catalog and the metamodel fragments from an existing project environment.
         * 
         * @param configuration2 an already configured project environment to copy.
         * @return this builder to chain calls.
         */
        @objid ("4cabc87f-95a2-4b65-9508-2f1b2c7f9d24")
        @Override
        public Builder withEnvironment(IGProjectEnv configuration2) {
            this.configuration.setModulesCache(configuration2.getModulesCache());
            this.configuration.addMetamodelExtensions(configuration2.getDefaultMetamodelExtensions());
            this.configuration.setRamcCache(configuration2.getRamcCache());
            return this;
        }

        /**
         * @param cache a modules catalog
         * @return this builder to chain calls.
         */
        @objid ("371bbd6d-fea3-42f3-8800-12ac21ebfbcc")
        public Builder withModuleCache(IModuleRTCache cache) {
            this.configuration.setModulesCache(cache);
            return this;
        }

        /**
         * Use the same modules catalog and metamodel fragments as another project.
         * 
         * @param aProject another project. The project must be open.
         * @return this builder to chain calls.
         */
        @objid ("c2abcfc3-d21c-4b89-8a1d-5930639d064c")
        @Override
        public Builder withEnvironmentOf(GProject aProject) {
            this.configuration.setModulesCache(aProject.getModuleCache());
            this.configuration.addMetamodelExtensions(aProject.getEnvironment().getDefaultMetamodelExtensions());
            this.configuration.setRamcCache(aProject.getEnvironment().getRamcCache());
            return this;
        }

        /**
         * Set a project monitor to add immediately on project creation.
         * <br>
         * This monitor will receive events fired while opening the project.
         * The monitor will remain once the project is open.
         * If it is not your intended behavior you have to remove it from the project manually
         * after having opened it.
         * 
         * @param aeventListener a project event monitor
         * @return this builder to chain calls.
         */
        @objid ("f38609a0-8e1f-4db1-896e-7d049c19ec86")
        public Builder withEventListener(IProjectMonitor aeventListener) {
            this.eventListener = aeventListener;
            return this;
        }

        /**
         * Create a GProject from a project descriptor without opening it.
         * <p>
         * Next step is either to synchronize it or opening it.
         * 
         * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call
         * <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be
         * reported and that the operation cannot be cancelled.
         * @return the loaded project.
         * @throws java.io.IOException on failure
         */
        @objid ("7222be4b-d8bb-4d19-ba62-bd3afedd4029")
        public GProject load(IModelioProgress monitor) throws IOException {
            if (this.configuration.getModulesCache() == null) {
                this.configuration.setModulesCache(EmptyModuleCache.getInstance());
            }
            
            IProjectFactory f = GProjectFactory.getProjectFactory(this.projectDescriptor);
            if (f != null) {
                GProject project = f.createProject(this.projectDescriptor);
            
                if (this.eventListener != null) {
                    project.getMonitorSupport().addMonitor(this.eventListener);
                }
            
                // Open project
                project.load(this.projectDescriptor, this.authData, this.configuration, monitor);
                return project;
            } else {
                // Default case
                GProject project = new GProject();
            
                if (this.eventListener != null) {
                    project.getMonitorSupport().addMonitor(this.eventListener);
                }
            
                project.load(this.projectDescriptor, this.authData, this.configuration, monitor);
                return project;
            }
        }

        /**
         * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call
         * <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be
         * reported and that the operation cannot be cancelled.
         * @return the loaded project.
         * @throws java.io.IOException on failure
         */
        @objid ("589297ac-354f-4dae-b085-e73abe887143")
        public GProject open(IModelioProgress monitor) throws IOException {
            SubProgress mon = SubProgress.convert(monitor, 3);
            
            // Laod the project
            GProject project = load(mon.newChild(1));
            
            // Add the event listener
            if (this.eventListener != null) {
                project.getMonitorSupport().addMonitor(this.eventListener);
            }
            
            // Open the project
            project.open(mon.newChild(2));
            return project;
        }

    }

}
