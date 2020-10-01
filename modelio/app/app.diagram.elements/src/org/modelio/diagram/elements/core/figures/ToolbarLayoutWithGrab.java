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

package org.modelio.diagram.elements.core.figures;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Same as {@link ToolbarLayout} but the last child may grab the remaining space.
 * <p>
 * Differences with ToolbarLayout:
 * <ul>
 * <li> The last child may grab the remaining space.<br>
 * This may be disabled since Modelio 3.4 with {@link #setLastChildGrab(boolean)}.<br>
 * <li> The children width is not respected if greater than container size.<br>
 * Since Modelio 3.4.
 * <li> Currently minor alignment is ignored and always TOP_LEFT.
 * </ul>
 * 
 * @author cmarin
 */
@objid ("7fcd4c2b-1dec-11e2-8cad-001ec947c8cc")
public class ToolbarLayoutWithGrab extends ToolbarLayout {
    @objid ("2fc55df3-f636-4bba-90ba-b7acc12269a8")
    private boolean lastChildGrab = true;

    /**
     * Constructs a vertically oriented ToolbarLayoutWithGrab with child spacing of 0 pixels, matchWidth
     * <code>true</code>, and {@link #ALIGN_TOPLEFT} alignment.
     */
    @objid ("7fcd4c2f-1dec-11e2-8cad-001ec947c8cc")
    public ToolbarLayoutWithGrab() {
        super();
    }

    /**
     * Constructs a ToolbarLayoutWithGrab with a specified orientation. Default values are: child spacing 0 pixels,
     * matchWidth <code>false</code>, and {@link #ALIGN_TOPLEFT} alignment.
     * 
     * @param isHorizontal whether the children are oriented horizontally
     */
    @objid ("7fcd4c32-1dec-11e2-8cad-001ec947c8cc")
    public ToolbarLayoutWithGrab(boolean isHorizontal) {
        super(isHorizontal);
    }

    /**
     * --------------------------
     */
    @objid ("7fcd4c36-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void layout(IFigure parent) {
        final List<?> children = parent.getChildren();
        final int numChildren = children.size();
        final Rectangle clientArea = this.transposer.t(parent.getClientArea());
        final int x = clientArea.x;
        int y = clientArea.y;
        final int availableHeight = clientArea.height;
        
        final Dimension prefSizes[] = new Dimension[numChildren];
        final Dimension minSizes[] = new Dimension[numChildren];
        
        // Calculate the width and height hints. If it's a vertical
        // ToolBarLayout,
        // then ignore the height hint (set it to -1); otherwise, ignore the
        // width hint. These hints will be passed to the children of the parent
        // figure when getting their preferred size.
        int wHint = -1;
        int hHint = -1;
        if (isHorizontal()) {
            hHint = parent.getClientArea(Rectangle.SINGLETON).height;
        } else {
            wHint = parent.getClientArea(Rectangle.SINGLETON).width;
        }
        
        /*
         * Calculate sum of preferred heights of all children(totalHeight).
         * Calculate sum of minimum heights of all children(minHeight). Cache
         * Preferred Sizes and Minimum Sizes of all children.
         *
         * totalHeight is the sum of the preferred heights of all children
         * totalMinHeight is the sum of the minimum heights of all children
         * prefMinSumHeight is the sum of the difference between all children's
         * preferred heights and minimum heights. (This is used as a ratio to
         * calculate how much each child will shrink).
         */
        IFigure child;
        int totalHeight = 0;
        int totalMinHeight = 0;
        int prefMinSumHeight = 0;
        
        for (int i = 0; i < numChildren; i++) {
            child = (IFigure) children.get(i);
        
            prefSizes[i] = this.transposer.t(getChildPreferredSize(child, wHint, hHint));
            minSizes[i] = this.transposer.t(getChildMinimumSize(child, wHint, hHint));
        
            totalHeight += prefSizes[i].height;
            totalMinHeight += minSizes[i].height;
        }
        totalHeight += (numChildren - 1) * getSpacing();
        totalMinHeight += (numChildren - 1) * getSpacing();
        prefMinSumHeight = totalHeight - totalMinHeight;
        /*
         * The total amount that the children must be shrunk is the sum of the
         * preferred Heights of the children minus Max(the available area and
         * the sum of the minimum heights of the children).
         *
         * amntShrinkHeight is the combined amount that the children must shrink
         * amntShrinkCurrentHeight is the amount each child will shrink
         * respectively
         */
        int amntShrinkHeight = totalHeight - Math.max(availableHeight, totalMinHeight);
        
        if (amntShrinkHeight < 0) {
            amntShrinkHeight = 0;
        }
        
        for (int i = 0; i < numChildren; i++) {
            int amntShrinkCurrentHeight = 0;
            final int prefHeight = prefSizes[i].height;
            final int minHeight = minSizes[i].height;
            final int prefWidth = prefSizes[i].width;
            final int minWidth = minSizes[i].width;
            final Rectangle newBounds = new Rectangle(x, y, prefWidth, prefHeight);
        
            child = (IFigure) children.get(i);
            if (prefMinSumHeight != 0) {
                amntShrinkCurrentHeight = (prefHeight - minHeight) * amntShrinkHeight / (prefMinSumHeight);
            }
        
            int width = Math.min(prefWidth, this.transposer.t(child.getMaximumSize()).width);
            if (isStretchMinorAxis()) {
                // Change: If matchWidth is on, force match even if it means
                // smaller than minimum size of child.
                //width = this.transposer.t(child.getMaximumSize()).width;
                width = clientArea.width;
            } else {
                width = Math.max(minWidth, Math.min(clientArea.width, width));
            }
            newBounds.width = width;
            newBounds.height -= amntShrinkCurrentHeight;
        
            if (this.lastChildGrab && i == numChildren - 1) {
                // Last child takes all remaining space
                newBounds.height = clientArea.bottom() - newBounds.y;
            }
        
            child.setBounds(this.transposer.t(newBounds));
        
            amntShrinkHeight -= amntShrinkCurrentHeight;
            prefMinSumHeight -= (prefHeight - minHeight);
            y += newBounds.height + getSpacing();
        }
    }

    /**
     * Activate or disable last child grabbing the remaining space.
     * 
     * @param grab whether last child grabs the remaining space.
     * @return this for convenience.
     */
    @objid ("dcdd36af-cbe5-404d-8881-2450b2cc7b7d")
    public ToolbarLayoutWithGrab setLastChildGrab(boolean grab) {
        this.lastChildGrab = grab;
        return this;
    }

}
