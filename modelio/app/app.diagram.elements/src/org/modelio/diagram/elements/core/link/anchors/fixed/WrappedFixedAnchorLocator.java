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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
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
@objid ("efbcb039-efc4-4094-9849-bb86606d8a2f")
@Deprecated
class WrappedFixedAnchorLocator implements IFixedAnchorLocator {
    @objid ("6aa59740-7094-464c-8bef-a3e2fcfe40d5")
    private IFixedAnchorLocator delegate;

    /**
     * @param delegate the locator to wrap
     */
    @objid ("aa4d31ae-5dfc-430c-932b-0238ce87b0cc")
    public  WrappedFixedAnchorLocator(IFixedAnchorLocator delegate) {
        this.delegate = delegate;
    }

    @objid ("3c121772-2aaa-45c2-b4ed-4bce4b82becd")
    @Override
    public Point getReferencePoint(FixedAnchor anchor) {
        return this.delegate.getReferencePoint(anchor);
    }

    @objid ("6756e611-d67b-4684-9c5d-849ddcfe1211")
    @Override
    public String getFaceName(FixedAnchor fixedAnchor) {
        return this.delegate.getFaceName(fixedAnchor);
    }

    @objid ("a5857ceb-16bf-4a07-8d98-8c71cf72963e")
    @Override
    public void onFigureMoved(IFigure figure) {
        this.delegate.onFigureMoved(figure);
    }

    @objid ("1d63539a-38ad-4b4a-8f98-bf93964b878a")
    @Override
    public Direction getDirection(FixedAnchor anchor) {
        return this.delegate.getDirection(anchor);
    }

    @objid ("69e216ff-4f3c-4137-8feb-dc8328477159")
    @Override
    public Point getLocation(FixedAnchor anchor, Point reference) {
        return this.delegate.getLocation(anchor, reference);
    }

    @objid ("51699afb-3f14-49e3-9635-18a68be605f9")
    @Override
    public String getAlgorithm() {
        return this.delegate.getAlgorithm();
    }

}
