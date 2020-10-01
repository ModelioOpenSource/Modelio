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

package org.modelio.diagram.elements.common.portcontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;

/**
 * Move/resize policy for satellite labels.
 * <p>
 * The request height is modified so that: <ul>
 * <li> the new size fits the label content.
 * <li> the satellite and container nearest borders keep the same distance.
 * </ul>
 * 
 * @author cmarin
 */
@objid ("9c223dc0-8de5-426d-bf97-efecb91ee5b0")
public class SatelliteLabelMoveResizeEditPolicy extends DefaultNodeResizableEditPolicy {
    @objid ("82bc9f10-c230-463b-ae1f-1f0b57a690b7")
    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        ChangeBoundsRequest r = patchResizeRequest(request);
        return super.getResizeCommand(r);
    }

    @objid ("19053085-833c-4ee4-885f-932234f940a8")
    @Override
    public void showSourceFeedback(Request request) {
        if (REQ_RESIZE.equals(request.getType())) {
            ChangeBoundsRequest r = patchResizeRequest((ChangeBoundsRequest) request);
            showChangeBoundsFeedback(r);
        } else {
            super.showSourceFeedback(request);
        }
    }

    @objid ("c5c769f0-a635-4ea5-a544-f15459d64c67")
    private ChangeBoundsRequest copy(ChangeBoundsRequest request) {
        ChangeBoundsRequest req = new ChangeBoundsRequest(REQ_RESIZE_CHILDREN);
        req.setEditParts(getHost());
        
        req.setMoveDelta(request.getMoveDelta().getCopy());
        req.setSizeDelta(request.getSizeDelta().getCopy());
        req.setLocation(request.getLocation());
        req.setExtendedData(request.getExtendedData());
        req.setResizeDirection(request.getResizeDirection());
        return req;
    }

    /**
     * Modifies the given request so better satisfy the figure preferred size.
     * @param request the request to hack
     */
    @objid ("7cb9151f-e6a8-499f-92a9-fe7e36afbd78")
    protected ChangeBoundsRequest patchResizeRequest(ChangeBoundsRequest origRequest) {
        ChangeBoundsRequest request = copy(origRequest);
        
        IFigure fig = getHostFigure();
        
        // Get asked figure bounds
        Rectangle askedBounds = new PrecisionRectangle(fig.getBounds());
        
        fig.translateToAbsolute(askedBounds);
        askedBounds.translate(request.getMoveDelta());
        askedBounds.resize(request.getSizeDelta());
        fig.translateToRelative(askedBounds);
        
        // Get the figure preferred size for asked bounds
        Dimension bestSize = new PrecisionDimension(computeBestSize(request, askedBounds));
        fig.translateToAbsolute(bestSize);
        
        Rectangle initBounds = new PrecisionRectangle(fig.getBounds());
        fig.translateToAbsolute(initBounds);
        
        // Set size delta
        request.setSizeDelta(bestSize.getShrinked(initBounds.getSize()));
        
        // Ask the figure to have still the same distance from its owner.
        // This would need to know which direction is the figure from its owner and align it accordingly
        IFigure refFig = getRefFigure();
        if (refFig != null) {
            fixFigurePosition(request, fig, initBounds, refFig);
        }
        
        /*System.out.println("patch request= "+origRequest.getMoveDelta()+" size="+origRequest.getSizeDelta());
        System.out.println("   new request= "+request.getMoveDelta()+" size="+request.getSizeDelta());
        System.out.println("   size diff="+bestSize);*/
        return request;
    }

    /**
     * Get the main node figure.
     * 
     * @return the main node figure.
     */
    @objid ("1aab4bca-425c-4442-b688-5d2b7d81f2a3")
    private IFigure getRefFigure() {
        EditPart parentEditPart = getHost().getParent();
        
        if (parentEditPart != null) {
            GmPortContainer pc = (GmPortContainer) parentEditPart.getModel();
            GmNodeModel mainNode = pc.getMainNode();
        
            for (Object o : parentEditPart.getChildren()) {
                GraphicalEditPart e = (GraphicalEditPart) o;
                Object m = e.getModel();
        
                if (m == mainNode) {
                    return e.getFigure();
                }
        
            }
        }
        return null;
    }

    /**
     * Modify the request so that the figure border keeps the same distance from its owner.
     * 
     * @param request the resize request to modify
     * @param fig the figure being resized
     * @param initBounds figure current bounds in absolute coordinates
     * @param refFigure the main figure
     */
    @objid ("da8b3eef-3111-45da-a00d-79eb240b1d93")
    private void fixFigurePosition(ChangeBoundsRequest request, IFigure fig, Rectangle initBounds, IFigure refFigure) {
        if (true) {
            Rectangle refBounds = new PrecisionRectangle(refFigure.getBounds());
            refFigure.translateToAbsolute(refBounds);
        
            // move depends on which direction the figure is from its owner to align it accordingly
            Dimension sizeDelta = request.getSizeDelta();
            switch (GeomUtils.getDirection(initBounds.getCenter(), refBounds)) {
            case EAST:
                // stick on left
                request.setMoveDelta(new PrecisionPoint(0, - sizeDelta.preciseHeight() / 2));
                break;
            case NONE:
                // Move the figure so that it has the same center point after resize
                request.setMoveDelta(new PrecisionPoint(- sizeDelta.preciseWidth() / 2, - sizeDelta.preciseHeight() / 2));
                break;
            case NORTH:
                request.setMoveDelta(new PrecisionPoint(- sizeDelta.preciseWidth() / 2, - sizeDelta.preciseHeight()));
                break;
            case SOUTH:
                request.setMoveDelta(new PrecisionPoint(- sizeDelta.preciseWidth() / 2, 0));
                break;
            case WEST:
                request.setMoveDelta(new PrecisionPoint(- sizeDelta.preciseWidth()    , - sizeDelta.preciseHeight() / 2));
                break;
            default:
                // Move the figure so that it has the same center point after resize
                request.setMoveDelta(new PrecisionPoint(- sizeDelta.preciseWidth() / 2, - sizeDelta.preciseHeight() / 2));
                break;
        
            }
        }
    }

    @objid ("76bd8c31-51b4-4c78-8f34-ab87c4f187dd")
    private Dimension computeBestSize(ChangeBoundsRequest request, Rectangle askedBounds) {
        IFigure fig = getHostFigure();
        Dimension delta = request.getSizeDelta();
        
        if (delta.height() == 0) {
            return fig.getPreferredSize(askedBounds.width(), -1);
        } else if (delta.width() == 0) {
            return fig.getPreferredSize(askedBounds.width(), askedBounds.height());
        } else {
            return fig.getPreferredSize(askedBounds.width(), -1);
        }
    }

}
