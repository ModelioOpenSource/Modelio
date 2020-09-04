/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.elements.drawings.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;

/**
 * Drawing geometry constants.
 */
@objid ("5299a673-9251-44f8-a5e4-e7ea05dce067")
public interface IDrawingConstants {
    /**
     * Default size of a drawing at creation
     */
    @objid ("5f6ab4e8-5854-489e-853e-7e9b66954c91")
    public static final Dimension DEFAULT_SIZE = new Dimension(50, 30);

    /**
     * Minimum size of a drawing node on "Fit to content"
     */
    @objid ("d5efe5d6-a4ce-43af-9c55-ae9ac4c0c729")
    public static final Dimension FIT_TO_CONTENT_MINSIZE = new Dimension(30, 10);

    /**
     * Minimum inset where a transparent drawing node is still clickable.
     */
    @objid ("e82c3741-d625-4a30-8bcb-b0b430accb83")
    public static final Insets TRANSPARENT_MARGIN = new Insets(5);

}
