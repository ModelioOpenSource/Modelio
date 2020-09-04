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

package org.modelio.core.rcp.extensionpoint;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("8d84be17-9ffc-4778-be2a-25e1c3adc149")
public interface IExtensionPointContribution {
    @objid ("2600fbf4-d915-4d16-8871-4c8f586da51c")
    default boolean isActive() {
        return true;
    }

}
