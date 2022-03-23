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
package org.modelio.diagram.elements.core.figures.rotated;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Translatable;
import org.modelio.diagram.elements.core.figures.ChainedLayout;

/**
 * This is a rotatable IFigure container .
 * <p>
 * This figure allows only one child figure that will be rotated by the given angle.
 * <p>
 * <h3>Implementation:</h3>
 * <li>It can be viewed as a rectangle containing a rotated figure that
 * take the maximum place.
 * 
 * @since Modelio 3.4
 */
@objid ("f489f6c9-e01e-468f-8613-339e0b8c6aa1")
public class RotatedFigureContainer extends Figure implements PositionConstants {
    @objid ("0a47947a-c9cb-4df7-8d6c-e11cdb1cf7a7")
    private static boolean DEBUG = false;

    /**
     * rotation angle in degrees.
     * <p>
     * Is guaranteed to be between 0 and 359.
     */
    @objid ("850fa97f-ea1e-497a-88ee-3a41ccd0606f")
    private int orientation;

    /**
     * The inner rotated rectangle coordinates and size (not bounds).
     * <p>
     * width and height are the rotated rectangle size, not bounds size.
     */
    @objid ("2fe6577f-af03-4b3e-912a-550ac3d57ca3")
    private PrecisionRectangle innerRectangle;

    /**
     * Creates an empty label figure oriented horizontally.
     */
    @objid ("9b375609-3d5a-49f2-bb37-d339fcd22016")
    public  RotatedFigureContainer() {
        init();
    }

    /**
     * initialize the figure with a child and a rotation angle.
     * @param f the contained figure
     * @param angle the rotation angle in counter clockwise degrees.
     */
    @objid ("85526176-07d1-4a28-ad9f-859c4755982a")
    public  RotatedFigureContainer(IFigure f, int angle) {
        add(f);
        setOrientationAngle(angle);
        init();
        
    }

    @objid ("fa506e9b-bfa8-480f-a5c1-99b4c4e7688f")
    @Override
    public void add(IFigure figure, Object constraint, int index) {
        if (!getChildren().isEmpty()) {
            throw new IllegalStateException("Only one child allowed.");
        }
        
        super.add(figure, constraint, index);
        
    }

    /**
     * Get the contained figure.
     * @return the contained figure.
     */
    @objid ("ae659e59-00d6-48bc-a9af-3de7e9fc79ab")
    public final IFigure getChild() {
        if (getChildren().isEmpty()) {
            return null;
        } else {
            return (IFigure) getChildren().get(0);
        }
        
    }

    /**
     * Get the orientation angle in counter-clockwise degrees.
     * @return the orientation angle in degrees. 0 means horizontal.
     */
    @objid ("ba1fde2d-8ea7-4a89-8f72-bd2ae1d98900")
    public int getOrientation() {
        return this.orientation;
    }

    @objid ("3c8bf956-3e65-4232-9816-6e2548ad9026")
    @Override
    public void invalidate() {
        super.invalidate();
        
        this.innerRectangle = null;
        
    }

    @objid ("d441080e-1677-47eb-b09a-9803e4f9ebea")
    @Override
    public void setLayoutManager(LayoutManager manager) {
        assert (checkLayoutManager(manager));
        
        super.setLayoutManager(manager);
        
    }

    /**
     * Rotates the label by the given counter-clockwise angle.
     * <p>
     * The angle is specified in degrees and for the identity transform 0 degrees is
     * at the 3 o'clock position.
     * A positive value indicates a counter-clockwise rotation while a negative value
     * indicates a clockwise rotation.
     * </p>
     * @param orientation the orientation in degrees.
     */
    @objid ("9c9f7fcc-e63d-49c8-9a35-f90cb79ee048")
    public void setOrientationAngle(int orientation) {
        this.orientation = orientation % 360;
        if (this.orientation < 0) {
            this.orientation = 360 + this.orientation;
        }
        
        revalidate();
        
    }

