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

package com.sun.star.comp.beans;

import java.awt.Component;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.awt.XWindowPeer;

/**
 * The concrete implementation of the OfficeWindow extends an appropriate type of visual component (java.awt.Canvas for
 * local and java.awt.Container for remote).
 * 
 * @deprecated
 */
@objid ("6115bc0e-91e0-48f5-bde8-add3749d077f")
@Deprecated
public interface OfficeWindow {
    /**
     * Retrieves an AWT component object associated with the OfficeWindow.
     * 
     * @return The AWT component object associated with the OfficeWindow.
     */
    @objid ("016cb1d3-4d57-4ecc-a206-802c5ea9b9aa")
    Component getAWTComponent();

    /**
     * Retrieves an UNO XWindowPeer object associated with the OfficeWindow.
     * 
     * @return The UNO XWindowPeer object associated with the OfficeWindow.
     */
    @objid ("a63d173a-42a5-4f2c-b3f9-f8e0f212bd7e")
    XWindowPeer getUNOWindowPeer();

}
