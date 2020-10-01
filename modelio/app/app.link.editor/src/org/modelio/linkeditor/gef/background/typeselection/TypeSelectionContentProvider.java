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

package org.modelio.linkeditor.gef.background.typeselection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.linkeditor.LinkTypeDescriptor;
import org.modelio.linkeditor.plugin.LinkEditor;

@objid ("1b960430-5e33-11e2-b81d-002564c97630")
class TypeSelectionContentProvider implements ITreeContentProvider {
    @objid ("9fa56836-3bb8-41aa-8df1-e09decf73907")
    private Map<Object, List<LinkTypeDescriptor>> types = new HashMap<>();

    @objid ("1b960436-5e33-11e2-b81d-002564c97630")
    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        // Parse the stereotype and establish a grouping based on the modules providing the stereotypes
        this.types.clear();
        
        TypeSelectionModel selectionModel = (TypeSelectionModel) newInput;
        if (selectionModel != null) {
            for (LinkTypeDescriptor d : selectionModel.getTypes()) {
                Object group = (d.getStereotype() != null) ? d.getStereotype().getModule() : LinkEditor.I18N.getString("TypeSelectionPopup.Metaclass");
                List<LinkTypeDescriptor> descriptors = this.types.get(group);
                if (descriptors == null) {
                    descriptors = new ArrayList<>();
                    this.types.put(group, descriptors);
                }
                descriptors.add(d);
            }
        }
    }

    @objid ("1b96043f-5e33-11e2-b81d-002564c97630")
    @Override
    public void dispose() {
        // Nothing to do.
    }

    @objid ("1b960442-5e33-11e2-b81d-002564c97630")
    @Override
    public boolean hasChildren(final Object element) {
        return !this.types.getOrDefault(element, Collections.EMPTY_LIST).isEmpty();
    }

    @objid ("1b960449-5e33-11e2-b81d-002564c97630")
    @Override
    public Object getParent(final Object element) {
        return null;
    }

    @objid ("1b960450-5e33-11e2-b81d-002564c97630")
    @Override
    public Object[] getElements(final Object inputElement) {
        return this.types.keySet().toArray();
    }

    @objid ("1b960459-5e33-11e2-b81d-002564c97630")
    @Override
    public Object[] getChildren(final Object element) {
        return this.types.getOrDefault(element, Collections.EMPTY_LIST).toArray();
    }

}