    /**
     * Set the label orientation using one of the {@link PositionConstants} constants.
     * <p>
     * {@link PositionConstants#EAST} is the default horizontal direction,
     * {@link PositionConstants#NORTH} is the default vertical direction.
     * </p>
     * @param orientation the label orientation
     */
    @objid ("51a16d0a-0c17-43e9-ad64-afac60677a03")
    public void setOrientationDirection(int orientation) {
        switch (orientation) {
        case PositionConstants.NORTH:
            setOrientationAngle(90);
            break;
        case PositionConstants.NORTH_EAST:
            setOrientationAngle(45);
            break;
        case PositionConstants.NORTH_WEST:
            setOrientationAngle(90 + 45);
            break;
        case PositionConstants.EAST:
            setOrientationAngle(0);
            break;
        case PositionConstants.SOUTH:
            setOrientationAngle(-90);
            break;
        case PositionConstants.SOUTH_EAST:
            setOrientationAngle(-45);
            break;
        case PositionConstants.SOUTH_WEST:
            setOrientationAngle(-90 - 45);
            break;
        case PositionConstants.WEST:
            setOrientationAngle(180);
            break;
        default:
            throw new IllegalArgumentException(String.valueOf(orientation));
        }
        
    }

    @objid ("ca773a5c-6341-47a4-8a0a-15f3d57f159e")
    @Override
    public void translateFromParent(Translatable t) {
        PrecisionRectangle ir = getInnerRectangle();
        
        double rad = Math.toRadians(this.orientation);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);
        
