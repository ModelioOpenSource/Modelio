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

package org.modelio.gproject.gproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.project.ModuleDescriptor;
import org.modelio.gproject.data.project.todo.InstallModuleDescriptor;
import org.modelio.gproject.data.project.todo.RemoveModuleDescriptor;
import org.modelio.gproject.data.project.todo.TodoActionDescriptor;
import org.modelio.gproject.data.project.todo.TodoDescriptor;
import org.modelio.gproject.data.project.todo.UpdateModuleDescriptor;
import org.modelio.gproject.module.GModule;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.net.UriPathAccess;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;

/**
 * Service to execute a {@link TodoDescriptor} on a {@link GProject}.
 * 
 * @since Modelio 3.4
 */
@objid ("b0456a4d-7b5e-41bc-a1fd-729576106f18")
public class TodoActionsRunner {
    @objid ("03fcf8d2-46f1-4621-a497-984e4038af90")
    private final GProject project;

    @objid ("6c74a7eb-208c-4dff-8ffc-3a7b53a077d1")
    private List<GProblem> failures = new ArrayList<>();

    /**
     * Initialize a project configurer with an open project
     * 
     * @param project an opened project.
     */
    @objid ("2472947b-a249-420d-949f-1716db09fdf5")
    public TodoActionsRunner(GProject project) {
        this.project = project;
    }

    /**
     * Execute the project to-do actions.
     * 
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     */
    @objid ("c3db9b10-229a-46f8-ae63-4fe72faf9083")
    public void execute(IModelioProgress monitor) {
        List<TodoActionDescriptor> actions = this.project.getTodo().getActions();
        
        SubProgress mon = SubProgress.convert(monitor, actions.size());
        
        // do removals first
        for (Iterator<TodoActionDescriptor> it = actions.iterator(); it.hasNext();) {
            TodoActionDescriptor action = it.next();
            if (action instanceof RemoveModuleDescriptor) {
                RemoveModuleDescriptor desc = (RemoveModuleDescriptor) action;
                if (callRemoveModule(desc, mon.newChild(1))) {
                    it.remove();
                }
            }
        }
        
        // As the list is not sorted, try install/upgrade each element until it works
        // or everyone fail.
        // TODO Better sort installs and updates by dependency, no dependency first.
        boolean workdone;
        do {
            workdone = false;
            for (Iterator<TodoActionDescriptor> it = actions.iterator(); it.hasNext();) {
                TodoActionDescriptor action = it.next();
                if (action instanceof InstallModuleDescriptor) {
                    InstallModuleDescriptor desc = (InstallModuleDescriptor) action;
                    if (callInstallModule(desc, mon.newChild(1))) {
                        it.remove();
                        workdone = true;
                        removeFailure(desc);
                    }
                } else if (action instanceof UpdateModuleDescriptor) {
                    UpdateModuleDescriptor desc = (UpdateModuleDescriptor) action;
                    desc.getOldModuleName();
                    if (callUpgradeModule(desc, mon.newChild(1))) {
                        it.remove();
                        workdone = true;
                        removeFailure(desc);
                    }
                }
            }
        } while (!actions.isEmpty() && workdone);
    }

    /**
     * Get to actions execution failures.
     * 
     * @return execution failures.
     */
    @objid ("d4b53dad-014b-4a21-adad-5546d5679b8c")
    public List<GProblem> getFailures() {
        return this.failures;
    }

    /**
     * @return the handled project
     */
    @objid ("31e21fd4-617e-4dc4-8b4b-fa2875e2b381")
    public GProject getProject() {
        return this.project;
    }

    /**
     * @param name a module name.
     * @return the {@link GModule} with the given name
     * @throws java.util.NoSuchElementException if no GModule with the given name
     */
    @objid ("e9f68115-229b-4f49-be1b-d1655cd886cc")
    protected final GModule getModule(String name) throws NoSuchElementException {
        GModule ret = findModule(name);
        
        if (ret == null) {
            throw new NoSuchElementException(String.format("No '%s' module in the project", name));
        }
        return ret;
    }

