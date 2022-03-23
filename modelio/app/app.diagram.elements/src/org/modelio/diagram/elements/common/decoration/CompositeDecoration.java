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
package org.modelio.diagram.elements.common.decoration;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.figures.LinkFigure;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * Decoration composed of the qualifier group and the arrow decoration.
 * 
 * @author cmarin
 */
@objid ("33e2c7df-55b7-11e2-877f-002564c97630")
public class CompositeDecoration extends Figure implements IPenOptionsSupport, RotatableDecoration {
    /**
     * Constant to set the container.
     */
    @objid ("f95508f0-ff8d-449d-b54b-fa82a768c4d4")
    public static final Integer CONTAINER = 1;

    /**
     * Constant to set the decoration.
     */
    @objid ("33d40e33-e68f-4b09-9298-81e84e2d70da")
    public static final Integer DECO = 2;

    @objid ("34ba75df-ccc4-4950-b66f-77e0e295d10a")
    protected IFigure qualifierGroup;

    @objid ("93e73508-8e65-4318-8e30-63b37bb948c5")
    protected RotatableDecoration decoration;

    /**
     * Location set by {@link #setLocation(Point)}
     */
    @objid ("25ccd162-8f4e-47c0-a39e-b671f8c97b38")
    private Point location = new Point();

    /**
     * Direction the arrow should be pointing FROM.
     */
    @objid ("36d67c61-fba4-4994-8181-6d279074b7e6")
    private Dimension rotationDirection = new Dimension();

    /**
     * Initialize the figure and its layout manager.
     */
    @objid ("d7b1a856-0ce5-4c3b-903c-232571e83887")
    public  CompositeDecoration() {
        setLayoutManager(new CompositeDecorationLayout());
    }

    @objid ("295e7dcd-6611-4d92-81ec-a797e48925bb")
    @Override
    public void setReferencePoint(final Point p) {
        Dimension newDir = p.getDifference(this.location);
        if (!newDir.equals(this.rotationDirection)) {
            this.rotationDirection = newDir;
            invalidate();
        }
        
    }

    @objid ("b02f3383-0d76-49e3-b9c7-54c8ffee58f8")
    @Override
    public void setLocation(final Point p) {
        if (!p.equals(this.location)) {
            this.location.setLocation(p);
            invalidate();
        }
        
    }

    /**
     * Get the location given to the last call to {@link #setLocation(Point)}.
     * @return this decoration location.
     */
    @objid ("f98d04f1-9fc9-4a62-b208-60580d75595f")
    public Point getDecorationLocation() {
        return this.location;
    }

    /**
     * Set the qualifier group
     * @param qualifierGroup the qualifier group
     */
    @objid ("a56ba33c-77b7-4c27-a6e2-6c0366ab263e")
    public void setQualifier(final IFigure qualifierGroup) {
        if (qualifierGroup == this.qualifierGroup)
            return;
        if (this.qualifierGroup != null)
            remove(this.qualifierGroup);
        
        this.qualifierGroup = qualifierGroup;
        
        if (qualifierGroup != null) {
            add(qualifierGroup, CompositeDecoration.CONTAINER, -1);
        }
        
    }

    /**
     * Set the arrow decoration.
     * @param deco the arrow decoration.
     */
    @objid ("519ecac8-f896-4cd4-a237-fb0cd1042e07")
    public void setDecoration(final RotatableDecoration deco) {
        if (this.decoration == deco)
            return;
        if (this.decoration != null)
            remove(this.decoration);
        
        this.decoration = deco;
        
        if (deco != null)
            add(deco, CompositeDecoration.DECO, -1);
        
    }

    @objid ("6692461f-b950-43ae-8089-c35532c61c49")
    @Override
    public Rectangle getBounds() {
        Rectangle ret = new Rectangle(this.location, this.location);
        for (Object o : getChildren()) {
            IFigure c = (IFigure) o;
            ret.union(c.getBounds());
        }
        return ret;
    }

    /**
     * Direction the arrow should be pointing FROM.
     * @return Direction the arrow should be pointing FROM.
     */
    @objid ("79eb9697-8b77-4732-8ccf-5cbc01a0a2bc")
    public Dimension getReferenceDirection() {
        return this.rotationDirection;
    }

    @objid ("5db5566f-d564-43b3-8e7a-8e1741ebc84b")
    @Override
    public Color getLineColor() {
        if (this.decoration instanceof IPenOptionsSupport)
            return ((IPenOptionsSupport) this.decoration).getLineColor();
        else
            return null;
        
    }

