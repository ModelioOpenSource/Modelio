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
package org.modelio.gproject.project;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.GProblem;
import org.modelio.gproject.core.IGPart;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.core.IGProjectState.GProjectStateEnum;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GProjectDescriptor;
import org.modelio.gproject.data.project.GProjectDescriptorWriter;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.project.ProjectFileStructure;
import org.modelio.gproject.data.project.ProjectType;
import org.modelio.gproject.data.project.auth.AuthDescriptor;
import org.modelio.gproject.env.GProjectEnvironment;
import org.modelio.gproject.env.IGProjectEnv;
import org.modelio.gproject.lock.ProjectLock;
import org.modelio.gproject.module.EmptyModuleCache;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.gproject.monitor.GProjectEvent;
import org.modelio.gproject.monitor.GProjectMonitorSupport;
import org.modelio.gproject.monitor.IProjectMonitor;
import org.modelio.gproject.mtools.AuthTool;
import org.modelio.gproject.mtools.ModelTool;
import org.modelio.gproject.parts.GPartFactory;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.collections.TopologicalSorter;
import org.modelio.vbasic.collections.TopologicalSorter.CyclicDependencyException;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.NullProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.model.spi.IGMetamodelExtension;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.version.ModelioVersion;

/**
 * GProject new design
 * 
 * @since 5.2
 */
@objid ("8299b20c-7440-401f-9e17-3d2c1925615d")
public class GProject extends AbstractGProject {
    /**
     * The serializable project descriptor
     */
    @objid ("fc334f8b-a8f0-41e0-8e80-59f32277370f")
    private final GProjectDescriptor descriptor;

    /**
     * The project runtime parts
     */
    @objid ("57158071-2bb7-4aa4-a241-b0adbdb380b8")
    private final List<IGPart> gParts = new CopyOnWriteArrayList<>();

    /**
     * Project monitoring support.<br/>
     * Holds a list of registered monitors for the project.<br/>
     * The monitors will receive {@link GProjectEvent} project configuration events that indicate modifications of the project state.
     */
    @objid ("6b235d67-ad2b-43da-bf1a-f0275cf8b31d")
    private final GProjectMonitorSupport monitorSupport = new GProjectMonitorSupport();

    /**
     * The file structure of the project space of this project
     */
    @objid ("f66d58b3-f45d-40f8-9f6f-8f7e33008780")
    private ProjectFileStructure pfs;

    /**
     * Project environment, ie the caches
     */
    @objid ("95e41ed9-8d9e-439f-81f0-4769607acb24")
    private GProjectEnvironment projectEnvironment;

    @objid ("d4444df5-cce7-4242-9694-dd1ce19a6bfe")
    private final GProjectState state;

    @objid ("a05f5e29-cdc7-4253-86cb-a1e3307adcba")
    private ProjectLock projectLock;

    @objid ("0de1626b-5d81-4940-b53a-85a283c8d2ef")
    @Override
    public void addGPart(IGPart gPart, boolean permanent) throws GPartException {
        addGPart(null, gPart, permanent);
    }

    @objid ("ec3f480c-3ae5-47d4-b052-c5bd82ce7d9c")
    private IGPart findPart(String id, GProjectPartType type) {
        for (IGPart part : this.gParts) {
            if (part.getId().equals(id) && part.getType().equals(type)) {
                return  part;
            }
        }
        return null;
    }

    @objid ("7b6b12fb-894d-4f95-8116-bb07e2dee6cf")
    private void checkDuplicatePart(GProjectPartDescriptor gPart) throws GPartException {
        IGPart existingPart = findPart(gPart.getId(), gPart.getType());
        if (existingPart != null) {
            throw new GPartException(String.format("Cannot add %s %s %s : The project already have a %s %s %s .",
                    gPart.getType(), gPart.getId(), gPart.getVersion(),
                    existingPart.getType(), existingPart.getId(), existingPart.getVersion()));
        }
        
    }

