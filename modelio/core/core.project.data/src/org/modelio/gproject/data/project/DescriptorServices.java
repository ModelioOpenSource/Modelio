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
import org.modelio.vbasic.net.UriUtils;

/**
 * Services that modify a project descriptor.
 */
@objid ("a0ccd9ea-19fa-4daa-84cb-3816d0f8eaa5")
public class DescriptorServices {
    @objid ("d694dacb-869b-483f-9cf6-270c688478ad")
    private  DescriptorServices() {
        // no instance
    }

    /**
     * Merges a project descriptor into another.
     * <p>
     * Fragments and modules details in the source descriptor override the destination descriptor.
     * @param source the descriptor whose content will be merged into the destination descriptor.
     * @param dest the descriptor that will be modified.
     */
    @objid ("6b45b13e-115c-11e2-8a4f-001ec947ccaf")
    public static void merge(ProjectDescriptor source, ProjectDescriptor dest) {
        if (source.getModelioVersion() != null) {
            dest.setModelioVersion(source.getModelioVersion());
        }
        
        if (source.getRemoteLocation() != null) {
            dest.setRemoteLocation(source.getRemoteLocation());
        }
        
        for (FragmentDescriptor  fd: source.getFragments()) {
            FragmentDescriptor my = dest.getFragment(fd.getId());
            if (my == null) {
                dest.getFragments().add(new FragmentDescriptor(fd));
            } else {
                merge(fd, my);
            }
        }
        
        for (ModuleDescriptor md : source.getModules()) {
            ModuleDescriptor same = dest.getModule(md.getName());
            if (same == null) {
                dest.getModules().add(new ModuleDescriptor(md));
            } else {
                merge(md, same);
            }
        }
        
        if (source.getAuthDescriptor().isDefined()) {
            dest.setAuthDescriptor(source.getAuthDescriptor());
        }
        
        dest.getProperties().merge(source.getProperties());
        
        dest.getSharedResources().clear();
        dest.getSharedResources().addAll(source.getSharedResources());
        
    }

    /**
     * Remove all {@link DefinitionScope#LOCAL local} parts from the descriptor.
     * @param projectDescriptor the project descriptor to clear.
     */
    @objid ("bdc4f09a-2f51-4db4-8321-ba230545f58e")
    public static void removeLocalPart(ProjectDescriptor projectDescriptor) {
        Iterator<FragmentDescriptor> it = projectDescriptor.getFragments().iterator();
        while (it.hasNext()) {
            FragmentDescriptor d = it.next();
            if (d.getScope() != DefinitionScope.SHARED) {
                it.remove();
            } else {
                removeScopedPart(d.getAuthDescriptor(), DefinitionScope.LOCAL);
                removeScopedPart(d.getProperties(), DefinitionScope.LOCAL);
            }
        }
        
        Iterator<ModuleDescriptor> mit = projectDescriptor.getModules().iterator();
        while (mit.hasNext()) {
            ModuleDescriptor d = mit.next();
            if (d.getScope() != DefinitionScope.SHARED) {
                mit.remove();
            } else {
                removeScopedPart(d.getAuthDescriptor(), DefinitionScope.LOCAL);
                removeScopedPart(d.getParameters(), DefinitionScope.LOCAL);
            }
        }
        
        removeScopedPart(projectDescriptor.getAuthDescriptor(), DefinitionScope.LOCAL);
        removeScopedPart(projectDescriptor.getProperties(), DefinitionScope.LOCAL);
        
    }

