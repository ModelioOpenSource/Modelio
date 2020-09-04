/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.eclipse.swt.SWT;

/**
 * Horizontal alignment enumeration.
 */
@objid ("8cd6fa9e-4380-41a4-bfae-e8217f8710c3")
public enum HAlign {
    /**
     * Left
     */
    Left (SWT.BEGINNING),
    /**
     * Center
     */
    Center (SWT.CENTER),
    /**
     * Right
     */
    Right (SWT.END);

    @objid ("c9d0f90e-fafa-4e51-8ba5-c9d3af5fdf68")
    private final int swtConst;

    @objid ("880c2540-0ba2-41a4-9444-95c6aae51355")
    HAlign(int swtConst) {
        this.swtConst = swtConst;
    }

    /**
     * @return the matching SWT constant
     */
    @objid ("283943ae-c3cb-42f2-8393-2825c9360164")
    public int toSwt() {
        return this.swtConst;
    }

}
