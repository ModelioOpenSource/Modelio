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
package org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.ellipse;

import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.fixed.ConfigurableFixedAnchorFactory;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.fixed.FixedNodeAnchorLocator;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.fixed.TolerantFixedAnchorLocator;
import org.modelio.diagram.elements.core.link.anchors.fixed2.core.IFigureAnchorsFactory;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Anchor factory for ellipse figures.
 * <p>
 * Produces one anchor at each face middle and one {@link EllipseAnchor}
 * used for non orthogonal links.
 * @author cmarin
 */
@objid ("898771e1-7bd9-4c79-8e3b-8e6ecb96555b")
public class EllipseAnchorFactory extends ConfigurableFixedAnchorFactory {
    @objid ("4824b89f-6e9c-4f7f-a825-0be533b7d53a")
    protected EllipseAnchor ellipseAnchor;

    @objid ("7bd68801-684b-46db-a69d-66673a67e944")
    public  EllipseAnchorFactory(String algorithmId, IFixedAnchorLocator locator) {
        super(algorithmId, locator);
        setAnchorCount(1, 1);
        
    }

    @objid ("963ec4cf-58af-488e-bb42-1be02df2e5d4")
    public  EllipseAnchorFactory(String algorithmId) {
        this(algorithmId, new TolerantFixedAnchorLocator(new FixedNodeAnchorLocator(algorithmId), 1));
    }

    @objid ("71ff001a-d821-474c-b886-ec0964c48585")
    @Override
    public Collection<ConnectionAnchor> getAllAnchors(ConnectionRouterId routerId, Integer face) {
        if (routerId==ConnectionRouterId.BENDPOINT || routerId==ConnectionRouterId.DIRECT)
            return Collections.singleton(this.ellipseAnchor);
        else
            return super.getAllAnchors( routerId, face);
        
    }

    @objid ("6367e5f1-662b-441e-97da-b19fbc2f4e55")
    @Override
    public IFigureAnchorsFactory getAnchorFactoryFor(GraphicalEditPart anEditPart, IFigure aFigure) {
        if (this.ellipseAnchor==null || this.ellipseAnchor.getOwner() != aFigure) {
            this.ellipseAnchor = new EllipseAnchor(aFigure);
        }
        return super.getAnchorFactoryFor(anEditPart, aFigure);
    }

    @objid ("9ae3506b-a83f-47e6-8dee-16c7638f1891")
    @Override
    public ConnectionAnchor getNearest(Point absPoint, ConnectionRouterId routerId, Integer face, boolean isSource) {
        if (routerId == ConnectionRouterId.BENDPOINT || routerId == ConnectionRouterId.DIRECT) {
            return this.ellipseAnchor;
        } else {
            return super.getNearest(absPoint, routerId, face, isSource);
        }
        
    }

}
