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
package org.modelio.diagram.api.dg;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.api.modelio.diagram.ILinkPoint;
import org.modelio.api.modelio.diagram.ILinkRouteBuilder;
import org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints;
import org.modelio.api.modelio.diagram.ILinkRouteBuilder.Target;
import org.modelio.api.modelio.diagram.InvalidDestinationPointException;
import org.modelio.api.modelio.diagram.InvalidPointsPathException;
import org.modelio.api.modelio.diagram.InvalidSourcePointException;
import org.modelio.api.modelio.diagram.LinkAnchorFace;
import org.modelio.api.modelio.diagram.LinkPointKind;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.routers.ConnectionState;
import org.modelio.diagram.elements.core.link.ConnectionRoutingServices;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.IAnchorModelProvider;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.link.ortho.edit.ConnectionEditor;
import org.modelio.diagram.elements.core.link.ortho.edit.ConnectionView;
import org.modelio.diagram.elements.core.link.path.ConnectionPolicyUtils;
import org.modelio.diagram.elements.core.link.path.IConnectionHelper;
import org.modelio.diagram.elements.core.link.path.RawPathData;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmPath;

/**
 * Implementation of {@link ILinkRouteBuilder} and its inner interfaces.
 * 
 * @since 5.1.0
 */
@objid ("61014094-4821-42ea-a208-2deb5594987b")
public class LinkRouteBuilder implements ILinkRouteBuilder, org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints, org.modelio.api.modelio.diagram.ILinkRouteBuilder.Target {
    @objid ("d430a54a-be87-41c2-8abb-93c06400bafc")
    private static final MPoint TMP = new MPoint();

    @objid ("04ee86ac-788f-4bc5-abfb-915c9bf0e941")
    private final ConnectionEditor editor;

    /**
     * @param ep the connection edit part
     */
    @objid ("53bd04fb-03ca-4641-9d3f-a1c67071d582")
    public  LinkRouteBuilder(ConnectionEditPart ep) {
        this.editor = new ConnectionEditor().init(ep);
        // reset the constraint
        this.editor.getView().getState().setConstraint(new ArrayList<>());
        
    }

    @objid ("8341fea4-cb33-4d4f-83d7-527aa9b2cfda")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints addBendPoint(Point loc) {
        MPoint mp = new MPoint(loc, false);
        return addMPoint(mp);
    }

    @objid ("9706fbdb-d3b3-4a35-b40a-8974d0a18837")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints addFixedPoint(Point loc) {
        MPoint mp = new MPoint(loc, true);
        return addMPoint(mp);
    }

    @objid ("d81a905b-9f98-4344-910b-6c7669de7b9f")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints addHorizontalSegment(int nextBendPointX, boolean fixed) {
        ConnectionView view = this.editor.getView();
        int n = view.getTargetAnchorIndex();
        MPoint p1 = view.getPoint(TMP, n - 1, true);
        addMPoint(p1.setX(nextBendPointX).setFixed(fixed));
        return this;
    }

    @objid ("73a9f732-45a8-410b-b785-922aa41b922f")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints finishHorizontalThenVertical(boolean fixed) {
        ConnectionView view = this.editor.getView();
        int n = view.getTargetAnchorIndex();
        MPoint p1 = view.getPoint(TMP, n - 1, true);
        MPoint p2 = view.getPoint(new MPoint(), n, true);
        
        if (p1.y() != p2.y()) {
            addMPoint(TMP.setX(p2.x()).setY(p1.y()).setFixed(fixed));
        }
        return this;
    }

    @objid ("a61e871b-ab1d-40dc-ad33-d2235f69bb81")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints addVerticalSegment(int nextBendPointY, boolean fixed) {
        ConnectionView view = this.editor.getView();
        int n = view.getTargetAnchorIndex();
        MPoint p1 = view.getPoint(TMP, n - 1, true);
        addMPoint(p1.setY(nextBendPointY).setFixed(fixed));
        return this;
    }

    @objid ("4736b31a-1b7d-4019-9e1d-3511788a38d4")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints finishVerticalThenHorizontal(boolean fixed) {
        ConnectionView view = this.editor.getView();
        int n = view.getTargetAnchorIndex();
        MPoint p1 = view.getPoint(TMP, n - 1, true);
        MPoint p2 = view.getPoint(new MPoint(), n, true);
        
        if (p1.x() != p2.x()) {
            addMPoint(TMP.setX(p1.x()).setY(p2.y()).setFixed(fixed));
        }
        return this;
    }

