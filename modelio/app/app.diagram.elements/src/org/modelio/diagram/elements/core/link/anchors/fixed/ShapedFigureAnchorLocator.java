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
import org.modelio.diagram.elements.core.figures.anchors.FacesConstants;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;
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
@objid ("eb9e22fd-ec57-44f8-b100-d275044c920f")
@Deprecated
class ShapedFigureAnchorLocator extends WrappedFixedAnchorLocator {
    /**
     * The node figure shape flattened to a point list.
     */
    @objid ("dd453f1d-d3bc-4654-abd5-b419507da273")
    private PointList flattened;

    @objid ("48542ecb-3f50-4202-b20a-7f10e761fed5")
    private final Rectangle prevBounds = new Rectangle();

    @objid ("d3f7957c-a543-4657-933c-c2004af9dd4e")
    private static final Point P1 = new PrecisionPoint();

    @objid ("c02a48d6-2c96-4d04-bd3a-823ce378eee2")
    private static final Point P2 = new PrecisionPoint();

    @objid ("288ccb4c-c779-4667-94de-2052cf315549")
    private static final Point P3 = new PrecisionPoint();

    @objid ("e8eec474-8634-42aa-b753-ad0a9d9074e0")
    private static final Point P4 = new PrecisionPoint();

    @objid ("8aee557f-b22f-4192-9f56-9b638ec42338")
    public  ShapedFigureAnchorLocator(IFixedAnchorLocator delegate) {
        super(delegate);
    }

    @objid ("b3babe9c-584b-4265-99ae-ea55c5e319a5")
    protected final PointList getFlattened(ShapedFigure shapedFigure) {
        if (this.flattened == null || !this.prevBounds.equals(shapedFigure.getBounds())) {
            this.prevBounds.setBounds(shapedFigure.getBounds());
            this.flattened = computeFlattened(shapedFigure);
        }
        return this.flattened;
    }

    @objid ("857526e8-f3af-4d2b-828f-4f0a8dd7c80f")
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

    @objid ("c1117790-2239-444a-9ebb-e7237bc5f22d")
    private ShapedFigure getShapedFigure(FixedAnchor anchor) {
        return (ShapedFigure) anchor.getOwner();
    }

    @objid ("33b08675-9d0c-4e34-b4e7-4067785d279f")
    protected Path computeFigurePath(ShapedFigure fig) {
        Rectangle bounds = fig.getBounds().getCopy();
        return fig.getShaper().createShapePath(bounds);
    }

    @objid ("960c7dfb-a754-43dc-a5cb-616daa284ec5")
    @Override
    public void onFigureMoved(IFigure figure) {
        this.flattened = null;
        super.onFigureMoved(figure);
        
    }

    @objid ("f0b6b911-b3de-4b4e-9e96-3a39f010fa50")
    @Override
    public Point getReferencePoint(FixedAnchor anchor) {
        Point referencePoint = super.getReferencePoint(anchor);
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
            break;
        case FacesConstants.FACE_SOUTH:
            p2.translate(0, 10);
            break;
        case FacesConstants.FACE_WEST:
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
