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

package org.modelio.diagram.elements.core.commands;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command that deletes a model element from the model.
 * 
 * @author cmarin
 */
@objid ("7f3e3f2b-1dec-11e2-8cad-001ec947c8cc")
public class DeleteInModelCommand extends Command {
    @objid ("7f3e3f2f-1dec-11e2-8cad-001ec947c8cc")
    private Collection<MObject> toDelete = new ArrayList<>();

    /**
     * Create a delete command.
     */
    @objid ("7f3e3f32-1dec-11e2-8cad-001ec947c8cc")
    public DeleteInModelCommand() {
    }

    /**
     * Create a delete command.
     * 
     * @param toDelete The element to delete.
     */
    @objid ("7f3e3f35-1dec-11e2-8cad-001ec947c8cc")
    public DeleteInModelCommand(MObject toDelete) {
        this.toDelete.add(toDelete);
    }

    @objid ("7f3e3f39-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        for (MObject el : this.toDelete) {
            if (!el.isDeleted()) {
                el.delete();
            }
        }
    }

    /**
     * Add an element to delete.
     * 
     * @param el an element to delete.
     */
    @objid ("7f3e3f3c-1dec-11e2-8cad-001ec947c8cc")
    public void addElementToDelete(MObject el) {
        this.toDelete.add(el);
    }

    /**
     * The command is executable if all elements to delete are modifiable/deletable.
     */
    @objid ("7f40a145-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        for (MObject el : this.toDelete) {
            final MObject owner = el.getCompositionOwner();
        
            // Standard rules: The element must be modifiable and either its owner is modifiable OR the element is a CMS node that is not a RAMC and
            // not CMS Managed. If any of these condition is not met, return false!
            if (!MTools.getAuthTool().canRemoveFrom(el, owner)) {
                return false;
            } else if (el instanceof Package && owner instanceof Project) {
                // Never delete root package
                return false;
            }
        }
        return true;
    }

}
