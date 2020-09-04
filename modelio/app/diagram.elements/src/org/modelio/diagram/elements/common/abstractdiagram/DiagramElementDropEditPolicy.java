/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.elements.common.abstractdiagram;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Adds the handling of dropped links: looks for both ends (unmask them if needed) then unmask the link itself.
 */
@objid ("7e0f746a-1dec-11e2-8cad-001ec947c8cc")
public class DiagramElementDropEditPolicy extends DefaultElementDropEditPolicy {
    /**
     * Constant to be referenced to get drop extensions from the {@link GraphicalViewer} properties.
     */
    @objid ("5973c597-3384-4381-86fa-eaf9c8c6940f")
    public static final String DROP_EXTENSIONS = "dropExtensions";

    @objid ("7e0f746d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command createDropCommandForElement(final Point p, MObject toUnmask) {
        Collection<IDiagramElementDropEditPolicyExtension> dropExtensions = getDropExtensions();
        if (dropExtensions != null) {
            for (IDiagramElementDropEditPolicyExtension extension : dropExtensions) {
                Command cmd = extension.getUnmaskCommandFor(this, toUnmask, p);
                if (cmd != null) {
                    return cmd;
                }
            }
        }
        return null;
    }

    /**
     * For diagram the default feedback provided by the base class is ugly because it has a background Specialize and remove the background. Code is tricky here otherwise we get flickering background: we make the background transparent only when it is
     * created
     */
    @objid ("7e11d6a2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showTargetFeedback(final Request request) {
        if (request.getType().equals(ModelElementDropRequest.TYPE)) {
            if (this.highlight == null) {
                super.showTargetFeedback(request);
                this.highlight.setOpaque(false);
                this.highlight.setBackgroundColor(null);
            } else {
                super.showTargetFeedback(request);
            }
        }
    }

    @objid ("7e11d6be-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPart getDropTargetEditPart(ModelElementDropRequest request) {
        // If either of the dropped elements cannot be unmasked, return null.
        for (final MObject droppedElement : request.getDroppedElements()) {
            if (!canDiagramUnmask(droppedElement) && (!request.isSmart() || !canDropExtensionUnmask(droppedElement))) {
                return null;
            }
        }
        // All dropped elements understood: return host!
        return getHost();
    }

    @objid ("89ec3faf-7def-4210-bdac-c9bfc0fb917e")
    @SuppressWarnings ("unchecked")
    private Collection<IDiagramElementDropEditPolicyExtension> getDropExtensions() {
        return (Collection<IDiagramElementDropEditPolicyExtension>) getHost().getViewer().getProperty(DiagramElementDropEditPolicy.DROP_EXTENSIONS);
    }

    /**
     * Creates a drop command for an element that will be unmasked as a link.
     * @param dropLocation the point where the drop happened.
     * @param link the link.
     * @return the created command.
     */
    @objid ("c7cc5e2c-ae9e-4574-a771-41542c87c83f")
    public Command createDropCommandForLink(final Point dropLocation, final IGmLink link) {
        return new UnmaskLinkCommand(link, (AbstractDiagramEditPart) getHost(), dropLocation);
    }

    @objid ("a55fac9a-7c22-4686-966c-c48e2d3c2c1f")
    private Deque<MObject> getGraphicalHierarchy(GmAbstractDiagram gmDiagram, MObject initialElement) {
        // Go through composition stack (upward, up to the Project if needed)
        // and while finding elements that are unmaskable,
        // look if they are already unmasked. If not, unmask them (in reverse
        // order).
        MObject element = initialElement;
        Deque<MObject> hierarchy = new ArrayDeque<>();
        while (element != null // < In case we went up too high!
                && (isMultipleUnmaskAllowed() || gmDiagram.getExistingModelFor(element) == null) // the element should not be already unmasked, or multiple unmask should be allowed
                && (gmDiagram.canUnmask(element) || gmDiagram.canUnmaskGenericElements())) { // the element must be unmaskable in this diagram
        
            // is the hierarchy complete
            final IDiagramElementDropEditPolicyExtension extension = getUnmaskingExtension(gmDiagram, hierarchy, element);
            if (extension == null) {
                break;
            }
        
            // add the element to the hierarchy
            hierarchy.push(element);
        
            // get next element in hierarchy from the extension
            element = extension.getParentInGraphicalHierarchy(gmDiagram, element);
        }
        
        // add the first excluded element to the hierarchy
        if (element != null) {
            hierarchy.push(element);
        }
        return hierarchy;
    }

