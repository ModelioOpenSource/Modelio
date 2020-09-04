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

package org.modelio.diagram.editor.sequence.elements.sequencediagram;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.helpers.UnmaskHelper;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command used for smart unmask interactions: <br>
 * Creates a {@link BindableInstance} representing the dropped element.
 * 
 * @author cma
 */
@objid ("d983b585-55b6-11e2-877f-002564c97630")
public class SmartCreateLifelineCommand extends Command {
    @objid ("2cc1b8ed-48c0-4dab-a6de-c27ff70e8a7f")
    private Point location;

    @objid ("19b48f45-057a-4db5-987e-0209fb086809")
    private MObject parentElement;

    @objid ("d5109998-3f01-4de0-83aa-56ab8b768562")
    private MObject toUnmask;

    @objid ("59d2be10-3bad-4fb3-8786-d53b6914d80f")
    private EditPart parentEditPart;

    /**
     * @param dropLocation the location where the ObjectNode is to be unmasked.
     * @param toUnmask the element that the ObjectNode will represent.
     * @param parentEditPart the edit part handling the unmasking
     * @param parentElement the element that will own the new ObjectNode
     */
    @objid ("d983b58f-55b6-11e2-877f-002564c97630")
    public SmartCreateLifelineCommand(final Point dropLocation, final MObject toUnmask, final EditPart parentEditPart, final MObject parentElement) {
        this.location = dropLocation;
        this.toUnmask = toUnmask;
        this.parentEditPart = parentEditPart;
        this.parentElement = parentElement;
    }

    @objid ("d983b59e-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        final GmModel gmModel = (GmModel) this.parentEditPart.getModel();
        final IGmDiagram gmDiagram = gmModel.getDiagram();
        
        if (!MTools.getAuthTool().canModify(gmDiagram.getRelatedElement())) {
            return false;
        }
        return (this.parentElement != null && this.parentElement.isValid() && this.parentElement.getStatus()
                        .isModifiable());
    }

    @objid ("d983b5a3-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        GmModel gmModel = (GmModel) this.parentEditPart.getModel();
        IGmDiagram gmDiagram = gmModel.getDiagram();
        IModelManager modelManager = gmDiagram.getModelManager();
        final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        
        Interaction interaction = (Interaction) this.parentElement;
        
        // Create the lifeline
        Lifeline lifelineNode = modelFactory.createLifeline("", interaction);
        
        // Unmask the node
        unmaskElement(lifelineNode);
        
        Instance instanceNode;
        if (this.toUnmask instanceof Instance) {
            instanceNode = (Instance) this.toUnmask;
        } else {
            instanceNode = createInstance(modelFactory, modelManager.getModelServices().getElementNamer(), interaction);
        
            // Attach to dropped element
            Collection<Port> ports = null;
            if (this.toUnmask instanceof Instance) {
                ((BindableInstance) instanceNode).setRepresentedFeature((Instance) this.toUnmask);
            } else if (this.toUnmask instanceof Attribute) {
                ((BindableInstance) instanceNode).setRepresentedFeature((Attribute) this.toUnmask);
                instanceNode.setBase(((Attribute) this.toUnmask).getType());
            } else if (this.toUnmask instanceof AssociationEnd) {
                ((BindableInstance) instanceNode).setRepresentedFeature((AssociationEnd) this.toUnmask);
            } else if (this.toUnmask instanceof GeneralClass) {
                instanceNode.setBase((NameSpace) this.toUnmask);
                ports = createPorts((BindableInstance) instanceNode);
            } else if (this.toUnmask instanceof UmlModelElement) {
                ((BindableInstance) instanceNode).setRepresentedFeature((UmlModelElement) this.toUnmask);
            }
        
            // Unmask the part ports too
            if (ports != null) {
                // Translate point to unmask port on the right
                this.location.translate(100, 10);
                Command cmd = UnmaskHelper.getUnmaskCommand(this.parentEditPart.getViewer(),
                        ports,
                        this.location);
                if (cmd != null && cmd.canExecute()) {
                    cmd.execute();
                }
            }
        }
        
        // Link the lifeline to the instance
        lifelineNode.setRepresented(instanceNode);
        lifelineNode.setName(instanceNode.getName());
    }

    @objid ("d983b5a6-55b6-11e2-877f-002564c97630")
    private BindableInstance createInstance(final IStandardModelFactory factory, IElementNamer elementNamer, final Interaction interaction) {
        // Get the "locals" collaboration
        Collaboration localsCollaboration = getLocalsCollaboration(interaction);
        // Create the "locals" collaboration if none was found
        if (localsCollaboration == null) {
            localsCollaboration = createLocalsCollaboration(factory, interaction);
        }
        
        // Create the new instance
        BindableInstance instanceNode = factory.createBindableInstance();
        localsCollaboration.getDeclared().add(instanceNode);
        instanceNode.setName(elementNamer.getUniqueName("r", instanceNode));
        return instanceNode;
    }

    @objid ("d983b5b4-55b6-11e2-877f-002564c97630")
    private Collaboration createLocalsCollaboration(final IStandardModelFactory factory, final Interaction interaction) {
        Collaboration localsCollaboration;
        localsCollaboration = factory.createCollaboration();
        localsCollaboration.setName("locals");
        interaction.getOwnedCollaboration().add(localsCollaboration);
        return localsCollaboration;
    }

    @objid ("d983b5c2-55b6-11e2-877f-002564c97630")
    private Collaboration getLocalsCollaboration(final Interaction interaction) {
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
    @objid ("d9853c21-55b6-11e2-877f-002564c97630")
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

    @objid ("d9853c2e-55b6-11e2-877f-002564c97630")
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
