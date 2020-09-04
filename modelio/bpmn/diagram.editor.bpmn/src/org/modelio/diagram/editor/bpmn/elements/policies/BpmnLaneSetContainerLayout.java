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

package org.modelio.diagram.editor.bpmn.elements.policies;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.editor.bpmn.plugin.DiagramEditorBpmn;

/**
 * A layout that is midway between a FlowLayout and a ToolbarLayout.
 */
@objid ("613c1547-55b6-11e2-877f-002564c97630")
public class BpmnLaneSetContainerLayout extends ToolbarLayout {
    @objid ("613c1549-55b6-11e2-877f-002564c97630")
    private final Map<IFigure, Integer> constraints = new HashMap<>();

    /**
     * @param child the figure whose preferred size is to be determined
     * @param wHint the width hint
     * @param hHint the height hint
     * @since 3.3
     * @return given figure's preferred size
     */
    @objid ("613c154e-55b6-11e2-877f-002564c97630")
    @Override
    protected Dimension getChildPreferredSize(IFigure child, final int wHint, final int hHint) {
        int width = wHint;
        int height = hHint;
        
        Integer constraint = this.constraints.get(child);
        Dimension childPrefSize = child.getPreferredSize(wHint, hHint);
        if (isHorizontal()) {
            if (constraint == null || constraint.intValue() == -1) {
                constraint = Integer.valueOf(childPrefSize.width);
            }
            if (height == -1) {
                height = childPrefSize.height;
            }
            return new Dimension(constraint.intValue(), height);
        } else {
            if (constraint == null || constraint.intValue() == -1) {
                constraint = Integer.valueOf(childPrefSize.height);
            }
            if (width == -1) {
                width = childPrefSize.width;
            }
            return new Dimension(width, constraint.intValue());
        }
    }

    @objid ("613c1557-55b6-11e2-877f-002564c97630")
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
    @objid ("613c155c-55b6-11e2-877f-002564c97630")
    @Override
    public void layout(IFigure parent) {
        List<?> children = parent.getChildren();
        int numChildren = children.size();
        Rectangle clientArea = this.transposer.t(parent.getClientArea());
        int x = clientArea.x;
        int y = clientArea.y;
        int availableHeight = clientArea.height;
        
        if (availableHeight <= 0) {
            // figure not ready, abort
            return;
        }
        
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
         * Calculate sum of preferred heights of all children(totalHeight). Calculate sum of minimum heights of all children(minHeight). Cache Preferred Sizes and Minimum Sizes of all children. totalHeight is the sum of the preferred heights of all
         * children totalMinHeight is the sum of the minimum heights of all children prefMinSumHeight is the sum of the difference between all children's preferred heights and minimum heights. (This is used as a ratio to calculate how much each child will
         * shrink).
         */
        IFigure child;
        int totalHeight = 0;
        int totalMinHeight = 0;
        int prefMinSumHeight = 0;
        
        for (int i = 0; i < numChildren; i++) {
            child = (IFigure) children.get(i);
        
            Integer constraint = (Integer) getConstraint(child);
            if (isHorizontal()) {
                wHint = constraint == null ? -1 : constraint;
            } else {
                hHint = constraint == null ? -1 : constraint;
            }
        
            prefSizes[i] = this.transposer.t(getChildPreferredSize(child, wHint, hHint));
            minSizes[i] = this.transposer.t(getChildMinimumSize(child, wHint, hHint));
        
            // Protect against minimum size > preferred size that uses constraint
            minSizes[i] = Dimension.min(prefSizes[i], minSizes[i]);
        
            totalHeight += prefSizes[i].height;
            totalMinHeight += minSizes[i].height;
        }
        totalHeight += (numChildren - 1) * getSpacing();
        totalMinHeight += (numChildren - 1) * getSpacing();
        prefMinSumHeight = totalHeight - totalMinHeight;
        /*
         * - The total amount that the children must be shrunk is the sum of the preferred Heights of the children minus the available area. - amntShrinkHeight is the combined amount that the children must shrink - amntShrinkCurrentHeight is the amount
         * each child will shrink respectively
         */
        int amntShrinkHeight = totalHeight - availableHeight;
        int remainMinHeight = totalMinHeight;
        final int initAmntShrinkHeight = amntShrinkHeight;
        final int initprefMinSumHeight = prefMinSumHeight;
        
        // If there is more available space than needed to satisfy wishes of all
        // children, make as if last child (if any) request all available space.
        if (amntShrinkHeight < 0) {
            if (prefSizes.length > 0) {
                prefSizes[prefSizes.length - 1].height -= amntShrinkHeight;
            }
            amntShrinkHeight = 0;
        }
        int effectiveShrink = 0;
        
        for (int i = 0; i < numChildren; i++) {
            int prefHeight = prefSizes[i].height;
            int minHeight = minSizes[i].height;
            int prefWidth = prefSizes[i].width;
            int minWidth = minSizes[i].width;
            Rectangle newBounds = new Rectangle(x, y, prefWidth, prefHeight);
        
            child = (IFigure) children.get(i);
        
            int amntShrinkCurrentHeight;
            if (initprefMinSumHeight < initAmntShrinkHeight) {
                // minimum size too big, dispatch shrink proportional to min size
                amntShrinkCurrentHeight = minHeight * amntShrinkHeight / remainMinHeight;
            } else if (amntShrinkHeight <= 0) {
                // no need to shrink anymore
                amntShrinkCurrentHeight = 0;
            } else if (prefMinSumHeight != 0) {
                // minimum size is enough to accommodate
                amntShrinkCurrentHeight = (prefHeight - minHeight) * amntShrinkHeight / (prefMinSumHeight);
            } else {
                // should not occur
                amntShrinkCurrentHeight = 0;
            }
        
            int width = Math.min(prefWidth, this.transposer.t(child.getMaximumSize()).width);
            if (isStretchMinorAxis()) {
                width = this.transposer.t(child.getMaximumSize()).width;
            }
            width = Math.max(minWidth, Math.min(clientArea.width, width));
            newBounds.width = width;
        
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
            remainMinHeight -= minHeight;
            effectiveShrink += amntShrinkCurrentHeight;
            y += newBounds.height + getSpacing();
        }
        
        // Post condition
        if (y - getSpacing() > clientArea.bottom()) {
        
            String msg = String.format("Lanes don't fit in %s:"
                    + "\n\t-children bottom:%d, container bottom:%d"
                    + "\n\t-height: preferred=%d, min=%d, avail=%d, total shrink=%d, pref shrink=%d, effective shrink=%d"
                    + "\n\t-preferred sizes: %s"
                    + "\n\t-minimum sizes: %s"
                    + "\n\t-client area:%s",
                    parent,
                    y - getSpacing(), clientArea.bottom(),
                    totalHeight, totalMinHeight, availableHeight, initAmntShrinkHeight, initprefMinSumHeight, effectiveShrink,
                    Arrays.toString(prefSizes), Arrays.toString(minSizes), clientArea);
            DiagramEditorBpmn.LOG.warning(new IllegalStateException(msg));
        }
    }

    @objid ("c2bf908d-7176-482e-acb5-23c6a222c023")
    @Override
    public Object getConstraint(IFigure child) {
        return this.constraints.get(child);
    }

}
