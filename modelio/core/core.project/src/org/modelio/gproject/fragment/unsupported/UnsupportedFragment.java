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

package org.modelio.gproject.fragment.unsupported;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.project.IFragmentInfos;
import org.modelio.gproject.fragment.FragmentState;
import org.modelio.gproject.fragment.Fragments;
import org.modelio.gproject.fragment.IFragmentFactory;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.fragment.migration.IFragmentMigrator;
import org.modelio.gproject.gproject.FragmentConflictException;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.GProjectEvent;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;

/**
 * Fragment whose technology is not supported.
 */
@objid ("1a6c65c1-e9ab-43a1-8579-90b62f1c5dfa")
public class UnsupportedFragment implements IProjectFragment {
    @objid ("eaf3d5eb-72fe-4d4a-ab37-bfc6cadefbf5")
    private final FragmentDescriptor desc;

    @objid ("55e6ef4a-b807-4118-88f7-127e8b4dd269")
    private Throwable downCause;

    @objid ("32fd3721-341d-4d4c-9154-696331e0c646")
    private final GAuthConf authConf;

    @objid ("480ec0d9-2de5-4bb3-a21b-8873289be641")
    private GProject project;

    @objid ("ed43e5af-33eb-448c-9694-71a5c679e45d")
    @Override
    public IRepository getRepository() {
        return null;
    }

    @objid ("bd989ed5-9dbb-4a8c-b761-ac9b92f03226")
    @Override
    public FragmentType getType() {
        return this.desc.getType();
    }

    @objid ("3cf96bb7-66e7-4796-8699-b760821c2450")
    @Override
    public URI getUri() {
        return this.desc.getUri();
    }

    /**
     * Instantiate an unsupported fragment.
     * 
     * @param fd the descriptor.
     */
    @objid ("858195ae-0749-4d16-b8f7-dbcb6eb8153a")
    public UnsupportedFragment(final FragmentDescriptor fd) {
        this.desc = fd;
        this.authConf = GAuthConf.from(fd.getAuthDescriptor());
        final String msg = CoreProject.I18N.getMessage("UnsupportedFragment.DownCause", fd.getType().toString(), fd.getId());
        this.downCause = new UnsupportedOperationException(msg);
    }

    @objid ("1695d1f8-3a66-4261-abce-4bf4934878bf")
    @Override
    public void reconfigure(final FragmentDescriptor fd, final IModelioProgress aMonitor) {
        // Test whether the fragment is still not supported.
        final IFragmentFactory fact = Fragments.getFactory(fd);
        if (fact != null && fact != UnsupportedFragmentFactory.getInstance()) {
            // The fragment is now supported, mount it.
            this.project.unregisterFragment(this);
        
            final IProjectFragment fragment = fact.instantiate(fd);
            try {
                this.project.registerFragment(fragment, aMonitor);
            } catch (final FragmentConflictException e) {
                // Report error
                this.project.getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(fragment, e));
        
                // try to rollback
                try {
                    this.project.registerFragment(this, aMonitor);
                } catch (final FragmentConflictException e1) {
                    e1.addSuppressed(e);
                    setDown(e1);
                }
            }
        }
    }

    @objid ("d0bf7248-4ffe-46a6-826c-c5cec2b6779b")
    @Override
    public void delete() throws IOException {
        // do nothing
    }

    @objid ("eb477915-33c7-4b75-acab-25e206925351")
    @Override
    public String getId() {
        return this.desc.getId();
    }

    @objid ("d69e9fd4-cecc-4ef7-8d10-f2134d8ef4f9")
    @Override
    public void mount(final IModelioProgress aMonitor) {
        // nothing to do
    }

    @objid ("faf37e99-97b6-4d16-a148-d32b9ad8dc0e")
    @Override
    public void unmount() {
        // nothing to do
    }

    @objid ("2c2f3b8d-8965-4bcf-8d55-835a16966574")
    @Override
    public GProperties getProperties() {
        return this.desc.getProperties();
    }

    @objid ("c0c2686e-4eeb-4900-9695-3aca58c55b8f")
    @Override
    public Collection<MObject> getRoots() {
        return Collections.emptyList();
    }

    @objid ("ed771c34-b5cc-4193-9e03-2cc5ea9dc5b8")
    @Override
    public FragmentState getState() {
        return FragmentState.DOWN;
    }

    @objid ("6acb9f9d-ed9d-4963-9c1f-d8af30678912")
    @Override
    public Throwable getDownError() {
        return this.downCause;
    }

    @objid ("b79225a7-bf4b-483a-b6ff-ad036b913ded")
    @Override
    public DefinitionScope getScope() {
        return this.desc.getScope();
    }

    @objid ("47e50c66-96fd-48f7-a9e1-cbf89bf11e6c")
    @Override
    public GAuthConf getAuthConfiguration() {
        return this.authConf;
    }

    @objid ("2def712c-d1ec-4ef4-be51-7e6aabafe78b")
    @Override
    public void setDown(final Throwable error) {
        if (! Objects.equals(error, this.downCause)) {
            // Stack errors
            error.addSuppressed(this.downCause);
            this.downCause = error;
        
            // fires a GProjectEvent
            this.project.getMonitorSupport().fireMonitors(GProjectEvent.FragmentDownEvent(this));
        }
    }

    @objid ("28b1d530-b1bf-4a0d-b5e8-2d1e9a4b2c1f")
    @Override
    public MetamodelVersionDescriptor getRequiredMetamodelDescriptor() throws IOException {
        throw new IOException(this.downCause.getLocalizedMessage(), this.downCause);
    }

    @objid ("6db8b8eb-2b3d-403f-82e0-ad4af968b15c")
    @Override
    public void setProject(final GProject project) {
        this.project = project;
    }

    @objid ("1fcbfce8-9882-4a6a-8ea8-e61c60310a5d")
    @Override
    public IFragmentMigrator getMigrator(final MetamodelVersionDescriptor targetMetamodel) throws IOException {
        throw new IOException(this.downCause.getLocalizedMessage(), this.downCause);
    }

    @objid ("6d250920-d266-4aef-a435-7ca0dd0449a8")
    @Override
    public void rename(final String name, final IModelioProgress aMonitor) throws IOException {
        throw new UnsupportedOperationException();
    }

    @objid ("b22ae8f7-7c5f-4706-a5c8-9144d869f772")
    @Override
    public IFragmentInfos getInformations() throws IOException {
        throw new IOException(this.downCause.getLocalizedMessage(), this.downCause);
    }

}
