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
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmNodeFactory;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.uml.usecasediagram.editor.elements.actor.GmActor;
import org.modelio.uml.usecasediagram.editor.elements.extensionpoint.GmExtensionPoint;
import org.modelio.uml.usecasediagram.editor.elements.usecase.GmUseCase;
import org.modelio.uml.usecasediagram.editor.plugin.DiagramEditorUseCase;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * UseCase diagram specific implementation of {@link IGmNodeFactory}.
 * <p>
 * This particular implementation:
 * <ul>
 * <li>does not support cascading</li>
 * <li>only processes use case diagram specific elements</li>
 * </ul>
 */
@objid ("5e8d5386-55b7-11e2-877f-002564c97630")
public final class UseCaseGmNodeFactory implements IGmNodeFactory {
    @objid ("43b8664e-0cd8-4c1f-a29e-1f4d825c478e")
    private static final String USECASE_OLD_NAMESPACE_ROOT = "org.modelio.diagram.editor.usecase";

    @objid ("3b3f3606-3aea-4911-a797-be449ef5b979")
    private static final String USECASE_NAMESPACE_ROOT = "org.modelio.uml.usecasediagram.editor";

    @objid ("5e8d5394-55b7-11e2-877f-002564c97630")
    @Override
    public GmNodeModel create(IGmDiagram diagram, GmCompositeNode parent, MObject newElement, Object initialLayoutData) {
        if (parent instanceof GmGroup) {
            // Use the label factory visitor
            final GmLabelFactoryVisitor v = new GmLabelFactoryVisitor(diagram, initialLayoutData);
        
            final GmNodeModel child = (GmNodeModel) newElement.accept(v);
            if (child != null) {
                parent.addChild(child);
            }
            return child;
        }
        // else Use the node factory visitor
        final NodeFactoryVisitor v = new NodeFactoryVisitor(diagram, initialLayoutData);
        
        final GmNodeModel child = (GmNodeModel) newElement.accept(v);
        if (child != null) {
            parent.addChild(child);
        }
        return child;
    }

    @objid ("5e8d53b8-55b7-11e2-877f-002564c97630")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        String fixedNamespace = migrateNamespace(namespace);
        if (!fixedNamespace.startsWith(USECASE_NAMESPACE_ROOT))
            return null;
        
        try {
            Class<?> clazz = Class.forName(fixedNamespace);
            if (clazz != null) {
                return clazz.asSubclass(IPersistent.class);
            }
        
        } catch (ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
            DiagramEditorUseCase.LOG.debug("Failed looking for '%s' transformed to '%': %s", namespace, fixedNamespace, e);
            DiagramEditorUseCase.LOG.debug(e);
        }
        return null;
    }

    @objid ("bbefafc3-5de8-4e44-b588-7ef439b3d101")
    @Override
    public Class<? extends IPersistentMigrator> resolveMigratorClass(String classNamespace) {
        String fixedNamespace = migrateNamespace(classNamespace);
        if (!fixedNamespace.startsWith(USECASE_NAMESPACE_ROOT))
            return null;
        
        try {
            Class<?> clazz = Class.forName(fixedNamespace);
            if (clazz != null) {
                return clazz.asSubclass(IPersistentMigrator.class);
            }
        } catch (ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
            DiagramEditorUseCase.LOG.debug("Failed looking for '%s' transformed to '%': %s", classNamespace, fixedNamespace, e);
            DiagramEditorUseCase.LOG.debug(e);
        }
        return null;
    }

    @objid ("7675f455-cecb-4b9c-91d2-f9be5e662d5b")
    @Override
    public Class<? extends Enum<?>> resolveEnumClass(String enumNamespace) {
        String fixedNamespace = migrateNamespace(enumNamespace);
        if (!fixedNamespace.startsWith(USECASE_NAMESPACE_ROOT))
            return null;
        
        try {
            Class<?> clazz = Class.forName(fixedNamespace);
            if (clazz != null && clazz.isEnum()) {
                return (Class<? extends Enum<?>>) clazz;
            }
        } catch (ClassNotFoundException | ClassCastException e) {
            // Enum not found, return null
            DiagramEditorUseCase.LOG.debug("Failed looking for '%s' transformed to '%': %s", enumNamespace, fixedNamespace, e);
            DiagramEditorUseCase.LOG.debug(e);
        }
        return null;
    }

    @objid ("2b89067d-6291-463f-ad55-396c65956647")
    @Override
    public String migrateNamespace(String namespace) {
        if (namespace.startsWith(USECASE_OLD_NAMESPACE_ROOT)) {
            return namespace.replace(USECASE_OLD_NAMESPACE_ROOT, USECASE_NAMESPACE_ROOT);
        }
        return namespace;
    }

    @objid ("5e8eda32-55b7-11e2-877f-002564c97630")
    private class GmLabelFactoryVisitor extends DefaultModelVisitor {
        @objid ("d9db06ca-55c2-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("25f3879b-f00e-4bb0-8998-c227570d2aef")
        private Object initialLayoutData;

        @objid ("5e8eda3a-55b7-11e2-877f-002564c97630")
        public  GmLabelFactoryVisitor(IGmDiagram diagram, Object initialLayoutData) {
            this.diagram = diagram;
            this.initialLayoutData = initialLayoutData;
            
        }

        @objid ("5e8eda40-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitExtensionPoint(ExtensionPoint theExtensionPoint) {
            GmExtensionPoint extensionPoint = new GmExtensionPoint(this.diagram,
                    theExtensionPoint,
                    new MRef(theExtensionPoint));
            extensionPoint.setLayoutData(this.initialLayoutData);
            return extensionPoint;
        }

    }

    @objid ("5e8d53c0-55b7-11e2-877f-002564c97630")
    private class NodeFactoryVisitor extends DefaultModelVisitor {
        @objid ("d9db06c9-55c2-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("6d89d451-5c07-4718-a899-ae8f302cd490")
        private Object initialLayoutData;

        @objid ("5e8eda1c-55b7-11e2-877f-002564c97630")
        public  NodeFactoryVisitor(IGmDiagram diagram, Object initialLayoutData) {
            this.diagram = diagram;
            this.initialLayoutData = initialLayoutData;
            
        }

        @objid ("5e8eda22-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitActor(Actor theActor) {
            GmActor actor = new GmActor(this.diagram, theActor, new MRef(theActor));
            actor.setLayoutData(this.initialLayoutData);
            return actor;
        }

        @objid ("5e8eda2a-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitUseCase(UseCase theUseCase) {
            GmUseCase useCase = new GmUseCase(this.diagram, theUseCase, new MRef(theUseCase));
            useCase.setLayoutData(this.initialLayoutData);
            return useCase;
        }

    }

}
