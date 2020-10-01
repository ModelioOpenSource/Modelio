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

package org.modelio.model.property.stereotype.chooser;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

@objid ("00dbe987-cb87-4eee-8f70-4c9dc06da339")
public class StereotypeChooserComparator extends ViewerComparator {
    @objid ("f0eaf8d0-d191-42e3-9bd6-5ab6fd221c07")
    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        if (e1 instanceof ModelElement && e2 instanceof ModelElement) {
            ModelElement me1 = (ModelElement) e1;
            ModelElement me2 = (ModelElement) e2;
            return me1.getName().compareToIgnoreCase(me2.getName());
        }
        return 0;
    }

}
