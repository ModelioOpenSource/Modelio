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

package org.modelio.vstore.exml.common.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Tells a SmDependency has not been found with its name.
 */
@objid ("40a12475-54f0-4252-a338-10bcefd8ad19")
public class DependencyNotFoundException extends Exception {
    @objid ("a5585f97-cff0-46fa-9fc4-ba1839b05646")
    private static final long serialVersionUID = 1L;

    /**
     * @param string an error message.
     */
    @objid ("66b09b1b-9755-469b-86b8-17c563cc7bd9")
    public DependencyNotFoundException(String string) {
        super(string);
    }

}
