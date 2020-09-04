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

package org.modelio.gproject.module;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.project.todo.InstallModuleDescriptor;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.fragment.ramcfile.MdaFragment;
import org.modelio.gproject.fragment.ramcfile.UnsupportedMdaFragment;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Represents a module deployed in the project.
 */
@objid ("64ea0bb3-ec5e-11e1-8186-001ec947ccaf")
public class GModule {
    /**
     * Class format version
     */
    @objid ("3e599a93-ec64-11e1-8186-001ec947ccaf")
    public static final long serialVersionUID = 1L;

    /**
     * Project property to ask Modelio to deploy this module on project open.
     * 
     * @deprecated Use {@link InstallModuleDescriptor}.
     */
    @objid ("01151a68-a0cd-486e-a968-f8dc97d58188")
    @Deprecated
    public static final String SELECT_ON_OPEN = "select-on-open";

    @objid ("2c918456-f2b0-11e1-8543-001ec947ccaf")
    private boolean activated = true;

    /**
     * Definition scope : local/shared.
     */
    @objid ("068515a8-3019-11e2-8f81-001ec947ccaf")
    private DefinitionScope scope;

    @objid ("a44af7c4-ecf9-11e1-912e-001ec947ccaf")
    private IProjectFragment modelFragment;

    @objid ("10c03dd0-f1b3-11e1-993d-001ec947ccaf")
    private final GProperties parameters;

    @objid ("4e3a7c5b-f2bb-11e1-90ff-002564c97630")
    private final IModuleHandle moduleHandle;

    @objid ("f87f0294-f369-11e1-9173-001ec947ccaf")
    private URI originalArchiveUri;

    /**
     * Only used to call getSession().beginLoadingSession()
     */
    @objid ("c9d66ceb-3a44-11e2-a6db-001ec947ccaf")
    private final GProject project;

    @objid ("9b095136-1979-42f0-bd0b-a424ba20f29f")
    private GAuthConf auth;

    /**
     * Initialize the GModule.
     * @param project the project
     * @param originalArchiveUri the original location of the archive as a URI
     * @param moduleHandle the base structure defining the module.
     * @param defScope the module definition scope: shared with the work group or local.
     * @param gProperties the module parameter values.
     * @param activated whether the module is activated
     */
    @objid ("f87f0295-f369-11e1-9173-001ec947ccaf")
    public GModule(GProject project, URI originalArchiveUri, IModuleHandle moduleHandle, DefinitionScope defScope, GProperties gProperties, boolean activated) {
        this.moduleHandle = Objects.requireNonNull(moduleHandle);
        this.parameters = Objects.requireNonNull(gProperties);
        this.scope = Objects.requireNonNull(defScope);
        this.project = Objects.requireNonNull(project);
        this.originalArchiveUri = originalArchiveUri;
        this.activated = activated;
        this.auth = GAuthConf.from(null);
    }

    @objid ("b37f4121-f27f-11e1-8543-001ec947ccaf")
    @Override
    protected void finalize() throws Throwable {
        unmount();
        
        super.finalize();
    }

    /**
     * Get the model fragment representing the module MDA model.
     * @return the module fragment.
     * @throws java.lang.IllegalStateException if the module is not mount
     */
    @objid ("a44d5a1e-ecf9-11e1-912e-001ec947ccaf")
    public IProjectFragment getModelFragment() throws IllegalStateException {
        if (this.modelFragment == null) {
            Path ramcPath = getModuleHandle().getModelComponentPath();
            if (ramcPath != null) {
                this.modelFragment = new MdaFragment(getName(), ramcPath.toUri(), getScope(), new GProperties(), getAuthData());
                this.modelFragment.setProject(getProject());
            } else {
                this.modelFragment = new UnsupportedMdaFragment(getName(), getScope(), new GProperties(), getAuthData());
                this.modelFragment.setProject(getProject());
            }
        }
        return this.modelFragment;
    }

    /**
     * Get the Module model element.
     * @return the module model element.
     */
    @objid ("8a39ceb7-f377-11e1-9173-001ec947ccaf")
    public ModuleComponent getModuleElement() {
        try {
            String uid = getModuleHandle().getUid();
            IProjectFragment fragment = getModelFragment();
            IRepository repository = fragment.getRepository();
            if (repository != null) {
                SmMetamodel mm = getProject().getSession().getMetamodel();
                return (ModuleComponent) repository.findById(mm.getMClass(ModuleComponent.class), uid);
            }
        } catch (IllegalArgumentException e) {
            // Invalid uuid, no module component to return
        }
        return null;
    }

