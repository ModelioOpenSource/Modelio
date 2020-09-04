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

package org.modelio.diagram.editor.state.elements.join;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transposer;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.editor.state.elements.ForkJoinOrientation;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.RectangularFigure;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;

/**
 * EditPart for an Join Node.
 * 
 * @author fpoyer
 */
@objid ("f55ac8e4-55b6-11e2-877f-002564c97630")
public class JoinPrimaryNodeEditPart extends AbstractNodeEditPart {
    @objid ("f55ac8e8-55b6-11e2-877f-002564c97630")
    private ForkJoinOrientation currentOrientation = null;

    @objid ("f55ac8e9-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        RectangularFigure fig = new RectangularFigure();
        fig.setOpaque(true);
        // set style independent properties
        fig.setPreferredSize(70, 10);
        fig.setMinimumSize(new Dimension(70, 10));
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("f55ac8ee-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        this.installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
    }

    @objid ("f55ac8f1-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmJoinPrimaryNode joinModel = (GmJoinPrimaryNode) this.getModel();
        this.getFigure().getParent().setConstraint(this.getFigure(), joinModel.getLayoutData());
    }

    @objid ("f55ac8f4-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("f55c4f5b-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof RectangularFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
        
                final GmJoinPrimaryNode model = (GmJoinPrimaryNode) this.getModel();
                final ForkJoinOrientation orientation = (ForkJoinOrientation) model.getDisplayedStyle().getProperty(GmJoinStructuredStyleKeys.ORIENTATION);
                if (this.currentOrientation == null) {
                    this.currentOrientation = orientation;
                } else if (orientation != null && this.currentOrientation != orientation) {
                    updateOrientation(aFigure, orientation);
                }
            }
        }
    }

    @objid ("f55c4f62-55b6-11e2-877f-002564c97630")
    private void updateOrientation(IFigure aFigure, ForkJoinOrientation orientation) {
        DefaultNodeResizableEditPolicy resizePolicy = (DefaultNodeResizableEditPolicy) this.getParent()
                .getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
        if (resizePolicy != null) {
            resizePolicy.deactivate();
            // set the resize direction of the fork join node
            switch (orientation) {
            case HORIZONTAL:
                resizePolicy.setResizeDirections(PositionConstants.EAST_WEST);
                break;
            case VERTICAL:
                resizePolicy.setResizeDirections(PositionConstants.NORTH_SOUTH);
                break;
            }
            resizePolicy.activate();
        
            // rotate the fork join node according to the orientation
            doRotationFigure(aFigure);
        
            this.currentOrientation = orientation;
        }
    }

    @objid ("f55c4f66-55b6-11e2-877f-002564c97630")
    private void doRotationFigure(IFigure aFigure) {
        Rectangle oldBounds = aFigure.getBounds().getCopy();
        Transposer transposer = new Transposer();
        transposer.enable();
        Rectangle newBounds = transposer.t(oldBounds);
        int w = (newBounds.width - oldBounds.width);
        int h = (newBounds.height - oldBounds.height);
        
        if (aFigure.getParent() != null) {
            ChangeBoundsRequest resizeRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
            resizeRequest.setLocation(new Point(0, 0));
            resizeRequest.setSizeDelta(new Dimension(w, h));
            resizeRequest.setEditParts(getParent());
            Command command = getParent().getCommand(resizeRequest);
            getViewer().getEditDomain().getCommandStack().execute(command);
        } else {
            aFigure.setSize(w, h);
        }
    }

}
