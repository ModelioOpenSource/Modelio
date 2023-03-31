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
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.api.modelio.diagram.ILinkPoint;
import org.modelio.api.modelio.diagram.ILinkRoute;
import org.modelio.api.modelio.diagram.InvalidDestinationPointException;
import org.modelio.api.modelio.diagram.InvalidPointsPathException;
import org.modelio.api.modelio.diagram.InvalidSourcePointException;
import org.modelio.api.modelio.diagram.LinkAnchorFace;
import org.modelio.api.modelio.diagram.LinkPointKind;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.link.ConnectionRoutingServices;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.IAnchorModelProvider;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.link.anchors.AnchorRefHelper;
import org.modelio.diagram.elements.core.link.ortho.edit.ConnectionEditor;
import org.modelio.diagram.elements.core.link.ortho.edit.ConnectionView;
import org.modelio.diagram.elements.core.link.path.BendPointUtils;
import org.modelio.diagram.elements.core.link.path.ConnectionPolicyUtils;
import org.modelio.diagram.elements.core.link.path.IConnectionHelper;
import org.modelio.diagram.elements.core.link.path.RawPathData;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmPath;

/**
 * Implementation of {@link ILinkRoute}.
 * 
 * @author cma
 * @since 5.1.0
 */
@objid ("755a9dd6-4f7a-470b-a009-97e1274a863f")
public class LinkRoute implements ILinkRoute {
    @objid ("7f89dbcc-4999-4d9c-95c7-3022a84dd511")
    private final List<ILinkPoint> points = new ArrayList<>();

    @objid ("5ee3b121-ccea-46f5-8327-f6d5e0ae28b3")
    private static final MPoint TMP = new MPoint();

    /**
     * @param ep the edited connection edit part
     */
    @objid ("264556f6-ddd6-4f08-afe6-80eae8b0103a")
    public  LinkRoute(ConnectionEditPart ep) {
        this( (Connection) ep.getFigure());
    }

    /**
     * @param conn the edited connection figure
     */
    @objid ("fce58f5d-2ef8-4e21-a113-4b80e47ab340")
    public  LinkRoute(Connection conn) {
        ConnectionView view = new ConnectionView().init(conn);
        
        this.points.add(computeSourceLinkPoint(view, new LinkPointData(LinkPointKind.ANCHOR_DISCRETE, TMP)));
        computeBendPoints(conn, this.points);
        this.points.add(computeTargetLinkPoint(view, new LinkPointData(LinkPointKind.ANCHOR_DISCRETE, TMP)));
        
    }

    @objid ("efecb1d6-65e4-4874-94ba-7ee5eda1e4bd")
    @Override
    public ILinkRoute addBendPoint(Point loc) {
        this.points.add(this.points.size()-1, new LinkPointData(LinkPointKind.BENDPOINT, loc));
        return this;
    }

    @objid ("bbd48f10-eb38-42eb-aa2c-e9c223418838")
    @Override
    public ILinkRoute addFixedPoint(Point loc) {
        this.points.add(this.points.size()-1, new LinkPointData(LinkPointKind.BENDPOINT_FIXED, loc));
        return this;
    }

