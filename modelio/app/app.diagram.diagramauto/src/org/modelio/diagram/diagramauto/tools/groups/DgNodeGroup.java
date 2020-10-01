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

package org.modelio.diagram.diagramauto.tools.groups;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramNode;

/**
 * Implementation of a (virtual) group of {@link IDiagramNode} as a layout helper used to manipulate a set of DG as a unique entity.
 * 
 * A DgNodeGroup:
 * <ul>
 * <li>can layout its children either as a vertical column {@link #vLayout(int, int, int)} or as an horizontal row {@link #hLayout(int, int, int)}.</li>
 * <li>can return its bounds, the rectangle enclosing all its children</li>
 * <li>can be moved as a whole to given (x,y) coordinates, computing and applying the proper move of each of its children.
 * <li>
 * </ul>
 */
@objid ("5e384f6e-c383-416f-a188-8dd4269f6dec")
public class DgNodeGroup {
    @objid ("1bd96b7e-b1ff-4678-bcf3-a635ad782783")
    private Collection<IDiagramNode> dgs;

    /**
     * C'tor
     * 
     * @param dgs the diagram nodes belonging to the group
     */
    @objid ("25065737-becf-4e9f-936d-1aefb2bccf47")
    public DgNodeGroup(Collection<IDiagramNode> dgs) {
        this.dgs = dgs;
    }

    /**
     * Move the group to the (x,y) position, applying the correct delta-move to each of its children to maintain the relative position of the children in the group.
     * 
     * @return the new bounds of the group.
     */
    @objid ("d27daf87-55ff-437e-b3b0-24ca370b4605")
    public Rectangle moveTo(double x, double y) {
        Rectangle currentBounds = getBounds();
        double dx = x - currentBounds.preciseX();
        double dy = y - currentBounds.preciseY();
        for (IDiagramNode dg : this.dgs) {
            dg.setBounds(dg.getBounds().translate(dx, dy));
        }
        return getBounds();
    }

    /**
     * Get the rectangle enclosing the group children.
     * 
     * @return the group children enclosing rectangle
     */
    @objid ("cd63c9c1-a03c-4bf5-b37f-8a2ddbde63f4")
    public Rectangle getBounds() {
        Rectangle bounds = new Rectangle();
        for (IDiagramNode dg : this.dgs) {
            bounds.union(dg.getBounds());
        }
        return bounds;
    }

    /**
     * Layout the children as a column. The first child will be laid out at position (x,y) and the next ones below it. The vertical spacing between two children is given by the spacing parameter. All children are left-aligned on the x coordinates
     */
    @objid ("7c0dbaf1-41ab-45ab-aee9-d8cfd04a12b9")
    public void vLayout(int x, int y, int spacing) {
        vLayout(x, y, spacing, 0);
    }

    /**
     * Layout the children as a row. The first child will be laid out at position (x,y) and the next ones on its right. The horizontal spacing between two children is given by the spacing parameter. All children are top-aligned on the y coordinates
     */
    @objid ("90191e2f-53b6-4260-86e6-2aae16501271")
    public void hLayout(int x, int y, int spacing) {
        hLayout(x, y, spacing, 0);
    }

    /**
     * Layout the children as a column. The first child will be laid out at position (x,y) and the next ones below it. The vertical spacing between two children is given by the spacing parameter. All children are left-aligned on the x coordinates
     * 
     * @param alignment 0 = left aligned nodes, 1 = horizontally centered nodes, 2 = right aligned nodes
     */
    @objid ("3e47ae82-4f72-45be-9b6a-94ccf10e3112")
    public void vLayout(int x, int y, int spacing, int alignment) {
        int curX = x;
        int curY = y;
        int maxWidth = 0;
        
        // Layout the nodes vertically
        for (IDiagramNode dg : this.dgs) {
            dg.setLocation(curX, curY);
            curY += dg.getBounds().height + spacing;
            maxWidth = Math.max(maxWidth, dg.getBounds().width);
        }
        
        // Fix the horizontal position for alignment
        switch (alignment) {
        case 1: // center aligned
            for (IDiagramNode dg : this.dgs) {
                int alignedX = dg.getBounds().x + (maxWidth - dg.getBounds().width) / 2;
                int alignedY = dg.getBounds().y;
                dg.setLocation(alignedX, alignedY);
            }
            break;
        case 2: // right aligned
            for (IDiagramNode dg : this.dgs) {
                int alignedX = dg.getBounds().x + (maxWidth - dg.getBounds().width);
                int alignedY = dg.getBounds().y;
                dg.setLocation(alignedX, alignedY);
            }
            break;
        case 0: // left aligned
        default:
            // Nothing to change
        
        }
    }

    /**
     * Layout the children as a row. The first child will be laid out at position (x,y) and the next ones on its right. The horizontal spacing between two children is given by the spacing parameter. All children are top-aligned on the y coordinates
     * 
     * @param alignment 0 = top aligned nodes, 1 = vertically centered nodes, 2 = bottom aligned nodes
     */
    @objid ("23b4b3f6-eda2-4b53-afb2-29bbf1193435")
    public void hLayout(int x, int y, int spacing, int alignment) {
        int curX = x;
        int curY = y;
        int maxHeight = 0;
        
        // Layout the nodes horizontally
        for (IDiagramNode dg : this.dgs) {
            dg.setLocation(curX, curY);
            curX += dg.getBounds().width + spacing;
            maxHeight = Math.max(maxHeight, dg.getBounds().height);
        }
        
        // Fix the vertical position for alignment
        switch (alignment) {
        case 1: // center aligned
            for (IDiagramNode dg : this.dgs) {
                int alignedX = dg.getBounds().x;
                int alignedY = dg.getBounds().y + (maxHeight - dg.getBounds().height) / 2;
                dg.setLocation(alignedX, alignedY);
            }
            break;
        case 2: // right aligned
            for (IDiagramNode dg : this.dgs) {
                int alignedX = dg.getBounds().x;
                int alignedY = dg.getBounds().y + (maxHeight - dg.getBounds().height);
                dg.setLocation(alignedX, alignedY);
            }
            break;
        case 0: // top aligned
        default:
            // Nothing to change
        }
    }

}
