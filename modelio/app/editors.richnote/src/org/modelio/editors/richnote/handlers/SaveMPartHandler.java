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

package org.modelio.editors.richnote.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

/**
 * Handler for the save button.
 */
@objid ("28587283-67f4-4121-a27c-f6889620f76f")
public class SaveMPartHandler {
    @objid ("0b409fd9-d3fc-4050-957e-a05cd2983051")
    @Execute
    void execute(MPart part, EPartService partSvc) {
        partSvc.savePart(part, false);
    }

    @objid ("3ac2cc91-2953-4c0f-81fd-35362a7005cd")
    @CanExecute
    public boolean canExecute(MPart part) {
        return part.isDirty();
    }

}
