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

package org.modelio.uml.deploymentdiagram.editor.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmLinkFactory;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.uml.deploymentdiagram.editor.elements.manifestation.GmManifestation;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

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
@objid ("972b26a1-55b6-11e2-877f-002564c97630")
public final class DeploymentGmLinkFactory implements IGmLinkFactory {
    @objid ("972b26aa-55b6-11e2-877f-002564c97630")
    @Override
    public GmLink create(IGmDiagram diagram, MObject linkElement) {
        return (GmLink) linkElement.accept(new ImplVisitor(diagram));
    }

    @objid ("a776a2e8-0be6-49eb-9e19-8ee72b7f7140")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        try {
            String fixedNamespace = migrateNamespacing(namespace);
        
            if (namespace.startsWith("org.modelio.uml.deploymentdiagram.editor")) {
                Class<?> clazz = Class.forName(fixedNamespace);
                if (clazz != null) {
                    return clazz.asSubclass(IPersistent.class);
                }
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
        }
        return null;
    }

    @objid ("4165b101-a7e5-4ed9-a64e-13d9c5adba83")
    @Override
    public Class<? extends IPersistentMigrator> resolveMigratorClass(String classNamespace) {
        try {
        
            String fixedNamespace = migrateNamespacing(classNamespace);
        
            Class<?> clazz = Class.forName(fixedNamespace);
            if (clazz != null) {
                return clazz.asSubclass(IPersistentMigrator.class);
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
        }
        return null;
    }

    @objid ("9aeb5fb4-5413-45cd-82fd-4bf9e3a4bcc2")
    @Override
    public Class<? extends Enum<?>> resolveEnumClass(String enumNamespace) {
        try {
            String fixedNamespace = migrateNamespacing(enumNamespace);
        
            Class<?> clazz = Class.forName(fixedNamespace);
            if (clazz != null && clazz.isEnum()) {
                return (Class<? extends Enum<?>>) clazz;
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Enum not found, return null
        }
        return null;
    }

    @objid ("a5ecfcc4-8486-48c3-83c7-77792698f40f")
    @Override
    public String migrateNamespacing(String namespace) {
        if (namespace.startsWith("org.modelio.diagram.editor.deployment")) {
            return namespace.replaceAll("org.modelio.diagram.editor.deployment", "org.modelio.uml.deploymentdiagram.editor");
        }
        return namespace;
    }

    /**
     * visitor class for the implementation of the links.
     */
    @objid ("972b26c9-55b6-11e2-877f-002564c97630")
    private class ImplVisitor extends DefaultModelVisitor {
        @objid ("1cf183f5-55c2-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("972b26d0-55b6-11e2-877f-002564c97630")
        public ImplVisitor(IGmDiagram diagram) {
            this.diagram = diagram;
        }

        @objid ("972b26d5-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitManifestation(Manifestation theManifestation) {
            return new GmManifestation(this.diagram, theManifestation, new MRef(theManifestation));
        }

    }

}
