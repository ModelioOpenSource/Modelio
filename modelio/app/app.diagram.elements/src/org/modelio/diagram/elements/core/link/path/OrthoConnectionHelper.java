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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;
import org.modelio.diagram.elements.core.figures.routers.AnchorBounds;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Helper class for Orthogonal routing mode.
 * 
 * @author cmarin
 */
@objid ("804ba8d7-1dec-11e2-8cad-001ec947c8cc")
public class OrthoConnectionHelper implements IConnectionHelper {
    @objid ("804ba8e1-1dec-11e2-8cad-001ec947c8cc")
    private static final int REFLEXIVE_OFFSET = 20;

    @objid ("64ec337b-1e83-11e2-8cad-001ec947c8cc")
    private final List<Point> modelBendPoints;

    @objid ("64ec337e-1e83-11e2-8cad-001ec947c8cc")
    private final Connection connection;

    /**
     * Temporary point used to avoid Point allocations.
     */
    @objid ("cd9c69fc-15ad-44d7-8004-e25566b39dcb")
    private static final PrecisionPoint A_POINT = new PrecisionPoint();

    /**
     * Constructor a connection and its existing points.
     * <p>
     * The existing points are added as automatic points.
     * @param connection the connection for which this helper is created.
     * @param connEp unused, just here to differentiate this constructor from {@link #OrthoConnectionHelper(Connection) the empty one}.
     */
    @objid ("804e0aff-1dec-11e2-8cad-001ec947c8cc")
    public  OrthoConnectionHelper(final Connection connection, ConnectionEditPart connEp) {
        this.connection = connection;
        
        PointList l = connection.getPoints();
        this.modelBendPoints = new ArrayList<>(l.size());
        for (int i = 0; i < l.size(); i++) {
            this.modelBendPoints.add(l.getPoint(new MPoint(0, 0, false), i));
        }
        
        assert (checkModelBendPoints()) : this.modelBendPoints;
        
    }

    /**
     * Builds an empty helper.
     * <p>
     * Uses {@link #updateFrom(RawPathData)} next.
     * @param connection the connection for which this helper is created.
     */
    @objid ("804e0b06-1dec-11e2-8cad-001ec947c8cc")
    public  OrthoConnectionHelper(final Connection connection) {
        this.connection = connection;
        this.modelBendPoints = new ArrayList<>();
        
    }

    /**
     * Constructor from a list of points stored in the model (in coordinates relative to the connection or the origin figure).
     * @param modelBendPoints the list of point as stored in the model.
     * @param connection the connection for which this helper is created.
     */
    @objid ("804e0b0f-1dec-11e2-8cad-001ec947c8cc")
    public  OrthoConnectionHelper(final List<Point> modelBendPoints, final Connection connection) {
        this.connection = connection;
        this.modelBendPoints = modelBendPoints;
        
        assert (checkModelBendPoints()) : this.modelBendPoints;
        
    }

    @objid ("3f3d9171-5f75-4b5a-a796-1b4cad168106")
    private boolean checkModelBendPoints() {
        if (this.modelBendPoints == null)
            return false;
        if (this.modelBendPoints.isEmpty())
            return true;
        return this.modelBendPoints.stream().allMatch(MPoint.class::isInstance);
    }

    @objid ("d17e6068-aae9-4118-a054-a3f8ff238923")
    @Override
    public boolean isUsable() {
        return this.connection != null && this.connection.getParent() != null;
    }

    @objid ("dda5bc01-83f2-4517-a8f2-e88db732f156")
    @Override
    public List<Point> getModelBendPoints() {
        return this.modelBendPoints;
    }

    @objid ("804e0b25-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<Bendpoint> getRoutingConstraint() {
        return BendPointUtils.toDraw2dConstraint(this.modelBendPoints);
    }

    @objid ("804e0b2e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ConnectionRouterId getRoutingMode() {
        return ConnectionRouterId.ORTHOGONAL;
    }

