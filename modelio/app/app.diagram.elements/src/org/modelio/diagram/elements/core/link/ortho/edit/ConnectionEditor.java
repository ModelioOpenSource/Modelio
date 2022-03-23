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
package org.modelio.diagram.elements.core.link.ortho.edit;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.anchors.PointAnchor;
import org.modelio.diagram.elements.core.figures.routers.AutoOrthogonalRouter;
import org.modelio.diagram.elements.core.figures.routers.ConnectionState;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.link.MPrecisionPoint;
import org.modelio.diagram.elements.core.link.path.BendPointUtils;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * Helper to edit a {@link ConnectionState} wrapped in a {@link ConnectionView} for a {@link ConnectionEditPart}.
 * <p>
 * All input and output coordinates are absolute.
 * 
 * @since 5.0.2
 */
@objid ("6fce6d88-3c0c-4f94-ac15-5f7b7d2911d8")
public class ConnectionEditor {
    /**
     * The edited connection edit part.
     */
    @objid ("cea12f68-fff0-499f-a66f-a772aee5d0f2")
    private ConnectionEditPart editPart;

    /**
     * A temporary point
     */
    @objid ("a084402e-c9d4-4805-b0a0-1f8de0854486")
    private static final MPrecisionPoint TMP = new MPrecisionPoint();

    /**
     * The edited {@link ConnectionView}
     */
    @objid ("2022b5d9-6187-4b25-a5ba-d470e7f98f46")
    private final ConnectionView view = new ConnectionView();

    /**
     * Applies the connection state to the edited connection.
     */
    @objid ("6a5307a6-44db-4714-97db-e0e8729293fc")
    public void applyStateToConnection() {
        // refresh anchor bounds
        this.view.refreshAnchorBounds();
        
        // run this only in debug
        assert assertValidPath();
        
        // Last resort check
        if (!getView().isValidPath()) {
            fixWithRouter();
        }
        
        this.getView().getState().applyTo(this.getView().getConnection());
        
    }

    /**
     * Dump a report and a stack trace if the connection path is not orthogonal.
     * @param c a Connection
     * @return true always true. Made to be put in "assert" statement.
     */
    @objid ("477c08bb-319b-4371-a3d7-66fdc0bd30f6")
    public static boolean assertValidPath(Connection c) {
        String r = ConnectionView.Validator.validate(new ConnectionView().init(c));
        if (r != null) {
            DiagramElements.LOG.warning(new IllegalStateException(r));
        }
        return true;
    }

    /**
     * Make the connection orthogonal if it is not and remove useless points.
     * <p>
     * It calls the full router if anything is wrong.
     */
    @objid ("e85b15a0-24b5-49b2-9701-d45b46701e88")
    public void fixWithRouter() {
        // refresh anchor bounds in case of they were changed
        this.view.refreshAnchorBounds();
        
        PolylineConnection dummy = new PolylineConnection();
        dummy.setParent(getView().getConnection().getParent());
        
        ConnectionState connState = getView().getState();
        connState.applyTo(dummy);
        
        List<MPoint> initConstraint = connState.getMPoints();
        List<MPoint> newConstraint;
        
        if (!getView().isValidPath()) {
            AutoOrthogonalRouter router = new AutoOrthogonalRouter()
                    .setCleanupManualPoints(true)
                    .setIgnoreAutomaticPoints(false);
            newConstraint = router.computeMPointRoute(dummy, initConstraint);
            AutoOrthogonalRouter.routeToConstraint(newConstraint);
        } else {
            newConstraint = AutoOrthogonalRouter.getCleanedConstraint(dummy, connState, true);
        }
        
        if (!newConstraint.equals(initConstraint)) {
            connState.setConstraint(newConstraint);
        }
        
        dummy.setParent(null);
        dummy.removeNotify();
        
    }

    /**
     * @return the edited connection edit part
     */
    @objid ("47eecbfd-16e8-4b68-89ab-da3911083c99")
    public ConnectionEditPart getConnectionEditPart() {
        return this.editPart;
    }

    /**
     * @return the edited connection state view
     */
    @objid ("26b93458-8aee-4191-b57e-59d9d606ff07")
    public ConnectionView getView() {
        return this.view;
    }

    /**
     * Initialize this instance from an existing connection edit part.
     * @param anEditPart the connection edit part to edit
     * @return this instance
     */
    @objid ("8b8bcf9b-4280-40b0-8e68-70f612014f7e")
    public ConnectionEditor init(ConnectionEditPart anEditPart) {
        this.editPart = anEditPart;
        this.view.init((Connection) anEditPart.getFigure());
        return this;
    }

