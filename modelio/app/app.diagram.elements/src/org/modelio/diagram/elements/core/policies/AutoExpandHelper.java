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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.commands.NoopCommand;
import org.modelio.diagram.elements.core.figures.ChainedLayout;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * Helper class for auto expand behavior.
 * 
 * @author cmarin
 * @since Modelio 3.4
 */
@objid ("6554b922-fdc2-4368-99bb-1da57b7af463")
public class AutoExpandHelper {
    /**
     * Get a command for the given request on the given edit part and executes it immediately.
     * <p>
     * Does nothing if request is null.
     * Logs a warning if no command could be obtained or if the command is not executable (if debug level is enabled).
     * 
     * @param req the request. May be null.
     * @param target the edit part that should run the request.
     * @return true if resizing was done else false.
     */
    @objid ("a52abbc6-f2a4-4d81-a117-e850793b6aab")
    public static boolean executeExpandRequest(ChangeBoundsRequest req, EditPart target) {
        if (req != null) {
            Command cmd = target.getCommand(req);
            if (cmd != null && cmd.canExecute()) {
                cmd.execute();
                return true;
            } else {
                if (DiagramElements.LOG.isDebugEnabled()) {
                    DiagramElements.LOG.warning("<%s> is not resizeable for request: move= %s , size+= %s", target.getClass().getSimpleName(), req.getMoveDelta(), req.getSizeDelta());
                    DiagramElements.LOG.warning("  parent edit part= <%s>;\n\t target edit part=%s\n\t command=%s", target.getParent(), target, cmd);
                    DiagramElements.LOG.warning(new Throwable("stack trace"));
                }
            }
        }
        return false;
    }