    @objid ("804e0b33-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void updateFrom(final RawPathData req) {
        this.modelBendPoints.clear();
        
        ConnectionAnchor sourceAnchor = this.connection.getSourceAnchor();
        IFigure sourceFigure = sourceAnchor.getOwner();
        
        ConnectionAnchor targetAnchor = this.connection.getTargetAnchor();
        IFigure targetFigure = targetAnchor.getOwner();
        
        if (sourceFigure != null && req.getPath().isEmpty()) {
            if (areAncestors(sourceFigure, targetFigure)) {
                // Source contains or is target
                // Reflexive case, add whatever points are necessary
                RawPathData reflexivePath = new RawPathData();
                reflexivePath.setSrcPoint(req.getSrcPoint().getCopy());
                reflexivePath.setLastPoint(req.getLastPoint());
                reflexivePath.setRoutingMode(req.getRoutingMode());
                addReflexivePoints(reflexivePath, sourceAnchor, sourceFigure, targetAnchor);
                readRawPoints(reflexivePath.getPath(), reflexivePath.getLastPoint());
            } else if (areAncestors(targetFigure, sourceFigure)) {
                // Target contains source
                // Add whatever points are necessary
                RawPathData reflexivePath = new RawPathData();
                reflexivePath.setSrcPoint(req.getSrcPoint().getCopy());
                reflexivePath.setLastPoint(req.getLastPoint());
                reflexivePath.setRoutingMode(req.getRoutingMode());
                addReflexivePoints(reflexivePath, sourceAnchor, targetFigure, targetAnchor);
                readRawPoints(reflexivePath.getPath(), reflexivePath.getLastPoint());
        
            } else {
                readRawPoints(req.getPath(), req.getLastPoint());
            }
        } else {
            readRawPoints(req.getPath(), req.getLastPoint());
        }
        
    }

    /**
     * Go through the list of point and try to make an orthogonal path from it.
     * <p>
     * Add the points to {@link #modelBendPoints} .
     * @param path a list of points in absolute coordinates
     * @param lastPoint the last point, not used anymore
     */
    @objid ("804e0b42-1dec-11e2-8cad-001ec947c8cc")
    private void readRawPoints(final List<Point> path, final Point lastPoint) {
        // Go through the list of point and try to make an orthogonal path from it.
        ConnectionAnchor sourceAnchor = this.connection.getSourceAnchor();
        // ConnectionAnchor targetAnchor = this.connection.getTargetAnchor();
        
        // Start by determining the exact position of the source anchor (in absolute coordinates).
        // Point lastReferencePoint = targetAnchor != null ? targetAnchor.getReferencePoint() : lastPoint;
        
        if (!path.isEmpty()) {
            MPoint tmpPoint = new MPoint(path.get(0), true);
            Point sourceLocation = sourceAnchor.getLocation(tmpPoint);
            // Now get the bounds of its owning figure (in absolute coordinates too)
            final Rectangle sourceBounds = AnchorBounds.getAnchorOwnerAbsoluteBounds(new PrecisionRectangle(), sourceAnchor);
            // Given the two datas, compute the orientation of the initial segment.
            Direction sourceAnchorOrientation = GeomUtils.getDirection(sourceLocation, sourceBounds);
            Orientation currentOrientation = sourceAnchorOrientation.orientation();
        
            Point previousPoint = sourceLocation;
        
            for (int i = 0; i < path.size(); ++i) {
                Point currentPoint = path.get(i);
                if (currentOrientation == Orientation.HORIZONTAL) {
                    tmpPoint.setLocation(currentPoint.x, previousPoint.y);
                    currentOrientation = Orientation.VERTICAL;
                } else {
                    tmpPoint.setLocation(previousPoint.x, currentPoint.y);
                    currentOrientation = Orientation.HORIZONTAL;
                }
        
                Point ppp = tmpPoint.getCopy();
                this.connection.translateToRelative(ppp);
                this.modelBendPoints.add(ppp);
        
                previousPoint.setLocation(tmpPoint);
            }
        }
        
    }

