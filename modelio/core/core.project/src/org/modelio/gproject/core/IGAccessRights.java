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
package org.modelio.gproject.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Represents the access rights on a project part.
 * 
 * @author cmarin
 * @since 5.5 - 29/08/2023
 */
@objid ("9e6d69f6-d48a-40f6-ba30-aa25c4329669")
public interface IGAccessRights {
    /**
     * @return whether the project part is visible to a normal user.
     */
    @objid ("9d8e3ed4-a3a7-4725-9d86-ea4c4806a979")
    boolean isVisible();

    /**
     * If the part is read only, absolutely no modification can be done.
     * @return whether the project part content is editable/modifiable or read only.
     */
    @objid ("88ab932f-7f19-4e3d-bb6a-31e21c3125fe")
    boolean isEditable();
}