    /**
     * Applies the route to the edited Connection edit part.
     * @param connectionEditPart the connection edit part to apply this route to
     * @throws InvalidSourcePointException if the source anchor is missing
     * @throws InvalidPointsPathException if the target anchor is missing
     * @throws InvalidDestinationPointException ?
     */
    @objid ("42780021-8faf-4bc0-bce0-71a816ecfcc5")
    public void apply(ConnectionEditPart connectionEditPart) throws InvalidSourcePointException, InvalidPointsPathException, InvalidDestinationPointException {
        if (this.points.isEmpty()) {
            createDefaultRoute(connectionEditPart);
        }
        
        ILinkPoint sourceLinkPoint = getSourceAnchor();
        ILinkPoint targetLinkPoint = getTargetAnchor();
        
        if (sourceLinkPoint == null) {
            throw new InvalidSourcePointException("No source anchor");
        }
        if (! isAnchorPoint(sourceLinkPoint)) {
            throw new InvalidSourcePointException(sourceLinkPoint+" first point is not an anchor link point");
        }
        if (targetLinkPoint == null) {
            throw new InvalidDestinationPointException("No target anchor");
        }
        if (! isAnchorPoint(targetLinkPoint)) {
            throw new InvalidSourcePointException(targetLinkPoint+" last point is not an anchor link point.");
        }
        
        final IGmLink model = (IGmLink) connectionEditPart.getModel();
        final IGmPath newPath = new GmPath(model.getPath());
        
        final RawPathData rawPath = createRawPathData(connectionEditPart);
        
        ConnectionEditor editor = new ConnectionEditor().init(connectionEditPart);
        
        ConnectionAnchor sourceAnchor = editor.requestSourceAnchor()
                .withLocation(computeSourceAnchorRef(editor))
                .withSliding(sourceLinkPoint.getKind() != LinkPointKind.ANCHOR_DISCRETE)
                .requestAnchor();
        
        ConnectionAnchor targetAnchor = editor.requestTargetAnchor()
                .withLocation(computeTargetAnchorRef(editor))
                .withSliding(targetLinkPoint.getKind() != LinkPointKind.ANCHOR_DISCRETE)
                .requestAnchor();
        
        
        // Change the path
        newPath.setSourceAnchor(((IAnchorModelProvider) connectionEditPart.getSource()).createAnchorModel(sourceAnchor));
        newPath.setTargetAnchor(((IAnchorModelProvider) connectionEditPart.getTarget()).createAnchorModel(targetAnchor));
        
        final Connection cnx = (Connection) connectionEditPart.getFigure();
        cnx.setSourceAnchor(sourceAnchor);
        cnx.setTargetAnchor(targetAnchor);
        
        ConnectionRoutingServices routingServices = ConnectionPolicyUtils.getRoutingServices(connectionEditPart);
        IConnectionHelper helper = routingServices.getConnectionHelperFactory().createFromRawData(rawPath, cnx);
        Object modelPathData = helper.getModelPathData();
        
        newPath.setPathData(modelPathData);
        model.setLayoutData(newPath);
        
        cnx.getUpdateManager().performValidation();
        
    }

    @objid ("4016f08e-423d-40db-839f-dab8e44fe962")
    private Point computeSourceAnchorRef(ConnectionEditor editor) {
        PrecisionRectangle sourceBounds = editor.getView().getAnchorBounds().source;
        Point forbidden = sourceBounds.getCenter();
        
        Point ret = AnchorRefHelper.findGoodAnchorRef(getAllPoints(), ILinkPoint::getLocation, true, getAllPoints().size(), forbidden);
        if (ret != null)
            return ret;
        return sourceBounds.getRight();
    }

    @objid ("6fcde409-5c92-4e32-8f05-82c4b162cfee")
    private Point computeTargetAnchorRef(ConnectionEditor editor) {
        PrecisionRectangle targetBounds = editor.getView().getAnchorBounds().target;
        Point forbidden = targetBounds.getCenter();
        
        Point ret = AnchorRefHelper.findGoodAnchorRef(getAllPoints(), ILinkPoint::getLocation, false, getAllPoints().size(), forbidden);
        if (ret != null)
            return ret;
        return targetBounds.getRight();
    }

    @objid ("057c9517-76ad-491e-950a-85239b872c79")
    @Override
    public ILinkRoute clearBendPoints() {
        getBendPoints().clear();
        return this;
    }

    /**
     * Create a completely empty LinkRoute.
     * @return an empty route
     */
    @objid ("b07ebc45-eb20-4998-baf6-49b05acedfbc")
    public static LinkRoute createEmpty() {
        return new LinkRoute();
    }

    @objid ("a061ac16-9963-43b3-8afa-d5ad9aede21f")
    @Override
    public ILinkPoint createLinkPoint(LinkPointKind kind, Point location) {
        return new LinkPointData(kind, location);
    }

    @objid ("dcf8ee45-7fc5-4fb6-831c-703bcfa94983")
    @Override
    public List<ILinkPoint> getAllPoints() {
        return this.points;
    }

    @objid ("71283291-dc17-4c96-8196-7e211eeab865")
    @Override
    public List<ILinkPoint> getBendPoints() {
        return this.points.subList(1, this.points.size()-1);
    }

    @objid ("7a273e3a-9b40-4d13-8ecc-e7cb0d09a654")
    @Override
    public ILinkPoint getSourceAnchor() {
        return this.points.get(0);
    }

    @objid ("ad00aed0-1356-40ba-b0b2-928c7ef2af49")
    @Override
    public ILinkPoint getTargetAnchor() {
        return this.points.get(this.points.size()-1);
    }