    /**
     * Install a new module.
     * <p>
     * May be redefined.
     * 
     * @param md the new module descriptor.
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @throws java.io.IOException in case of failure
     */
    @objid ("cf6db2cb-a424-499a-a716-a15e4d31a80a")
    protected void installModule(ModuleDescriptor md, IModelioProgress monitor) throws IOException {
        SubProgress mon = SubProgress.convert(monitor, 5);
        
        // Install the module as if the user asked for it
        // The module may change some parameters on upgrade, they won't be lost.
        
        IModuleHandle handle = getModuleHandle(mon.newChild(2), md);
        
        this.project.installModule(handle, md.getArchiveLocation(), mon.newChild(2));
        
        // Overwrite default module parameters with the server parameters
        // Note: 'm' is invalid after installModule(...)
        GModule newGModule = getModule(md.getName());
        reconfigureModule(newGModule, md, mon.newChild(1));
    }

    /**
     * Default implementation replaces share scoped parameters with the news.
     * <p>
     * Local scope parameters are left untouched. To be redefined for a better behavior.
     * 
     * @param m the module to update
     * @param desc the new module parameters.
     * @param mon the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @throws java.io.IOException in case of failure
     */
    @objid ("2805553c-e7dd-48e2-8bb3-9cf5fadd2eaa")
    protected void reconfigureModule(GModule m, ModuleDescriptor desc, IModelioProgress mon) throws IOException {
        m.setScope(desc.getScope());
        
        if (m.getAuthData() == null) {
            m.setAuthData(GAuthConf.from(desc.getAuthDescriptor()));
        } else {
            m.getAuthData().reconfigure(desc.getAuthDescriptor());
        }
        
        GProperties myParameters = m.getParameters();
        GProperties descParams = desc.getParameters();
        
        for (GProperties.Entry theirParam : descParams.entries()) {
            final GProperties.Entry myParam = myParameters.getProperty(theirParam.getName());
            if (myParam == null || theirParam.getScope() == DefinitionScope.SHARED || myParam.getScope() == DefinitionScope.SHARED) {
                myParameters.setProperty(theirParam.getName(), theirParam.getValue(), theirParam.getScope());
            }
        }
        
        // Shared parameters that are not on the server anymore become local
        for (GProperties.Entry p : myParameters.entries()) {
            if (p.getScope() == DefinitionScope.SHARED && descParams.getProperty(p.getName()) == null) {
                p.setScope(DefinitionScope.LOCAL);
            }
        }
    }

    /**
     * Uninstall a module.
     * <p>
     * To be redefined to add other behavior.
     * 
     * @param m the module to remove.
     * @param mon the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @throws java.io.IOException in case of failure
     */
    @objid ("3c2e7adf-d545-4b59-a806-359cac491966")
    protected void removeModule(GModule m, IModelioProgress mon) throws IOException {
        this.project.removeModule(m);
    }

    /**
     * Update a module to another version.
     * <p>
     * May be redefined.
     * 
     * @param m the module to update
     * @param md the new module descriptor
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @throws java.io.IOException in case of failure
     */
    @objid ("59be1f0e-aac2-4d4b-8dd8-99fe120ccbba")
    protected void upgradeModule(GModule m, ModuleDescriptor md, IModelioProgress monitor) throws IOException {
        SubProgress mon = SubProgress.convert(monitor, 30);
        mon.subTask(CoreProject.I18N.getMessage("ProjectSynchro.updateModule", m.getName(), m.getVersion(), md.getVersion()));
        
        removeModule(m, mon.newChild(10));
        installModule(md, mon.newChild(10));
        
        reconfigureModule(m, md, mon.newChild(10));
    }

    /**
     * Record failure to synchronize a module.
     * 
     * @param desc a module descriptor if available
     * @param cause the error
     */
    @objid ("f8442abd-44fe-4eca-95ec-d9af05294ce9")
    private void addFailure(TodoActionDescriptor desc, Throwable cause) {
        for (GProblem f : this.failures) {
            if (f.getSubject().equals(desc.getLocalizedLabel())) {
                // Problem already recorded, skip it
                return;
            }
        }
        
        this.failures.add(new GProblem(desc.getLocalizedLabel(), cause));
    }

    @objid ("3cc8ca71-4bb9-465a-8387-604aca080f2f")
    private boolean callInstallModule(InstallModuleDescriptor desc, IModelioProgress mon) {
        ModuleDescriptor md = desc.getModuleDescriptor();
        
        try {
            GModule m = findModule(md.getName());
            if (m != null && m.getName().equals(md.getName()) && m.getVersion().equals(md.getVersion())) {
                // module already installed
                return true;
            }
        
            installModule(md, mon);
            return true;
        } catch (IOException | RuntimeException e) {
            addFailure(desc, e);
            return false;
        }
    }

