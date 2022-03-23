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
package org.modelio.diagram.elements.core.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.swt.SWT;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramElementDropEditPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.IDiagramElementDropEditPolicyExtension;
import org.modelio.diagram.elements.common.abstractdiagram.UnmaskLinkCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * A default drop policy implementation that can process {@link ModelElementDropRequest} requests.
 */
@objid ("80bbb77e-1dec-11e2-8cad-001ec947c8cc")
public class DefaultElementDropEditPolicy extends AbstractElementDropEditPolicy {
    /**
     * Tells whether or not an element may be unmasked several times (i.e. several GMs for the same model element).
     */
    @objid ("a29cc813-59ff-448d-b351-04e0015bde7c")
    private boolean multipleUnmaskAllowed;

    /**
     * C'tor forbidding multiple unmask.
     */
    @objid ("ecf682ad-cd34-47bf-8eea-3a54e303f287")
    public  DefaultElementDropEditPolicy() {
        this(false);
    }

    /**
     * C'tor.
     * @param multipleUnmaskAllowed allow or forbid multiple unmask for model elements.
     */
    @objid ("4bb89082-bf6f-43a1-86e9-ee608b5796b9")
    public  DefaultElementDropEditPolicy(boolean multipleUnmaskAllowed) {
        this.multipleUnmaskAllowed = multipleUnmaskAllowed;
    }

    /**
     * Tells whether or not an element may be unmasked several times (i.e. several GMs for the same model element).
     */
    @objid ("86ea0098-7a9a-4570-8f57-d3831d89329a")
    public final boolean isMultipleUnmaskAllowed() {
        return this.multipleUnmaskAllowed;
    }

    /**
     * Creates a drop command for an element that will be unmasked as a node.
     * @param command      the compound in which to add the created command.
     * @param dropLocation the point where the drop happened.
     * @param toUnmask the element to unmask.
     */
    @objid ("80be19c1-1dec-11e2-8cad-001ec947c8cc")
    protected Command createDropCommandForElement(Point dropLocation, MObject toUnmask) {
        // unmask it.
        final CreateRequest req = new CreateRequest();
        req.setLocation(dropLocation);
        req.setSize(new Dimension(-1, -1));
        req.setFactory(new ModelioCreationContext(toUnmask));
        final EditPart targetPart = getHost().getTargetEditPart(req);
        if (targetPart != null) {
            return targetPart.getCommand(req);
        } else {
            return null;
        }
        
    }

    /**
     * Default implementation of {@link IDiagramElementDropEditPolicyExtension#getUnmaskCommandFor(DiagramElementDropEditPolicy, MObject, Point)} that will unmask droppedElement as a link.
     */
    @objid ("8fdb26b3-2b4c-44d6-8921-a3157976bbe3")
    protected Command createDropCommandForLinkElement(Point dropLocation, MObject droppedElement) {
        final IGmLink gmLink = ((GmModel) getHost().getModel()).getDiagram().unmaskLink(droppedElement);
        if (gmLink != null) {
            return createUnmaskCommandForLink(dropLocation, (GmLink) gmLink);
        }
        return null;
    }

    @objid ("80be19bb-1dec-11e2-8cad-001ec947c8cc")
    protected final Command createSelectionCommand(GmModel toSelect) {
        final SelectionRequest request = new SelectionRequest();
        request.setType(RequestConstants.REQ_SELECTION);
        request.setLastButtonPressed(SWT.BUTTON_MASK);
        final EditPart toSelectEditPart = (EditPart) getHost().getViewer().getEditPartRegistry().get(toSelect);
        EditPart target = null;
        if (toSelectEditPart != null) {
            target = toSelectEditPart.getTargetEditPart(request);
            if (target == null) {
                target = toSelectEditPart;
            }
            Command selectionCommand = target.getCommand(request);
            if (selectionCommand == null) {
                selectionCommand = new RevealEditPartCommand(target);
            }
            return selectionCommand;
        }
        return null;
    }

