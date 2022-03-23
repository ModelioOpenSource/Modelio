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
package org.modelio.uml.usecasediagram.editor.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmLinkFactory;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.uml.usecasediagram.editor.elements.communicationlink.GmCommunicationLink;
import org.modelio.uml.usecasediagram.editor.elements.usecasedependency.GmUseCaseDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * UseCase diagram specific implementation of {@link IGmLinkFactory}.
 * <p>
 * This particular implementation:
 * <ul>
 * <li>does not support cascading</li>
 * <li>only processes use case diagram specific elements</li>
 * </ul>
 * </p>
 */
@objid ("5e8a467b-55b7-11e2-877f-002564c97630")
public final class UseCaseGmLinkFactory implements IGmLinkFactory {
    @objid ("5e8bcce0-55b7-11e2-877f-002564c97630")
    @Override
    public GmLink create(IGmDiagram diagram, MObject linkElement) {
        return (GmLink) linkElement.accept(new ImplVisitor(diagram));
    }

    @objid ("a9db7659-8262-4c8e-b055-7387d00a6879")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        try {
            if (namespace.startsWith("org.modelio.uml.usecasediagram.editor")) {
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

    @objid ("fe3972e4-f071-4856-b451-5e2b94a40e82")
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

    @objid ("f00d45c5-bf18-4ded-96ba-778ff345e8bb")
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

    @objid ("1f415cd1-a455-476c-b627-93ccf7e25234")
    @Override
    public String migrateNamespacing(String namespace) {
        if (namespace.startsWith("org.modelio.diagram.editor.usecase")) {
            return namespace.replaceAll("org.modelio.diagram.editor.usecase", "org.modelio.uml.usecasediagram.editor");
        }
        return namespace;
    }

    @objid ("5e8bccff-55b7-11e2-877f-002564c97630")
    private class ImplVisitor extends DefaultModelVisitor {
        @objid ("d9d4ec57-55c2-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("5e8bcd06-55b7-11e2-877f-002564c97630")
        public  ImplVisitor(IGmDiagram diagram) {
            this.diagram = diagram;
        }

        @objid ("5e8bcd1a-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitAssociationEnd(AssociationEnd role) {
            // Communication link are returned only for associations between actors or usecases.
            if (!(role.getSource() instanceof Actor) && !(role.getSource() instanceof UseCase)) {
                return null;
            }
            AssociationEnd otherEnd = role.getOpposite();
            if (!(otherEnd.getSource() instanceof Actor) && !(otherEnd.getSource() instanceof UseCase)) {
                return null;
            }
            return new GmCommunicationLink(this.diagram, role, new MRef(role), new MRef(role.getAssociation()));
        }

        @objid ("5e8bcd0b-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitUseCaseDependency(UseCaseDependency theUseCaseDependency) {
            return new GmUseCaseDependency(this.diagram,
                                theUseCaseDependency,
                                new MRef(theUseCaseDependency));
            
        }

    }

}
