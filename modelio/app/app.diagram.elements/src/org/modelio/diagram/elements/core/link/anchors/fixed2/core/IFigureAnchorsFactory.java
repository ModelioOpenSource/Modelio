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
package org.modelio.diagram.elements.core.link.anchors.fixed2.core;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.elements.core.link.anchors.GmFixedAnchor;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Fixed {@link ConnectionAnchor} factory interface to produces and find fixed anchors.
 * <p>
 * A factory may be retained as long as the figure and the edit part don't change:
 * no move, resize, nor connection number change.
 * <p>
 * Once one of these may change a call to the {@link IFigureAnchorsAbstractFactory} must be done to obtain a refreshed instance.
 */
@objid ("a48b7175-434b-4ad0-87b7-18a6beb1ca9b")
public interface IFigureAnchorsFactory {
    /**
     * Create a draw2d anchor from a model anchor
     * @param gmLinkAnchor the anchor model
     * @return the draw2d anchor
     */
    @objid ("508e9fd8-c796-4804-b109-692feb553d9f")
    ConnectionAnchor createFromModel(GmFixedAnchor gmLinkAnchor);

    /**
     * Return all possible anchors for the node and the connection routing mode.
     * @param routerId the connection routing mode
     * @param face optional face number, to filter anchors
     * @return all possible anchors
     */
    @objid ("b86d317f-eef2-40d0-9666-57edfeaf0310")
    Collection<ConnectionAnchor> getAllAnchors(ConnectionRouterId routerId, Integer face);

    /**
     * Return the nearest anchor from the given point.
     * @param absPoint a point in absolute coordinates.
     * @param routerId the connection routing mode
     * @param face optional face number, to filter anchors
     * @param isSourceAnchor true if requesting anchor for the source node, false for the target node.
     * @return the nearest anchor from the given point.
     */
    @objid ("9d5b48ac-5afe-41b3-a752-9c36c6ad5325")
    default ConnectionAnchor getNearest(Point absPoint, ConnectionRouterId routerId, Integer face, boolean isSourceAnchor) {
        Collection<ConnectionAnchor> allAnchors = getAllAnchors( routerId, face);
        if (allAnchors.isEmpty())
            return null;
        
        double mind = Double.MAX_VALUE;
        ConnectionAnchor nearest = allAnchors.iterator().next();
        
        for (ConnectionAnchor a : allAnchors) {
            double dist = a.getLocation(absPoint).getDistance(absPoint);
            if (dist < mind) {
                mind = dist;
                nearest = a;
            }
        }
        return nearest;
    }

    /**
     * Get the unique identifier of the algorithm that uses this factory to generate anchors.
     * <p>
     * Will be serialized in graphic model in order to regenerate the same anchors.
     * @return the identifier of the algorithm.
     */
    @objid ("e7e1e6db-7509-452b-93dd-7c85ca696c36")
    String getAlgorithmId();
}