    /**
     * Compute an auto expand command from a child edit part move/resize request.
     * 
     * @param request a resize/move request for container children
     * @param containerEditPart the container edit part
     * @param layoutContainer the container figure directly containing child edit part figures.
     * @return the auto expand command or null if auto expand not supported.
     */
    @objid ("bbd6f1a5-6d14-4f74-870d-b7127773d1e6")
    public static Command getExpandContainerCommand(ChangeBoundsRequest request, EditPart containerEditPart, IFigure layoutContainer) {
        // First check whether this figure must be resized
        
        Dimension curSize = layoutContainer.getSize();
        Dimension requiredSize = AutoExpandHelper.computePreferredContainerSize(request, containerEditPart, layoutContainer);
        
        if (!curSize.contains(requiredSize)) {
            // Resize needed, ask for resize
            final Dimension finalSize = requiredSize.getUnioned(curSize);
            Dimension sizeDelta = finalSize.getShrinked(curSize);
            layoutContainer.translateToAbsolute(sizeDelta);
        
            ChangeBoundsRequest r2 = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE_CHILDREN);
            r2.setEditParts(containerEditPart);
            r2.setLocation(request.getLocation());
            r2.setMoveDelta(request.getMoveDelta());
            r2.setSizeDelta(sizeDelta);
        
            EditPart tep = containerEditPart.getParent();
            if (tep != null) {
                Command resizeContainerCommand = tep.getCommand(r2);
                
                if ((resizeContainerCommand == null || !resizeContainerCommand.canExecute()) && DiagramElements.LOG.isDebugEnabled()) {
                    DiagramElements.LOG.debug("<%s> is not resizeable for request: %s", containerEditPart, RequestHelper.toString(r2));
                    DiagramElements.LOG.debug("  parent edit part= <%s>;\n\t command=%s", tep, resizeContainerCommand);
                    DiagramElements.LOG.debug(new Throwable("stack trace"));
                }
        
                return resizeContainerCommand;
            } else {
        
                return null;
            }
        }
        return NoopCommand.INSTANCE;
    }

    /**
     * Compute an auto fit to preferred size command from a child edit part move/resize request.
     * 
     * @param request a resize/move request for container children
     * @param containerEditPart the container edit part
     * @param layoutContainer the container figure directly containing child edit part figures.
     * @return the auto expand command or null if auto expand not supported.
     */
    @objid ("6793fafc-32ff-4f07-b107-5c052c21a4cd")
    public static ChangeBoundsRequest getFitToPreferredSizeRequest(ChangeBoundsRequest request, EditPart containerEditPart, IFigure layoutContainer) {
        // First check whether this figure must be resized
        
        Dimension curSize = layoutContainer.getSize();
        Dimension requiredSize = AutoExpandHelper.computePreferredContainerSize(request, containerEditPart, layoutContainer);
        
        if (!curSize.equals(requiredSize)) {
            // Resize needed, ask for resize
            Dimension sizeDelta = requiredSize.getShrinked(curSize);
            layoutContainer.translateToAbsolute(sizeDelta);
        
            ChangeBoundsRequest r2 = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
            r2.setEditParts(containerEditPart);
            r2.setLocation(request.getLocation());
            r2.setMoveDelta(request.getMoveDelta());
            r2.setSizeDelta(sizeDelta);
        
            return r2;
        }
        return null;
    }

    /**
     * Compute an auto expand request for a child edit part addition.
     * <p>
     * The child edit part must already be owned by the parent one.
     * 
     * @param containerEditPart the parent edit part.
     * @param newChild the new child edit part
     * @param layoutContainer the container figure that directly own the children edit part figures.
     * @return a resize request or null if no resize is needed.
     */
    @objid ("5c795ca8-f07d-4f2a-81f0-2c93ec49c932")
    public static ChangeBoundsRequest getNewChildAutoExpandRequest(EditPart containerEditPart, GraphicalEditPart newChild, IFigure layoutContainer) {
        // First check whether this figure must be resized
        
        Dimension curSize = layoutContainer.getSize();
        Dimension requiredSize = AutoExpandHelper.computeRequiredContainerSizeForNewChild(newChild, containerEditPart, layoutContainer);
        
        if (!curSize.contains(requiredSize)) {
            // Resize needed, ask for resize
        
            // No dimension part must shrink
            requiredSize.union(curSize);
        
            ChangeBoundsRequest r2 = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
            r2.setEditParts(containerEditPart);
            r2.getSizeDelta().expand(requiredSize).shrink(curSize);
            layoutContainer.translateToAbsolute(r2.getSizeDelta());
        
            return r2;
        
        }
        return null;
    }

    @objid ("2d56d43b-c120-4574-8109-2758ad86fbbf")
    protected static Dimension computePreferredContainerSize(ChangeBoundsRequest request, EditPart containerEditPart, IFigure layoutContainer) {
        List<GraphicalEditPart> reqChildren = request.getEditParts();
        List<GraphicalEditPart> containerChildren = containerEditPart.getChildren();
        
        // First force container children preferred size depending on whether
        // they are involved in the request or not.
        for (GraphicalEditPart child : containerChildren) {
            if (reqChildren.contains(child)) {
                // Compute involved child bounds from the request and temporary force
                // their preferred size to the computed one.
                Rectangle constraint = AutoExpandHelper.getNewBoundsFor(request, child);
                TempSizeLayout.apply(child.getFigure(), constraint.getSize());
            } else {
                // force preferred size to current one
                TempSizeLayout.apply(child.getFigure(), child.getFigure().getSize());
            }
        }
        
        // Compute container required size
        Dimension requiredSize = layoutContainer.getPreferredSize();
        
        // restore children old layout
        for (GraphicalEditPart child : containerChildren) {
            TempSizeLayout.restore(child.getFigure());
        }
        return requiredSize;
    }

    @objid ("6616b43a-ac78-4bfe-b91d-1553cbacc909")
    protected static Dimension computeRequiredContainerSizeForNewChild(GraphicalEditPart newChild, EditPart containerEditPart, IFigure layoutContainer) {
        List<GraphicalEditPart> containerChildren = containerEditPart.getChildren();
        
        // First force container children preferred size depending on whether
        // they are involved in the request or not.
        for (GraphicalEditPart child : containerChildren) {
            if (child != newChild) {
                // force preferred size to current one
                TempSizeLayout.apply(child.getFigure(), child.getFigure().getSize());
            }
        }
        
        // Compute container required size
        Dimension requiredSize = layoutContainer.getPreferredSize();
        
        // restore children old layout
        for (GraphicalEditPart child : containerChildren) {
            if (child != newChild) {
                TempSizeLayout.restore(child.getFigure());
            }
        }
        return requiredSize;
    }

    @objid ("cb4f94a9-8f95-4541-a505-60b0eee46606")
    private static Rectangle getNewBoundsFor(ChangeBoundsRequest request, GraphicalEditPart child) {
        IFigure childFig = child.getFigure();
        Rectangle r = childFig.getBounds().getCopy();
        
        childFig.translateToAbsolute(r);
        r.translate(request.getMoveDelta());
        r.resize(request.getSizeDelta());
        childFig.translateToRelative(r);
        return r;
    }

    /**
     * Layout manager that forces preferred size.
     * <p>
     * This is used to temporarily force artificially a figure preferred size,
     * skip layout and nothing else.
     * 
     * @author cmarin
     */
    @objid ("513a3940-2452-4228-8813-d2ac0073e164")
    protected static class TempSizeLayout extends ChainedLayout {
        @objid ("139da0af-9c6f-40a4-8dc5-0e37106c955d")
        private Dimension prefSize;

        @objid ("5d2d7338-fd3a-4efb-9931-ccb091b9d4b3")
        public static TempSizeLayout apply(IFigure fig, Dimension prefSize) {
            TempSizeLayout l = new TempSizeLayout(fig.getLayoutManager(), prefSize);
            fig.setLayoutManager(l);
            return l;
        }

        @objid ("c9bc55ec-c99c-43df-ad2c-f242a0937a8d")
        public static void restore(IFigure fig) {
            ChainedLayout.removeLayout(fig, TempSizeLayout.class);
        }

        /**
         * constructor.
         * 
         * @param old the old layout manager
         * @param prefSize the preferred size to force.
         */
        @objid ("cf85c946-fe32-49ac-ab9d-525f96d2d1e3")
        public TempSizeLayout(LayoutManager old, Dimension prefSize) {
            super(old);
            this.prefSize = prefSize;
        }

        @objid ("34b9184a-a9ba-42b7-bd58-e5bc260b8cde")
        @Override
        public void layout(IFigure container) {
            // ignore
        }

        @objid ("f621c6d3-bd59-439d-9e28-9b0f01ba122c")
        @Override
        public Dimension getMinimumSize(IFigure container, int wHint, int hHint) {
            if (getChained() == null) {
                return this.prefSize;
            } else {
                // force hints to include preferred size
                int w = wHint;
                int h = hHint;
                if (w < 0 || w < this.prefSize.width()) {
                    w = this.prefSize.width();
                }
                if (h < 0 || h < this.prefSize.height()) {
                    h = this.prefSize.height();
                }
            
                return getChained().getMinimumSize(container, w, h);
            }
        }

        @objid ("730a2dfb-cb5e-4ee3-85dd-2df36f817886")
        @Override
        public Dimension getPreferredSize(IFigure container, int wHint, int hHint) {
            return this.prefSize;
        }

    }

}
