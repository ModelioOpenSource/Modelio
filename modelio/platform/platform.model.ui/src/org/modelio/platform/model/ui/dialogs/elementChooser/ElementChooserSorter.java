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
package org.modelio.platform.model.ui.dialogs.elementChooser;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Element;

/**
 * Default sorter for the element chooser dialog.
 * ModuleComponents are always first, and everything is then sorted alphabetically.
 */
@objid ("26e385c7-186f-11e2-bc4e-002564c97630")
public class ElementChooserSorter extends ViewerSorter {
    @objid ("26e385c8-186f-11e2-bc4e-002564c97630")
    @Override
    public int compare(Viewer viewer, Object o1, Object o2) {
        if (o1 instanceof ModuleComponent && !(o2 instanceof ModuleComponent)) {
            // ModuleComponents are always first
            return -1;
        } else if (!(o1 instanceof ModuleComponent) && o2 instanceof ModuleComponent) {
            // ModuleComponents are always first
            return 1;
        } else if (o1 instanceof Element && o2 instanceof Element) {
            Element elt1 = (Element) o1;
            Element elt2 = (Element) o2;
            return elt1.getName().compareToIgnoreCase(elt2.getName());
        }
        return 0;
    }

}
