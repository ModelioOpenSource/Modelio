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
package org.modelio.diagram.elements.core.link.anchors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default implementation of the anchor ref resolver always returning the center of the figure as a reference.
 * 
 * @since 5.1
 */
@objid ("384412fd-6be8-4ff0-a266-01098584ac46")
public final class DefaultAnchorRefResolver implements IAnchorRefResolver {
    @objid ("66f45440-e8ee-4f93-a1f5-a9d400b3e8e4")
    private static final DefaultAnchorRefResolver INSTANCE = new DefaultAnchorRefResolver();

    /**
     * Default empty c'tor.
     */
    @objid ("b614cd44-ed78-4bde-89db-e7ee10d6c116")
    private  DefaultAnchorRefResolver() {
        super();
    }

    @objid ("d560629f-ee12-4ea2-a8ec-5a52112cdf8f")
    @Override
    public Point resolveAnchorRef(AbstractGraphicalEditPart source, AbstractGraphicalEditPart target, MObject linkElement) {
        return getAbsoluteFigureCenter(source);
    }

    /**
     * Get the center of the figure bounds in absolute coordinates.
     * @param editPart a figure edit part
     * @return the center of the figure.
     */
    @objid ("4634b2c7-ff3f-47e7-9883-17ba3ca665d0")
    protected static final Point getAbsoluteFigureCenter(final AbstractGraphicalEditPart editPart) {
        IFigure fig = editPart.getFigure();
        fig.getUpdateManager().performValidation();
        Point ret = fig.getBounds().getCenter();
        fig.translateToAbsolute(ret);
        return ret;
    }

    /**
     * @return the singleton instance.
     */
    @objid ("c9a25098-b54e-4b2b-9a38-9214581417a0")
    public static DefaultAnchorRefResolver get() {
        return INSTANCE;
    }

}
