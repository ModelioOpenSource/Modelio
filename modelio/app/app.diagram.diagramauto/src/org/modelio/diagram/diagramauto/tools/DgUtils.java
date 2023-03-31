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
package org.modelio.diagram.diagramauto.tools;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.IDiagramNode.Role;
import org.modelio.metamodel.uml.statik.Interface;

/**
 * Utility methods for automatic diagrams.
 * 
 * @since 5.1
 */
@objid ("675d9633-166c-4dfb-b90c-86435f465fe0")
public class DgUtils {
    /**
     * Move a node and align its label if it has one.
     * @param dg the node to move.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    @objid ("1b2b54df-a6d4-4667-89c9-f75e6dad9678")
    public static void setLocation(IDiagramNode dg, int x, int y) {
        dg.setLocation(x, y);
        
        // Interface labels are on the right, skip them
        if (dg.getElement() instanceof Interface) {
            return;
        }
        
        // Fix label coordinates to move them below the node
        Rectangle containerBounds = dg.getBounds();
        Point containerCenter = containerBounds.getCenter();
        
        for (IDiagramNode label : dg.getNodes(Role.LABEL)) {
            Rectangle labelBounds = label.getBounds();
        
            // Make sure the label is not on the node
            labelBounds.y = containerBounds.getBottom().y;
        
            // Center label and node
            Point labelCenter = labelBounds.getCenter();
            if (labelCenter.x != containerCenter.x) {
                labelBounds.x += containerCenter.x - labelCenter.x;
            }
            label.setLocation(labelBounds.x, labelBounds.y);
        }
        
    }

}