    /**
     * Remove all {@link DefinitionScope#SHARED shared} parts from the descriptor.
     * <p>
     * Replaces shared fragments and modules descriptors by references if they contain
     * local properties or authentication data.
     * @param projectDescriptor the project descriptor to clear.
     */
    @objid ("e803e272-539a-4eab-9b73-e287e88240f2")
    public static void removeSharedPart(final ProjectDescriptor projectDescriptor) {
        Iterator<FragmentDescriptor> it = projectDescriptor.getFragments().iterator();
        while (it.hasNext()) {
            FragmentDescriptor d = it.next();
            removeScopedPart(d.getProperties(), DefinitionScope.SHARED);
            removeScopedPart(d.getAuthDescriptor(), DefinitionScope.SHARED);
        
            if (d.getScope() == DefinitionScope.SHARED) {
                if (d.getProperties().entries().isEmpty() && !d.getAuthDescriptor().isDefined()) {
                    // fragment as no local property, remove it
                    it.remove();
                } else {
                    // Replace fragment by fragment reference
                    d.setScope(null);
                    d.setUri((URI)null);
                    d.setType(null);
                }
            }
        }
        
        Iterator<ModuleDescriptor> mit = projectDescriptor.getModules().iterator();
        while (mit.hasNext()) {
            ModuleDescriptor d = mit.next();
            removeScopedPart(d.getParameters(), DefinitionScope.SHARED);
            removeScopedPart(d.getAuthDescriptor(), DefinitionScope.SHARED);
        
            if (d.getScope() == DefinitionScope.SHARED) {
                if (d.getParameters().entries().isEmpty() && !d.getAuthDescriptor().isDefined()) {
                    mit.remove();
                } else {
                    // Replace module by module reference
                    d.setScope(null);
                    d.setArchiveLocation(null);
                    d.setVersion(null);
                }
            }
        }
        
        removeScopedPart(projectDescriptor.getProperties(), DefinitionScope.SHARED);
        removeScopedPart(projectDescriptor.getAuthDescriptor(), DefinitionScope.SHARED);
        projectDescriptor.getSharedResources().clear();
        
    }

    /**
     * Modifies the descriptor to resolve the descriptor URIs against the given URI.
     * @param projectDescriptor the descriptor to resolve.
     * @param aRemoteUri the remote project URI
     */
    @objid ("aa8a95eb-0eed-11e2-8e4b-001ec947ccaf")
    public static void resolveUris(final ProjectDescriptor projectDescriptor, URI aRemoteUri) {
        URI baseUri = UriUtils.asDirectoryUri(aRemoteUri);
        
        // Resolve fragments
        for (FragmentDescriptor fd : projectDescriptor.getFragments()) {
            if (fd.getUri() != null) {
                fd.setUri(baseUri.resolve(fd.getUri()));
            }
        }
        
        // Resolve modules
        for (ModuleDescriptor md : projectDescriptor.getModules()) {
            if (md.getArchiveLocation() != null) {
                md.setArchiveLocation(baseUri.resolve(md.getArchiveLocation()));
            }
        }
        
    }

    /**
     * Merge the other fragment details into this one.
     * @param fd the fragment to merge into this.
     */
    @objid ("0460ce5e-3019-11e2-8f81-001ec947ccaf")
    private static void merge(FragmentDescriptor fd, FragmentDescriptor dest) {
        assert (Objects.equals(dest.getId(), fd.getId()));
        
        dest.setScope(fd.getScope());
        dest.setType(fd.getType());
        dest.setUri(fd.getUri());
        
        if (fd.getAuthDescriptor().isDefined()) {
            dest.setAuthDescriptor(fd.getAuthDescriptor());
        }
        
        dest.getProperties().merge(fd.getProperties());
        
    }

    /**
     * Merge the other module descriptor into this one.
     * @param source the other module descriptor
     */
    @objid ("046a5765-3019-11e2-8f81-001ec947ccaf")
    private static void merge(ModuleDescriptor source, ModuleDescriptor dest) {
        assert (Objects.equals(dest.getName(), source.getName()));
        
        dest.setArchiveLocation(source.getArchiveLocation());
        dest.getParameters().merge(source.getParameters());
        dest.setScope(source.getScope());
        dest.setVersion(source.getVersion());
        
        if (source.getAuthDescriptor().isDefined()) {
            dest.setAuthDescriptor(source.getAuthDescriptor());
        }
        
    }

    @objid ("9fcc21f1-6b6e-484f-bb79-89af0d1465d1")
    private static void removeScopedPart(AuthDescriptor authDescriptor, DefinitionScope scope) {
        if (authDescriptor == null) {
            return;
        }
        
        if (authDescriptor.getScope() == scope) {
            authDescriptor.setData(null);
        }
        
    }

    @objid ("4a27d6db-9095-4e9a-81b5-bfeca4ce662d")
    private static void removeScopedPart(GProperties props, DefinitionScope toRemove) {
        for (Iterator<Entry> it = props.entries().iterator(); it.hasNext(); ) {
            Entry entry = it.next();
            if (entry.getScope() == toRemove) {
                it.remove();
            }
        }
        
    }

}
