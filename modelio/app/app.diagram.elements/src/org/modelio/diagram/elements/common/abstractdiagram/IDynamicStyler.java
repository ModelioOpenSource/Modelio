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

package org.modelio.diagram.elements.common.abstractdiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.styles.core.IStyle;

/**
 * Decorator for graphical models in diagram.
 * <p>
 * With a style, it becomes possible to dynamically overwrite graphical property on any unmasked element.
 * </p>
 */
@objid ("adc3c21f-d423-4a75-bd0c-b2c4e63d7836")
public interface IDynamicStyler {
    /**
     * Dynamically redefine a diagram's style.
     * 
     * @param gmModel the Gm being analyzed.
     * @param originalStyle the current style of the Gm.
     * @return the redefined style.
     */
    @objid ("4ccd7b8e-9a6a-4483-9a5d-3877226ff177")
    IStyle getStyle(GmModel gmModel, IStyle originalStyle);

}
