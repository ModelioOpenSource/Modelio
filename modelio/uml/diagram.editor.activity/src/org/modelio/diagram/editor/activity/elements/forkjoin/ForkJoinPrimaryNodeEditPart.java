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

package org.modelio.diagram.editor.activity.elements.forkjoin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.editor.activity.elements.policies.CreateFlowEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.RectangularFigure;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;

/**
 * EditPart for a {@link GmForkJoinPrimaryNode} Node.
 * 
 * @author fpoyer
 */
@objid ("2a7fefda-55b6-11e2-877f-002564c97630")
public class ForkJoinPrimaryNodeEditPart extends AbstractNodeEditPart {
    @objid ("33a05cdd-55b6-11e2-877f-002564c97630")
    private ForkOrientation currentOrientation = null;

    @objid ("2a7fefde-55b6-11e2-877f-002564c97630")
    private Label labelFigure;

    /**
     * Creates the Figure to be used as this part's main visuals.
     */
    @objid ("2a7fefe0-55b6-11e2-877f-002564c97630")
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

    @objid ("2a7fefe6-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new CreateFlowEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
    }

    @objid ("2a7fefe9-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmForkJoinPrimaryNode forkJoinModel = (GmForkJoinPrimaryNode) getModel();
        getFigure().getParent().setConstraint(getFigure(), forkJoinModel.getLayoutData());
    }

    @objid ("2a7fefec-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("2a7feff1-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof RectangularFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
        
                final GmForkJoinPrimaryNode model = (GmForkJoinPrimaryNode) getModel();
                final ForkOrientation orientation = (ForkOrientation) model.getDisplayedStyle().getProperty(GmForkJoinStructuredStyleKeys.ORIENTATION);
                if (this.currentOrientation == null) {
                    this.currentOrientation = orientation;
                } else if (orientation != null && this.currentOrientation != orientation) {
                    updateOrientation(aFigure, orientation);
                }
            }
        }
    }

    @objid ("2a7feff8-55b6-11e2-877f-002564c97630")
    private void updateOrientation(IFigure aFigure, ForkOrientation orientation) {
        DefaultNodeResizableEditPolicy resizePolicy = (DefaultNodeResizableEditPolicy) getParent().getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
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
            default:
                break;
            }
            resizePolicy.activate();
        
            // rotate the fork join node according to the orientation
            doRotationFigure(aFigure);
        
            this.currentOrientation = orientation;
        }
    }

    @objid ("2a7feffc-55b6-11e2-877f-002564c97630")
    private void doRotationFigure(IFigure aFigure) {
        Rectangle oldBounds = aFigure.getBounds();
        Point center = oldBounds.getCenter();
        
        Rectangle newBounds = oldBounds.getCopy();
        newBounds.translate(-center.x(), -center.y());
        newBounds.transpose();
        newBounds.translate(center);
        
        if (aFigure.getParent() != null) {
            ChangeBoundsRequest resizeRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
            RequestHelper.setDeltas(resizeRequest, aFigure, newBounds);
            Command command = getParent().getCommand(resizeRequest);
            getViewer().getEditDomain().getCommandStack().execute(command);
        } else {
            aFigure.setBounds(newBounds);
        }
    }

}
