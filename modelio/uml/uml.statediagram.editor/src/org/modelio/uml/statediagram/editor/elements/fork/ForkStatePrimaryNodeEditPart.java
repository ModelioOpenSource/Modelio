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
package org.modelio.uml.statediagram.editor.elements.fork;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transposer;
import org.eclipse.gef.AccessibleAnchorProvider;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.RectangularFigure;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.link.anchors.fixed.AbstractFixedNodeAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.fixed.IFixedConnectionAnchorFactory;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.AnchorsFeedbackEditPolicy;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.uml.statediagram.editor.elements.ForkJoinOrientation;
import org.modelio.uml.statediagram.editor.elements.common.ForkJoinAnchorProvider;

/**
 * EditPart for a Fork Node.
 * 
 * @author fpoyer
 */
@objid ("f5300f3a-55b6-11e2-877f-002564c97630")
public class ForkStatePrimaryNodeEditPart extends AbstractNodeEditPart {
    @objid ("f6e81f7a-55b6-11e2-877f-002564c97630")
    private ForkJoinOrientation currentOrientation = null;

    @objid ("6b46349f-c8f7-46d3-952f-eecea43b0862")
    private ForkJoinAnchorProvider nodeAnchorProvider;

    @objid ("f5300f3f-55b6-11e2-877f-002564c97630")
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

    @objid ("f5300f44-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        this.installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        installEditPolicy(AnchorsFeedbackEditPolicy.class, new AnchorsFeedbackEditPolicy(this.nodeAnchorProvider));
        
    }

    @objid ("f5300f47-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmForkStatePrimaryNode forkModel = (GmForkStatePrimaryNode) this.getModel();
        this.getFigure().getParent().setConstraint(this.getFigure(), forkModel.getLayoutData());
        
    }

    @objid ("f5300f4a-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("f5300f4f-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof RectangularFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
        
                final GmForkStatePrimaryNode model = (GmForkStatePrimaryNode) this.getModel();
                final ForkJoinOrientation orientation = (ForkJoinOrientation) model.getDisplayedStyle().getProperty(GmForkStateStructuredStyleKeys.ORIENTATION);
                if (this.currentOrientation == null) {
                    this.currentOrientation = orientation;
                } else if (orientation != null && this.currentOrientation != orientation) {
                    updateOrientation(aFigure, orientation);
                }
            }
        }
        
    }

    @objid ("f5300f56-55b6-11e2-877f-002564c97630")
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
        
            this.currentOrientation = orientation;
        
            // rotate the fork join node according to the orientation
            doRotationFigure(aFigure);
        
            this.nodeAnchorProvider.refresh(this.figure);
        }
        
    }

    @objid ("f5300f5a-55b6-11e2-877f-002564c97630")
    private void doRotationFigure(IFigure aFigure) {
        Rectangle oldBounds = aFigure.getBounds().getCopy();
        Transposer transposer = new Transposer();
        transposer.enable();
        Rectangle newBounds = transposer.t(oldBounds);
        int w = newBounds.width - oldBounds.width;
        int h = newBounds.height - oldBounds.height;
        
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

    @objid ("066b119c-31af-44e1-bd53-f06559d652e0")
    @Override
    protected ForkJoinAnchorProvider getNodeAnchorProvider() {
        if (this.nodeAnchorProvider == null) {
            this.nodeAnchorProvider = createAnchorProvider(this.figure);
        }
        return this.nodeAnchorProvider;
    }

    @objid ("faabe72a-04ad-419d-a609-8af3623f0cbb")
    @Override
    protected void setFigure(IFigure figure) {
        super.setFigure(figure);
        getNodeAnchorProvider().onFigureMoved(figure);
        
    }

    /**
     * Create the {@link AbstractFixedNodeAnchorProvider} for this edit part.
     * @param figure the edit part figure.
     * @return the created anchor provider.
     */
    @objid ("53347b4f-4d0c-4a64-8b00-fbdeb6a84c6a")
    protected ForkJoinAnchorProvider createAnchorProvider(IFigure figure) {
        return new ForkJoinAnchorProvider(() -> this.currentOrientation);
    }

    /**
     * Adapt to {@link AccessibleAnchorProvider} or {@link IFixedConnectionAnchorFactory}.
     */
    @objid ("564550ae-2933-4a08-9790-c08670213253")
    @Override
    public Object getAdapter(Class adapter) {
        if (AccessibleAnchorProvider.class.isAssignableFrom(adapter)) {
            return this.nodeAnchorProvider.getAccessibleAnchorProvider(getFigure());
        } else if (IFixedConnectionAnchorFactory.class.isAssignableFrom(adapter)) {
            return this.nodeAnchorProvider;
        }
        return super.getAdapter(adapter);
    }

}
