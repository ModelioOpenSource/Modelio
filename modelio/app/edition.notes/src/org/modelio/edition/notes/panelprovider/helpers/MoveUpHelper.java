/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.edition.notes.panelprovider.helpers;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

@objid ("06f4597b-a0d4-4340-987c-a5f60aa1e40d")
public class MoveUpHelper extends AbstractHelper {
    @objid ("50d7c0ed-0b2f-48da-9a4f-698568c3c9cd")
    public static boolean canExecute(ModelElement element, List<ModelElement> selectedItems) {
        if (element == null || !element.isModifiable()) {
            return false;
        }
        
        Class<?> selectType = null;
        for (ModelElement me : selectedItems) {
            if (!me.getStatus().isModifiable()) {
                return false;
            }
            if (selectType == null) {
                selectType = me.getClass();
            } else {
                if (me.getClass() != selectType) { // cannot move elements with different types
                    return false;
                }
            }
        }
        return (selectedItems.size() > 0);
    }

    @objid ("f4912815-3d6a-4e19-b9e4-3de1341cad1c")
    public boolean execute(ModelElement element, List<ModelElement> selectedItems) {
        int nbToMove = 0;
        List listToReorder = new ArrayList<>();
        if (selectedItems.get(0) instanceof Note) {
            listToReorder = element.getDescriptor(); // list of Notes
        } else if (selectedItems.get(0) instanceof Constraint) {
            listToReorder = ((UmlModelElement) element).getConstraintDefinition(); // list of Constraints
        } else if (selectedItems.get(0) instanceof Document) {
            listToReorder = element.getAttached(); // // list of Documents
        }
        
        for (ModelElement e : selectedItems) {
            int index = MoveUpHelper.getIndexUp(e, listToReorder);
            if (index != -1) {
                nbToMove++;
                listToReorder.remove(e);
                listToReorder.add(index, e);
            } else {
                break;
            }
        }
        return (nbToMove > 0);
    }

    @objid ("26fe304d-aabb-48e7-a309-84f6a190f24f")
    private static int getIndexUp(ModelElement element, List listToReorder) {
        int index = listToReorder.indexOf(element);
        if (index < 1) {
            return -1;
        }
        index--;
        // Iterate until we find an element of the same metaclass or until we find the beginning of the list.
        while (index != -1 && listToReorder.get(index).getClass() != element.getClass()) {
            index--;
        }
        return index;
    }

}
