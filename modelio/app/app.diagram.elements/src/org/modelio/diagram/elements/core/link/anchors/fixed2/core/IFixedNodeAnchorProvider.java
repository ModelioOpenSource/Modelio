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
import org.eclipse.gef.AccessibleAnchorProvider;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.elements.core.link.anchors.INodeAnchorProvider;

@objid ("e4159507-4ddf-4eca-a20a-59805ae0837d")
public interface IFixedNodeAnchorProvider extends INodeAnchorProvider {
    /**
     * Get an anchor factory for the given edit part and node figure.
     * <p>
     * The figure may be the {@link GraphicalEditPart#getFigure()} but may also be a feedback or even a temporary figure.
     * @param anEditPart a node edit part
     * @param aFigure a  node figure
     * @return the anchor factory.
     */
    @objid ("f571cc58-d582-47eb-a5e9-6f55a0391b76")
    IFigureAnchorsFactory getAnchorFactoryFor(GraphicalEditPart anEditPart, IFigure aFigure);

    /**
     * <p>
     * Get an implementation of {@link AccessibleAnchorProvider} for the given node figure.
     * </p>
     * @param nodeEditPart a node edit part
     * @return an implementation of {@link AccessibleAnchorProvider}
     */
    @objid ("dde1f6d1-7688-4fd7-b561-914be8b9d6fa")
    AccessibleAnchorProvider getAccessibleAnchorProvider(GraphicalEditPart nodeEditPart);
}