    /**
     * Applies the route to the edited Connection edit part.
     * @throws InvalidSourcePointException if the source anchor is missing
     * @throws InvalidPointsPathException if the target anchor is missing
     * @throws InvalidDestinationPointException ?
     */
    @objid ("3276ac7c-3db4-4997-aa03-601dee3dae39")
    @Override
    public void apply() throws InvalidSourcePointException, InvalidPointsPathException, InvalidDestinationPointException {
        ConnectionView view = this.editor.getView();
        if (view.getState().getSourceAnchor() == null) {
            throw new InvalidSourcePointException("No source anchor");
        }
        if (view.getState().getTargetAnchor() == null) {
            throw new InvalidDestinationPointException("No target  anchor");
        }
        
        final ConnectionEditPart editPart = this.editor.getConnectionEditPart();
        final IGmLink model = (IGmLink) editPart.getModel();
        final IGmPath newPath = new GmPath(model.getPath());
        
        final RawPathData rawPath = createRawPathData();
        
        ConnectionAnchor sourceAnchor = view.getState().getSourceAnchor();
        ConnectionAnchor targetAnchor = view.getState().getTargetAnchor();
        
        // Change the path
        newPath.setSourceAnchor(((IAnchorModelProvider) editPart.getSource()).createAnchorModel(sourceAnchor));
        newPath.setTargetAnchor(((IAnchorModelProvider) editPart.getTarget()).createAnchorModel(targetAnchor));
        
        final Connection cnx = (Connection) editPart.getFigure();
        cnx.setSourceAnchor(sourceAnchor);
        cnx.setTargetAnchor(targetAnchor);
        
        ConnectionRoutingServices routingServices = ConnectionPolicyUtils.getRoutingServices(editPart);
        IConnectionHelper helper = routingServices.getConnectionHelperFactory().createFromRawData(rawPath, cnx);
        Object modelPathData = helper.getModelPathData();
        
        newPath.setPathData(modelPathData);
        model.setLayoutData(newPath);
        
        editPart.getFigure().getUpdateManager().performValidation();
        
    }

    /**
     * Get a snapshot of the points.
     * <p>
     * Modifying the returned list and elements will have no effect on this builder.
     * @return the current points.
     */
    @objid ("76cb615b-4eb3-4ac9-b3a8-d4308b2c6f71")
    @Override
    public List<ILinkPoint> getAllPoints() {
        int cardPoints = this.editor.getView().cardPoints();
        List<ILinkPoint> ret = new ArrayList<>(cardPoints);
        ret.add(createSourceAnchorLinkPoint());
        
        for (int i = 1; i < cardPoints - 1; i++) {
            ret.add(createBendPointData(i));
        }
        
        ret.add(createTargetAnchorLinkPoint());
        return ret;
    }

    @objid ("7ccee7b4-6774-49f1-b8a9-4e42451e20ed")
    private org.modelio.diagram.api.dg.LinkRoute.LinkPointData createBendPointData(int i) {
        this.editor.getView().getPoint(TMP, i, true);
        LinkPointKind kind = TMP.isFixed() ? LinkPointKind.BENDPOINT_FIXED : LinkPointKind.BENDPOINT;
        LinkRoute.LinkPointData linkPoint = new LinkRoute.LinkPointData(kind, TMP);
        return linkPoint;
    }

    /**
     * Get a snapshot of the current bend points.
     * <p>
     * Modifying the returned list and elements will have no effect on this builder.
     * @return the current bend points.
     */
    @objid ("5b6666a8-3994-4c83-a9db-c3bff51cff30")
    @Deprecated
    public List<ILinkPoint> getBendPoints() {
        final int cardPoints = this.editor.getView().cardPoints();
        List<ILinkPoint> ret = new ArrayList<>(cardPoints - 2);
        
        for (int i = 1; i < cardPoints - 1; i++) {
            ret.add(createBendPointData(i));
        }
        return ret;
    }

    @objid ("79cfa423-450a-4ef7-85df-eb192cc4e2d6")
    private ILinkPoint createSourceAnchorLinkPoint() {
        ConnectionView view = this.editor.getView();
        LinkPointKind kind = getKind(view.getState().getSourceAnchor());
        return new LinkRoute.LinkPointData(kind, view.getSourceLocation(TMP, true));
    }

