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
package org.modelio.diagram.elements.core.figures.routers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("9ef9bb66-a289-4b41-811f-b05cde452dd6")
public final class AutoOrthoConstants {
    /**
     * Minimum distance between 2 bend points.
     */
    @objid ("7a97ba98-28cc-46c8-8202-9ee77e16d32a")
    public static final int MIN_DIST = 20;

    /**
     * Distance under which 2 bend point may be merged.
     */
    @objid ("d379512d-aa57-466f-955c-7e92e0c468cf")
    public static final int SNAP_DIST = 10;

    @objid ("8115acc5-bb43-4a49-823f-43f7e6a88cfd")
    private  AutoOrthoConstants() {
        // no instance
    }

}
