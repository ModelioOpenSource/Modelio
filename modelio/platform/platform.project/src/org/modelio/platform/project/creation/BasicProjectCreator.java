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
package org.modelio.platform.project.creation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.GProjectCreator;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GProjectDescriptor;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.gproject.env.IGProjectEnv;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.gproject.project.GProject;
import org.modelio.platform.project.plugin.AppProjectCore;
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
 * <li>create the project.conf
 * <li>create a local fragment in the project
 * <li>install modeler module
 * <li>deploy the specified modules
 * <li>deploy the specified RAMC
 * </ul>
 * The project creation task is configured by a {@link BasicProjectCreationDataModel} object defining the chosen required options and values
 */
@objid ("0044845a-cc35-1ff2-a7f4-001ec947cd2a")
public class BasicProjectCreator implements IProjectCreatorDelegate {
    @objid ("c6c9f00b-c73d-4087-84ba-ef9ac7dd0a4e")
    private static final String INFO_DESCRIPTION = "info.description";

    @objid ("235c0e6a-5041-4823-a2f9-408010612d74")
    private List<MRef> diagrams = new ArrayList<>();

    @objid ("007534b0-7310-10b7-9941-001ec947cd2a")
    @Override
    public void createProject(IProjectCreationData creationData, IGProjectEnv configuration, IModelioProgress monitor) throws IOException {
        BasicProjectCreationDataModel data = (BasicProjectCreationDataModel) creationData;
        SubProgress mon = SubProgress.convert(monitor, 10);
        
        String name = data.getProjectName();
        Path projectPath = data.workspace.resolve(name);
        
        // Create an empty IGProject, open it
        GProjectDescriptor projectDescriptor = GProjectCreator.buildEmptyProject(name, projectPath);
        
        // Create and register at least one local fragment named from the project name
        GProjectPartDescriptor localModelFragmentDescriptor = new GProjectPartDescriptor(GProjectPartType.EXMLFRAGMENT, name, null, DefinitionScope.LOCAL);
        projectDescriptor.getPartDescriptors().add(localModelFragmentDescriptor);
        
        // Register modules
        for (IModuleHandle mh : data.getModuleHandles()) {
            GProjectPartDescriptor module = new GProjectPartDescriptor(GProjectPartType.MODULE, mh.getName(), mh.getVersion(), DefinitionScope.LOCAL);
            module.setLocation(mh.getArchive().toUri());
            projectDescriptor.getPartDescriptors().add(localModelFragmentDescriptor);
            mon.setWorkRemaining((1 + data.getModuleHandles().size()) * 2);
        }
        
        IGProject project = GProject.newBuilder(projectDescriptor).withEnvironment(configuration).build(monitor);
        
        // Add project description
        project.getProperties().setProperty(BasicProjectCreator.INFO_DESCRIPTION, data.getProjectDescription(), DefinitionScope.LOCAL);
        
        // Create a initial model
        project.open(monitor);
        createInitialModel(project, name);
        project.save(mon);
        project.close();
        
    }

    @objid ("cde8bf09-9636-49a5-8113-d98f394c07c4")
    private void createInitialModel(IGProject project, String localfragmentName) {
        ICoreSession session = project.getSession();
        try (ITransaction t = session.getTransactionSupport().createTransaction("initial model")) {
            IGModelFragment fragment = project.getParts(IGModelFragment.class).stream()
                    .filter((f) -> {
                        return Objects.equals(f.getId(), localfragmentName);
                    }).findFirst().get();
        
            if (fragment != null) {
                MTools.get(session).getPopulator().populate(localfragmentName, session, fragment.getRepository());
            }
            t.commit();
        }
        
    }

    /**
     * Return the diagrams that ought to be opened when first opening the project right after its creation
     * @return the diagrams to open.
     */
    @objid ("8716539d-59a0-4e88-8366-8d78d836652f")
    public List<MRef> getDiagramsToOpen() {
        return this.diagrams;
    }

    /**
     * Find the ModelerModule, if not found : abort as this module is mandatory.
     * @param moduleCache the modules cache
     * @param monitor a progress monitor
     * @return the ModelerModule handle
     * @throws IOException in case of failure
     * @throws FileSystemException in case of file system error
     */
    @objid ("3e9074cc-ba08-40be-ac20-9f2456c7e329")
    private IModuleHandle getModelerModule(IModuleRTCache moduleCache, IModelioProgress monitor) throws IOException, FileSystemException {
        IModuleHandle modelerModule = moduleCache.findModule("ModelerModule", /*
                                                                               * latest version
                                                                               */null, monitor);
        if (modelerModule == null) {
            final String errMsg = AppProjectCore.I18N.getString("ModelerModuleMissing.Message");
            throw new FileNotFoundException(errMsg);
        }
        return modelerModule;
    }

}