    @objid ("c4d5e08d-9906-40d2-bf70-1003074db21d")
    @Override
    public void addGPart(IModelioProgress monitor, IGPart gPart, boolean permanent) throws GPartException {
        GProjectStateEnum s = this.state.getValue();
        if (s != GProjectStateEnum.OPENING && s != GProjectStateEnum.OPENED) {
            // Wrong state, throw programming error
            throw new IllegalStateException(String.format("Cannot add part %s when project is not opened (state=%s)...", gPart.getId(), s));
        }
        
        checkDuplicatePart(gPart.getDescriptor());
        
        // Add the part and mount it
        // Note: the gpart mount() method will manage the part state and fire proper project events
        this.gParts.add(gPart);
        SubProgress mon = SubProgress.convert(monitor, 5);
        gPart.install(this, mon.newChild(1));
        gPart.mount(mon.newChild(1));
        
        if (permanent) {
            // Remove existing part with the same id
            //this.descriptor.getPartDescriptors().removeIf(d -> d.matches(gPart.getDescriptor()));
        
            // Update the descriptor
            this.descriptor.getPartDescriptors().add(gPart.getDescriptor());
        }
        
    }

    @objid ("091f8f15-5efb-4cee-834b-aa18af5c2e41")
    @Override
    public void addGPartDescriptor(GProjectPartDescriptor newPartDesc) throws GPartException {
        GProjectStateEnum s = this.state.getValue();
        switch (s) {
        case NEW:
        case SESSIONUP: {
            checkDuplicatePart(newPartDesc);
            this.descriptor.getPartDescriptors().add(newPartDesc);
            IGPart gPart = GPartFactory.getInstance().instantiate(newPartDesc);
            this.gParts.add(gPart);
            break;
        }
        case OPENED:
        case OPENING: {
            checkDuplicatePart(newPartDesc);
            IGPart gPart = GPartFactory.getInstance().instantiate(newPartDesc);
            addGPart(gPart, true);
            break;
        }
        case INITIAL:
        default:
            throw new IllegalStateException(String.format("Cannot add part %s when project is in state=%s...", newPartDesc.getId(), s));
        }
        
    }

    @objid ("e4b84d7e-634a-4aba-ae90-068c0e791a7a")
    private void lockProject() throws IOException {
        Files.createDirectories(this.pfs.getProjectRuntimePath());
        
        this.projectLock = ProjectLock.get(this.pfs.getProjectRuntimePath(), getName());
        this.projectLock.lock();
        
    }

    /**
     * Close the project and release the resources.
     */
    @objid ("07defca0-e01b-4f90-93a6-5c218f2f9254")
    @Override
    public void close() {
        // Unmount all parts
        unmountParts(new NullProgress());
        
        // Close the session
        super.close();
        
        // Release the lock
        releaseLock();
        
    }

