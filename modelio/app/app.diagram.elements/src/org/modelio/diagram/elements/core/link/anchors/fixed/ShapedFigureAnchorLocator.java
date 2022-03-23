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
package org.modelio.diagram.elements.core.link.anchors.fixed;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.PathData;
import org.modelio.diagram.elements.core.figures.ShapedFigure;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.LineSeg;

/**
 * Fixed anchor locator for {@link ShapedFigure}.
 * <p>
 * It uses an initial rectangle based {@link IFixedAnchorLocator}, then
 * projects its anchor orthogonally on the  {@link ShapedFigure#getShaper() figure shape}.
 * 
 * @author cma
 * @since 5.1.0
 * @deprecated beta, Nearly works but still experimental. Needs connection editors to be aware of {@link org.modelio.diagram.elements.core.figures.anchors.IOrientedAnchor IOrientedAnchor}
 */
@objid ("92b2c41d-1823-47d2-9825-2e1b70862fec")
@Deprecated
public class ShapedFigureAnchorLocator implements IFixedAnchorLocator {
    @objid ("53166620-b6d0-4868-94b2-aeefba9b9afc")
    private final IFixedAnchorLocator delegate;

    @objid ("facdd3df-9ec1-427c-b70a-d69a4a948e6c")
    private PointList flattened;

    @objid ("be374b9d-be77-4e9b-8e0d-a501a9d9542a")
    private final Rectangle prevBounds = new Rectangle();

    @objid ("e80666d0-5d4c-4278-ab83-38caf62c12ca")
    private static final Point P1 = new PrecisionPoint();

    @objid ("00ac5026-6059-42e9-9cc1-3a34a761ca4d")
    private static final Point P2 = new PrecisionPoint();

    @objid ("d78a3915-4844-4ab2-bc03-7176786197d0")
    private static final Point P3 = new PrecisionPoint();

    @objid ("a3e45bb2-0467-4914-9095-837a4576b9e1")
    private static final Point P4 = new PrecisionPoint();

    @objid ("3e503729-7043-4aee-8d76-cfe763442566")
    public  ShapedFigureAnchorLocator(IFixedAnchorLocator delegate) {
        super();
        this.delegate = delegate;
        
    }

    @objid ("e4fde07a-8c65-4251-b4a0-8d7af1abbad3")
    protected PointList getFlattened(ShapedFigure shapedFigure) {
        if (this.flattened == null || !this.prevBounds.equals(shapedFigure.getBounds())) {
            this.prevBounds.setBounds(shapedFigure.getBounds());
            this.flattened = computeFlattened(shapedFigure);
        }
        return this.flattened;
    }

    @objid ("021d2be8-7806-48e7-910e-eb80c72ad3dc")
    protected PointList computeFlattened(ShapedFigure shapedFigure) {
        Path orig = computeFigurePath(shapedFigure);
        
        Path flattenedPath = new Path(orig.getDevice(), orig, 5);
        PathData data = flattenedPath.getPathData();
        flattenedPath.dispose();
        orig.dispose();
        
        byte[] types = data.types;
        float[] points = data.points;
        PointList ret = new PointList(types.length);
        PrecisionPoint allocFirst = new PrecisionPoint();
        PrecisionPoint first = null;
        PrecisionPoint p = new PrecisionPoint();
        
        for (int i = 0, j = 0; i < types.length; i++) {
            switch (types[i]) {
                case SWT.PATH_MOVE_TO:
                    p.setPreciseLocation(points[j++], points[j++]);
                    ret.addPoint(p);
                    if (first == null)
                        first = allocFirst.setPreciseLocation(p);
        
                    break;
                case SWT.PATH_LINE_TO:
                    p.setPreciseLocation(points[j++], points[j++]);
                    if (first == null)
                        first = allocFirst.setPreciseLocation(p);
                    ret.addPoint(p);
                    break;
                case SWT.PATH_CUBIC_TO:
                    //cubicTo(points[j++], points[j++], points[j++], points[j++], points[j++], points[j++]);
                    throw new IllegalStateException("SWT.PATH_CUBIC_TO");
                case SWT.PATH_QUAD_TO:
                    //quadTo(points[j++], points[j++], points[j++], points[j++]);
                    throw new IllegalStateException("SWT.PATH_QUAD_TO");
                case SWT.PATH_CLOSE:
                    if (first!=null)
                        ret.addPoint(first);
        
        
                    break;
                default:
                    throw new IllegalStateException(String.valueOf(types[i]));
            }
        }
        return ret;
    }

    @objid ("3dd100c5-c365-4948-b4d2-107351fcbf02")
    private ShapedFigure getShapedFigure(FixedAnchor anchor) {
        return (ShapedFigure) anchor.getOwner();
    }

    @objid ("df6872e6-dd7d-4c7e-9c62-701a5b321a2e")
    protected Path computeFigurePath(ShapedFigure fig) {
        Rectangle bounds = fig.getBounds().getCopy();
        return fig.getShaper().createShapePath(bounds);
    }

    @objid ("a4270deb-a9ca-417b-8578-b12c529c5c6c")
    @Override
    public void onFigureMoved(IFigure figure) {
        this.flattened = null;
        this.delegate.onFigureMoved(figure);
        
    }

    @objid ("33b37d1a-15c3-4c42-aa3a-953f955e6a73")
    @Override
    public Direction getDirection(FixedAnchor anchor) {
        return this.delegate.getDirection(anchor);
    }

    @objid ("054625c3-dd44-4579-b601-09ba255fa861")
    @Override
    public String getFaceName(FixedAnchor fixedAnchor) {
        return this.delegate.getFaceName(fixedAnchor);
    }

    @objid ("607ffa1e-de40-45c7-976c-dfba2144f6e4")
    @Override
    public Point getReferencePoint(FixedAnchor anchor) {
        Point referencePoint = this.delegate.getReferencePoint(anchor);
        Point p1 = P1.setLocation(referencePoint);
        anchor.getOwner().translateToRelative(p1);
        Point p2 = P2.setLocation(p1);
        
        // put p2 farther from node
        switch (anchor.getFace()) {
        case FixedNodeAnchorProvider.FACE_NORTH:
            p2.translate(0, -10);
            break;
        case FixedNodeAnchorProvider.FACE_EAST:
            p2.translate(10, 0);
            break;
        case FixedNodeAnchorProvider.FACE_SOUTH:
            p2.translate(0, 10);
            break;
        case FixedNodeAnchorProvider.FACE_WEST:
        default:
            p2.translate(-10, 0);
            break;
        }
        
        
        PointList flatPath = getFlattened(getShapedFigure(anchor));
        PointList intersections = new LineSeg(p1, p2).getLineIntersectionsWithLineSegs(flatPath);
        
        if (intersections.size() < 1)
            return referencePoint;
        
        int nearestIndex = -1;
        double nearestDist = Integer.MAX_VALUE;
        for (int i=0; i < intersections.size(); i++) {
            intersections.getPoint(P3, i);
            double curDist = P3.getDistance(p2);
            if (curDist < nearestDist) {
                nearestDist = curDist;
                nearestIndex = i;
            }
        }
        
        intersections.getPoint(P4, nearestIndex);
        anchor.getOwner().translateToAbsolute(P4);
        return P4;
    }

}
