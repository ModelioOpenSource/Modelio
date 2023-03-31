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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.elements.core.link.anchors.GmFixedAnchor;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Aggregates many {@link IFixedConnectionAnchorFactory factories} to use many anchor position algorithms.
 * @author cmarin
 * @since 5.3.1
 */
@objid ("f5c917fa-cc93-4783-b001-d543c38b5a24")
@Deprecated
class CompositeAnchorProvider implements IFixedConnectionAnchorFactory {
    @objid ("52ded861-f860-4c7f-b58a-e54e27bcdce1")
    private final List<IFixedConnectionAnchorFactory> providers;

    /**
     * @param initProviders the aggregated factories
     */
    @objid ("aceba736-4499-42af-883c-4b86416f95d3")
    public  CompositeAnchorProvider(IFixedConnectionAnchorFactory... initProviders) {
        this.providers = new ArrayList<>(Arrays.asList(initProviders));
    }

    @objid ("58c03aed-51c5-416c-8b9c-925a1c870997")
    @Override
    public ConnectionAnchor createFromModel(IFigure nodeFig, GmFixedAnchor gmLinkAnchor) {
        String factoryId = gmLinkAnchor.getLocator();
        for (IFixedConnectionAnchorFactory factory : this.providers) {
            if (factory.getAlgorithmId().equals(factoryId))
                return factory.createFromModel(nodeFig, gmLinkAnchor);
        }
        return null;
    }

    @objid ("bd7feee7-fd3f-470c-bb1c-749a2051eaa8")
    @Override
    public Collection<ConnectionAnchor> getAllAnchors(IFigure nodeFig, ConnectionRouterId routerId, Integer face) {
        Collection<ConnectionAnchor> ret = new ArrayList<>();
        for (IFixedConnectionAnchorFactory provider : this.providers) {
            ret.addAll(provider.getAllAnchors(nodeFig, routerId, face));
        }
        return ret;
    }

    /**
     * Redefined to ask each provider the nearest point, then choose the nearest of the nearest.
     * <p>
     * Use this algorithm because some providers don't use the default algorithm to choose the nearest anchor.
     */
    @objid ("b04be369-6af5-4532-a6fc-33e8e415d411")
    @Override
    public ConnectionAnchor getNearest(IFigure nodeFig, Point absPoint, ConnectionRouterId routerId, Integer face, boolean isSourceAnchor) {
        Collection<ConnectionAnchor> allAnchors = getAllAnchors(nodeFig, routerId, face);
        if (allAnchors.isEmpty())
            return null;
        
        double nearestDist = Double.MAX_VALUE;
        ConnectionAnchor nearest = null;
        
        for (IFixedConnectionAnchorFactory factory : this.providers) {
            ConnectionAnchor a = factory.getNearest(nodeFig, absPoint, routerId, face, isSourceAnchor);
            if (a == null)
                continue;
        
            double dist = a.getLocation(absPoint).getDistance(absPoint);
            if (dist < nearestDist) {
                nearestDist = dist;
                nearest = a;
            }
        }
        return nearest;
    }

    @objid ("e4a68ed4-d60a-4934-ab6a-d5e9d1dd51f3")
    @Override
    public String getAlgorithmId() {
        throw new UnsupportedOperationException();
    }

}
