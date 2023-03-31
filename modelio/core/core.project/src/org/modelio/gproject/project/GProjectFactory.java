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
package org.modelio.gproject.project;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.GProjectDescriptor;

@objid ("9285b92b-c53e-4efd-b307-9af7c292c640")
public class GProjectFactory implements IProjectFactory {
    @objid ("313c6a53-464e-47c9-80cb-875fa51aa776")
    @Override
    public IGProject createProject(GProjectDescriptor projectDescriptor) throws IllegalArgumentException {
        // FIXME
        return GProject.newBuilder(projectDescriptor).build(null);
    }

    @objid ("770fe185-6425-464e-9205-748d96776419")
    @Override
    public boolean supports(GProjectDescriptor projectDescriptor) {
        // FIXME
        return true;
    }

}
