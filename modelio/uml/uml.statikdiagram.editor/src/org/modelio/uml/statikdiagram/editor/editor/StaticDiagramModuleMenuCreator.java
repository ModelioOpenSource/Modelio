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
/**
 *
 */
package org.modelio.uml.statikdiagram.editor.editor;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.editor.context.ModuleMenuCreator;
import org.modelio.uml.statikdiagram.editor.elements.namespacinglink.CompositionLinkEditPart;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("4b76a9d4-7354-46c4-a487-5ae08c2f3468")
public class StaticDiagramModuleMenuCreator extends ModuleMenuCreator {
    @objid ("82f39754-7419-4308-ab06-567e2b24bb01")
    @Override
    public void aboutToShow(List<MMenuElement> items) {
        if (getSelectedElement() != null) {
            super.aboutToShow(items);
        }
        
    }

    @objid ("6c7da2b2-8d82-4327-aabb-ca6990533424")
    private MObject getSelectedElement() {
        // Get the active selection from the application, to avoid context-related issues when opening the same diagram several times...
        IStructuredSelection selection = (IStructuredSelection) this.application.getContext().get(IServiceConstants.ACTIVE_SELECTION);
        if (selection == null || selection.size() != 1) {
            return null;
        }
        
        final Object obj = selection.getFirstElement();
        if (obj instanceof MObject) {
            return (MObject) obj;
        } else if (obj instanceof CompositionLinkEditPart) {
            return null;
        } else if (obj instanceof IAdaptable) {
            IAdaptable adaptable = (IAdaptable) obj;
            return adaptable.getAdapter(MObject.class);
        }
        return null;
    }

}
