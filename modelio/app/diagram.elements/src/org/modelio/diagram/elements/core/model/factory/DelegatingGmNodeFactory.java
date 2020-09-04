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

package org.modelio.diagram.elements.core.model.factory;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Implementation of {@link IGmNodeFactory} delegating all request to its cascaded factories extensions.
 * <p>
 * This factory is dynamically enriched by diagram plugins, so that it ends by being able to process elements from several metamodel.<br/>
 * </p>
 * <p>
 * Therefore, no ordering can be assumed in the delegation mechanism.
 * </p>
 */
@objid ("4ee4d883-1e36-41b7-80bb-7ec6e216a967")
public final class DelegatingGmNodeFactory implements IGmNodeFactory {
    /**
     * Cascaded factories catalog(if any).
     */
    @objid ("a719beb5-73ef-4dd9-a90a-7c0e4f451944")
    private final List<IGmNodeFactory> cascadedFactories;

    /**
     * Instantiate the factory.
     * 
     * @param factoryIds identifier of the cascaded factories needed to call the {@link DiagramFactoryRegistry}.
     */
    @objid ("6ea497f0-0ee7-4e63-952a-3fd82a01b2a0")
    public DelegatingGmNodeFactory(List<String> factoryIds) {
        this.cascadedFactories = new ArrayList<>();
        for (String factoryId : factoryIds) {
            IGmNodeFactory cascadedFactory = DiagramFactoryRegistry.getInstance().getNodeFactory(factoryId);
            if (cascadedFactory != null) {
                this.cascadedFactories.add(cascadedFactory);
            }
        }
    }

    @objid ("6cfe3f0e-f6cf-46d0-b23e-21db3a624342")
    @Override
    public GmNodeModel create(IGmDiagram diagram, GmCompositeNode parentNode, MObject elementToUnmask, Object initialLayoutData) {
        // Ask the cascaded factories
        for (IGmNodeFactory factory : this.cascadedFactories) {
            GmNodeModel nodeModel = factory.create(diagram, parentNode, elementToUnmask, initialLayoutData);
            if (nodeModel != null) {
                return nodeModel;
            }
        }
        return null;
    }

    @objid ("0b4595ac-40a7-43fa-bce9-67f7719fed30")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        // Ask the cascaded factories
        for (IGmNodeFactory f : this.cascadedFactories) {
            Class<? extends IPersistent> ret = f.resolveClass(namespace);
            if (ret != null) {
                return ret;
            }
        }
        return null;
    }

    @objid ("d529cc62-f5a6-472b-940d-f7dd869571a6")
    @Override
    public Class<? extends IPersistentMigrator> resolveMigratorClass(String namespace) {
        // Ask the cascaded factories
        for (IGmNodeFactory f : this.cascadedFactories) {
            Class<? extends IPersistentMigrator> ret = f.resolveMigratorClass(namespace);
            if (ret != null) {
                return ret;
            }
        }
        return null;
    }

    @objid ("8961bdf8-7cad-427a-8397-289ddc19b57a")
    @Override
    public Class<? extends Enum<?>> resolveEnumClass(String namespace) {
        // Ask the cascaded factories
        for (IGmNodeFactory f : this.cascadedFactories) {
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
     * @param factory the node factory extension.
     */
    @objid ("90116ded-837a-4861-871c-1930dacdaa81")
    public void registerFactory(IGmNodeFactory factory) {
        if (factory != null && !this.cascadedFactories.contains(factory)) {
            this.cascadedFactories.add(factory);
        }
    }

    /**
     * Remove a registered cascaded factory.
     * 
     * @param factory the node factory extension.
     */
    @objid ("fa8bc1c2-a1b0-4716-bf75-c92346381c90")
    public void unregisterFactory(IGmNodeFactory factory) {
        this.cascadedFactories.remove(factory);
    }

    /**
     * Instantiate the factory.
     * 
     * @param factory a cascaded factory.
     */
    @objid ("07312c92-6313-412b-9c6a-a434322bd5eb")
    public DelegatingGmNodeFactory(IGmNodeFactory factory) {
        this.cascadedFactories = new ArrayList<>();
        this.cascadedFactories.add(factory);
    }

}