    @objid ("47cc1283-74cd-42da-94f3-c10292ab30e9")
    private IDiagramElementDropEditPolicyExtension getUnmaskingExtension(GmAbstractDiagram gmDiagram, Deque<MObject> hierarchy, MObject candidate) {
        for (IDiagramElementDropEditPolicyExtension extension : getDropExtensions()) {
            if (extension.isToBeAddedToHierarchy(gmDiagram, hierarchy, candidate)) {
                return extension;
            }
        }
        return null;
    }

    /**
     * Creates a drop command for an element that will be unmasked as a node.
     * @param dropLocation the point where the drop happened.
     * @param toUnmask the element to unmask.
     * @return the created command.
     */
    @objid ("f6b4ddc0-5f65-47b4-95cb-9a6475694e90")
    public Command createDropCommandForNodeHierarchy(Point dropLocation, MObject toUnmask) {
        final AbstractDiagramEditPart host = (AbstractDiagramEditPart) getHost();
        final GmAbstractDiagram gmDiagram = (GmAbstractDiagram) host.getModel();
        
        // Compute the graphical hierarchy
        Deque<MObject> elementsHierarchy = getGraphicalHierarchy(gmDiagram, toUnmask);
        
        // and unmask it
        return createUnmaskHierarchyCommand(host, dropLocation, elementsHierarchy);
    }

    @objid ("28108045-8e85-490e-91a2-7693c656f15f")
    private Command createUnmaskHierarchyCommand(final AbstractDiagramEditPart host, Point initialDropLocation, final Deque<MObject> elementsHierarchy) {
        IGmDiagram gmDiagram = (IGmDiagram) host.getModel();
        
        // Take the first element out of the hierarchy as the first parent.
        MObject parent = elementsHierarchy.size() > 1 ? elementsHierarchy.pop() : null;
        
        CompoundCommand hierarchyUnmaskCommand = new CompoundCommand();
        
        Point dropLocation = initialDropLocation.getCopy();
        MObject child;
        if (parent != null && !isMultipleUnmaskAllowed() && gmDiagram.getExistingModelFor(parent) != null) {
            // an ancestor was found unmasked in the diagram: use it as "root"
            // of the hierarchy to unmask.
            child = parent;
        } else if (!elementsHierarchy.isEmpty()) {
            // We didn't find any unmasked ancestor:
            // Make it so that the first element of the hierarchy is unmasked
            // directly in the diagram.
            child = elementsHierarchy.pop();
        
            Command cmd = super.createDropCommandForElement(dropLocation, child);
            if (cmd != null) {
                hierarchyUnmaskCommand.add(cmd);
            }
        
            dropLocation = dropLocation.getCopy().setLocation(0, 0);
            // It will then be used as "root" of the hierarchy to unmask
        } else {
            // Hierarchy is inconsistent
            return null;
        }
        
        while (!elementsHierarchy.isEmpty()) {
            MObject previous = child;
            child = elementsHierarchy.pop();
            hierarchyUnmaskCommand.add(new DeferredUnmaskCommand(previous, child, dropLocation, host));
        
            dropLocation = dropLocation.getCopy().setLocation(0, 0);
        }
        return hierarchyUnmaskCommand.unwrap();
    }

    @objid ("8fe9ae94-d714-4bd4-baad-020e838b8d4c")
    private boolean canDiagramUnmask(MObject droppedElement) {
        boolean canUnmaskAsNode = ((GmModel) getHost().getModel()).canUnmask(droppedElement);
        if (canUnmaskAsNode) {
            return true;
        }
        
        IGmDiagram model = (IGmDiagram) getHost().getModel();
        IGmLink gmLink = model.unmaskLink(droppedElement);
        boolean canUnmaskAsLink = (gmLink != null);
        if (gmLink != null) {
            gmLink.delete();
        }
        return canUnmaskAsLink;
    }

    @objid ("24b39df2-5137-4779-a3a1-e0e468a2bb21")
    private boolean canDropExtensionUnmask(MObject droppedElement) {
        for (IDiagramElementDropEditPolicyExtension extension : getDropExtensions()) {
            if (extension.canUnmask(this, droppedElement)) {
                return true;
            }
        }
        return false;
    }

    /**
     * C'tor forbidding multiple unmask.
     */
    @objid ("74f91ad7-bd45-4593-85d7-979d5fe595b9")
    public DiagramElementDropEditPolicy() {
        super(false);
    }

    /**
     * C'tor.
     * @param multipleUnmaskAllowed allow or forbid multiple unmask for model elements.
     */
    @objid ("45449ee2-30e0-41b9-a420-5f67660dea1f")
    public DiagramElementDropEditPolicy(boolean multipleUnmaskAllowed) {
        super(multipleUnmaskAllowed);
    }

}