    @objid ("804e0b4e-1dec-11e2-8cad-001ec947c8cc")
    private static void addReflexivePoints(final RawPathData rawData, final ConnectionAnchor sourceAnchor, final IFigure sourceFigure, final ConnectionAnchor targetAnchor) {
        Rectangle sourceAbsoluteBounds = new Rectangle(sourceFigure.getBounds());
        sourceFigure.translateToAbsolute(sourceAbsoluteBounds);
        int right = sourceAbsoluteBounds.right() + REFLEXIVE_OFFSET;
        int bottom = sourceAbsoluteBounds.bottom() + REFLEXIVE_OFFSET;
        int left = sourceAbsoluteBounds.x() - REFLEXIVE_OFFSET;
        int top = sourceAbsoluteBounds.y() - REFLEXIVE_OFFSET;
        Point center = sourceAbsoluteBounds.getCenter();
        
        Point sourceRef = sourceAnchor.getReferencePoint();
        Point targetRef = targetAnchor.getReferencePoint();
        
        Direction sourceDirection = GeomUtils.getDirection(sourceRef, sourceAbsoluteBounds);
        Direction targetDirection = GeomUtils.getDirection(targetRef, sourceAbsoluteBounds);
        
        List<Point> path = rawData.getPath();
        switch (sourceDirection) {
        case EAST: {
            switch (targetDirection) {
            case EAST: {
                path.add(new MPoint(right, sourceRef.y(), false));
                path.add(new MPoint(right, targetRef.y(), false));
                break;
            }
            case SOUTH: {
                path.add(new MPoint(right, sourceRef.y(), false));
                path.add(new MPoint(right, bottom, false));
                path.add(new MPoint(targetRef.x(), bottom, false));
                break;
            }
            case WEST: {
                path.add(new MPoint(right, sourceRef.y(), false));
                if (sourceRef.y() <= center.y()) {
                    path.add(new MPoint(right, top, false));
                    path.add(new MPoint(left, top, false));
        
                } else {
                    path.add(new MPoint(right, bottom, false));
                    path.add(new MPoint(left, bottom, false));
                }
                path.add(new MPoint(left, targetRef.y(), false));
                break;
            }
            case NORTH:
            case NONE:
            default: {
                path.add(new MPoint(right, sourceRef.y(), false));
                path.add(new MPoint(right, top, false));
                path.add(new MPoint(targetRef.x(), top, false));
                break;
            }
            }
            break;
        }
        case SOUTH: {
            switch (targetDirection) {
            case EAST: {
                path.add(new MPoint(sourceRef.x(), bottom, false));
                path.add(new MPoint(right, bottom, false));
                path.add(new MPoint(right, targetRef.y(), false));
                break;
            }
            case SOUTH: {
                path.add(new MPoint(sourceRef.x(), bottom, false));
                path.add(new MPoint(targetRef.x(), bottom, false));
                break;
            }
            case WEST: {
                path.add(new MPoint(sourceRef.x(), bottom, false));
                path.add(new MPoint(left, bottom, false));
                path.add(new MPoint(left, targetRef.y(), false));
                break;
            }
            case NORTH:
            case NONE:
            default: {
                path.add(new MPoint(sourceRef.x(), bottom, false));
                if (sourceRef.x() < center.x()) {
                    path.add(new MPoint(left, bottom, false));
                    path.add(new MPoint(left, top, false));
                } else {
                    path.add(new MPoint(right, bottom, false));
                    path.add(new MPoint(right, top, false));
                }
                path.add(new MPoint(targetRef.x(), top, false));
                break;
            }
            }
            break;
        }
        case WEST: {
            switch (targetDirection) {
            case EAST: {
                path.add(new MPoint(left, sourceRef.y(), false));
                if (sourceRef.y() < center.y()) {
                    path.add(new MPoint(left, top, false));
                    path.add(new MPoint(right, top, false));
                } else {
                    path.add(new MPoint(left, bottom, false));
                    path.add(new MPoint(right, bottom, false));
                }
                path.add(new MPoint(right, targetRef.y(), false));
                break;
            }
            case SOUTH: {
                path.add(new MPoint(left, sourceRef.y(), false));
                path.add(new MPoint(left, bottom, false));
                path.add(new MPoint(targetRef.x(), bottom, false));
                break;
            }
            case WEST: {
                path.add(new MPoint(left, sourceRef.y(), false));
                path.add(new MPoint(left, targetRef.y(), false));
                break;
            }
            case NORTH:
            case NONE:
            default: {
                path.add(new MPoint(left, sourceRef.y(), false));
                path.add(new MPoint(left, top, false));
                path.add(new MPoint(targetRef.x(), top, false));
                break;
            }
            }
            break;
        }
        case NORTH:
        case NONE:
        default: {
            switch (targetDirection) {
            case SOUTH: {
                path.add(new MPoint(sourceRef.x(), top, false));
                if (sourceRef.x() < center.x()) {
                    path.add(new MPoint(left, top, false));
                    path.add(new MPoint(left, bottom, false));
                } else {
                    path.add(new MPoint(right, top, false));
                    path.add(new MPoint(right, bottom, false));
                }
                path.add(new MPoint(targetRef.x(), bottom, false));
                break;
            }
            case WEST: {
                path.add(new MPoint(sourceRef.x(), top, false));
                path.add(new MPoint(left, top, false));
                path.add(new MPoint(left, targetRef.y(), false));
                break;
            }
            case NORTH: {
                path.add(new MPoint(sourceRef.x(), top, false));
                path.add(new MPoint(targetRef.x(), top, false));
                break;
            }
            case EAST:
            case NONE:
            default: {
                path.add(new MPoint(sourceRef.x(), top, false));
                path.add(new MPoint(right, top, false));
                path.add(new MPoint(right, targetRef.y(), false));
                break;
            }
            }
            break;
        }
        }
        
    }

