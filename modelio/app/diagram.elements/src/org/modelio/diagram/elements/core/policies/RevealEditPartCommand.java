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

package org.modelio.diagram.elements.core.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * Simple command that makes an {@link EditPart}'s figure visible in the viewer.
 * 
 * @author fpoyer
 */
@objid ("80d5f124-1dec-11e2-8cad-001ec947c8cc")
public class RevealEditPartCommand extends Command {
    @objid ("ff2d596e-7f38-4325-8050-088613101185")
    private EditPart editPartToSelect;

    /**
     * C'tor.
     * 
     * @param editPartToSelect the {@link EditPart} to select.
     */
    @objid ("80d5f12b-1dec-11e2-8cad-001ec947c8cc")
    public RevealEditPartCommand(EditPart editPartToSelect) {
        this.editPartToSelect = editPartToSelect;
    }

    @objid ("80d5f131-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        this.editPartToSelect.getViewer().reveal(this.editPartToSelect);
        this.editPartToSelect.getViewer().setSelection(new StructuredSelection(this.editPartToSelect));
    }

}
