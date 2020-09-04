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

package org.modelio.diagram.elements.core.link.extensions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.vcore.model.api.MTools;

/**
 * Command that updates the location of a GmLink extension.
 */
@objid ("7ff5d3fe-1dec-11e2-8cad-001ec947c8cc")
public class ChangeExtensionLocationCommand extends Command {
    @objid ("7ff5d402-1dec-11e2-8cad-001ec947c8cc")
    private GmNodeModel model;

    @objid ("7ff5d403-1dec-11e2-8cad-001ec947c8cc")
    private IGmLocator layoutData;

    @objid ("7ff5d404-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        if (this.model != null) {
            this.model.getParentLink().setLayoutConstraint(this.model, this.layoutData);
        }
    }

    /**
     * Set the constraint that will be the new layoutData.
     * 
     * @param constraint the new constraint.
     */
    @objid ("7ff5d407-1dec-11e2-8cad-001ec947c8cc")
    public void setConstraint(IGmLocator constraint) {
        this.layoutData = constraint;
    }

    /**
     * Set the model that will be updated.
     * 
     * @param model the model to update. Must be a {@link GmNodeModel}
     */
    @objid ("7ff5d40b-1dec-11e2-8cad-001ec947c8cc")
    public void setModel(GmNodeModel model) {
        this.model = model;
    }

    @objid ("7ff5d40f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        final IGmDiagram diagram = this.model.getDiagram();
        return (MTools.getAuthTool().canModify(diagram.getRelatedElement()));
    }

}
