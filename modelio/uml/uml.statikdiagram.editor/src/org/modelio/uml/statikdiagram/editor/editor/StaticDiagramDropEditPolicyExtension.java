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

package org.modelio.uml.statikdiagram.editor.editor;

import java.util.Deque;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramElementDropEditPolicyExtension;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramElementDropEditPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.UnmaskConstraintCommand;
import org.modelio.diagram.elements.common.abstractdiagram.UnmaskLinkToVoidCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Drop extension for {@link StaticDiagram}.
 * <p>
 * Smart interactions are:
 * <ul>
 * <li>to be provided</li>
 * </ul>
 * </p>
 */
@objid ("7e11d6d5-1dec-11e2-8cad-001ec947c8cc")
public class StaticDiagramDropEditPolicyExtension extends AbstractDiagramElementDropEditPolicyExtension {
    /**
     * c'tor.
     */
    @objid ("7e11d6dd-1dec-11e2-8cad-001ec947c8cc")
    public StaticDiagramDropEditPolicyExtension() {
        super();
    }

    @objid ("164e41cc-2d88-4d5b-9a47-a12b5bb92585")
    @Override
    public Command getUnmaskCommandFor(DiagramElementDropEditPolicy dropPolicy, final MObject droppedElement, final Point dropLocation) {
        if (droppedElement.getMClass().getOrigin().getName().equals(StandardMetamodel.NAME)) {
            Command cmd = (Command) droppedElement.accept(new StandardVisitorImpl(dropPolicy, dropLocation));
            return cmd != null ? cmd : super.getUnmaskCommandFor(dropPolicy, droppedElement, dropLocation);
        }
        return null;
    }

    @objid ("91acf364-e47c-464e-bd1b-7fd6d0953fb6")
    @Override
    public MObject getParentInGraphicalHierarchy(IGmDiagram context, final MObject element) {
        final MObject owner = element.getCompositionOwner();
        if (owner instanceof Region && ((Region) owner).getParent() == null) {
            return ((Region) owner).getRepresented();
        }
        return owner;
    }

    @objid ("c9bcff3a-a107-4372-a124-5def992abeb4")
    @Override
    public boolean isToBeAddedToHierarchy(IGmDiagram context, Deque<MObject> hierarchy, MObject candidate) {
        MClass mClass = candidate.getMClass();
        boolean isUML = mClass.getOrigin().getName().equals(StandardMetamodel.NAME) && !mClass.getName().startsWith("Bpmn");
        boolean isSuperOk = super.isToBeAddedToHierarchy(context, hierarchy, candidate);
        
        final MObject lastInHierarchy = hierarchy.peek();
        if (lastInHierarchy == null) {
            return isUML && isSuperOk;
        }
        
        boolean isCurrentInstance = (lastInHierarchy.getMClass().getQualifiedName().equals(Instance.MQNAME));
        boolean isCurrentCollaboration = lastInHierarchy instanceof Collaboration;
        boolean isCurrentEnumeration = lastInHierarchy instanceof Enumeration;
        boolean isInformationItem = lastInHierarchy instanceof InformationItem;
        boolean isNary = lastInHierarchy instanceof NaryAssociation || lastInHierarchy instanceof NaryLink;
        return isUML && isSuperOk && !isCurrentInstance && !isCurrentCollaboration && !isCurrentEnumeration && !isInformationItem && !isNary;
    }

    @objid ("d2728399-b57a-44d7-81cd-bc742c777a0e")
    private static class StandardVisitorImpl extends DefaultModelVisitor {
        @objid ("6fd4a78d-576c-4a1b-946e-e22a203e0d5a")
        private Point dropLocation;

        @objid ("ff3ae184-c10c-4625-8c4c-5734d8f37da3")
        private DiagramElementDropEditPolicy dropPolicy;

        @objid ("05030594-ad7e-4f2b-9dac-20f2fd7df187")
        public StandardVisitorImpl(DiagramElementDropEditPolicy dropPolicy, Point dropLocation) {
            this.dropPolicy = dropPolicy;
            this.dropLocation = dropLocation;
        }

        @objid ("7e11d6ed-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitCollaborationUse(final CollaborationUse theCollaborationUse) {
            // Do not try to unmask collaboration use as a link, it doesn't work (should it?)
            return this.dropPolicy.createDropCommandForNodeHierarchy(this.dropLocation, theCollaborationUse);
        }

        @objid ("7e1438f9-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitConstraint(final Constraint theConstraint) {
            return new UnmaskConstraintCommand(theConstraint, (AbstractDiagramEditPart) this.dropPolicy.getHost(), new Rectangle(this.dropLocation, new Dimension(-1, -1)));
        }

        @objid ("7e143914-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitProvidedInterface(final ProvidedInterface theProvidedInterface) {
            AbstractDiagramEditPart diagramPart = (AbstractDiagramEditPart) this.dropPolicy.getHost();
            GmModel model = diagramPart.getModel();
            return new UnmaskLinkToVoidCommand(theProvidedInterface, theProvidedInterface.getProviding(), model.getRelatedElement(), diagramPart, this.dropLocation);
        }

        @objid ("7e14391b-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitRequiredInterface(final RequiredInterface theRequiredInterface) {
            AbstractDiagramEditPart diagramPart = (AbstractDiagramEditPart) this.dropPolicy.getHost();
            GmModel model = diagramPart.getModel();
            return new UnmaskLinkToVoidCommand(theRequiredInterface, theRequiredInterface.getRequiring(), model.getRelatedElement(), diagramPart, this.dropLocation);
        }

    }

}
