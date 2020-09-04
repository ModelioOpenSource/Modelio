/* 
 * Copyright 2013-2018 Modeliosoft
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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.vcore.model.api.MTools;

/**
 * A command that reorders children of a composite node.
 * 
 * @author fpoyer
 */
@objid ("7f0c2d8d-1dec-11e2-8cad-001ec947c8cc")
public class ReorderChildrenCommand extends Command {
    @objid ("7f0c2d91-1dec-11e2-8cad-001ec947c8cc")
    private GmCompositeNode container;

    @objid ("7f0c2d92-1dec-11e2-8cad-001ec947c8cc")
    private GmNodeModel childToMove;

    @objid ("7f0c2d93-1dec-11e2-8cad-001ec947c8cc")
    private GmNodeModel reference;

    @objid ("7f0c2d94-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        // this.container.removeChild(this.childToMove);
        this.container.moveChild(this.childToMove, this.container.getChildren().indexOf(this.reference));
    }

    /**
     * C'tor.
     * @param container the container in which children will be moved.
     * @param childToMove the child to move.
     * @param reference the reference: moved child will be moved just before this reference. If it is null, child will be
     * moved at the end of the container.
     */
    @objid ("7f0c2d97-1dec-11e2-8cad-001ec947c8cc")
    public ReorderChildrenCommand(GmCompositeNode container, GmNodeModel childToMove, GmNodeModel reference) {
        this.container = container;
        this.childToMove = childToMove;
        this.reference = reference;
    }

    @objid ("7f0c2d9d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        final IGmDiagram diagram = this.container.getDiagram();
        return (MTools.getAuthTool().canModify(diagram.getRelatedElement()));
    }

}
