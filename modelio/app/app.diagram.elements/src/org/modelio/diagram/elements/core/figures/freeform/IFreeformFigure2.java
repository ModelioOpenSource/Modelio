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

package org.modelio.diagram.elements.core.figures.freeform;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.FreeformFigure;

/**
 * Extension of {@link FreeformFigure} that handles {@link IExtentFilter}.
 * @author cma
 * @since 3.7
 */
@objid ("b64df78e-97e9-4415-86fc-b5f30893c678")
public interface IFreeformFigure2 extends FreeformFigure {
    /**
     * Set a filter to use when computing {@link #getFreeformExtent()}.
     * <p>
     * Filtered out figures won't be used to compute the extent, that may result to the figures being cropped.
     * 
     * @param f an extent filter.
     */
    @objid ("e9c0598f-f65b-4d48-a3fb-240b3f752340")
    void setExtentFilter(IExtentFilter f);

    /**
     * Get the filter used when computing {@link #getFreeformExtent()}.
     * <p>
     * Filtered out figures won't be used to compute the extent, that may result to the figures being cropped.
     * 
     * @return an extent filter.
     */
    @objid ("cad7beb4-7ecb-4ce2-b1ac-3160cd31e73b")
    IExtentFilter getExtentFilter();

}
