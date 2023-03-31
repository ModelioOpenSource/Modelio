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
@objid ("f6cb4af9-b977-4887-a8d8-760721ece08c")
public class LinkRouteBuilder implements ILinkRouteBuilder, org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints, org.modelio.api.modelio.diagram.ILinkRouteBuilder.Target {
    @objid ("548ba471-9f25-4cf9-a384-e507c40d2667")
    private static final MPoint TMP = new MPoint();

    @objid ("12128602-4caa-43a7-ba43-13ec2b180919")
    private final ConnectionEditor editor;

    /**
     * @param ep the connection edit part
     */
    @objid ("004ddcd1-9183-4cc1-8d6a-df05e2cf676e")
    public  LinkRouteBuilder(ConnectionEditPart ep) {
        this.editor = new ConnectionEditor().init(ep);
        // reset the constraint
        this.editor.getView().getState().setConstraint(new ArrayList<>());
        
    }

    @objid ("9a324c7a-799b-40d7-a7ba-2edc8b7cf28e")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints addBendPoint(Point loc) {
        MPoint mp = new MPoint(loc, false);
        return addMPoint(mp);
    }

    @objid ("49ad2e3d-19f8-4279-8304-d5329d23a0d2")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints addFixedPoint(Point loc) {
        MPoint mp = new MPoint(loc, true);
        return addMPoint(mp);
    }

    @objid ("155b53a9-d175-41bb-a7b2-e416270c5364")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints addHorizontalSegment(int nextBendPointX, boolean fixed) {
        ConnectionView view = this.editor.getView();
        int n = view.getTargetAnchorIndex();
        MPoint p1 = view.getPoint(TMP, n - 1, true);
        addMPoint(p1.setX(nextBendPointX).setFixed(fixed));
        return this;
    }

    @objid ("fb53d733-ec1d-4cd5-8d55-d3ec52bab268")
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

    @objid ("2694fade-e413-42bf-9374-10ab1d60f699")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints addVerticalSegment(int nextBendPointY, boolean fixed) {
        ConnectionView view = this.editor.getView();
        int n = view.getTargetAnchorIndex();
        MPoint p1 = view.getPoint(TMP, n - 1, true);
        addMPoint(p1.setY(nextBendPointY).setFixed(fixed));
        return this;
    }

    @objid ("e145f138-25c6-4081-bead-02d46241f388")
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
    @objid ("23badf89-0b45-4b43-a44e-d8a5267d0df8")
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
    @objid ("ff51c6ca-9613-4e68-aedd-36632c336fbc")
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

    @objid ("1d4624e2-fbb0-4acf-8029-06887242fa02")
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
    @objid ("7a6c57b0-cfa1-4624-8f44-4bcdbb6aac84")
    @Deprecated
    public List<ILinkPoint> getBendPoints() {
        final int cardPoints = this.editor.getView().cardPoints();
        List<ILinkPoint> ret = new ArrayList<>(cardPoints - 2);
        
        for (int i = 1; i < cardPoints - 1; i++) {
            ret.add(createBendPointData(i));
        }
        return ret;
    }

    @objid ("cd469a67-19ab-41e8-818f-dc300ced1535")
    private ILinkPoint createSourceAnchorLinkPoint() {
        ConnectionView view = this.editor.getView();
        LinkPointKind kind = getKind(view.getState().getSourceAnchor());
        return new LinkRoute.LinkPointData(kind, view.getSourceLocation(TMP, true));
    }

    @objid ("e7d6c231-9380-4196-a44b-ccb0a8cfc167")
    private static LinkPointKind getKind(ConnectionAnchor a) {
        if (a instanceof FixedAnchor) {
            return LinkPointKind.ANCHOR_DISCRETE;
        } else {
            return LinkPointKind.ANCHOR_SLIDING;
        }
        
    }

    @objid ("2d1d3aac-0bcd-4967-956e-d9b6ef982994")
    private ILinkPoint createTargetAnchorLinkPoint() {
        ConnectionView view = this.editor.getView();
        LinkPointKind kind = getKind(view.getState().getSourceAnchor());
        return new LinkRoute.LinkPointData(kind, view.getTargetLocation(TMP, true));
    }

    @objid ("4001e072-9ac7-4b7e-a059-787d5661a629")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.Target setSourceAnchor(Point loc, boolean sliding) {
        ConnectionAnchor a = this.editor.requestSourceAnchor()
                .withLocation(loc)
                .withSliding(sliding)
                .requestAnchor();
        this.editor.getView().getState().setSourceAnchor(a);
        return this;
    }

    @objid ("fdee0cce-7052-43c1-93f6-2c1b734c00f7")
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

    @objid ("04be3538-6d9e-4134-a98a-c07284394dba")
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

    @objid ("402b8d73-6fb4-428e-9181-a75813db1d58")
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

    @objid ("f96271ee-2a29-41a0-ad68-0bcad6cc8ad3")
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

    @objid ("edc7681d-fc25-4c8a-a5be-84fdd7fef8eb")
    @Override
    public org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints setTargetAnchor(Point loc, boolean sliding) {
        ConnectionAnchor a = this.editor.requestTargetAnchor()
                .withLocation(loc)
                .withSliding(sliding)
                .requestAnchor();
        this.editor.getView().getState().setTargetAnchor(a);
        return this;
    }

    @objid ("f8172d8f-2f2d-46d3-9f8f-52bc59fc4f83")
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

    @objid ("08c11b85-393a-4de6-8407-56b55434c322")
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
    @objid ("8617e02c-fd31-4785-b755-23cc62d40a70")
    protected org.modelio.api.modelio.diagram.ILinkRouteBuilder.BendPoints addMPoint(MPoint mp) {
        MPoint newMP = mp.getCopy();
        ConnectionView view = this.editor.getView();
        view.getConnection().translateToRelative(newMP);
        view.getState().getMPoints().add(newMP);
        return this;
    }

    @objid ("f114dd92-8374-47bb-bc42-08bc61152754")
    protected static Point getLocationFromFace(GraphicalEditPart node, LinkAnchorFace face) {
        IFigure figure = node.getFigure();
        Rectangle bounds = figure.getBounds();
        Point ref = getRelLocationFromFace(bounds, face);
        figure.translateToAbsolute(ref);
        return ref;
    }

    @objid ("d5204be7-8a24-4d29-8dea-cb67ce3364c3")
    protected static Point getLocationFromFace(GraphicalEditPart node, LinkAnchorFace face, int numerator, int denominator) {
        IFigure figure = node.getFigure();
        Rectangle bounds = figure.getBounds();
        Point ref = getRelLocationFromFace(bounds, face, numerator, denominator);
        figure.translateToAbsolute(ref);
        return ref;
    }

    @objid ("1cc07ef2-b9fa-451d-9304-a4c66e81f129")
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

    @objid ("8618d94a-2672-4cde-a411-5090d293be1f")
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

    @objid ("419dd389-06cd-4182-ac90-8b8b0f5053f5")
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
