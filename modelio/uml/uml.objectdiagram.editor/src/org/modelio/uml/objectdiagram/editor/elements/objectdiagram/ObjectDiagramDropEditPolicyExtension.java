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
package org.modelio.uml.objectdiagram.editor.elements.objectdiagram;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramElementDropEditPolicyExtension;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramElementDropEditPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.UnmaskConstraintCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.helpers.UnmaskHelper;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.ObjectDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Drop extension for {@link ObjectDiagram}.
 * <p>
 * Smart interactions are:
 * <ul>
 * <li>dropping a {@link GeneralClass} creates an {@link Instance} and unmasks it</li>
 * </ul>
 * </p>
 */
@objid ("791f3e79-a99c-42d5-ad37-3fddcd50931e")
public class ObjectDiagramDropEditPolicyExtension extends AbstractDiagramElementDropEditPolicyExtension {
    @objid ("b095d4cb-bd4e-4e7c-8851-5bb8dd1a04a8")
    @Override
    public Command getUnmaskCommandFor(DiagramElementDropEditPolicy dropPolicy, MObject droppedElement, Point dropLocation) {
        if (droppedElement instanceof GeneralClass) {
            final GmModel gmModel = (GmModel) dropPolicy.getHost().getModel();
            final AbstractDiagram owner = (AbstractDiagram) gmModel.getRelatedElement();
            ModelElement origin = owner.getOrigin();
            return new SmartCreateInstanceCommand(dropLocation, (GeneralClass) droppedElement, dropPolicy.getHost(), origin);
        } else if (droppedElement != null) {
            // TODO improve check for an "object diagram" element
            if (droppedElement.getMClass().getOrigin().getName().equals(StandardMetamodel.NAME)) {
                Command cmd = (Command) droppedElement.accept(new StandardVisitorImpl(dropPolicy, dropLocation));
                return cmd != null ? cmd : super.getUnmaskCommandFor(dropPolicy, droppedElement, dropLocation);
            }
        }
        return null;
    }

    @objid ("032063d7-b6df-44f3-a3ed-cf9f8c4f3c48")
    @Override
    public boolean canUnmask(DiagramElementDropEditPolicy dropPolicy, MObject candidate) {
        // Gm doesn't know how to handle this element directly, look if
        // it is an toUnmask for which we can do something "smarter".
        if (!(candidate instanceof GeneralClass)) {
            // It is not a smart interaction
            //
            // The Gm cannot unmask this element directly, and we don't know
            // what to do with it... return null
            return false;
        }
        
        // All dropped elements understood: return host!
        return true;
    }

    /**
     * Always accept candidates in the hierarchy as long as the last in the hierarchy is not strictly an {@link Instance}.
     */
    @objid ("49602783-40dd-4a0e-b0c9-94e03580cada")
    @Override
    public boolean isToBeAddedToHierarchy(IGmDiagram context, Deque<MObject> hierarchy, MObject candidate) {
        final MObject lastInHierarchy = hierarchy.peek();
        if (lastInHierarchy == null) {
            return true;
        }
        
        boolean isCurrentCollaboration = lastInHierarchy instanceof Collaboration;
        boolean isCurrentInstance = lastInHierarchy.getMClass().getQualifiedName().equals(Instance.MQNAME);
        boolean isCurrentBindableInstance = lastInHierarchy.getMClass().getQualifiedName().equals(BindableInstance.MQNAME);
        boolean isNary =  lastInHierarchy instanceof NaryLink;
        return !isCurrentCollaboration && !isCurrentInstance && !isCurrentBindableInstance && !isNary;
    }

    /**
     * Command used for smart unmask interactions: <br>
     * Creates an {@link Instance} or {@link BindableInstance} representing the dropped element.
     * 
     * @author cma
     */
    @objid ("2579588f-1d79-42a0-8b33-45ee77af3c09")
    private static class SmartCreateInstanceCommand extends Command {
        @objid ("99d62d78-8cc0-4a1f-ae28-bfb5ee3afb5d")
        private MObject parentElement;

        @objid ("1300acc2-0186-42d7-899c-92fa684b3c55")
        private NameSpace toUnmask;

        @objid ("a84f8a70-8dd0-4991-9be2-38424c954e76")
        private EditPart parentEditPart;

        @objid ("bcfc42c2-2171-487d-806c-6b204ea3a0d6")
        private EditPartViewer viewer;

        @objid ("2c45c796-7d10-49d1-ba52-2d11018d19b8")
        private Point location;

        /**
         * @param dropLocation the location where the ObjectNode is to be unmasked.
         * @param toUnmask the element that the ObjectNode will represent.
         * @param parentEditPart the edit part handling the unmasking
         * @param parentElement the element that will own the new ObjectNode
         */
        @objid ("fa8f54ba-704a-481a-a102-0d6876d80782")
        public  SmartCreateInstanceCommand(final Point dropLocation, final NameSpace toUnmask, final EditPart parentEditPart, final MObject parentElement) {
            this.location = dropLocation;
            this.toUnmask = toUnmask;
            this.parentEditPart = parentEditPart;
            this.parentElement = parentElement;
            this.viewer = parentEditPart.getViewer();
            
        }

