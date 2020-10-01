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

package org.modelio.diagram.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.DelegatingGmNodeFactory;
import org.modelio.diagram.elements.core.model.factory.DiagramFactoryRegistry;
import org.modelio.diagram.elements.core.model.factory.IGmNodeFactory;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.factories.common.DiagramElementsGmNodeFactory;
import org.modelio.diagram.elements.factories.generic.GenericGmFactory;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Implementation of {@link IGmNodeFactory} delegating all requests to cascaded factories in the following order :
 * <ol>
 * <li>The main factory</li>
 * <li>Secondary factories registered as extensions in the {@link DiagramFactoryRegistry}</li>
 * <li>The {@link DiagramElementsGmNodeFactory}</li>
 * <li>Optionally, the {@link GenericGmFactory}</li>
 * </ol>
 */
@objid ("0b27cdf5-957f-48c3-bc33-d362fcff80fc")
public final class StandardGmNodeFactory implements IGmNodeFactory {
    @objid ("425c5931-90a1-4cb5-85c5-2a7cac95a3d7")
    private final IGmNodeFactory mainFactory;

    @objid ("bf508411-9acd-4400-a042-b4cec5bafd4b")
    private final DelegatingGmNodeFactory secondaryFactories;

    @objid ("54dae79a-dbbb-46aa-a43d-31560151827a")
    private final IGmNodeFactory diagramElementsFactory;

    @objid ("7ab9a520-2eac-4451-9c1c-2247f408f7f7")
    private final IGmNodeFactory genericFactory;

    /**
     * Instantiate the factory.
     * 
     * @param factoryId identifier of the main factory.
     * @param useGenericFactory whether or not the generic factory should be used as fallback.
     */
    @objid ("3ef86265-1442-408a-ae23-be5d7eb91a05")
    public StandardGmNodeFactory(String factoryId, boolean useGenericFactory) {
        this.mainFactory = DiagramFactoryRegistry.getInstance().getNodeFactory(factoryId);
        this.secondaryFactories = new DelegatingGmNodeFactory(DiagramFactoryRegistry.getInstance().getExtensions(factoryId));
        this.diagramElementsFactory = new DiagramElementsGmNodeFactory();
        this.genericFactory = useGenericFactory ? new GenericGmFactory() : null;
    }

    @objid ("8eb9f0f8-893a-4aaa-bad7-c6940637eb42")
    @Override
    public GmNodeModel create(IGmDiagram diagram, GmCompositeNode parentNode, MObject elementToUnmask, Object initialLayoutData) {
        // 1 - Main factory
        GmNodeModel ret = this.mainFactory.create(diagram, parentNode, elementToUnmask, initialLayoutData);
        if (ret != null) {
            return ret;
        }
        
        // 2 - Secondary factories
        ret = this.secondaryFactories.create(diagram, parentNode, elementToUnmask, initialLayoutData);
        if (ret != null) {
            return ret;
        }
        
        // 3 - Common elements
        ret = this.diagramElementsFactory.create(diagram, parentNode, elementToUnmask, initialLayoutData);
        if (ret != null) {
            return ret;
        }
        
        if (this.genericFactory != null) {
            return this.genericFactory.create(diagram, parentNode, elementToUnmask, initialLayoutData);
        } else {
            return null;
        }
    }

    @objid ("e0368b36-4491-48df-bef5-8af1b304723c")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        Class<? extends IPersistent> ret = this.mainFactory.resolveClass(namespace);
        if (ret != null) {
            return ret;
        }
        
        ret = this.secondaryFactories.resolveClass(namespace);
        if (ret != null) {
            return ret;
        }
        
        ret = this.diagramElementsFactory.resolveClass(namespace);
        if (ret != null) {
            return ret;
        }
        
        if (this.genericFactory != null) {
            return this.genericFactory.resolveClass(namespace);
        } else {
            return null;
        }
    }

    @objid ("13e451c8-c363-4ca6-9aaf-ddcdf1cf7204")
    @Override
    public Class<? extends Enum<?>> resolveEnumClass(String namespace) {
        Class<? extends Enum<?>> ret = this.mainFactory.resolveEnumClass(namespace);
        if (ret != null) {
            return ret;
        }
        ret = this.secondaryFactories.resolveEnumClass(namespace);
        if (ret != null) {
            return ret;
        }
        
        ret = this.diagramElementsFactory.resolveEnumClass(namespace);
        if (ret != null) {
            return ret;
        }
        
        if (this.genericFactory != null) {
            return this.genericFactory.resolveEnumClass(namespace);
        } else {
            return null;
        }
    }

    @objid ("12cfd800-94af-45f7-a167-b29891590095")
    @Override
    public Class<? extends IPersistentMigrator> resolveMigratorClass(String namespace) {
        Class<? extends IPersistentMigrator> ret = this.mainFactory.resolveMigratorClass(namespace);
        if (ret != null) {
            return ret;
        }
        ret = this.secondaryFactories.resolveMigratorClass(namespace);
        if (ret != null) {
            return ret;
        }
        
        ret = this.diagramElementsFactory.resolveMigratorClass(namespace);
        if (ret != null) {
            return ret;
        }
        
        if (this.genericFactory != null) {
            return this.genericFactory.resolveMigratorClass(namespace);
        } else {
            return null;
        }
    }

    @objid ("232cd8fb-859a-4a75-9fd1-fc376b9727a3")
    public boolean isGenericFactory() {
        return this.genericFactory != null;
    }

}
