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
package org.modelio.uml.statikdiagram.editor.elements.namespacinglink.redraw;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.uml.statikdiagram.editor.elements.namespacinglink.CompositionLinkEditPart;

@objid ("a62a270e-85fc-4f32-8728-f8c864f15fce")
public class RedrawLinkCommandPropertyTester extends PropertyTester {
    /**
     * Default constructor.
     */
    @objid ("1afd3424-916b-4682-a51f-2106df48a524")
    public  RedrawLinkCommandPropertyTester() {
        // nothing
    }

    @objid ("01580afc-0283-440a-84ed-1f6571f350a8")
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (!(receiver instanceof IStructuredSelection)) {
            return false;
        }
        
        final IStructuredSelection selection = (IStructuredSelection) receiver;
        Object[] elements = selection.toArray();
        if (elements.length != 1) return false;
        Object element = selection.getFirstElement();
        switch (property) {
        case "redrawlink":
            // true is all selected element are GmLinks
            if (element instanceof LinkEditPart || element instanceof CompositionLinkEditPart) {
                return true;
            }
            return false;
        
        default:
            throw new IllegalArgumentException(property + " property not supported by " + getClass().getSimpleName());
        }
        
    }

    @objid ("e824f5ff-2471-41f5-85b0-ce5c670cc8da")
    private ModelElement getSelectedElement(final IStructuredSelection selection) {
        if (selection.size() == 1) {
            Object element = selection.getFirstElement();
            if (element instanceof ModelElement) {
                return (ModelElement) element;
            } else if (element instanceof IAdaptable) {
                return ((IAdaptable) element).getAdapter(ModelElement.class);
            }
        }
        return null;
    }

}
