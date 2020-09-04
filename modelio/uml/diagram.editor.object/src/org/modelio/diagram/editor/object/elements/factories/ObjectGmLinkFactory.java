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

package org.modelio.diagram.editor.object.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmLinkFactory;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Deployment diagram specific implementation of {@link IGmLinkFactory}.
 * <p>
 * This particular implementation:
 * <ul>
 * <li>does not support cascading</li>
 * <li>only processes deployment diagram specific elements</li>
 * </ul>
 * </p>
 */
@objid ("cf326a4c-4778-4a73-9a54-6591aadce9fd")
public final class ObjectGmLinkFactory implements IGmLinkFactory {
    @objid ("faa9e6b4-a577-4b3f-90a7-859eb9ce1618")
    @Override
    public GmLink create(IGmDiagram diagram, MObject linkElement) {
        return (GmLink) linkElement.accept(new ImplVisitor(diagram));
    }

    @objid ("50e311cf-fefe-4332-940c-f1c799eeb5bc")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        return null;
    }

    @objid ("d278d0b9-fe44-42b1-af76-894b6d87f034")
    @Override
    public Class<? extends IPersistentMigrator> resolveMigratorClass(String classNamespace) {
        try {
            Class<?> clazz = Class.forName(classNamespace);
            if (clazz != null) {
                return clazz.asSubclass(IPersistentMigrator.class);
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
        }
        return null;
    }

    @objid ("443c33e5-3aa9-4bce-aa11-88444b1f4ae7")
    @Override
    public Class<? extends Enum<?>> resolveEnumClass(String enumNamespace) {
        try {
            Class<?> clazz = Class.forName(enumNamespace);
            if (clazz != null && clazz.isEnum()) {
                return (Class<? extends Enum<?>>) clazz;
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Enum not found, return null
        }
        return null;
    }

    /**
     * visitor class for the implementation of the links.
     */
    @objid ("b0b67782-df46-47a4-a396-9d19f6f96d8e")
    private class ImplVisitor extends DefaultModelVisitor {
        @objid ("9d21c970-b234-4017-8d9f-500da7a5fcd2")
        private IGmDiagram diagram;

        @objid ("b693e6cd-c895-429d-ab24-6d49c7c5e0c1")
        public ImplVisitor(IGmDiagram diagram) {
            this.diagram = diagram;
        }

    }

}
