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

package org.modelio.diagram.elements.core.model.factory;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Implementation of {@link IGmLinkFactory} delegating all request to its cascaded factories extensions.
 * <p>
 * This factory is dynamically enriched by diagram plugins, so that it ends by being able to process elements from several metamodel.<br/>
 * </p>
 * <p>
 * Therefore, no ordering can be assumed in the delegation mechanism.
 * </p>
 */
@objid ("4162fc78-1578-4bf1-9412-a2b5bb60ee89")
public class DelegatingGmLinkFactory implements IGmLinkFactory {
    /**
     * Cascaded factories catalog(if any).
     */
    @objid ("9de16d1a-5c4b-4d42-b361-16d15d36c168")
    private List<IGmLinkFactory> cascadedFactories = new ArrayList<>();

    /**
     * Instantiate the factory.
     * 
     * @param factoryIds identifier of the cascaded factories needed to call the {@link DiagramFactoryRegistry}.
     */
    @objid ("c8468442-8806-418b-be1f-b9a26cf8267f")
    public DelegatingGmLinkFactory(List<String> factoryIds) {
        this.cascadedFactories = new ArrayList<>();
        for (String factoryId : factoryIds) {
            IGmLinkFactory cascadedFactory = DiagramFactoryRegistry.getInstance().getLinkFactory(factoryId);
            if (cascadedFactory != null) {
                this.cascadedFactories.add(cascadedFactory);
            }
        }
    }

    @objid ("7d8a4493-4d40-43b7-af3d-51a29d52ddfa")
    @Override
    public GmLink create(IGmDiagram diagram, MObject linkElement) {
        // First ask to the cascaded factories
        for (IGmLinkFactory factory : this.cascadedFactories) {
            GmLink newLink = factory.create(diagram, linkElement);
            if (newLink != null) {
                return newLink;
            }
        }
        return null;
    }

    @objid ("51028c64-d3fb-412b-b2bc-a1fb34e244f5")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        // Ask the cascaded factories
        for (IGmLinkFactory f : this.cascadedFactories) {
            Class<? extends IPersistent> ret = f.resolveClass(namespace);
            if (ret != null) {
                return ret;
            }
        }
        return null;
    }

    @objid ("a4627e5e-761a-4edf-b263-f5e788df539f")
    @Override
    public Class<? extends IPersistentMigrator> resolveMigratorClass(String namespace) {
        // Ask the cascaded factories
        for (IGmLinkFactory f : this.cascadedFactories) {
            Class<? extends IPersistentMigrator> ret = f.resolveMigratorClass(namespace);
            if (ret != null) {
                return ret;
            }
        }
        return null;
    }

    @objid ("5cffda90-a8ed-4b9e-82ae-a7ad4e0ab766")
    @Override
    public Class<? extends Enum<?>> resolveEnumClass(String namespace) {
        // Ask the cascaded factories
        for (IGmLinkFactory f : this.cascadedFactories) {
            Class<? extends Enum<?>> ret = f.resolveEnumClass(namespace);
            if (ret != null) {
                return ret;
            }
        }
        return null;
    }

    /**
     * Register a cascaded factory.
     * 
     * @param factory the link factory extension.
     */
    @objid ("69d15b28-f955-4012-8387-5c5f94fe7832")
    public void registerFactory(IGmLinkFactory factory) {
        if (factory != null && !this.cascadedFactories.contains(factory)) {
            this.cascadedFactories.add(factory);
        }
    }

    /**
     * Remove a registered cascaded factory.
     * 
     * @param factory the link factory extension.
     */
    @objid ("31b5e39f-2e5f-4b23-8deb-9d76455d9949")
    public void unregisterFactory(IGmLinkFactory factory) {
        this.cascadedFactories.remove(factory);
    }

    /**
     * Instantiate the factory.
     * 
     * @param factory a cascaded factory.
     */
    @objid ("a51e0b48-b222-4311-b34b-7d8cf0a2ffd1")
    public DelegatingGmLinkFactory(IGmLinkFactory factory) {
        this.cascadedFactories = new ArrayList<>();
        this.cascadedFactories.add(factory);
    }

}