    @objid ("4b0bcc91-8910-4dee-ba93-1704198dac27")
    private boolean callReconfigureModule(UpdateModuleDescriptor desc, SubProgress mon) {
        GModule m = null;
        try {
            m = findModule(desc.getOldModuleName());
            if (m != null) {
                reconfigureModule(m, desc.getNewModuleDescriptor(), mon);
            }
            return true;
        } catch (IOException | RuntimeException e) {
            addFailure(desc, e);
            return false;
        }
    }

    @objid ("c1612e1a-a02b-4b5d-a127-8d30a71c632c")
    private boolean callRemoveModule(RemoveModuleDescriptor desc, SubProgress mon) {
        GModule m = null;
        try {
            m = findModule(desc.getModuleName());
            if (m == null) {
                return true;
            }
        
            if (m.getModuleHandle().isMandatory()) {
                addFailure(desc, new AccessDeniedException(m.getName() + " v" + m.getVersion(), null, "It is a mandatory module."));
                return true;
            }
        
            removeModule(m, mon);
        
            return true;
        } catch (IOException | RuntimeException e) {
            addFailure(desc, e);
            return false;
        }
    }

    @objid ("680cb347-9f31-495d-9609-d3065b7a5c37")
    private boolean callUpgradeModule(UpdateModuleDescriptor desc, IModelioProgress mon) {
        ModuleDescriptor fd = desc.getNewModuleDescriptor();
        GModule m = null;
        try {
            m = findModule(desc.getOldModuleName());
            if (m != null && m.getName().equals(fd.getName()) && m.getVersion().equals(fd.getVersion())) {
                // module already upgraded
                return true;
            }
        
            upgradeModule(m, fd, mon);
            return true;
        } catch (IOException | RuntimeException e) {
            addFailure(desc, e);
            return false;
        }
    }

    @objid ("cdbacc0f-d1f6-4e77-9060-f1c23e4e4882")
    private void removeFailure(TodoActionDescriptor desc) {
        this.failures.removeIf(f -> f.getSubject().equals(desc.getLocalizedLabel()));
    }

    /**
     * Get a module handle for the given {@link ModuleDescriptor}.
     * <p>
     * If the descriptor has an archive location, try to use it first. If the archive location is null or invalid, look into the project {@link IModuleRTCache module cache}. If nothing in the module cache throws FileNotFoundException or NoSuchFileException
     * 
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @param md a module descriptor
     * @return the found module handle, never <code>null</code>.
     * @throws java.io.FileNotFoundException if no module handle could be found
     * @throws java.nio.file.NoSuchFileException if no module handle could be found
     * @throws java.io.IOException on I/O error
     * @since 3.8
     */
    @objid ("e239832d-b14d-4e1b-abcc-df28336e08fa")
    protected IModuleHandle getModuleHandle(IModelioProgress monitor, ModuleDescriptor md) throws FileNotFoundException, IOException, NoSuchFileException {
        IModuleRTCache cache = getProject().getModuleCache();
        IOException cachedFailure = null;
        
        if (md.getArchiveLocation() != null) {
            // always use the given archive location
        
            IAuthData authData = new AuthResolver(getProject()).resolve(md);
            try (UriPathAccess access = new UriPathAccess(md.getArchiveLocation(), authData)) {
                // Install the module in the cache
                Path archivePath = access.getPath();
                IModuleHandle handle = cache.installModuleArchive(archivePath, monitor);
                return handle;
            } catch (FileNotFoundException | NoSuchFileException e) {
                // Record failure and try to find module in catalog
                cachedFailure = e;
            }
        }
        
        // Look into module cache/catalog
        IModuleHandle handle = cache.findModule(md.getName(), md.getVersion().toString(), monitor);
        
        if (handle != null) {
            return handle;
        }
        
        if (cachedFailure != null) {
            throw cachedFailure;
        }
        
        throw new FileNotFoundException(md.toString());
    }

    /**
     * @param name a module name.
     * @return the {@link GModule} with the given name or null.
     */
    @objid ("8980a07a-3f37-441f-ae81-d1f03c857ea8")
    protected final GModule findModule(String name) {
        for (GModule gModule : this.project.getModules()) {
            if (gModule.getName().equals(name)) {
                return gModule;
            }
        }
        return null;
    }

}
