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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.GraphicalEditPart;

/**
 * Factory to get an {@link IFigureAnchorsFactory} for a {@link GraphicalEditPart node edit part}.
 * <p>
 * Implementations may choose to :<ul>
 * <li>Return always a fresh {@link IFigureAnchorsFactory}. Beware of CPU, memory and GC performances !
 * <li>Return a cached instance. Beware of complexity !
 * <li>Return the same instance with local state updated. Beware of balance of CPU, memory, GC, and complexity !
 * </ul>
 * @author cmarin
 * @since 5.3.1
 */
@objid ("beaeb5a4-c48c-4d0e-9ba8-133db0fa694d")
public interface IFigureAnchorsAbstractFactory {
    /**
     * Get an implementation of {@link IFigureAnchorsFactory} for the given node edit part.
     * <p>
     * The figure may be the {@link GraphicalEditPart#getFigure()} but may also be a feedback or even a temporary figure.
     * <p>
     * The returned factory may be retained as long as the figure and the edit part don't change:
     * no move, resize, nor connection number change.
     * <p>
     * Implementations may choose to :<ul>
     * <li>Return always a fresh instance. Beware of CPU, memory and GC performances !
     * <li>Return a cached instance. Beware of complexity !
     * <li>Return the same instance with local state updated. Beware of complexity !
     * </ul>
     * @param anEditPart a node edit part
     * @param aFigure a node figure
     * @return an implementation of {@link IFigureAnchorsFactory}
     */
    @objid ("06162ec0-4a67-4645-9781-d75ce40d9f3d")
    IFigureAnchorsFactory getAnchorFactoryFor(GraphicalEditPart anEditPart, IFigure aFigure);

    @objid ("75985b24-1a33-4f0f-b5db-2bf50744390d")
    String getAlgorithmId();
}

