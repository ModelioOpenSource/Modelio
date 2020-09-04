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
 * Vertical alignment enumeration
 */
@objid ("933d5a30-200f-4426-ad5f-f6787e14355e")
public enum VAlign {
    /**
     * Top
     */
    Top (SWT.BEGINNING),
    /**
     * Center
     */
    Center (SWT.CENTER),
    /**
     * Bottom
     */
    Bottom (SWT.END);

    @objid ("86d26b0a-ce2d-45f5-a9c9-f29e45486722")
    private final int swtConst;

    @objid ("600338e2-1d20-4052-8f82-dc93c5334ba1")
    VAlign(int swtConst) {
        this.swtConst = swtConst;
    }

    /**
     * @return the matching SWT constant
     */
    @objid ("9c7ea6dc-f0c3-4e28-bad7-a23d872d3fce")
    public int toSwt() {
        return this.swtConst;
    }

}
