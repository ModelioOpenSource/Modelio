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

package org.modelio.diagram.elements.common.abstractdiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.vcore.model.api.MTools;

/**
 * Specific command that will unmask a constraint and the link(s) between it and the constrained model element(s).
 * 
 * @author fpoyer
 */
@objid ("7e2024c2-1dec-11e2-8cad-001ec947c8cc")
public class UnmaskConstraintCommand extends Command {
    @objid ("7e2024c6-1dec-11e2-8cad-001ec947c8cc")
    private Constraint theConstraint;

    @objid ("7e2024c7-1dec-11e2-8cad-001ec947c8cc")
    private Object constraint;

    @objid ("7e2024c8-1dec-11e2-8cad-001ec947c8cc")
    private IGmDiagram diagram;

    /**
     * C'tor.
     * 
     * @param theConstraint the constraint to unmask.
     * @param host the edit part of the diagram in which to unmask it.
     * @param initialLayoutData the initial layout data for the node part
     */
    @objid ("7e2024c9-1dec-11e2-8cad-001ec947c8cc")
    public UnmaskConstraintCommand(final Constraint theConstraint, final AbstractDiagramEditPart host, final Object initialLayoutData) {
        this.theConstraint = theConstraint;
        this.diagram = (IGmDiagram) host.getModel();
        this.constraint = initialLayoutData;
    }

    @objid ("7e2024d2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        this.diagram.unmaskAsChild(this.theConstraint, this.constraint);
    }

    @objid ("7e2024d5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        // the diagram must be modifiable
        return MTools.getAuthTool().canModify(this.diagram.getRelatedElement());
    }

}
