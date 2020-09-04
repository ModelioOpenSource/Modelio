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

package org.modelio.diagram.editor.statik.corexp;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.editor.statik.elements.namespacinglink.CompositionLinkEditPart;

/**
 * Core Expression property tester for diagram nodes and composition links.
 */
@objid ("3ca071d9-6f90-4d4a-bf09-fba788e256f8")
public class CompositionLinkTester extends PropertyTester {
    @objid ("c01d990e-c91d-4c54-81bc-27f19be58de5")
    private static final String IS_COMPOSITON_LINK = "iscompositionlink";

    @objid ("f90120d9-13ad-476d-a00c-f8643e7271ef")
    public CompositionLinkTester() {
        // nothing
    }

    @objid ("05d9638a-8082-491b-a1fc-2160c895aee1")
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (!(receiver instanceof IStructuredSelection)) {
            return false;
        }
        
        final IStructuredSelection selection = (IStructuredSelection) receiver;
        Object[] elements = selection.toArray();
        switch (property) {
        case IS_COMPOSITON_LINK:
            // true is all selected element not CompositionLink
            for (Object element : elements) {
                if (element instanceof CompositionLinkEditPart) {
                    return false;
                }
            }
            return true;
        default:
            throw new IllegalArgumentException(property + " property not supported by " + getClass().getSimpleName());
        }
    }

}
