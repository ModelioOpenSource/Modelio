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

package org.modelio.linkeditor.ext.depfilter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Stereotype;

@objid ("1b640798-5e33-11e2-b81d-002564c97630")
class FilterContentProvider implements PropertyChangeListener, ITreeContentProvider {
    @objid ("1b640799-5e33-11e2-b81d-002564c97630")
    private Map<ModuleComponent, Set<Stereotype>> model;

    @objid ("9bc0eef1-3b7e-4786-ac6c-d628691272e8")
    private TreeViewer treeViewer;

    @objid ("1b6407a2-5e33-11e2-b81d-002564c97630")
    @Override
    public void dispose() {
        this.treeViewer = null;
    }

    @objid ("1b6668c5-5e33-11e2-b81d-002564c97630")
    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        if (oldInput instanceof DialogModel) {
            ((DialogModel) oldInput).removePropertyChangeListener(this);
            this.model = null;
        }
        if (newInput instanceof DialogModel) {
            DialogModel dialogModel = (DialogModel) newInput;
            this.model = dialogModel.getFilterStereotypes();
            dialogModel.addPropertyChangeListener(this);
        }
        this.treeViewer = (TreeViewer) viewer;
    }

    @objid ("1b6668ce-5e33-11e2-b81d-002564c97630")
    @Override
    public Object[] getElements(final Object inputElement) {
        return this.model.keySet().toArray();
    }

    @objid ("1b6668d7-5e33-11e2-b81d-002564c97630")
    @Override
    public Object[] getChildren(final Object parentElement) {
        if (parentElement instanceof ModuleComponent &&
            this.model.get(parentElement) != null &&
            !this.model.get(parentElement).isEmpty()) {
            return this.model.get(parentElement).toArray();
        } else {
            return null;
        }
    }

    @objid ("1b6668df-5e33-11e2-b81d-002564c97630")
    @Override
    public Object getParent(final Object element) {
        return null;
    }

    @objid ("1b6668e6-5e33-11e2-b81d-002564c97630")
    @Override
    public boolean hasChildren(final Object element) {
        if (element instanceof ModuleComponent) {
            return this.model.get(element) != null && !this.model.get(element).isEmpty();
        }
        return false;
    }

    @objid ("1b6668ed-5e33-11e2-b81d-002564c97630")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        // TODO Finer update method for viewer
        if (this.treeViewer != null) {
            this.treeViewer.refresh();
            this.treeViewer.expandAll();
        }
    }

}