    @objid ("88c3037d-d3d9-485d-941e-a672c658a70d")
    private static LinkPointKind getKind(ConnectionAnchor a) {
        if (a instanceof FixedAnchor) {
            return LinkPointKind.ANCHOR_DISCRETE;
        } else {
            return LinkPointKind.ANCHOR_SLIDING;
        }
        
    }

    @objid ("04909db7-53cc-4412-994a-2e3efd46205c")
    private ILinkPoint createTargetAnchorLinkPoint() {
        ConnectionView view = this.editor.getView();
        LinkPointKind kind = getKind(view.getState().getSourceAnchor());
        return new LinkRoute.LinkPointData(kind, view.getTargetLocation(TMP, true));
    }

    @objid ("af0a496d-8c40-44a5-8acc-a44c2bf350d2")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.Target setSourceAnchor(Point loc, boolean sliding) {
        ConnectionAnchor a = this.editor.requestSourceAnchor()
                .withLocation(loc)
                .withSliding(sliding)
                .requestAnchor();
        this.editor.getView().getState().setSourceAnchor(a);
        return this;
    }

    @objid ("4c95f305-1162-4701-9354-299fb4c49ef1")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.Target setSourceAnchor(LinkAnchorFace face, boolean sliding) {
        GraphicalEditPart nodeEp = (GraphicalEditPart) this.editor.getConnectionEditPart().getSource();
        ConnectionAnchor a = this.editor.requestSourceAnchor()
                .withLocation(getLocationFromFace(nodeEp, face))
                .withSliding(sliding)
                .requestAnchor();
        this.editor.getView().getState().setSourceAnchor(a);
        return this;
    }

    @objid ("494aba74-f387-49e7-8760-25e10e5a2dd9")
    @Override
    public Target setSourceAnchorFaceTarget(boolean sliding) {
        // Use the target node center as reference point
        GraphicalEditPart nodeEp = (GraphicalEditPart) this.editor.getConnectionEditPart().getTarget();
        Point center = nodeEp.getFigure().getBounds().getCenter();
        nodeEp.getFigure().translateToAbsolute(center);
        
        ConnectionAnchor a = this.editor.requestSourceAnchor()
                .withLocation(center)
                .withSliding(sliding)
                .requestAnchor();
        this.editor.getView().getState().setSourceAnchor(a);
        return this;
    }

    @objid ("bb0858cd-d9eb-4e32-ae3a-0ba660d8905f")
    @Override
    public BendPoints setTargetAnchorFaceSource(boolean sliding) {
        // Use the source node center as reference point
        GraphicalEditPart nodeEp = (GraphicalEditPart) this.editor.getConnectionEditPart().getSource();
        Point center = nodeEp.getFigure().getBounds().getCenter();
        nodeEp.getFigure().translateToAbsolute(center);
        
        ConnectionAnchor a = this.editor.requestTargetAnchor()
                .withLocation(center)
                .withSliding(sliding)
                .requestAnchor();
        this.editor.getView().getState().setTargetAnchor(a);
        return this;
    }

    @objid ("99aba1f6-e340-493a-83f9-fdfa70010a87")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.Target setSourceAnchor(LinkAnchorFace face, int numerator, int denominator, boolean sliding) {
        GraphicalEditPart nodeEp = (GraphicalEditPart) this.editor.getConnectionEditPart().getSource();
        ConnectionAnchor a = this.editor.requestSourceAnchor()
                .withLocation(getLocationFromFace(nodeEp, face, numerator, denominator))
                .withSliding(sliding)
                .requestAnchor();
        this.editor.getView().getState().setSourceAnchor(a);
        return this;
    }

    @objid ("8788337b-8d7b-478c-bd35-0d09473faf48")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints setTargetAnchor(Point loc, boolean sliding) {
        ConnectionAnchor a = this.editor.requestTargetAnchor()
                .withLocation(loc)
                .withSliding(sliding)
                .requestAnchor();
        this.editor.getView().getState().setTargetAnchor(a);
        return this;
    }

    @objid ("fbf5d484-1fc3-4acf-b2f9-ad7a714e1d6e")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints setTargetAnchor(LinkAnchorFace face, boolean sliding) {
        GraphicalEditPart nodeEp = (GraphicalEditPart) this.editor.getConnectionEditPart().getTarget();
        ConnectionAnchor a = this.editor.requestTargetAnchor()
                .withLocation(getLocationFromFace(nodeEp, face))
                .withSliding(sliding)
                .requestAnchor();
        this.editor.getView().getState().setTargetAnchor(a);
        return this;
    }