        @objid ("2b9d4259-07e9-498a-87e1-8f9065947d9c")
        @Override
        public boolean canExecute() {
            final GmModel gmModel = (GmModel) this.parentEditPart.getModel();
            final IGmDiagram gmDiagram = gmModel.getDiagram();
            if (!MTools.getAuthTool().canModify(gmDiagram.getRelatedElement())) {
                return false;
            }
            return (this.parentElement != null && this.parentElement.isValid() && this.parentElement.isModifiable());
        }

        @objid ("20b79399-3107-44a7-b5fd-2e9c610de595")
        @Override
        public void execute() {
            GmModel gmModel = (GmModel) this.parentEditPart.getModel();
            IGmDiagram gmDiagram = gmModel.getDiagram();
            IStandardModelFactory factory = gmDiagram.getModelManager().getModelFactory().getFactory(IStandardModelFactory.class);
            
            // Create the smart node
            Instance instanceNode;
            String namePattern;
            if (this.parentElement instanceof Package) {
                instanceNode = factory.createInstance();
                namePattern = "Instance";
            } else if (this.parentElement instanceof Collaboration) {
                instanceNode = factory.createBindableInstance();
                namePattern = "r";
            } else {
                instanceNode = factory.createBindableInstance();
                namePattern = "i";
            }
            
            // Attach to parent
            MMetamodel mm = instanceNode.getMClass().getMetamodel();
            final MDependency effectiveDependency = mm.getMExpert().getDefaultCompositionDep(this.parentElement, instanceNode);
            
            if (effectiveDependency == null) {
                StringBuilder msg = new StringBuilder();
                msg.append("Cannot find a composition dependency to attach ");
                msg.append(instanceNode.toString());
                msg.append(" to ");
                msg.append(this.parentElement.toString());
                throw new IllegalStateException(msg.toString());
            }
            this.parentElement.mGet(effectiveDependency).add(instanceNode);
            
            // Attach to dropped element
            instanceNode.setBase(this.toUnmask);
            instanceNode.setName(gmDiagram.getModelManager().getModelServices().getElementNamer().getUniqueName(namePattern, instanceNode));
            
            // Unmask the node
            unmaskElement(instanceNode);
            
            // Handle ports if applicable
            if (this.toUnmask instanceof Classifier) {
                Collection<Port> ports = createPorts(instanceNode);
                if (!ports.isEmpty()) {
                    // Force graphical validation of parent to avoid some nasty side effects
                    ((GraphicalEditPart) this.parentEditPart).getFigure().getUpdateManager().performValidation();
            
                    // Translate point to unmask port on the right
                    this.location.translate(100, 10);
                    Command cmd = UnmaskHelper.getUnmaskCommand(this.viewer, ports, this.location);
                    if (cmd != null && cmd.canExecute()) {
                        cmd.execute();
                    }
                }
            }
            
        }

        /**
         * Copy the Ports of the base class to the instance.
         * @param part the part where Ports are to be added.
         * @return the created ports.
         */
        @objid ("b19835f4-2d48-4818-a476-08afdde58902")
        private Collection<Port> createPorts(final Instance part) {
            final Classifier type = (Classifier) part.getBase();
            final Collection<Port> ret = new ArrayList<>(type.getInternalStructure().size());
            
            for (Instance typePart : type.getInternalStructure()) {
                if (typePart instanceof Port) {
                    final Port partPort = (Port) MTools.getModelTool().cloneElement(typePart);
                    partPort.setInternalOwner(null);
                    partPort.setCluster(part);
                    partPort.setRepresentedFeature(typePart);
            
                    ret.add(partPort);
                }
            }
            return ret;
        }

        @objid ("9c594154-0e47-4c25-add3-1e7b74a62421")
        private void unmaskElement(final MObject el) {
            final ModelioCreationContext gmCreationContext = new ModelioCreationContext(el);
            
            final CreateRequest creationRequest = new CreateRequest();
            creationRequest.setLocation(this.location);
            creationRequest.setSize(new Dimension(-1, -1));
            creationRequest.setFactory(gmCreationContext);
            
            final Command cmd = this.parentEditPart.getTargetEditPart(creationRequest).getCommand(creationRequest);
            if (cmd != null && cmd.canExecute()) {
                cmd.execute();
            }
            
        }

    }

    @objid ("44cf43b6-c326-448f-8039-874ae0b7c6d1")
    private static class StandardVisitorImpl extends DefaultModelVisitor {
        @objid ("8775a191-2c31-4f50-95b7-38713cd7c481")
        private Point dropLocation;

        @objid ("37033a7f-f1a6-4ab6-9937-189c87392f6a")
        private DiagramElementDropEditPolicy dropPolicy;

        @objid ("b1e48ca2-014c-4741-adda-f75c331b4493")
        public  StandardVisitorImpl(DiagramElementDropEditPolicy dropPolicy, Point dropLocation) {
            this.dropPolicy = dropPolicy;
            this.dropLocation = dropLocation;
            
        }

        @objid ("3f9c6f63-7dc4-4a83-9b36-21992ea8405f")
        @Override
        public Object visitConstraint(final Constraint theConstraint) {
            return new UnmaskConstraintCommand(theConstraint, (AbstractDiagramEditPart) this.dropPolicy.getHost(), new Rectangle(this.dropLocation, new Dimension(-1, -1)));
        }

    }

}