    /**
     * Initialize this instance from an existing connection edit part and an initial state.
     * @param anEditPart the connection edit part to edit
     * @param state the initial state
     * @return this instance
     */
    @objid ("f404ecea-597c-491f-a5b8-928f678275d2")
    public ConnectionEditor init(ConnectionEditPart anEditPart, ConnectionState state) {
        this.editPart = anEditPart;
        this.view.init((Connection) anEditPart.getFigure(), state);
        return this;
    }

    /**
     * Compute a new source anchor
     * @param reqLoc a location in absolute coordinates
     * @param sameFace if true request an anchor on the same face as the initial anchor
     * @return a new target anchor
     */
    @objid ("357b3a44-11da-419d-8a55-1a848737e08c")
    public ConnectionAnchor requestSourceAnchor(Point reqLoc, boolean sameFace) {
        ConnectionAnchor sameFaceAsAnchor = sameFace ? getView().getState().getSourceAnchor() : null;
        return requestSourceAnchor()
                .withLocation(reqLoc)
                .withSameFaceAs(sameFaceAsAnchor)
                .requestAnchor();
        
    }

    /**
     * Compute a new target anchor
     * @param reqLoc a location in absolute coordinates
     * @param sameFace if true request an anchor on the same face as the initial anchor
     * @return a new target anchor
     */
    @objid ("e5df2ae3-ecb6-4616-9fd3-a60e0f74e6a2")
    public ConnectionAnchor requestTargetAnchor(Point reqLoc, boolean sameFace) {
        ConnectionAnchor sameFaceAsAnchor = sameFace ? getView().getState().getTargetAnchor() : null;
        return requestTargetAnchor()
                .withLocation(reqLoc)
                .withSameFaceAs(sameFaceAsAnchor)
                .requestAnchor();
        
    }

    /**
     * Set the position of the point at the given index from the anchors and routing constraint.
     * @param index the index of the point to modify in the points list.<br>
     * <li><b>0</b> is the source anchor,
     * <li><b>1</b> is the first bend point and
     * <li><b>c.size()</b> is the last bend point.
     * <li><b>c.size()+1</b> is the target anchor.
     * @param absPoint a point in absolute coordinates.
     * @param sameFace if index matches anchor, request an anchor on same face ?
     * @param manual if non null and index matches an anchor, calls {@link MPoint#setFixed(boolean)} with 'manual'
     */
    @objid ("ca436640-92a0-4c82-8006-2587a208d930")
    public void setPoint(int index, Point absPoint, boolean sameFace, Boolean manual) {
        int targetAnchorIndex = getView().getTargetAnchorIndex();
        if (index < 0 || index > targetAnchorIndex) {
            throw new IllegalArgumentException(String.format("invalid index: %d not in [0..%d]", index, targetAnchorIndex));
        }
        
        if (index == 0) {
            // Update source anchor.
            ConnectionAnchor newConnectionAnchor = requestSourceAnchor(absPoint, sameFace);
            getView().getState().setSourceAnchor(newConnectionAnchor);
        } else if (index == targetAnchorIndex) {
            // Update target anchor.
            ConnectionAnchor newAnchor = requestTargetAnchor(absPoint, sameFace);
            getView().getState().setTargetAnchor(newAnchor);
        } else {
            MPrecisionPoint p = getView().getPoint(TMP, index, true);
            p.setLocation(absPoint);
            if (manual != null) {
                p.setFixed(manual.booleanValue());
            }
            getView().setPoint(index, p);
        }
        
    }

    @objid ("0eb9f1c7-8045-42cb-90b2-aa2f4ea3a797")
    private boolean assertValidPath() {
        String r = ConnectionView.Validator.validate(getView());
        if (r != null) {
            DiagramElements.LOG.warning(new IllegalStateException(r));
        }
        return true;
    }

