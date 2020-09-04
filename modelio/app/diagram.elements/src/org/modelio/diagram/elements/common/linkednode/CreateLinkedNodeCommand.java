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

package org.modelio.diagram.elements.common.linkednode;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer.Conditional;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.helpers.AnchorModelHelper;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.mmextensions.infrastructure.IInfrastructureModelFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.IModelFactoryService;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link GmNodeModel} creation command that:
 * <ul>
 * <li>creates and initialize the MObject if asked.
 * <li>creates the {@link GmNodeModel} and unmask it.
 * </ul>
 * according to the provided {@link ModelioCreationContext}.
 */
@objid ("7eb658e1-1dec-11e2-8cad-001ec947c8cc")
public class CreateLinkedNodeCommand extends Command {
    @objid ("7eb658e5-1dec-11e2-8cad-001ec947c8cc")
    protected final ModelioCreationContext context;

    @objid ("7eb658ea-1dec-11e2-8cad-001ec947c8cc")
    protected MObject parentElement;

    @objid ("7eb658eb-1dec-11e2-8cad-001ec947c8cc")
    protected IGmLinkable sourceNode;

    @objid ("7eb658ef-1dec-11e2-8cad-001ec947c8cc")
    protected GmCompositeNode destNode;

    @objid ("7eb658f3-1dec-11e2-8cad-001ec947c8cc")
    protected Object sourceAnchor;

    @objid ("65636912-1e83-11e2-8cad-001ec947c8cc")
    protected Point location;

    @objid ("65636913-1e83-11e2-8cad-001ec947c8cc")
    protected Dimension size = new Dimension(-1, -1);

    @objid ("6565cb69-1e83-11e2-8cad-001ec947c8cc")
    protected EditPart sourceEditPart;

    /**
     * Creates a node creation command.
     * 
     * @param context Details on the MObject and/or the node to create
     */
    @objid ("7eb658f4-1dec-11e2-8cad-001ec947c8cc")
    public CreateLinkedNodeCommand(ModelioCreationContext context) {
        this.context = context;
    }

    @objid ("7eb658f8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.sourceNode.getDiagram();
        
        MObject newElement = this.context.getElementToUnmask();
        
        if (newElement == null) {
            newElement = createElement(diagram.getModelManager().getModelFactory(), diagram.getModelManager().getModelServices().getElementNamer());
        
            if (newElement == null) {
                return;
            }
        }
        
        // Get the new element bounds
        final Rectangle rect;
        if (this.size != null) {
            rect = new Rectangle(this.location, this.size);
        } else {
            rect = new Rectangle(this.location, new Dimension(-1, -1));
        }
        
        // Show the new element in the diagram (ie create its Gm )
        final GmNodeModel createdNode = diagram.unmask(this.destNode, newElement, rect);
        
        // Show the link between the source node and the unmasked node
        if (this.sourceNode != this.destNode) {
            CreateConnectionRequest connCreateRequest = getConnectionCreationRequest(createdNode);
            IGmLinkable linkTarget = (IGmLinkable) connCreateRequest.getTargetEditPart().getModel();
        
            IGmLink gmlink = diagram.unmaskLink(newElement);
            linkTarget.addEndingLink(gmlink); // Add the target first because the link depends on the target
            this.sourceNode.addStartingLink(gmlink);
        
            IGmPath path = new GmPath();
            path.setSourceAnchor(this.sourceAnchor);
            path.setTargetAnchor(AnchorModelHelper.getTargetAnchorModel(connCreateRequest));
            path.setPathData(new ArrayList<>());
            gmlink.setLayoutData(path);
        }
    }

    /**
     * Set the node inside which the node will be created.
     * 
     * @param destNode The node in which the node will be created.
     */
    @objid ("7eb658fb-1dec-11e2-8cad-001ec947c8cc")
    public void setDestinationNode(GmCompositeNode destNode) {
        this.destNode = destNode;
    }

    /**
     * Set the node location.
     * 
     * @param location The location in absolute coordinates.
     */
    @objid ("7eb658ff-1dec-11e2-8cad-001ec947c8cc")
    public void setNodeLocation(Point location) {
        this.location = location;
    }

    /**
     * set the node size.
     * 
     * @param size The size of the node to create
     */
    @objid ("7eb8bb3e-1dec-11e2-8cad-001ec947c8cc")
    public void setNodeSize(Dimension size) {
        this.size = size;
    }

    /**
     * Set the parent element independently from the parent node.
     * 
     * @param parentElement the parent element.
     */
    @objid ("7eb8bb44-1dec-11e2-8cad-001ec947c8cc")
    public void setParentElement(MObject parentElement) {
        this.parentElement = parentElement;
    }

