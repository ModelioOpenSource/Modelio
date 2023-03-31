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
package org.modelio.diagram.elements.core.figures.anchors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;

/**
 * Interface implementable by ConnectionAnchor to create a figure that represents the anchor.
 * <p>
 * Used to display fixed anchor positions when displaying feedback or connection creation handles.
 * @author cmarin
 * @since 5.3.1
 */
@objid ("eb7125f4-4f91-4d2d-ae6d-50ebb87d6ef0")
public interface IAnchorHandleProvider {
    /**
     * Creates a figure for a {@link ConnectionAnchor}.
     * <p>
     * Used to display fixed anchor positions when displaying feedback or connection creation handles.
     * @param anchor a connection anchor
     * @return a figure
     */
    @objid ("be8e434b-bdcc-49a3-9790-c37c3f324568")
    IFigure createAnchorHandleFigure(ConnectionAnchor anchor);
}