    /**
     * Create a dummy figure with same bounds than the given figure, parented to the connection layer.
     * <p>
     * The returned figure is independent from the original one. The figure is NOT added to its parent and is not expected to be displayed.
     * @param nodeFig a figure to copy
     * @return a dummy figure with same bounds as the original.
     */
    @objid ("4a73f0b4-2f95-448d-8a37-c7f9a593183f")
    private IFigure createDummyFigure(IFigure nodeFig) {
        if (nodeFig instanceof Connection) {
            // Create a PolylineConnection with same points
            Connection orig = (Connection) nodeFig;
            PolylineConnection dummy = new PolylineConnection();
            dummy.setParent(orig.getParent());
            dummy.setPoints(orig.getPoints().getCopy());
        
            // don't copy anchor so that router is never run
            if (false) {
                // Make snapshot of the anchor
                Point p = orig.getPoints().getPoint(TMP, 0);
                dummy.setSourceAnchor(new PointAnchor(dummy, p));
        
                // Make snapshot of the anchor
                p = orig.getPoints().getPoint(TMP, orig.getPoints().size()-1);
                dummy.setTargetAnchor(new PointAnchor(dummy, p));
        
                // If anchors are needed you need to copy the constraint too
                dummy.setRoutingConstraint(BendPointUtils.copyConstraint((List<MPoint>) orig.getRoutingConstraint()));
                dummy.setConnectionRouter(orig.getConnectionRouter());
            }
        
            return dummy;
        } else {
            Figure dummyNode = new Figure();
            dummyNode.setParent(getView().getConnection().getParent());
        
            // Directly hack the figure bounds to avoid firing listeners & validation
            Rectangle dummyBounds = dummyNode.getBounds();
            dummyBounds.setBounds(nodeFig.getBounds());
            nodeFig.translateToAbsolute(dummyBounds);
            dummyNode.translateToRelative(dummyBounds);
            return dummyNode;
        }
        
    }

    /**
     * Create a "frozen" copy of the edited connection state.
     * <p>
     * Makes a copy of the anchors by requesting the node edit parts an anchor on a dummy node figure created for this occasion. with this the frozen anchor location will not move with the original node figure.
     * <p>
     * The returned state must be used only to make a safe backup of the connection state, it must NOT be applied to a live {@link Connection} figure because the anchors are attached to dummy figures that are not owned by their parents. You also risk
     * leaking the figure or ancestor listeners.
     * @return a "frozen" copy of the edited connection state.
     */
    @objid ("861cda71-a633-40db-b473-ce2c40c45d6e")
    public ConnectionState createFrozenState() {
        ConnectionView aview = getView();
        ConnectionState frozen = new ConnectionState();
        frozen.init(aview.getState());
        
        IFigure srcFig = frozen.getSourceAnchor().getOwner();
        IFigure srcFreeze = createDummyFigure(srcFig);
        MPrecisionPoint sourceLocation = aview.getSourceLocation(TMP, true);
        frozen.setSourceAnchor(requestSourceAnchor()
                .withLocation(sourceLocation)
                .withSameSliding(frozen.getSourceAnchor())
                .withSameFaceAs(frozen.getSourceAnchor())
                .withNodeFigure(srcFreeze)
                .requestAnchor());
        
        IFigure targetFig = frozen.getTargetAnchor().getOwner();
        IFigure targetFreeze = createDummyFigure(targetFig);
        frozen.setTargetAnchor(requestTargetAnchor()
                .withLocation(aview.getTargetLocation(TMP, true))
                .withSameSliding(frozen.getTargetAnchor())
                .withSameFaceAs(frozen.getTargetAnchor())
                .withNodeFigure(targetFreeze)
                .requestAnchor());
        return frozen;
    }

    /**
     * Get a request builder to request a connection anchor.
     * <p>
     * The returned builder is a shared instance that must be used immediately then discarded.
     * @return a connection anchor request builder.
     */
    @objid ("970df594-13f7-40d7-a241-f12fc84222cd")
    public AnchorReqBuilder requestTargetAnchor() {
        NodeEditPart targetEditPart = (NodeEditPart) this.editPart.getTarget();
        return AnchorReqBuilder.SHARED.initTarget(this.editPart, targetEditPart);
    }

    /**
     * Get a request builder to request a connection anchor
     * <p>
     * The returned builder is a shared instance that must be used immediately then discarded.
     * @return a connection anchor request builder.
     */
    @objid ("7df64b62-7fce-4caf-b004-d4ebdc9de625")
    public AnchorReqBuilder requestSourceAnchor() {
        NodeEditPart targetEditPart = (NodeEditPart) this.editPart.getSource();
        return AnchorReqBuilder.SHARED.initSource(this.editPart, targetEditPart);
    }

    /**
     * Helper to build {@link ReconnectRequest} to ask for connection anchors.
     * @author cma
     */
    @objid ("1ef1701c-97ee-48ee-818f-90b3acbe1490")
    public static class AnchorReqBuilder {
        /**
         * A reusable shared instance for short usages.
         */
        @objid ("88a00086-479d-4fac-8483-c028bfeecb4d")
        public static final AnchorReqBuilder SHARED = new AnchorReqBuilder();

        @objid ("4e7101d2-1e52-49c6-8053-705435523ed8")
        private ReconnectRequest reconnectRequest;

