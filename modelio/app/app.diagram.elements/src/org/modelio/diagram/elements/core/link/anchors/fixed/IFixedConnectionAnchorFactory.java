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
package org.modelio.diagram.elements.core.link.anchors.fixed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.AccessibleAnchorProvider;
import org.eclipse.gef.EditPart;
import org.modelio.diagram.elements.core.link.anchors.GmFixedAnchor;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Fixed {@link ConnectionAnchor} factory interface to produces and find fixed anchors.
 * <p>
 * This interface can produce a default implementation of {@link AccessibleAnchorProvider} produced by {@link #getAccessibleAnchorProvider(IFigure)},
 * to be called by {@link EditPart#getAdapter(Class) EditPart.getAdapter(AccessibleAnchorProvider.class)} .
 */
@objid ("51375c3a-3200-4ddc-9235-be55e4103a68")
public interface IFixedConnectionAnchorFactory {
    /**
     * Anchor feedback figures radius.
     */
    @objid ("7fa8d49a-ba83-4131-992e-5e9920530eb5")
    public static final int ANCHOR_RADIUS = 5;

    /**
     * Create a draw2d anchor from a model anchor
     * @param nodeFig the node figure the anchor is anchored to
     * @param gmLinkAnchor the anchor model
     * @return the draw2d anchor
     */
    @objid ("70f5b6d9-17bd-4dfb-9c73-366495eb1954")
    ConnectionAnchor createFromModel(IFigure nodeFig, GmFixedAnchor gmLinkAnchor);

    /**
     * Get an implementation of {@link AccessibleAnchorProvider} for the given node figure.
     * @param nodeFig a node figure
     * @return an implementation of {@link AccessibleAnchorProvider}
     */
    @objid ("de30b1c0-9df1-42a8-a249-c77c0ba1a413")
    default AccessibleAnchorProvider getAccessibleAnchorProvider(IFigure nodeFig) {
        return new AccessibleAnchorProvider() {
            @objid ("6ec0672d-aeaf-4655-987d-4150507d600b")
            @Override
            public List getSourceAnchorLocations() {
                return getTargetAnchorLocations();
            }
        
            @objid ("1bd01c33-3d6d-4108-ae55-84b7242c9220")
            @Override
            public List getTargetAnchorLocations() {
                Collection<ConnectionAnchor> allAnchors = getAllAnchors(nodeFig, ConnectionRouterId.ORTHOGONAL, null);
                List<Point> ret = new ArrayList<>(allAnchors.size());
        
                for (ConnectionAnchor a : allAnchors) {
                    ret.add(a.getReferencePoint());
                }
                return ret;
            }
        };
        
    }

    /**
     * Return all possible anchors for the node and the connection routing mode.
     * @param nodeFig a node figure
     * @param routerId the connection routing mode
     * @param face optional face number, to filter anchors
     * @return all possible anchors
     */
    @objid ("9791c7de-abb6-4107-916b-f2d025402dea")
    Collection<ConnectionAnchor> getAllAnchors(IFigure nodeFig, ConnectionRouterId routerId, Integer face);

    /**
     * Return the nearest anchor from the given point.
     * @param nodeFig a node figure that will own the anchor
     * @param absPoint a point in absolute coordinates.
     * @param routerId the connection routing mode
     * @param face optional face number, to filter anchors
     * @return the nearest anchor from the given point.
     */
    @objid ("20f920f4-99e3-4642-8b29-6ef1ea98c915")
    default ConnectionAnchor getNearest(IFigure nodeFig, Point absPoint, ConnectionRouterId routerId, Integer face, boolean isSourceAnchor) {
        Collection<ConnectionAnchor> allAnchors = getAllAnchors(nodeFig, routerId, face);
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

}
