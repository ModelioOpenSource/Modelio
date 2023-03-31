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
package org.modelio.gproject.core;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.IFragmentInfos;
import org.modelio.gproject.parts.IGModelFragmentMigrator;
import org.modelio.gproject.parts.fragment.AbstractGModelFragment;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;

/**
 * This interface adds specific features of model fragments in a Modelio project.
 * 
 * <p>
 * A project model fragment may contain model elements and files. Its content may be available locally or be located on a distant server. A model fragment must be mounted in order for its content to be accessible.
 * <p>
 * This interface should not be directly implemented. Extends {@link AbstractGModelFragment} instead.
 * 
 * @since 5.2
 */
@objid ("f2766b2b-7300-497a-bdd4-677329e46acc")
public interface IGModelFragment extends IGPart {
    /**
     * Property that tells the fragment is read only.
     * <p>
     * This property must be supported by all fragment implementations.
     */
    @objid ("b9b04627-3f94-483c-878f-a8fc496065a3")
    public static final String PROP_READ_ONLY = "readonly";

    /**
     * @return the model repository
     */
    @objid ("a2cef7d7-d904-4438-b228-97806aba0c72")
    IRepository getRepository();

    /**
     * Get the root elements of the fragment.
     * @return the root elements of the fragment.
     */
    @objid ("74579da3-490e-42a5-b22b-ba52fd8895af")
    Collection<MObject> getRoots();

    /**
     * Get the service that will migrate the fragment to the current metamodel version.
     * @param targetMetamodel the target metamodel
     * @return the fragment migration service.
     * @throws IOException if migration fails.
     */
    @objid ("9a937bdf-cc69-41cd-8a16-e94283c16a24")
    IGModelFragmentMigrator getMigrator(MetamodelVersionDescriptor targetMetamodel) throws IOException;

    /**
     * Reconfigure the fragment from the given descriptor.
     * <p>
     * The given fragment will have the same ID and type. It may have a different URI. The fragment may choose to update, unmount and remount or even to remove itself and install a new one.
     * @param fd the new fragment configuration
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @throws GPartException if the fragment can't be reconfigured.
     */
    @objid ("84181c1b-dcbc-450f-a558-b177b13341fe")
    void reconfigure(GProjectPartDescriptor fd, IModelioProgress monitor) throws GPartException;

    /**
     * Rename the fragment, including the part on the disk.
     * @param name the new fragment name. If equals to the current id, calling this method does nothing.
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @throws IOException if the fragment directory could not be renamed on the disk.
     * @throws GPartException if the fragment can't be renamed.
     */
    @objid ("3cb9b55f-5ec4-46c7-b609-1564fae0a67c")
    void rename(String name, IModelioProgress monitor) throws IOException, GPartException;

    /**
     * Get the fragment informations such as the version, the description...
     * @return the fragment informations.
     * @throws IOException in case of I/O failure.
     */
    @objid ("5b9b6d90-97fe-43be-a317-07e79ee08c0d")
    IFragmentInfos getInformations() throws IOException;

    /**
     * Get the fragment required metamodel as a descriptor.
     * @return the required metamodel fragments.
     * @throws IOException in case of I/O failure reading the version
     */
    @objid ("c2238cb9-20e8-4b9e-97a1-4e2d93d31c45")
    MetamodelVersionDescriptor getRequiredMetamodelDescriptor() throws IOException;

    /**
     * Get the fragment runtime directory.
     * <p>
     * The runtime directory contains files that can be deleted at any time without breaking the fragment.
     * @return the fragment runtime directory.
     */
    @objid ("3f4a5079-792e-4a40-9aa1-aec625454bf2")
    Path getRuntimeDirectory();

    /**
     * Get the fragment data directory.
     * <p>
     * The data directory contains files that are mandatory for the fragment.
     * @return the fragment data directory.
     */
    @objid ("60c7505e-22c0-46c4-81a9-9896e352748a")
    Path getDataDirectory();
}

