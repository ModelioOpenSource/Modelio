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
package org.modelio.diagram.editor.popup.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("0bd00c2b-dea1-42fa-955d-fedccc5c9891")
public class CreateEntryExitPointHandler extends AbstractDiagramCreateHandler {
    @objid ("81a30fc1-1392-4e0a-ad99-ff2686225430")
    @Override
    public boolean canExecute(@Named("org.eclipse.ui.selection") ISelection aSelection) {
        if (super.canExecute(aSelection)) {
            // Now lets have a look a some specific things:
            MObject selectedElement = getSelectedElement();
            
            if ((selectedElement instanceof State) && ((State) selectedElement).getSubMachine() != null) {
                return false;
            }           
        }
        return true;
    }

}