    @objid ("0a82c1e6-c496-4f83-a407-880e2878d2d6")
    @Override
    public LinePattern getLinePattern() {
        if (this.decoration instanceof IPenOptionsSupport)
            return ((IPenOptionsSupport) this.decoration).getLinePattern();
        else
            return null;
        
    }

    @objid ("ad2e5d14-e1e0-4391-9d14-81ffe25e6810")
    @Override
    public int getLineWidth() {
        if (this.decoration instanceof IPenOptionsSupport)
            return ((IPenOptionsSupport) this.decoration).getLineWidth();
        else
            return 0;
        
    }

    @objid ("13b2a7dd-f7d5-452d-abe6-fd5552307960")
    @Override
    public Color getTextColor() {
        if (this.decoration instanceof IPenOptionsSupport)
            return ((IPenOptionsSupport) this.decoration).getTextColor();
        else
            return null;
        
    }

    @objid ("211dedbe-cd98-4538-8341-813f3dfbc307")
    @Override
    public Font getTextFont() {
        if (this.decoration instanceof IPenOptionsSupport)
            return ((IPenOptionsSupport) this.decoration).getTextFont();
        else
            return null;
        
    }

    @objid ("c9586178-0187-4142-9240-e8b37d61e223")
    @Override
    public void setLineColor(final Color lineColor) {
        if (this.decoration instanceof IPenOptionsSupport)
            ((IPenOptionsSupport) this.decoration).setLineColor(lineColor);
        
    }

    @objid ("42fb98d0-c8e3-4471-87e9-675d8983e1b9")
    @Override
    public void setLinePattern(final LinePattern lineStyle) {
        if (this.decoration instanceof IPenOptionsSupport)
            ((IPenOptionsSupport) this.decoration).setLinePattern(lineStyle);
        
    }

    @objid ("9e6e0710-0937-463b-abd5-35fa507a55e8")
    @Override
    public void setLineWidth(final int lineWidth) {
        if (this.decoration instanceof IPenOptionsSupport)
            ((IPenOptionsSupport) this.decoration).setLineWidth(lineWidth);
        
    }

    @objid ("793a990a-8e6f-4ade-9958-7fd3ba74f978")
    @Override
    public void setTextColor(final Color textColor) {
        if (this.decoration instanceof IPenOptionsSupport)
            ((IPenOptionsSupport) this.decoration).setTextColor(textColor);
        
    }

    @objid ("d5daec79-b94f-427e-8c25-16f6b0557ac8")
    @Override
    public void setTextFont(final Font textFont) {
        if (this.decoration instanceof IPenOptionsSupport)
            ((IPenOptionsSupport) this.decoration).setTextFont(textFont);
        
    }

    @objid ("89bd7f36-ae30-478e-9c75-fe2c9c11ca2c")
    @Override
    public Color getBackgroundColor() {
        if (this.decoration != null)
            return this.decoration.getBackgroundColor();
        else
            return super.getBackgroundColor();
        
    }

    @objid ("ed914ae6-6f7f-4046-a5c8-821c0a941c78")
    @Override
    public void setBackgroundColor(final Color bg) {
        if (this.decoration != null)
            this.decoration.setBackgroundColor(bg);
        else
            super.setBackgroundColor(bg);
        
    }

    /**
     * Get the decoration part of the composite decoration.
     * @return the decoration part.
     */
    @objid ("9e0c643f-e971-487c-812a-84d2812d6a0e")
    public RotatableDecoration getDecoration() {
        return this.decoration;
    }

    @objid ("b8aa45d0-d2e0-4ea6-8e10-f449a80f1975")
    private class CompositeDecorationLayout extends AbstractLayout {
        @objid ("1262768f-7e90-4026-b85c-7a2853950735")
        public  CompositeDecorationLayout() {
            super();
        }

        @objid ("fc9769a6-15d6-4069-a8f0-e8dbb7de1c13")
        @SuppressWarnings("unqualified-field-access")
        @Override
        public Object getConstraint(final IFigure child) {
            if (child == qualifierGroup)
                return CONTAINER;
            else if (child == decoration)
                return DECO;
            else
                return null;
            
        }

        @objid ("d1f3d61b-751a-4bfa-adde-6f41ed6c7e8e")
        @Override
        protected Dimension calculatePreferredSize(final IFigure container, final int wHint, final int hHint) {
            Dimension ret = new Dimension();
            for (Object o : getChildren()) {
                IFigure c = (IFigure) o;
                ret.union(c.getMinimumSize());
            }
            
            ret.union(getBorderPreferredSize(container));
            return ret;
        }

