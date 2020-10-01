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

package com.sun.star.comp.beans;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * indicates that an operation needed a system window, but no system window was acquired yet.
 * 
 * @see com.sun.star.comp.beans.AwtOOoBean#aquireSystemWindow()
 * @since OOo 2.0.0
 */
@objid ("58e39ebd-f12c-491c-98ea-374231898dfc")
public class SystemWindowException extends Exception {
    @objid ("46adb596-c494-4d8d-b189-cafc8d8a9a63")
    private static final long serialVersionUID = 1L;

}
