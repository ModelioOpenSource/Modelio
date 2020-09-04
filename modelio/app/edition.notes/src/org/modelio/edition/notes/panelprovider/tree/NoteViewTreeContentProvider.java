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

package org.modelio.edition.notes.panelprovider.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * Content provider for the annotation view.
 */
@objid ("0ecb1752-4080-4939-be55-c38f1bbca3d5")
public class NoteViewTreeContentProvider implements ITreeContentProvider {
    @objid ("a7b299ff-9e03-4cdd-9daf-598da3ac1f7f")
    @Override
    public Object[] getElements(Object object) {
        List<Object> objects = new ArrayList<>();
        
        if (object instanceof ModelElement) {
            ModelElement me = (ModelElement) object;
        
            // Notes
            for (Note note : me.getDescriptor()) {
                if (note.getModel() != null && !note.getModel().isIsHidden()) {
                    objects.add(note);
                }
            }
        
            // Constraints
            if (me instanceof UmlModelElement) {
                for (Constraint constraint : ((UmlModelElement) me).getConstraintDefinition()) {
                    // if there are stereotypes and only hidden ones, the constraint is hidden
                    if (constraint.getExtension().size() > 0) {
                        for (Stereotype stereotype : constraint.getExtension()) {
                            if (!stereotype.isIsHidden()) {
                                objects.add(constraint);
                                break;
                            }
        
                        }
                    } else {
                        // not stereotype => visible constraint
                        objects.add(constraint);
                    }
                }
            }
            
            // Documents
            for (Document ex : me.getAttached(Document.class)) {
                if (!ex.getType().isIsHidden()) {
                    objects.add(ex);
                }
            }
        }
        return objects.toArray();
    }

    @objid ("fe406029-6100-4c58-ac1c-2a568e45d5b5")
    @Override
    public void dispose() {
        // Nothing to dispose
    }

    @objid ("5a7a405a-5e04-4ebf-91f0-6ddca7bbd342")
    @Override
    public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
        // Nothing to change
    }

    @objid ("876473ec-5667-4474-bf31-df840c848988")
    @Override
    public Object[] getChildren(Object parent) {
        return Collections.EMPTY_LIST.toArray();
    }

    @objid ("3cdc160e-9141-4b7c-89dc-81030de3e90d")
    @Override
    public Object getParent(Object child) {
        if (child instanceof ModelElement && !((ModelElement) child).isDeleted()) {
            return ((ModelElement) child).getCompositionOwner();
        } else {
            return null;
        }
    }

    @objid ("69ed0706-5bad-46c4-9b98-2740f5be9b93")
    @Override
    public boolean hasChildren(Object parent) {
        if (parent instanceof UmlModelElement) {
            UmlModelElement me = (UmlModelElement) parent;
            return (me.getAttached(Document.class).size() + me.getDescriptor().size() + me.getConstraintDefinition().size()) > 0;
        } else if (parent instanceof ModelElement) {
            ModelElement me = (ModelElement) parent;
            return (me.getAttached(Document.class).size() + me.getDescriptor().size()) > 0;
        }
        return false;
    }

}
