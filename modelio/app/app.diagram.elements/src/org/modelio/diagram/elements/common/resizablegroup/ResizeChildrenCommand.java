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

package org.modelio.diagram.elements.common.resizablegroup;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.vcore.model.api.MTools;

/**
 * Specific command that resize all children at once.
 * 
 * @author fpoyer
 */
@objid ("7f181994-1dec-11e2-8cad-001ec947c8cc")
public class ResizeChildrenCommand extends Command {
    @objid ("7f181999-1dec-11e2-8cad-001ec947c8cc")
    private Map<GmNodeModel, Integer> newConstraints;

    @objid ("7f181998-1dec-11e2-8cad-001ec947c8cc")
    private GmResizableGroup container;

    /**
     * Constructor.
     * 
     * @param container the partition container.
     */
    @objid ("7f1a7ba9-1dec-11e2-8cad-001ec947c8cc")
    public ResizeChildrenCommand(GmResizableGroup container) {
        this.container = container;
    }

    /**
     * Set the constraints that must be changed.
     * 
     * @param newConstraints the constraints that must be changed.
     */
    @objid ("7f1a7bad-1dec-11e2-8cad-001ec947c8cc")
    public void setNewConstraints(Map<GmNodeModel, Integer> newConstraints) {
        this.newConstraints = newConstraints;
    }

    @objid ("7f1a7bb5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        for (GmNodeModel child : this.container.getChildren()) {
            Integer i = this.newConstraints.get(child);
            if (i != null) {
                child.setLayoutData(i);
            }
        }
    }

    @objid ("7f1a7bb8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        final IGmDiagram diagram = this.container.getDiagram();
        return (MTools.getAuthTool().canModify(diagram.getRelatedElement()));
    }

}
