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
package org.modelio.diagram.elements.core.node;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;

/**
 * Indicates that a {@link GmNodeModel} can be displayed in stereotype mode.
 * 
 * @author cmarin
 */
@objid ("80a17d83-1dec-11e2-8cad-001ec947c8cc")
public interface IImageableNode {
    /**
     * Get the stereotype image to display.
     * @return the stereotype image to display. Must not be <i>null</i>.
     */
    @objid ("80a17d85-1dec-11e2-8cad-001ec947c8cc")
    Image getImage();

}