    /**
     * Set the node on which the created node will be linked.
     * <p>
     * Set the parent element to be the represented element of the source node.
     * 
     * @param source the source edit part.
     */
    @objid ("7eb8bb48-1dec-11e2-8cad-001ec947c8cc")
    public void setSource(final EditPart source) {
        this.sourceEditPart = source;
        this.sourceNode = (IGmLinkable) this.sourceEditPart.getModel();
        this.parentElement = this.sourceNode.getRelatedElement();
    }

    /**
     * Create and initialize the model element.
     * 
     * @param modelFactory the model factory.
     * @return the created model element.
     */
    @objid ("7eb8bb4f-1dec-11e2-8cad-001ec947c8cc")
    protected MObject createElement(final IModelFactoryService modelFactory, IElementNamer elementNamer) {
        // Create the MObject...
        IInfrastructureModelFactory factory = modelFactory.getFactory(IInfrastructureModelFactory.class);
        MClass metaclass = this.context.getMetaclass();
        MObject newElement = metaclass.getJavaInterface().equals(Note.class) ? factory.createNote() : modelFactory.createElement(metaclass);
        
        // ... and attach it to its parent.
        try {
            this.parentElement.mGet(this.context.getDependency()).add(newElement);
        } catch (Exception e) {
            // The dependency indicated in the context cannot be used: try
            // to find a valid one!
            final MExpert expert = metaclass.getMetamodel().getMExpert();
            final MDependency compositionDep = expert.getDefaultCompositionDep(this.parentElement, newElement);
            if (compositionDep != null) {
                this.parentElement.mGet(compositionDep).add(newElement);
            } else {
                newElement.delete();
                throw e;
            }
        }
        
        // Attach the stereotype if needed.
        if (this.context.getStereotype() != null && newElement instanceof ModelElement) {
            ((ModelElement) newElement).getExtension().add(this.context.getStereotype());
        }
        
        // Set default name
        newElement.setName(elementNamer.getUniqueName(newElement));
        return newElement;
    }

    @objid ("7eb8bb56-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        if (!MTools.getAuthTool().canModify(this.sourceNode.getDiagram().getRelatedElement())) {
            return false;
        }
        
        final MObject newElement = this.context.getElementToUnmask();
        
        if (newElement == null) {
            return MTools.getAuthTool().canAdd(this.parentElement, this.context.getMetaclass());
        } else {
            return true;
        }
    }

    /**
     * Set the source anchor model where the link starts.
     * 
     * @param sourceAnchorModel the source anchor model.
     */
    @objid ("7eb8bb5a-1dec-11e2-8cad-001ec947c8cc")
    public void setSourceAnchor(final Object sourceAnchorModel) {
        this.sourceAnchor = sourceAnchorModel;
    }

    @objid ("7eb8bb5f-1dec-11e2-8cad-001ec947c8cc")
    protected CreateConnectionRequest getConnectionCreationRequest(final GmNodeModel createdNode) {
        // Get the edit part of the node
        EditPart targetPart = (EditPart) this.sourceEditPart.getViewer().getEditPartRegistry().get(createdNode);
        
        // Compute the node figure bounds now.
        ((GraphicalEditPart) targetPart).getFigure().getUpdateManager().performValidation();
        
        // Create a connection creation request
        CreateConnectionRequest request = new CreateConnectionRequest();
        request.setType(LinkedNodeRequestConstants.REQ_LINKEDNODE_END);
        request.setLocation(this.location);
        request.setSourceEditPart(this.sourceEditPart);
        request.setTargetEditPart(targetPart);
        request.setFactory(this.context);
        
        // Check the edit part support this connection
        targetPart = targetPart.getTargetEditPart(request);
        if (targetPart == null) {
            // Find the right edit part to connect the link to.
            targetPart = findTargetAnchorEditPart(request);
        }
        if (targetPart != null) {
            request.setTargetEditPart(targetPart);
        }
        return request;
    }

    /**
     * Find in the viewer the right edit part to anchor the connection to.
     * 
     * @param request a connection creation request
     * @return the edit part to which the node link must be anchored to.
     */
    @objid ("7eb8bb67-1dec-11e2-8cad-001ec947c8cc")
    protected EditPart findTargetAnchorEditPart(final CreateConnectionRequest request) {
        EditPartViewer viewer = request.getTargetEditPart().getViewer();
        Conditional conditional = new Conditional() {
            @Override
            public boolean evaluate(EditPart editpart) {
                return editpart.getTargetEditPart(request) != null;
            }
        };
        
        EditPart ret = viewer.findObjectAtExcluding(request.getLocation(), Collections.emptyList(), conditional);
        if (ret != null) {
            ret = ret.getTargetEditPart(request);
        }
        return ret;
    }

}