    @objid ("3f5eae4e-d1e3-47f2-99f5-5baa7cba672b")
    private void releaseLock() {
        if (this.projectLock != null) {
            try {
                this.projectLock.close();
                this.projectLock = null;
            } catch (IOException e) {
                getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(this, e));
            }
        }
        
    }

    @objid ("53b2e404-be0d-4ebe-bb06-17cd9b176db6")
    @Override
    public AuthDescriptor getAuth() {
        return this.descriptor.getAuthDescriptor();
    }

    /**
     * Get this project descriptor.
     * <p>
     * <b>Note</b> The returned value is NOT a clone and that modifications should not be made in the returned instance !
     * @return this project descriptor.
     */
    @objid ("83563468-de7e-49e5-9f14-a95f5c4f848f")
    @Override
    public GProjectDescriptor getDescriptor() {
        return this.descriptor;
    }

    @objid ("54a4a7b9-2b7f-4326-81e8-edec17e2a536")
    @Override
    public Version getExpectedModelioVersion() {
        return this.descriptor.getModelioVersion();
    }

    @objid ("d23233b6-ab77-4d12-9f4b-86db5dad5b5a")
    @Override
    public GProjectMonitorSupport getMonitorSupport() {
        return this.monitorSupport;
    }

    /**
     * Get the project's name.
     * @return the project's name.
     */
    @objid ("5ed45cf6-8553-4c95-b8bf-df3643f898ec")
    @Override
    public String getName() {
        return this.descriptor.getName();
    }

    /**
     * Get the parts of exact type 'type'
     * @param <T> the requested parts type
     * @return the matching parts
     */
    @objid ("c929d2c1-ee7a-4d1b-a219-9f27697b6a5b")
    @Override
    public <T extends IGPart> List<T> getParts(java.lang.Class<T> type) {
        return this.gParts.stream()
                .filter(p -> type == null || type.isAssignableFrom(p.getClass()))
                .map(type::cast)
                .collect(Collectors.toList());
        
    }

    @objid ("cc84ec32-f2c3-4074-ac70-417220d06bb3")
    @Override
    public <T extends IGPart> T getPart(String partId, java.lang.Class<T> partType) {
        Objects.requireNonNull(partType);
        return this.gParts.stream()
                .filter(p -> (p.getId().equals(partId))
                        && (partType.isAssignableFrom(p.getClass())))
                .map(partType::cast)
                .findFirst()
                .orElse(null);
        
    }

    @objid ("92358812-00c9-4c87-844d-959f7e9af171")
    @Override
    public ProjectFileStructure getPfs() {
        return this.pfs;
    }

    @objid ("934ccea3-7deb-4f29-8e3e-4dffb1fa331f")
    @Override
    public GProjectEnvironment getProjectEnvironment() {
        return this.projectEnvironment;
    }

    /**
     * Get the properties stored in the project.
     * @return the current set of properties.
     */
    @objid ("67e21b54-11c0-4816-a0cc-79c995f7cb1d")
    @Override
    public GProperties getProperties() {
        return this.descriptor.getProperties();
    }

    /**
     * Get the project remote location.
     * <p>
     * Returns <code>null</code> for local projects.
     * @return the project remote location.
     */
    @objid ("4ce3663a-a857-4d63-900a-a8ae9a923150")
    @Override
    @SuppressWarnings ("static-method")
    public String getRemoteLocation() {
        return this.descriptor.getRemoteLocation();
    }

    @objid ("00a7f19b-32d6-4c21-923e-238c16aecf9a")
    @Override
    public GProjectState getState() {
        return this.state;
    }

    /**
     * Get the project type.
     * <p>
     * This method should be redefined by subclasses.
     * @return the project type.
     */
    @objid ("57204679-008e-4ebf-99c3-b6922ba601ae")
    @Override
    public ProjectType getType() {
        String type = this.descriptor.getType();
        try {
            return ProjectType.valueOf(type);
        } catch (IllegalArgumentException e) {
            Log.error("Invalid project type '%s', assume it is a local project.", type);
            Log.trace(e);
            return ProjectType.LOCAL;
        }
        
    }

    /**
     * Indicates whether or not the project is opened.
     * @return true if the project is open.
     */
    @objid ("a67033a8-a777-40c4-8c8d-58813aea9499")
    @Override
    public boolean isOpen() {
        return this.session != null;
    }

    /**
     * Instantiates a GProject builder.
     * <p>
     * Mandatory entry point to instantiate a {@link GProject}.
     * @param projectDescriptor the project descriptor
     * @return a builder
     */
    @objid ("aecb75d4-8b31-4d32-9abe-782fc197d153")
    public static GProject2Builder newBuilder(GProjectDescriptor projectDescriptor) {
        return new GProject2Builder(projectDescriptor);
    }

    /**
     * Open the project.
     * <p>
     * @param aProgress a progress monitor
     * @throws IOException in case of I/O error preventing the project from being open.
     * @throws FileSystemException in case of file system I/O error preventing the project from being open.
     */
    @objid ("9ad3a474-e5a0-4907-8419-53a4c51ab6f3")
    @Override
    public void open(IModelioProgress aProgress) throws IOException, FileSystemException {
        SubProgress progress = SubProgress.convert(aProgress, 80);
        
        // Check project is not already opened
        checkNotOpen();
        
        // Check Modelio version
        Version modelioVersion = this.descriptor.getModelioVersion();
        if (!ModelioVersion.isCompatible(modelioVersion)) {
            String msg;
            if (modelioVersion == null) {
                msg = CoreProject.I18N.getMessage(
                        "GProject.projectTooOld",
                        getName(),
                        ModelioVersion.MAJOR_MINOR.toString("V.R"));
            } else {
                msg = CoreProject.I18N.getMessage(
                        "GProject.modelioTooOld",
                        getName(),
                        modelioVersion.toString("V.R"),
                        ModelioVersion.MAJOR_MINOR.toString("V.R"));
            }
            throw new IOException(msg);
        } else {
            // Upgrade project version to current Modelio version
            setModelioVersion(ModelioVersion.MAJOR_MINOR);
        }
        
        // lock the project
        lockProject();
        
        boolean ok = false;
        try {
            // Open the session
            this.session = new CoreSession();
            this.state.sendSessionUp(progress.newOptionalChild(10));
            progress.setWorkRemaining(60);
        
            // Mount the metamodel fragments required by the project
            mountMetamodelFragments(this.session, this.projectEnvironment.getDefaultMetamodelExtensions());
            progress.worked(20);
        
            // Mount the internally used default repository
            this.session.mountNSURepository(this.pfs.getNsUseRepositoryPath(), progress.newChild(20));
        
            this.state.sendOpening(progress.newOptionalChild(10));
            progress.setWorkRemaining(50);
        
            // Install the parts
            installParts(progress.newChild(20));
        
            // Mount the project parts
            mountParts(progress.newChild(20));
        
            this.state.sendOpened(progress.newOptionalChild(10));
        
            ok = true;
        } catch (CyclicDependencyException e) {
            this.problems.add(new GProblem(getDescriptor(), e));
        } finally {
            if (!ok) {
                if (this.session != null) {
                    this.session.close();
                    this.session = null;
                }
        
                if (this.projectLock != null) try {
                    this.projectLock.close();
                    this.projectLock = null;
                } catch (IOException | RuntimeException e) {
                    Log.trace(e);
                }
            }
        }
        
    }

    @objid ("105f9117-37dc-4768-b287-ef8ed7b3e5f6")
    protected void mountParts(IModelioProgress aProgress) {
        SubProgress progress = SubProgress.convert(aProgress, this.gParts.size());
        for (IGPart gPart : this.gParts) {
            try {
                gPart.mount(progress.newChild(1));
            } catch (GPartException e) {
                this.problems.add(new GProblem(gPart, e));
            }
        }
        
    }

    @objid ("f26180d2-0114-4012-b3d4-01c6ddcee06e")
    protected void installParts(IModelioProgress aProgress) {
        SubProgress progress = SubProgress.convert(aProgress, this.gParts.size());
        for (IGPart gPart : this.gParts) {
            try {
                gPart.install(this, progress.newChild(1));
            } catch (GPartException e) {
                this.problems.add(new GProblem(gPart, e));
            }
        }
        
    }

    @objid ("e6804393-a1ec-4ebd-a5f4-eee50254e100")
    protected void unmountParts(IModelioProgress aProgress) {
        SubProgress mon = SubProgress.convert(aProgress, this.gParts.size());
        for (IGPart gPart : this.gParts) {
            try {
                gPart.unmount(mon.newChild(1));
            } catch (GPartException e) {
                this.problems.add(new GProblem(gPart, e));
            }
        }
        
    }

    @objid ("39422c62-8d34-4a71-ad1b-4bf91a58d04b")
    @Override
    public void removeGPart(IGPart gPart) throws GPartException {
        removeGPart(null, gPart);
    }

    @objid ("d2383927-d566-4223-9104-aeef0daeaf8f")
    @Override
    public void removeGPart(IModelioProgress monitor, IGPart gPart) throws GPartException {
        GProjectStateEnum s = this.state.getValue();
        if (s != GProjectStateEnum.OPENING && s != GProjectStateEnum.OPENED) {
            // Do nothing and return immediately.
            // However log it as this is an error.
            Log.error("Cannot remove part %s when project is not opened (state=%s)...", gPart.getId(), s);
            return;
        }
        
        if (this.gParts.contains(gPart)) {
            SubProgress mon = SubProgress.convert(monitor, 2);
            gPart.unmount(mon.newChild(1));
            gPart.uninstall(this, mon.newChild(1));
            this.gParts.remove(gPart);
            this.descriptor.getPartDescriptors().remove(gPart.getDescriptor());
        }
        
    }

    @objid ("48d37542-ecac-4b60-be04-379f1c2c4b84")
    @Override
    public void removeGPartDescriptor(IModelioProgress monitor, GProjectPartDescriptor partDescriptor) throws GPartException {
        GProjectStateEnum s = this.state.getValue();
        switch (s) {
        case NEW:
        case SESSIONUP:
            // Delete ALL parts that match the descriptor, allows cleaning ill formed projects
            for (IGPart existingPart : this.gParts) {
                if (existingPart.getId().equals(partDescriptor.getId()) && existingPart.getType()==partDescriptor.getType()) {
                    existingPart.uninstall(this, monitor);
                    this.gParts.remove(existingPart);
                }
            }
            break;
        case OPENED:
        case OPENING: {
            // Delete ALL parts that match the descriptor, allows cleaning ill formed projects
            for (IGPart existingPart : this.gParts) {
                if (existingPart.getId().equals(partDescriptor.getId()) && existingPart.getType()==partDescriptor.getType()) {
                    SubProgress mon = SubProgress.convert(monitor, 2);
                    existingPart.unmount(mon.newChild(1));
                    existingPart.uninstall(this, mon.newChild(1));
                    this.gParts.remove(existingPart);
                }
            }
        
            break;
        }
        case INITIAL:
        default:
            // Throw wrong state
            throw new IllegalStateException(String.format(
                    "Cannot remove part descriptor %s when project state is not 'new'd (state=%s)...", partDescriptor.getId(), s));
        }
        
        
        // Update the descriptor
        this.descriptor.getPartDescriptors().remove(partDescriptor);
        
    }

    /**
     * Save the model and the project description.
     * @param progress a Modelio progress monitor
     * @throws IOException if a repository failed to save.
     */
    @objid ("816210eb-ed65-44b5-b1f6-b8227ef664dd")
    @Override
    public void save(IModelioProgress progress) throws IOException {
        // Save the session (model chnages)
        if (this.session != null) {
            this.session.save(progress);
        }
        
        // Save the project.conf
        new GProjectDescriptorWriter().write(this.descriptor);
        
    }

    /**
     * Set the project's name.
     */
    @objid ("c9bc1409-f462-418d-adae-09065fabcd62")
    @Override
    public void setName(String name) {
        this.descriptor.setName(name);
    }

    /**
     * Replace the current GProperties of the project by the given properties. WARNING: full replacement, no merge.
     */
    @objid ("74e33b55-b2c8-453b-aa76-a6989af5765c")
    @Override
    public void setProperties(GProperties gProperties) {
        this.descriptor.setProperties(gProperties);
    }

    /**
     * Updates the project remote location.
     * <p>
     * <ul>
     * <li>To be called only by {@link GProjectConfigurer}.
     * <li>To be redefined by remote project implementations.
     * </ul>
     * @param remoteLocation the new project remote location.
     * @throws URISyntaxException if the given location is invalid
     */
    @objid ("9a326168-bc0e-4c04-ac1b-7c9ad6f45d82")
    @Override
    public void setRemoteLocation(String remoteLocation) throws URISyntaxException {
        // nothing to do for a local project.
    }

    @objid ("2e4f06fe-9790-4b4d-ab51-7beacc0dc782")
    protected final void checkNotOpen() throws IllegalStateException {
        if (this.session != null) {
            throw new IllegalStateException("'" + getName() + "' project already open.");
        }
        
    }

    /**
     * Set the project version.
     * <p>
     * On local project checks the Modelio version is same or newer than expected one. If Modelio is newer its version will be written in the project descriptor for next save.
     * <p>
     * This method may be (and is) redefined to add other constraints on remote projects. Check the expected Modelio version with the current Modelio version.
     * <p>
     * @throws IOException if the Modelio version does not match.
     */
    @objid ("23b1c270-4a07-4d0c-84b9-cb8fb2fcfd55")
    protected void setModelioVersion(Version version) throws IOException {
        if (!ModelioVersion.isCompatible(version)) {
            String msg;
            if (version == null) {
                msg = CoreProject.I18N.getMessage(
                        "GProject.projectTooOld",
                        getName(),
                        ModelioVersion.MAJOR_MINOR.toString("V.R"));
            } else {
                msg = CoreProject.I18N.getMessage(
                        "GProject.modelioTooOld",
                        getName(),
                        version.toString("V.R"),
                        ModelioVersion.MAJOR_MINOR.toString("V.R"));
            }
            throw new IOException(msg);
        }
        // If project expected Modelio version is compatible writes the current Modelio version in the descriptor (no migration)
        this.descriptor.setModelioVersion(version);
        
    }

    @objid ("faea4736-313a-4c81-b5ca-30cf58de411c")
    private  GProject(GProjectDescriptor descriptor) {
        this.descriptor = descriptor;
        this.state = new GProjectState(this);
        
    }

    @objid ("2f3b7bce-afb2-4f6f-928c-c6237c98f071")
    private void mountMetamodelFragments(CoreSession aSession, Collection<IGMetamodelExtension> mmExtensions) throws CyclicDependencyException {
        // Mount metamodel fragments
        // metamodel fragments have to be topologically sorted first to deal with their dependencies
        
        List<IGMetamodelExtension> sortedExtensions = new GMetamodelExtensionTopologicalSorter<>(mmExtensions).sort();
        
        for (IGMetamodelExtension mmExt : sortedExtensions) {
            // monitor.subTask(CoreProject.I18N.getMessage("GProject.mountingMetamodelFragment", mmF.getName()));
            // Add metamodel fragments to the metamodel
            aSession.getMetamodel().addMetamodelFragment(mmExt.getMmFragment());
            mmExt.register(aSession);
            // monitor.worked(1);
        }
        // monitor.done();
        
    }

    /**
     * Get the metamodel extensions loaded in the project.
     * @return the metamodel extensions.
     * @since 3.6
     */
    @objid ("8333279c-10ac-436b-af16-3cf7dad1f7df")
    @Override
    public Collection<IGMetamodelExtension> getMetamodelExtensions() {
        // note : extensions might be added later so may have to be stored elsewhere than in the environment.
        return this.projectEnvironment.getDefaultMetamodelExtensions();
    }

    @objid ("b21b3a8f-b289-46b0-b680-3e410d27dab3")
    @Override
    public List<IGPart> getParts() {
        return Collections.unmodifiableList(this.gParts);
    }

