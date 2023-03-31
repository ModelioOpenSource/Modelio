/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.modelio.api.modelio.mc;

import java.io.File;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.modelio.api.module.IPeerModule;
import org.modelio.gproject.ramc.core.packaging.IModelComponentContributor;
import org.modelio.metamodel.uml.statik.Artifact;

/**
 * This interface allows the management of Model Components in the model.<br>
 * Available services are: <li> {@link IModelComponentService#deployModelComponent(File, IProgressMonitor)}: deploy a model component
 * from a file.</li> <li> {@link IModelComponentService#removeModelComponent(IModelComponentDescriptor)}: remove a model component
 * from the model.</li> <li> {@link IModelComponentService#getModelComponents()}: get the list of all model components deployed in
 * the model.</li> <li> {@link IModelComponentService#packageModelComponent(Artifact, Set, File, IProgressMonitor)}: package a new
 * model component file from a model.</li>
 */
@objid ("f65a9a0e-6bf4-11e0-a371-001ec947cd2a")
public interface IModelComponentService {
    /**
     * Delete a deployed model component from the model.
     * @param modelComponent The model component to remove.
     */
    @objid ("81ca27e1-6c30-11e0-b589-002564c97630")
    void removeModelComponent(final IModelComponentDescriptor modelComponent);

    /**
     * Get the list of all model components already deployed in the model.
     * @return a list of {@link IModelComponentDescriptor}. Must not be <code>null</code>.
     */
    @objid ("df41e1ac-7ade-11e0-ac17-001ec947cd2a")
    List<IModelComponentDescriptor> getModelComponents();

    /**
     * Deploy a model component in the model from the given file.
     * @param archive the file containing the model component to deploy (usually ending with ".ramc").
     * @param monitor a monitor for deployment infos. Might be <code>null</code>.
     */
    @objid ("a3dd6920-0ecc-11e2-96c4-002564c97630")
    void deployModelComponent(final File archive, final IProgressMonitor monitor);

    /**
     * Package a model component from the model into a file.<br>
     * Modules might contribute to a model component, adding model extensions (notes, tagged values, stereotypes) and/or files to
     * it.<br>
     * @param mc the model component to package.
     * @param peerModules a list of public interfaces from modules, indicating which module should add content into this model component.
     * @param targetFile the file to contain the model component (usually ending with ".ramc").
     * @param monitor a monitor for deployment infos. Might be <code>null</code>.
     */
    @objid ("a3ddb742-0ecc-11e2-96c4-002564c97630")
    void packageModelComponent(final Artifact mc, final Set<IPeerModule> peerModules, final File targetFile, final IProgressMonitor monitor);

    /**
     * Get model component contributions for given model component corresponding to the peer modules.
     * @param mc the model component to package.
     * @param peerModules a list of public interfaces from modules, indicating which module should add content into this model component.
     * @return a list of {@link IModelComponentContributor}.
     * @since 3.4
     */
    @objid ("457863fa-f5c7-422f-9178-7d62c1292345")
    List<IModelComponentContributor> getContributors(final Artifact mc, final Set<IPeerModule> peerModules);
}

