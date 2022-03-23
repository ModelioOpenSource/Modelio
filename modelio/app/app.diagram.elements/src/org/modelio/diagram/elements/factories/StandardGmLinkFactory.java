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
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.DelegatingGmLinkFactory;
import org.modelio.diagram.elements.core.model.factory.DiagramFactoryRegistry;
import org.modelio.diagram.elements.core.model.factory.IGmLinkFactory;
import org.modelio.diagram.elements.factories.common.DiagramElementsGmLinkFactory;
import org.modelio.diagram.elements.factories.generic.GenericGmFactory;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Implementation of {@link IGmLinkFactory} delegating all requests to cascaded factories in the following order :
 * <ol>
 * <li>The main factory</li>
 * <li>Secondary factories registered as extensions in the {@link DiagramFactoryRegistry}</li>
 * <li>The {@link DiagramElementsGmLinkFactory}</li>
 * <li>Optionally, the {@link GenericGmFactory}</li>
 * </ol>
 */
@objid ("5aef14e3-8971-4f7a-bbb0-71b1b51760ac")
public final class StandardGmLinkFactory implements IGmLinkFactory {
    @objid ("b49c84a5-4edb-4a22-ba9c-ac5a900d1837")
    private final IGmLinkFactory mainFactory;

    @objid ("716aff19-839c-4f17-9cd8-918e1bbc6d3b")
    private final DelegatingGmLinkFactory secondaryFactories;

    @objid ("7bd844f9-db92-43b0-89ab-9ebb374d36cd")
    private final IGmLinkFactory diagramElementsFactory;

    @objid ("3300765e-c812-4459-8e73-e9dcb436604a")
    private final IGmLinkFactory genericFactory;

    /**
     * Instantiate the factory.
     * @param factoryId identifier of the main factory.
     * @param useGenericFactory whether or not the generic factory should be used as fallback.
     */
    @objid ("aa475681-82d9-44a8-ace2-154dc4d23e60")
    public  StandardGmLinkFactory(String factoryId, boolean useGenericFactory) {
        this.mainFactory = DiagramFactoryRegistry.getInstance().getLinkFactory(factoryId);
        this.secondaryFactories = new DelegatingGmLinkFactory(DiagramFactoryRegistry.getInstance().getExtensions(factoryId));
        this.diagramElementsFactory = new DiagramElementsGmLinkFactory();
        this.genericFactory = useGenericFactory ? new GenericGmFactory() : null;
        
    }

    @objid ("5c2e682f-3982-4f24-9894-7218f52dbe05")
    @Override
    public GmLink create(IGmDiagram diagram, MObject linkElement) {
        // 1 - Main factory
        GmLink linkModel = this.mainFactory.create(diagram, linkElement);
        if (linkModel != null) {
            return linkModel;
        }
        
        // 2 - Secondary factories
        linkModel = this.secondaryFactories.create(diagram, linkElement);
        if (linkModel != null) {
            return linkModel;
        }
        
        // 3 - Common elements
        linkModel = this.diagramElementsFactory.create(diagram, linkElement);
        if (linkModel != null) {
            return linkModel;
        }
        
        if (this.genericFactory != null) {
            return this.genericFactory.create(diagram, linkElement);
        } else {
            return null;
        }
        
    }

    @objid ("6477dbf7-22fa-4ee3-9680-2e5953c975bb")
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

    @objid ("8e922d3d-9000-4741-aa37-7d2d95207bad")
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

    @objid ("a96bb8b7-96ba-427c-9d45-a213884dfeff")
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

    @objid ("227bbfa2-fd17-4610-83c0-9955b4bc3f3f")
    public boolean isGenericFactory() {
        return this.genericFactory != null;
    }

}