    @objid ("6d036c1c-3d91-410a-9506-4fcf388096b1")
    @Override
    public void removePoint(int index) {
        if (index == 0 ||index >= this.points.size()-1)
            throw new IndexOutOfBoundsException(String.format("Index %d not inside [%d..%d]", index, 1, this.points.size()-2));
        this.points.remove(index);
        
    }

    @objid ("b96e0fdf-901b-4212-82e9-8de9474815bd")
    @Override
    public ILinkRoute setSourceAnchor(Point loc, boolean sliding) {
        LinkPointKind kind = sliding ? LinkPointKind.ANCHOR_SLIDING : LinkPointKind.ANCHOR_DISCRETE;
        if (this.points.isEmpty()) {
            this.points.add(new LinkPointData(kind, loc));
        } else {
            this.points.get(0).setKind(kind).setLocation(loc);
        }
        return this;
    }

    @objid ("a9b79f89-899a-4fbe-b20d-8d4d3b40c43f")
    @Override
    public ILinkRoute setTargetAnchor(Point loc, boolean sliding) {
        int n = this.points.size();
        if (n == 0) {
            this.points.add(new LinkPointData(LinkPointKind.ANCHOR_DISCRETE, new Point()));
        }
        
        LinkPointKind kind = sliding ? LinkPointKind.ANCHOR_SLIDING : LinkPointKind.ANCHOR_DISCRETE;
        if (n==1) {
            this.points.add(new LinkPointData(kind, loc));
        } else {
            this.points.get(n-1).setKind(kind).setLocation(loc);
        }
        return this;
    }

