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

package org.modelio.app.project.ui.views.workspace;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.modelio.gproject.data.project.ProjectDescriptor;

@objid ("efeba28c-b0d5-4ddf-8935-17563609695c")
public class WksNameSorter extends ViewerComparator {
    @objid ("d2934bfb-d171-47e5-858f-cf5b45f2c158")
    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        return super.compare(viewer, ((ProjectDescriptor) e1).getName(), ((ProjectDescriptor) e2).getName());
    }

}
