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

package org.modelio.diagram.browser.model.related;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * The related content provider returns the diagram related to the current
 * element. The currentElement changes with the selection in Modelio
 */
@objid ("386664d5-cbe6-4949-ad22-485694125622")
public class RelatedContentProvider implements ITreeContentProvider {
    @objid ("d2aeb408-cfea-444f-88a3-502cece02b8a")
    @Override
    public void inputChanged(Viewer v, Object oldInput, Object newInput) {
        // nothing to do
    }

    @objid ("0722b10c-1034-427e-b3e0-872a3e329ce3")
    @Override
    public void dispose() {
        // Empty
    }

    @objid ("e777a4a4-88bd-4f9a-b1ad-8168d246de04")
    @Override
    public Object[] getElements(Object inputElement) {
        final List<AbstractDiagram> relatedDiagrams = new ArrayList<>();
        if (inputElement instanceof ModelElement) {
            ModelElement me = (ModelElement) inputElement;
            // Get owned diagrams
            relatedDiagrams.addAll(me.getProduct());
            // Diagrams where the element appears
            relatedDiagrams.addAll(me.getDiagramElement());
        }
        return relatedDiagrams.toArray();
    }

    @objid ("cf2c70e5-f536-4d7d-bf0c-76049e1e4ac8")
    @Override
    public Object getParent(Object child) {
        return null;
    }

    @objid ("0fb2417f-3725-47a6-aa37-9d53eab47dd2")
    @Override
    public Object[] getChildren(Object parent) {
        return new Object[0];
    }

    @objid ("3f79bd3c-89a2-4445-b857-0dcde5c89101")
    @Override
    public boolean hasChildren(Object parent) {
        return false;
    }

    @objid ("a54d3361-d128-44d9-a20c-7e18f11c6b5b")
    public RelatedContentProvider() {
    }

}