        @objid ("74cde007-ad85-41f7-81ae-5200241c376f")
        @SuppressWarnings("unqualified-field-access")
        @Override
        public void layout(final IFigure container) {
            if (qualifierGroup == null)
                return;
            
            // Get side of the 'location' relative to the node
            IFigure node = getParentConnectionExtremity();
            Direction side;
            if (node == null) {
                Rectangle r = new Rectangle(getDecorationLocation().getTranslated(-1, -1),
                                            new Dimension(3, 3));
                side = GeomUtils.getDirection(getDecorationLocation().getTranslated(getReferenceDirection()),
                                              r);
            } else {
                Rectangle nodeBounds = getRelativeBounds(node);
                side = GeomUtils.getDirection(getDecorationLocation(), nodeBounds);
            }
            
            // Put the qualifier on the side
            Rectangle qualifierBounds = calcQualifierBounds(side);
            qualifierGroup.setBounds(qualifierBounds);
            
            // Move the decoration behind
            moveDecoration(qualifierBounds, side);
            
        }

        /**
         * Get the given node bounds in the container figure coordinates.
         * @param node a figure
         * @return the figure bounds in the container coordinates.
         */
        @objid ("875124cb-5159-4509-a5b9-40975742cf3d")
        private Rectangle getRelativeBounds(final IFigure node) {
            if (node == null) {
                Rectangle nodeBounds = new Rectangle(CompositeDecoration.this.location.x, CompositeDecoration.this.location.y, 0, 0);
                return nodeBounds;
            } else {
                Rectangle nodeBounds = node.getBounds().getCopy();
                node.translateToAbsolute(nodeBounds);
                translateToRelative(nodeBounds);
                return nodeBounds;
            }
            
        }

        /**
         * Get the connection extremity this decoration is sticked on.
         * @return a node figure.
         */
        @objid ("4b88dc76-033b-45d2-a135-70444c98d312")
        private IFigure getParentConnectionExtremity() {
            final LinkFigure conn = getParentConnection();
            if (conn.getSourceDecoration() == CompositeDecoration.this) {
                return conn.getSourceAnchor().getOwner();
            } else {
                return conn.getTargetAnchor().getOwner();
            }
            
        }

        @objid ("480006f1-5b89-4fdc-a351-328e0a9fef68")
        private LinkFigure getParentConnection() {
            return (LinkFigure) getParent();
        }

        /**
         * Place the decoration
         * @param r the qualifier bounds
         * @param side the side where to place the decoration
         */
        @objid ("c82e2a3e-2fea-4e9f-95bc-b368db359635")
        private void moveDecoration(final Rectangle r, final Direction side) {
            if (CompositeDecoration.this.decoration != null) {
                Point p = GeomUtils.getLineIntersection(getDecorationLocation(),
                                                        getDecorationLocation().getTranslated(getReferenceDirection().getScaled(10)),
                                                        r);
                if (p == null)
                    p = r.getTopLeft(); //getSideCenter(r, side);
            
                CompositeDecoration.this.decoration.setLocation(p);
                CompositeDecoration.this.decoration.setReferencePoint(p.getTranslated(getReferenceDirection()));
            }
            
        }

        @objid ("f2820066-d829-4084-b501-bf32bc6032e6")
        private Point getSideCenter(final Rectangle r, final Direction side) {
            switch (side) {
                case EAST:
                    return r.getRight();
                case NORTH:
                    return r.getTop();
                case SOUTH:
                    return r.getBottom();
                case WEST:
                    return r.getLeft();
                default:
                    throw new IllegalArgumentException(side + " is invalid side.");
            }
            
        }

        @objid ("6c3da13c-b12f-43d0-8c83-df5de445b15c")
        private Rectangle calcQualifierBounds(final Direction side) {
            Dimension qualSize = CompositeDecoration.this.qualifierGroup.getMinimumSize();
            Dimension halfSize = qualSize.getScaled(0.5);
            Point topleft = CompositeDecoration.this.getDecorationLocation().getCopy();
            switch (side) {
                case EAST:
                    topleft.translate(-2, -halfSize.height);
                    break;
                case NORTH:
                    topleft.translate(-halfSize.width, -qualSize.height + 2);
                    break;
                case SOUTH:
                    topleft.translate(-halfSize.width, -2);
                    break;
                case WEST:
                    topleft.translate(-qualSize.width + 2, -halfSize.height);
                    break;
                case NONE:
                    throw new IllegalArgumentException(side + " is invalid side.");
            }
            return new Rectangle(topleft, qualSize);
        }

    }

}
