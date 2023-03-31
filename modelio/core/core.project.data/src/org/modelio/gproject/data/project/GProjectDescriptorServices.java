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
package org.modelio.gproject.data.project;

import java.net.URI;
import java.util.Iterator;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.GProperties.Entry;
import org.modelio.gproject.data.project.auth.AuthDescriptor;
import org.modelio.vbasic.net.UriUtils;

/**
 * Services that modify a project descriptor.
 */
@objid ("0feafdd4-7695-4705-bc2c-d7e05772b34e")
public class GProjectDescriptorServices {
    @objid ("0290c298-1be9-44fc-b9fe-a669347f2b3b")
    private  GProjectDescriptorServices() {
        // no instance
    }

    /**
     * Merges a project descriptor into another.
     * <p>
     * Fragments and modules details in the source descriptor override the destination descriptor.
     * @param source the descriptor whose content will be merged into the destination descriptor.
     * @param dest the descriptor that will be modified.
     */
    @objid ("4ef56a15-d9be-49f2-8477-6872ae632277")
    public static void merge(GProjectDescriptor source, GProjectDescriptor dest) {
        if (source.getModelioVersion() != null) {
            dest.setModelioVersion(source.getModelioVersion());
        }
        
        if (source.getRemoteLocation() != null) {
            dest.setRemoteLocation(source.getRemoteLocation());
        }
        
        for (GProjectPartDescriptor fd : source.getPartDescriptors()) {
            GProjectPartDescriptor my = dest.getPartDescriptor(fd.getId());
            if (my == null) {
                dest.getPartDescriptors().add(new GProjectPartDescriptor(fd));
            } else {
                merge(fd, my);
            }
        }
        
        if (source.getAuthDescriptor().isDefined()) {
            dest.setAuthDescriptor(source.getAuthDescriptor());
        }
        
        dest.getProperties().merge(source.getProperties());
        
    }

    /**
     * Remove all {@link DefinitionScope#LOCAL local} parts from the descriptor.
     * @param projectDescriptor the project descriptor to clear.
     */
    @objid ("c5e7a88c-8b3d-4474-b72b-564ffc9f6de3")
    public static void removeLocalPart(GProjectDescriptor projectDescriptor) {
        Iterator<GProjectPartDescriptor> it = projectDescriptor.getPartDescriptors().iterator();
        while (it.hasNext()) {
            GProjectPartDescriptor d = it.next();
            if (d.getDefinitionScope() != DefinitionScope.SHARED) {
                it.remove();
            } else {
                removeScopedPart(d.getAuth(), DefinitionScope.LOCAL);
                removeScopedProperties(d.getProperties(), DefinitionScope.LOCAL);
            }
        }
        
        removeScopedPart(projectDescriptor.getAuthDescriptor(), DefinitionScope.LOCAL);
        removeScopedProperties(projectDescriptor.getProperties(), DefinitionScope.LOCAL);
        
    }

    /**
     * Remove all {@link DefinitionScope#SHARED shared} parts from the descriptor.
     * <p>
     * Replaces shared fragments and modules descriptors by references
     * if they contain local properties or authentication data.
     * @param projectDescriptor the project descriptor to clear.
     */
    @objid ("90cf6221-2faf-43cc-a761-3adb27d6bfb7")
    public static void removeSharedPart(final GProjectDescriptor projectDescriptor) {
        Iterator<GProjectPartDescriptor> it = projectDescriptor.getPartDescriptors().iterator();
        while (it.hasNext()) {
            GProjectPartDescriptor d = it.next();
            removeScopedProperties(d.getProperties(), DefinitionScope.SHARED);
            removeScopedPart(d.getAuth(), DefinitionScope.SHARED);
        
            if (d.getDefinitionScope() == DefinitionScope.SHARED) {
                if (d.getProperties().entries().isEmpty() && !d.getAuth().isDefined()) {
                    // fragment as no local property, remove it
                    it.remove();
                } else {
                    // Replace fragment by fragment reference
                    d.setDefinitionScope(null);
                    d.setLocation((URI) null);
                    d.setVersion(null);
                }
            }
        }
        
        removeScopedProperties(projectDescriptor.getProperties(), DefinitionScope.SHARED);
        removeScopedPart(projectDescriptor.getAuthDescriptor(), DefinitionScope.SHARED);
        
    }

    /**
     * Modifies the descriptor to resolve the descriptor URIs against the given URI.
     * @param projectDescriptor the descriptor to resolve.
     * @param aRemoteUri the remote project URI
     */
    @objid ("ad7f9d4b-4dde-40c7-8383-db109a806ad6")
    public static void resolveUris(final GProjectDescriptor projectDescriptor, URI aRemoteUri) {
        URI baseUri = UriUtils.asDirectoryUri(aRemoteUri);
        
        // Resolve fragments
        for (GProjectPartDescriptor fd : projectDescriptor.getPartDescriptors()) {
            if (fd.getLocation() != null) {
                fd.setLocation(baseUri.resolve(fd.getLocation()));
            }
        }
        
    }

    /**
     * Merge the other part details into this one.
     * @param srcDesc the part to merge into this.
     */
    @objid ("a2d0923b-0cbc-4054-9aad-0348bdd6f5ee")
    private static void merge(GProjectPartDescriptor srcDesc, GProjectPartDescriptor dest) {
        assert (Objects.equals(dest.getId(), srcDesc.getId()));
        assert (Objects.equals(srcDesc.getType(), dest.getType()));
        
        dest.setDefinitionScope(srcDesc.getDefinitionScope());
        dest.setVersion(srcDesc.getVersion());
        dest.setLocation(srcDesc.getLocation());
        
        if (srcDesc.getLabel() != null) {
            dest.setLabel(srcDesc.getLabel());
        }
        
        AuthDescriptor srcAuth = srcDesc.getAuth();
        if (srcAuth != null && srcAuth.isDefined()) {
            dest.setAuth(new AuthDescriptor(srcAuth));
        }
        
        dest.getProperties().merge(srcDesc.getProperties());
        
    }

    /**
     * Clean authentication data if the scope matches the given one
     * @param authDescriptor the authentication descriptor
     * @param scope the scope to remove
     */
    @objid ("c89c8a8f-17c5-4413-85e2-0c5ce4660c26")
    private static void removeScopedPart(AuthDescriptor authDescriptor, DefinitionScope scope) {
        if (authDescriptor == null) {
            return;
        }
        
        if (authDescriptor.getScope() == scope) {
            authDescriptor.setData(null);
        }
        
    }

    /**
     * Remove all {@link GProperties} entries that have the given scope
     * @param props the properties to clean
     * @param scope the scope to remove
     */
    @objid ("ba207084-561f-4584-8c39-40d5fe9c4fc6")
    private static void removeScopedProperties(GProperties props, DefinitionScope scope) {
        for (Iterator<Entry> it = props.entries().iterator(); it.hasNext();) {
            Entry entry = it.next();
            if (entry.getScope() == scope) {
                it.remove();
            }
        }
        
    }

}
