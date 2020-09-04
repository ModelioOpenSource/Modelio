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

package org.modelio.gproject.fragment;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.fragment.migration.IFragmentMigrator;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.GProjectEvent;
import org.modelio.gproject.gproject.GProjectEventType;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;

/**
 * Represents a fragment of the project.
 * <p>
 * A project fragment may contain model elements and files.
 * Its content may be available locally or be located on a distant server.
 * A fragment must be mounted in order for its content to be accessible.
 * <p>
 * This interface should not be directly implemented. Please extends {@link AbstractFragment} instead.
 */
@objid ("225ed21f-8ed5-11e1-be7e-001ec947ccaf")
public interface IProjectFragment {
    /**
     * Property that tells the fragment is read only.
     * <p>
     * This property must be supported by all fragment implementations.
     */
    @objid ("c0e73380-379f-4cd3-b33f-cb685cc2f3b4")
    public static final String PROP_READ_ONLY = "readonly";

    /**
     * Delete the fragment from the disk.
     * @throws java.io.IOException if the fragment could not completely be removed from disk.
     */
    @objid ("d10c42db-9849-11e1-ac83-001ec947ccaf")
    void delete() throws IOException;

    /**
     * Get the fragment authentication configuration if needed.
     * May be <code>null</code>.
     * @return authentication data.
     */
    @objid ("3da5c624-a576-4396-8f7d-4e2082ec9617")
    GAuthConf getAuthConfiguration();

    /**
     * Get the error that caused the fragment to be in the down state.
     * @return the down state cause.
     */
    @objid ("6a83f283-d66d-11e1-9f03-001ec947ccaf")
    Throwable getDownError();

    /**
     * @return the fragment identifier.
     */
    @objid ("225ed221-8ed5-11e1-be7e-001ec947ccaf")
    String getId();

    /**
     * Get the service that will migrate the fragment to the current metamodel version.
     * @param targetMetamodel the target metamodel
     * @return the fragment migration service.
     * @throws java.io.IOException if the migration is not possible
     */
    @objid ("416d6dfa-51b6-43b1-bf7c-3ba566c97794")
    IFragmentMigrator getMigrator(MetamodelVersionDescriptor targetMetamodel) throws IOException;

    /**
     * Get the fragment specific properties.
     * <p>
     * The available properties are implementation dependent.
     * @return the fragment properties.
     */
    @objid ("49c0ef8e-ab3f-11e1-8392-001ec947ccaf")
    GProperties getProperties();

    /**
     * @return the model repository
     */
    @objid ("c1811634-95da-11e1-ac83-001ec947ccaf")
    IRepository getRepository();

    /**
     * Get the fragment required metamodel as a descriptor.
     * @return the required metamodel fragments.
     * @throws java.io.IOException in case of I/O failure reading the version
     */
    @objid ("de579a41-ffb4-48f7-9ef6-592270f353d7")
    MetamodelVersionDescriptor getRequiredMetamodelDescriptor() throws IOException;

    /**
     * Get the root elements of the fragment.
     * @return the root elements of the fragment.
     */
    @objid ("573e06b7-c1f7-11e1-8e7a-001ec947ccaf")
    Collection<MObject> getRoots();

    /**
     * Get the fragment definition scope: local or shared by a server.
     * @return the fragment scope.
     */
    @objid ("6376a977-3004-11e2-8f81-001ec947ccaf")
    DefinitionScope getScope();

    /**
     * Get the state of the fragment.
     * @return the fragment state.
     */
    @objid ("6a83f280-d66d-11e1-9f03-001ec947ccaf")
    FragmentState getState();

    /**
     * Get the fragment type identifier.
     * <p>
     * The fragment type is used on persistence to determine the fragment class to instantiate.
     * @return the fragment type.
     */
    @objid ("13a7bf99-9a85-11e1-ac83-001ec947ccaf")
    FragmentType getType();

    /**
     * Get the project mount URI.
     * @return the mounted URI or null if not mounted.
     */
    @objid ("d10c42dd-9849-11e1-ac83-001ec947ccaf")
    URI getUri();

    /**
     * Mount the fragment so that it is usable.
     * @param project the project where the fragment is mount.
     * @param aMonitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call
     * <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     */
    @objid ("225ed222-8ed5-11e1-be7e-001ec947ccaf")
    void mount(IModelioProgress aMonitor);

    /**
     * Reconfigure the fragment from the given descriptor.
     * <p>
     * The given fragment will have the same ID and type.
     * It may have a different URI.
     * The fragment may choose to update, unmount and remount or even to remove itself and install
     * a new one.
     * @param fd the new fragment configuration
     * @param aMonitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call
     * <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     */
    @objid ("925c2ea7-c4f6-4959-911c-52214fcce935")
    void reconfigure(FragmentDescriptor fd, IModelioProgress aMonitor);

    /**
     * Set the fragment in "down" state, with the cause.
     * <p>
     * Fires a {@link GProjectEventType#FRAGMENT_DOWN FRAGMENT_DOWN} {@link GProjectEvent event}.
     * @param error the cause of down state
     */
    @objid ("5b8b018a-e45c-46b5-a424-4f9a48b38ddd")
    void setDown(Throwable error);

    /**
     * initialize the project owning this fragment.
     * <p>
     * This method is called just after the fragment instantiation.
     * @param project the project where the fragment will be mount.
     */
    @objid ("a958715e-54f6-4abe-9119-a77d1fa76bd3")
    void setProject(GProject project);

    /**
     * Release all resources allocated by the fragment.
     */
    @objid ("225ed223-8ed5-11e1-be7e-001ec947ccaf")
    void unmount();

    /**
     * Rename the fragment, including the part on the disk.
     * @param name the new fragment name. If equals to the current id, calling this method does nothing.
     * @param aMonitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call
     * <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @throws java.io.IOException if the fragment directory could not be renamed on the disk.
     */
    @objid ("16de4b2d-aede-42cb-b699-3c4eb10cb0e2")
    void rename(String name, IModelioProgress aMonitor) throws IOException;

}
