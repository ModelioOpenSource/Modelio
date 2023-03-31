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
package org.modelio.diagram.elements.core.link.path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.modelio.diagram.elements.core.figures.routers.AutoOrthoConstants;
import org.modelio.diagram.elements.core.figures.routers.AutoOrthogonalRouter;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.link.ortho.edit.ConnectionView;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Helper class for Orthogonal routing mode.
 * 
 * @author cmarin
 */
@objid ("b7f25aae-15ba-4b9e-8157-dfb2312c1520")
public class AutoOrthoConnectionHelper implements IConnectionHelper {
    @objid ("84cca576-cad8-4e96-892e-dabb671a955d")
    private final List<Point> modelBendPoints;

    @objid ("b438605d-a813-46fb-ac1a-0f41ad1fe13f")
    private final Connection connection;

    @objid ("8c7c0b45-be34-42d4-9bd7-2017e7976ea0")
    private final ConnectionView view = new ConnectionView();

    /**
     * Constructor a connection and its existing points.
     * @param connection the connection for which this helper is created.
     * @return the built helper
     */
    @objid ("3a912538-7a49-4f8a-b907-d6b8f3b557a5")
    public static AutoOrthoConnectionHelper fromConnectionPoints(final Connection connection) {
        return new AutoOrthoConnectionHelper(connectionPointsToModelConstraint(connection), connection);
    }

    @objid ("a16c9652-3c51-4918-9b74-6bb71fae0d13")
    protected static List<Point> connectionPointsToModelConstraint(final Connection connection) {
        PointList l = connection.getPoints();
        List<Point> draw2dBendPoints = new ArrayList<>(l.size());
        for (int i = 1; i < l.size() - 1; i++) {
            draw2dBendPoints.add(l.getPoint(i));
        }
        return BendPointUtils.draw2dPointsToModelConstraint(draw2dBendPoints);
    }

    /**
     * Create an empty helper.
     * <p>
     * Uses {@link #updateFrom(RawPathData)} next.
     * @param connection the connection for which this helper is created.
     * @return the built helper
     */
    @objid ("ec94f9c8-8a3b-47c1-85a2-79a0dfe89800")
    public static AutoOrthoConnectionHelper empty(final Connection connection) {
        return new AutoOrthoConnectionHelper(new ArrayList<>(), connection);
    }

    @objid ("167fd4f8-a5dc-467f-a209-391d99924a6f")
    @Override
    public boolean isUsable() {
        return this.connection != null && this.connection.getParent() != null;
    }

    /**
     * Constructor from a list of points stored in the model (in coordinates relative to the connection or the origin figure).
     * @param modelBendPoints the list of point as stored in the model.
     * @param connection the connection for which this helper is created.
     */
    @objid ("4ac88b5b-3da1-40c1-9aa0-37854511ef8b")
    public  AutoOrthoConnectionHelper(final List<Point> modelBendPoints, final Connection connection) {
        this.connection = connection;
        this.modelBendPoints = modelBendPoints;
        
    }

    @objid ("d752a3da-7b11-4283-9186-97b24bd32bf3")
    @Override
    public List<Point> getModelBendPoints() {
        return this.modelBendPoints;
    }

    @objid ("34d40328-5aff-4aa0-8905-2a4f7fa5bd88")
    @Override
    public List<Bendpoint> getRoutingConstraint() {
        return BendPointUtils.toDraw2dConstraint(this.modelBendPoints);
    }

    @objid ("855191e1-0688-4d0a-88e8-40cc598f8f87")
    @Override
    public ConnectionRouterId getRoutingMode() {
        return ConnectionRouterId.ORTHOGONAL;
    }

    @objid ("a1ae57b8-7cef-4c1c-922a-5d94e40b72d4")
    @Override
    public void updateFrom(final RawPathData req) {
        this.modelBendPoints.clear();
        
        ConnectionAnchor targetAnchor = this.connection.getTargetAnchor();
        
        if (targetAnchor instanceof XYAnchor) {
            // Replace XY anchor with an anchor that snaps with previous point
            this.connection.setTargetAnchor(new OrthoSnapAnchor(targetAnchor.getReferencePoint()));
        
            readRawPoints(req.getPath());
        
            // Hack : modify RawPathData.getLastPoint() with the snapped target point
            this.view.init(this.connection);
            this.view.getState().setConstraint(this.modelBendPoints);
            MPoint mp = this.view.getTargetLocation(new MPoint(), true);
            req.setLastPoint(mp);
        } else {
            readRawPoints(req.getPath());
        }
        
    }

    /**
     * Go through the list of point and try to make an orthogonal path from it.
     * <p>
     * Add the points to {@link #modelBendPoints} .
     * @param path a list of points in absolute coordinates
     */
    @objid ("0d9c0369-d07c-4b33-a08b-c29d4c163e8b")
    private void readRawPoints(final List<Point> path) {
        AutoOrthogonalRouter router = new AutoOrthogonalRouter()
                .setCleanupManualPoints(false)
                .setSimplifyEnds(false);
        if (path.isEmpty()) {
            List<MPoint> newRoute = router.computeMPointRoute(this.connection, Collections.emptyList());
            AutoOrthogonalRouter.routeToConstraint(newRoute);
            this.modelBendPoints.addAll(newRoute);
        
        } else {
            // Go through the list of user point as is.
            List<MPoint> userBendPoints = convertToRelativeConstraint(path);
        
            // launch the router to compute automatic points
            List<MPoint> newRoute = router.computeMPointRoute(this.connection, userBendPoints);
            AutoOrthogonalRouter.routeToConstraint(newRoute);
            this.modelBendPoints.addAll(newRoute);
        }
        
    }

    @objid ("4d57c366-9c24-4b90-8279-9ae495296e26")
    private List<MPoint> convertToRelativeConstraint(final List<Point> absPath) {
        List<MPoint> userBendPoints = new ArrayList<>(absPath.size());
        for (int i = 0; i < absPath.size(); ++i) {
            Point absPt = absPath.get(i);
            MPoint relPt = new MPoint(absPt, true);
            if (absPt instanceof MPoint) {
                // This may happen when RawPath is built with module API.
                relPt.setFixed(((MPoint) absPt).isFixed());
            }
        
            this.connection.translateToRelative(relPt);
            userBendPoints.add(relPt);
        }
        return userBendPoints;
    }

    /**
     * Variation of XYAnchor that snaps to the point passed to {@link #getLocation(Point)}.
     * 
     * @author cma
     */
    @objid ("a7d36ff7-8205-4396-96ef-ec3f980f1edb")
    private static class OrthoSnapAnchor extends XYAnchor {
        @objid ("6c55df6c-5e64-4546-a47c-ea4a8040c4fc")
        public  OrthoSnapAnchor(Point p) {
            super(p);
        }

        @objid ("bcd72433-c55a-4947-a792-323417126487")
        @Override
        public Point getLocation(Point prev) {
            Point p = getReferencePoint().getCopy();
            
            // snap to prev
            if (Math.abs(p.x() - prev.x()) < AutoOrthoConstants.MIN_DIST) {
                p.setX(prev.x());
            }
            if (Math.abs(p.y() - prev.y()) < AutoOrthoConstants.MIN_DIST) {
                p.setY(prev.y());
            }
            return p;
        }

    }

}