    @objid ("78b2cae3-16ac-45f9-82d9-ec5e66b56f21")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints setTargetAnchor(LinkAnchorFace face, int numerator, int denominator, boolean sliding) {
        GraphicalEditPart nodeEp = (GraphicalEditPart) this.editor.getConnectionEditPart().getTarget();
        ConnectionAnchor a = this.editor.requestTargetAnchor()
                .withLocation(getLocationFromFace(nodeEp, face, numerator, denominator))
                .withSliding(sliding)
                .requestAnchor();
        this.editor.getView().getState().setTargetAnchor(a);
        return this;
    }

    /**
     * Insert a copy of the point into the connection's bendpoints.
     * @param mp a MPoint in absolute coordinates
     * @return this instance
     */
    @objid ("1fa85cbd-9f46-4738-9544-81d69b36b56e")
    protected org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints addMPoint(MPoint mp) {
        MPoint newMP = mp.getCopy();
        ConnectionView view = this.editor.getView();
        view.getConnection().translateToRelative(newMP);
        view.getState().getMPoints().add(newMP);
        return this;
    }

    @objid ("7583b32d-ef73-4937-bd51-6a8941bb0208")
    protected static Point getLocationFromFace(GraphicalEditPart node, LinkAnchorFace face) {
        IFigure figure = node.getFigure();
        Rectangle bounds = figure.getBounds();
        Point ref = getRelLocationFromFace(bounds, face);
        figure.translateToAbsolute(ref);
        return ref;
    }

    @objid ("c5609c35-3acd-40e7-a275-ce59df6bbeeb")
    protected static Point getLocationFromFace(GraphicalEditPart node, LinkAnchorFace face, int numerator, int denominator) {
        IFigure figure = node.getFigure();
        Rectangle bounds = figure.getBounds();
        Point ref = getRelLocationFromFace(bounds, face, numerator, denominator);
        figure.translateToAbsolute(ref);
        return ref;
    }

    @objid ("f748ebc7-b36d-4ee6-b4ea-a44769abea00")
    private RawPathData createRawPathData() {
        final ConnectionEditPart editPart = this.editor.getConnectionEditPart();
        final IGmLink gmLink = (IGmLink) editPart.getModel();
        final ConnectionState viewState = this.editor.getView().getState();
        Connection conn = this.editor.getView().getConnection();
        
        final RawPathData rawPath = new RawPathData();
        rawPath.setRoutingMode(gmLink.getPath().getRouterKind());
        rawPath.setSrcPoint(viewState.getSourceAnchor().getLocation(new Point()));
        rawPath.setLastPoint(viewState.getTargetAnchor().getLocation(new Point()));
        
        // Add all bend points in the raw path
        List<Point> rawPathPoints = rawPath.getPath();
        for (MPoint relPoint : viewState.getMPoints()) {
            // hack : create a MPoint instead of Point, it is identified by AutoOrthoconnectionHelper .
            MPoint absPoint = new MPoint(relPoint, relPoint.isFixed());
            conn.translateToAbsolute(absPoint);
            rawPathPoints.add(absPoint);
        }
        return rawPath;
    }

    @objid ("1c4d662c-b333-4aef-bef1-e01d591161ee")
    private static Point getRelLocationFromFace(Rectangle bounds, LinkAnchorFace face) {
        switch (face) {
        case EAST:
            return bounds.getRight();
        case NORTH:
            return bounds.getTop();
        case SOUTH:
            return bounds.getBottom();
        case WEST:
            return bounds.getLeft();
        default:
            return bounds.getCenter();
        }
        
    }

    @objid ("bf8daa03-4660-4447-baa9-7db99e18a469")
    private static Point getRelLocationFromFace(Rectangle bounds, LinkAnchorFace face, int numerator, int denominator) {
        double fraction = (double) numerator / denominator;
        switch (face) {
        case EAST:
            return bounds.getTopRight().translate(0, fraction * bounds.height());
        case NORTH:
            return bounds.getTopLeft().translate(fraction * bounds.width(), 0);
        case SOUTH:
            return bounds.getBottom().translate(fraction * bounds.width(), 0);
        case WEST:
            return bounds.getLeft().translate(0, fraction * bounds.height());
        default:
            return bounds.getCenter();
        }
        
    }

}
