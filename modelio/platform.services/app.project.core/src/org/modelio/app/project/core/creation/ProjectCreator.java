/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.app.project.core.creation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.app.project.core.plugin.AppProjectCore;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.fragment.exml.ExmlFragmentFactory;
import org.modelio.gproject.gproject.FragmentConflictException;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.GProjectCreator;
import org.modelio.gproject.gproject.GProjectFactory;
import org.modelio.gproject.gproject.IGProjectEnv;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class is in charge of creating a new Modelio project.<br>
 * The project creation task carries out the followings:
 * <ul>
 * <li>create the project
 * <li>create alocal fragment in the project
 * <li>install modeler module
 * <li>deploy the specified modules
 * <li>deploy the specified RAMC
 * </ul>
 * The project creation task is configured by a {@link ProjectCreationDataModel}
 * object defining the chosen required options and values
 */
@objid ("0044845a-cc35-1ff2-a7f4-001ec947cd2a")
public class ProjectCreator implements IProjectCreator {
    @objid ("c6c9f00b-c73d-4087-84ba-ef9ac7dd0a4e")
    private static final String INFO_DESCRIPTION = "info.description";

    @objid ("235c0e6a-5041-4823-a2f9-408010612d74")
    private List<MRef> diagrams = new ArrayList<>();

/*
     * (non-Javadoc)
     * @see
     * org.modelio.app.project.core.creation.IProjectCreator#createProject(org
     * .modelio.app.project.core.creation. ProjectCreationDataModel,
     * org.modelio.gproject.module.IModuleCatalog,
     * org.eclipse.core.runtime.IProgressMonitor)
     */
    @objid ("007534b0-7310-10b7-9941-001ec947cd2a")
    @Override
    public void createProject(IProjectCreationData creationData, IGProjectEnv configuration, IModelioProgress monitor) throws IOException {
        ProjectCreationDataModel data = (ProjectCreationDataModel) creationData;
        SubProgress mon = SubProgress.convert(monitor, 10);
        
        String name = data.getProjectName();
        Path projectPath = data.workspace.resolve(name);
        
        IModuleHandle modelerModule = getModelerModule(configuration.getModulesCache(), mon.newChild(1));
        
        // Create an empty GProject, open it
        ProjectDescriptor projectDescriptor = GProjectCreator.buildEmptyProject(name, projectPath);
        
        GProject project = GProjectFactory
                .from(projectDescriptor)
                .withEnvironment(configuration)
                .open(mon.newChild(1));
        
        // Create and register at least one local fragment
        IProjectFragment fragment = ExmlFragmentFactory.instantiateLocal(name);
        try {
            project.registerFragment(fragment, mon.newChild(1));
        } catch (FragmentConflictException e) {
            // This should never happen here
            throw new IOException(e.getLocalizedMessage(), e);
        }
        
        // Install modules
        mon.setWorkRemaining((1 + data.getModuleHandles().size()) * 2);
        project.installModule(modelerModule, modelerModule.getArchive() != null ? modelerModule.getArchive().toUri() : null, mon.newChild(1));
        
        for (IModuleHandle moduleHandle : data.getModuleHandles()) {
            project.installModule(moduleHandle, moduleHandle.getArchive() != null ? moduleHandle.getArchive().toUri() : null, mon.newChild(1));
        }
        
        // Add project description
        project.getProperties().setProperty(ProjectCreator.INFO_DESCRIPTION, data.getProjectDescription(), DefinitionScope.LOCAL);
        
        // Create a initial model
        ICoreSession session = project.getSession();
        createInitialModel(name, session, fragment);
        
        project.save(mon); // _mon_ : give remaining of progress to save()
        project.close();
    }

    @objid ("cde8bf09-9636-49a5-8113-d98f394c07c4")
    private void createInitialModel(String name, ICoreSession session, IProjectFragment fragment) {
        try (ITransaction t = session.getTransactionSupport().createTransaction("initial model")) {
            MTools.get(session).getPopulator().populate(name, session, fragment.getRepository());
            /*
             * IStandardModelFactory factory = MTools.get(session).getModelFactory().getFactory(IStandardModelFactory.class);
             * for (Package model : ((Project) root).getModel()) {
             * ClassDiagram d = factory.createClassDiagram();
             * d.setOrigin(model);
             * d.setName("overview diagram");
             * this.diagrams.add(new MRef(d));
             * }
             */
        
            t.commit();
        }
    }

    /**
     * Return the diagrams that ought to be opened when first opening the project right after its creation
     * 
     * @return the diagrams to open.
     */
    @objid ("8716539d-59a0-4e88-8366-8d78d836652f")
    public List<MRef> getDiagramsToOpen() {
        return this.diagrams;
    }

    /**
     * Find the ModelerModule, if not found : abort as this module is mandatory.
     * 
     * @param moduleCache the modules cache
     * @param monitor a progress monitor
     * @return the ModelerModule handle
     * @throws java.io.IOException in case of failure
     * @throws java.nio.file.FileSystemException in case of file system error
     */
    @objid ("3e9074cc-ba08-40be-ac20-9f2456c7e329")
    private IModuleHandle getModelerModule(IModuleRTCache moduleCache, IModelioProgress monitor) throws FileSystemException, IOException {
        IModuleHandle modelerModule = moduleCache.findModule("ModelerModule", /*
                                                                               * latest
                                                                               * version
                                                                               */null, monitor);
        if (modelerModule == null) {
            final String errMsg = AppProjectCore.I18N.getString("ModelerModuleMissing.Message");
            throw new FileNotFoundException(errMsg);
        }
        return modelerModule;
    }

}
