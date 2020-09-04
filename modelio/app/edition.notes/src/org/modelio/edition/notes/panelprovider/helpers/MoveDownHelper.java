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
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("e3b32076-33ec-4563-a45e-477558e6fd35")
public class MoveDownHelper extends AbstractHelper {
    @objid ("1dabdbe0-f8d0-4fbd-8757-9e7faa17fd91")
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

    @objid ("b48dd6df-53c8-4bf1-84d5-84be0b7e2615")
    public boolean execute(ModelElement element, List<ModelElement> selectedItems) {
        int nbToMove = 0;
        
        List listToReorder = new ArrayList<>();
        if (selectedItems.get(0) instanceof Note) {
            listToReorder = element.getDescriptor(); // list of Notes
        } else if (selectedItems.get(0) instanceof Constraint) {
            listToReorder = ((UmlModelElement) element).getConstraintDefinition(); // list of Constraints
        } else if (selectedItems.get(0) instanceof Document) {
            listToReorder = element.getAttached(); // list of Documents
        }
        // We first move down the Last selected element of the list; This way the positions of other
        // selected elements are not affected by the move of the current element.
        for (int i = selectedItems.size() - 1; i >= 0; --i) {
            ModelElement e = selectedItems.get(i);
        
            // Retrieve the new index of the element
            int index = MoveDownHelper.computeNewIndex(e, listToReorder);
        
            if (index == -1) {
                // Invalid case, just exit
                return false;
            }
        
            // Move the element in the list
            nbToMove++;
            listToReorder.remove(e);
            listToReorder.add(index, e);
        }
        return (nbToMove > 0);
    }

    @objid ("235eaa39-1b2a-4879-b9cc-d327fe433217")
    private static int computeNewIndex(ModelElement element, List<MObject> listToReorder) {
        int index = listToReorder.indexOf(element) + 1;
        
        // Iterate until we find an element of the same metaclass or until we find the end of the list.
        while (index < listToReorder.size() && listToReorder.get(index).getClass() != element.getClass()) {
            index++;
        }
        
        // If that would move outside of the list, that means element is already the last one.
        if (index >= listToReorder.size()) {
            return -1;
        }
        return index;
    }

}