    /**
     * Get the base structure defining the module.
     * @return the IModuleHandle for this GModule. Might be <code>null</code> if the module has been unmounted.
     */
    @objid ("5ffb02ca-f2bb-11e1-90ff-002564c97630")
    public IModuleHandle getModuleHandle() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.moduleHandle;
    }

    /**
     * Get the name of the GModule.
     * @return the module's name.
     */
    @objid ("aa6f19d6-ec75-11e1-912e-001ec947ccaf")
    public String getName() {
        return getModuleHandle().getName();
    }

    /**
     * Get the module parameters.
     * @return the module parameters.
     */
    @objid ("10c03dd4-f1b3-11e1-993d-001ec947ccaf")
    public GProperties getParameters() {
        return this.parameters;
    }

    /**
     * @return the definition scope.
     */
    @objid ("632cc37b-3004-11e2-8f81-001ec947ccaf")
    public DefinitionScope getScope() {
        return this.scope;
    }

    /**
     * Get the version of the GModule.
     * @return the module's version.
     */
    @objid ("aa717c30-ec75-11e1-912e-001ec947ccaf")
    public Version getVersion() {
        return getModuleHandle().getVersion();
    }

    /**
     * Indicates whether or not this module is activated.
     * @return <code>true</code> when the module is active, <code>false</code> otherwise.
     */
    @objid ("2c91845b-f2b0-11e1-8543-001ec947ccaf")
    public boolean isActivated() {
        return this.activated;
    }

    /**
     * Activate/deactivate the module.
     * @param activated <code>true</code> when the module must be activated, <code>false</code> otherwise.
     */
    @objid ("2c91845f-f2b0-11e1-8543-001ec947ccaf")
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    /**
     * Unmount the module, closing the {@link IModuleHandle} and therefore releasing any related system resources.
     * If the module is already unmounted, then invoking this method has no effect.
     */
    @objid ("b37f411c-f27f-11e1-8543-001ec947ccaf")
    public void unmount() {
        if (this.moduleHandle instanceof Closeable) {
            try {
                ((Closeable) this.moduleHandle).close();
            } catch (IOException e) {
                Log.warning(e);
            }
        }
    }

    /**
     * Returns the project this module is installed into.
     * @return a GProject.
     */
    @objid ("f889b2a8-3ed5-11e2-a0a4-002564c97630")
    public GProject getProject() {
        return this.project;
    }

    /**
     * Set the definition scope.
     * @param aScope the definition scope.
     */
    @objid ("a1a826d9-509f-442e-86f0-a1f1c0f36647")
    public void setScope(DefinitionScope aScope) {
        this.scope = aScope;
    }

    @objid ("dd1a238d-bb17-47da-b436-e4dd0d822028")
    @Override
    public String toString() {
        if (getModuleHandle() != null) {
            return getName() + " v" + getVersion() + " GModule @" + getOriginalArchiveUri();
        } else {
            return "GModule @" + getOriginalArchiveUri();
        }
    }

    /**
     * @return the original archive URI.
     */
    @objid ("7f8d3687-d0ad-4b54-9b3c-2d63382ecbba")
    public URI getOriginalArchiveUri() {
        return this.originalArchiveUri;
    }

    /**
     * Get the fragment authentication informations.
     * Never <code>null</code>.
     * @return authentication configuration.
     */
    @objid ("d0d7e210-e059-4765-a54d-ae762597ec35")
    public GAuthConf getAuthData() {
        return this.auth;
    }

    /**
     * Set the module URI authentication data.
     * @param auth authentication data, must not be <code>null</code>.
     */
    @objid ("3891d0d6-9a66-4fa4-a459-4092354e877b")
    public void setAuthData(GAuthConf auth) {
        this.auth = Objects.requireNonNull(auth);
    }

    /**
     * Set the original archive URI.
     * @param originalArchiveUri the original archive URI.
     */
    @objid ("3c5da208-d95a-4ff8-ac18-c141075077a4")
    public void setOriginalArchiveUri(URI originalArchiveUri) {
        this.originalArchiveUri = originalArchiveUri;
    }

}