        /**
         * Set a reference point to compute an anchor.
         * @param absLoc the desired anchor location in absolute coordinates
         * @return this instance to chain calls.
         */
        @objid ("f80428ab-c728-4dbf-b9c7-26555cdde030")
        public AnchorReqBuilder withLocation(Point absLoc) {
            this.reconnectRequest.setLocation(absLoc);
            return this;
        }

        /**
         * Initialize the builder for a source anchor request.
         * @param connEp the connection edit part
         * @param nodeEditPart the new source edit part
         * @return this instance
         */
        @objid ("6d68f3d8-626f-4af1-8ccb-bada8538d308")
        public AnchorReqBuilder initSource(ConnectionEditPart connEp, NodeEditPart nodeEditPart) {
            this.reconnectRequest = new ReconnectRequest(RequestConstants.REQ_RECONNECT_SOURCE);
            this.reconnectRequest.setTargetEditPart(nodeEditPart);
            this.reconnectRequest.setConnectionEditPart(connEp);
            return this;
        }

        /**
         * Initialize the builder for a target anchor request.
         * @param connEp the connection edit part
         * @param nodeEditPart the new source edit part
         * @return this instance
         */
        @objid ("493780b9-b19a-4a21-99dd-aa8817093ff8")
        public AnchorReqBuilder initTarget(ConnectionEditPart connEp, NodeEditPart nodeEditPart) {
            this.reconnectRequest = new ReconnectRequest(RequestConstants.REQ_RECONNECT_TARGET);
            this.reconnectRequest.setTargetEditPart(nodeEditPart);
            this.reconnectRequest.setConnectionEditPart(connEp);
            return this;
        }

        /**
         * Request the anchor to be on the same node face than the given anchor.
         * <p>
         * This is a hint that may be ignored by anchor providers not supporting this feature.
         * @param sameFace a connection anchor
         * @return this instance
         */
        @objid ("16ce2c99-ed71-47d2-bce9-96980894b9f2")
        public AnchorReqBuilder withSameFaceAs(ConnectionAnchor sameFace) {
            this.reconnectRequest.getExtendedData().put(CreateLinkConstants.PROP_RECONNECT_ON_SAME_FACE, sameFace);
            return this;
        }

        /**
         * Request a discrete (sliding) anchor, contrary to fixed anchors whose position depend only from the node.
         * @param discrete true to request a discrete anchor
         * @return this instance.
         */
        @objid ("3a946b1e-6cc4-4a80-bab4-636aba2fcb68")
        public AnchorReqBuilder withSliding(boolean discrete) {
            this.reconnectRequest.getExtendedData().put(CreateLinkConstants.PROP_NEED_SLIDABLE_ANCHOR, discrete);
            return this;
        }

        /**
         * Request a discrete (sliding) anchor if the passed one is discrete.
         * <p>
         * @see #withSliding(boolean)
         * @param as true to request a discrete anchor
         * @return this instance.
         */
        @objid ("5d578039-404f-469c-a24f-b7dc298ac0b9")
        public AnchorReqBuilder withSameSliding(ConnectionAnchor as) {
            if (as != null && ! (as instanceof FixedAnchor))
                withSliding(true);
            return this;
        }

        /**
         * Create the anchor on the given figure instead of the node edit part figure.
         * <p>
         * Used to create anchors on feedback figures.
         * @param nodeFigure a node figure
         * @return this instance
         */
        @objid ("319c0886-becd-48be-b136-4fc3183f6f4e")
        public AnchorReqBuilder withNodeFigure(IFigure nodeFigure) {
            if (nodeFigure != null) {
                this.reconnectRequest.getExtendedData().put(CreateLinkConstants.PROP_RECONNECT_ON_FIGURE, nodeFigure);
            } else {
                this.reconnectRequest.getExtendedData().remove(CreateLinkConstants.PROP_RECONNECT_ON_FIGURE);
            }
            return this;
        }

        /**
         * @return request a {@link ConnectionAnchor} for the wrapped request.
         */
        @objid ("fa0fe438-bf4d-46ac-8d36-f449d7962f99")
        public ConnectionAnchor requestAnchor() {
            NodeEditPart nodeEditPart = (NodeEditPart) this.reconnectRequest.getTarget();
            if (this.reconnectRequest.getType() == RequestConstants.REQ_RECONNECT_TARGET) {
                return nodeEditPart.getTargetConnectionAnchor(this.reconnectRequest);
            } else {
                return nodeEditPart.getSourceConnectionAnchor(this.reconnectRequest);
            }
            
        }

    }

}
