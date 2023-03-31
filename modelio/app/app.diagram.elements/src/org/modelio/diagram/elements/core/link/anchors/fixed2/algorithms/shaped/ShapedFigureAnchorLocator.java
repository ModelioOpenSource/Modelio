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
package org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.shaped;

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
import org.modelio.diagram.elements.core.figures.anchors.FacesConstants;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;
import org.modelio.diagram.elements.core.figures.geometry.LineSeg;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.wrapped.WrappedFixedAnchorLocator;

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
@objid ("40e77989-8b3b-4a6c-bcea-305edbb18efe")
@Deprecated
public class ShapedFigureAnchorLocator extends WrappedFixedAnchorLocator {
    /**
     * The node figure shape flattened to a point list.
     */
    @objid ("a400fe4e-a6f7-4d86-939e-30646d4329d6")
    private PointList flattened;

    @objid ("8a35d970-1f14-47df-a45a-b911fa65d039")
    private final Rectangle prevBounds = new Rectangle();

    @objid ("c39dff4f-42a3-4b65-8e22-cebfd3778c19")
    private static final Point P1 = new PrecisionPoint();

    @objid ("595fd457-d1db-423d-a8f0-30df6d29d65f")
    private static final Point P2 = new PrecisionPoint();

    @objid ("de477f05-0a65-4ef9-ae7a-0ca3b8582576")
    private static final Point P3 = new PrecisionPoint();

    @objid ("c3261855-c3c8-4dc5-b4ad-c398ec8a285b")
    private static final Point P4 = new PrecisionPoint();

    @objid ("dcd5ba7e-5a11-416e-8fe3-a283a1a18d40")
    public  ShapedFigureAnchorLocator(IFixedAnchorLocator delegate) {
        super(delegate);
    }

    @objid ("7fb3ff0b-e730-4648-a00a-ded77efddbb0")
    protected final PointList getFlattened(ShapedFigure shapedFigure) {
        if (this.flattened == null || !this.prevBounds.equals(shapedFigure.getBounds())) {
            this.prevBounds.setBounds(shapedFigure.getBounds());
            this.flattened = computeFlattened(shapedFigure);
        }
        return this.flattened;
    }

    @objid ("2265f72d-0bc2-423c-9e99-ec965d2251fb")
    protected PointList computeFlattened(ShapedFigure shapedFigure) {
        // Get the figure path
        Path orig = computeFigurePath(shapedFigure);
        
        // Make a flattened copy, copy it in a PathData and dispose the copy.
        Path flattenedPath = new Path(orig.getDevice(), orig, 5);
        PathData data = flattenedPath.getPathData();
        flattenedPath.dispose();
        orig.dispose();
        
        // Convert the flattened PathData to a PointList
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

    @objid ("f5d7dd49-915f-4ebb-b2a3-9d526196c3af")
    private ShapedFigure getShapedFigure(FixedAnchor anchor) {
        return (ShapedFigure) anchor.getOwner();
    }

    @objid ("17a57705-c818-486d-ac88-43f6393870cb")
    protected Path computeFigurePath(ShapedFigure fig) {
        Rectangle bounds = fig.getBounds().getCopy();
        return fig.getShaper().createShapePath(bounds);
    }

    @objid ("1e0d7840-683d-4e61-be05-6a74700b2b2f")
    @Override
    public void onFigureMoved(IFigure figure) {
        this.flattened = null;
        super.onFigureMoved(figure);
        
    }

    @objid ("3ad92942-1e69-441b-be3e-ef111f4d4e8a")
    @Override
    public Point getReferencePoint(FixedAnchor anchor) {
        Point referencePoint = super.getReferencePoint(anchor);
        return getProjectedPoint(anchor, referencePoint);
    }

    @objid ("47e85316-105e-4107-9c38-6f30ec943c0c")
    @Override
    public Point getLocation(FixedAnchor anchor, Point reference) {
        Point delegateLoc = super.getLocation(anchor, reference);
        return getProjectedPoint(anchor, delegateLoc);
    }

    @objid ("9a0fd69b-a293-41e8-b6f9-8087244c6510")
    private Point getProjectedPoint(FixedAnchor anchor, Point referencePoint) {
        Point p1 = P1.setLocation(referencePoint);
        anchor.getOwner().translateToRelative(p1);
        Point p2 = P2.setLocation(p1);
        
        // put p2 farther from node
        switch (anchor.getFace()) {
        case FacesConstants.FACE_NORTH:
            p2.translate(0, -10);
            break;
        case FacesConstants.FACE_EAST:
            p2.translate(10, 0);
            //return referencePoint;
            break;
        case FacesConstants.FACE_SOUTH:
            p2.translate(0, 10);
            break;
        case FacesConstants.FACE_WEST:
        default:
            p2.translate(-10, 0);
            //return referencePoint;
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
