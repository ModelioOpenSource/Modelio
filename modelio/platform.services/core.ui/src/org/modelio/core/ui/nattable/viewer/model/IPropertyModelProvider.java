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

package org.modelio.core.ui.nattable.viewer.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("b4b09922-1f9b-4376-bbae-c794458346f4")
public interface IPropertyModelProvider {
    /**
     * Provides the data model matching a given model element.
     * 
     * @param element The element to display in the property view.
     * @param context The project context.
     * @return The matching property model.
     */
    @objid ("2368d1cb-22dd-4618-bd68-4d0793fddc07")
    IPropertyModel<?> getPropertyModel(MObject element, INatTableViewerContext context);

}
