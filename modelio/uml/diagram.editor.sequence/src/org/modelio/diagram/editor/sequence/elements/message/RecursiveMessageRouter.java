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

package org.modelio.diagram.editor.sequence.elements.message;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;

/**
 * Connection router that is specific to the sequence diagram and that is used to route Messages for which the start and end are on the same lifeline.
 * 
 * @author fpoyer
 */
@objid ("d96b4b84-55b6-11e2-877f-002564c97630")
public class RecursiveMessageRouter extends BendpointConnectionRouter {
    @objid ("d96b4b86-55b6-11e2-877f-002564c97630")
    private static final RecursiveMessageRouter instance = new RecursiveMessageRouter();

    @objid ("4c079b15-22c7-440a-ad17-9ec04f1df653")
    private static final Point point1 = new Point();

    @objid ("35be450a-aa9f-4fe8-a8bc-8bdde63fd82a")
    private static final Point point2 = new Point();

    /**
     * Private constructor to avoid unnecessary c'tor.
     */
    @objid ("d96b4b8c-55b6-11e2-877f-002564c97630")
    private RecursiveMessageRouter() {
    }

    /**
     * Returns the instance of this router.
     * 
     * @return the instance of this router.
     */
    @objid ("d96b4b8f-55b6-11e2-877f-002564c97630")
    public static RecursiveMessageRouter getInstance() {
        return instance;
    }

    @objid ("d96b4b94-55b6-11e2-877f-002564c97630")
    @Override
    public void route(final Connection conn) {
        final ConnectionAnchor sourceAnchor = conn.getSourceAnchor();
        final ConnectionAnchor targetAnchor = conn.getTargetAnchor();
        final List<Bendpoint> bendpoints = new ArrayList<>();
        
        // Let's assume the first point is the source anchor reference point (This may be modified later).
        point1.setLocation(sourceAnchor.getReferencePoint());
        conn.translateToRelative(point1);
        bendpoints.add(new AbsoluteBendpoint(point1));
        
        IFigure sourceFigure = sourceAnchor.getOwner();
        if (sourceFigure != null) {
            point1.setLocation(sourceFigure.getBounds().getLeft());
            sourceFigure.translateToAbsolute(point1);
        } else {
            point1.setLocation(sourceAnchor.getReferencePoint());
        }
        point1.translate(50, 0);
        
        IFigure targetFigure = targetAnchor.getOwner();
        if (targetFigure != null) {
            point2.setLocation(targetFigure.getBounds().getLeft());
            targetFigure.translateToAbsolute(point2);
        } else {
            point2.setLocation(targetAnchor.getReferencePoint());
        }
        point2.translate(50, 0);
        
        int x = Math.max(point1.x, point2.x);
        point1.x = x;
        point2.x = x;
        
        conn.translateToRelative(point1);
        bendpoints.add(new AbsoluteBendpoint(point1));
        conn.translateToRelative(point2);
        bendpoints.add(new AbsoluteBendpoint(point2));
        
        point1.setLocation(targetAnchor.getReferencePoint());
        conn.translateToRelative(point1);
        bendpoints.add(new AbsoluteBendpoint(point1));
        
        // Start by computing the actual location of the source anchor, based on the first bendpoint
        point1.setLocation(bendpoints.get(1).getLocation());
        conn.translateToAbsolute(point1);
        Point sourcePoint = sourceAnchor.getLocation(point1);
        conn.translateToRelative(sourcePoint);
        // Use that value in the list, instead of the reference point.
        bendpoints.set(0, new AbsoluteBendpoint(sourcePoint));
        
        // Now compute the actual location of the target anchor, based on the second bendpoint
        int index = bendpoints.size() - 1;
        point1.setLocation(bendpoints.get(index - 1).getLocation());
        conn.translateToAbsolute(point1);
        Point targetPoint = targetAnchor.getLocation(point1);
        conn.translateToRelative(targetPoint);
        // Use that value in the list, instead of the reference point.
        bendpoints.set(index, new AbsoluteBendpoint(targetPoint));
        
        // Clear the old points list
        final PointList points = conn.getPoints();
        points.removeAllPoints();
        for (int i = 0; i < bendpoints.size(); i++) {
            Bendpoint bp = bendpoints.get(i);
            points.addPoint(bp.getLocation());
        }
        
        conn.setPoints(points);
    }

}
