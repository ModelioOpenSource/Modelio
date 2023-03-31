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
import org.modelio.diagram.elements.core.figures.routers.AutoOrthogonalRouter;
import org.modelio.diagram.elements.core.figures.routers.ConnectionState;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.link.MPrecisionPoint;
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
            fixWithRouter(true);
        }
        
        this.getView().getState().applyTo(this.getView().getConnection());
        
    }

    /**
     * Dump a report and a stack trace if the connection path is not orthogonal.
     * @param c a Connection
     * @return true always true. Made to be put in "assert" statement.
     */
    @objid ("bd7b929a-4342-4e1c-9e75-37d6c0d1632d")
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
     * @param cleanManualPoints ignored, it a disabled experiment and manual points are still cleaned.
     * To be deleted if no value is confirmed.
     */
    @objid ("e85b15a0-24b5-49b2-9701-d45b46701e88")
    public void fixWithRouter(boolean cleanManualPoints) {
        // Ignore the parameter, it seems it is best to always clean fixed points.
        cleanManualPoints = true;
        
        // refresh anchor bounds in case of they were changed
        this.view.refreshAnchorBounds();
        
        PolylineConnection dummy = new PolylineConnection();
        dummy.setParent(getView().getConnection().getParent());
        
        ConnectionState connState = getView().getState();
        connState.applyTo(dummy);
        
        List<MPoint> initConstraint = connState.getMPoints();
        List<MPoint> newConstraint;
        
        if ( !getView().isValidPath()) {
            AutoOrthogonalRouter router = new AutoOrthogonalRouter()
                    .setCleanupManualPoints(cleanManualPoints)
                    .setRerouteWrongSectionFromPreviousManualPoint(true);
            newConstraint = router.computeMPointRoute(dummy, initConstraint);
            AutoOrthogonalRouter.routeToConstraint(newConstraint);
        } else {
            newConstraint = AutoOrthogonalRouter.getCleanedConstraint(dummy, connState, cleanManualPoints);
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
    @objid ("aff83e3b-0657-4312-8407-da5596cab473")
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
    @objid ("b42960fc-f339-461b-ba5a-6dacda48fe04")
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
    @objid ("be23c766-8fd5-4d1b-a23d-c4ec60894dc6")
    private IFigure createDummyFigure(IFigure nodeFig) {
        if (nodeFig instanceof Connection) {
            // Create a PolylineConnection with same points.
            // Don't initialize anchors to avoid the router being run.
            Connection orig = (Connection) nodeFig;
            PolylineConnection dummy = new PolylineConnection();
            dummy.setParent(orig.getParent());
            dummy.setPoints(orig.getPoints().getCopy());
        
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
    @objid ("af218f76-e81b-4926-94ad-b03fed136faf")
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
    @objid ("c21125da-0221-4668-a4ca-3c720c42abf1")
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
    @objid ("ec88be48-611b-4db3-9fac-4b9f314c5a49")
    public AnchorReqBuilder requestSourceAnchor() {
        NodeEditPart targetEditPart = (NodeEditPart) this.editPart.getSource();
        return AnchorReqBuilder.SHARED.initSource(this.editPart, targetEditPart);
    }

    /**
     * Helper to build {@link ReconnectRequest} to ask for connection anchors.
     * @author cma
     */
    @objid ("486e18fb-2ec9-45fc-87c9-08f725944f52")
    public static class AnchorReqBuilder {
        /**
         * A reusable shared instance for short usages.
         */
        @objid ("9882667c-405c-4273-a8f8-c39d7d87c84f")
        public static final AnchorReqBuilder SHARED = new AnchorReqBuilder();

        @objid ("7d772a2f-c173-45f9-889f-4fa8250ddc2d")
        private ReconnectRequest reconnectRequest;

        /**
         * Set a reference point to compute an anchor.
         * @param absLoc the desired anchor location in absolute coordinates
         * @return this instance to chain calls.
         */
        @objid ("7d4efdb5-9c00-4301-9af2-58d8319fc42c")
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
        @objid ("2cf198f5-f669-4a11-a8fe-afc372549362")
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
        @objid ("49132668-9832-40fc-8c4b-b470071eeb5d")
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
        @objid ("80be40dd-248f-488d-a5d6-5808f7929a1e")
        public AnchorReqBuilder withSameFaceAs(ConnectionAnchor sameFace) {
            this.reconnectRequest.getExtendedData().put(CreateLinkConstants.PROP_RECONNECT_ON_SAME_FACE, sameFace);
            return this;
        }

        /**
         * Request a discrete (sliding) anchor, contrary to fixed anchors whose position depend only from the node.
         * @param discrete true to request a discrete anchor
         * @return this instance.
         */
        @objid ("5567081e-4d73-4be8-9ecb-2ec95a360121")
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
        @objid ("769b65ec-f42b-4b80-81a1-f7d86dd1dd31")
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
        @objid ("8a4cfd87-9b44-4565-bd5a-e839428f217a")
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
        @objid ("cd41934d-f791-4840-a234-03796516cca6")
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
