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

package org.modelio.editors.richnote.gui.creation.doctype;

import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Document type field content provider.
 */
@objid ("eeb4f800-d3e7-43b3-ac05-e762b82fcabb")
class DocTypeChooserContentProvider implements ITreeContentProvider {
    @objid ("1b2a6010-8330-4249-8a61-c16b0977dabc")
    @Override
    public void dispose() {
        // nothing to do
    }

    @objid ("c2b23bae-3f95-460b-be9d-5a5a78ad23f3")
    @Override
    public Object[] getChildren(Object parent) {
        if (parent instanceof DocTypeChooserModel) {
            DocTypeChooserModel model = (DocTypeChooserModel) parent;
            return model.getMdacAdapters().values().stream()
                    .map(module -> module.getAdapters())
                    .flatMap(List::stream)
                    .collect(Collectors.toList()).toArray();
        } else if (parent instanceof AdapterModule) {
            AdapterModule adapter = (AdapterModule) parent;
            return adapter.getAdapters().toArray();
        } else if (parent instanceof AdapterStereotype) {
            AdapterStereotype adapter = (AdapterStereotype) parent;
            return adapter.getAdapters().toArray();
        } else {
            return new Object[0];
        }
    }

    @objid ("28f0531e-525d-4ba6-bc05-df65bd9e56fa")
    @Override
    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

    @objid ("5181235b-3ec7-46fc-bc23-8d44dcf53a8e")
    @Override
    public Object getParent(Object child) {
        if (child instanceof AdapterRichNoteType) {
            AdapterRichNoteType adapter = (AdapterRichNoteType) child;
            return adapter.getParent();
        } else {
            return null;
        }
    }

    @objid ("bb53834a-d16c-47a0-82b5-7278190604ed")
    @Override
    public boolean hasChildren(Object parent) {
        if (parent instanceof DocTypeChooserModel) {
            DocTypeChooserModel model = (DocTypeChooserModel) parent;
            return model.getMdacAdapters().size() > 0;
        } else if (parent instanceof AdapterModule) {
            AdapterModule adapter = (AdapterModule) parent;
            return adapter.getAdapters().size() > 0;
        } else if (parent instanceof AdapterStereotype) {
            AdapterStereotype adapter = (AdapterStereotype) parent;
            return adapter.getAdapters().size() > 0;
        } else {
            return false;
        }
    }

    @objid ("d39fe58f-ea10-4597-ba57-db8d3b2b4911")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // ignore
    }

}