    @objid ("46c0d2be-729e-41c5-8932-06b3f7f9de1f")
    private static void fixNoBendpointsLink(final List<Point> allPoints, final Point sourceLocation, final Point targetLocation, final Direction sourceAnchorOrientation, final Direction targetAnchorOrientation) {
        // If there is no intermediary bend point, check whether the anchors location are aligned, and add bend point(s) if not.
        if (sourceAnchorOrientation == Direction.NORTH || sourceAnchorOrientation == Direction.SOUTH) {
            if (targetAnchorOrientation == Direction.NORTH || targetAnchorOrientation == Direction.SOUTH) {
                if (sourceLocation.x != targetLocation.x) {
                    // No luck: not aligned, we need 2 additional bend points.
                    A_POINT.setLocation(sourceLocation.x, (sourceLocation.y + targetLocation.y) / 2);
                    allPoints.add(allPoints.size() - 1, new MPoint(A_POINT, false));
                    A_POINT.setLocation(targetLocation.x, (sourceLocation.y + targetLocation.y) / 2);
                    allPoints.add(allPoints.size() - 1, new MPoint(A_POINT, false));
                }
                // else: good luck: both anchors are aligned, nothing to do!
            } else {
                // We need an additional bend point.
                A_POINT.setLocation(sourceLocation.x, targetLocation.y);
                allPoints.add(allPoints.size() - 1, new MPoint(A_POINT, false));
            }
        } else {
            if (targetAnchorOrientation == Direction.NONE) {
                // Not oriented target anchor: we might need an additional bend point.
                if (sourceLocation.y != targetLocation.y) {
                    // No luck, anchors are not aligned, we need a bend point.
                    A_POINT.setLocation(targetLocation.x, sourceLocation.y);
                    allPoints.add(allPoints.size() - 1, new MPoint(A_POINT, false));
                }
                // else: good luck, both anchors are aligned, nothing to do!
            } else if (targetAnchorOrientation == Direction.SOUTH ||
                    targetAnchorOrientation == Direction.NORTH) {
                // We need an additional bend point
                A_POINT.setLocation(targetLocation.x, sourceLocation.y);
                allPoints.add(allPoints.size() - 1, new MPoint(OrthoConnectionHelper.A_POINT, false));
            } else {
                if (sourceLocation.y != targetLocation.y) {
                    // No luck: not aligned, we need 2 additional bend points.
                    A_POINT.setLocation((sourceLocation.x + targetLocation.x) / 2, sourceLocation.y);
                    allPoints.add(allPoints.size() - 1, new MPoint(A_POINT, false));
                    A_POINT.setLocation((sourceLocation.x + targetLocation.x) / 2, targetLocation.y);
                    allPoints.add(allPoints.size() - 1, new MPoint(A_POINT, false));
                }
                // else: good luck: both anchors are aligned, nothing to do!
            }
        }
        
    }

    @objid ("a68af7e8-9692-44cd-b05b-8dd86deb3dd2")
    @Deprecated
    private static void __fixLastBendpointLink(final List<Point> allPoints, final Point targetLocation, final Direction targetAnchorOrientation) {
        if (allPoints.size() >= 2) {
            Point previousLocation = allPoints.get(allPoints.size() - 2);
            Point lastLocation = allPoints.get(allPoints.size() - 1);
        
            Direction direction;
            if (previousLocation.x == lastLocation.x) {
                // VERTICAL
                direction = lastLocation.y - targetLocation.y >= 0 ? Direction.WEST : Direction.EAST;
            } else if (previousLocation.y == lastLocation.y) {
                // HORIZONTAL
                direction = lastLocation.x - targetLocation.x >= 0 ? Direction.NORTH : Direction.SOUTH;
            } else {
                assert false : "impossible to determine a direction, something is wrong with the provided list of allPoints!";
                direction = Direction.NONE;
            }
            fixNoBendpointsLink(allPoints, lastLocation, targetLocation, direction, targetAnchorOrientation);
        }
        
    }

    @objid ("f0d0b584-ea72-4479-8636-247de661a10f")
    public boolean areAncestors(IFigure f1, IFigure f2) {
        return f1 != null && f2 != null && (f1.equals(f2) || FigureUtilities.isAncestor(f1, f2));
    }

}
