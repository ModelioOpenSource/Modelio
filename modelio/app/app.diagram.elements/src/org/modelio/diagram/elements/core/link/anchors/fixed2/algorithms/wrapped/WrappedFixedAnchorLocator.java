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
package org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.wrapped;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;
import org.modelio.diagram.elements.core.figures.geometry.Direction;

/**
 * Anchor locator that delegates partially to another anchor locator.
 * <p>
 * To be subclassed to decorate another locator.
 */
@objid ("144b1009-4801-4f97-a592-f8bf3af2f467")
public class WrappedFixedAnchorLocator implements IFixedAnchorLocator {
    @objid ("b9d39ae5-b722-4b8f-9e88-528074b50175")
    private IFixedAnchorLocator delegate;

    /**
     * @param delegate the locator to wrap
     */
    @objid ("347bbf98-e2ed-4523-bcb7-4f0658ab1916")
    public  WrappedFixedAnchorLocator(IFixedAnchorLocator delegate) {
        this.delegate = delegate;
    }

    @objid ("c1be0a7c-958e-409f-8f00-7a8f84330adf")
    @Override
    public Point getReferencePoint(FixedAnchor anchor) {
        return this.delegate.getReferencePoint(anchor);
    }

    @objid ("e9c9b52b-f223-4999-9e5b-7ee6cf8a513d")
    @Override
    public String getFaceName(FixedAnchor fixedAnchor) {
        return this.delegate.getFaceName(fixedAnchor);
    }

    @objid ("3a6449fb-c391-4940-8348-d603c5a55ab6")
    @Override
    public void onFigureMoved(IFigure figure) {
        this.delegate.onFigureMoved(figure);
    }

    @objid ("1152da46-c17a-463a-bf83-2ef58ef9e334")
    @Override
    public Direction getDirection(FixedAnchor anchor) {
        return this.delegate.getDirection(anchor);
    }

    @objid ("7ae1c1e6-2719-4f3d-86db-6901c1a241c9")
    @Override
    public Point getLocation(FixedAnchor anchor, Point reference) {
        return this.delegate.getLocation(anchor, reference);
    }

    @objid ("ec437370-fb94-42f2-b572-752d028bc131")
    @Override
    public String getAlgorithm() {
        return this.delegate.getAlgorithm();
    }

    @objid ("e090acd8-4686-4747-9829-e920954fa84f")
    @Override
    public IFigure createAnchorHandleFigure(ConnectionAnchor anchor) {
        return this.delegate.createAnchorHandleFigure(anchor);
    }

}
