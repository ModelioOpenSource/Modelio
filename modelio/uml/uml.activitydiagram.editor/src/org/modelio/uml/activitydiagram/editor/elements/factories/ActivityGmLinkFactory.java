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

package org.modelio.uml.activitydiagram.editor.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmLinkFactory;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.uml.activitydiagram.editor.elements.controlflow.GmControlFlow;
import org.modelio.uml.activitydiagram.editor.elements.exceptionhandler.GmExceptionHandler;
import org.modelio.uml.activitydiagram.editor.elements.objectflow.GmObjectFlow;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Activity diagram specific implementation of {@link IGmLinkFactory}.
 * <p>
 * This particular implementation:
 * <ul>
 * <li>does not support cascading</li>
 * <li>only processes activity diagram specific elements</li>
 * </ul>
 * </p>
 */
@objid ("2a8dab7a-55b6-11e2-877f-002564c97630")
public class ActivityGmLinkFactory implements IGmLinkFactory {
    @objid ("2a8dab83-55b6-11e2-877f-002564c97630")
    @Override
    public GmLink create(IGmDiagram diagram, MObject linkElement) {
        return (GmLink) linkElement.accept(new ImplVisitor(diagram));
    }

    @objid ("3ba58b8b-897b-4e16-bb28-3af57e5a528d")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        try {
            String fixedNamespace = migrateNamespacing(namespace);
            if (namespace.startsWith("org.modelio.uml.activitydiagram.editor")) {
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

    @objid ("bdd52894-3bc0-423a-9249-bdbd450eabb7")
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

    @objid ("9c61c88b-475d-4fb4-bebb-1c97d93f1191")
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

    @objid ("af2def7a-de53-43c8-9b2b-e9960394ab3b")
    @Override
    public String migrateNamespacing(String namespace) {
        if (namespace.startsWith("org.modelio.diagram.editor.activity")) {
            return namespace.replaceAll("org.modelio.diagram.editor.activity", "org.modelio.uml.activitydiagram.editor");
        }
        return namespace;
    }

    /**
     * visitor class for the implementation of the links.
     */
    @objid ("2a8daba2-55b6-11e2-877f-002564c97630")
    private class ImplVisitor extends DefaultModelVisitor {
        @objid ("d21050ae-55c0-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("2a8f321b-55b6-11e2-877f-002564c97630")
        public ImplVisitor(IGmDiagram diagram) {
            this.diagram = diagram;
        }

        @objid ("2a8f3228-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitControlFlow(ControlFlow theControlFlow) {
            final GmControlFlow controlFlow = new GmControlFlow(this.diagram,
                    theControlFlow,
                    new MRef(theControlFlow));
            return controlFlow;
        }

        @objid ("2a8f3238-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitExceptionHandler(ExceptionHandler theExceptionHandler) {
            final GmExceptionHandler exception = new GmExceptionHandler(this.diagram,
                    theExceptionHandler,
                    new MRef(theExceptionHandler));
            return exception;
        }

        @objid ("2a8f3230-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitObjectFlow(ObjectFlow theObjectFlow) {
            final GmObjectFlow objectFlow = new GmObjectFlow(this.diagram,
                    theObjectFlow,
                    new MRef(theObjectFlow));
            return objectFlow;
        }

    }

}
