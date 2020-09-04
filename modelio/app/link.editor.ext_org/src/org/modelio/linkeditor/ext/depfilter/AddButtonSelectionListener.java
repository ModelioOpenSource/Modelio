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

package org.modelio.linkeditor.ext.depfilter;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Stereotype;

@objid ("1b5ce355-5e33-11e2-b81d-002564c97630")
final class AddButtonSelectionListener extends SelectionAdapter {
    @objid ("1b5ce356-5e33-11e2-b81d-002564c97630")
    private final DialogView dialogView;

    @objid ("1b5ce358-5e33-11e2-b81d-002564c97630")
    public AddButtonSelectionListener(final DialogView dialogView) {
        super();
        this.dialogView = dialogView;
    }

    @objid ("1b5ce35c-5e33-11e2-b81d-002564c97630")
    @Override
    public void widgetSelected(final SelectionEvent e) {
        ISelection selection = this.dialogView.getLeftTree().getSelection();
        if (selection instanceof IStructuredSelection) {
            List<?> selectedObjects = ((IStructuredSelection) selection).toList();
            for (Object selectedObject : selectedObjects) {
                if (selectedObject instanceof ModuleComponent) {
                    this.dialogView.getModel().addToFilter((ModuleComponent) selectedObject);
                } else if (selectedObject instanceof Stereotype) {
                    this.dialogView.getModel().addToFilter((Stereotype) selectedObject);
                }
            }
        }
    }

}
