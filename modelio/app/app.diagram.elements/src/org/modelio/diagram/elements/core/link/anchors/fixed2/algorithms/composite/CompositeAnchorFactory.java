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
package org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.composite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.elements.core.link.anchors.GmFixedAnchor;
import org.modelio.diagram.elements.core.link.anchors.fixed2.core.IFigureAnchorsAbstractFactory;
import org.modelio.diagram.elements.core.link.anchors.fixed2.core.IFigureAnchorsFactory;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Aggregates many {@link IFigureAnchorsAbstractFactory factories} to use many anchor position algorithms.
 * @author cmarin
 * @since 5.3.1
 */
@objid ("9a9880e2-f08b-4f34-8623-50dbcee35fee")
public class CompositeAnchorFactory implements IFigureAnchorsFactory, IFigureAnchorsAbstractFactory {
    @objid ("c35eb5cc-10b8-49f6-a7cd-de31edaa7809")
    private final List<IFigureAnchorsAbstractFactory> providers;

    @objid ("7841bc67-b88d-4d93-8043-edb3f01e5901")
    private GraphicalEditPart editPart;

    @objid ("d5984ff5-011c-4d97-bc57-e555c085ddc0")
    private IFigure figure;

    @objid ("40624dac-5bbe-467c-ab73-ecd61702be8b")
    @Override
    public IFigureAnchorsFactory getAnchorFactoryFor(GraphicalEditPart anEditPart, IFigure aFigure) {
        this.figure = aFigure;
        this.editPart = editPart;
        return this;
    }

    /**
     * @param initProviders the aggregated factories
     */
    @objid ("4e51b762-b4d3-498d-a386-19c25ede20fe")
    public  CompositeAnchorFactory(IFigureAnchorsAbstractFactory... initProviders) {
        this.providers = new ArrayList<>(Arrays.asList(initProviders));
    }

    @objid ("74e45f97-db2e-4b4e-a266-6f4fd7f37e11")
    @Override
    public ConnectionAnchor createFromModel(GmFixedAnchor gmLinkAnchor) {
        String factoryId = gmLinkAnchor.getLocator();
        for (IFigureAnchorsAbstractFactory factory : this.providers) {
            if (factory.getAlgorithmId().equals(factoryId))
                return factory.getAnchorFactoryFor(this.editPart, this.figure).createFromModel( gmLinkAnchor);
        }
        return null;
    }

    @objid ("44113fad-4f4f-4b59-9879-6c94c89cb790")
    @Override
    public Collection<ConnectionAnchor> getAllAnchors(ConnectionRouterId routerId, Integer face) {
        Collection<ConnectionAnchor> ret = new ArrayList<>();
        for (IFigureAnchorsAbstractFactory provider : this.providers) {
            ret.addAll(provider.getAnchorFactoryFor(this.editPart, this.figure).getAllAnchors( routerId, face));
        }
        return ret;
    }

    /**
     * Redefined to ask each provider the nearest point, then choose the nearest of the nearest.
     * <p>
     * Use this algorithm because some providers don't use the default algorithm to choose the nearest anchor.
     */
    @objid ("eda8a3d5-4524-49d7-bec7-731f4fdd3bd2")
    @Override
    public ConnectionAnchor getNearest(Point absPoint, ConnectionRouterId routerId, Integer face, boolean isSourceAnchor) {
        double nearestDist = Double.MAX_VALUE;
        ConnectionAnchor nearest = null;
        
        for (IFigureAnchorsAbstractFactory factory : this.providers) {
            ConnectionAnchor a = factory.getAnchorFactoryFor(this.editPart, this.figure).getNearest( absPoint, routerId, face, isSourceAnchor);
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

    @objid ("63841262-d64b-46f6-9468-edda565c7772")
    @Override
    public String getAlgorithmId() {
        throw new UnsupportedOperationException();
    }

}