static {
                    // Initialize MTools
                    MTools.initializeMTools(new ModelTool(), new AuthTool());
                }
    
    // end class GProjectBuilder
    @objid ("75c225b9-2644-4398-9fbc-cfea6944b556")
    private static final class GMetamodelExtensionTopologicalSorter<T extends IGMetamodelExtension> extends TopologicalSorter<T> {
        @objid ("20259d62-5707-4d54-9126-8efe6d8bf0ec")
        private Collection<T> extensions = Collections.EMPTY_LIST;

        @objid ("43f57251-970d-4dc5-a488-91fb5ea8d52d")
        public  GMetamodelExtensionTopologicalSorter(Collection<T> mmExtensions) {
            this.extensions = mmExtensions;
        }

        @objid ("caed1e5a-0e26-4725-aabd-620c504b1b1b")
        @Override
        public Collection<T> getNodes() {
            return this.extensions;
        }

        @objid ("38444d04-7ab5-43aa-abe6-cf2fc1c01b7f")
        @Override
        public Collection<T> getAdjacent(T node) {
            Collection<VersionedItem<MMetamodelFragment>> neededFragments = node.getMmFragment().getNeededFragments();
            if (neededFragments.isEmpty()) {
                return Collections.emptyList();
            } else {
                Collection<T> ret = new ArrayList<>(neededFragments.size());
                for (VersionedItem<MMetamodelFragment> needed : neededFragments) {
                    for (T f : this.extensions) {
                        if (f.getMmFragment().getName().equals(needed.getName())) {
                            // skip version checking
                            ret.add(f);
                        }
                    }
                }
            
                return ret;
            }
            
        }

    }

    /**
     * {@link GProject} instances builder.
     * <p>
     * Mandatory entry point to instantiate a GProject,
     * there are too much options to use a standard constructor.
     */
    @objid ("70d36a47-c6ce-4b60-b024-fc8457281274")
    public static class GProject2Builder {
        @objid ("d1865833-5847-4b32-8d88-3804323b27f0")
        private IProjectMonitor eventMonitor;

        @objid ("e50f4b27-f188-4cdf-940e-4f6a5ab117ff")
        private GProjectEnvironment projectEnv = new GProjectEnvironment();

        @objid ("49d2d87a-e9d0-47aa-af2b-dc6defa0e291")
        private GProjectDescriptor projectDescriptor;

        @objid ("e05d3440-77e8-4eda-9e5c-c4ae46677930")
        private IAuthData authData;

        @objid ("dbc9cd15-8e4d-4d10-a44b-e1e821db0899")
        private GProperties properties;

        @objid ("88b1fda6-eff6-43b6-adc3-9b16abd82198")
        private Version expectedModelioVersion;

        /**
         * @param projectDescriptor the mandatory project descriptor
         */
        @objid ("375a45a2-aa01-49d3-a29d-d1167f667387")
        public  GProject2Builder(GProjectDescriptor projectDescriptor) {
            this.projectDescriptor = projectDescriptor;
        }

        /**
         * @param auth optional authentication data
         * @return this builder to chain calls.
         */
        @objid ("4a1cef51-95a8-44a3-9380-0863ec918b7d")
        public GProject2Builder withAuth(IAuthData auth) {
            this.authData = auth;
            return this;
        }

        /**
         * @param metamodelExtensions metamodel extensions to add
         * @return this builder with new methods accessible.
         */
        @objid ("11a0ec08-6318-49fb-9438-365227e6e184")
        public GProject2Builder withMetamodelExtensions(Collection<IGMetamodelExtension> metamodelExtensions) {
            this.projectEnv.addMetamodelExtensions(metamodelExtensions);
            return this;
        }

        /**
         * Set the module catalog and the metamodel fragments from an existing project environment.
         * @param aProjectEnv an already configured project environment to copy.
         * @return this builder with new methods accessible.
         */
        @objid ("630d23b9-6c32-4819-a4f9-c671ece46c13")
        public GProject2Builder withEnvironment(IGProjectEnv aProjectEnv) {
            this.projectEnv.setModulesCache(aProjectEnv.getModulesCache() != null ? aProjectEnv.getModulesCache() : EmptyModuleCache.getInstance());
            this.projectEnv.addMetamodelExtensions(aProjectEnv.getDefaultMetamodelExtensions());
            this.projectEnv.setRamcCache(aProjectEnv.getRamcCache());
            return this;
        }

        /**
         * @param cache a modules catalog
         * @return this builder to chain calls.
         */
        @objid ("2c119b52-becb-4edd-b284-fc94b3c9288d")
        public GProject2Builder withModuleCache(IModuleRTCache cache) {
            this.projectEnv.setModulesCache(cache);
            return this;
        }

        /**
         * Set a project monitor to add immediately on project creation. <br>
         * This monitor will receive events fired while opening the project. The monitor will remain once the project is open. If it is not your intended behavior you have to remove it from the project manually after having opened it.
         * @param anEventMonitor a project event monitor
         * @return this builder to chain calls.
         */
        @objid ("9adae8d5-5cc9-4b4c-adc7-0f041a35c455")
        public GProject2Builder withEventMonitor(IProjectMonitor anEventMonitor) {
            this.eventMonitor = anEventMonitor;
            return this;
        }

        /**
         * Instantiates the project.
         * <p>
         * The project is not open, open it with {@link GProject#open(IModelioProgress)}
         * @param aMonitor a progress monitor
         * @return the instantiated project
         */
        @objid ("27e2f480-ff55-4a6b-b11f-a5e7899d58c8")
        public IGProject build(IModelioProgress aMonitor) {
            SubProgress progress = SubProgress.convert(aMonitor);
            
            // Create new GProject instance
            GProject project = new GProject(this.projectDescriptor);
            project.projectEnvironment = new GProjectEnvironment();
            
            // Migrate the project file structure if needed
            this.projectDescriptor = migrateProjectSpace(this.projectDescriptor, project.projectEnvironment);
            project.pfs = new ProjectFileStructure(this.projectDescriptor.getProjectFileStructure().getProjectPath());
            
            // process 'withAuth' settings
            if (this.authData != null) {
                this.projectDescriptor.setAuthDescriptor(new AuthDescriptor(this.authData, DefinitionScope.LOCAL));
            }
            
            // process 'withEnvironment' settings
            project.projectEnvironment = this.projectEnv;
            
            // Fix null module cache if null
            if (project.projectEnvironment.getModulesCache() == null) {
                project.projectEnvironment.setModulesCache(EmptyModuleCache.getInstance());
            }
            
            // process 'withEventMonitor' settings
            if (this.eventMonitor != null) {
                project.monitorSupport.addMonitor(this.eventMonitor);
            }
            
            // Instantiate the project parts.
            progress.setWorkRemaining(this.projectDescriptor.getPartDescriptors().size() + 2);
            
            for (GProjectPartDescriptor partDescriptor : this.projectDescriptor.getPartDescriptors()) {
                IGPart gPart = GPartFactory.getInstance().instantiate(partDescriptor);
                progress.worked(1);
            
                project.gParts.add(gPart);
            }
            
            project.getState().sendNew(progress.newOptionalChild(2));
            
            progress.done();
            return project;
        }

        /**
         * Migrate the project space structure.
         * @param descriptor the current project
         * @param conf environment for the project.
         * @return the descriptor after migration.
         */
        @objid ("b8602b46-284e-4570-8342-14a7c6598f0c")
        private GProjectDescriptor migrateProjectSpace(GProjectDescriptor descriptor, GProjectEnvironment conf) {
            // Do project space migration here if necessary
            return descriptor;
        }

    }

}
