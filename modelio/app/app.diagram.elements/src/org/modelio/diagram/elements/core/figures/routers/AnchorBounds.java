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
package org.modelio.diagram.elements.core.figures.routers;

import java.util.List;
import java.util.function.Function;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Translatable;
import org.eclipse.gef.handles.HandleBounds;
import org.modelio.diagram.elements.core.figures.anchors.DirectionalAnchor;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;

/**
 * Holder for both connection source and target figures bounds.
 * @since 5.0.2
 */
@objid ("2a183de1-d064-407d-9561-c56862fdb463")
public final class AnchorBounds implements Translatable {
    /**
     * A shared instance of {@link AnchorBounds} to be used for short computations.
     * <p>
     * Use {@link AnchorBounds#fromConnectionAbs(Connection)} to initialize it.
     */
    @objid ("fe988ea8-07c5-4f2a-854a-bacdc84e353f")
    public static final AnchorBounds SHARED = new AnchorBounds();

    /**
     * The source node bounds
     */
    @objid ("0d70a224-0815-4cb4-ad1a-58184de80353")
    public final PrecisionRectangle source = new PrecisionRectangle();

    /**
     * The target node bounds
     */
    @objid ("fd7cc519-e1dc-4ae4-9394-617cc100a4d6")
    public final PrecisionRectangle target = new PrecisionRectangle();

    /**
     * Get the anchor owner (handle)bounds in absolute coordinates.
     * <p>
     * If the anchor is not attached to a figure or attached to a {@link Connection},
     * returns a a 3x3 sized rectangle centered at the anchor reference point.
     * @param out the rectangle to fill with the node bounds. a PrecisionRectangle is needed to avoid precision loss on lower zooms levels.
     * @param anchor The anchor.
     * @return out for convenience. Contains the anchor owner bounds.
     */
    @objid ("d8a4f38b-c119-4a38-bf7f-b6f775b4deb6")
    public static Rectangle getAnchorOwnerAbsoluteBounds(PrecisionRectangle out, ConnectionAnchor anchor) {
        return getAnchorOwnerAbsoluteBounds(out, anchor, anchor.getReferencePoint());
    }

    /**
     * Get the anchor owner (handle)bounds in absolute coordinates.
     * <p>
     * If the anchor is not attached to a figure or attached to a {@link Connection},
     * returns a 3x3 sized rectangle centered at the anchor location computed with refPoint.
     * @param out the rectangle to fill with the node bounds. a PrecisionRectangle is needed to avoid precision loss on lower zooms levels.
     * @param anchor The anchor.
     * @param refPoint a reference point in absolute coordinates to call {@link ConnectionAnchor#getLocation(Point)} when anchor is not attached to a node figure.
     * @return out for convenience. Contains the anchor owner bounds.
     */
    @objid ("2d12539f-9857-406a-9736-ceef74096aad")
    public static Rectangle getAnchorOwnerAbsoluteBounds(PrecisionRectangle out, ConnectionAnchor anchor, Point refPoint) {
        final IFigure f = anchor.getOwner();
        if (f == null || f instanceof Connection) {
            Point p = anchor.getLocation(refPoint);
            if (anchor instanceof DirectionalAnchor) {
                switch (((DirectionalAnchor) anchor).getDirection()) {
                case NORTH:
                    return out.setPreciseBounds(p.preciseX() - 1, p.preciseY(), 3, 3);
                case SOUTH:
                    return out.setPreciseBounds(p.preciseX() - 1, p.preciseY() - 3, 3, 3);
                case EAST:
                    return out.setPreciseBounds(p.preciseX() - 3, p.preciseY() - 1, 3, 3);
                case WEST:
                    return out.setPreciseBounds(p.preciseX(), p.preciseY() - 1, 3, 3);
                default:
                    break;
                }
            }
            return out.setPreciseBounds(p.preciseX()-1, p.preciseY()-1, 3, 3);
        } else {
            Rectangle usedBounds = f instanceof HandleBounds
                    ? ((HandleBounds) f).getHandleBounds()
                    : f.getBounds();
        
            out.setBounds(usedBounds);
        
            f.translateToAbsolute(out);
            return out;
        }
        
    }