        if (t instanceof Point) {
            t.performTranslate(-ir.x() - getBounds().x - getInsets().left, -ir.y() - getBounds().y - getInsets().top);
        
            Point p = (Point) t;
        
            double x = p.x() * cos - p.y() * sin;
            double y = p.x() * sin + p.y() * cos;
        
            p.setX((int) Math.round(x));
            p.setY((int) Math.round(y));
        } else if (t instanceof Rectangle) {
            // Rotate the 4 rectangle corners and calculate a bounding box
            Rectangle r = (Rectangle) t;
        
            Point p1 = r.getTopLeft();
            translateFromParent(p1);
            Point p2 = r.getTopRight();
            translateFromParent(p2);
            Point p3 = r.getBottomLeft();
            translateFromParent(p3);
            Point p4 = r.getBottomRight();
            translateFromParent(p4);
        
            int x1 = Math.min(Math.min(p1.x, p2.x), Math.min(p3.x, p4.x));
            int y1 = Math.min(Math.min(p1.y, p2.y), Math.min(p3.y, p4.y));
        
            int x2 = Math.max(Math.max(p1.x, p2.x), Math.max(p3.x, p4.x));
            int y2 = Math.max(Math.max(p1.y, p2.y), Math.max(p3.y, p4.y));
        
            r.setBounds(x1, y1, x2 - x1, y2 - y1);
        
        } else if (t instanceof Dimension) {
            Dimension d = (Dimension) t;
            d.setSize(calculateRotatedRectangleBounds(d));
        }
        
    }

    @objid ("a2ca1a65-61b6-4553-b51f-05763fcbf4bf")
    @Override
    public void translateToParent(Translatable t) {
        if (t instanceof Point) {
            PrecisionRectangle ir = getInnerRectangle();
            Rectangle containerBounds = getBounds();
        
            double rad = Math.toRadians(this.orientation);
            double cos = Math.cos(-rad);
            double sin = Math.sin(-rad);
        
            Point p = (Point) t;
        
            double x = ir.preciseX() + p.preciseX() * cos - p.preciseY() * sin;
            double y = ir.preciseY() + p.preciseX() * sin + p.preciseY() * cos;
        
            p.setX((int) Math.round(x));
            p.setY((int) Math.round(y));
        
            t.performTranslate(containerBounds.x + getInsets().left, containerBounds.y + getInsets().top);
        } else if (t instanceof Rectangle) {
            // Rotate the 4 rectangle corners and calculate a bounding box
            Rectangle r = (Rectangle) t;
        
            Point p1 = r.getTopLeft();
            translateToParent(p1);
            Point p2 = r.getTopRight();
            translateToParent(p2);
            Point p3 = r.getBottomLeft();
            translateToParent(p3);
            Point p4 = r.getBottomRight();
            translateToParent(p4);
        
            int x1 = Math.min(Math.min(p1.x, p2.x), Math.min(p3.x, p4.x));
            int y1 = Math.min(Math.min(p1.y, p2.y), Math.min(p3.y, p4.y));
        
            int x2 = Math.max(Math.max(p1.x, p2.x), Math.max(p3.x, p4.x));
            int y2 = Math.max(Math.max(p1.y, p2.y), Math.max(p3.y, p4.y));
        
            r.setBounds(x1, y1, x2 - x1, y2 - y1);
        } else if (t instanceof Dimension) {
            Dimension d = (Dimension) t;
            // XXX Hack : temporary inverse the direction. This won't work if cos and sin values are cached in the future
            this.orientation = -this.orientation;
            d.setSize(calculateRotatedRectangleBounds(d));
            this.orientation = -this.orientation;
        }
        
    }

    /**
     * Called by the layout manager.
     * @param w The width hint
     * @param h The height hint
     * @return The minimum size
     */
    @objid ("e484b2df-4d86-4d33-bac9-48f5efafc5aa")
    protected Dimension calculateMinimumSize(int w, int h) {
        Dimension availChildSize;
        if (w == -1 && h == -1) {
            availChildSize = new Dimension(-1, -1); // unlimited size
        } else {
            availChildSize = (calculateMaxRotatedRectangleSize(w, h));
        }
        
        // constraint child into availChildSize, and compute its size
        Dimension ttDim = getChildPreferredSize(availChildSize.width(), availChildSize.height());
        
        // Compute needed bounds for child
        Dimension lMinSize = calculateRotatedRectangleBounds(ttDim);
        
        // Add insets
        Insets insets = getInsets();
        lMinSize.expand(insets.getWidth(), insets.getHeight());
        return lMinSize;
    }

    /**
     * Called by the layout manager.
     * @param wHint The width hint
     * @param hHint The height hint
     * @return The preferred size
     */
    @objid ("d6bab940-dc50-468f-928b-27aad8af6fbf")
    protected Dimension calculatePreferredSize(int wHint, int hHint) {
        Dimension lPrefSize = calculateRotatedRectangleBounds(getChild().getPreferredSize());
        Insets insets = getInsets();
        lPrefSize.expand(insets.getWidth(), insets.getHeight());
        
        if ((wHint >= 0 && wHint < lPrefSize.width) || (hHint >= 0 && hHint < lPrefSize.height)) {
            // Default preferred size is too big for given hints,
            // calculate minimum size for them.
        
            Dimension lminSize = getMinimumSize(wHint, hHint);
            Dimension result = lminSize.getCopy();
        
            if (wHint >= 0) {
                result.width = Math.min(result.width, wHint);
            }
        
            if (hHint >= 0) {
                result.height = Math.min(result.height, hHint);
            }
        
            // result.width = Math.max(lminSize.width, result.width);
            // result.height = Math.max(lminSize.height, result.height);
            return result;
        }
        return lPrefSize;
    }

    @objid ("00083bb2-281b-4dfa-b908-6b2e5835542f")
    protected Dimension getChildMinimumSize(int wHint, int hHint) {
        IFigure child = getChild();
        
        if (child == null) {
            return new Dimension(0, 0);
        } else {
            return child.getMinimumSize(wHint, hHint);
        }
        
    }

    /**
     * Returns the preferred size for the child figure using the provided width and height hints.
     * <p>
     * The returned dimension may be by <i>reference</i>, and it must not be modified by the caller.
     * A hint value of -1 indicates that there is no constraint in that direction.
     * @param wHint a width hint
     * @param hHint a height hint
     * @return The preferred size
     */
    @objid ("764f808e-bd42-4c1b-a1b8-f373e179e0d1")
    protected Dimension getChildPreferredSize(int wHint, int hHint) {
        IFigure child = getChild();
        
        if (child == null) {
            return new Dimension(0, 0);
        } else {
            return child.getPreferredSize(wHint, hHint);
        }
        
    }

    /**
     * Returns the size of the child figure or empty Dimension.
     */
    @objid ("634fa51b-098e-4ce0-86e1-ac131fc1da06")
    protected Dimension getChildSize() {
        IFigure child = getChild();
        
        if (child == null) {
            return new Dimension(0, 0);
        } else {
            return child.getSize();
        }
        
    }

    @objid ("ec257de0-b173-4297-99fb-55e8fb5179c7")
    public PrecisionRectangle getInnerRectangle() {
        if (this.innerRectangle == null) {
            PrecisionRectangle r = new PrecisionRectangle();
            Dimension size = getSize();
            r.setSize(calculateMaxRotatedRectangleSize(size.width, size.height));
        
            this.innerRectangle = r;
            double innerW = this.innerRectangle.preciseWidth();
            double innerH = this.innerRectangle.preciseHeight();
            double sinr = Math.sin(Math.toRadians(this.orientation));
            double cosr = Math.cos(Math.toRadians(this.orientation));
        
            // Translate the rotated inner rectangle so that it fits inside the figure bounds.
            if (this.orientation <= 90) {
                // final double dy = size.preciseHeight() / 2;
                final double dy = this.innerRectangle.width() * sinr;
                this.innerRectangle.translate(0.0, dy);
            } else if (this.orientation > 90 && this.orientation <= 180) {
                // double dx = - size.width() / 2.0;
                double dx = -innerW * cosr;
                double dy = innerW * sinr - innerH * cosr;
                this.innerRectangle.translate(dx, dy /* getSize().height */);
            } else if (this.orientation > 180 && this.orientation <= 270) {
                double dx = -innerW * cosr - innerH * sinr;
                double dy = -innerH * cosr;
                this.innerRectangle.translate(dx, dy);
            } else {
                double dx = -innerH * sinr;
                final double dy = 0;
                this.innerRectangle.translate(dx, dy);
            }
        }
        return this.innerRectangle;
    }

    @objid ("c836593e-7567-42b7-8f05-1de95f730e44")
    @Override
    protected void layout() {
        PrecisionRectangle b = getInnerRectangle().getPreciseCopy();
        b.setPreciseLocation(0, 0);
        
        for (Object o : getChildren()) {
            IFigure f = (IFigure) o;
            f.setBounds(b);
        }
        
    }

    @objid ("4f2a3a68-872c-4957-9391-829b434ec20f")
    @Override
    protected void paintChildren(Graphics graphics) {
        Rectangle lbounds = getBounds();
        graphics.translate(lbounds.x, lbounds.y);
        
        Graphics childGraphics = graphics;
        
        if (this.orientation != 0) {
            if (RotatedFigureContainer.DEBUG) {
                // own bounds
                graphics.setAlpha(40);
                graphics.drawRectangle(1, 0, lbounds.width() - 1, lbounds.height() - 1);
                graphics.setAlpha(255);
            }
        
            final Rectangle innerRect = getInnerRectangle();
        
            graphics.translate((float) innerRect.preciseX(), (float) innerRect.preciseY());
            childGraphics = new RotatedGraphics(graphics, -this.orientation);
        
            if (RotatedFigureContainer.DEBUG) {
                // inner rectangle
                childGraphics.setAlpha(40);
                childGraphics.drawRectangle(0, 0, innerRect.width(), innerRect.height());
                childGraphics.setAlpha(255);
            }
        }
        
        for (Object o : getChildren()) {
            IFigure f = (IFigure) o;
        
            if (RotatedFigureContainer.DEBUG) {
                // figure bounds rotated
                childGraphics.setAlpha(100);
                childGraphics.drawRectangle(f.getBounds());
                childGraphics.setAlpha(255);
            }
        
            f.paint(childGraphics);
            childGraphics.restoreState();
        }
        
        if (this.orientation != 0) {
            childGraphics.dispose();
        }
        
    }

    /**
     * Calculate the max size of a rectangle inside the given rectangle size rotated by the labelum angle .
     * <p>
     * @param w rectangle width
     * @param h rectangle height
     * @return the inner rotated rectangle dimension
     */
    @objid ("d7ee3e2c-37f3-46e6-9456-cd7f844361f5")
    private PrecisionDimension calculateMaxRotatedRectangleSize(int w, int h) {
        if (this.orientation == 0 || this.orientation == 180) {
            // Horizontal
            return new PrecisionDimension(w, h);
        } else if (this.orientation == 90 || this.orientation == 270) {
            // Vertical
            return new PrecisionDimension(h, w);
        } else {
            return guessLargestRotatedTextSize(this.orientation, w, h);
        }
        
    }

    /**
     * Get the size of the given rectangle after rotation.
     * <p>
     * The returned dimension is always a copy and may be freely modified.
     * @param d the initial size. the size won't be modified.
     * @return the bounds of the rotated dimension
     */
    @objid ("554d44f9-04c3-4d7a-957c-4a66fc2230ff")
    private Dimension calculateRotatedRectangleBounds(Dimension d) {
        if (this.orientation == 0 || this.orientation == 180) {
            return d.getCopy();
        } else if (this.orientation == 90 || this.orientation == -90) {
            return d.getTransposed();
        } else {
            // http://stackoverflow.com/questions/3231176/how-to-get-size-of-a-rotated-rectangle/3234405#3234405
            double rad = Math.toRadians(this.orientation);
            double cos = Math.cos(rad);
            double sin = Math.sin(rad);
        
            double a = Math.abs(d.width * cos) + Math.abs(d.height * sin);
            double b = Math.abs(d.width * sin) + Math.abs(d.height * cos);
        
            return new Dimension((int) Math.ceil(a), (int) Math.ceil(b));
        }
        
    }

    /**
     * Guess the ideal child width for the given bounds dimensions.
     * @param angle the rotation angle in degrees
     * @param boundsWidth the constrained bounds width.
     * @param boundsHeight the constrained bounds height
     * @return the best text size.
     */
    @objid ("bdd346d7-d5af-4cbb-ad88-d39fa54978cf")
    private PrecisionDimension guessLargestRotatedTextSize(double angle, int boundsWidth, int boundsHeight) {
        PrecisionDimension ret = new PrecisionDimension(getChildPreferredSize(-1, -1));
        
        int boundsLimit = boundsWidth;
        boolean vertical = false;
        
        int quarter = (int) ((angle + 45) / 90);
        if (quarter == 1 || quarter == 3) {
            boundsLimit = boundsHeight;
            vertical = true;
        }
        
        Dimension curBounds = calculateRotatedRectangleBounds(ret);
        if (boundsLimit == -1 || curBounds.width() < boundsLimit) {
            // inside constraints
            return ret;
        }
        
        int wrapCur = ret.width();
        int wrapHigh = wrapCur;
        int wrapLow = 0;
        Dimension wrapDim = new Dimension(-1, -1);
        
        while (wrapHigh - wrapLow > 2) {
            wrapCur = (wrapHigh + wrapLow) / 2;
            wrapDim.setWidth(wrapCur);
        
            ret = new PrecisionDimension(getChildPreferredSize(wrapCur, -1));
            curBounds = calculateRotatedRectangleBounds(ret);
        
            if (curBounds.width() > boundsLimit) {
                // outside constraints
                wrapHigh = wrapCur;
            } else if (curBounds.width() < boundsLimit) {
                // inside constraints
                wrapLow = wrapCur;
            } else {
                // On the constraint, probably the better size
                return ret;
            }
        }
        
        // Crop the other side to other limits
        if (!vertical) {
            // the text is horizontal
            if (boundsHeight != -1 && curBounds.height() > boundsHeight) {
                double sin = Math.sin(Math.toRadians(angle));
                double cos = Math.cos(Math.toRadians(angle));
                ret.height = (int) ((boundsHeight - (ret.width * sin)) / cos);
            }
        } else {
            // the text is vertical
            if (boundsWidth != -1 && curBounds.width() > boundsWidth) {
                double sin = Math.sin(Math.toRadians(angle));
                double cos = Math.cos(Math.toRadians(angle));
                ret.width = (int) ((boundsWidth - (ret.height * sin)) / cos);
            }
        }
        return ret;
    }

    @objid ("b89d87e1-e4be-488f-ae5d-ec185acd107f")
    private void init() {
        setLayoutManager(new LM());
    }

    @objid ("a5fc121f-8099-49ea-b626-3c2ca75d3c98")
    private boolean checkLayoutManager(LayoutManager manager) {
        LayoutManager rootlayout = ChainedLayout.getRootLayout(manager);
        return (rootlayout instanceof LM);
    }

    /**
     * Custom {@link AbstractHintLayout} layout manager.
     */
    @objid ("cb53a1d1-1aee-4923-9871-28f79e8bb74e")
    protected static class LM extends AbstractHintLayout {
        @objid ("dc35e5ab-81ba-4b93-8ee9-1193cb905f11")
        @Override
        protected Dimension calculateMinimumSize(IFigure container, int wHint, int hHint) {
            return ((RotatedFigureContainer) container).calculateMinimumSize(wHint, hHint);
        }

        @objid ("c3270aae-2a3f-4f7b-a448-45f409272d89")
        @Override
        protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {
            return ((RotatedFigureContainer) container).calculatePreferredSize(wHint, hHint);
        }

        @objid ("f221c93e-db92-4d17-98bf-be5a48eb5f82")
        @Override
        public void layout(IFigure container) {
            // nothing to do
        }

    }

}
