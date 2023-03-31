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
package org.modelio.platform.project.services.createproject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.project.creation.IProjectCreationData;
import org.modelio.platform.project.creation.IProjectCreatorDelegate;

@objid ("10b3dd5f-369b-44fa-8afb-734bda685867")
public interface IProjectCreatorFactory {
    @objid ("8a661c8e-2858-4ca9-b436-82653eb083ae")
    IProjectCreatorDelegate getProjectCreator(IProjectCreationData data);
}