    /**
     * Initialize source and target bounds from the given anchors.
     * <p>
     * The bounds will be in absolute coordinates.
     * @param srcAnchor the source anchor
     * @param targetAnchor the target anchor
     * @return this instance to chain calls
     */
    @objid ("55c66152-8ec8-444f-ad3e-e1baedabf16f")
    public AnchorBounds fromAnchors(ConnectionAnchor srcAnchor, ConnectionAnchor targetAnchor) {
        getAnchorOwnerAbsoluteBounds(this.source, srcAnchor);
        getAnchorOwnerAbsoluteBounds(this.target, targetAnchor);
        return this;
    }

    /**
     * Initialize source and target bounds from the given anchors.
     * <p>
     * The bounds will be in absolute coordinates.
     * @param srcAnchor the source anchor
     * @param srcRef a source reference point in absolute coordinates to call {@link ConnectionAnchor#getLocation(Point)}.
     * @param targetAnchor the target anchor
     * @param targetRef a target reference point in absolute coordinates to call {@link ConnectionAnchor#getLocation(Point)}.
     * @return this instance to chain calls
     */
    @objid ("adeea870-1142-4b6b-ab2e-daad035f2742")
    public AnchorBounds fromAnchors(ConnectionAnchor srcAnchor, Point srcRef, ConnectionAnchor targetAnchor, Point targetRef) {
        getAnchorOwnerAbsoluteBounds(this.source, srcAnchor, srcRef);
        getAnchorOwnerAbsoluteBounds(this.target, targetAnchor, targetRef);
        return this;
    }

    /**
     * Initialize source and target bounds from the given connection.
     * <p>
     * The bounds will be in absolute coordinates.
     * @param c the connection to use
     * @return this instance to chain calls
     */
    @objid ("510eeb95-71cf-4fb9-877d-b75218c28414")
    public AnchorBounds fromConnectionAbs(Connection c) {
        return fromAnchors(c.getSourceAnchor(), c.getTargetAnchor());
    }

    /**
     * Initialize source and target bounds from the given node figures.
     * <p>
     * The bounds will be in absolute coordinates.
     * @param srcNode the source node figure
     * @param targetNode the target node figure
     * @return this instance to chain calls
     */
    @objid ("10612307-f354-4c11-ba65-b650992531ca")
    public AnchorBounds fromNodes(IFigure srcNode, IFigure targetNode) {
        this.source.setBounds(srcNode.getBounds());
        srcNode.translateToAbsolute(this.source);
        this.target.setBounds(targetNode.getBounds());
        targetNode.translateToAbsolute(this.target);
        return this;
    }

    /**
     * Copy constructor from another {@link AnchorBounds}.
     * <p>
     * The bounds will be in same coordinates as the other.
     * @param other the object to copy
     * @return this instance to chain calls
     */
    @objid ("8cdc81fa-5c11-430f-abb9-aa24a1b9b958")
    public AnchorBounds fromOther(AnchorBounds other) {
        this.source.setBounds(other.source);
        this.target.setBounds(other.target);
        return this;
    }

    /**
     * Convert both bounds to coordinates relative to the given figure.
     * @param c the figure to use convert coordinates
     * @return this instance
     */
    @objid ("8a35b629-f651-4a88-b94c-745b260ef060")
    public AnchorBounds toRelative(IFigure c) {
        c.translateToRelative(this);
        return this;
    }

    /**
     * Convert both bounds to coordinates relative to the given figure.
     * @param c the figure to use convert coordinates
     * @return this instance
     */
    @objid ("4a66d06f-928a-4444-9bcb-ba499be1bf51")
    public AnchorBounds toAbsolute(IFigure c) {
        c.translateToAbsolute(this);
        return this;
    }

