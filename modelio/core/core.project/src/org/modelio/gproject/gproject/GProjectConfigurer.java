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
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.AuthDescriptor;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.DescriptorServices;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.project.GProperties.Entry;
import org.modelio.gproject.data.project.ModuleDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.data.project.ResourceDescriptor;
import org.modelio.gproject.data.project.todo.InstallModuleDescriptor;
import org.modelio.gproject.data.project.todo.RemoveModuleDescriptor;
import org.modelio.gproject.data.project.todo.TodoDescriptor;
import org.modelio.gproject.data.project.todo.UpdateModuleDescriptor;
import org.modelio.gproject.fragment.Fragments;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.remote.GRemoteProject;
import org.modelio.gproject.module.GModule;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.gproject.module.ModuleSorter;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.collections.TopologicalSorter.CyclicDependencyException;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;

/**
 * Reconfigure an opened project with a new project descriptor.
 * <p>
 * This class will be redefined in GUI plugins to update the modules.
 */
@objid ("ec2c8fb7-cc48-46d2-82ee-fe56b40646ce")
public class GProjectConfigurer {
    @objid ("abb5fb19-a5e2-434d-808b-97c93c546bd1")
    private IAccessDeniedHandler accessDeniedHandler;

    @objid ("009e7105-8a6c-48ba-bb6d-d1f0826d6a1d")
    private List<GProblem> failures = new ArrayList<>();

    @objid ("547b67ec-3786-42fc-85a2-41591e0032ae")
    private Map<String, ModuleChange> moduleChanges;

    @objid ("1d3e1140-30aa-43c0-b311-4d317632b27f")
    private GProject project;

    @objid ("7e021cf9-8a5b-4c19-a048-ebc392b77c5b")
    private TodoDescriptor todo;

    /**
     * Initialize a project configurer with an open project
     * @param project an opened project.
     */
    @objid ("59b929ba-abcb-4e5c-8ffa-c9ed636e1d07")
    public  GProjectConfigurer() {
        
    }

    /**
     * Get the list of fragments or modules that couldn't be installed/removed or modified.
     * @return the failures list.
     */
    @objid ("97df6319-296e-4234-b887-8c0941659964")
    public List<GProblem> getFailures() {
        return this.failures;
    }

    /**
     * Get the modules modified by the last synchronization.
     * @return the module changes.
     */
    @objid ("51292fd2-af65-4427-9381-d57e982ea91b")
    public Collection<ModuleChange> getModuleChanges() {
        return this.moduleChanges.values();
    }

    /**
     * Reconfigure the project with the given new descriptor.
     * <p>
     * Replaces the project content. Old fragments will be removed, new ones will be added and mount.
     * @param aProject The project to synchronize
     * @param newDesc the new project description
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @throws IOException in case of failure preventing synchronization
     */
    @objid ("cb5025f3-61d6-4aed-9e1a-55dc13c2b4be")
    public final void reconfigure(GProject aProject, ProjectDescriptor newDesc, IModelioProgress monitor) throws IOException {
        this.project = aProject;
        this.failures = new ArrayList<>();
        this.todo = aProject.getTodo();
        
        final int nModuleTicks = newDesc.getModules().size();
        final int nFragmentTicks = newDesc.getFragments().size();
        final int nResourceTicks = newDesc.getSharedResources().size() + this.project.getSharedResources().size();
        final SubProgress mon = SubProgress.convert(monitor, nModuleTicks + nFragmentTicks + 1 + nResourceTicks);
        
        if (!this.project.getName().equals(newDesc.getName())) {
            // Project renamed, no pb
            this.project.setName(newDesc.getName());
        }
        
        if (!this.project.getRemoteLocation().equals(newDesc.getRemoteLocation())) {
            // Project address on server renamed, no pb
            try {
                this.project.setRemoteLocation(newDesc.getRemoteLocation());
            } catch (URISyntaxException e) {
                this.failures.add(new GProblem(e));
            }
        }
        
        if (!this.project.getType().toString().equals(newDesc.getType())) {
            // different type, this is a different project.
            throw new IllegalArgumentException(this + " incompatible with " + newDesc.getType());
        }
        
        this.project.reconfigureExpectedVersion(newDesc.getModelioVersion());
        
        this.project.reconfigureProperties(newDesc.getProperties(), mon.newChild(1));
        
        // TODO I don't know what to do if project login changes.
        @SuppressWarnings ("unused")
        boolean authChanged = this.project.getAuthConfiguration().reconfigure(newDesc.getAuthDescriptor());
        
        // Modules
        reconfigureModules(newDesc, mon.newChild(nModuleTicks));
        
        // Fragments
        reconfigureFragments(newDesc, mon.newChild(nFragmentTicks));
        
        // Shared resources
        reconfigureSharedResources(newDesc, mon.newChild(nResourceTicks));
        
        mon.subTask("");
        mon.done();
        
    }

