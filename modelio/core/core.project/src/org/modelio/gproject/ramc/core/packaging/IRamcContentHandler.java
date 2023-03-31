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
package org.modelio.gproject.ramc.core.packaging;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.ramc.core.packaging.filters.IModelFilterConfigurer;

@objid ("14de9455-9e9f-11e1-a22d-001ec947ccaf")
public interface IRamcContentHandler {
    @objid ("ac6e87ed-a419-11e1-aa98-001ec947ccaf")
    void configure(final IModelFilterConfigurer packager);
}

