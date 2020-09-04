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

package org.modelio.diagram.editor.communication.elements.communicationdiagram;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramElementDropEditPolicyExtension;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramElementDropEditPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.UnmaskConstraintCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.CommunicationDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Drop extension for {@link CommunicationDiagram}.
 * <p>
 * Smart interactions are:
 * <ul>
 * <li>dropping an {@link Instance}, {@link Attribute}, {@link AssociationEnd}, {@link GeneralClass} creates an {@link Instance} and unmasks a {@link CommunicationNode}</li>
 * </ul>
 * </p>
 */
@objid ("f227f27b-a8e4-41b0-92c2-a3f61253aa38")
public class CommunicationDiagramDropEditPolicyExtension extends AbstractDiagramElementDropEditPolicyExtension {
    @objid ("184ef83d-03e2-4718-b057-084219f08a7e")
    @Override
    public Command getUnmaskCommandFor(DiagramElementDropEditPolicy dropPolicy, MObject droppedElement, Point dropLocation) {
        if ((droppedElement instanceof AssociationEnd)
                || (droppedElement instanceof Attribute)
                || (droppedElement instanceof Instance && !(droppedElement instanceof Port))
                || (droppedElement instanceof GeneralClass)) {
            final AbstractDiagram owner = ((GmCommunicationDiagram) dropPolicy.getHost().getModel()).getDiagram().getRelatedElement();
            ModelElement origin = owner.getOrigin();
            return new SmartCreateCommunicationNodeCommand(dropLocation, droppedElement, dropPolicy.getHost(), (CommunicationInteraction) origin);
        } else if (droppedElement != null) {
            // TODO improve check for a "communication diagram" element
            if (droppedElement.getMClass().getOrigin().getName().equals(StandardMetamodel.NAME)) {
                Command cmd = (Command) droppedElement.accept(new StandardVisitorImpl(dropPolicy, dropLocation));
                return cmd != null ? cmd : super.getUnmaskCommandFor(dropPolicy, droppedElement, dropLocation);
                //return super.getUnmaskCommandFor(dropPolicy, droppedElement, dropLocation);
            }
        }
        return null;
    }

    @objid ("ee5c232a-e6bf-41a8-8cb5-024d42a2513c")
    @Override
    public boolean canUnmask(DiagramElementDropEditPolicy dropPolicy, MObject candidate) {
        // Gm doesn't know how to handle this element directly, look if
        // it is an toUnmask for which we can do something "smarter".
        if (!(candidate instanceof AssociationEnd)
                && !(candidate instanceof Attribute)
                && !(candidate instanceof Instance || candidate instanceof Port)
                && !(candidate instanceof GeneralClass)) {
            // It is not a smart interaction
            //
            // The Gm cannot unmask this element directly, and we don't know
            // what to do with it... return null
            return false;
        }
        
        // All dropped elements understood: return host!
        return true;
    }

    @objid ("930ed759-cb11-4aae-84bd-ce9eeafc6814")
    @Override
    public boolean isToBeAddedToHierarchy(IGmDiagram context, Deque<MObject> hierarchy, MObject candidate) {
        if (candidate == null || candidate instanceof CommunicationInteraction) {
            return false;
        }
        
        // Make sure the element is part of the current communication
        ModelElement communication = context.getRelatedElement().getOrigin();
        MObject parent = candidate.getCompositionOwner();
        while (parent != null) {
            if (parent.equals(communication)) {
                return true;
            } else {
                parent = parent.getCompositionOwner();
            }
        }
        return false;
    }

    /**
     * Command used for smart unmask interactions: <br>
     * Creates a {@link BindableInstance} representing the dropped element.
     */
    @objid ("7a2e30ff-55b6-11e2-877f-002564c97630")
    private static class SmartCreateCommunicationNodeCommand extends Command {
        @objid ("7a2e3101-55b6-11e2-877f-002564c97630")
        private CommunicationInteraction parentElement;

        @objid ("7a2e3104-55b6-11e2-877f-002564c97630")
        private MObject toUnmask;

        @objid ("05691e82-599a-11e2-ae45-002564c97630")
        private EditPart parentEditPart;

        @objid ("db9bef70-6b13-4259-a9c2-4d5ad23e44de")
        private Point location;

        /**
         * @param dropLocation the location where the {@link CommunicationNode} is to be unmasked.
         * @param toUnmask the element that the {@link CommunicationNode} will represent.
         * @param parentEditPart the edit part handling the unmasking
         * @param parentElement the element that will own the new {@link CommunicationNode}
         */
        @objid ("7a2e3109-55b6-11e2-877f-002564c97630")
        public SmartCreateCommunicationNodeCommand(final Point dropLocation, final MObject toUnmask, final EditPart parentEditPart, final CommunicationInteraction parentElement) {
            this.location = dropLocation;
            this.toUnmask = toUnmask;
            this.parentEditPart = parentEditPart;
            this.parentElement = parentElement;
        }

        @objid ("7a2e3118-55b6-11e2-877f-002564c97630")
        @Override
        public boolean canExecute() {
            final GmModel gmModel = (GmModel) this.parentEditPart.getModel();
            final IGmDiagram gmDiagram = gmModel.getDiagram();
            
            if (!MTools.getAuthTool().canModify(gmDiagram.getRelatedElement())) {
                return false;
            }
            return (this.parentElement != null && this.parentElement.isValid() && this.parentElement.getStatus().isModifiable());
        }

