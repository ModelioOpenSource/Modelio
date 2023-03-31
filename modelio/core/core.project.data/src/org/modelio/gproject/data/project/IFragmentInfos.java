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
package org.modelio.gproject.data.project;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;

/**
 * Fragment information interface.
 */
@objid ("ddbed45c-b674-4c81-b327-60e5330973c8")
public interface IFragmentInfos {
    /**
     * @return the description.
     */
    @objid ("813ee2a8-78d8-4d72-945c-72471339ca02")
    String getDescription();

    /**
     * Get the version of Modelio used to create this fragment.
     * @return the Modelio version.
     */
    @objid ("682bbec2-dd6f-470a-90b3-9b6977393d15")
    Version getModelioVersion();

    /**
     * @return this fragment name.
     */
    @objid ("701732de-e3c8-4f72-b2c7-fa8830b5559b")
    String getName();

    /**
     * @return this model component version.
     */
    @objid ("da940d73-adfa-42cd-964c-96e960cc8751")
    Version getVersion();
}