    @objid ("e86c74e9-e5f9-44b4-96df-111585b36c91")
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Point pt = new Point();
        boolean first = true;
        s.append(getClass().getSimpleName());
        s.append("[");
        for (ILinkPoint lp : this.points) {
            if (first)
                first = false;
            else
                s.append(", ");
            s.append(lp.getKind()).append(" ");
            s.append(lp.getLocation(pt));
        }
        s.append("]");
        return s.toString();
    }

    /**
     * initialize a completely empty LinkRoute.
     */
    @objid ("21757b46-1d36-479d-a620-1aa239f169c7")
    protected  LinkRoute() {
        
    }

    @objid ("8b39d7ca-0eb8-4ed9-b4a7-052514ba84a3")
    protected static void computeBendPoints(Connection c, List<ILinkPoint> out) {
        Object constraint = c.getRoutingConstraint();
        if (BendPointUtils.isMPointList(constraint)) {
            @SuppressWarnings ("unchecked")
            List<MPoint> mpl = (List<MPoint>) constraint;
            for (MPoint mp : mpl) {
                LinkPointKind kind = mp.isFixed() ? LinkPointKind.BENDPOINT_FIXED : LinkPointKind.BENDPOINT;
                TMP.setValues(mp);
                c.translateToAbsolute(TMP);
                out.add(new LinkPointData(kind , TMP));
            }
        } else {
            PointList connPoints = c.getPoints();
            final int cardPoints = connPoints.size();
            for (int i = 1; i < cardPoints-1; i++) {
                connPoints.getPoint(TMP, i);
                LinkPointKind kind = TMP.isFixed() ? LinkPointKind.BENDPOINT_FIXED : LinkPointKind.BENDPOINT;
                c.translateToAbsolute(TMP);
                out.add(new LinkPointData(kind , TMP));
            }
        }
        
    }

    @objid ("66c1c6a8-780a-4c44-94c0-6eb1a5aa9567")
    protected static Point getLocationFromFace(GraphicalEditPart node, LinkAnchorFace face) {
        IFigure figure = node.getFigure();
        Rectangle bounds = figure.getBounds();
        Point ref = getRelLocationFromFace(bounds, face).getCopy();
        figure.translateToAbsolute(ref);
        return ref;
    }

    @objid ("6ea542ea-abe0-4cff-ac2d-ffa5719cf788")
    private static ILinkPoint computeSourceLinkPoint(ConnectionView view, ILinkPoint out) {
        LinkPointKind kind = getKind(view.getState().getSourceAnchor());
        out.setLocation(view.getSourceLocation(TMP, true));
        out.setKind(kind);
        return out;
    }

    @objid ("b9403616-582c-47d1-a659-4c36f13c5e9d")
    private static ILinkPoint computeTargetLinkPoint(ConnectionView view, ILinkPoint out) {
        LinkPointKind kind = getKind(view.getState().getTargetAnchor());
        out.setLocation(view.getTargetLocation(TMP, true));
        out.setKind(kind);
        return out;
    }

    @objid ("ccbe8e54-a15f-4f0e-a895-bd73401226fa")
    private void createDefaultRoute(ConnectionEditPart connectionEditPart) {
        this.points.clear();
        this.points.addAll(new LinkRouteBuilder(connectionEditPart)
                .setSourceAnchorFaceTarget(false)
                .setTargetAnchorFaceSource(false)
                .getAllPoints());
        
    }

    @objid ("3c6e3b5c-6ae6-41e2-b17d-f58e5cd7df0c")
    private RawPathData createRawPathData(ConnectionEditPart editPart) {
        final IGmLink gmLink = (IGmLink) editPart.getModel();
        
        final RawPathData rawPath = new RawPathData();
        rawPath.setRoutingMode(gmLink.getPath().getRouterKind());
        rawPath.setSrcPoint(this.points.get(0).getLocation());
        rawPath.setLastPoint(this.points.get(this.points.size()-1).getLocation());
        
        // Add all bend points in the raw path
        List<Point> rawPathPoints = rawPath.getPath();
        for (int i = 1, n = this.points.size() - 1; i < n; i++) {
            ILinkPoint lp = this.points.get(i);
            // hack : create a MPoint instead of Point, it is identified by AutoOrthoconnectionHelper .
            MPoint absPoint = new MPoint();
            lp.getLocation(absPoint).setFixed(lp.getKind() == LinkPointKind.BENDPOINT_FIXED);
            rawPathPoints.add(absPoint);
        }
        return rawPath;
    }

    @objid ("543c75fd-0419-41be-9bf8-257169a2d98e")
    private static LinkPointKind getKind(ConnectionAnchor a) {
        if (a instanceof FixedAnchor)
            return LinkPointKind.ANCHOR_DISCRETE;
        else
            return LinkPointKind.ANCHOR_SLIDING;
        
    }

    @objid ("565f553b-8c84-4136-a15e-02f86b890d54")
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

    @objid ("2420f248-c970-4d7d-a5f2-1b648e85fbdf")
    private static boolean isAnchorPoint(ILinkPoint p) {
        return p.getKind()==LinkPointKind.ANCHOR_DISCRETE || p.getKind()==LinkPointKind.ANCHOR_SLIDING;
    }

    /**
     * Immutable implementation of {@link ILinkPoint}
     */
    @objid ("a8faf080-9502-4d6f-9449-0e584a01a689")
    static class LinkPointData implements ILinkPoint {
        @objid ("e2fe6f1e-04dc-4225-aea2-edc514793b1e")
        private LinkPointKind kind;

        @objid ("268caa65-b34a-49bb-958f-1aee987ad3fc")
        private final Point location;

        @objid ("a7270a4a-bb47-44a8-8e36-90a2917c36b3")
        public  LinkPointData(LinkPointKind kind, Point location) {
            super();
            this.kind = kind;
            this.location = new Point(location);
            
        }

        @objid ("e24f8fac-4279-4555-b528-22521d3c29a4")
        @Override
        public LinkPointKind getKind() {
            return this.kind;
        }

        @objid ("5d2d914e-5c31-4d10-b51f-f706b2fc8b50")
        @SuppressWarnings ("unchecked")
        @Override
        public <P extends Point> P getLocation(P out) {
            return (P) out.setLocation(this.location);
        }

        @objid ("9dfcdb22-484f-4bf3-b14a-6e06298a6cb9")
        @Override
        public ILinkPoint setKind(LinkPointKind val) {
            this.kind = val;
            return this;
        }

        @objid ("8d8d5912-47c3-4778-bb3e-6598c2c5b4c9")
        @Override
        public ILinkPoint setLocation(Point val) {
            this.location.setLocation(val);
            return this;
        }

        @objid ("c40ba623-8ad3-424b-b2fb-8947f5be4373")
        @Override
        public int x() {
            return this.location.x();
        }

        @objid ("191da64f-df1c-40e1-93a4-90cce4697b4e")
        @Override
        public int y() {
            return this.location.y();
        }

        @objid ("13f316a5-1d2a-45a3-88f7-687a45f15350")
        @Override
        public String toString() {
            return String.format("%s [kind=%s, location=%s]", getClass().getSimpleName(), this.kind, this.location);
        }

    }

}
