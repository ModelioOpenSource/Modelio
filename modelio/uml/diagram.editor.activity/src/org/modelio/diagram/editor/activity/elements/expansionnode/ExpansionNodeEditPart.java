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

package org.modelio.diagram.editor.activity.elements.expansionnode;

import java.beans.PropertyChangeEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.editor.activity.elements.figures.ExpansionNodeFigure;
import org.modelio.diagram.editor.activity.elements.policies.CreateFlowEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint.Border;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint;
import org.modelio.diagram.elements.common.portcontainer.PortContainerFigure;
import org.modelio.diagram.elements.common.portcontainer.PortContainerLayoutHelper;
import org.modelio.diagram.elements.core.figures.IOrientableShaper.Orientation;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;

/**
 * EditPart for an ExpansionNode Node.
 * <p>
 * The model is {@link GmExpansionNodePrimaryNode}.
 * 
 * @author fpoyer
 */
@objid ("2a52291a-55b6-11e2-877f-002564c97630")
public class ExpansionNodeEditPart extends AbstractNodeEditPart {
    @objid ("2a52291e-55b6-11e2-877f-002564c97630")
     Label labelFigure;

    @objid ("2a52291f-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new CreateFlowEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
    }

    @objid ("2a522922-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        ExpansionNodeFigure fig = new ExpansionNodeFigure();
        
        // set style independent properties
        fig.setSize(80, 15); // suppose we are horizontal for the moment
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("2a522927-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmExpansionNodePrimaryNode expansionnodeModel = (GmExpansionNodePrimaryNode) getModel();
        ExpansionNodeFigure expansionNodeFig = (ExpansionNodeFigure) getFigure();
        expansionNodeFig.getParent().setConstraint(expansionNodeFig, expansionnodeModel.getLayoutData());
        
        refreshOrientation();
    }

    @objid ("2a52292c-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("2a522931-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof ExpansionNodeFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
            }
        } else {
            super.refreshFromStyle(aFigure, style);
        }
    }

    @objid ("0de6a928-0447-441a-b89d-5d56a66fe45b")
    @Override
    public SelectionEditPolicy getPreferredDragRolePolicy(String requestType) {
        return new ExpansionNodeDragEditPolicy();
    }

    @objid ("883e8b7e-e37e-4199-979d-7bb7ab380c60")
    protected static GraphicalEditPart getPortContainerEditPart(EditPart expansionNodeEditPart) {
        return (GraphicalEditPart) expansionNodeEditPart.getParent();
    }

    @objid ("4422a5d1-cd92-46f1-8a79-b8c80dd47ac9")
    protected static GraphicalEditPart getExpansionRegionEditPart(EditPart expansionNodeEditPart) {
        return (GraphicalEditPart) expansionNodeEditPart.getParent().getParent();
    }

    @objid ("aed22180-2888-4f33-9ba9-00753a3d273e")
    @Override
    public void activate() {
        super.activate();
        
        // Listen for the parent port container layout data to change "orientation" according to the border.
        GmNodeModel gmModel = getModel();
        gmModel.getParent().addPropertyChangeListener(this);
        
        // initialize orientation to vertical if needed
        Object parentLayoutData = gmModel.getParent().getLayoutData();
        if (gmModel.getLayoutData() == null && parentLayoutData != null) {
            // The node is newly created
            PortConstraint pd = (PortConstraint) parentLayoutData;
            Orientation or = ExpansionNodeFigure.getOrientation(pd.getReferenceBorder());
            if (or == Orientation.NorthSouth || or == Orientation.SouthNorth) {
                // Set the node as vertical
                getFigure().setSize(15, 80);
            }
        }
    }

    @objid ("3be0db8d-6ecf-4a1e-b003-a78141899163")
    @Override
    public void deactivate() {
        GmModel gmParent = getModel().getParent();
        if (gmParent != null) {
            gmParent.removePropertyChangeListener(this);
        }
        
        super.deactivate();
    }

    @objid ("88596540-abbf-42e0-999f-8d3baeeaef07")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GmModel gmParent = getModel().getParent();
        if (evt.getSource() == gmParent) {
            if (evt.getPropertyName().equals(IGmObject.PROPERTY_LAYOUTDATA)) {
                // The port border may have changed
                refreshOrientation();
            }
        } else {
            super.propertyChange(evt);
        }
    }

    @objid ("7ad9f50f-8d1d-4537-933d-752308165bc7")
    protected void refreshOrientation() {
        ExpansionNodeFigure expansionNodeFig = (ExpansionNodeFigure) getFigure();
        // PortConstraint parentPortConst = (PortConstraint) (getExpansionRegionEditPart(this)).getFigure().getLayoutManager().getConstraint(getPortContainerEditPart(this).getFigure());
        PortConstraint parentPortConst = (PortConstraint) ((GmModel) getPortContainerEditPart(this).getModel()).getLayoutData();
        
        if (parentPortConst != null) {
            expansionNodeFig.setReferenceBorder(parentPortConst.getReferenceBorder());
        }
    }

    /**
     * Redefine {@link DefaultNodeResizableEditPolicy} to rotate the expansion node depending on the target border.
     * 
     * @author cmarin
     * @since 3.4
     */
    @objid ("72df758f-8bce-4817-a320-89f577c8fd8d")
    private static class ExpansionNodeDragEditPolicy extends DefaultNodeResizableEditPolicy {
        @objid ("f06e9ad3-71a7-448a-a9ba-8eafc0a9a4be")
        public ExpansionNodeDragEditPolicy() {
            super();
        }

        @objid ("8b7acba4-4078-4684-80a1-878feb3720bd")
        @Override
        protected Command getMoveCommand(ChangeBoundsRequest request) {
            IFigure hostFigure = getHostFigure();
            
            Rectangle requestedBounds = RequestHelper.getTransformedBounds(hostFigure, request);
            
            if (isOrientationChange(requestedBounds)) {
                // translate to absolute to compute a rotation request
                hostFigure.translateToAbsolute(requestedBounds);
            
                // Rotate bounds 90° around its center
                Rectangle rotated = requestedBounds.getCopy();
                rotateRect(rotated);
            
                ChangeBoundsRequest rotateReq = new ChangeBoundsRequest(REQ_RESIZE);
                rotateReq.setEditParts(request.getEditParts());
                rotateReq.getMoveDelta().setLocation(rotated.x - requestedBounds.x, rotated.y - requestedBounds.y);
                rotateReq.getSizeDelta().setSize(rotated.width - requestedBounds.width, rotated.height - requestedBounds.height);
            
                Command c1 = super.getMoveCommand(request);
                Command c2 = getResizeCommand(rotateReq);
            
                return c1.chain(c2);
            } else {
                return super.getMoveCommand(request);
            }
        }

        @objid ("cbab22b7-a031-4ef0-b1e1-8d3366714a0d")
        protected ChangeBoundsRequest getTransformedChangeBoundsRequest(ChangeBoundsRequest request) {
            IFigure hostFigure = getHostFigure();
            Rectangle requestedBounds = RequestHelper.getTransformedBounds(hostFigure, request);
            
            boolean orientationChanged = isOrientationChange(requestedBounds);
            
            if (orientationChanged) {
                rotateRect(requestedBounds);
            
                ChangeBoundsRequest newReq = RequestHelper.shallowCopy(request);
                RequestHelper.setDeltas(newReq, hostFigure, requestedBounds);
            
                return newReq;
            } else {
                return request;
            }
        }

        @objid ("59d8a2c8-0886-4135-9319-0fc462931ba1")
        @Override
        protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
            super.showChangeBoundsFeedback(getTransformedChangeBoundsRequest(request));
        }

        @objid ("39ad848d-e27d-4dd0-b444-d37c09778bb2")
        private boolean isOrientationChange(Rectangle requestedBounds) {
            // expansion region port container figure:
            PortContainerFigure parentPortContainerFigure = (PortContainerFigure) getExpansionRegionEditPart(getHost()).getFigure();
            // expansion node port container figure
            PortContainerFigure portContainerFigure = (PortContainerFigure) getPortContainerEditPart(getHost()).getFigure();
            
            PortContainerLayoutHelper h = new PortContainerLayoutHelper(parentPortContainerFigure);
            PortConstraint curConstraint = (PortConstraint) parentPortContainerFigure.getLayoutManager().getConstraint(portContainerFigure);
            Border curBorder = curConstraint != null ? curConstraint.getReferenceBorder() : h.determineReferenceBorderFromBounds(portContainerFigure.getBounds());
            Border newBorder = h.determineReferenceBorderFromBounds(requestedBounds);
            
            Orientation curOrientation = ExpansionNodeFigure.getOrientation(curBorder);
            Orientation newOrientation = ExpansionNodeFigure.getOrientation(newBorder);
            return curOrientation != newOrientation;
        }

        /**
         * Rotate the rectangle by 90° around its center
         * @param rotated the rectangle to rotate.
         */
        @objid ("2df8df0d-634f-4aea-9799-4cea95a2b445")
        private void rotateRect(Rectangle rotated) {
            Point center = rotated.getCenter();
            rotated.translate(-center.x(), -center.y());
            rotated.transpose();
            rotated.translate(center);
        }

    }

}
