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
package org.modelio.diagram.elements.common.resizablegroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * A layout that is midway between a FlowLayout and a ToolbarLayout.
 * 
 * @author fpoyer
 */
@objid ("7f0e9000-1dec-11e2-8cad-001ec947c8cc")
public class ResizableGroupLayout extends ToolbarLayout {
    @objid ("7f0e9004-1dec-11e2-8cad-001ec947c8cc")
    private Map<IFigure, Integer> constraints = new HashMap<>();

    /**
     * @param child the figure whose preferred size is to be determined
     * @param wHint the width hint
     * @param hHint the height hint
     * @since 3.3
     * @return given figure's preferred size
     */
    @objid ("7f0e9009-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Dimension getChildPreferredSize(IFigure child, int wHint, int hHint) {
        Dimension dim = new Dimension(wHint, hHint);
        dim = this.transposer.t(dim);
        Integer constraint = this.constraints.get(child);
        Dimension childPrefSize = child.getPreferredSize(wHint, hHint).getCopy();
        childPrefSize = this.transposer.t(childPrefSize);
        // At the very least the min size should be respected.
        Dimension childMinSize = child.getMinimumSize(wHint, hHint).getCopy();
        childMinSize = this.transposer.t(childMinSize);
        childPrefSize.height = Math.max(childPrefSize.height, childMinSize.height);
        childPrefSize.width = Math.max(childPrefSize.width, childMinSize.width);
        
        if (constraint == null || constraint.intValue() == -1) {
            dim.height = childPrefSize.height;
        } else {
            dim.height = constraint.intValue();
        }
        if (dim.width == -1) {
            dim.width = childPrefSize.width;
        }
        this.transposer.t(dim);
        return dim;
    }

    @objid ("7f0e9016-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setConstraint(IFigure child, Object constraint) {
        super.setConstraint(child, constraint);
        if (constraint instanceof Integer) {
            this.constraints.put(child, (Integer) constraint);
        }
        
    }

    /**
     * Overridden to make the last child use all available space if any.
     */
    @objid ("7f0e901d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void layout(IFigure parent) {
        List<?> children = parent.getChildren();
        int numChildren = children.size();
        Rectangle clientArea = this.transposer.t(parent.getClientArea());
        int x = clientArea.x;
        int y = clientArea.y;
        int availableHeight = clientArea.height;
        
        Dimension prefSizes[] = new Dimension[numChildren];
        Dimension minSizes[] = new Dimension[numChildren];
        
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
         * Calculate sum of preferred heights of all children(totalHeight). Calculate sum of minimum heights of all children(minHeight). Cache Preferred Sizes and Minimum Sizes of all children.
         *
         * totalHeight is the sum of the preferred heights of all children totalMinHeight is the sum of the minimum heights of all children prefMinSumHeight is the sum of the difference between all children's preferred heights and minimum heights. (This is
         * used as a ratio to calculate how much each child will shrink).
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
         * The total amount that the children must be shrunk is the sum of the preferred Heights of the children minus Max(the available area and the sum of the minimum heights of the children).
         *
         * amntShrinkHeight is the combined amount that the children must shrink amntShrinkCurrentHeight is the amount each child will shrink respectively
         */
        int amntShrinkHeight = totalHeight - availableHeight;
        
        // If there is more available space than needed to satisfy wishes of all
        // children, make as if last child (if any) request all available space.
        if (amntShrinkHeight < 0) {
            if (prefSizes.length > 0) {
                prefSizes[prefSizes.length - 1].height -= amntShrinkHeight;
            }
            amntShrinkHeight = 0;
        }
        
        for (int i = 0; i < numChildren; i++) {
            int amntShrinkCurrentHeight = 0;
            int prefHeight = prefSizes[i].height;
            int minHeight = minSizes[i].height;
            int prefWidth = prefSizes[i].width;
            // int minWidth = minSizes[i].width;
            Rectangle newBounds = new Rectangle(x, y, prefWidth, prefHeight);
        
            child = (IFigure) children.get(i);
            if (prefMinSumHeight != 0) {
                amntShrinkCurrentHeight = (prefHeight - minHeight) * amntShrinkHeight / (prefMinSumHeight);
            }
        
            int width = Math.min(prefWidth, this.transposer.t(child.getMaximumSize()).width);
            if (isStretchMinorAxis()) {
                width = this.transposer.t(child.getMaximumSize()).width;
            }
        
            width = Math.min(clientArea.width, width);
            newBounds.width = width;
        
            // int height = Math.min(prefHeight, this.transposer.t(child.getMaximumSize()).height);
            // if(numChildren == 1){
            // newBounds.height = Math.min(clientArea.height, height);
            // }else{
            // newBounds.height = height;
            // }
        
            int adjust = clientArea.width - width;
            switch (getMinorAlignment()) {
            case ALIGN_TOPLEFT:
                adjust = 0;
                break;
            case ALIGN_CENTER:
                adjust /= 2;
                break;
            case ALIGN_BOTTOMRIGHT:
            default:
                break;
            }
            newBounds.x += adjust;
            newBounds.height -= amntShrinkCurrentHeight;
            child.setBounds(this.transposer.t(newBounds));
        
            amntShrinkHeight -= amntShrinkCurrentHeight;
            prefMinSumHeight -= (prefHeight - minHeight);
            y += newBounds.height + getSpacing();
        }
        
    }

}
