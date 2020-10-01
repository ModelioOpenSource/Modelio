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
@objid ("051d3728-c30f-49bb-b5e1-29ea16cb4316")
public interface IFragmentInfos {
    /**
     * @return the description.
     */
    @objid ("9c4a3893-4661-43de-b7c0-b36c77a9bddf")
    String getDescription();

    /**
     * Get the version of Modelio used to create this fragment.
     * 
     * @return the Modelio version.
     */
    @objid ("b48eb12b-a728-4154-b5ed-d3a23f137151")
    Version getModelioVersion();

    /**
     * @return this fragment name.
     */
    @objid ("9068c018-ecd4-4644-9ed5-7eb963c61c1f")
    String getName();

    /**
     * @return this model component version.
     */
    @objid ("3b04693c-21a6-401c-b65f-cba51d2f9cfd")
    Version getVersion();

}
