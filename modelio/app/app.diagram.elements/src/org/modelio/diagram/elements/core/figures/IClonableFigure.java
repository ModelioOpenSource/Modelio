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
package org.modelio.diagram.elements.core.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;

/**
 * Indicates that the figure may be copied.
 * @author cmarin
 * @since 3.4
 */
@objid ("d816c19b-40b8-4a64-97a6-eeaa586f4f8f")
public interface IClonableFigure extends IFigure {
    /**
     * @return a copy of the figure
     */
    @objid ("d0374309-92b9-4752-89ae-724a75735e25")
    IFigure getCopy();
}