    /**
     * Creates a drop command for an element that will be unmasked as a link.
     * @param dropLocation the point where the drop happened.
     * @param link the link.
     * @return the created command.
     */
    @objid ("4e033a8a-571c-49ac-82da-ea8fe6c4889d")
    protected UnmaskLinkCommand createUnmaskCommandForLink(final Point dropLocation, final GmLink link) {
        // Look for the diagram edit part
        AbstractDiagramEditPart diagEp = null;
        for (EditPart ep = getHost(); ep != null && diagEp == null; ep = ep.getParent()) {
            if (ep instanceof AbstractDiagramEditPart) {
                diagEp = (AbstractDiagramEditPart) ep;
            }
        }
        
        assert diagEp != null : getHost();
        return new UnmaskLinkCommand(link, diagEp, dropLocation);
    }

    /**
     * <p>
     * Creates the Command to handle a ModelElementDropRequest. This default implementation will delegate a CreateRequest for each dropped element.
     * </p>
     * @param request The drop request.
     * @return the created command.
     */
    @objid ("80be1997-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final Command getDropCommand(ModelElementDropRequest request) {
        final CompoundCommand command = new CompoundCommand();
        
        Point dropLocation = request.getDropLocation();
        final GmModel hostModel = (GmModel) getHost().getModel();
        final IGmDiagram diagram = hostModel.getDiagram();
        for (final MObject toUnmask : request.getDroppedElements()) {
            final GmModel previousUnmask = diagram.getExistingModelFor(toUnmask);
            Command cmd;
        
            if (this.multipleUnmaskAllowed || previousUnmask == null) {
                // FIXME related diagrams should not be tested here, they are handled by another policy...
                if (isLinkMetaclass(toUnmask) && !(toUnmask instanceof Dependency && ((Dependency) toUnmask).isStereotyped("ModelerModule", "related_diagram"))) {
                    cmd = createDropCommandForLinkElement(dropLocation, toUnmask);
                } else {
                    cmd = createDropCommandForElement(dropLocation, toUnmask);
                }
                // Introduce some offset, so that all elements are not totally on top of each other.
                dropLocation = dropLocation.getTranslated(20, 20);
            } else {
                // Otherwise, just select it.
                cmd = createSelectionCommand(previousUnmask);
            }
        
            if (cmd != null) {
                command.add(cmd);
            }
        
        }
        return command.isEmpty() ? null : command.unwrap();
    }

    @objid ("1650d989-9740-4e14-bad8-500bf49ed2ec")
    private boolean isLinkMetaclass(final MObject toUnmask) {
        return toUnmask.getMClass().isLinkMetaclass()
                || toUnmask instanceof BpmnDataAssociation; // BpmnDataAssociation is a mess: it is considered as a link in diagrams despite the metaclass not being tagged as a link
        
    }

    /**
     * <p>
     * Returns the edit part the DROP is to be processed on. If the Gm corresponding to the host edit part knows how to unmask each dropped element, the host is returned. Otherwise, <code>null</code> is returned.
     * </p>
     * <p>
     * Subclasses should redefine this method to provide "smart interactions".
     * </p>
     * @param request the drop request
     * @return the host if all dropped elements can be unmasked by the Gm, <code>null</code> otherwise.
     */
    @objid ("80be199f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPart getDropTargetEditPart(ModelElementDropRequest request) {
        // If either of the dropped elements cannot be unmasked, return null.
        for (final MObject droppedElement : request.getDroppedElements()) {
            final Object model = getHost().getModel();
            if (!(model instanceof GmModel)) {
                return null;
            }
        
            final GmModel hostModel = (GmModel) model;
            if (!hostModel.canUnmask(droppedElement)) {
                return null;
            }
        
        }
        // All dropped elements understood: return host!
        return getHost();
    }

    /**
     * <p>
     * Creates the Command to "smartly" handle a ModelElementDropRequest. This default implementation returns <code>null</code>.
     * </p>
     * <p>
     * Subclasses should redefine this method to provide "smart interactions".
     * </p>
     * @param request The drop request.
     * @return the created command.
     */
    @objid ("80be19b3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getSmartDropCommand(ModelElementDropRequest request) {
        return null;
    }

}
