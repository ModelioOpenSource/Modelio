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

package org.modelio.diagram.diagramauto.diagram.layout;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.ILinkPath;
import org.modelio.api.modelio.diagram.InvalidDestinationPointException;
import org.modelio.api.modelio.diagram.InvalidPointsPathException;
import org.modelio.api.modelio.diagram.InvalidSourcePointException;

@objid ("578596b5-501f-4968-a428-7d1157e48096")
public class CircleLayout {
    @objid ("2b58ff6a-0dbf-473e-aeb9-16c335ef1da1")
    public void layout(final IDiagramHandle dh, final List<IDiagramNode> contentDgs) throws InvalidDestinationPointException, InvalidPointsPathException, InvalidSourcePointException {
        int maxWidth = 0;
        int maxHeight = 0;
        
        // compute max height and width of the elements to layout.
        for (IDiagramNode content : contentDgs) {
            content.fitToContent();
        
            Rectangle r = content.getOverallBounds();
        
            maxWidth = Math.max(maxWidth, r.width);
            maxHeight = Math.max(maxHeight, r.height);
        }
        
        // compute the center and radius of the circle
        double radius = Math.max(maxHeight, maxWidth) * (1.5 + contentDgs.size() / 10);
        PrecisionPoint center = new PrecisionPoint(maxWidth + radius, maxHeight + radius);
        
        // set the elements on the circle
        moveElements(contentDgs, center, radius);
        
        dh.save();
        
        // move links on content nodes
        for (IDiagramNode source : contentDgs) {
            Rectangle sourceR = source.getOverallBounds();
        
            // Links to the content
            for (IDiagramLink link : source.getFromLinks()) {
                link.setRouterKind(LinkRouterKind.DIRECT);
        
                IDiagramNode target = (IDiagramNode) link.getTo();
                Rectangle targetR = target.getOverallBounds();
        
                ILinkPath path = link.getPath();
        
                // Each link is a direct line between the element's centers
                List<Point> points = new ArrayList<>();
                if (sourceR.x > targetR.x) {
                    points.add(new Point(sourceR.x + sourceR.width / 2, sourceR.y + sourceR.height / 2));
                    points.add(new Point(targetR.x + targetR.width / 2, targetR.y + targetR.height / 2));
                } else {
                    points.add(new Point(targetR.x + targetR.width / 2, targetR.y + targetR.height / 2));
                    points.add(new Point(sourceR.x + sourceR.width / 2, sourceR.y + sourceR.height / 2));
                }
        
                // System.out.println();
                path.setPoints(points);
                link.setPath(path);
            }
        }
        
        dh.save();
    }

    /**
     * Sets locations of all dgs, distributed around a circle.
     * The center of each dg is part of the circle itself.
     * 
     * @param dgs the graphics to layout.
     * @param center The center of the circle.
     * @param radius The radius of the circle.
     */
    @objid ("c6d505b7-bc4b-4473-af82-821cdd5bc837")
    protected void moveElements(final List<IDiagramNode> dgs, final PrecisionPoint center, final double radius) {
        int n = dgs.size();
        double angleDeg = 360 / (double) n;
        double angleRad = Math.PI * angleDeg / 180;
        double cos = Math.cos(angleRad);
        double sin = Math.sin(angleRad);
        
        // Compute the center point of each dg
        PrecisionPoint p = new PrecisionPoint(0, radius);
        for (IDiagramNode node : dgs) {
            Rectangle r = node.getOverallBounds();
        
            // Set the node in the correct place in the circle, with a shift corresponding to the center's coordinates
            node.setLocation(center.x + p.x - r.width / 2, center.y + p.y - r.height / 2);
        
            // Compute next point, with a rotation of (360/n) ?
            double newX = p.preciseX() * cos + p.preciseY() * sin;
            double newY = -p.preciseX() * sin + p.preciseY() * cos;
            p = new PrecisionPoint(newX, newY);
        }
    }

}