    /**
     * Set the callback that will be called if authentication fails for a fragment or a module.
     * <p>
     * The implementation is expected to prompt the user for new authentication data.
     * @param accessDeniedHandler the callback.
     * @since 3.7
     */
    @objid ("6e946859-2882-43ae-9c74-1ad1ac2be9a3")
    public void setAccessDeniedHandler(IAccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }

    /**
     * Synchronize the project against its remote configuration.
     * <p>
     * Does nothing on local projects.
     * @param aProject The project to synchronize
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @return <code>true</code> if the configuration was changed, <code>false</code> if no change was made.
     * @throws IOException in case of failure preventing synchronization.
     * @throws GProjectAuthenticationException in case of authentication error.
     */
    @objid ("cd85e181-73c6-4b4b-9f6e-10cd4e201570")
    public boolean synchronize(GProject aProject, IModuleStore moduleCatalog, IModelioProgress monitor) throws IOException, GProjectAuthenticationException {
        if (aProject instanceof GRemoteProject) {
            String title = CoreProject.I18N.getMessage("GProjectConfigurer.Synchronizing", aProject.getName());
            SubProgress mon = SubProgress.convert(monitor, title, 100);
        
            GRemoteProject rp = (GRemoteProject) aProject;
        
            ProjectDescriptor newDesc = new ProjectWriter(rp).writeProject();
            IAuthData authData = rp.getAuthConfiguration().getAuthData();
            ProjectDescriptor newServerDesc = GProjectFactory.getRemoteDescriptor(newDesc, authData, mon.newChild(10));
        
            addMandatoryModules(mon.newChild(5), newServerDesc, moduleCatalog);
        
            if (newServerDesc == null) {
                final String msg = CoreProject.I18N.getMessage("GProjectConfigurer.NoRemoteProjectDescriptor", rp.getRemoteLocation());
                rp.getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(new IOException(msg)));
                Log.trace(new IOException(msg));
            } else if (aProject.needsReconfiguration(newServerDesc)) {
                DescriptorServices.removeSharedPart(newDesc);
                DescriptorServices.merge(newServerDesc, newDesc);
                newDesc.cleanup();
        
                // Clean previously unresolved TODO actions
                newDesc.getTodo().getActions().clear();
        
                reconfigure(aProject, newDesc, mon.newChild(95));
        
                aProject.save(mon.newChild(5));
                return true;
            }
        }
        return false;
    }

    /**
     * Download synchronized resources to update.
     * @param mon a progress monitor
     * @param aProject the project to synchronize.
     * @param resourcesToDownload The descriptors of the resources to download, with their target location.
     * @throws IOException on I/O failure
     */
    @objid ("3200cc6e-d7e0-476c-893a-e2d1d371a824")
    protected void downloadResources(IModelioProgress mon, GProject aProject, Collection<ResourceDescriptor> resourcesToDownload) throws IOException {
        // to be redefined
        throw new UnsupportedOperationException();
        
    }

    /**
     * Fetch a module handle for the given descriptor.
     * <p>
     * Prompt for authentication if needed.
     * @param md a module descriptor
     * @return the module handle
     * @throws FileSystemException in case of failure
     * @throws IOException in case of failure
     */
    @objid ("6bd85c21-db36-429e-af73-a810237123e2")
    protected IModuleHandle fetchModuleHandle(IModelioProgress mon, ModuleDescriptor md) throws FileSystemException, IOException {
        try {
            return this.project.getModuleHandle(mon, md);
        } catch (AccessDeniedException e) {
            AuthDescriptor authDesc = md.getAuthDescriptor();
            final IAuthData badAuthData = authDesc.getData();
            if (authDesc.getScope() == DefinitionScope.LOCAL || badAuthData == null) {
                // Authentication may be modified locally, prompt user for correct ones
                final String moduleLabel = md.getName() + " " + md.getVersion();
                IAuthData authData = handleAccessDeniedException(moduleLabel, md.getArchiveLocation(), badAuthData, e);
                if (authData != null) {
                    authDesc.setData(authData);
                    return fetchModuleHandle(mon, md);
                }
            }
            throw e;
        }
        
    }

    /**
     * Install a new module.
     * <p>
     * May be redefined.
     * @param fd the new module descriptor.
     * @param mon the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @throws IOException in case of failure
     */
    @objid ("ea6f1023-85dd-4f21-816a-090fee511246")
    protected void installModule(ModuleDescriptor fd, IModelioProgress mon) throws IOException {
        Objects.requireNonNull(fd, "no module descriptor");
        
        InstallModuleDescriptor desc = new InstallModuleDescriptor();
        
        desc.setDescriptor(fd);
        this.todo.getActions().add(desc);
        
    }

    /**
     * Default implementation replaces share scoped parameters with the news.
     * <p>
     * Local scope parameters are left untouched. To be redefined for a better behavior.
     * @param m the module to update
     * @param desc the new module parameters.
     * @param mon the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @throws IOException in case of failure
     */
    @objid ("5b042a6f-1415-4f2c-95ce-7010b25d1e09")
    protected void reconfigureModule(GModule m, ModuleDescriptor desc, IModelioProgress mon) throws IOException {
        m.setScope(desc.getScope());
        m.setOriginalArchiveUri(desc.getArchiveLocation());
        
        if (m.getAuthData() == null) {
            m.setAuthData(GAuthConf.from(desc.getAuthDescriptor()));
        } else {
            m.getAuthData().reconfigure(desc.getAuthDescriptor());
        }
        
        GProperties myParameters = m.getParameters();
        GProperties descParams = desc.getParameters();
        
        for (Entry theirParam : descParams.entries()) {
            final Entry myParam = myParameters.getProperty(theirParam.getName());
            if (myParam == null || theirParam.getScope() == DefinitionScope.SHARED || myParam.getScope() == DefinitionScope.SHARED) {
                myParameters.setProperty(theirParam.getName(), theirParam.getValue(), theirParam.getScope());
            }
        }
        
        // Shared parameters that are not on the server anymore become local
        for (Entry p : myParameters.entries()) {
            if (p.getScope() == DefinitionScope.SHARED && descParams.getProperty(p.getName()) == null) {
                p.setScope(DefinitionScope.LOCAL);
            }
        }
        
    }

    @objid ("b1f40ad6-421b-4f4e-8f75-26baf1b405cc")
    protected void reconfigureModules(ProjectDescriptor newDesc, SubProgress mon) {
        this.moduleChanges = new HashMap<>();
        mon.setWorkRemaining(newDesc.getModules().size() * 2);
        
        // Iterates old modules descriptors
        for (GModule oldModule : this.project.getModules()) {
            ModuleChange entry = this.moduleChanges.get(oldModule.getName());
            if (entry == null) {
                final IModuleHandle oldHandle = oldModule.getModuleHandle();
                entry = new ModuleChange(null, oldModule, oldHandle);
                this.moduleChanges.put(oldModule.getName(), entry);
            } else {
                entry.oldModule = oldModule;
            }
        }
        
        // Iterates new modules descriptors
        ProjectWriter pw = new ProjectWriter(this.project);
        
        for (ModuleDescriptor md : newDesc.getModules()) {
            try {
        
                IModuleHandle mh = fetchModuleHandle(mon.newChild(1), md);
        
                ModuleChange entry = this.moduleChanges.get(md.getName());
                if (entry == null) {
                    // It's a new module
                    entry = new ModuleChange(md, null, mh);
                    this.moduleChanges.put(mh.getName(), entry);
                } else {
                    // It's an existing module
                    if (pw.writeModuleDescriptor(entry.oldModule).equals(md)) {
                        // No change, remove entry
                        this.moduleChanges.remove(md.getName());
                    } else {
                        entry.newDesc = md;
                        entry.newHandle = mh;
                    }
                }
        
            } catch (IOException | RuntimeException e) {
                // report failure
                addFailure(md, null, e);
        
                // record this module to be ignored for changes
                this.moduleChanges.remove(md.getName());
            }
        }
        
        // Process modules changes
        List<IModuleHandle> allModuleHandles = new ArrayList<>(newDesc.getModules().size());
        for (ModuleChange change : this.moduleChanges.values()) {
            if (change.newHandle != null) {
                allModuleHandles.add(change.newHandle);
            }
        }
        
        // Process modules changes in installation order
        mon.setWorkRemaining(allModuleHandles.size());
        for (IModuleHandle h : getSortedModules(allModuleHandles, this.moduleChanges)) {
            ModuleChange entry = this.moduleChanges.get(h.getName());
            if (entry.newDesc == null) {
                // module deleted
                callRemoveModule(entry.oldModule, mon.newChild(1));
            } else if (entry.oldModule == null) {
                // new module
                callInstallModule(entry, mon.newChild(1));
            } else if (entry.oldModule.getModuleHandle().equals(entry.newHandle)) {
                // old test : entry.newDesc.getVersion().equals(entry.oldModule.getVersion())
                // module updated
                // Same version, just update parameters
                callReconfigureModule(entry.oldModule, entry.newDesc, mon.newChild(1));
            } else {
        
                // module updated
                // Another version
                callUpgradeModule(entry, mon.newChild(1));
            }
        }
        
    }

    /**
     * Uninstall a module.
     * <p>
     * By default postpone the action in the to-do descriptor. To be redefined to add other behavior.
     * @param m the module to remove.
     * @param mon the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @throws IOException in case of failure
     */
    @objid ("27503c90-5d40-4bbf-a76d-02a69d9db936")
    protected void removeModule(GModule m, IModelioProgress mon) throws IOException {
        RemoveModuleDescriptor desc = new RemoveModuleDescriptor();
        desc.setModuleName(m.getName());
        this.todo.getActions().add(desc);
        
    }

    /**
     * Update a module to another version.
     * <p>
     * May be redefined.
     * <p>
     * By default postpone the operation in the project to-do list.
     * @param oldModule the module to update
     * @param md the new module descriptor
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @throws IOException in case of failure
     */
    @objid ("f7e455f8-2eed-4888-b017-baaa46ffb9c5")
    protected void upgradeModule(GModule oldModule, ModuleDescriptor md, IModelioProgress monitor) throws IOException {
        UpdateModuleDescriptor desc = new UpdateModuleDescriptor();
        desc.setNewModuleDescriptor(md);
        desc.setOldModuleName(oldModule.getName());
        
        this.todo.getActions().add(desc);
        
    }

    /**
     * Record failure to synchronize a module.
     * @param moduleDescriptor a module descriptor if available
     * @param module the module if available
     * @param cause the error
     */
    @objid ("c8223389-c506-4fb9-b8ad-365e419d0866")
    private void addFailure(ModuleDescriptor moduleDescriptor, GModule module, Throwable cause) {
        this.failures.add(new GProblem(moduleDescriptor, module, cause));
    }

    /**
     * Record failure to synchronize a fragment.
     * @param f the fragment if available
     * @param fd a fragment descriptor if available
     * @param cause the error
     */
    @objid ("30814275-e282-4d16-a5cc-3a6ec0e2e4a2")
    private void addFailure(IProjectFragment f, FragmentDescriptor fd, Exception cause) {
        this.failures.add(new GProblem(fd, f, cause));
    }

    @objid ("eb1e6797-17ca-4c29-bfde-67da41c67c2e")
    private void callDownloadResources(SubProgress mon, GProject aProject, Collection<ResourceDescriptor> resourcesToDownload) {
        mon.subTask("Downloading " + resourcesToDownload.size() + "...");
        mon.setWorkRemaining(4);
        
        try {
            downloadResources(mon.newChild(3), aProject, resourcesToDownload);
        
            checkDownloadedResources(mon, resourcesToDownload);
        
        } catch (IOException | RuntimeException e) {
            Log.warning(e);
            this.failures.add(new GProblem(e));
        }
        
    }

    @objid ("289fd0a5-9690-48cf-8e1b-80aac5a6c6a7")
    private void callInstallModule(ModuleChange entry, IModelioProgress mon) {
        mon.subTask("Installing " + entry.newDesc.getName() + " " + entry.newDesc.getVersion() + "...");
        
        try {
            installModule(entry.newDesc, mon);
        } catch (IOException | RuntimeException e) {
            addFailure(entry.newDesc, null, e);
        }
        
    }

    @objid ("61098f30-1054-4e06-9088-15b447fa0ba8")
    private void callReconfigureFragment(SubProgress mon, IProjectFragment f, FragmentDescriptor fd) {
        mon.subTask(CoreProject.I18N.getMessage("GProjectConfigurer.SynchronizingFragment", f.getId()));
        
        try {
            f.reconfigure(fd, mon);
        } catch (Exception e) {
            addFailure(f, fd, e);
        }
        
    }

    @objid ("fff74949-a51e-4e31-a32a-3886cf44e49e")
    private void callReconfigureModule(GModule m, ModuleDescriptor fd, SubProgress mon) {
        try {
            reconfigureModule(m, fd, mon);
        } catch (IOException | RuntimeException e) {
            addFailure(fd, m, e);
        }
        
    }

    @objid ("cd5fa51e-1823-463d-abdb-e133715bcdc7")
    private void callRemoveModule(GModule m, SubProgress mon) {
        mon.subTask("Removing " + m.getName() + " " + m.getVersion() + "...");
        
        try {
            removeModule(m, mon);
        } catch (IOException | RuntimeException e) {
            addFailure(null, m, e);
        }
        
    }

    @objid ("0d2ee5da-eb51-44c0-b4a7-80b839df1679")
    private void callUpgradeModule(ModuleChange ch, IModelioProgress mon) {
        try {
            upgradeModule(ch.oldModule, ch.newDesc, mon);
        } catch (IOException | RuntimeException e) {
            addFailure(ch.newDesc, ch.oldModule, e);
        }
        
    }

    @objid ("19824abe-e648-436a-a315-dd4f9000695c")
    private void checkDownloadedResources(SubProgress mon, Collection<ResourceDescriptor> resourcesToDownload) {
        mon.setWorkRemaining(resourcesToDownload.size());
        for (ResourceDescriptor newRd : resourcesToDownload) {
            Path newPath = this.project.getProjectFileStructure().getProjectPath().resolve(newRd.getTargetLocation());
        
            try {
                FileTime newTimeStamp = Files.getLastModifiedTime(newPath);
        
                newRd.setTimestamp(newTimeStamp.toMillis());
            } catch (IOException e) {
                // download failed, force update on next synchro .
                newRd.setTimestamp(0);
                this.failures.add(new GProblem(newRd, e));
            }
            mon.worked(1);
        }
        
    }

    /**
     * Sort the module handles by dependency order.
     * <p>
     * Reports and does best effort in case of dependency cycle.
     * @param allModuleHandles the handles to sort
     * @param map the changes map for failure reporting
     * @return the sorted handle.
     */
    @objid ("c0e139b9-622f-4467-aa25-f19e0082df37")
    private List<IModuleHandle> getSortedModules(List<IModuleHandle> allModuleHandles, Map<String, ModuleChange> map) {
        List<IModuleHandle> sortedHandles;
        
        try {
            sortedHandles = ModuleSorter.sortHandles(allModuleHandles);
        } catch (CyclicDependencyException e) {
            // Report an error for each module involved in the cycle
            // note : the cycle members list is not sorted.
            for (IModuleHandle h : e.<IModuleHandle> getCycle()) {
                ModuleChange entry = map.get(h.getName());
                addFailure(entry.newDesc, entry.oldModule, e);
            }
        
            try {
                // Sort the not cycled part and append the cycle at end
                sortedHandles = new ArrayList<>(allModuleHandles);
                sortedHandles.removeAll(e.getCycle());
        
                sortedHandles = ModuleSorter.sortHandles(allModuleHandles);
                sortedHandles.addAll(e.<IModuleHandle> getCycle());
            } catch (CyclicDependencyException e2) {
                // Last resort, return the not sorted list
                sortedHandles = allModuleHandles;
            }
        }
        return sortedHandles;
    }

    /**
     * Called when authentication fails on a fragment or a module.
     * <p>
     * Not redefinable anymore since 3.7, subclasses or caller may either set a callback with {@link #setAccessDeniedHandler(IAccessDeniedHandler)}.
     * @param name the name of the module/fragment that cannot be accessed
     * @param uri the location of the element
     * @param data the failed authentication data
     * @param e the failure
     * @return the new authentication data to try or null to abort.
     */
    @objid ("c04acc39-33bc-4c9f-9f8b-60ccd0013634")
    private final IAuthData handleAccessDeniedException(String name, URI uri, IAuthData data, AccessDeniedException e) {
        if (this.accessDeniedHandler != null) {
            return this.accessDeniedHandler.handleAccessDeniedException(name, uri, data, e);
        } else {
            // abort
            return null;
        }
        
    }

    @objid ("4585b512-ab72-444c-bb5d-5eb0ac31118d")
    private boolean isResource(List<ResourceDescriptor> resources, String id) {
        for (ResourceDescriptor rd : resources) {
            if (rd.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @objid ("6544e0cf-9f0a-4bcc-ba4b-6127a3b52b79")
    private void reconfigureFragments(ProjectDescriptor newDesc, SubProgress mon) {
        ProjectWriter dfact = new ProjectWriter(this.project);
        
        List<FragmentDescriptor> newFragments = new ArrayList<>(newDesc.getFragments());
        
        for (IProjectFragment f : this.project.getOwnFragments()) {
            boolean found = false;
            for (Iterator<FragmentDescriptor> it = newFragments.iterator(); it.hasNext() && !found;) {
                FragmentDescriptor fd = it.next();
                if (fd.getId().equals(f.getId()) && fd.getType().equals(f.getType())) {
                    found = true;
        
                    if (!dfact.writeFragmentDescriptor(f).equals(fd)) {
                        callReconfigureFragment(mon.newChild(10), f, fd);
                    }
        
                    it.remove();
                }
            }
        
            if (!found) {
                mon.subTask(CoreProject.I18N.getMessage("GProjectConfigurer.RemovingFragment", f.getId()));
                this.project.unregisterFragment(f);
                try {
                    mon.subTask(CoreProject.I18N.getMessage("GProjectConfigurer.DeletingFragment", f.getId()));
                    f.delete();
                } catch (IOException | RuntimeException e) {
                    this.failures.add(new GProblem(null, f, e));
                }
            }
        }
        
        // Add new fragments
        for (FragmentDescriptor fd : newFragments) {
            IProjectFragment fragment = Fragments.getFactory(fd).instantiate(fd);
            mon.subTask(CoreProject.I18N.getMessage("GProjectConfigurer.AddingFragment", fd.getId()));
            try {
                this.project.registerFragment(fragment, mon.newChild(10));
            } catch (FragmentConflictException | RuntimeException e) {
                this.project.getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(e));
            }
        }
        
    }

    @objid ("9efc62a3-6dc9-4081-a051-b76eadc9f4f1")
    private void reconfigureSharedResources(ProjectDescriptor newDesc, SubProgress mon) throws FileSystemException, SecurityException, IOException {
        List<ResourceDescriptor> ownSharedResources = this.project.getSharedResources();
        if (ownSharedResources.isEmpty() && newDesc.getSharedResources().isEmpty()) {
            return;
        }
        
        // Update existing resources and delete obsolete ones.
        Collection<ResourceDescriptor> resourcesToDownload = new ArrayList<>();
        
        for (Iterator<ResourceDescriptor> it = ownSharedResources.iterator(); it.hasNext();) {
            ResourceDescriptor oldRd = it.next();
            ResourceDescriptor newRd = newDesc.getSharedResource(oldRd.getId());
        
            Path oldPath = this.project.getProjectFileStructure().getProjectPath().resolve(oldRd.getTargetLocation());
        
            // Fetch last modif time from file system and compare
            boolean isOutdated = false;
            try {
                FileTime timeStamp = Files.getLastModifiedTime(oldPath);
                oldRd.setTimestamp(timeStamp.toMillis());
        
                isOutdated = newRd == null || !newRd.equals(oldRd);
            } catch (@SuppressWarnings ("unused") NoSuchFileException e) {
                // Resource missing, set it as to download
                isOutdated = true;
            }
        
            if (isOutdated) {
                try {
                    if (Files.exists(oldPath)) {
                        FileUtils.delete(oldPath);
                    }
        
                    if (newRd == null) {
                        it.remove();
                    } else {
                        oldRd.setTargetLocation(newRd.getTargetLocation());
                        resourcesToDownload.add(oldRd);
                    }
                } catch (IOException e) {
                    this.failures.add(new GProblem(oldRd, e));
                }
            }
        }
        
        // Add new resources
        for (ResourceDescriptor newrd : newDesc.getSharedResources()) {
            if (!isResource(ownSharedResources, newrd.getId())) {
                resourcesToDownload.add(newrd);
                ownSharedResources.add(newrd);
            }
        }
        
        // Download all resources to update
        if (!resourcesToDownload.isEmpty()) {
            callDownloadResources(mon, this.project, resourcesToDownload);
        }
        
    }

    /**
     * Complete the {@link ProjectDescriptor} by adding missing mandatory modules.
     * @param monitor a progress monitor
     * @param projDesc the descriptor to complete
     * @param moduleCatalog the module catalog
     * @throws IOException on I/O failure
     */
    @objid ("b674f2ee-6a31-4edd-97a6-18ca58221b22")
    protected void addMandatoryModules(IModelioProgress monitor, ProjectDescriptor projDesc, IModuleStore moduleCatalog) throws IOException {
        if (projDesc == null) {
            return;
        }
        
        for (IModuleHandle moduleHandle : moduleCatalog.findAllModules(monitor)) {
            if (moduleHandle.isMandatory()) {
                if (projDesc.getModules().stream().noneMatch(d -> d.getName().equals(moduleHandle.getName()))) {
                    ModuleDescriptor md = new ModuleDescriptor();
                    md.setName(moduleHandle.getName());
                    md.setVersion(moduleHandle.getVersion());
                    md.setScope(DefinitionScope.SHARED);
                    md.setAuthDescriptor(new AuthDescriptor(null, DefinitionScope.SHARED));
                    projDesc.getModules().add(md);
                }
            }
        
        }
        
    }

    /**
     * Callback called when authentication fails on a fragment or a module.
     * <p>
     * Implementations should prompt the user for new authentication data.
     * 
     * @author cma
     * @since 3.7
     */
    @objid ("59b09915-9ce4-4430-b2e9-70270495e84e")
    public interface IAccessDeniedHandler {
        /**
         * Called when authentication fails on a fragment or a module.
         * <p>
         * Implementations should prompt the user for new authentication data.
         * @param name the name of the module/fragment that cannot be accessed
         * @param uri the location of the element
         * @param data the failed authentication data
         * @param e the failure
         * @return the new authentication data to try authentication again or null to abort.
         */
        @objid ("056f3d6c-2993-4035-b795-e0495243d62d")
        IAuthData handleAccessDeniedException(String name, URI uri, IAuthData data, AccessDeniedException e);

    }

    /**
     * Detail for a module to install/remove/update.
     */
    @objid ("f23595d2-e710-49ae-8ed3-23310ba619ef")
    private static class ModuleChange {
        @objid ("e93c6b8b-3b63-4147-886d-486502b1118c")
        ModuleDescriptor newDesc;

        @objid ("8b081b1d-9829-47c3-b826-f77907477005")
        GModule oldModule;

        @objid ("8fa8fde9-9eb6-4668-9a4c-0db51cea420c")
        IModuleHandle newHandle;

        /**
         * @param newDesc new module descriptor
         * @param oldModule old module
         * @param newHandle new module handle
         */
        @objid ("1313212f-7dc8-4df3-9d65-f9915a9f9fb0")
         ModuleChange(ModuleDescriptor newDesc, GModule oldModule, IModuleHandle newHandle) {
            super();
            this.newDesc = newDesc;
            this.oldModule = oldModule;
            this.newHandle = newHandle;
            
        }

    }

}
