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
import java.net.URISyntaxException;
import java.nio.file.FileSystemException;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.GProblem;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.data.project.GProjectDescriptor;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.project.ProjectFileStructure;
import org.modelio.gproject.data.project.ProjectType;
import org.modelio.gproject.data.project.auth.AuthDescriptor;
import org.modelio.gproject.env.IGProjectEnv;
import org.modelio.gproject.monitor.GProjectMonitorSupport;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.model.spi.IGMetamodelExtension;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("7603934e-63ba-494d-88c6-f0ffe3bf2185")
public interface IGProject {
    /**
     * Add a GPartDescriptor to the project descriptors.
     * 
     * Project state must be NEW.
     * @param descriptor the part to add
     * @throws GPartException on failure
     */
    @objid ("4b277619-dc5b-40f0-80e5-623ebe2cbff8")
    void addGPartDescriptor(GProjectPartDescriptor descriptor) throws GPartException;

    /**
     * Remove a GPartDescriptor from the project descriptors.
     * 
     * Project state must be NEW.
     * @param monitor a progress monitor for file deletions.
     * @param partDescriptor the part to remove
     * @throws GPartException on failure
     */
    @objid ("f78c2e37-3002-4bea-89f0-97bd8885587e")
    void removeGPartDescriptor(IModelioProgress monitor, GProjectPartDescriptor partDescriptor) throws GPartException;

    /**
     * Add a GPart to an opened project.
     * The part:<ol>
     * <li>is added from 'gparts'</li>
     * <li>is mounted first</li>
     * <li>is added to descriptor if 'permanent' is true</li>
     * </ol>
     * Project state must be OPENING or OPENED.
     * @param monitor a progress monitor
     * @param gPart the part to add
     * @param permanent if true the part presence is persisted on save.
     * @throws GPartException on failure
     */
    @objid ("61c817a7-f90a-4360-9cf8-52cdf9df92dd")
    void addGPart(IModelioProgress monitor, IGPart gPart, boolean permanent) throws GPartException;

    /**
     * @deprecated Use {@link #addGPart(IModelioProgress, IGPart, boolean)}
     */
    @objid ("d8515929-f58a-47ff-8293-f7d5a407fede")
    @SuppressWarnings ("javadoc")
    @Deprecated
    void addGPart(IGPart gPart, boolean permanent) throws GPartException;

    /**
     * Remove a GPart from an opened project.
     * The part:<ol>
     * <li>is unmounted first</li>
     * <li>is removed from 'gparts'</li>
     * <li>is removed from descriptor</li>
     * </ol>
     * Project state must either OPENING or OPENED.
     * @param gPart the part to remove
     * @throws GPartException on failure
     * @deprecated use {@link #removeGPart(IModelioProgress, IGPart)}
     */
    @objid ("f652ca0a-52f5-457c-aa4f-0f9404b11796")
    @Deprecated
    void removeGPart(IGPart gPart) throws GPartException;

    /**
     * Remove a GPart from an opened project.
     * The part:<ol>
     * <li>is unmounted first</li>
     * <li>is removed from 'gparts'</li>
     * <li>is removed from descriptor</li>
     * </ol>
     * Project state must either OPENING or OPENED.
     * @param monitor a progress monitor.
     * @param gPart the part to remove
     * @throws GPartException on failure
     */
    @objid ("f675cff1-56e3-4208-9024-8ec07ea63c55")
    void removeGPart(IModelioProgress monitor, IGPart gPart) throws GPartException;

    @objid ("6cf0fcf0-b755-4db1-8558-6853bd9e69dc")
    void setRemoteLocation(String remoteLocation) throws URISyntaxException;

    @objid ("3f7b2157-165a-4734-871f-5eed98675afc")
    void setProperties(GProperties gProperties);

    @objid ("5f37a6f6-0f1e-46b0-a77c-d739fe54b44b")
    void setName(String name);

    @objid ("a2b87085-64a8-4390-ac60-e58dc43dc584")
    void save(IModelioProgress progress) throws IOException;

    @objid ("074fa406-8a36-43ff-b5ae-54f437e2cc74")
    void open(IModelioProgress aProgress) throws IOException, FileSystemException;

    @objid ("1c832593-86cf-4123-987b-7c4869b3fac4")
    List<GProblem> getProblems();

    @objid ("db08ee16-8d04-4355-9ae4-6368287cf51c")
    boolean isOpen();

    @objid ("13aa058e-7905-4e91-a062-8bd3fbdc12bf")
    ProjectType getType();

    @objid ("c0d0277c-a77d-4960-8539-4aa69d17cc9e")
    IGProjectState getState();

    @objid ("7592a6b6-7d3a-4ac8-b08f-72fec9bf3f41")
    String getRemoteLocation();

    @objid ("e23f3f70-722f-40d4-b015-f3ade7356cf9")
    GProperties getProperties();

    @objid ("028d4bf5-2eb6-4a08-85aa-b30c40ba1769")
    IGProjectEnv getProjectEnvironment();

    @objid ("d41b89e6-41f7-4c13-99dc-ff9d1c2472d4")
    void close();

    @objid ("e14504c2-1a8f-40e4-a4fa-8eb4b807b4fe")
    AuthDescriptor getAuth();

    @objid ("bfdbe063-f777-4f01-ae79-45a9645d09f4")
    GProjectDescriptor getDescriptor();

    @objid ("19404c5e-3b1b-47b4-9085-d52611d1b848")
    Version getExpectedModelioVersion();

    @objid ("434756a9-23f9-464c-9241-21779b0333c7")
    GProjectMonitorSupport getMonitorSupport();

    @objid ("b30c52a2-60f5-45c0-9ad4-e9073d2b0b1f")
    String getName();

    @objid ("b499468f-6eff-40ff-9f75-9cce73975895")
    ProjectFileStructure getPfs();

    @objid ("f29f633f-224e-4212-ac12-6a7e0ffb8ac4")
    <T extends IGPart> List<T> getParts(Class<T> type);

    @objid ("05f9763e-04c4-4cf1-995d-a2faa97db583")
    List<IGPart> getParts();

    @objid ("9972eb0c-cac3-47d6-9cbb-66764922d97e")
    ICoreSession getSession();

    @objid ("8afa47de-f0e0-47b1-bdda-0f63b932b5b5")
    Collection<IGMetamodelExtension> getMetamodelExtensions();

    @objid ("12de4524-a38e-4824-8d35-a3cc864795f1")
    IGModelFragment getFragment(MObject obj);

    @objid ("72aad93c-c9e9-44d4-b07a-6b18c6d7cd8d")
    <T extends IGPart> T getPart(String partId, java.lang.Class<T> partType);
}

