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
package org.modelio.uml.activitydiagram.editor.elements.activitydiagram;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramEditLayoutPolicy;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.metamodel.diagrams.ActivityDiagram;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.uml.activitydiagram.editor.elements.partition.GmPartition;
import org.modelio.uml.activitydiagram.editor.elements.partition.PartitionToolKind;
import org.modelio.uml.activitydiagram.editor.elements.partitioncontainer.GmPartitionParameterContainer;

/**
 * LayoutPolicy that is specific to Activity Diagram background
 */
@objid ("29905fea-55b6-11e2-877f-002564c97630")
public class ActivityDiagramLayoutPolicy extends DiagramEditLayoutPolicy {
    /**
     * <p>
     * If the request if to CREATE a partition container, return host.
     * </p>
     * <p>
     * If the request is to CREATE a sibling partition or to create an inner partition, ignore.
     * </p>
     * <p>
     * If the request is to ADD a partition, return null (do not accept the reparenting of a partition directly on the diagram background: at least a partition container should be used). We have to do this, because ActivityPartition is considered a
     * supported type, since it is possible to create partition containers on the diagram background.
     * </p>
     * <p>
     * Otherwise return the result of super.getTargetEditPart().
     * </p>
     */
    @objid ("29905fee-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(Request request) {
        // Only care about specific creation or add requests, super can handle
        // the rest.
        if (RequestConstants.REQ_ADD.equals(request.getType()) || RequestConstants.REQ_MOVE.equals(request.getType())) {
            // If the request is to ADD or MOVE a partition, return the
            // "top level" partition container that is currently holding the
            // partition (do not allow to reparent to the diagram background).
            // Else if the request to to MOVE or ADD a parameter container
            // (should only be MOVE by the way), allow it and return host.
            List<?> movedEditParts = ((ChangeBoundsRequest) request).getEditParts();
            for (Object editPartObj : movedEditParts) {
                if (((EditPart) editPartObj).getModel() instanceof GmPartition) {
                    // Move up in the composition stack to find the top level
                    // partition container (which itself is contained in the
                    // activity parameter container)
                    EditPart container = ((EditPart) editPartObj).getParent();
                    while (container.getParent() != null
                            && !(container.getParent().getModel() instanceof GmPartitionParameterContainer)) {
                        container = container.getParent();
                    }
                    return container;
                } else if (((EditPart) editPartObj).getModel() instanceof GmPartitionParameterContainer) {
                    return getHost();
                }
            }
            // No partition nor parameter container in the moved edit
            // parts: super should be able to handle the rest!
            return super.getTargetEditPart(request);
        
        } else if (RequestConstants.REQ_CREATE != request.getType()) {
            return super.getTargetEditPart(request);
        }
        
        // Only care about request for partitions, super can handle the rest.
        final ModelioCreationContext ctx = (ModelioCreationContext) ((CreateRequest) request).getNewObject();
        if (ctx.getJavaClass() != ActivityPartition.class) {
            return super.getTargetEditPart(request);
        }
        // Get the specific property "kind" from the tool, to know exactly what
        // is requested: a partition container, a sibling partition, or an inner
        // partition.
        PartitionToolKind kind = getPartitionKind(ctx);
        switch (kind) {
        case SIBLING: {
            // Siblings are created by partition containers themselves.
            return null;
        }
        case INNER: {
            // Inner partitions are created by partition containers
            // themselves.
            return null;
        }
        case HORIZONTAL_CONTAINER: {
            if (canHandle(ctx.getMetaclass().getMetamodel().getMClass(ActivityPartition.class), null)) {
                return getHost();
            }
            // else
            return null;
        }
        case VERTICAL_CONTAINER: {
            if (canHandle(ctx.getMetaclass().getMetamodel().getMClass(ActivityPartition.class), null)) {
                return getHost();
            }
            // else
            return null;
        }
        }
        // no kind: this is probably a drop from explorer, do not handle here.
        return null;
    }

