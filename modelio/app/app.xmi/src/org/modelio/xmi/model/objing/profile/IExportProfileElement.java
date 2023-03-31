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
package org.modelio.xmi.model.objing.profile;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.xmi.generation.ProfileExportVisitorImpl;

@objid ("2fe234aa-2e9d-4695-962a-a1a59c90a61c")
interface IExportProfileElement {
    @objid ("7f489ed3-13f1-4944-a797-ef6504c7c014")
    void accept(ProfileExportVisitorImpl visitor);
}

