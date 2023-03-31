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
package org.modelio.gproject.parts;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.core.IGPart;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.core.IGPartState.GPartStateEnum;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.project.auth.AuthDescriptor;
import org.modelio.gproject.data.project.auth.InheritedAuthData;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.version.Version;

/**
 * Abstract implementation of {@link IGPart}.
 * <p>
 * The class conveniently manages the common features of all {@link IGPart} implementations.<br/>
 * Implementers of {@link IGPart} are strongly invited to derive from this class to provide additional implementations.
 * </p>
 */
@objid ("e81636e5-c810-4f58-b323-f5cf6c9190a4")
public abstract class AbstractGPart implements IGPart {
    @objid ("64322083-43a0-43a7-9252-67d0990bfa88")
    private static final String PROP_ISACTIVE = "isActive";

    @objid ("bc835c2d-bddb-43a1-bd91-d698d1592405")
    private static final long serialVersionUID = 1L;

    @objid ("c0f9ed41-25b4-4f76-a90a-fd455f408cb6")
    private final GProjectPartDescriptor descriptor;

    /**
     * The part {@link GPartState } state manager.
     */
    @objid ("85d4d5e3-a68b-4265-92e4-d7e56db99ba4")
    protected final GPartState state;

    @objid ("52d8d51d-e9c4-4a71-82e3-ee74e9997e1f")
    private IGProject project;

    @objid ("7f05765a-d420-4588-8fbf-962349dd027f")
    protected  AbstractGPart(GProjectPartDescriptor descriptor) {
        this.descriptor = descriptor;
        this.state = new GPartState(this);
        
        if (this.descriptor.getAuth() == null) {
            // Many callers of getAuth() don't expect null return value.
            // Some of them also directly edit the returned descriptor and expect it to be persisted.
            this.descriptor.setAuth(new AuthDescriptor(null, DefinitionScope.LOCAL));
        }
        
    }

    /**
     * Get the authData for this GModule by resolving its descriptor fields. If none, fallback to the project authData if possible.
     * @return the IAuthData to use for this GModule MDA fragment. May be <code>null</code>.
     */
    @objid ("f9aa92cc-eb03-4a5a-b1ac-3d0de2570a39")
    protected IAuthData resolveAuthData() {
        IAuthData authData = getAuth().getData();
        if (InheritedAuthData.matches(authData)) {
            return this.project.getAuth().getData();
        } else {
            return authData;
        }
        
    }

    /**
     * Get the project this part is currently mounted in.
     * @return the project this part is mounted in or <code>null</code> if the part is not currently mounted in a project.
     */
    @objid ("b6eec665-9ff0-4bda-bbfd-8f40905b0b67")
    @Override
    public IGProject getProject() {
        return this.project;
    }

    @objid ("782bd200-58a7-4e97-96a0-5ed6a7a43b06")
    @Override
    public AuthDescriptor getAuth() {
        return this.descriptor.getAuth();
    }

    @objid ("d943dccd-7c60-4dec-8841-764ec264c1a8")
    @Override
    public String getId() {
        return this.descriptor.getId();
    }

    @objid ("6d320444-08b1-4949-b877-178e145310f9")
    @Override
    public Version getVersion() {
        return this.descriptor.getVersion();
    }

    @objid ("8e57c3f7-236b-4126-bbfc-469a2d1c93ad")
    @Override
    public GPartState getState() {
        return this.state;
    }

    @objid ("507f1671-c9d2-4f23-870b-4df98624f4d0")
    @Override
    public final GProjectPartType getType() {
        return this.descriptor.getType();
    }

    /**
     * Get the descriptor instance that reflects <code>this</code> part.
     */
    @objid ("fb136e90-06e7-442a-a011-c0360751a8c7")
    @Override
    public GProjectPartDescriptor getDescriptor() {
        return this.descriptor;
    }

    @objid ("acc9c55e-33cc-4fad-bfed-a050f3d1e1dd")
    @Override
    public GProperties getProperties() {
        return this.descriptor.getProperties();
    }

    @objid ("62a51ab5-e6f7-46ac-866d-1ead73057d33")
    protected void setProperties(GProperties properties) {
        this.descriptor.setProperties(properties);
    }

    @objid ("e7c4da10-acc2-407c-a921-5d48e31551e4")
    @Override
    public DefinitionScope getDefinitionScope() {
        return this.descriptor.getDefinitionScope();
    }

    @objid ("471d7715-5c0d-473e-8e53-f3386612b068")
    @Override
    public boolean isActive() {
        return this.getProperties().getBooleanValue(PROP_ISACTIVE, true);
    }

    @objid ("80c897ca-27fa-48cb-aa01-50340d40ae41")
    @Override
    public void setActive(boolean b) {
        DefinitionScope scope = this.getProperties().getPropertyScope(PROP_ISACTIVE, DefinitionScope.LOCAL);
        this.getProperties().setBooleanProperty(PROP_ISACTIVE, b, scope);
        
    }

    @objid ("9c5ba9bb-54f0-4bd0-85f6-0f3061943783")
    @Override
    public void install(IGProject aProject, IModelioProgress monitor) throws GPartException {
        this.project = aProject;
        if (this.state.sendInstall() != GPartStateEnum.INSTALLED) {
            throw new GPartException("Install failed");
        }
        
    }

    @objid ("7def9691-f4a6-4642-98eb-5dc38bc3cd66")
    @Override
    public void uninstall(IGProject aProject, IModelioProgress monitor) throws GPartException {
        if (this.state.sendUninstall() != GPartStateEnum.UNINSTALLED) {
            throw new GPartException("Uninstall failed");
        }
        this.project = null;
        
    }

    @objid ("c57e6974-ed27-404e-a534-104a87090cac")
    @Override
    public String toString() {
        return String.format("GPart [id='%s', version='%s', type='%s', definitionScope='%s', state='%s']",
                this.getId(), this.getVersion(), this.getType(), this.getDefinitionScope(), this.getState().getValue());
        
    }

}
