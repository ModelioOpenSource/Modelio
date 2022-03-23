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
@objid ("5d914e7b-a981-442f-9628-df52de6f3638")
public class LinkRoute implements ILinkRoute {
    @objid ("433d0578-b46a-493e-94fe-8b8765a54efb")
    private static final MPoint TMP = new MPoint();

    @objid ("fa8ae6e3-be29-4cbf-935c-f21b3c90c027")
    private final List<ILinkPoint> points = new ArrayList<>();

    /**
     * @param ep the edited connection edit part
     */
    @objid ("c9e45433-15e0-4a42-862f-1ef0dd88943f")
    public  LinkRoute(ConnectionEditPart ep) {
        this( (Connection) ep.getFigure());
    }

    /**
     * @param conn the edited connection figure
     */
    @objid ("ab08db64-b1f2-4d36-aef2-51fa4ed2d385")
    public  LinkRoute(Connection conn) {
        ConnectionView view = new ConnectionView().init(conn);
        
        this.points.add(computeSourceLinkPoint(view, new LinkPointData(LinkPointKind.ANCHOR_DISCRETE, TMP)));
        computeBendPoints(conn, this.points);
        this.points.add(computeTargetLinkPoint(view, new LinkPointData(LinkPointKind.ANCHOR_DISCRETE, TMP)));
        
    }

    @objid ("3672642c-3b83-41ad-a62b-0533e791f3f6")
    @Override
    public ILinkRoute addBendPoint(Point loc) {
        this.points.add(this.points.size()-1, new LinkPointData(LinkPointKind.BENDPOINT, loc));
        return this;
    }

    @objid ("4e56e2cc-b429-407e-b3dd-7e91e4285b4d")
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
    @objid ("4107ad79-06ac-434a-9709-5dea158213e7")
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

    @objid ("9b591060-ba6b-49c2-b81a-e174e66634d0")
    private Point computeSourceAnchorRef(ConnectionEditor editor) {
        PrecisionRectangle sourceBounds = editor.getView().getAnchorBounds().source;
        Point forbidden = sourceBounds.getCenter();
        
        Point ret = AnchorRefHelper.findGoodAnchorRef(getAllPoints(), ILinkPoint::getLocation, true, getAllPoints().size(), forbidden);
        if (ret != null)
            return ret;
        return sourceBounds.getRight();
    }

    @objid ("dcd4f924-f247-482a-a80a-843a916d65f7")
    private Point computeTargetAnchorRef(ConnectionEditor editor) {
        PrecisionRectangle targetBounds = editor.getView().getAnchorBounds().target;
        Point forbidden = targetBounds.getCenter();
        
        Point ret = AnchorRefHelper.findGoodAnchorRef(getAllPoints(), ILinkPoint::getLocation, false, getAllPoints().size(), forbidden);
        if (ret != null)
            return ret;
        return targetBounds.getRight();
    }

    @objid ("679a71fe-709a-4511-9945-42d573544668")
    @Override
    public ILinkRoute clearBendPoints() {
        getBendPoints().clear();
        return this;
    }

    /**
     * Create a completely empty LinkRoute.
     * @return an empty route
     */
    @objid ("72fa6bce-4f8c-4fa1-a31c-1582ea79dffa")
    public static LinkRoute createEmpty() {
        return new LinkRoute();
    }

    @objid ("1dec99ec-ecdb-4419-ba24-c1cc87f760b5")
    @Override
    public ILinkPoint createLinkPoint(LinkPointKind kind, Point location) {
        return new LinkPointData(kind, location);
    }

    @objid ("8a43e572-d104-48ef-8fcb-d5c086620cdf")
    @Override
    public List<ILinkPoint> getAllPoints() {
        return this.points;
    }

    @objid ("1ab89870-ff92-43c7-8151-7e8f1ddd2209")
    @Override
    public List<ILinkPoint> getBendPoints() {
        return this.points.subList(1, this.points.size()-1);
    }

    @objid ("ad2f6732-668d-4b65-b763-9ff95834c57d")
    @Override
    public ILinkPoint getSourceAnchor() {
        return this.points.get(0);
    }

    @objid ("94d2935c-4b46-4d06-9278-044458087f4b")
    @Override
    public ILinkPoint getTargetAnchor() {
        return this.points.get(this.points.size()-1);
    }

    @objid ("cb3206c5-20ba-4e61-aad6-774b6960c43e")
    @Override
    public void removePoint(int index) {
        if (index == 0 ||index >= this.points.size()-1)
            throw new IndexOutOfBoundsException(String.format("Index %d not inside [%d..%d]", index, 1, this.points.size()-2));
        this.points.remove(index);
        
    }

    @objid ("a0b5b61f-59e7-4854-bdf0-c0f3e4d7f16a")
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

