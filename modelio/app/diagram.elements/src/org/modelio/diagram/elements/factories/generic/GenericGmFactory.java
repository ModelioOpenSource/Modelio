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

package org.modelio.diagram.elements.factories.generic;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.genericlink.GmGenericLink;
import org.modelio.diagram.elements.common.genericnode.GmGenericNode;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmLinkFactory;
import org.modelio.diagram.elements.core.model.factory.IGmNodeFactory;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Implementation of {@link IGmNodeFactory} and {@link IGmLinkFactory} always returning generic GMs.
 */
@objid ("d870ebfa-0c69-4ff8-ad4a-a6824b99a4f1")
public class GenericGmFactory implements IGmNodeFactory, IGmLinkFactory {
    /**
     * @see MClass#isLinkMetaclass()
     * @return a {@link GmGenericNode} for any {@link ModelElement} which metaclass is tagged as a node.
     */
    @objid ("dacefe91-521d-4a92-a593-3a58869381b1")
    @Override
    public GmGenericNode create(IGmDiagram diagram, GmCompositeNode parentNode, MObject elementToUnmask, Object initialLayoutData) {
        if (!elementToUnmask.getMClass().isLinkMetaclass() && elementToUnmask instanceof ModelElement) {
            GmGenericNode node = new GmGenericNode(diagram, (ModelElement) elementToUnmask, new MRef(elementToUnmask));
            node.setLayoutData(initialLayoutData);
            parentNode.addChild(node);
            return node;
        } else {
            return null;
        }
    }

    /**
     * @see MClass#isLinkMetaclass()
     * @return a {@link GmGenericLink} for any {@link ModelElement} which metaclass is tagged as a node.
     */
    @objid ("e78da893-6d3b-46b0-8878-d1e0529c13e7")
    @Override
    public GmGenericLink create(IGmDiagram diagram, MObject elementToUnmask) {
        if (elementToUnmask.getMClass().isLinkMetaclass() && elementToUnmask instanceof ModelElement) {
            return new GmGenericLink(diagram, (ModelElement) elementToUnmask, new MRef(elementToUnmask));
        } else {
            return null;
        }
    }

    @objid ("4faf68ab-11c7-4a25-9dd7-d9cbd40241f8")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        // Look for elements belonging to the DiagramElements plugin
        try {
            if (namespace.startsWith("org.modelio.diagram.elements.common.generic")) {
                Class<?> clazz = Class.forName(namespace);
                if (clazz != null) {
                    return clazz.asSubclass(IPersistent.class);
                }
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
        }
        return null;
    }

    @objid ("f072b77a-3977-4f29-a676-2c708bd1461b")
    @Override
    public Class<? extends IPersistentMigrator> resolveMigratorClass(String namespace) {
        return null;
    }

    @objid ("cd34ecac-fe2c-43c5-9d0b-37cbfcdf17c2")
    @Override
    public Class<? extends Enum<?>> resolveEnumClass(String namespace) {
        return null;
    }

}