        @objid ("7a2e311d-55b6-11e2-877f-002564c97630")
        @Override
        public void execute() {
            GmModel gmModel = (GmModel) this.parentEditPart.getModel();
            IGmDiagram gmDiagram = gmModel.getDiagram();
            IModelManager modelManager = gmDiagram.getModelManager();
            IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
            
            CommunicationInteraction interaction = this.parentElement;
            
            // Create the communication node
            CommunicationNode commNode = modelFactory.createCommunicationNode();
            commNode.setOwner(interaction);
            
            Instance instanceNode;
            if (this.toUnmask instanceof Instance) {
                instanceNode = (Instance) this.toUnmask;
            } else {
                instanceNode = createBindableInstance(modelFactory, modelManager.getModelServices().getElementNamer(), interaction);
            }
            
            // Link the node to the instance
            commNode.setRepresented(instanceNode);
            commNode.setName(instanceNode.getName());
            
            // Unmask the node
            unmaskElement(commNode);
        }

        @objid ("7a2e3120-55b6-11e2-877f-002564c97630")
        private BindableInstance createBindableInstance(final IStandardModelFactory factory, IElementNamer elementNamer, final CommunicationInteraction interaction) {
            // Get the "locals" collaboration
            Collaboration localsCollaboration = getLocalsCollaboration(interaction);
            // Create the "locals" collaboration if none was found
            if (localsCollaboration == null) {
                localsCollaboration = createLocalsCollaboration(factory, interaction);
            }
            
            // Create the new instance
            BindableInstance bindableInstance = factory.createBindableInstance();
            localsCollaboration.getDeclared().add(bindableInstance);
            
            bindableInstance.setName(elementNamer.getUniqueName("r", bindableInstance));
            
            // Attach to dropped element
            if (this.toUnmask instanceof Instance) {
                bindableInstance.setRepresentedFeature((Instance) this.toUnmask);
            } else if (this.toUnmask instanceof Attribute) {
                bindableInstance.setRepresentedFeature((Attribute) this.toUnmask);
                bindableInstance.setBase(((Attribute) this.toUnmask).getType());
            } else if (this.toUnmask instanceof AssociationEnd) {
                bindableInstance.setRepresentedFeature((AssociationEnd) this.toUnmask);
            } else if (this.toUnmask instanceof GeneralClass) {
                bindableInstance.setBase((NameSpace) this.toUnmask);
                createPorts(bindableInstance);
            }
            return bindableInstance;
        }

        @objid ("7a2e312e-55b6-11e2-877f-002564c97630")
        private Collaboration createLocalsCollaboration(final IStandardModelFactory factory, final CommunicationInteraction interaction) {
            Collaboration localsCollaboration;
            localsCollaboration = factory.createCollaboration();
            localsCollaboration.setName("locals");
            interaction.getOwnedCollaboration().add(localsCollaboration);
            return localsCollaboration;
        }

        @objid ("7a2e313c-55b6-11e2-877f-002564c97630")
        private Collaboration getLocalsCollaboration(final CommunicationInteraction interaction) {
            for (Collaboration collaboration : interaction.getOwnedCollaboration()) {
                if (collaboration.getName().equals("locals")) {
                    return collaboration;
                }
            }
            return null;
        }

        /**
         * Copy the Ports of the base class to the instance.
         * 
         * @param part the part where Ports are to be added.
         * @return the created ports.
         */
        @objid ("7a2fb7a1-55b6-11e2-877f-002564c97630")
        private Collection<Port> createPorts(final BindableInstance part) {
            final Classifier type = (Classifier) part.getBase();
            final Collection<Port> ret = new ArrayList<>();
            
            for (BindableInstance typePart : type.getInternalStructure()) {
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

        @objid ("7a2fb7ae-55b6-11e2-877f-002564c97630")
        private void unmaskElement(final MObject el) {
            final ModelioCreationContext gmCreationContext = new ModelioCreationContext(el);
            
            final CreateRequest creationRequest = new CreateRequest();
            creationRequest.setLocation(this.location);
            creationRequest.setSize(new Dimension(-1, -1));
            creationRequest.setFactory(gmCreationContext);
            
            final Command cmd = this.parentEditPart.getTargetEditPart(creationRequest)
                    .getCommand(creationRequest);
            if (cmd != null && cmd.canExecute()) {
                cmd.execute();
            }
        }

    }

    @objid ("3e15dd28-7847-4129-9ce7-c8acb4ba20a3")
    private static class StandardVisitorImpl extends DefaultModelVisitor {
        @objid ("e2f06296-35d2-4f0f-abbb-c4b8f61cecbe")
        private Point dropLocation;

        @objid ("8d1d42f8-c761-4297-9fdc-714bfe34740c")
        private DiagramElementDropEditPolicy dropPolicy;

        @objid ("598a770e-d85f-4a69-ab1b-ed12158e5fef")
        public StandardVisitorImpl(DiagramElementDropEditPolicy dropPolicy, Point dropLocation) {
            this.dropPolicy = dropPolicy;
            this.dropLocation = dropLocation;
        }

        @objid ("a0de39a1-8c6e-43f1-8344-a2a26df7fa28")
        @Override
        public Object visitConstraint(final Constraint theConstraint) {
            return new UnmaskConstraintCommand(theConstraint, (AbstractDiagramEditPart) this.dropPolicy.getHost(), new Rectangle(this.dropLocation, new Dimension(-1, -1)));
        }

    }

}