    @objid ("3ad097ed-ab52-4f83-ad6a-8875683349f5")
    @Override
    public String toString() {
        return String.format(
                "AnchorBounds [source=%s, target=%s]",
                GeomUtils.toString(this.source),
                GeomUtils.toString(this.target));
        
    }

    /**
     * Expands both bounds by the given delta.
     * <p>
     * The bounds center will stay the same
     * @see Rectangle#expand(int, int)
     * @param delta the expansio delta
     * @return this instance
     */
    @objid ("e3b85764-4bd7-459f-b2b0-910cb51297a0")
    public AnchorBounds expand(int delta) {
        this.source.expand(delta, delta);
        this.target.expand(delta, delta);
        return this;
    }

    @objid ("c8c5fe24-e458-4a1a-8fb3-fd39a1cf127e")
    @Override
    public void performTranslate(int dx, int dy) {
        this.source.performTranslate(dx, dy);
        this.target.performTranslate(dx, dy);
        
    }

    @objid ("dd6aa845-8d36-4640-9355-381ca9287764")
    @Override
    public void performScale(double factor) {
        this.target.performScale(factor);
        this.source.performScale(factor);
        
    }

    /**
     * Remove from the given points list first at last points that are contained in either the source or target bounds.
     * @param <T> the type of the elements in the list.
     * @param bendpoints the list to trim
     * @param andTrimFirstLast if true, always remove first point unless link is reflexive, and removes the last point if any
     */
    @objid ("cd0946de-173f-4d3f-94ad-3ad11cc5357a")
    public <T extends Point> void trimContainedPoints(List<T> bendpoints, boolean andTrimFirstLast) {
        trimContainedPoints(bendpoints, t -> t, andTrimFirstLast);
    }

    /**
     * Remove from the given points list first at last points that are contained in either the source or target bounds.
     * @param <T> the type of the elements in the list.
     * @param bendpoints the list to trim
     * @param locGetter a function to convert a T to a {@link Point}
     * @param andTrimFirstLast if true, always remove first point unless link is reflexive, and removes the last point if any
     */
    @objid ("272d01c9-a040-4863-9431-25aaf4ea0efa")
    public <T> void trimContainedPoints(List<T> bendpoints, Function<T, Point> locGetter, boolean andTrimFirstLast) {
        if (!this.source.contains(this.target)) {
            // Remove from the beginning of the list all points until the first outside the source bounds.
            while (!bendpoints.isEmpty() && this.source.contains(locGetter.apply(bendpoints.get(0)))) {
                bendpoints.remove(0);
            }
        } else if (andTrimFirstLast && !this.source.equals(this.target)) {
            // Remove the first point unless the link is reflexive
            bendpoints.remove(0);
        }
        
        if (!this.target.contains(this.source)) {
            // Remove from the end of the list all points until the first outside the target bounds.
            for (int lastIdx = bendpoints.size() - 1; lastIdx >= 0 && this.target.contains(locGetter.apply(bendpoints.get(lastIdx))); lastIdx--) {
                bendpoints.remove(lastIdx);
            }
        } else if (andTrimFirstLast && !bendpoints.isEmpty()) {
            bendpoints.remove(0);
        }
        
    }

    /**
     * Remove from the given points list first at last points that are contained in either the source or target bounds.
     * @param bendpoints the list to trim
     * @param andTrimFirstLast if true, always remove first point unless link is reflexive, and removes the last point if any
     */
    @objid ("1317a15d-636a-489a-9e5d-a2edfffe4d67")
    public void trimContainedBendPoints(List<? extends Bendpoint> bendpoints, boolean andTrimFirstLast) {
        trimContainedPoints(bendpoints, Bendpoint::getLocation, andTrimFirstLast);
    }

}
