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

package org.modelio.edition.notes.panelprovider.helpers;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("db2befb3-ce20-429c-b3c9-27bd2d1fface")
public class CleanNoteContentHelper extends AbstractHelper {
    @objid ("4f312c44-81b8-4973-82e5-066089f77d89")
    public static boolean canExecute(List<ModelElement> selectedItems) {
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

    @objid ("8dcae511-df5e-41bb-950d-8918108ed974")
    public boolean execute(ModelElement element, List<ModelElement> selectedItems) {
        if (selectedItems.size() == 1) {
            ModelElement note = selectedItems.get(0);
            if (note instanceof Note) {
                ((Note) note).setContent("");
            } else if (note instanceof Constraint) {
                ((Constraint) note).setBody("");
            } else if (note instanceof Document) {
                ((Document) note).setAbstract("");
            }
            return true;
        }
        return false;
    }

    @objid ("96c2f5c0-99cb-457a-b3eb-6d74dd918206")
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
