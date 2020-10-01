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

package org.modelio.linkeditor.gef.background;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

@objid ("1b8a1d59-5e33-11e2-b81d-002564c97630")
public class BackgroundLayout extends FreeformLayout {
    @objid ("1b8a1d66-5e33-11e2-b81d-002564c97630")
    private static final int offset = 200;

    @objid ("17e420b8-4dd4-411b-acee-40a526e63564")
    private Map<IFigure, Rectangle> cachedPositions = new HashMap<>();

    @objid ("27b984eb-f64f-45a7-a2f6-1034739ba12d")
    private static final Rectangle REFERENCE_RECTANGLE = new Rectangle(0, 0, 100, 50);

    @objid ("1b8a1d68-5e33-11e2-b81d-002564c97630")
    @Override
    public void layout(final IFigure parent) {
        Point offsetPoint = getOrigin(parent);
        for (Object obj : parent.getChildren()) {
            IFigure f = (IFigure) obj;
            Rectangle bounds = getPosition(f);
            bounds = bounds.getTranslated(offsetPoint);
            f.setBounds(bounds);
        }
    }

    @objid ("1b8a1d6f-5e33-11e2-b81d-002564c97630")
    @Override
    protected Dimension calculatePreferredSize(final IFigure f, final int wHint, final int hHint) {
        Rectangle rect = new Rectangle();
        ListIterator<?> children = f.getChildren().listIterator();
        while (children.hasNext()) {
            IFigure child = (IFigure) children.next();
            Rectangle r = getPosition(child);
            if (r == null)
                continue;
        
            if (r.width == -1 || r.height == -1) {
                Dimension preferredSizeDimension = child.getPreferredSize(r.width, r.height);
                r = r.getCopy();
                if (r.width == -1)
                    r.width = preferredSizeDimension.width;
                if (r.height == -1)
                    r.height = preferredSizeDimension.height;
            }
            rect.union(r);
        }
        Dimension d = rect.getSize();
        Insets insets = f.getInsets();
        return new Dimension(d.width + insets.getWidth(), d.height + insets.getHeight()).union(getBorderPreferredSize(f));
    }

    @objid ("1b8a1d7e-5e33-11e2-b81d-002564c97630")
    @Override
    public Point getConstraint(final IFigure figure) {
        return (Point) super.getConstraint(figure);
    }

    @objid ("1b8a1d89-5e33-11e2-b81d-002564c97630")
    private Rectangle getPosition(final IFigure figure) {
        if (this.cachedPositions.isEmpty()) {
            // TODO
        }
        return this.cachedPositions.get(figure);
    }

    @objid ("1b8a1d93-5e33-11e2-b81d-002564c97630")
    @Override
    public void setConstraint(final IFigure figure, final Object newConstraint) {
        super.setConstraint(figure, newConstraint);
        this.cachedPositions.clear();
    }

}