    /**
     * If the request if to create a partition container, return specific command. If the request is to create a sibling partition or to create an inner partition, ignore. Other return the result of super.getCreateCommand().
     */
    @objid ("29905ff5-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        // Only care about request for partitions, super can handle the rest.
        final ModelioCreationContext ctx = (ModelioCreationContext) request.getNewObject();
        if (ctx.getJavaClass() != ActivityPartition.class) {
            return super.getCreateCommand(request);
        }
        // Get the specific property "kind" from the tool, to know exactly what
        // is requested: a partition container, a sibling partition, or an inner
        // partition.
        PartitionToolKind kind = getPartitionKind(ctx);
        switch (kind) {
        case SIBLING: {
            // Siblings are created by partition containers themselves.
            return null;
        }
        case INNER: {
            // Inner partitions are created by partition containers
            // themselves.
            return null;
        }
        case HORIZONTAL_CONTAINER: {
            if (canHandle(ctx.getMetaclass().getMetamodel().getMClass(ActivityPartition.class), null)) {
                // modify the request to ignore the "size"
                request.setSize(null);
                return new CreatePartitionContainerCommand(request, (ActivityDiagramEditPart) getHost(), ctx,
                        (Rectangle) getConstraintFor(request), false);
            }
            // else
            return null;
        }
        case VERTICAL_CONTAINER: {
            if (canHandle(ctx.getMetaclass().getMetamodel().getMClass(ActivityPartition.class), null)) {
                // modify the request to ignore the "size"
                request.setSize(null);
                return new CreatePartitionContainerCommand(request, (ActivityDiagramEditPart) getHost(), ctx,
                        (Rectangle) getConstraintFor(request), true);
            }
            // else
            return null;
        }
        }
        // Should never end here...
        return null;
    }

    @objid ("29905ffc-55b6-11e2-877f-002564c97630")
    @Override
    protected Object getConstraintFor(CreateRequest request) {
        // Only care about request for partitions, super can handle the rest.
        final ModelioCreationContext ctx = (ModelioCreationContext) request.getNewObject();
        if (ctx.getJavaClass() != ActivityPartition.class) {
            return super.getConstraintFor(request);
        }
        // Get the specific property "kind" from the tool, to know exactly what
        // is requested: a partition container, a sibling partition, or an inner
        // partition.
        PartitionToolKind kind = getPartitionKind(ctx);
        switch (kind) {
        case SIBLING:
        case INNER: {
            // Siblings and Inner partitions are created by partition
            // containers
            // themselves.
            return null;
        }
        case HORIZONTAL_CONTAINER:
        case VERTICAL_CONTAINER: {
            IFigure figure = getLayoutContainer();
        
            Point where = request.getLocation().getCopy();
            Dimension size = request.getSize();
        
            figure.translateToRelative(where);
            figure.translateFromParent(where);
            where.translate(getLayoutOrigin().getNegated());
        
            if (size == null || size.isEmpty()) {
                // If a default size is not provided, define one.
                if (ctx.getElementToUnmask() != null) {
                    // If we are really asking to unmask a single existing
                    // partition, use a default size for 1 partition.
                    return getConstraintFor(new Rectangle(where, new Dimension(300, 600)));
                }
                // else we will be creating 2 partitions, use a default size
                // covering both.
                return getConstraintFor(new Rectangle(where, new Dimension(600, 600)));
            }
            size = size.getCopy();
            figure.translateToRelative(size);
            figure.translateFromParent(size);
            return getConstraintFor(new Rectangle(where, size));
        }
        
        }
        // Should never end here...
        return null;
    }

    /**
     * Get the partition orientation from the context.
     * <p>
     * If empty or <code>null</code>, fix it with the value from the {@link ActivityDiagram} itself.
     * </p>
     */
    @objid ("f46af23c-c829-4d98-b121-aa921e6e78fa")
    private PartitionToolKind getPartitionKind(final ModelioCreationContext ctx) {
        PartitionToolKind kind;
        String kindProperty = (String) ctx.getProperties().get("kind");
        if (kindProperty == null || kindProperty.isEmpty()) {
            final boolean isVertical = ((ActivityDiagram) ((GmModel) getHost().getModel()).getDiagram().getRelatedElement()).isIsVertical();
            kind = isVertical ? PartitionToolKind.VERTICAL_CONTAINER : PartitionToolKind.HORIZONTAL_CONTAINER;
            ctx.getProperties().put("kind", kind.toString());
        } else {
            kind = PartitionToolKind.valueOf(kindProperty);
        }
        return kind;
    }

}