    @objid ("9d1a8dfd-a0fd-40fa-bbb8-3cd26ec132b5")
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

    @objid ("96231e9e-963e-40c0-96d6-65e57c9b286d")
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
    @objid ("c630060e-8b45-45a4-98f8-4b61b760ac44")
    protected  LinkRoute() {
        
    }

    @objid ("8025f290-3a6f-4373-b3b6-6b82500667a3")
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

    @objid ("290f294f-495c-4936-9a62-71a36e73f229")
    protected static Point getLocationFromFace(GraphicalEditPart node, LinkAnchorFace face) {
        IFigure figure = node.getFigure();
        Rectangle bounds = figure.getBounds();
        Point ref = getRelLocationFromFace(bounds, face).getCopy();
        figure.translateToAbsolute(ref);
        return ref;
    }

    @objid ("aa997024-60d1-4a88-81a0-56fb7ea99d72")
    private static ILinkPoint computeSourceLinkPoint(ConnectionView view, ILinkPoint out) {
        LinkPointKind kind = getKind(view.getState().getSourceAnchor());
        out.setLocation(view.getSourceLocation(TMP, true));
        out.setKind(kind);
        return out;
    }

    @objid ("e76bf8be-92bb-48d8-8ed0-c89c5a4cda12")
    private static ILinkPoint computeTargetLinkPoint(ConnectionView view, ILinkPoint out) {
        LinkPointKind kind = getKind(view.getState().getTargetAnchor());
        out.setLocation(view.getTargetLocation(TMP, true));
        out.setKind(kind);
        return out;
    }

    @objid ("a40488d7-5925-45c0-b5c8-ac0a279e1d8d")
    private void createDefaultRoute(ConnectionEditPart connectionEditPart) {
        this.points.clear();
        this.points.addAll(new LinkRouteBuilder(connectionEditPart)
                .setSourceAnchorFaceTarget(false)
                .setTargetAnchorFaceSource(false)
                .getAllPoints());
        
    }

    @objid ("94f07999-e4a3-4487-ad3c-3eaa18d1518d")
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

    @objid ("182f49b2-9d27-4b7f-842d-b5d66e2775d2")
    private static LinkPointKind getKind(ConnectionAnchor a) {
        if (a instanceof FixedAnchor)
            return LinkPointKind.ANCHOR_DISCRETE;
        else
            return LinkPointKind.ANCHOR_SLIDING;
        
    }

    @objid ("df903604-6f3a-4f60-84b7-aae9833bd7e2")
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

    @objid ("c267a3ee-54cd-4da6-9309-dcf5ec17fc89")
    private static boolean isAnchorPoint(ILinkPoint p) {
        return p.getKind()==LinkPointKind.ANCHOR_DISCRETE || p.getKind()==LinkPointKind.ANCHOR_SLIDING;
    }

    /**
     * Immutable implementation of {@link ILinkPoint}
     */
    @objid ("b46d320a-c64b-4c02-8ef7-5bd60572cd72")
    static class LinkPointData implements ILinkPoint {
        @objid ("148b9bde-4f5a-4a93-ba8f-374595399911")
        private LinkPointKind kind;

        @objid ("a3acf4c8-9338-47f4-803c-74a35ef7c2c9")
        private final Point location;

        @objid ("32974ab6-eaf6-481b-8dd0-f94b2d88e3b5")
        public  LinkPointData(LinkPointKind kind, Point location) {
            super();
            this.kind = kind;
            this.location = new Point(location);
            
        }

        @objid ("87046cd3-2fe4-4c54-83eb-610e3dc01209")
        @Override
        public LinkPointKind getKind() {
            return this.kind;
        }

        @objid ("f880e029-8c23-4105-a8f6-b3a47f83a69c")
        @SuppressWarnings ("unchecked")
        @Override
        public <P extends Point> P getLocation(P out) {
            return (P) out.setLocation(this.location);
        }

        @objid ("a39d518b-bd8e-4923-bbc6-9d71faf75027")
        @Override
        public ILinkPoint setKind(LinkPointKind val) {
            this.kind = val;
            return this;
        }

        @objid ("e633f487-6b67-476f-b2cc-86f66c3ff236")
        @Override
        public ILinkPoint setLocation(Point val) {
            this.location.setLocation(val);
            return this;
        }

        @objid ("6e515966-4524-4072-9de6-08089f3b6cd8")
        @Override
        public int x() {
            return this.location.x();
        }

        @objid ("d0a65c9e-7cb1-449b-9d86-5d496d3c29ff")
        @Override
        public int y() {
            return this.location.y();
        }

        @objid ("97e60e7e-8d2a-4007-b78f-e4ed03dd211d")
        @Override
        public String toString() {
            return String.format("%s [kind=%s, location=%s]", getClass().getSimpleName(), this.kind, this.location);
        }

    }

}
